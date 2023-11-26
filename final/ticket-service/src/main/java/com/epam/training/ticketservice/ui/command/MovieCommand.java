package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
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
public class MovieCommand {

    private final MovieService movieService;
    private final UserService userService;

    @ShellMethod(key = "list movies", value = "List the available products.")
    public List<MovieDto> listMovies() {
        return movieService.getMovieList();
    }
    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "create movie", value = "Create a new product.")
    public MovieDto createMovie(String name, String genre, Integer duration) {
        MovieDto movieDto = MovieDto.builder()
                .withName(name)
                .withGenre(genre)
                .withDuration(duration)
                .build();
        movieService.createMovie(movieDto);
        return movieDto;
    }
    @ShellMethodAvailability("isAvailable")
    @ShellMethod(key = "update movie", value = "Create a new product.")
    public MovieDto updateMovie(String name, String genre, Integer duration) {
        movieService.deleteMovie(name);
        MovieDto movieDto = MovieDto.builder()
                .withName(name)
                .withGenre(genre)
                .withDuration(duration)
                .build();
        movieService.createMovie(movieDto);
        return movieDto;
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
