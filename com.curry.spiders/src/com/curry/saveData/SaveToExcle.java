package com.curry.saveData;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.curry.pojo.Book;
import com.curry.pojo.BookList;

public class SaveToExcle implements ISaveData {

	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(ArrayList<Book> bList) {
		HSSFWorkbook wb = new HSSFWorkbook();//创建工作薄  	
		HSSFSheet sheet = wb.createSheet("test");//创建工作表，名称为test 
		//设置第一行为名字
		String[] titles = {"排名","书名","评价","评论数","URL"};
		HSSFRow row= sheet.createRow(0);
		HSSFCell cell;
		for(int i = 0;i<5;i++){
			 cell = row.createCell(i); 
			cell.setCellValue(new HSSFRichTextString(titles[i])); 
		} 
		int len=bList.size();
		List<Book> local=bList;
        for(int j=1;j<len+1;j++){
        	String[] values = {""+j,local.get(j-1).getTitle(),local.get(j-1).getCommonLerver(),String.valueOf(local.get(j-1).getCommonSize()),local.get(j-1).getUrl()};
        	HSSFRow trow= sheet.createRow(j);
        	for(int i=0;i<5;i++){
        		cell = trow.createCell(i); 
    			cell.setCellValue(new HSSFRichTextString(values[i])); 
        	}
        }
		ByteArrayOutputStream os = new ByteArrayOutputStream();  

		try{  
			wb.write(os);
		}catch(IOException e){  
			e.printStackTrace();  
			//return null;  
		} 
		byte[] xls = os.toByteArray();  
        File f =new File("C:\\douban\\excle");
        if(!f.exists()) f.mkdirs();
		File file = new File("C:\\douban\\excle\\Books.xls");  
		OutputStream out = null;  
		try {  
			out = new FileOutputStream(file);  
			try {  
				out.write(xls);  
			} catch (IOException e) {  
				// TODO Auto-generated catch block  
				e.printStackTrace();  
			}  
		} catch (FileNotFoundException e1) {  
			// TODO Auto-generated catch block  
			e1.printStackTrace();  
		} finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public void change() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
