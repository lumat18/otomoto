package com.sda.controller;

import com.sda.dto.AdvertDTO;
import com.sda.model.Advert;
import com.sda.model.User;
import com.sda.service.ObserveService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ObservedAdvertsController", value = "/panel/observed")
public class ObservedAdvertsController extends HttpServlet {

    private ObserveService observeService = ObserveService.getInstance();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final HttpSession session = httpServletRequest.getSession();
        final User user = (User) session.getAttribute("user");
        final List<AdvertDTO> observedAdvertDTOs = observeService.getUserObservedAdvertDTOs(user.getId());
        observeService.markObservedByUser(observedAdvertDTOs, user.getId());
        httpServletRequest.setAttribute("observedAdvertDTOs", observedAdvertDTOs);
        final RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/observed.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
