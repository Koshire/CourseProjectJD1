package by.itakademy.akulov.servlet;


import by.itakademy.akulov.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/course-register")
public class CourseRegister extends HttpServlet {

    private StudentService studentService = StudentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cid = req.getParameter("cid");
        String uid = req.getParameter("uid");
        if (!cid.isEmpty() && !uid.isEmpty()) {
            studentService.save(Integer.parseInt(cid), Integer.parseInt(uid));

            //Запись на курс, добавить переадресацию.
        }
    }
}
