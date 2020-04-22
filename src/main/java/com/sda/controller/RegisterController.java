package com.sda.controller;


import com.sda.model.User;
import com.sda.model.UserRole;
import com.sda.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends HttpServlet {

    private UserService userService = UserService.aUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = createUserFrom(req);
        boolean created = userService.saveUser(user);
        if (created) {
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        } else {
            req.setAttribute("loginExists", user.getLogin());
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }

    private User createUserFrom(HttpServletRequest req) {
        return User.builder()
                .login(req.getParameter("login"))
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .password(req.getParameter("pwd"))
                .role(UserRole.USER)
                .isBlocked(false)
                .isActive(true)
                .observed(new ArrayList<>())
                .build();
    }
}
