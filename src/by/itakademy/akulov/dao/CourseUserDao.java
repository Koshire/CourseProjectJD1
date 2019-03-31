package by.itakademy.akulov.dao;

import by.itakademy.akulov.JdbcResource.ConnectionManager;
import by.itakademy.akulov.entity.CourseUser;
import lombok.SneakyThrows;
import by.itakademy.akulov.utils.BuildEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class CourseUserDao {

    private static final CourseUserDao INSTANCE = new CourseUserDao();

    private static final String GET_USER_BY_ID =
            "SELECT c.id AS course_user_id," +
                    " r.id AS user_role_id," +
                    " r.name AS user_role_name," +
                    " c.first_name AS first_name," +
                    " c.middle_name AS middle_name," +
                    " c.last_name AS last_name," +
                    " c.e_mail AS e_mail," +
                    " c.phone AS phone," +
                    " c.passphrase AS passphrase" +
                    " FROM course_user c" +
                    " JOIN role r ON c.role_id = r.id" +
                    " WHERE c.id = ?;";

    private static final String GET_USER_FOR_LOGIN =
            "SELECT c.id AS course_user_id," +
                    " r.id AS user_role_id," +
                    " r.name AS user_role_name," +
                    " c.first_name AS first_name," +
                    " c.middle_name AS middle_name," +
                    " c.last_name AS last_name," +
                    " c.e_mail AS e_mail," +
                    " c.phone AS phone," +
                    " c.passphrase AS passphrase" +
                    " FROM course_user c" +
                    " JOIN role r ON c.role_id = r.id" +
                    " WHERE c.e_mail = ? AND c.passphrase = ?;";

    private static final String GET_ALL_USER =
            "SELECT c.id AS course_user_id," +
                    " r.id AS user_role_id," +
                    " r.name AS user_role_name," +
                    " c.first_name AS first_name," +
                    " c.middle_name AS middle_name," +
                    " c.last_name AS last_name," +
                    " c.e_mail AS e_mail," +
                    " c.phone AS phone," +
                    " c.passphrase AS passphrase" +
                    " FROM course_user c" +
                    " JOIN role r ON c.role_id = r.id;";

    private final String CREATE =
            "INSERT INTO course_user (role_id, last_name, first_name, middle_name, e_mail, phone, passphrase)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";


    private final String DELETE =
            "DELETE FROM course_user " +
                    "WHERE id = ?;";
    @SneakyThrows
    public Optional<CourseUser> getUserById(Integer id) {
        CourseUser courseUser = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courseUser = BuildEntity.buildUser(resultSet);
            }
        }
        return Optional.ofNullable(courseUser);
    }

    @SneakyThrows
    public Optional<CourseUser> getUserForLogin(String name, String passphrase) {
        CourseUser courseUser = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_FOR_LOGIN)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, passphrase);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courseUser = BuildEntity.buildUser(resultSet);
            }
        }
        return Optional.ofNullable(courseUser);
    }


    @SneakyThrows
    public boolean delete(CourseUser courseUser) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, courseUser.getId());
            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    @SneakyThrows
    public Set<CourseUser> getAllUser() {
        Set<CourseUser> set = new HashSet<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USER)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                set.add(BuildEntity.buildUser(resultSet));
            }
        }
        return set;
    }

    @SneakyThrows
    public CourseUser save(CourseUser courseUser) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(CREATE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, courseUser.getRole().getId());
            preparedStatement.setString(2, courseUser.getFirstName());
            preparedStatement.setString(3, courseUser.getMiddleName());
            preparedStatement.setString(4, courseUser.getLastName());
            preparedStatement.setString(5, courseUser.getEmail());
            preparedStatement.setLong(6, courseUser.getPhone());
            preparedStatement.setString(7, courseUser.getPassphrase());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                courseUser.setId(generatedKeys.getInt(1));
            }
        }
        return courseUser;
    }

    private final String UPDATE =
            "UPDATE course_user " +
                    "SET first_name = ?," +
                    "last_name = ?," +
                    "middle_name = ?," +
                    "passphrase = ?," +
                    "phone = ?" +
                    "where id = ?;";

    @SneakyThrows
    public CourseUser update(CourseUser courseUser) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, courseUser.getFirstName());
            preparedStatement.setString(2, courseUser.getLastName());
            preparedStatement.setString(3, courseUser.getMiddleName());
            preparedStatement.setString(4, courseUser.getPassphrase());
            preparedStatement.setLong(5, courseUser.getPhone());
            preparedStatement.setInt(6, courseUser.getId());
            preparedStatement.executeUpdate();
        }
        return courseUser;
    }

    public static CourseUserDao getInstance() {
        return INSTANCE;
    }
}
