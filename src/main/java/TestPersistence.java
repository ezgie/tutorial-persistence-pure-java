import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class TestPersistence {

    private static Logger LOGGER = LoggerFactory.getLogger(TestPersistence.class);

    public static void main(String[] args) {
        try {
            Properties connectionProperties = new Properties();
            connectionProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("connection.properties"));

            StringBuilder url = new StringBuilder();
            url.append("jdbc:postgresql://");
            url.append(connectionProperties.getProperty("server")).append(":");
            url.append(connectionProperties.getProperty("port")).append("/");
            url.append(connectionProperties.getProperty("database"));

            String username = connectionProperties.getProperty("username");
            String password = connectionProperties.getProperty("password");

            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url.toString(), username, password);
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO persons(firstname, lastname) VALUES('test_first_name', 'test_last_name')";
            statement.executeUpdate(sql);
            LOGGER.info("Inserted a row.");
        } catch(Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
