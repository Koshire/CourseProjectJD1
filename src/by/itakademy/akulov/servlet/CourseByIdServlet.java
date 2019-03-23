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
import java.util.Optional;

@WebServlet("/get-course")
public class CourseByIdServlet extends HttpServlet {

    private CourseService courseService = CourseService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (StringUtils.isNotEmpty(id)) {
            Optional<CourseDto> courseDto = courseService.find(Integer.parseInt(id));
            if (courseDto.isPresent()) {
                req.setAttribute("course", courseDto.get());
                getServletContext()
                        .getRequestDispatcher(JspPath.get("get-course"))
                        .forward(req, resp);
            } else {
                getServletContext()
                        .getRequestDispatcher(JspPath.get("error"))
                        .forward(req, resp);
            }
        }
    }
}