/*
 **Deprecation Notice:** 
 This program uses CachedRowSetImpl, a class from the com.sun.rowset package. 
 While it functions for disconnected data access, be aware that this package 
 is part of Sun's proprietary extensions and might be deprecated in later Java versions 
 (beyond Java 8). Consider using standard JDBC RowSet implementations from 
 the javax.sql.rowset package for broader compatibility.
*/

// Check another file  @JDBCRowSetExample

//import javax.sql.rowset.*;

import com.sun.rowset.CachedRowSetImpl;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.SQLException;

public class CachedRowSetExample {
    public static void main(String[] args) {
        String JDBC_URL = "jdbc:mysql://localhost:3306/sampledb";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "password";

        try {
            CachedRowSet cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.setUrl(JDBC_URL);
            cachedRowSet.setUsername(JDBC_USER);
            cachedRowSet.setPassword(JDBC_PASSWORD);

            cachedRowSet.setCommand("SELECT * FROM students");
            cachedRowSet.execute();

            // Process data without maintaining continuous connection to DB
            while (cachedRowSet.next()) {
                System.out.println(cachedRowSet.getString("name"));
            }

            // Update data if required
            cachedRowSet.absolute(2);  // move to the 2nd row
            cachedRowSet.updateString("name", "NewName");
            cachedRowSet.updateRow();

            // Sync changes back to database
            cachedRowSet.acceptChanges();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
