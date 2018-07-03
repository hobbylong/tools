package com.compoment.db.creater;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.compoment.db.helper.XmlDBColumnBean;
import com.compoment.db.helper.XmlDBParser;
import com.compoment.db.helper.XmlDBTableBean;

public class CreaterDBContentResolverBean {

	List<XmlDBTableBean> tables = null;
	public static void main(String[] args) {
		new CreaterDBContentResolverBean();
	}
	public CreaterDBContentResolverBean() {

		String classDir = this.getClass().getResource("/").getPath();
		try {

			XmlDBParser xmlDbParser = new XmlDBParser();
			xmlDbParser.parserXml(classDir + "com/compoment/db/db.uxf");
			tables = xmlDbParser.tables;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String className = "";
		for (XmlDBTableBean table : tables) {
			className += table.tableName + "_";
		}

		

		for (XmlDBTableBean table : tables) {
			String m = "\n";
			m += "		/** " + table.tableChineseName + "*/\n";
			m += "public class " + table.tableName + "Bean{\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m+="/** "+ column.columnChineseName+"*/\n";
				m += "String " + firstCharLowercase(column.columnName )+ ";\n";
				m += "public String " + firstCharLowercase(column.columnName )+ "_ChineseName=\""+column.columnChineseName+"\";\n";
			}

			for (XmlDBColumnBean column : table.columnsName) {
				m+="/**"+column.columnChineseName+"*/\n";
			    m+="public String get"+column.columnName+"(){return "+firstCharLowercase(column.columnName)+";};\n";
			}

			for (XmlDBColumnBean column : table.columnsName) {
				m+="/**"+column.columnChineseName+"*/\n";
				 m+="public void set"+column.columnName+"(String "+firstCharLowercase(column.columnName)+"){ this."+firstCharLowercase(column.columnName)+"="+firstCharLowercase(column.columnName)+";};\n";
			}
			m += "}\n\n\n";

			System.out.println(m);
			stringToFile("d:\\"+table.tableName + "Bean.java",m);
		}

		
	}

	public String firstCharLowercase(String s)
	{
		if(s.length()>0)
		{
		String firstchar=String.valueOf(s.charAt(0)).toLowerCase();
		s=s.substring(1);
		s=firstchar+s;
		return s;
		}else
		{
			return null;
		}

	}
	
	public void stringToFile(String fileName,String str)
	{
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			fw.write(str); 
			fw.flush();//加上这句
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
