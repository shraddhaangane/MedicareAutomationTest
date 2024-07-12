package com.medicare.database;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOperations {

    private static final Logger log = LoggerFactory.getLogger(DatabaseOperations.class);

    public void cleanUserDetails(String email) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            ConnectionUtil connectionUtil = new ConnectionUtil();
            connection = connectionUtil.jdbcConnectionUtil();

            String selectQuery = "select id from User_detail where email =?";
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, email);
            ResultSet resultset = preparedStatement.executeQuery();
            Integer userId = null;

            while (resultset.next()) {
                userId = resultset.getInt("id");
            }

            if (null != userId) {
                String deleteCartQuery = "DELETE from Cart where user_id =?";
                preparedStatement = connection.prepareStatement(deleteCartQuery);
                preparedStatement.setInt(1, userId);
                preparedStatement.executeUpdate();
                log.info("Record deleted from Cart table for User_id: {}", userId);

                String orderItemQuery = "select oi.id from Order_item oi inner join Order_detail od on od.id = oi.order_id and od.user_id = ?";
                preparedStatement = connection.prepareStatement(orderItemQuery);
                preparedStatement.setInt(1, userId);
                resultset = preparedStatement.executeQuery();

                while (resultset.next()) {
                    String orderItemId = resultset.getString("id");
                    log.info("Order items retrieved for order item id : {}", orderItemId);
                    preparedStatement = connection.prepareStatement("delete from Order_item where id = ?");
                    preparedStatement.setString(1, orderItemId);
                    preparedStatement.executeUpdate();

                    log.info("Records deleted from Order Item table for order item id : {}", orderItemId);
                }

                String deleteOrderdetailQuery = "DELETE from Order_detail where user_id=?";
                preparedStatement = connection.prepareStatement(deleteOrderdetailQuery);
                preparedStatement.setInt(1, userId);
                preparedStatement.executeUpdate();
                log.info("Record deleted from Order_detail for User_id: {}", userId);

                String deleteAddressQuery = "Delete from Address where user_id=?";
                preparedStatement = connection.prepareStatement(deleteAddressQuery);
                preparedStatement.setInt(1, userId);
                preparedStatement.executeUpdate();
                log.info("Record deleted from Address for User_id: {}", userId);

                String deleteUserDetailQuery = "Delete from User_detail where id=?";
                preparedStatement = connection.prepareStatement(deleteUserDetailQuery);
                preparedStatement.setInt(1, userId);
                preparedStatement.executeUpdate();
                log.info("Record deleted from User_detail for User_id: {}", userId);

            }
        }catch (Exception ex) {
            log.error("Error while performing database operations.");
        }finally {
            preparedStatement.close();
            connection.close();
            log.info("Database connection closed");
        }

    }


}