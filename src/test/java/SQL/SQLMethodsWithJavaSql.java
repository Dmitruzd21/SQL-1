package SQL;

import lombok.SneakyThrows;
import ru.netology.web.data.DataHelper;

import java.sql.*;


public class SQLMethodsWithJavaSql {

    @SneakyThrows
    public static void clearAllTables() {
        String deleteUsers = "DELETE FROM users";
        String deleteCards = "DELETE FROM cards";
        String deleteAuth_codes = "DELETE FROM auth_codes";
        String deleteCard_transactions = "DELETE FROM card_transactions";
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sql-project", "dmitry", "21uzd"
                );
                PreparedStatement preparedStatement1 = connection.prepareStatement(deleteUsers);
                PreparedStatement preparedStatement2 = connection.prepareStatement(deleteCards);
                PreparedStatement preparedStatement3 = connection.prepareStatement(deleteAuth_codes);
                PreparedStatement preparedStatement4 = connection.prepareStatement(deleteCard_transactions);
        ) {
            preparedStatement3.executeUpdate();
            preparedStatement4.executeUpdate();
            preparedStatement2.executeUpdate();
            preparedStatement1.executeUpdate();
        }
    }

    @SneakyThrows
    public static int getVerificationCodeFor() {
        String dataSQL = "SELECT code FROM auth_codes INNER JOIN users ON auth_codes.user_id = users.id" + " WHERE users.login = ? ORDER BY created DESC LIMIT 1";
        int code = 0;
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sql-project", "dmitry", "21uzd"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(dataSQL);
        ) {
            preparedStatement.setString(1, DataHelper.getAuthInfo().getLogin());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // выборка значения по индексу столбца (нумерация с 1)
                    code = resultSet.getInt(1);
                }
            }
            return code;
        }
    }

}
