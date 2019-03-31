package by.itakademy.akulov.servlet;

import by.itakademy.akulov.JdbcResource.JspPath;
import by.itakademy.akulov.dto.CourseDto;
import by.itakademy.akulov.service.CourseService;
import by.itakademy.akulov.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/get-all-course")
public class CourseAllServlet extends HttpServlet {

    private CourseService courseService = CourseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<CourseDto> courseDtoList = courseService.findAll();
        req.setAttribute("allCourses", courseDtoList);
        getServletContext()
                .getRequestDispatcher(JspPath.get("course/course-list"))
                .forward(req, resp);
    }
}
