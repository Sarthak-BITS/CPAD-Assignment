package com.welcomehome.booking.services;

import com.welcomehome.booking.data.Booking;
import com.welcomehome.booking.data.BookingCustomRepositoryImpl;
import com.welcomehome.booking.data.BookingRepository;
import com.welcomehome.booking.data.BookingRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingCustomRepositoryImpl bookingCustomRepository;

    @Override
    public String confirmBooking(BookingRequestDto bookingRequestDto){
        String status = null;
        Integer newBookingId = 0;

        try {

            newBookingId = bookingCustomRepository.getMaxBookingId();
            if(newBookingId == null){
                newBookingId = 1;
            } else {
                newBookingId = newBookingId + 1;
            }
            Booking booking = new Booking(newBookingId,
                    bookingRequestDto.getCustomerId(),
                    bookingRequestDto.getAgentId(),
                    bookingRequestDto.getPropertyId());

            Booking booking1 = bookingRepository.save(booking);
            if (booking1 != null) {
                status = "Booking confirmed.";
            }

        } catch (Exception e){
            log.error(e.getMessage());
            status = "Booking failed. Please reconfirm.";
        }
        return status;
    }
}
