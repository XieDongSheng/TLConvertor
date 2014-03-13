package com.ebay.ecg.convert;
import java.io.*;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.*;

/**
* 
* ReadXml.java Create on 2014-2-24    
*     
* Copyright (c) 2014-2-24  
*     
* @author doxie@ebay.com   
* @version 1.0
*
*/

class ReadXml{

 private File xmlFile;
 
 public List<String> getName(String strFileName) throws DocumentException{
	 xmlFile=new File(strFileName);      
 	  SAXReader reader=new SAXReader();     
 	  Document xmlDoc;
	
		xmlDoc = reader.read(xmlFile);
	 
		Element root=xmlDoc.getRootElement();
		List<String> li = new ArrayList<String>();
		List testcase=xmlDoc.selectNodes("//testcase");
		
		for(Iterator i=testcase.iterator();i.hasNext();){
			Element ele=(Element)i.next();
			System.out.println("Test "+ele.attribute("name").getValue()); 
			li.add(ele.attribute("name").getValue());
		}
	 return li;
 }
 
 public List<String> getKey(String strFileName) throws DocumentException{
	  xmlFile=new File(strFileName);      
 	  SAXReader reader=new SAXReader();     
 	  Document xmlDoc;
	
	  xmlDoc = reader.read(xmlFile);
	 
 	  Element root=xmlDoc.getRootElement();
 	  List<String> li = new ArrayList<String>();
 	  List testcase=xmlDoc.selectNodes("//keyword");
 	  
		for(Iterator i=testcase.iterator();i.hasNext();){
			Element ele=(Element)i.next();
//			System.out.println("Test "+ele.attribute("name").getValue()); 
			li.add(ele.attribute("name").getValue());
		}
	 return li;
 }
 
 public int[] getTime(String strFileName) throws DocumentException{
	 xmlFile=new File(strFileName);      
	 SAXReader reader=new SAXReader();     
	 Document xmlDoc;
	 
	 xmlDoc = reader.read(xmlFile);
	 
	 	
	  	List step_number = xmlDoc.selectNodes("//step_number");
		String number = "";
		int[] ti = new int[1000];
		
		for(Iterator it = step_number.iterator();it.hasNext();){
			Element ele=(Element)it.next();
			number += ele.getText();
		} 
		
		System.out.println("Output Array size: "+number);
		String[] num = number.split("1");
		System.out.println("Output Array length: "+num.length);
		
		for(int i=1; i<num.length; i++){
			System.out.println(num[i].length());
			ti[i] = num[i].length()+1;
		} 
		return ti;
			
 }
 
 
 public List[] Read(String strFileName) throws DocumentException{
  
  
	  xmlFile=new File(strFileName);      
  	  SAXReader reader=new SAXReader();     
  	  Document xmlDoc;
	
	  xmlDoc = reader.read(xmlFile);
	 
  	  Element root=xmlDoc.getRootElement();    

	  
  	  
  	  	int add_num = 0;
  	  	
  	  	List step_number = xmlDoc.selectNodes("//step_number");
 		String number = "";
 		
 		for(Iterator it = step_number.iterator();it.hasNext();){
 			Element ele=(Element)it.next();
 			number += ele.getText();
 		} 
 		System.out.println("Output array bumber: "+number);
 		String[] num = number.split("1");
 		int leng = num.length-1;
 		System.out.println("Output array length: "+num.length);
 		
 		for(String len:num){
 			System.out.println(len.length());
 			add_num = len.length();
 		}
 		
  	  	List testcase=xmlDoc.selectNodes("//version");
  	  	
  	  	
		List importance=xmlDoc.selectNodes("//importance");

		List execution_type=xmlDoc.selectNodes("//testcase/execution_type");
  	  
		List version=xmlDoc.selectNodes("//version");
			
   		List summary=xmlDoc.selectNodes("//summary");
 
   		List preconditions=xmlDoc.selectNodes("//preconditions");
   		
   		
   		
		List actions=xmlDoc.selectNodes("//actions");
		System.out.println("list length:"+actions.size());

		List expectedresults=xmlDoc.selectNodes("//expectedresults");

			
		List externalid=xmlDoc.selectNodes("//externalid");
		
			/*for(Iterator i=externalid.iterator();i.hasNext();){
				Element ele=(Element)i.next();
				System.out.println("Test "+ele.getText()); 
			}*/
   
 	List[] result = {testcase,importance,execution_type,version,summary,preconditions,step_number,actions,expectedresults,externalid,};
 	
	
	return result;   
  	
 }
 
/* public static void main(String [] args) throws DocumentException{

    new ReadXml().getTime("F://testcases.xml");

  }*/
}