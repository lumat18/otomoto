package com.sda.controller;

import com.sda.model.User;
import com.sda.service.UserService;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

  private UserService userService = UserService.aUserService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String login = req.getParameter("login");
    String pass = req.getParameter("pwd");

    Optional<User> user = userService.getUserBy(login, pass);
    if (user.isPresent()) {
      if(user.get().isBlocked()){
        req.setAttribute("errorLogin", "User is blocked");
        req.getRequestDispatcher("login.jsp").forward(req, resp);
      }else{
        HttpSession session = req.getSession();
        session.setAttribute("user", user.get());
      }
    } else {
      req.setAttribute("errorLogin", "Invalid login or password");
      req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
    resp.sendRedirect("/");
  }
}
