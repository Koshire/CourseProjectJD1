package by.itakademy.akulov.dao;

import by.itakademy.akulov.JdbcResource.ConnectionManager;
import by.itakademy.akulov.entity.Course;
import lombok.SneakyThrows;
import by.itakademy.akulov.utils.BuildEntity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class CourseDao {

    private static final CourseDao INSTANCE = new CourseDao();

    private static final String COURSE_GET_BY_ID =
            "SELECT c.id AS course_id," +
                    " ct.id AS course_type_id," +
                    " ct.name AS course_type_name," +
                    " c.start_date AS course_startdate," +
                    " c.duration_hours AS course_duration," +
                    " c.name AS course_name," +
                    " c.description AS course_description," +
                    " c.plan AS course_plan" +
                    " FROM course c" +
                    " JOIN course_type ct ON c.type_id = ct.id" +
                    " WHERE c.id = ?;";

    private static final String COURSE_GET_ALL =
            "SELECT c.id AS course_id," +
                    " ct.id AS course_type_id," +
                    " ct.name AS course_type_name," +
                    " c.start_date AS course_startdate," +
                    " c.duration_hours AS course_duration," +
                    " c.name AS course_name," +
                    " c.description AS course_description," +
                    " c.plan AS course_plan" +
                    " FROM course c" +
                    " JOIN course_type ct ON c.type_id = ct.id;";

    private final String CREATE =
            "INSERT INTO course (type_id, start_date, duration_hours, name, description, plan)" +
                    "VALUES (?, ?, ?, ?, ?, ?);";

    private final String DELETE =
            "DELETE FROM course " +
                    "WHERE id = ?;";

    @SneakyThrows
    public Optional<Course> getCourseById(Integer id) {
        Course course = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(COURSE_GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                course = BuildEntity.buildCource(resultSet);
            }
        }
        return Optional.ofNullable(course);
    }


    @SneakyThrows
    public boolean delete(Course course) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, course.getId());
            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    @SneakyThrows
    public Set<Course> getAllCourse() {
        Set<Course> set = new HashSet<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(COURSE_GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                set.add(BuildEntity.buildCource(resultSet));
            }
        }
        return set;
    }

    @SneakyThrows
    public Course save(Course course) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(CREATE, RETURN_GENERATED_KEYS)) {
            //type_id, start_date, duration_hours, name, description, plan
            preparedStatement.setInt(1, course.getType().getId());
            preparedStatement.setDate(2, Date.valueOf(course.getStartDate()));
            preparedStatement.setInt(3, course.getDuration());
            preparedStatement.setString(4, course.getName());
            preparedStatement.setString(5, course.getDescription());
            preparedStatement.setString(6, course.getPlan());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                course.setId(generatedKeys.getInt(1));
            }
        }
        return course;
    }

    public static CourseDao getInstance() {
        return INSTANCE;
    }
}
