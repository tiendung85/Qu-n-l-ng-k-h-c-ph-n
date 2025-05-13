package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public Connection c;

    public DBConnect() {
        openConnection();
    }

    private void openConnection() {
        try {
            if (c == null || c.isClosed()) {
                String url = "jdbc:sqlserver://localhost:1433;databaseName=N5;encrypt=true;trustServerCertificate=true;characterEncoding=UTF-8;sendStringParametersAsUnicode=true";
                String username = "sa";
                String pass = "1234567";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                c = DriverManager.getConnection(url, username, pass);
                System.out.println("Database connection established successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (c != null && !c.isClosed()) {
                c.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
