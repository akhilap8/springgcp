package com.strawberry.practgcp.model;

public record Wo(
        String date,
        String type,
        String workout,
        Integer difficulty,
        Integer calories
) { }

//import com.google.cloud.bigquery.QueryParameterValue;
//import lombok.*;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Wo {
//    private String date;
//    private String type;
//    private String workout;
//    private Integer difficulty;
//    private Integer calories;
//
//}