package com.sda.controller;

import com.sda.model.User;
import com.sda.service.ObserveService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ObserveController", value = "/panel/observe")
public class ObserveController extends HttpServlet {

    private ObserveService observeService = ObserveService.getInstance();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String advertId = httpServletRequest.getParameter("id");
        final User user = (User) httpServletRequest.getSession().getAttribute("user");

        observeService.observeAdvert(user.getId(), Long.parseLong(advertId));
        httpServletResponse.sendRedirect("/panel/search");
    }
}
