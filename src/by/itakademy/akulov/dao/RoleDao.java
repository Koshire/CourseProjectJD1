package by.itakademy.akulov.dao;

import by.itakademy.akulov.JdbcResource.ConnectionManager;
import by.itakademy.akulov.entity.Role;
import lombok.SneakyThrows;
import by.itakademy.akulov.utils.BuildEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class RoleDao {

    private static final RoleDao INSTANCE = new RoleDao();

    private final String GET_ALL =
            "SELECT id, name" +
                    " FROM role;";

    private final String GET_BY_ID =
            "SELECT id, name " +
                    "FROM role " +
                    "WHERE id = ?";

    private final String CREATE =
            "INSERT INTO role (name)" +
                    "VALUES (?);";

    private final String DELETE =
            "DELETE FROM role " +
                    "WHERE id = ?;";

    @SneakyThrows
    public Set<Role> getAll() {
        Set<Role> set = new HashSet<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                set.add(BuildEntity.buildRole(resultSet));
            }
        }
        return set;
    }

    @SneakyThrows
    public Optional<Role> getEntityById(Integer id) {
        Role role = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = BuildEntity.buildRole(resultSet);
            }
        }
        return Optional.ofNullable(role);
    }

    @SneakyThrows
    public boolean delete(Role role) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, role.getId());
            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    @SneakyThrows
    public Role add(Role role) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(CREATE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                role.setId(generatedKeys.getInt(1));
            }
        }
        return role;
    }

    public static RoleDao getInstance() {
        return INSTANCE;
    }
}
