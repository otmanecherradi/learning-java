package me.otmane.assignment.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.otmane.assignment.core.DatabaseDAO;
import me.otmane.assignment.models.Branch;
import me.otmane.assignment.models.Gender;
import me.otmane.assignment.models.Student;
import me.otmane.assignment.utils.HttpMethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


@WebServlet(name = "StudentsServlet", urlPatterns = {"/students/*"})
public class StudentsServlet extends HttpServlet {
    DatabaseDAO<Student, Long> studentsDAO;
    DatabaseDAO<Branch, Long> branchesDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            studentsDAO = new DatabaseDAO<>(Student.class);
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
                    case "show" -> listStudents(request, response, params);
                    case "create", "edit", "delete" -> showStudent(request, response, params);
                }
            }
            case POST -> {
                switch (action) {
                    case "create" -> addNewStudent(request, response, params);
                    case "edit" -> updateStudent(request, response, params);
                    case "delete" -> deleteStudent(request, response, params);
                }
            }
        }
    }


    private void listStudents(HttpServletRequest request, HttpServletResponse response, String[] params) throws ServletException, IOException {
        try {
            request.setAttribute("students", studentsDAO.all());
        } catch (SQLException e) {
            request.setAttribute("error", e.getMessage());
        }

        request.getRequestDispatcher("/views/students/all.jsp").forward(request, response);
    }

    private void showStudent(HttpServletRequest request, HttpServletResponse response, String[] params) throws ServletException, IOException {
        try {
            request.setAttribute("student", params.length > 0 ? studentsDAO.get(Long.parseLong(params[0])) : null);
            request.setAttribute("branches", branchesDAO.all());
        } catch (SQLException e) {
            request.setAttribute("error", e.getMessage());
        }

        request.getRequestDispatcher("/views/students/form.jsp").forward(request, response);
    }


    private void addNewStudent(HttpServletRequest request, HttpServletResponse response, String[] params) throws ServletException, IOException {
        Student.StudentBuilder studentBuilder = Student.builder()
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .gender(Gender.valueOf(request.getParameter("gender")));
        if (request.getParameter("branch") == null || request.getParameter("branch").isEmpty())
            studentBuilder.branch(null);
        else
            studentBuilder.branch(Branch.builder().pk(Long.valueOf(request.getParameter("branch"))).build());

        try {
            studentsDAO.insert(studentBuilder.build());
            response.sendRedirect(request.getContextPath() + "/students/");
        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/students/form.jsp").forward(request, response);
        }
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response, String[] params) throws ServletException, IOException {
        try {
            Student student = studentsDAO.get(Long.parseLong(params[0]));
            student.setFirstName(request.getParameter("firstName"));
            student.setLastName(request.getParameter("lastName"));
            student.setGender(Gender.valueOf(request.getParameter("gender")));
            if (request.getParameter("branch") == null || request.getParameter("branch").isEmpty())
                student.setBranch(null);
            else
                student.setBranch(Branch.builder().pk(Long.valueOf(request.getParameter("branch"))).build());
            studentsDAO.update(student);
            response.sendRedirect(request.getContextPath() + "/students/");
        } catch (SQLException | IllegalAccessException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/students/form.jsp").forward(request, response);
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response, String[] params) throws ServletException, IOException {
        try {
            studentsDAO.delete(Long.parseLong(params[0]));
            response.sendRedirect(request.getContextPath() + "/students/");
        } catch (SQLException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/students/form.jsp").forward(request, response);
        }
    }
}
