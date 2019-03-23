package by.itakademy.akulov.dao;

import by.itakademy.akulov.JdbcResource.ConnectionManager;
import by.itakademy.akulov.entity.KnowlegeBaseComment;
import lombok.SneakyThrows;
import by.itakademy.akulov.utils.BuildEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class KnowlegeBaseCommentDao {

    private static final KnowlegeBaseCommentDao INSTANCE = new KnowlegeBaseCommentDao();

    private static final String KB_GET_ALL_COMMENT_BY_KB_ID =
            "SELECT " +
                    " id," +
                    " knowlege_base_id," +
                    " user_id" +
                    " timestamp," +
                    " text " +
                    " FROM knowlege_base_comment" +
                    " WHERE knowlege_base_id = ?;";

    private final String CREATE =
            "INSERT INTO knowlege_base_comment (knowlege_base_id, user_id, timestamp, text) " +
                    "VALUES (?, ?, ?, ?);";

    private final String DELETE =
            "DELETE FROM knowlege_base_comment " +
                    "WHERE id = ?;";

    @SneakyThrows
    public KnowlegeBaseComment save(KnowlegeBaseComment knowlegeBaseComment) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(CREATE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, knowlegeBaseComment.getKnowlegeBaseId());
            preparedStatement.setInt(2, knowlegeBaseComment.getCourseUserId());
            preparedStatement.setTimestamp(3, knowlegeBaseComment.getTimestamp());
            preparedStatement.setString(4, knowlegeBaseComment.getText());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                knowlegeBaseComment.setId(generatedKeys.getInt("id"));
            }
        }
        return knowlegeBaseComment;
    }

    @SneakyThrows
    public Set<KnowlegeBaseComment> getKbByCourseId(Integer id) {
        Set<KnowlegeBaseComment> set = new HashSet<>();
        KnowlegeBaseComment knowlegeBaseComment = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(KB_GET_ALL_COMMENT_BY_KB_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                set.add(BuildEntity.buildKbComment(resultSet));
            }
        }
        return set;
    }

    @SneakyThrows
    public boolean delete(KnowlegeBaseComment knowlegeBaseComment) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, knowlegeBaseComment.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    public static KnowlegeBaseCommentDao getInstance() {
        return INSTANCE;
    }
}
