package com.compoment.jsonToJava.creater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.google.gson.Gson;

/**
 * 请求 接收 函数
 * */
public class RequestRespond {

	public void requestRespond(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		for (InterfaceBean interfaceBean : interfaceBeans) {

			request(interfaceBean);
			respond(interfaceBean);
		}
	}

	public String request(InterfaceBean interfaceBean) {
		String m = "\n\n\n";
		List<String> mChirldClass = new ArrayList();
		String className="RequestParam" + interfaceBean.id ;	
		
	
		
		m += "/**" + interfaceBean.title + interfaceBean.id + "*/\n";
		m+="int n"+interfaceBean.id+"="+interfaceBean.id +";\n";
		m += "/**" + interfaceBean.title + interfaceBean.id + "*/\n";
		m += "public void request"+interfaceBean.id+"(){\n";
		
		 m+=className+" bean"+"=new "+className+"();\n";
		 
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
						    m+="bean."+row.enName+"=\"\";\n";
							
							isCustomerClass = false;
						}
					} else {
						if (isCustomerClass) {

						} else {
							m += "/** " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m+="List<String> "+row.enName+"List=new ArrayList();\n";
							m += "bean."+row.enName+"="+row.enName+ "List.toArray();\n";
						
						}
					}
					i++;
				}
			
				m+="}\n\n";
				
			} else {
				
				for (Row row : group.rows) {
					m += "/** " + row.cnName + " 备注:" + row.remarks + "*/\n";
					m +=  "bean."+row.enName+"=\"\";\n";

				}
			}

		}
		
		
		m+="Gson gson = new Gson();\n";
		m+= "String s  = gson.toJson(bean);\n";
		
		
		m+="Intent intent = new Intent();\n";
		m+="intent.setClass(.this,WaitActivity.class);\n";
		m+="Bundle bundle = new Bundle();\n";
		m+="bundle.putString(\"url\",WaitActivity.urlbase+\"/Serverlet"+interfaceBean.id+"?parameter=s\");\n";
		m+="intent.putExtras(bundle);\n";
		m+="startActivityForResult(intent,n"+interfaceBean.id+");\n";
		m += "}\n\n";

	

		System.out.println(m);
		return m;
	}
	
	
	public String  respond(InterfaceBean interfaceBean) {
		String m = "\n\n\n";
		List<String> mChirldClass = new ArrayList();
		String className="RespondParam" + interfaceBean.id ;	
		String classNameForCache="CacheRespondParam" + interfaceBean.id ;
		m+="List<"+className+"> listData=new ArrayList();\n";
		
		m += "/**" + interfaceBean.title + interfaceBean.id + "*/\n";
		m += "if (requestCode == n"+interfaceBean.id +"){\n";

		m+="Gson gson = new Gson();\n";
	
		m+= classNameForCache+" bean = gson.fromJson(body, "+classNameForCache+".class);\n";
		
		List<Group> groups = interfaceBean.respondGroups;
		

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
						  
							m+="for(int i=0;i<bean."+row.enName+";i++)\n{\n";
							  m+=className+" item"+groupCount+"=new "+className+"();\n";
							isCustomerClass = false;
						}
					} else {
						if (isCustomerClass) {

						} else {
							m += "/** " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m += "item"+groupCount+"."+row.enName+"=bean." + row.enName
									+ "[i];\n";
						
						}
					}
					i++;
				}
			
				m+="}\n\n";
				
			} else {
				 m+=className+" commonItem"+"=new "+className+"();\n";
				for (Row row : group.rows) {
					m += "/** " + row.cnName + " 备注:" + row.remarks + "*/\n";
					m +=  "commonItem." + row.enName + "=bean."+row.enName+";\n";

				}
			}
groupCount++;
		}
		m += "}\n\n";

	

		System.out.println(m);
		return m;
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
}
