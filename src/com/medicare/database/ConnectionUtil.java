package com.medicare.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

	public Connection jdbcConnectionUtil() {

		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// To establish the connection

			String url = "jdbc:mysql://localhost:3306/medicare";
			String userid = "root";
			String password = "admin";

			connection = DriverManager.getConnection(url, userid, password);
			System.out.println("Test Connection Successfully");

		} catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}

}
