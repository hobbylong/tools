package com.compoment.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
/**
* ReadProperties.java
* Description: 读取操作属性配置文件
* @author li.b
* @version 2.0
* Jun 26, 2008
*/
public class KeyValue {
	
	public static String readCache(String key)
	{
		String courseFile = null;
		File directory = new File("");// 参数为空
		try {
			courseFile =directory.getCanonicalPath();
		

		String path = courseFile + "/res/other/cache.txt";
		
		return readValue(path,key);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return "";
	}
	
	public static void writeCache(String key,String value)
	{
		if(value==null || value.equals(""))
		{
			return;
		}
		String courseFile = null;
		File directory = new File("");// 参数为空
		try {
			courseFile =directory.getCanonicalPath();
		

		String path = courseFile + "/res/other/cache.txt";
		
		writeProperties(path,key,value);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	}
	
	//根据key读取value
	 public static String readValue(String filePath,String key) {
	  Properties props = new Properties();
	        try {
	         InputStream in = new BufferedInputStream (new FileInputStream(filePath));
	        // InputStream in= KeyValue.class.getResourceAsStream(filePath);//读取jar包内部文件( src内的)
	         props.load(in);
	         String value = props.getProperty (key);
	         if(value==null)
	        	 value="";
	            System.out.println(key+value);
	            return value;
	        } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	        }
	 }
	 
	 //读取properties的全部信息
	    public static void readProperties(String filePath) {
	     Properties props = new Properties();
	        try {
	        InputStream in = new BufferedInputStream (new FileInputStream(filePath));
	         //InputStream in= KeyValue.class.getResourceAsStream(filePath);
	         props.load(in);
	            Enumeration en = props.propertyNames();
	             while (en.hasMoreElements()) {
	              String key = (String) en.nextElement();
	                    String Property = props.getProperty (key);
	                    System.out.println(key+Property);
	                }
	        } catch (Exception e) {
	         e.printStackTrace();
	        }
	    }

	    //写入properties信息
	    public static void writeProperties(String filePath,String parameterName,String parameterValue) {
	     Properties prop = new Properties();
	     try {
	     InputStream fis = new FileInputStream(filePath);
	    	// InputStream fis= KeyValue.class.getResourceAsStream(filePath);
	            //从输入流中读取属性列表（键和元素对）
	            prop.load(fis);
	            //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
	            //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
	            OutputStream fos = new FileOutputStream(filePath);
	            prop.setProperty(parameterName, parameterValue);
	            //以适合使用 load 方法加载到 Properties 表中的格式，
	            //将此 Properties 表中的属性列表（键和元素对）写入输出流
	            prop.store(fos, "Update '" + parameterName + "' value");
	        } catch (IOException e) {
	         System.err.println("Visit "+filePath+" for updating "+parameterName+" value error");
	        }
	    }

	    public static void main(String[] args) {
	     readValue("info.properties","url");
	        writeProperties("info.properties","age","21");
	        readProperties("info.properties" );
	        System.out.println("OK");
	    }
	
}

 