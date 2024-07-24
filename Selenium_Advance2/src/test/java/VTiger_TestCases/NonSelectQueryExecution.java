package VTiger_TestCases;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;

public class NonSelectQueryExecution {

	public static void main(String[] args)  {
		
		//String query="";
		DatabaseUtility dlib=new DatabaseUtility();
		dlib.getConnection();
		int res = dlib.executeNonSelectQuery();
		System.out.println(res);
		dlib.closeConnection();
		
		
	}

}
