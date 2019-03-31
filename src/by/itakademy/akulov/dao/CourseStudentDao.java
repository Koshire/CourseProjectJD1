package by.itakademy.akulov.dao;

import by.itakademy.akulov.JdbcResource.ConnectionManager;
import by.itakademy.akulov.entity.CourseStudent;
import by.itakademy.akulov.utils.BuildEntity;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            "INSERT INTO course_student (cource_id, user_id)" +
                    "VALUES (?, ?);";

    private final String DELETE =
            "DELETE FROM course_student " +
                    "WHERE cource_id = ? && user_id = ?;";

    private final String GET_ONE =
            "SELECT cource_id, user_id, result " +
                    "FROM course_student " +
                    "WHERE cource_id = ? && user_id = ?;";

    private final String SAVE_RESULT =
            "UPDATE course_student" +
                    "SET result = ? " +
                    "WHERE cource_id = ? && user_id = ?;";


    @SneakyThrows
    private List<CourseStudent> getAllById(String query, Integer id) {
        List<CourseStudent> list = new ArrayList<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(BuildEntity.buildCourseStudent(resultSet));
            }
        }
        return list;
    }

    @SneakyThrows
    public List<CourseStudent> getAllByUserId(Integer userId) {
        return getAllById(GET_ALL_BY_USER_ID, userId);
    }

    @SneakyThrows
    public List<CourseStudent> getAllByCourseId(Integer courseId) {
        return getAllById(GET_ALL_BY_COURSE_ID, courseId);
    }

    @SneakyThrows
    public boolean delete(Integer courseId, Integer userId) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, userId);
            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    @SneakyThrows
    public Optional<CourseStudent> getOne(Integer courseId, Integer userId) {
        CourseStudent courseStudent = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ONE)) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courseStudent = BuildEntity.buildCourseStudent(resultSet);
            }
        }
        return Optional.ofNullable(courseStudent);
    }


    @SneakyThrows
    public Optional<CourseStudent> save(Integer courseId, Integer userId) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(CREATE)) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        }
        return Optional.ofNullable(CourseStudent.builder()
                .courseId(courseId)
                .courseUserId(courseId)
                .build());

    }

    @SneakyThrows
    public Optional<CourseStudent> saveResult(Integer courseId, Integer userId, Integer result) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SAVE_RESULT)) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, result);
            preparedStatement.executeUpdate();
        }
        return Optional.ofNullable(CourseStudent.builder()
                .courseId(courseId)
                .courseUserId(courseId)
                .result(result)
                .build());
    }

    public static CourseStudentDao getInstance() {
        return INSTANCE;
    }
}
