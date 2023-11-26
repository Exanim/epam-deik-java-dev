package com.epam.training.ticketservice.core.booking.persistence;

import com.epam.training.ticketservice.core.booking.model.BookingDto;
import com.epam.training.ticketservice.core.movie.persistence.Movie;
import com.epam.training.ticketservice.core.room.persistence.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Screenings")
public class Booking {

    private Movie movie;
    private Room room;
    private LocalDateTime dateTime;

    public Booking(BookingDto bookingDto) {
        this.movie = bookingDto.getMovie();
        this.room = bookingDto.getRoom();
        this.dateTime = bookingDto.getDateTime();
    }

}
