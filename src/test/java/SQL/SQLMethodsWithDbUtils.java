package SQL;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLMethodsWithDbUtils {
    @SneakyThrows
    public static void clearAllTables() {
        String deleteUsers = "DELETE FROM users";
        String deleteCards = "DELETE FROM cards";
        String deleteAuth_codes = "DELETE FROM auth_codes";
        String deleteCard_transactions = "DELETE FROM cards_transactions";
        QueryRunner runner = new QueryRunner();
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sql-project", "dmitry", "21uzd"
                )
        ) {
            runner.update(connection, deleteAuth_codes);
            runner.update(connection, deleteUsers);
            //runner.update(connection, deleteCard_transactions);
            //runner.update(connection, deleteCards);
        }
    }

    @SneakyThrows
    public static String getVerificationCodeFor() {
        QueryRunner runner = new QueryRunner();
        String dataSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1;";
        String code = null;
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sql-project", "dmitry", "21uzd"
                )
        ) {
            code = runner.query(connection, dataSQL, new ScalarHandler<>());
        }
        return code;
    }


}
