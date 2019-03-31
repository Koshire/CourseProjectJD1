package by.itakademy.akulov.servlet;

import by.itakademy.akulov.JdbcResource.JspPath;
import by.itakademy.akulov.dao.CourseUserDao;
import by.itakademy.akulov.dto.CourseUserDto;
import by.itakademy.akulov.entity.Language;
import by.itakademy.akulov.service.UserService;
import by.itakademy.akulov.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/autherr")
public class AuthErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("autherror"))
                .forward(req, resp);
    }
}