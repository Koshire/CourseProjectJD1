package by.itakademy.akulov.dao;

import by.itakademy.akulov.JdbcResource.ConnectionManager;
import by.itakademy.akulov.entity.CourseTeacher;
import lombok.SneakyThrows;
import by.itakademy.akulov.utils.BuildEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class CourseTeacherDao {

    private static final CourseTeacherDao INSTANCE = new CourseTeacherDao();

    private final String GET_ALL_BY_COURSE_ID =
            "SELECT cource_id, user_id " +
                    "FROM course_teacher " +
                    "WHERE cource_id = ?";

    private final String GET_ALL_BY_TEACHER_ID =
            "SELECT cource_id, user_id " +
                    "FROM course_teacher " +
                    "WHERE user_id = ?";

    private final String CREATE =
            "INSERT INTO course_student (cource_id, user_id)" +
                    "VALUES (?, ?);";

    private final String DELETE =
            "DELETE FROM course_teacher " +
                    "WHERE cource_id = ?;";

    @SneakyThrows
    public Set<CourseTeacher> getAllById(String query, Integer id) {
        Set<CourseTeacher> set = new HashSet<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                set.add(BuildEntity.buildCourseTeacher(resultSet));
            }
        }
        return set;
    }

    @SneakyThrows
    public Set<CourseTeacher> getAllByUserId(Integer userId) {
        return getAllById(GET_ALL_BY_TEACHER_ID, userId);
    }

    @SneakyThrows
    public Set<CourseTeacher> getAllByCourseId(Integer courseId) {
        return getAllById(GET_ALL_BY_COURSE_ID, courseId);
    }

    @SneakyThrows
    public boolean delete(CourseTeacher courseTeacher) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, courseTeacher.getCourseUserId());
            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    @SneakyThrows
    public CourseTeacher save(CourseTeacher courseTeacher) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, courseTeacher.getCourseId());
            preparedStatement.setInt(2, courseTeacher.getCourseUserId());
            preparedStatement.executeUpdate();
        }
        return courseTeacher;
    }

    public static CourseTeacherDao getInstance() {
        return INSTANCE;
    }
}
