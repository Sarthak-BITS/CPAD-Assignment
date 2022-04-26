package com.welcomehome.personas.configuration;

import com.couchbase.client.core.env.Authenticator;
import com.couchbase.client.core.env.PasswordAuthenticator;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

    @Value("${spring.couchbase.bootstrap-hosts}")
    private String hostname;

    @Value("${spring.couchbase.bucket.name}")
    private String bucketName;

    @Value("${spring.couchbase.user.name}")
    private String userName;

    @Value("${spring.couchbase.bucket.password}")
    private String password;

    @Override
    public String getConnectionString() {
        return hostname;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getBucketName() {
        return bucketName;
    }

    @Override
    protected Authenticator authenticator(){
        return PasswordAuthenticator.builder()
                .username(this.getUserName())
                .password(this.getPassword())
                .onlyEnablePlainSaslMechanism()
                .build();
    }

    @Bean
    public Cluster cluster(){
        Cluster cluster = Cluster.connect(getConnectionString(), ClusterOptions.clusterOptions(authenticator()));
        return cluster;
    }

    @Bean
    public Bucket bucket(){
        Bucket bucket = cluster().bucket(getBucketName());
        return bucket;
    }
}
