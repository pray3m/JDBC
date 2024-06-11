import java.sql.*;

/*
 Scroll-sensitive and updatable ResultSet:
 - ResultSet.TYPE_SCROLL_SENSITIVE: This type of ResultSet allows the cursor to move forward, backward,
 and to specific rows. It is sensitive to changes made by others to the underlying data while the ResultSet is open.
 
 - ResultSet.CONCUR_UPDATABLE: This concurrency mode indicates that the ResultSet can be updated.
 It allows for modifications to the data in the ResultSet, which can then be reflected in the database.
 */

public class SensitiveResultSet {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/jdbc";
        String user = "postgres";
        String password = "p@ssword";

        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            readData(statement);

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void readData(Statement statement) {
        try {
            String SELECT_QUERY = "select * from student";
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
            displayData(resultSet);
        } catch (SQLException e) {
            System.out.println("Error reading data" + e.getMessage());
        }
    }

    public static void displayData(ResultSet resultSet) {
        try {
            System.out.printf("%-5s %-20s %-30s %-10s %-30s %-15s %-20s%n", "ID", "Name", "Address", "Roll No", "Email", "Phone Number", "Class");

            resultSet.next();
            resultSet.next();
            resultSet.previous();
            
            resultSet.updateString("name", "Preeem");
            resultSet.updateRow();
            
            System.out.printf("%-5d %-20s %-30s %-10d %-30s %-15s %-20s %n", resultSet.getInt("student_id"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getInt("roll_no"), resultSet.getString("email"), resultSet.getString("phone_number"), resultSet.getString("class"));

        } catch (SQLException e) {
            System.out.println("Error displaying data" + e.getMessage());
        }
    }

}
