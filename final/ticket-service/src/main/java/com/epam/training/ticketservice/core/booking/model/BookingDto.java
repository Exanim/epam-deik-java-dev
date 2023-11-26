package com.epam.training.ticketservice.core.booking.model;

import com.epam.training.ticketservice.core.movie.persistence.Movie;
import com.epam.training.ticketservice.core.room.persistence.Room;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class BookingDto {

    Movie movie;
    Room room;
    LocalDateTime dateTime;


    public static BookingBuilder builder() {
        return new BookingBuilder();
    }

    public static class BookingBuilder {

        Movie movie;
        Room room;
        LocalDateTime dateTime;

        public BookingBuilder withMovie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public BookingBuilder withRoom(Room room) {
            this.room = room;
            return this;
        }

        public BookingBuilder withDateTime(String dateTime) {
            dateTime = dateTime.replace(" ", "T");
            this.dateTime = LocalDateTime.parse(dateTime);
            return this;
        }

        public BookingBuilder withDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public BookingDto build() {
            return new BookingDto(movie, room, dateTime);
        }
    }
}

