package com.sda.service;

import com.sda.model.Advert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SortService {
    private static SortService sortingService;

    public static SortService aSortingService() {
        if (sortingService == null) {
            sortingService = new SortService();
        }
        return sortingService;
    }

    public List<Advert> sortByPriceASC(List<Advert> adverts) {
        return adverts.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Advert> sortByPriceDESC(List<Advert> adverts) {
        return adverts.stream()
                .sorted((advert1, advert2) -> Integer.compare(advert2.getPrice(), advert1.getPrice()))
                .collect(Collectors.toList());
    }
}
