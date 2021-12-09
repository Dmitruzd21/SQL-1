package NotUsedInProject;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;

import java.sql.*;

public class SQLDemoJavaSql {
    @SneakyThrows
    public void setUp() {
        Faker faker = new Faker();
        String dataSQL = "INSERT INTO users(login,password) VALUES (?,?);";
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sql-project", "dmitry", "21uzd"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(dataSQL);
        ) {
            preparedStatement.setString(1, String.valueOf(faker.name()));
            preparedStatement.setString(2, "password");
            preparedStatement.executeUpdate();
        }

    }

    @SneakyThrows
    public void setUp2() {
        String countSQL = "SELECT COUNT(*) FROM USERS;";
        String cardsSQL = "SELECT id, number,balance_in_kopecs FROM CARDS WHERE user_id =?;";
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sql-project", "dmitry", "21uzd"
                );
                Statement countStmt = connection.createStatement();
                PreparedStatement preparedCardsStmt = connection.prepareStatement(cardsSQL);

        ) {
            try (ResultSet resultSet = countStmt.executeQuery(countSQL)) {
                if (resultSet.next()) {
                    // выборка значения по индексу столбца (нумерация с 1)
                    int count = resultSet.getInt(1);
                    // TODO: использовать
                    System.out.println(count);
                }
            }
            preparedCardsStmt.setInt(1, 1);
            try (ResultSet resultSet = preparedCardsStmt.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String number = resultSet.getString("number");
                    int balance_in_kopecs = resultSet.getInt("balance_in_kopecs");
                    // TODO: сложить все в список
                    System.out.println(id + "" + number + "" + balance_in_kopecs);
                }
            }

        }
    }


}
