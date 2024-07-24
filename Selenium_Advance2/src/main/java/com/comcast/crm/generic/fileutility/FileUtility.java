package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;


public class FileUtility {

	
		// Read data from Properties File
		
		public String getDataFromProp(String key) throws Throwable
		{
			FileInputStream fis=new FileInputStream(IPathConstant.FILEUTILITY_PATH);
			Properties pro=new Properties();
			pro.load(fis);
			String value = pro.getProperty(key);
			return value;
		}
		
	

}
