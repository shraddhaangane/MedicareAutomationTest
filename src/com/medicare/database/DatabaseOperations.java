package com.medicare.database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOperations {
	
public void cleanUserDetail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset=null;
		
	    ConnectionUtil connectionUtil = new ConnectionUtil();
	    connection = connectionUtil.jdbcConnectionUtil();
		
		String selectQuery= "select * from User_detail where email =?";
		preparedStatement = connection.prepareStatement(selectQuery);
		preparedStatement.setString(1,email);
		resultset = preparedStatement.executeQuery();
		Integer userId = null;
		
		while(resultset.next())
		{
			userId = resultset.getInt("id");
		}
		
		if(null != userId) {
			String deleteCartQuery = "DELETE from Cart where user_id =?";
			preparedStatement = connection.prepareStatement(deleteCartQuery);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
			System.out.println("Record deleted from Cart table for User_id:" + userId );
			
			
			String deleteOrderdetailQuery = "DELETE from Order_detail where user_id=?";
			preparedStatement = connection.prepareStatement(deleteOrderdetailQuery);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
			System.out.println("Record deleted from Order_detail for User_id:" + userId );
	
			
			String deleteAddressQuery= "Delete from Address where user_id=?";
			preparedStatement = connection.prepareStatement(deleteAddressQuery);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
			System.out.println("Record deleted from Address for User_id:" + userId );
			
			String deleteUserDetailQuery = "Delete from User_detail where id=?";
			preparedStatement = connection.prepareStatement(deleteUserDetailQuery);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
			System.out.println("Record deleted from User_detail for User_id:" + userId );
			
		}
		connection.close();
		
	}
	
		
}