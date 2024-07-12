package com.medicare.database;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
public class ConnectionUtil {

	private static final Logger log = LoggerFactory.getLogger(ConnectionUtil.class);

	public Connection jdbcConnectionUtil() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// To establish the connection
			String url = "jdbc:mysql://localhost:3306/medicare";
			String userid = "root";
			String password = "admin";

			connection = DriverManager.getConnection(url, userid, password);
			log.info("Test Connection Successfully");
		} catch (Exception e) {
			log.error("Error while connecting to database : {}", e.getMessage());
		}
		return connection;
	}

}
