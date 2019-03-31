package by.itakademy.akulov.servlet;

import by.itakademy.akulov.entity.Role;
import by.itakademy.akulov.service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by 1 on 11.03.2018.
 */
@WebServlet("/roles")
public class RoleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> role = RoleService.getInstance().findAll();
        req.setAttribute("roles", role);
    }
}
