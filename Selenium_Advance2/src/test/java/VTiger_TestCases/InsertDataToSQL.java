package VTiger_TestCases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

public class InsertDataToSQL {

	public static void main(String[] args) throws Throwable {
		boolean flag=false;
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter name :");
		String ename=sc.next();
		System.out.println("ename: "+ename);
		Random random=new Random();
		int rInt = random.nextInt(1000);
		
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:Mysql://localhost:3306/TP04","root","root");
		Statement stat = conn.createStatement();
		ResultSet resultset = stat.executeQuery("select * from emp1;");
		while (resultset.next())
		{
			String name=resultset.getString(2);
			if (name.equalsIgnoreCase(ename)) 
			{
				flag=true;
				break;
			}
		}
		if (flag==false) 
		{
			int result = stat.executeUpdate(" insert into emp1 values("+rInt+",'"+ename+"');");
			System.out.println(ename+" is added successfully in the Database");
		}
		else
		{
			System.out.println(ename+" is already there in the Database");
		}
		
		conn.close();
	}

	}


