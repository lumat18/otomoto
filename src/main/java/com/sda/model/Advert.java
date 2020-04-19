package com.sda.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class Advert {
    private final Car car;
    private final User user;
    private final String description;
    private final Integer price;
    private final LocalDate date = LocalDate.now();

    @Override
    public String toString() {
        return "Advertisement{" +
                "car=" + car +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
