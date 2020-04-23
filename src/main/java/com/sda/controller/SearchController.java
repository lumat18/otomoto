package com.sda.controller;

import com.sda.dto.AdvertDTO;
import com.sda.exception.InvalidInputDataException;
import com.sda.model.Advert;
import com.sda.model.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchController", value = "/panel/search")
public class SearchController extends HttpServlet {
    private AdvertService advertService = AdvertService.aAdvertService();
    private SearchService searchService = SearchService.aFilteringService();
    private InputValidatingService inputValidatingService = InputValidatingService.aInputValidatingService();
    private ObserveService observeService = ObserveService.getInstance();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        //final List<Advert> adverts = advertService.getAdverts();
        final HttpSession session = httpServletRequest.getSession();
        final User user = (User) session.getAttribute("user");
        final List<AdvertDTO> advertDTOs = advertService.getAdvertDTOs();
        observeService.markObservedByUser(advertDTOs, user.getId());
        session.setAttribute("advertDTOs", advertDTOs);

        final RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/search.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            final String brand = inputValidatingService.getCorrectAdvertContent(httpServletRequest.getParameter("brand"));
            final String model = inputValidatingService.getCorrectAdvertContent(httpServletRequest.getParameter("model"));
            final Integer minYear = inputValidatingService.validateNumberField(httpServletRequest.getParameter("minYear"));
            final Integer maxYear = inputValidatingService.validateNumberField(httpServletRequest.getParameter("maxYear"));
            final Integer minMileage = inputValidatingService.validateNumberField(httpServletRequest.getParameter("minMileage"));
            final Integer maxMileage = inputValidatingService.validateNumberField(httpServletRequest.getParameter("maxMileage"));

            final List<Advert> adverts = advertService.getAdverts();
            final List<Advert> foundAdverts = searchService.search(adverts, brand, model, minYear, maxYear, minMileage, maxMileage);

            final HttpSession session = httpServletRequest.getSession();
            session.setAttribute("adverts", foundAdverts);

        } catch (InvalidInputDataException exception) {
            httpServletRequest.setAttribute("invalidInputDataError", exception.getMessage());
        }

        final RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/search.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
