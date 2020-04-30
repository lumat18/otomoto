package com.sda.service;

import com.sda.model.Advert;
import com.sda.request.SearchRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchService {
    private static SearchService searchService;

    public static SearchService aFilteringService() {
        if (searchService == null) {
            searchService = new SearchService();
        }
        return searchService;
    }

    public List<Advert> search(List<Advert> adverts, SearchRequest searchRequest) {
        final List<Advert> filterBrand = filterBrand(adverts, searchRequest.getBrand());
        final List<Advert> filterModel = filterModel(filterBrand, searchRequest.getModel());
        final List<Advert> filterMaxYear = filterMaxYear(filterModel, searchRequest.getMaxYear());
        final List<Advert> filterMinYear = filterMinYear(filterMaxYear, searchRequest.getMinYear());
        final List<Advert> filterMaxMileage = filterMaxMileage(filterMinYear, searchRequest.getMaxMileage());
        return filterMinMileage(filterMaxMileage, searchRequest.getMinMileage());
    }

    private List<Advert> filterBrand(List<Advert> adverts, String brand) {
        if (brand.isEmpty()) {
            return adverts;
        }
        return adverts.stream()
                .filter(advert -> advert.getCar().getBrand().equals(brand))
                .collect(Collectors.toList());
    }

    private List<Advert> filterModel(List<Advert> adverts, String model) {
        if (model.isEmpty()) {
            return adverts;
        }
        return adverts.stream()
                .filter(advert -> advert.getCar().getModel().equals(model))
                .collect(Collectors.toList());
    }

    private List<Advert> filterMaxYear(List<Advert> adverts, Integer maxYear) {
        if (maxYear == null || maxYear == 0) {
            return adverts;
        }
        return adverts.stream()
                .filter(advert -> advert.getCar().getYear() <= maxYear)
                .collect(Collectors.toList());
    }

    private List<Advert> filterMinYear(List<Advert> adverts, Integer minYear) {
        if (minYear == null) {
            return adverts;
        }
        return adverts.stream()
                .filter(advert -> advert.getCar().getYear() >= minYear)
                .collect(Collectors.toList());
    }

    private List<Advert> filterMaxMileage(List<Advert> adverts, Integer maxMileage) {
        if (maxMileage == null || maxMileage == 0) {
            return adverts;
        }
        return adverts.stream()
                .filter(advert -> advert.getCar().getMileage() <= maxMileage)
                .collect(Collectors.toList());
    }

    private List<Advert> filterMinMileage(List<Advert> adverts, Integer minMileage) {
        if (minMileage == null) {
            return adverts;
        }
        return adverts.stream()
                .filter(advert -> advert.getCar().getMileage() >= minMileage)
                .collect(Collectors.toList());
    }
}
