package com.compoment.addfunction.web.servletMybatis;

import java.util.List;

import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class SqlScript {

	public void mysql(List<TableBean>tables)
	{
	
		
		for (TableBean table : tables) {
			String m="";
			m+="\n\n drop table "+StringUtil
					.tableName(table.tableEnName)+";\n";
			m+="CREATE TABLE "+StringUtil
					.tableName(table.tableEnName)+"(\n";
			
			String primarykeyString="";
			for (TableColumnBean column : table.columns) {
				if (column.type.toLowerCase().contains("int")) {
				  if(column.key.toLowerCase().contains("key"))
				  {
					  primarykeyString+=column.columnEnName+",";
				  
					  m+=column.columnEnName+"     INT   NOT NULL,\n";
				  }else
				  {
					  m+=column.columnEnName+"     INT,\n";
				  }
				} else {
					int start=column.columnEnName.indexOf("(");
					int end=column.columnEnName.indexOf(")");
					String size="100";
					if(start==-1||end==-1)
					{
						
					}else
					{
					 size=column.columnEnName.substring(start+1, end);
					}
					
					
					 if(column.key.toLowerCase().contains("key"))
					  {
						 primarykeyString+=column.columnEnName+",";
					  
						  m+=column.columnEnName+"     VARCHAR("+size+")   NOT NULL,\n";
					  }else
					  {
						  m+=column.columnEnName+"     VARCHAR("+size+"),\n";
					  }
				}
		}
	m+=");\n";
	
	
	FileUtil.makeFile(
			KeyValue.readCache("projectPath"),
			"src/web",
			StringUtil
			.tableName(table.tableEnName)+"_Mysql"
					+ "", "sql", m );
	
		}
		
	
	}
	
	
	public void oracle(List<TableBean>tables)
	{
		
		
		for (TableBean table : tables) {
			String m="";
			String zhushi="";
			m+="\n\n drop table "+StringUtil
					.tableName(table.tableEnName)+";\n";
			m+="CREATE TABLE "+StringUtil
					.tableName(table.tableEnName)+"(\n";
			
			String primarykeyString="";
			for (TableColumnBean column : table.columns) {
				if (column.type.toLowerCase().contains("int")) {
				  if(column.key.toLowerCase().contains("key")||column.key.toLowerCase().equals("y"))
				  {
					  primarykeyString+=column.columnEnName+",";
				  
					  m+=column.columnEnName+"     NUMBER   NOT NULL,\n";
					  zhushi+="comment  on  column  "+StringUtil
								.tableName(table.tableEnName)+"."+column.columnEnName+"   is  '"+column.columnCnName+"';\n";
				  }else
				  {
					  m+=column.columnEnName+"     NUMBER,\n";
					  zhushi+="comment  on  column  "+StringUtil
								.tableName(table.tableEnName)+"."+column.columnEnName+"   is  '"+column.columnCnName+"';\n";
				  }
				} else {
					int start=column.type.indexOf("(");
				
					int end=column.type.indexOf(")");
					
					String size="100";
					if(start==-1||end==-1)
					{
						
					}else
					{
					 size=column.type.substring(start+1, end);
					}
					
					
					 if(column.key.toLowerCase().contains("key")||column.key.toLowerCase().equals("y"))
					  {
						 primarykeyString+=column.columnEnName+",";
					  
						  m+=column.columnEnName+"     VARCHAR2("+size+" BYTE)   NOT NULL,\n";
						  zhushi+="comment  on  column  "+StringUtil
									.tableName(table.tableEnName)+"."+column.columnEnName+"   is  '"+column.columnCnName+"';\n";
					  }else
					  {
						  m+=column.columnEnName+"     VARCHAR2("+size+" BYTE),\n";
						  zhushi+="comment  on  column  "+StringUtil
									.tableName(table.tableEnName)+"."+column.columnEnName+"   is  '"+column.columnCnName+"';\n";
					  }
				}
		}
	m+=")TABLESPACE TBS_QS_AGENT;\n";
	primarykeyString=primarykeyString.substring(0, primarykeyString.lastIndexOf(","));
	m+="alter table "+StringUtil
			.tableName(table.tableEnName)+" add primary key ("+primarykeyString+") using index TABLESPACE IDX_TBS_QS_AGENT;\n";
	m+=zhushi;
	FileUtil.makeFile(
			KeyValue.readCache("projectPath"),
			"src/web",
			StringUtil
			.tableName(table.tableEnName)+ "_Oracle", "sql", m );
		}
		
		
	}
}
