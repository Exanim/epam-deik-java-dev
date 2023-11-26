package com.epam.training.ticketservice.core.room;

import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistence.Room;
import com.epam.training.ticketservice.core.room.persistence.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<RoomDto> getRoomList() {
        return roomRepository.findAll()
            .stream()
            .map(this::createEntityFromDto)
            .toList();
    }

    @Override
    public Optional<RoomDto> getRoomByName(String roomName) {
        return createEntityFromDto(roomRepository.findByName(roomName));
    }

    public void createRoom(RoomDto roomDto) {
        Room room = new Room(roomDto);
        roomRepository.save(room);
    }

    private RoomDto createEntityFromDto(Room room) {
        return RoomDto.builder()
            .withName(room.getName())
            .withRow(room.getRow())
            .withCol(room.getCol())
            .build();
    }

    private Optional<RoomDto> createEntityFromDto(Optional<Room> product) {
        return product.map(this::createEntityFromDto);
    }
}
