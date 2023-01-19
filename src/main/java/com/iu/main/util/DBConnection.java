package com.iu.main.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection  {

	public static Connection getConnection() throws Exception {
		//1. 접속 정보 준비
		
		// 1) id
		String username = "hr";
		
		// 2) pw
		String password = "hr";
		
		// 3) url 정보
		//String url = "jdbc:oracle:thin:@ip:port:1521/ServiceName";
		//String url = "jdbc:oracle:thin:@ip:port:SID";
		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		
		// 4) driver		//driver는 생략해도 쓸 수 있음
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		Class.forName(driver);
		

		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
		
		
	}
	
	public static void disConnect(PreparedStatement st, Connection connection) throws Exception{
		st.close();
		connection.close();
	}
	
	public static void disConnect(ResultSet rs, PreparedStatement st, Connection connection)  throws Exception {
		rs.close();
		st.close();
		connection.close();
	}
	
}
