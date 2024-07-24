package VTiger_TestCases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class FetchingfromSql {

	public static void main(String[] args) throws Throwable {
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("Jdbc:MySql://localhost:3306/TP04", "root", "root");
		Statement state = conn.createStatement();
		ResultSet resultset = state.executeQuery("Select Emp_Name from emp_TP where Emp_Id='D' and Emp_Address='Hyderabad';");
		while (resultset.next()) {
			String name=resultset.getString(1);
			System.out.print(name);
//		System.out.print(resultset.getString(3)+"\t");
//		System.out.println(resultset.getString(4));
		}
		conn.close();
	}

}
