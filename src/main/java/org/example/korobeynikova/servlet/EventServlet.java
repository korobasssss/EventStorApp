package org.example.korobeynikova.servlet;

import jakarta.servlet.annotation.WebServlet;
import org.example.korobeynikova.application.entity.UserEntity;
import org.example.korobeynikova.application.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@WebServlet("/event")
public class EventServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action") != null && req.getParameter("action").equals("enter")) {
            req.getRequestDispatcher("/").forward(req, resp);
        }
        Map<Integer, UserEntity> getAll = userService.showAll();
        for (UserEntity one : getAll.values()) {
            if (Objects.equals(req.getParameterValues("form")[0], one.getName()) &&
                    Objects.equals(req.getParameterValues("form")[1], one.getPassword())) {
                req.getRequestDispatcher("/WEB-INF/event.jsp").forward(req, resp);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/event.jsp").forward(req, resp);
    }

}
