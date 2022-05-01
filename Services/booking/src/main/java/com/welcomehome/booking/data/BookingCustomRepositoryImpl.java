package com.welcomehome.booking.data;

import com.couchbase.client.java.query.QueryResult;
import com.welcomehome.booking.configuration.CouchbaseConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class BookingCustomRepositoryImpl implements BookingCustomRepository {

    @Autowired
    private CouchbaseConfiguration couchbaseConfiguration;

    @Override
    public Integer getMaxBookingId(){
        Integer maxId = 0;
        try{
            String maxIdQuery = "select max(meta().id) max_id from `Booking`";

            QueryResult maxIdResult = couchbaseConfiguration.cluster()
                    .query(maxIdQuery);

            maxId = Integer.parseInt(maxIdResult.rowsAsObject().get(0).get("max_id").toString());
        } catch (Exception e){
            e.getMessage();
            return null;
        }
        return maxId;
    }
}
