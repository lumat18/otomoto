package com.sda.controller;

import com.sda.model.User;
import com.sda.model.UserRole;
import com.sda.respository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomePageController", value = "/")
public class HomePageController extends HttpServlet {
    private UserRepository userRepository = UserRepository.aUserRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAdminAccount();
        RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
        dispatcher.forward(req, resp);
    }

    private void setAdminAccount() {
        final User admin = User.builder()
                .name("admin")
                .surname("admin")
                .login("admin")
                .password("admin")
                .isActive(true)
                .isBlocked(false)
                .role(UserRole.ADMIN)
                .build();

        userRepository.save(admin);
    }
}
