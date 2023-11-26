package com.epam.training.ticketservice.core.booking;

import com.epam.training.ticketservice.core.booking.model.BookingDto;
import com.epam.training.ticketservice.core.booking.persistence.BookingRepository;
import com.epam.training.ticketservice.core.movie.persistence.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.epam.training.ticketservice.core.booking.persistence.Booking;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public List<BookingDto> getBookingList() {
        return bookingRepository.findAll()
                .stream()
                .map(this::createEntityFromDto)
                .toList();
    }

    private BookingDto createEntityFromDto(Booking booking) {
        return BookingDto.builder()
                .withMovie(booking.getMovie())
                .withRoom(booking.getRoom())
                .withDateTime(booking.getDateTime())
                .build();
    }

    @Override
    public void createBooking(BookingDto bookingDto) {
        Booking booking = new Booking(bookingDto);
        bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(BookingDto bookingDto) {
    }
}