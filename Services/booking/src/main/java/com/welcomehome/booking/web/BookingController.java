package com.welcomehome.booking.web;

import com.welcomehome.booking.data.BookingRequestDto;
import com.welcomehome.booking.services.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/booking")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<String> createBooking(@RequestBody BookingRequestDto bookingRequestDto){
        ResponseEntity responseEntity = null;
        String status = null;
        try {
            status = bookingService.confirmBooking(bookingRequestDto);
            if(status.toLowerCase().contains("fail")){
                responseEntity = new ResponseEntity<>(status, HttpStatus.NOT_IMPLEMENTED);
            } else {
                responseEntity = new ResponseEntity<>(status, HttpStatus.CREATED);
            }
        } catch (Exception e){
            log.error(e.getMessage());
            responseEntity = new ResponseEntity<>("Booking request failed.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
