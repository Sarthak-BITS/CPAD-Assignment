package com.welcomehome.booking.data;


import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface BookingRepository extends CouchbaseRepository<Booking, Integer> {
}
