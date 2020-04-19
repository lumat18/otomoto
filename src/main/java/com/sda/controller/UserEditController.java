package com.sda.controller;

import com.sda.model.User;
import com.sda.request.EditUserRequest;
import com.sda.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserEditController", value = "/panel/edit")
public class UserEditController extends HttpServlet {

    private UserService userService = UserService.aUserService();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String login = httpServletRequest.getParameter("login");
        final User user = userService.getUserByLogin(login);
        httpServletRequest.setAttribute("user", user);
        final RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/edit.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String name = httpServletRequest.getParameter("name");
        final String surname = httpServletRequest.getParameter("surname");
        final String login = httpServletRequest.getParameter("login");

        final EditUserRequest editUserRequest = new EditUserRequest(name, surname, login);
        userService.editUser(editUserRequest);

        httpServletResponse.sendRedirect("/panel/users");
    }
}
