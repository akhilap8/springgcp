package com.strawberry.practgcp.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.BigQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BigQueryConfig {

    @Value("${google.cloud.credentials.location}")
    private String bigQueryFile;

    @Value("${google.cloud.project-id}")
    private String projectId;

    @Bean
    public BigQuery bigQuery() throws java.io.IOException {
        GoogleCredentials credentials = com.google.auth.oauth2.GoogleCredentials.fromStream(new java.io.FileInputStream(bigQueryFile));
        return com.google.cloud.bigquery.BigQueryOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId(projectId)
                .build()
                .getService();
    }

}
