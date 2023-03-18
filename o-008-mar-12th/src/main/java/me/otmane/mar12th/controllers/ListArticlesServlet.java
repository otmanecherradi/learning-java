package me.otmane.mar12th.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import me.otmane.mar12th.services.ArticleDAOImpl;
import me.otmane.mar12th.services.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "ListArticlesServlet", value = "/articles/all")
public class ListArticlesServlet extends HttpServlet {
  Connection cnx;
  ArticleDAOImpl articleDAO;

  @Override
  public void init() throws ServletException {
    super.init();
    try {
      cnx = ConnectionFactory.getInstance();
      articleDAO = new ArticleDAOImpl(cnx);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      request.setAttribute("articles", articleDAO.all());
    } catch (SQLException e) {
      request.setAttribute("error", e.getMessage());
    }
    request.getRequestDispatcher("/articles/all.jsp").forward(request, response);
  }
}
