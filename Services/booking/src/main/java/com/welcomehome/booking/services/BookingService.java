package com.welcomehome.booking.services;

import com.welcomehome.booking.data.Booking;
import com.welcomehome.booking.data.BookingRequestDto;

public interface BookingService {
    String confirmBooking(BookingRequestDto bookingRequestDto);
}
