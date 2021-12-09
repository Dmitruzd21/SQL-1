package SQL;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.web.data.DataHelper;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLMethodsWithDbUtils {
    @SneakyThrows
    public static void clearAllTables() {
        String deleteUsers = "DELETE FROM users;";
        String deleteCards = "DELETE FROM cards;";
        String deleteAuth_codes = "DELETE FROM auth_codes;";
        String deleteCard_transactions = "DELETE FROM card_transactions;";
        QueryRunner runner = new QueryRunner();
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sql-project", "dmitry", "21uzd"
                )
        ) {
            runner.update(connection, deleteAuth_codes);
            runner.update(connection, deleteCard_transactions);
            runner.update(connection, deleteCards);
            runner.update(connection, deleteUsers);
        }
    }

    @SneakyThrows
    public static String getVerificationCodeFor() {
        QueryRunner runner = new QueryRunner();
        String dataSQL = "SELECT code FROM auth_codes INNER JOIN users ON auth_codes.user_id = users.id" + " WHERE users.login = ? ORDER BY auth_codes.created DESC LIMIT 1";
        String code = null;
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sql-project", "dmitry", "21uzd"
                )
        ) {
            code = runner.query(connection, dataSQL, new ScalarHandler<>(), DataHelper.getAuthInfo().getLogin());
        }
        return code;
    }
}
