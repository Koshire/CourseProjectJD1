package by.itakademy.akulov.servlet;

import by.itakademy.akulov.JdbcResource.JspPath;
import by.itakademy.akulov.dto.CourseDto;
import by.itakademy.akulov.dto.CourseUserDto;
import by.itakademy.akulov.entity.Course;
import by.itakademy.akulov.entity.CourseUser;
import by.itakademy.akulov.entity.Language;
import by.itakademy.akulov.entity.Role;
import by.itakademy.akulov.service.CourseService;
import by.itakademy.akulov.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-cource-user")
public class CourseUserUpdate extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("update-cource-user"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseUserDto courseUserDto = (CourseUserDto)req.getSession().getAttribute("courseUser");
        courseUserDto.setFirstName(req.getParameter("firstName"));
        courseUserDto.setMiddleName(req.getParameter("middleName"));
        courseUserDto.setLastName(req.getParameter("lastName"));
        courseUserDto.setPassphrase(req.getParameter("passphrase"));
        courseUserDto.setPhone(req.getParameter("phone"));

        req.getSession().setAttribute("courseUser", courseUserDto);

        CourseUser request = userService.update(courseUserDto);
        resp.sendRedirect("/profile");
    }
}
