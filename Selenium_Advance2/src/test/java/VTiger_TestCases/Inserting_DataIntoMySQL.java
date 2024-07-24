package VTiger_TestCases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

public class Inserting_DataIntoMySQL {

	public static void main(String[] args) throws Throwable {
		int result=0;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter");
		String ename=sc.next();
		Random random=new Random();
		int rInt = random.nextInt(1000);
		Driver driverRef=new Driver();
		System.out.println("ename: "+ename);
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:Mysql://localhost:3306/TP04","root","root");
		Statement stat = conn.createStatement();
		try {
		 result = stat.executeUpdate(" insert into emp values("+rInt+",'"+ename+"');");
		}
		catch (Exception e) {
		}
		if (result==0) {
			System.out.println(ename+" is already there in the Database");
		}
		else
		{
			System.out.println(ename+" is added successfully in the Database");
		}
		
		
		
		conn.close();
	}

}
