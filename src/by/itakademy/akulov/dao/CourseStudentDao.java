package by.itakademy.akulov.dao;

import by.itakademy.akulov.JdbcResource.ConnectionManager;
import by.itakademy.akulov.entity.CourseStudent;
import lombok.SneakyThrows;
import by.itakademy.akulov.utils.BuildEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class CourseStudentDao {

    private static final CourseStudentDao INSTANCE = new CourseStudentDao();

    private final String GET_ALL_BY_COURSE_ID =
            "SELECT cource_id, user_id, result " +
                    "FROM course_student " +
                    "WHERE cource_id = ?";

    private final String GET_ALL_BY_USER_ID =
            "SELECT cource_id, user_id, result " +
                    "FROM course_student " +
                    "WHERE user_id = ?";

    private final String CREATE =
            "INSERT INTO course_student (cource_id, user_id, result)" +
                    "VALUES (?, ?, ?);";

    private final String DELETE =
            "DELETE FROM course_student " +
                    "WHERE cource_id = ?;";

    @SneakyThrows
    public Set<CourseStudent> getAllById(String query, Integer id) {
        Set<CourseStudent> set = new HashSet<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                set.add(BuildEntity.buildCourseStudent(resultSet));
            }
        }
        return set;
    }

    @SneakyThrows
    public Set<CourseStudent> getAllByUserId(Integer userId) {
        return getAllById(GET_ALL_BY_USER_ID, userId);
    }

    @SneakyThrows
    public Set<CourseStudent> getAllByCourseId(Integer courseId) {
        return getAllById(GET_ALL_BY_COURSE_ID, courseId);
    }

    @SneakyThrows
    public boolean delete(CourseStudent courseStudent) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, courseStudent.getCourseId());
            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    @SneakyThrows
    public CourseStudent save(CourseStudent courseStudent) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, courseStudent.getCourseId());
            preparedStatement.setInt(2, courseStudent.getCourseUserId());
            preparedStatement.setInt(3, courseStudent.getResult());
            preparedStatement.executeUpdate();
        }
        return courseStudent;
    }

    public static CourseStudentDao getInstance() {
        return INSTANCE;
    }
}
