package com.sda.controller;

import com.sda.model.User;
import com.sda.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserController", value = "/panel/users")
public class UserController extends HttpServlet {

  private UserService userService = UserService.aUserService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<User> users = userService.findAll();
    req.setAttribute("users", users);
    req.getRequestDispatcher("/users.jsp").forward(req, resp);
  }
}
