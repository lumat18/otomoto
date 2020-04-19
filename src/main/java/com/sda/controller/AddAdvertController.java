package com.sda.controller;

import com.sda.exception.InvalidInputDataException;
import com.sda.model.Advert;
import com.sda.model.Car;
import com.sda.model.User;
import com.sda.service.AdvertService;
import com.sda.service.InputValidatingService;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddAdvertController", value = "/panel/add")
public class AddAdvertController extends HttpServlet {

  private AdvertService advertService = AdvertService.aAdvertService();
  private InputValidatingService inputValidatingService = InputValidatingService.aInputValidatingService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if(req.getSession().getAttribute("user") != null)
    req.getRequestDispatcher("/addCar.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
    Advert advert = null;
    try {
      advert = createAdvertisementFrom(httpServletRequest);
    } catch (InvalidInputDataException exception) {
      httpServletRequest.setAttribute("invalidInputDataError", exception.getMessage());
    }
    boolean created = advertService.postAdvert(advert);

    httpServletRequest.setAttribute("created", created);

    final RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/addCar.jsp");
    requestDispatcher.forward(httpServletRequest, httpServletResponse);
  }

  private Advert createAdvertisementFrom(HttpServletRequest req) throws InvalidInputDataException {
    return Advert.builder()
            .car(Car.builder()
                    .brand(inputValidatingService.getCorrectAdvertContent(req.getParameter("brand")))
                    .model(inputValidatingService.getCorrectAdvertContent(req.getParameter("model")))
                    .year(inputValidatingService.validateNumberField(req.getParameter("year")))
                    .mileage(inputValidatingService.validateNumberField(req.getParameter("mileage")))
                    .build())
            .description(req.getParameter("description"))
            .price(inputValidatingService.validateNumberField(req.getParameter("price")))
            .user((User) req.getSession().getAttribute("user"))
            .build();
  }
}
