package me.otmane.assignment.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.otmane.assignment.core.DatabaseDAO;
import me.otmane.assignment.models.Branch;
import me.otmane.assignment.utils.HttpMethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet(name = "BranchesServlet", urlPatterns = {"/branches/*"})
public class BranchesServlet extends HttpServlet {
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
        handleRequest(HttpMethod.GET, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(HttpMethod.POST, request, response);
    }

    private void handleRequest(HttpMethod method, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlString = request.getPathInfo().substring(1);

        if (urlString.isBlank())
            urlString = "show";

        String[] urlSegments = urlString.split("/");

        String action = "";
        String[] params = new String[]{};

        if (urlSegments.length == 1)
            action = urlSegments[0];

        if (urlSegments.length > 1) {
            action = urlSegments[1];
            params = new String[]{urlSegments[0]};
        }

        switch (method) {
            case GET -> {
                switch (action) {
                    case "show" -> listBranches(request, response, params);
                    case "create", "edit", "delete" -> showBranch(request, response, params);
                }
            }
            case POST -> {
                switch (action) {
                    case "create" -> addNewBranch(request, response, params);
                    case "edit" -> updateBranch(request, response, params);
                    case "delete" -> deleteBranch(request, response, params);
                }
            }
        }
    }


    private void listBranches(HttpServletRequest request, HttpServletResponse response, String[] params) throws ServletException, IOException {
        try {
            request.setAttribute("branches", branchesDAO.all());
        } catch (SQLException e) {
            request.setAttribute("error", e.getMessage());
        }

        request.getRequestDispatcher("/views/branches/all.jsp").forward(request, response);
    }

    private void showBranch(HttpServletRequest request, HttpServletResponse response, String[] params) throws ServletException, IOException {
        try {
            request.setAttribute("branch", params.length > 0 ? branchesDAO.get(Long.parseLong(params[0])) : null);
        } catch (SQLException e) {
            request.setAttribute("error", e.getMessage());
        }

        request.getRequestDispatcher("/views/branches/form.jsp").forward(request, response);
    }


    private void addNewBranch(HttpServletRequest request, HttpServletResponse response, String[] params) throws ServletException, IOException {
        Branch branch = Branch.builder().name(request.getParameter("name")).build();

        try {
            branchesDAO.insert(branch);
            response.sendRedirect(request.getContextPath() + "/branches/");
        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/branches/form.jsp").forward(request, response);
        }
    }

    private void updateBranch(HttpServletRequest request, HttpServletResponse response, String[] params) throws ServletException, IOException {
        try {
            Branch branch = branchesDAO.get(Long.parseLong(params[0]));
            branch.setName(request.getParameter("name"));
            branchesDAO.update(branch);
            response.sendRedirect(request.getContextPath() + "/branches/");
        } catch (SQLException | IllegalAccessException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/branches/form.jsp").forward(request, response);
        }
    }

    private void deleteBranch(HttpServletRequest request, HttpServletResponse response, String[] params) throws ServletException, IOException {
        try {
            branchesDAO.delete(Long.parseLong(params[0]));
            response.sendRedirect(request.getContextPath() + "/branches/");
        } catch (SQLException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/branches/form.jsp").forward(request, response);
        }
    }

}
