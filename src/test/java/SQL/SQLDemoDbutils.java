package SQL;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class SQLDemoDbutils {

    @SneakyThrows
    public void setUp() {
        Faker faker = new Faker();
        QueryRunner runner = new QueryRunner();
        String dataSQL = "INSERT INTO users(login,password) VALUES (?,?);";
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sql-project", "dmitry", "21uzd"
                );
        ) {
            runner.update(connection, dataSQL, faker.name().username(), "pass");
            runner.update(connection, dataSQL, faker.name().username(), "pass");
        }
    }

    @SneakyThrows
    public void setUp2() {
        String countSQL = "SELECT COUNT(*) FROM USERS;";
        String userSQL = "SELECT * FROM USERS;";
        QueryRunner runner = new QueryRunner();
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sql-project", "dmitry", "21uzd"
                );
        ) {
            var count = runner.query(connection, countSQL, new ScalarHandler<>());
            System.out.println(count);
            // User first = runner.query(connection,userSQL, new BeanHandler<>(User.class));
            // System.out.println(first);
            // List<User> all = runner.query(connection,userSQL,new BeanListHandler<>(User.class));
            // System.out.println(all);
        }
    }
}
