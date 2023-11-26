package com.epam.training.ticketservice.core.booking;


import com.epam.training.ticketservice.core.booking.model.BookingDto;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    List<BookingDto> getBookingList();

    void createBooking(BookingDto bookingDto);
    void deleteBooking(BookingDto bookingDto);
}
