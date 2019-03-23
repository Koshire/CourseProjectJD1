package by.itakademy.akulov.JdbcResource;

import lombok.experimental.UtilityClass;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

@UtilityClass
public class ConnectionManager {

    private static final String URL = "db.url";
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";
    private static final String DRIVER = "db.driver";


/*
    static {
        loadDriver();
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("org.postgresql.Driver");
    }

    @SneakyThrows
    public static Connection get() {
        return DriverManager.getConnection(
                PropertiesManager.get(URL),
                PropertiesManager.get(USERNAME),
                PropertiesManager.get(PASSWORD));
    }
*/

    public static Connection get() throws SQLException {

        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(PropertiesManager.get(URL));
        poolProperties.setUsername(PropertiesManager.get(USERNAME));
        poolProperties.setPassword(PropertiesManager.get(PASSWORD));
        poolProperties.setDriverClassName(PropertiesManager.get(DRIVER));
        DataSource dataSource = new DataSource(poolProperties);
        return dataSource.getConnection();
    }
}
