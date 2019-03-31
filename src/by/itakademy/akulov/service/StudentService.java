package by.itakademy.akulov.service;

import by.itakademy.akulov.dao.CourseStudentDao;
import by.itakademy.akulov.entity.CourseStudent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentService {

    private static final StudentService INSTANCE = new StudentService();

    CourseStudentDao  courseStudentDao = CourseStudentDao.getInstance();

    public List<CourseStudent> getByCourseId(Integer id) {
        return courseStudentDao.getAllByCourseId(id);
    }

    public List<CourseStudent> getByUserId (Integer id) {
        return courseStudentDao.getAllByUserId(id);
    }

    public Optional<CourseStudent> getByUserId (Integer courseId, Integer userId) {
        return courseStudentDao.getOne(courseId, userId);
    }

    public Optional<CourseStudent> save (Integer courseId, Integer userId) {
        return courseStudentDao.save(courseId, userId);
    }



    public static StudentService getInstance() {
        return INSTANCE;
    }


}
