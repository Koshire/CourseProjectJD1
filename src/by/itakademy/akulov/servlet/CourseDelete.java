package by.itakademy.akulov.servlet;

import by.itakademy.akulov.JdbcResource.JspPath;
import by.itakademy.akulov.dto.CourseDto;
import by.itakademy.akulov.entity.Course;
import by.itakademy.akulov.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-course")
public class CourseDelete extends HttpServlet {

    private CourseService courseService = CourseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        courseService.delete(Integer.parseInt(req.getParameter("id")));
        getServletContext()
                .getRequestDispatcher(JspPath.get("support/deleted"))
                .forward(req, resp);
    }
}
