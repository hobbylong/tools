package com.compoment.jsonToJava.creater;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;


import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.KeyValue;
import com.google.gson.Gson;

/**
 * 请求 接收 函数
 * */
public class RequestRespondForServerlet {



	public String request(InterfaceBean interfaceBean) {
		String m = "\n\n\n";
		List<String> mChirldClass = new ArrayList();
		String className="RequestParam" + interfaceBean.id ;	
		
	
		
		m+="Gson gson = new Gson();\n";
		
		m+= className+" bean = gson.fromJson(parameter, "+className+".class);\n";
		
	
		 
		List<Group> groups = interfaceBean.requestGroups;
		int groupCount=0;
		for (Group group : groups) {
		
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {

				int i = 0;
				boolean isCustomerClass = false;
				for (Row row : group.rows) {
					
					if (i == 0) {
						// 循环域开始
						if (row.type != null && !isCommonType(row.type)) {//自定义对象
							
							isCustomerClass = true;
						} else {//非自定义对象
							m += "/** " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
						    m+="bean."+row.enName+";//"+row.type+"\n";
							
							isCustomerClass = false;
						}
					} else {
						if (isCustomerClass) {

						} else {
							m += "/** " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							
							m += "bean."+row.enName+";//"+row.type+"[]\n";
						
						}
					}
					i++;
				}
			
				m+="}\n\n";
				
			} else {
				
				for (Row row : group.rows) {
					m += "/** " + row.cnName + " 备注:" + row.remarks + "*/\n";
					m +=  "bean."+row.enName+";//"+row.type+"\n";

				}
			}

		}
		
		
		

	

		System.out.println(m);
		return m;
	}
	
	
	public String  respond(InterfaceBean interfaceBean) {
		String m = "\n\n\n";
		List<String> mChirldClass = new ArrayList();
		String className="RespondParam" + interfaceBean.id ;	
		String classNameForCache="cacheRespondParam" + interfaceBean.id ;
		
		
		m += "/**" + interfaceBean.title + interfaceBean.id + "*/\n";
		m += firstCharUpperCase(classNameForCache)+" "+classNameForCache+"=new "+firstCharUpperCase(classNameForCache)+"(); \n";


		
		List<Group> groups = interfaceBean.respondGroups;
		

		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				for (Row row : group.rows) {
					m += "/** " + row.cnName + " 备注:" + row.remarks + "*/\n";
					m += classNameForCache+"." + row.enName + "=;//"+row.getType()+"\n";

				}
			} else {
				int i = 0;
				boolean isCustomerClass = false;
				for (Row row : group.rows) {
					
					if (i == 0) {// 循环域开始
						if (row.getType() != null && !isCommonType(row.getType())) {
							m += "\n\n/** " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m += "public List<" + row.getType() + "> " + row.getType() + ";\n";
							mChirldClass.add(chirldClass(group));
							isCustomerClass = true;
						} else {
							m += "\n\n/** " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
						
							m += classNameForCache+"." + row.enName + "=;//"+row.getType()+"\n";
							isCustomerClass = false;
						}
					} else {
						if (isCustomerClass) {

						} else {
					
							//if(type.equals("CacheRespond"))
							{
							m += "/** " + row.cnName + " (数组)备注:" + row.remarks
									+ "*/\n";
							m += classNameForCache+"." + row.enName + "=new "+row.getType()+"[]{};//"+row.getType()+"[]\n";
						
							}
						}
					}
					i++;
				}
			}

		}
		
		
	
		for (String cirldClassString : mChirldClass) {
			m += cirldClassString + "\n\n";
		}
	

		System.out.println(m);
		return m;
	}

	
	public String chirldClass(Group group) {
		String n = "";
		int i = 0;
		for (Row row : group.rows) {
			if (i == 0) {
				n += "/** 子类" + row.getType() + " " + row.cnName + " 备注:"
						+ row.remarks + "*/\n";
				n += "public class " + row.getType() + "{\n";
			} else {
				n += "public " + row.getType() + " " + row.enName + ";\n";
			}
			i++;
		}
		n += "}\n\n\n";
		return n;
	}
	
	
	

	public boolean isCommonType(String type) {
		if (type.equals("String") || type.equals("int") || type.equals("long")||type.equals("float")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNum(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	/** 首字母大写 */
	public static String firstCharUpperCase(String s) {
		if (s.length() > 0) {
			String firstchar = String.valueOf(s.charAt(0)).toUpperCase();
			s = s.substring(1);
			s = firstchar + s;
			return s;
		} else {
			return null;
		}

	}
}
