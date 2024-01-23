package databaselister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseLister {

	private final String url = "jdbc:postgresql://localhost/mydb";
	private final String user = "postgres";
	private final String password = "1234";

	private Connection myConn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;
	private String dbQuery = "SELECT * FROM pg_database";
	
	public void connect() throws SQLException {

		try {
			myConn = DriverManager.getConnection(url, user, password);
			System.out.println("Successfully connected.\n");
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(dbQuery);

			System.out.println("DATABASES LIST:");
			while (myRs.next()) {
				System.out.println(myRs.getString("datname"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (myConn != null)
				myConn.close();

			if (myStmt != null)
				myStmt.close();

			if (myRs != null)
				myRs.close();
		}
	}
}
