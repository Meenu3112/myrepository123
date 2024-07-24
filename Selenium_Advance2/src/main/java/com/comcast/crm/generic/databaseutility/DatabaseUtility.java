package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.Resultset;

public class DatabaseUtility {
	Connection conn;
	public void getConnection() 
	{
		try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			 conn = DriverManager.getConnection("jdbc:Mysql://localhost:3306/tp04","root","root");
		} catch (Exception e) {
							}
	}
	public int executeNonSelectQuery()
	{	
		int result=0;
		try 
		{
			Statement state = conn.createStatement();
			result=state.executeUpdate("insert into emp values(12,'hbhyyh');");
		} catch (Exception e)
		{
			System.out.println("Hi");
		}
		return result;
	}
	public ResultSet executeSelectQuery(String query)
	{	
		ResultSet result1=null;
		try 
		{
			Statement state = conn.createStatement();
			result1=state.executeQuery(query);
		} catch (Exception e)
		{
		}
		return result1;
	}
	public void closeConnection() 
	{
			try {
				conn.close();
			} catch (SQLException e) {
				
			}
	}
}
