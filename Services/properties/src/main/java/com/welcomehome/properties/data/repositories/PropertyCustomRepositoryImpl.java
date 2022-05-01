package com.welcomehome.properties.data.repositories;

import com.couchbase.client.java.query.QueryResult;
import com.welcomehome.properties.configuration.CouchbaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PropertyCustomRepositoryImpl implements PropertyCustomRepository {

    @Autowired
    private CouchbaseConfiguration couchbaseConfiguration;

    @Override
    public Integer getMaxPropertyId(){
        Integer maxId = 0;
        try{
            String maxIdQuery = "select max(meta().id) max_id from `Properties`";

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
