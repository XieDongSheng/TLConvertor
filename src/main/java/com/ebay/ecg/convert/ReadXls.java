package com.ebay.ecg.convert;
import java.io.FileInputStream;  
import java.io.InputStream;  
import java.util.ArrayList;  
import java.util.List;  
import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.WorkbookSettings;  

/**
 *  
* ReadXls.java Create on 2014-2-24    
*     
* Copyright (c) 2014-2-24  
*     
* @author doxie@ebay.com   
* @version 1.0
*
 */
public class ReadXls {  
      
    public static List<String> readExcel(String excelfilePath) throws Exception {  
        String data="";
        InputStream ins = new FileInputStream(excelfilePath); 
        WorkbookSettings setEncode = new WorkbookSettings();   
        setEncode.setEncoding("UTF-8");  
        Workbook rwb = Workbook.getWorkbook(ins, setEncode);  
        List<String> alldata = new ArrayList<String>();  
        alldata.clear();  
        Sheet[] sheets=rwb.getSheets();
        int pages = sheets.length;
          
        for(int i=0; i<pages; i++) {  
            Sheet sheet = rwb.getSheet(i);             
           
            int rows = sheet.getRows();		
            int cols = sheet.getColumns();  
            	for(int r=1; r<rows; r++){
            		for(int c=0; c<cols; c++) {  
                                      
            			Cell excelRows = sheet.getCell(c, r);  
            			String strRow = excelRows.getContents();  
            			data+=(strRow+",");
                    
            		}
            		alldata.add(data);
            		data="";
            	}
              
        }  
               ins.close();  

        System.out.println(alldata);

        return alldata;  
    } 
}