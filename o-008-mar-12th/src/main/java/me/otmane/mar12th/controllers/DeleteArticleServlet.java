package me.otmane.mar12th.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import me.otmane.mar12th.services.ArticleDAOImpl;
import me.otmane.mar12th.services.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "DeleteArticleServlet", value = "/articles/delete")
public class DeleteArticleServlet extends HttpServlet {
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
    String pk = request.getParameter("id");

    if (pk != null && !pk.isEmpty()) {
      try {
        articleDAO.delete(Long.parseLong(pk));
      } catch (SQLException ignored) {
      }
    }
    response.sendRedirect("../../articles/all");
  }
}
