package me.otmane.mar12th.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import me.otmane.mar12th.models.Article;
import me.otmane.mar12th.services.ArticleDAOImpl;
import me.otmane.mar12th.services.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "EditArticleServlet", value = "/articles/edit")
public class EditArticleServlet extends HttpServlet {
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
        request.setAttribute("article", articleDAO.get(Long.parseLong(pk)));
      } catch (SQLException e) {
        request.setAttribute("error", e.getMessage());
      }
    }
    request.getRequestDispatcher("/articles/form.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String pk = request.getParameter("id");

    String title = request.getParameter("title");
    String price = request.getParameter("price");
    if(price.isEmpty())
      price = "0";
    String description = request.getParameter("description");

    var a = Article.builder();
    if (pk != null && !pk.isEmpty()) {
      a.pk(Long.parseLong(pk));
    }
    a.title(title)
        .price(Float.parseFloat(price))
        .description(description);

    try {
      if (pk != null && pk.isEmpty()) {
        articleDAO.insert(a.build());
      } else {
        articleDAO.update(a.build());
      }
      response.sendRedirect(request.getContextPath()+"/articles/all");

    } catch (SQLException e) {
      request.setAttribute("error", e.getMessage());
      request.getRequestDispatcher("/articles/form.jsp").forward(request, response);
    }
  }
}
