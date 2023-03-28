import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseOperation {
    public Connection getConnection () {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javaCoursesTask", "root", "Password");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Statement getStatement () {
        Statement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return getConnection().createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void executeInsertQuery(String expression, double result) {
        String insertQuery = "INSERT INTO math_expres (Expression, Result) values ('" + expression +  "', " + result + ")";
        try {
            getStatement().executeUpdate(insertQuery);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printRecordByExpressionResult (double expectedResult) {
        String retrieveQuery = "SELECT * from math_expres WHERE Result = " + expectedResult;
        try {
            ResultSet resultSet = getStatement().executeQuery(retrieveQuery);
            while (resultSet.next())
                System.out.println("ID: " + resultSet.getInt(1) + " Expression: " + resultSet.getString(2) + " Result: " + resultSet.getDouble(3));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printAllRecordsFromDataBase () {
        try {
            ResultSet resultSet = getStatement().executeQuery("SELECT * from math_expres");
            while (resultSet.next())
                System.out.println("ID: " + resultSet.getInt(1) + " Expression: " + resultSet.getString(2) + " Result: " + resultSet.getDouble(3));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateRecordById (int id, String expression, double result) {
        String updateQueryExpression = "UPDATE math_expres SET Expression = '" + expression + "' WHERE ID = " + id;
        String updateQueryResult = "UPDATE math_expres SET Result = '" + result + "' WHERE ID = " + id;
        try {
            getStatement().executeUpdate(updateQueryExpression);
            getStatement().executeUpdate(updateQueryResult);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
