import java.sql.*;

public class FirstJdbc {
    
    private final String INSERT_QUERY = "";

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/jdbc";
        String user = "postgres";
        String password = "p@ssword";

        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();
            
            readData(statement);

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void createTable(Statement statement) {
        String createQuery = "";
        try {
            int rowsAffected = statement.executeUpdate(createQuery);
            System.out.println("Rows affected : " + rowsAffected);

        } catch (SQLException e) {
            System.out.println("Error creating table" + e.getMessage());
        }
    }

    public static void insertData(Statement statement) {
        String insertQuery = "";
        try {
            int rowsAffected = statement.executeUpdate(insertQuery);
            System.out.println("Rows affected : " + rowsAffected);
        } catch (Exception e) {
            System.out.println("Error inserting data" + e.getMessage());
        }
    }

    public static void readData(Statement statement) {
        String selectQuery = "SELECT student.student_id, student.name AS student_name, student.address AS student_address, student.roll_no, student.email AS student_email, student.phone_number, student.class, college.college_id, college.name AS college_name, college.address AS college_address, college.established_year, college.email AS college_email FROM student INNER JOIN college ON student.college_id = college.college_id";
        try {
            ResultSet resultSet = statement.executeQuery(selectQuery);
            displayData(resultSet);
        } catch (SQLException e) {
            System.out.println("Error reading data" + e.getMessage());
        }
    }

    public static void displayData(ResultSet resultSet) {
        try {

            System.out.printf("%-5s %-20s %-30s %-10s %-30s %-15s %-20s %-5s %-50s %-30s %-15s %-30s%n", "ID", "Name", "Address", "Roll No", "Email", "Phone number", "Class", "C_ID", "College Name", "College Address", "Est. Year", "College Email");

            while (resultSet.next()) {
                System.out.printf("%-5d %-20s %-30s %-10d %-30s %-15s %-20s %-5d %-50s %-30s %-15d %-30s%n", resultSet.getInt("student_id"), resultSet.getString("student_name"), resultSet.getString("student_address"), resultSet.getInt("roll_no"), resultSet.getString("student_email"), resultSet.getString("phone_number"), resultSet.getString("class"), resultSet.getInt("college_id"), resultSet.getString("college_name"), resultSet.getString("college_address"), resultSet.getInt("established_year"), resultSet.getString("college_email"));
            }

        } catch (SQLException e) {
            System.out.println("Error displaying data" + e.getMessage());
        }
    }

    
}
