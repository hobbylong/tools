package com.compoment.addfunction.webmanage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.compoment.creater.first.QuotationMarks;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class TableToHibernateEntity {
	
	
	public TableToHibernateEntity(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		for (InterfaceBean interfaceBean : interfaceBeans) {
			
			tableScriptToHibernateEntity(interfaceBean, "Respond");
		}
	}
	
	public void tableScriptToHibernateEntity(InterfaceBean interfaceBean ,String type)
	{
		

		int keycount=0;
		List<Group> groups = interfaceBean.respondGroups;
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
						if(row.remarks.toLowerCase().contains("key"))
						{
							keycount++;
						}
				}
			}

		}

			

			String m = "\n\n";
			m += "import javax.persistence.Entity;\n";
			m += "import javax.persistence.Id;\n";
			m += "import javax.persistence.Table;\n";
			m += "import javax.persistence.GeneratedValue;\n";
			m += "import javax.persistence.GenerationType;\n";
			m += "import org.apache.commons.lang.builder.ToStringBuilder;\n";
			m += "import org.hibernate.annotations.Cache;\n";
			m += "import org.hibernate.annotations.CacheConcurrencyStrategy;\n";

			m+="//"+interfaceBean.title+"\n";
			m += "@Entity\n";
			m += "@Table(name = \""+interfaceBean.tableName+"\")\n";
			if(keycount>1)
			{
			m+="@IdClass("+interfaceBean.enName+"EntityIds.class)\n";
			}
			m += "public class "+interfaceBean.enName+"Entity {\n";
			
			
		
			for (Group group : groups) {
				String groupname = group.name;
				if (groupname.equals("CommonGroup")) {
					int i = 0;
					for (Row row : group.rows) {
					
						if(row.type.toLowerCase().contains("int"))
						{
							m += "	private int " + row.enName.toLowerCase() + ";//"+row.cnName+"\n";
						}else
						{
							m += "	private String " + row.enName.toLowerCase() + ";//"+row.cnName+"\n";
						}
						
						i++;
					}
				}

			}

			
			
			
			
			
			for (Group group : groups) {
				String groupname = group.name;
				if (groupname.equals("CommonGroup")) {
					int i = 0;
					for (Row row : group.rows) {
						String start = row.enName.substring(0, 1)
								.toUpperCase();
						String end = row.enName.substring(1)
								.toLowerCase();
						if (i == 0) {// 循环域开始
							
							if(row.remarks.toLowerCase().contains("key"))
							{
							m += "	@Id\n";
							m += "	@GeneratedValue(strategy=GenerationType.AUTO)\n";
							}
							
							if(row.type.toLowerCase().contains("int"))
							{
								m += "	public int get" + start + end + "() {\n";
								m += "		return " + row.enName.toLowerCase() + ";\n";
								m += "	}\n";
								m += "	public void set" + start + end + "(int m"
										+ row.enName.toLowerCase() + ") {\n";
								m += "		" + row.enName.toLowerCase() + " = m"
										+ row.enName.toLowerCase() + ";\n";
								m += "	}\n";
							}else
							{
							m += "	public String get" + start + end + "() {\n";
							m += "		return " + row.enName.toLowerCase() + ";\n";
							m += "	}\n";
							m += "	public void set" + start + end + "(String m"
									+ row.enName.toLowerCase() + ") {\n";
							m += "		" + row.enName.toLowerCase() + " = m"
									+ row.enName.toLowerCase() + ";\n";
							m += "	}\n";
							}
						} else {
							if(row.remarks.toLowerCase().contains("key"))
							{
							m += "	@Id\n";
							m += "	//@GeneratedValue(strategy=GenerationType.AUTO)\n";
							}
							
							
							
							if(row.type.toLowerCase().contains("int"))
							{
								m += "	public int get" + start + end + "() {\n";
								m += "		return " + row.enName.toLowerCase() + ";\n";
								m += "	}\n";
								m += "	public void set" + start + end + "(int m"
										+ row.enName.toLowerCase() + ") {\n";
								m += "		" + row.enName.toLowerCase() + " = m"
										+ row.enName.toLowerCase() + ";\n";
								m += "	}\n";
							}else
							{
							m += "	public String get" + start + end + "() {\n";
							m += "		return " + row.enName.toLowerCase() + ";\n";
							m += "	}\n";
							m += "	public void set" + start + end + "(String m"
									+ row.enName.toLowerCase() + ") {\n";
							m += "		" + row.enName.toLowerCase() + " = m"
									+ row.enName.toLowerCase() + ";\n";
							m += "	}\n";
							}
						}
						i++;
					}
				}

			}
		

			
			m += "	//public String toString() {\n";
			m += "    //    return ToStringBuilder.reflectionToString(this);\n";
			m += "    //}\n";
			m += "}\n";

			
			
			
			
			
			
			String m2="";
			m2+="@SuppressWarnings(\"serial\")\n";
			m2+="public class "+interfaceBean.enName+"EntityIds implements java.io.Serializable{\n";
			for (Group group : groups) {
				String groupname = group.name;
				if (groupname.equals("CommonGroup")) {
					int i = 0;
					for (Row row : group.rows) {
					
							if(row.remarks.toLowerCase().contains("key"))
							{
							m2 += "	private String " + row.enName.toLowerCase() + ";//"+row.cnName+"\n";
							}
						i++;
					}
				}

			}
			
			
			
			for (Group group : groups) {
				String groupname = group.name;
				if (groupname.equals("CommonGroup")) {
					int i = 0;
					for (Row row : group.rows) {
						String start = row.enName.substring(0, 1)
								.toUpperCase();
						String end = row.enName.substring(1)
								.toLowerCase();
						 
							if(row.remarks.toLowerCase().contains("key"))
							{
							m2 += "	public String get" + start + end + "() {\n";
							m2 += "		return " + row.enName.toLowerCase() + ";\n";
							m2 += "	}\n";
							m2 += "	public void set" + start + end + "(String m"
									+ row.enName.toLowerCase() + ") {\n";
							m2 += "		" + row.enName.toLowerCase() + " = m"
									+ row.enName.toLowerCase() + ";\n";
							m2 += "	}\n";
							}
						i++;
					}
				}

			}
			m2+="}\n";
			
			
		
			FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/webManager",interfaceBean.enName+"Entity", "java", m);
			if(keycount>1)
			{
		
			FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/webManager", interfaceBean.enName+"EntityIds", "java", m2);
			}
			System.out.println(m);
	

		
	}
	
	
	

	public static boolean isinteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}





