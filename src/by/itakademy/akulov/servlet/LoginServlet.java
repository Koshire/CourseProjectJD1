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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("inputEmail");
        String passphrase = req.getParameter("inputPassword");
        String lang = req.getParameter("lang");
        if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(passphrase) && StringUtils.isNotEmpty(lang)) {
            Optional<CourseUserDto> courseUserDto = userService.findLogin(name,passphrase);
            if (courseUserDto.isPresent()) {
                courseUserDto.get().setLang(Language.valueOf(lang));
                req.getSession().setAttribute("courseUser", courseUserDto.get());
                resp.sendRedirect("/global");
            } else {
                resp.sendRedirect("/autherr");
            }
        }
    }
}