import java.sql.*;

public class ForwardOnlyResultset {

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
            
            while (resultSet.next()) {
//                resultSet.updateString("name","Prem");
//                resultSet.updateRow();
                System.out.printf("%-5d %-20s %-30s %-10d %-30s %-15s %-20s %n", resultSet.getInt("student_id"), resultSet.getString("name"), resultSet.getString("address"), resultSet.getInt("roll_no"), resultSet.getString("email"), resultSet.getString("phone_number"), resultSet.getString("class"));
            }

        } catch (SQLException e) {
            System.out.println("Error displaying data" + e.getMessage());
        }
    }

}
