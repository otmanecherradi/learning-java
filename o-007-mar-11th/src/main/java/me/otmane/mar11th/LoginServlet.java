package me.otmane.mar11th;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if(username.equals("admin") && password.equals("admin")){
      response.sendRedirect(request.getContextPath() + "/welcome");
    } else {
      request.setAttribute("error", "Login Error");
      request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
  }
}
