package com.sda.controller;

import com.sda.dto.AdvertDTO;
import com.sda.exception.InvalidInputDataException;
import com.sda.model.Advert;
import com.sda.model.User;
import com.sda.request.SearchRequest;
import com.sda.service.AdvertService;
import com.sda.service.InputValidatingService;
import com.sda.service.ObserveService;
import com.sda.service.SearchService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "SearchController", value = "/panel/search")
public class SearchController extends HttpServlet {
    private AdvertService advertService = AdvertService.aAdvertService();
    private SearchService searchService = SearchService.aFilteringService();
    private InputValidatingService inputValidatingService = InputValidatingService.aInputValidatingService();
    private ObserveService observeService = ObserveService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final List<AdvertDTO> advertDTOs = advertService.getAdvertDTOs();

        final List<AdvertDTO> advertDTOsWithObserved = markObservedAdverts(advertDTOs, request);
        request.setAttribute("advertDTOs", advertDTOsWithObserved);

        final RequestDispatcher requestDispatcher = request.getRequestDispatcher("/search.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final SearchRequest searchRequest = createSearchRequest(request);
            final List<Advert> adverts = advertService.getAdverts();
            final List<Advert> foundAdverts = searchService.search(adverts, searchRequest);
            final List<AdvertDTO> advertDTOs = mapAdvertsToAdvertDTOs(foundAdverts);
            final List<AdvertDTO> advertDTOsWithObserved =
                    markObservedAdverts(advertDTOs, request);

            request.setAttribute("advertDTOs", advertDTOsWithObserved);

        } catch (InvalidInputDataException exception) {
            request.setAttribute("invalidInputDataError", exception.getMessage());
        }

        final RequestDispatcher requestDispatcher = request.getRequestDispatcher("/search.jsp");
        requestDispatcher.forward(request, response);
    }

    private List<AdvertDTO> mapAdvertsToAdvertDTOs(List<Advert> adverts) {
        return adverts.stream()
                .map(advert -> new AdvertDTO(advert, false))
                .collect(Collectors.toList());
    }

    private List<AdvertDTO> markObservedAdverts(List<AdvertDTO> advertDTOs, HttpServletRequest request) {
        final User user = (User) request.getSession().getAttribute("user");
        return observeService.markObservedByUser(advertDTOs, user.getId());
    }

    private SearchRequest createSearchRequest(HttpServletRequest request) throws InvalidInputDataException {
        final String brand = inputValidatingService.getCorrectAdvertContent(request.getParameter("brand"));
        final String model = inputValidatingService.getCorrectAdvertContent(request.getParameter("model"));
        final Integer minYear = inputValidatingService.validateNumberField(request.getParameter("minYear"));
        final Integer maxYear = inputValidatingService.validateNumberField(request.getParameter("maxYear"));
        final Integer minMileage = inputValidatingService.validateNumberField(request.getParameter("minMileage"));
        final Integer maxMileage = inputValidatingService.validateNumberField(request.getParameter("maxMileage"));

        return SearchRequest.builder()
                .brand(brand)
                .model(model)
                .minYear(minYear)
                .maxYear(maxYear)
                .minMileage(minMileage)
                .maxMileage(maxMileage)
                .build();

    }
}
