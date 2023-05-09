package SQLData;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import SQLData.SQLDemo.RowList;
//Class that will be used for using our sql databases to query information on the factors that will be used to get locations based on user preferences. 
public class FactorGetter {
		//public static void main(String args[]) {
	public int Get_Crime_Factor(int Scale) throws ClassNotFoundException, IOException {
		//string that will hold sql query requestion
		
		int Factor = 0;
		//checks to see if user arguments were valid
		if((Scale < 0 || Scale > 10)) {
			throw new IllegalArgumentException("Scale must be between 1-10");
		}
		// load Derby JDBC driver
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			System.err.println("Could not load Derby JDBC driver");
			System.err.println(e.getMessage());
			System.exit(1);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		// connect to the database
		try {
			conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		try {
			conn.setAutoCommit(true);

			// a canned query to find book information (including author name) from title
			stmt = conn.prepareStatement(
					"select RatePerHundredThousand "
							+ "from CrimeRate "
							+ "where Scale = ?"
			);

			// substitute the title entered by the user for the placeholder in the query
			stmt.setInt(1, Scale);

			// execute the query
			resultSet = stmt.executeQuery();

			if(resultSet.next()) {
				Factor = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// close result set, statement, connection
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(conn);
		}

		
		return Factor;
		
		
		
	}
	//public static void main(String args[]) {
	public int Get_AvgSalary_Factor(int Scale) throws ClassNotFoundException, IOException {
		//string that will hold sql query requestion
		
		int Factor = 0;
		//checks to see if user arguments were valid
		if((Scale < 0 || Scale > 10)) {
			throw new IllegalArgumentException("Scale must be between 1-10");
		}
		// load Derby JDBC driver
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			System.err.println("Could not load Derby JDBC driver");
			System.err.println(e.getMessage());
			System.exit(1);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		// connect to the database
		try {
			conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		try {
			conn.setAutoCommit(true);

			// a canned query to find book information (including author name) from title
			stmt = conn.prepareStatement(
					"select AvgSalaryPerHousehold "
							+ "from AverageSalary "
							+ "where Scale = ?"
			);

			// substitute the title entered by the user for the placeholder in the query
			stmt.setInt(1, Scale);

			// execute the query
			resultSet = stmt.executeQuery();

			if(resultSet.next()) {
				Factor = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// close result set, statement, connection
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(conn);
		}

	
		return Factor;
		
		
		
	}
	//public static void main(String args[]) {
	public float Get_CostofLiving_Factor(int Scale, int COLType) throws ClassNotFoundException, IOException {
		//string that will hold sql query requestion
		
		System.out.println("scale is " + Scale + ", col type is " + COLType);
		
		float Factor = 0.0f;
		//checks to see if user arguments were valid
		if((Scale < 0 || Scale > 10)) {
			throw new IllegalArgumentException("Scale must be between 1-10");
		}
		// load Derby JDBC driver
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			System.err.println("Could not load Derby JDBC driver");
			System.err.println(e.getMessage());
			System.exit(1);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;

		// connect to the database
		try {
			conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		try {
			conn.setAutoCommit(true);

			// a canned query to find book information (including author name) from title
			if(COLType == 0) {
				stmt = conn.prepareStatement(
						"select CostOfLivingIndex "
								+ "from CostOfLivingRent "
								+ "where Scale = ?"
					);
			} else if(COLType == 1) {
				stmt = conn.prepareStatement(
						"select CostOfLivingIndex "
								+ "from CostOfLivingMortgage "
								+ "where Scale = ?"
					);
			} else {
				stmt = conn.prepareStatement(
						"select CostOfLivingIndex "
								+ "from CostOfLivingNoMortgage "
								+ "where Scale = ?"
					);
			}
			// substitute the title entered by the user for the placeholder in the query
			stmt.setInt(1, Scale);

			// execute the query
			resultSet = stmt.executeQuery();

			if(resultSet.next()) {
				System.out.println("output is " + resultSet.getFloat(1));
				Factor = resultSet.getFloat(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// close result set, statement, connection
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(conn);
		}

		
		return Factor;
		
		
		
	}
}
