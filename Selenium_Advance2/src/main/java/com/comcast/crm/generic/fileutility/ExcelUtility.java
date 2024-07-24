package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcelFile(String sheetname,int rowNum,int cellNum) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IPathConstant.EXCEL_PATH);
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return data;
	}
	
	public void setDataToExcelFile(String sheetname,int rowNum,int cellNum,String data) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IPathConstant.EXCEL_PATH);
		Workbook wb = WorkbookFactory.create(fis);
		 wb.getSheet(sheetname).getRow(rowNum).createCell(cellNum).setCellValue(data);
		 FileOutputStream fos=new FileOutputStream(IPathConstant.EXCEL_PATH);
		 wb.write(fos);
		 wb.close();
	}

	public int getRowCount(String sheetname) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IPathConstant.EXCEL_PATH);
		Workbook wb = WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(sheetname).getLastRowNum();
		return rowCount;
	}
}
