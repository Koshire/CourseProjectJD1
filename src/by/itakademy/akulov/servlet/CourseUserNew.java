package by.itakademy.akulov.servlet;

import by.itakademy.akulov.JdbcResource.JspPath;
import by.itakademy.akulov.dto.CourseUserDto;
import by.itakademy.akulov.entity.CourseUser;
import by.itakademy.akulov.service.RoleService;
import by.itakademy.akulov.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebServlet("/new-cource-user")
public class CourseUserNew extends HttpServlet {

    private UserService userService = UserService.getInstance();
    private RoleService roleService = RoleService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", roleService.findAll());
        getServletContext()
                .getRequestDispatcher(JspPath.get("admin/newuser"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CourseUserDto courseUserDto = CourseUserDto.builder()
                .firstName(req.getParameter("firstName"))
                .middleName(req.getParameter("middleName"))
                .lastName(req.getParameter("lastName"))
                .role(roleService.getById(Integer.parseInt(req.getParameter("role"))).get())
                .email(req.getParameter("email"))
                .passphrase(req.getParameter("passphrase"))
                .phone(req.getParameter("phone"))
                .build();

        userService.save(courseUserDto);
        req.setAttribute("courseUser", courseUserDto);
        getServletContext()
                .getRequestDispatcher(JspPath.get("admin/profile-view"))
                .forward(req, resp);
    }
}

