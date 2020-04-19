package com.sda.controller;

import com.sda.model.User;
import com.sda.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BlockController", value = "/panel/block")
public class BlockController extends HttpServlet {

    private UserService userService = UserService.aUserService();

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String login = httpServletRequest.getParameter("login");
        final User user = userService.getUserByLogin(login);
        user.setBlocked(true);
        httpServletResponse.sendRedirect("/panel/users");
    }
}
