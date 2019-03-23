package by.itakademy.akulov.dao;

import by.itakademy.akulov.JdbcResource.ConnectionManager;
import by.itakademy.akulov.entity.BlockList;
import lombok.SneakyThrows;
import by.itakademy.akulov.utils.BuildEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class BlockListDao {


    private static final BlockListDao INSTANCE = new BlockListDao();

    private final String GET_ALL =
            "SELECT user_id" +
                    " FROM blocklist;";

    private final String GET_BY_ID =
            "SELECT user_id " +
                    "FROM blocklist " +
                    "WHERE user_id = ?";

    private final String CREATE =
            "INSERT INTO blocklist (user_id)" +
                    "VALUES (?);";

    private final String DELETE =
            "DELETE FROM blocklist " +
                    "WHERE user_id = ?;";

    @SneakyThrows
    public Set<BlockList> getAll() {
        Set<BlockList> set = new HashSet<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                set.add(BuildEntity.buildBlockList(resultSet));
            }
        }
        return set;
    }

    @SneakyThrows
    public Optional<BlockList> getEntityById(Integer user_id) {
        BlockList blockList = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                blockList = BuildEntity.buildBlockList(resultSet);
            }
        }
        return Optional.ofNullable(blockList);
    }

    @SneakyThrows
    public boolean delete(BlockList blockList) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, blockList.getCourseUserId());
            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    @SneakyThrows
    public BlockList add(BlockList blockList) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(CREATE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, blockList.getCourseUserId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                blockList.setCourseUserId(generatedKeys.getInt(1));
            }
        }
        return blockList;
    }

    public static BlockListDao getInstance() {
        return INSTANCE;
    }
}
