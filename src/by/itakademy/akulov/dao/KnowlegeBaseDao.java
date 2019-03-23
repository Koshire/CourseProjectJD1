package by.itakademy.akulov.dao;

import by.itakademy.akulov.JdbcResource.ConnectionManager;
import by.itakademy.akulov.entity.KnowlegeBase;
import lombok.SneakyThrows;
import by.itakademy.akulov.utils.BuildEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class KnowlegeBaseDao {

    private static final KnowlegeBaseDao INSTANCE = new KnowlegeBaseDao();

    private static final String KB_GET_ALL_BY_COURSE_ID =
            "SELECT " +
                    " id," +
                    " course_id," +
                    " user_id" +
                    " timestamp," +
                    " text " +
                    " FROM knowlege_base" +
                    " WHERE course_id = ?;";

    private final String CREATE =
            "INSERT INTO knowlege_base (course_id, user_id, timestamp, text) " +
                    "VALUES (?, ?, ?, ?);";

    private final String DELETE =
            "DELETE FROM knowlege_base " +
                    "WHERE id = ?;";

    @SneakyThrows
    public KnowlegeBase save(KnowlegeBase knowlegeBase) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(CREATE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, knowlegeBase.getCourseId());
            preparedStatement.setInt(2, knowlegeBase.getCourseUserId());
            preparedStatement.setTimestamp(3, knowlegeBase.getTimestamp());
            preparedStatement.setString(4, knowlegeBase.getText());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                knowlegeBase.setId(generatedKeys.getInt("id"));
            }
        }
        return knowlegeBase;
    }

    @SneakyThrows
    public Set<KnowlegeBase> getKbByCourseId(Integer id) {
        Set<KnowlegeBase> set = new HashSet<>();
        KnowlegeBase knowlegeBase = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(KB_GET_ALL_BY_COURSE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                set.add(BuildEntity.buildKb(resultSet));
            }
        }
        return set;
    }

    @SneakyThrows
    public boolean delete(KnowlegeBase knowlegeBase) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, knowlegeBase.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    public static KnowlegeBaseDao getInstance() {
        return INSTANCE;
    }
}
