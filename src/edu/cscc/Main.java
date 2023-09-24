package edu.cscc;
import java.sql.*;

//TODO - student name, date, purpose of program
//Calvin Gates, 9/26/2022, formatting data obtained from a database

public class Main {

	// JDBC driver name and database URL
	// static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	//Database credentials
	static final String USER = "";
	static final String PASS = "";
	static final String PORT = "";
	static final String HOST = "";
	static final String DATABASE = "";

	// Build connection URL
	static final String connectionURL = "jdbc:sqlserver://" + HOST + ":" + PORT +
			";databaseName=" + DATABASE + ";user=" + USER + ";password=" + PASS + 
			";encrypt=true;TrustServerCertificate=true";
	
	public static void main(String[] args) {

		// Open a connection using Connection URL - auto close connection and statement
		try (Connection conn = DriverManager.getConnection(connectionURL); 
				Statement stmt = conn.createStatement();
				) {

			//String sql = "SELECT @@VERSION";
			String sql = "SELECT CompanyName, Address, City, Region, PostalCode, Country FROM Customers";
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println("Company Name                             Address                                            City                 Region          Postal     Country");
			System.out.println("========================================================================================================================================================");

			while (rs.next()) {
				String companyName = rs.getNString(1);
				String address = rs.getNString(2);
				String city = rs.getNString(3);
				String region = rs.getNString(4);
				String postal = rs.getNString(5);
				String country = rs.getNString(6);
				//System.out.println(region == null);
				if (region == null) {
					region = "n/a";
				}
				
				System.out.printf("%-40s %-50s %-20s %-15s %-10s %-15s\n", companyName, address, city, region, postal, country);
			}
			// Clean-up result set
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} 
		System.out.println("========================================================================================================================================================");
	}
}
