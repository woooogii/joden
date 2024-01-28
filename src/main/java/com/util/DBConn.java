package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {

	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			String url= "jdbc:mysql://localhost:3306/test";
			String user = "joden";
			String pwd = "pass";
			
			if(conn==null) {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, pwd);
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return conn;
	}
	
	public static void close() {
	
		if(conn==null) {
			return;
		}
		
		try {
			if(!conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		conn = null;
	}
	
	
	
	
	

}
