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

@WebServlet("/save-course")
public class CourseSave extends HttpServlet {

    private CourseService courseService = CourseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("course/save-course"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseDto courseDto = CourseDto.builder()
                .type(req.getParameter("type"))
                .name(req.getParameter("name"))
                .startdate(req.getParameter("startdate"))
                .duration(Integer.parseInt(req.getParameter("duration")))
                .description(req.getParameter("description"))
                .plan(req.getParameter("plan"))
                .build();

        Course request = courseService.save(courseDto);
        resp.sendRedirect("/get-course?id=" + request.getId());
    }
}
