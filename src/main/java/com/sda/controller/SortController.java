package com.sda.controller;

import com.sda.model.Advert;
import com.sda.service.SortService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SortController", value = "/panel/sort")
public class SortController extends HttpServlet {

    private SortService advertSortingService = SortService.aSortingService();

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final HttpSession session = httpServletRequest.getSession();
        final List<Advert> filteredAdverts = (List<Advert>) session.getAttribute("adverts");

        if (filteredAdverts != null) {
            final List<Advert> sortedAdverts = new ArrayList<>();
            final String sequence = httpServletRequest.getParameter("sequence");
            if (sequence.equals("priceASC")) {
                sortedAdverts.addAll(advertSortingService.sortByPriceASC(filteredAdverts));
            } else if (sequence.equals("priceDESC")) {
                sortedAdverts.addAll(advertSortingService.sortByPriceDESC(filteredAdverts));
            }
            session.setAttribute("adverts", sortedAdverts);
        }

        final RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/search.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
