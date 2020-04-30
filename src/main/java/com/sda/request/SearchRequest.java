package com.sda.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SearchRequest {
    private String brand;
    private String model;
    private Integer minYear;
    private Integer maxYear;
    private Integer minMileage;
    private Integer maxMileage;
}
