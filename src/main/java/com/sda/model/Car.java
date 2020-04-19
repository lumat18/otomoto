package com.sda.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Car {
    private String brand;
    private String model;
    private Integer year;
    private Integer mileage;
}
