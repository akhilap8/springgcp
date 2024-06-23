package com.strawberry.practgcp.service;

import com.google.cloud.bigquery.TableResult;
import com.strawberry.practgcp.client.BigQueryClient;
import com.strawberry.practgcp.model.Wo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class WoService {

    @Autowired
    private BigQueryClient bigQueryClient;

    @Value("${google.cloud.project-id}")
    private String projectId;

    @Value("${google.cloud.dataset-id}")
    private String datasetId;

    private String tableName;

    @PostConstruct
    private void init() {
        this.tableName = String.format("`%s.%s.fbwo`", projectId, datasetId);
    }

    // get
    public List<Wo> getAll() {
        String query = String.format("SELECT * FROM %s order by date", tableName);
        TableResult result = bigQueryClient.query(query);
        List<Wo> woList = new ArrayList<>();
        result.iterateAll().forEach(row -> {
            String date = row.get("date").getStringValue();
            String type = row.get("type").getStringValue();
            String workout = row.get("workout").getStringValue();
            Integer difficulty = row.get("difficulty").getNumericValue().intValue();
            Integer calories = row.get("calories").getNumericValue().intValue();
            Wo wo = new Wo(date, type, workout, difficulty, calories);
            woList.add(wo);
        });
        return woList;
    }

    public List<Wo> findDateRange(String start, String end) {
        // String query2 = String.format("SELECT * FROM %s WHERE date between '2021-07-25' and '2021-07-28' order by date", tableName);
        String query2 = String.format("SELECT * FROM %s WHERE date between '%s' and '%s' order by date", tableName, start, end);
        TableResult result = bigQueryClient.query(query2);
        List<Wo> woList2 = new ArrayList<>();
        result.iterateAll().forEach(row -> {
            String date = row.get("date").getStringValue();
            String type = row.get("type").getStringValue();
            String workout = row.get("workout").getStringValue();
            Integer difficulty = row.get("difficulty").getNumericValue().intValue();
            Integer calories = row.get("calories").getNumericValue().intValue();
            Wo wo = new Wo(date, type, workout, difficulty, calories);
            woList2.add(wo);
        });
        return woList2;
    }

}
