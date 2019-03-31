package by.itakademy.akulov.servlet;

import by.itakademy.akulov.JdbcResource.JspPath;
import by.itakademy.akulov.dto.CourseUserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CourseUserDto courseUserDto = (CourseUserDto) req.getSession().getAttribute("courseUser");
        if (nonNull(courseUserDto)) {
            getServletContext()
                    .getRequestDispatcher(JspPath.get("user/profile"))
                    .forward(req, resp);

        }
    }
}