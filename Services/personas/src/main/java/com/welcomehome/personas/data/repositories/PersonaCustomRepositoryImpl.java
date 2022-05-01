package com.welcomehome.personas.data.repositories;

import com.couchbase.client.java.query.QueryResult;
import com.welcomehome.personas.configuration.CouchbaseConfiguration;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class PersonaCustomRepositoryImpl implements PersonaCustomRepository {

    @Autowired
    private CouchbaseConfiguration couchbaseConfiguration;

    @Override
    public Integer getMaxPersonaId(){
        Integer maxId = 0;
        try{
            String maxIdQuery = "select max(meta().id) max_id from `Personas`";

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
