import java.sql.*;

// use execute method 

public class PreparedStatementDemo {

    public static void main(String[] args) {
        String URL = "jdbc:postgresql://localhost:5432/jdbc";
        String USER = "postgres";
        String PASSWORD = "p@ssword";

        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement statement = connection.createStatement();

//            insertDoctor("hello", "hello", "hello", connection);
//            updateDoctor("hello", "newemail@example.com", connection);
            deleteDoctor(11, connection);
            printTableData(statement);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void printTableData(Statement statement) throws SQLException {
        boolean isResultSet = statement.execute("Select * from doctor");

        if (isResultSet) {
            ResultSet resultSet = statement.getResultSet();
            System.out.printf("%-10s %-15s %-20s %-10s%n", "Doctor_id", "First_name", "Specialty", "Email");
            while (resultSet.next()) {
                System.out.printf("%-10s %-15s %-20s %-10s%n", resultSet.getInt("doctor_id"), resultSet.getString("first_name"), resultSet.getString("specialty"), resultSet.getString("email"));
            }
        }
    }

    public static void insertDoctor(String name, String specialty, String email, Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO doctor(first_name,specialty,email) VALUES (?, ? ,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, specialty);
        preparedStatement.setString(3, email);
//        preparedStatement.executeUpdate();
//        preparedStatement.close();

        boolean isResultSet = preparedStatement.execute();
        if (!isResultSet) {
            int updateCount = preparedStatement.getUpdateCount();
            System.out.println("Update Count------->" + updateCount);
        }
    }

    public static void updateDoctor(String name, String newEmail, Connection connection) throws SQLException {
        String updateQuery = "UPDATE doctor SET email= ? WHERE first_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setString(1, newEmail);
        preparedStatement.setString(2, name);
        
        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println("Update Count------->" + rowsAffected);
        preparedStatement.close();
    }
    
    public static void deleteDoctor(int doctorId, Connection connection) throws  SQLException {
        String deleteQuery = "DELETE FROM doctor WHERE doctor.doctor_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.setInt(1, doctorId);
        int rowsAffected =preparedStatement.executeUpdate();
        preparedStatement.close();

        System.out.println("Delete count ---------> " + rowsAffected);
    }


}