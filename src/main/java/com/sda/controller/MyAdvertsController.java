package com.sda.controller;

import com.sda.model.Advert;
import com.sda.model.User;
import com.sda.service.AdvertService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyAdvertsController", value = "/panel/myadverts")
public class MyAdvertsController extends HttpServlet {

    private AdvertService advertService = AdvertService.aAdvertService();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final User user = (User) httpServletRequest.getSession().getAttribute("user");
        final List<Advert> adverts = advertService.getUserAdverts(user.getId());
        httpServletRequest.setAttribute("adverts", adverts);
        final RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/myAdverts.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
