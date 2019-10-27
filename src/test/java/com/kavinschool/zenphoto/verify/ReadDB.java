/*
 * Author: Kangeyan Passoubady
 * (c) Kavin School
 */
package com.kavinschool.zenphoto.verify;

import com.kavinschool.zenphoto.utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The Class ReadDB.
 */
public class ReadDB {
	

	public static void main(String[] args) {

		Connection con = null;
		int count = 0;
		try {
			try {
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			} catch (SQLException e) {
				System.out
						.println("Oops! Got a MySQL error: " + e.getMessage());
			}
			// Connect to the database
			//Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(Constants.DB_CONN_WITH_TZ, Constants.DB_USER_ID, Constants.DB_PASSWORD);
			try {
				Statement st = con.createStatement();
				ResultSet res = st
						.executeQuery("SELECT * FROM  `kavinsch_TellAFriend`.`TellAFriend` LIMIT 0 , 30;");
				count = 0;
				while (res.next()) {
					count++;
					int id = res.getInt("id");
					String firstName = res.getString("name");
					String lastName = res.getString("email");
					String message = res.getString("message");
					String email1 = res.getString("email1");
					String email2 = res.getString("email2");
					String email3 = res.getString("email3");
					System.out.println(id + "," + firstName + "," + lastName
							+ "," + message + "," + email1 + "," + email2 + ","
							+ email3);
				}
				System.out.println("Number of rows selected from the database: "
						+ count);
				// Check whether the e-mail id exist in the database
			} catch (SQLException s) {
				s.printStackTrace();
				System.out.println("SQL statement is not executed!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
