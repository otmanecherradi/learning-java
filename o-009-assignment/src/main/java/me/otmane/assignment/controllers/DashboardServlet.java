package me.otmane.assignment.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.otmane.assignment.core.DatabaseDAO;
import me.otmane.assignment.models.Branch;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DashboardServlet", value = "/dashboard/")
public class DashboardServlet extends HttpServlet {
    DatabaseDAO<Branch, Long> branchesDAO;

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            branchesDAO = new DatabaseDAO<>(Branch.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("branchesCount", branchesDAO.count());
        } catch (SQLException e) {
            request.setAttribute("error", e.getMessage());
        }

        request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
