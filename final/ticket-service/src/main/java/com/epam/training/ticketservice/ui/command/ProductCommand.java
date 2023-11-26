package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.finance.money.Money;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.User;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

@ShellComponent
@RequiredArgsConstructor
public class ProductCommand {

    private final RoomService roomService;
    private final UserService userService;

    @ShellMethod(key = "user product list", value = "List the available products.")
    public List<RoomDto> listProduct() {
        return roomService.getRoomList();
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "admin product create", value = "Create a new product.")
    public RoomDto createProduct(String name, Double amount) {
        RoomDto roomDto = RoomDto.builder()
            .withName(name)
            .withsize(new Money(amount, Currency.getInstance("HUF")))
            .build();
        roomService.createRoom(roomDto);
        return roomDto;
    }

    private Availability isAvailable() {
        Optional<UserDto> user = userService.describe();
        if (user.isEmpty()) {
            return Availability.unavailable("You are not logged in!");
        } else if (user.get().role() != User.Role.ADMIN) {
            return Availability.unavailable("You are not an admin!");
        }
        return Availability.available();
    }
}
