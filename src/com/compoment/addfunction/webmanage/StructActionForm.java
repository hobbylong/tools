package com.compoment.addfunction.webmanage;

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
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.google.gson.Gson;
/**
 * 查询 接收Bean 拥有新增，修改，删除，查询需要的所有变量
 * */
public class StructActionForm {

	public  StructActionForm(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		for (InterfaceBean interfaceBean : interfaceBeans) {
			
			beanCreate(interfaceBean, "Respond");
		}
	}

	public void beanCreate(InterfaceBean interfaceBean, String type) {
		String m = "\n\n\n";
		List<String> mChirldClass = new ArrayList();

	
		m += "/**" + interfaceBean.title + interfaceBean.enName + "*/\n";
		m += "public class "  + interfaceBean.enName + "ActionForm extends ActionForm{\n";
		m += "public String checkbox_row_id[];\n";
		m += "public List<"+interfaceBean.enName+"Bean> redo;\n";
		List<Group> groups = null;
		if (type.equals("Request")) {
			groups = interfaceBean.requestGroups;
		} else {
			groups = interfaceBean.respondGroups;
		}

		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				for (Row row : group.rows) {
					
					m += "/** " + row.cnName + " 备注:" + row.remarks
							+ "*/\n";
					m += "public " + row.getType() + " " + row.enName.toLowerCase()
							+ ";\n";
					
					m += "/** Array[]" + row.cnName + " 备注:" + row.remarks
							+ "*/\n";
					m += "public " + row.getType() + " " + row.enName.toLowerCase()
							+ "s[];\n";
					
					m += "/** Collection" + row.cnName + " 备注:" + row.remarks
							+ "*/\n";
					m += "public Collection<" + row.getType() + ">  " + row.enName.toLowerCase()
							+ "list;\n";
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
							m += "public " + row.getType() + " " + row.enName.toLowerCase()
									+ ";\n";
							isCustomerClass = false;
						}
					} else {
						if (isCustomerClass) {

						} else {
							if(type.equals("Request"))
							{
								m += "/** " + row.cnName + " 备注:" + row.remarks
										+ "*/\n";
								m += "public " + row.getType() + " " + row.enName.toLowerCase()
										+ "[];\n";
							}
							if(type.equals("Respond"))
							{
							m += "/** " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m += "public " + row.getType() + " " + row.enName.toLowerCase()
									+ ";\n";
							
							m += "/** Array[]" + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m += "public " + row.getType() + " " + row.enName.toLowerCase()
									+ "s[];\n";
							
							m += "/** Collection" + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m += "public Collection<" + row.getType() + ">  " + row.enName.toLowerCase()
									+ "list;\n";
							
				
							}
							if(type.equals("CacheRespond"))
							{
							m += "/** " + row.cnName + " (数组)备注:" + row.remarks
									+ "*/\n";
							m += "public " + row.getType() + " " + row.enName.toLowerCase()
									+ "[];\n";
							}
						}
					}
					i++;
				}
			}

		}
		m += "}\n\n";

		for (String cirldClassString : mChirldClass) {
			m += cirldClassString + "\n\n";
		}
	
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/webManager",interfaceBean.enName+"ActionForm", "java", m);
		System.out.println(m);
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
				n += "public " + row.getType() + " " + row.enName.toLowerCase() + ";\n";
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
	
	public void makeFile(String fileName,String s)
	{
		try {
		String doc=KeyValue.readCache("docPath");
		int p=doc.lastIndexOf("/");
		if(p==-1)
		{
			 p=doc.lastIndexOf("\\");
		}
		
		
		      
		    
		
		File tofile=new File(doc.substring(0, p)+"/java/"+fileName+".java");
		  if(! tofile.exists()) {  
	            makeDir(tofile.getParentFile());  
	        }  
	      
		  tofile.createNewFile(); 
		
		FileWriter fw;
		
			fw = new FileWriter(tofile);
			BufferedWriter buffw=new BufferedWriter(fw);
			PrintWriter pw=new PrintWriter(buffw);
		
		

	
		pw.println(s);

		pw.close();
		buffw.close();
		fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  public  void makeDir(File dir) {  
	        if(! dir.getParentFile().exists()) {  
	            makeDir(dir.getParentFile());  
	        }  
	        dir.mkdir();  
	    }  
	
	
}





