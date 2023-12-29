package org.example.korobeynikova.servlet;

import org.example.korobeynikova.application.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action") != null && req.getParameter("action").equals("new")) {
            req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.addUser(req.getParameterValues("form"));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
