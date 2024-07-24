package VTiger_TestCases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class UsingMySql {

	@Test
	public void usingSelectQuery() throws Throwable
	{
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("Jdbc:MySql://localhost:3306/information_schema", "root", "root");
		System.out.println("Connection Established");
		Statement stat = conn.createStatement();
		ResultSet resultset = stat.executeQuery("select * from STATISTICS");
		while (resultset.next()) {
			System.out.println(resultset.getString(1)+"\t"+resultset.getString(2)+"\t"+resultset.getString(3)+"\t"+resultset.getString(4)+"\t"+resultset.getString(5));
		};
		
	}
}
