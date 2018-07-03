package com.compoment.addfunction.web.servletMybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class MapperXml {

	public void mapperXml(List<TableBean> tables,String interfaceName) {
//批量生成时tables里只有一个tablebean
		
		if (tables != null && tables.size() <= 1) {
			return;
		}

		String show = "";
		String condition = "";
		String relate = "";
		String mainTableName = "";

		boolean haveRelate = false;
		
		List<Map> mainTableRelateChirldTableList=new ArrayList();

		boolean conditionFirstColumn = true;

		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("left".equals(column.leftClickSelected)) {
					String tablename=StringUtil
							.tableName(column.belongWhichTable.tableEnName);
					String shortTableName=tablename.substring(tablename.lastIndexOf("_")+1);
					
					show += " "
							+ shortTableName
							+ "." + column.columnEnName + ",";
					

				}

				if ("right".equals(column.rightClickSelected)) {
					String tablename=StringUtil
							.tableName(column.belongWhichTable.tableEnName);
					String shortTableName=tablename.substring(tablename.lastIndexOf("_")+1);

					if (conditionFirstColumn) {
						condition += "		<if test=\"" + column.columnEnName
								+ "!= null \">\n";
						condition += " "
								+ shortTableName
								+ "." + column.columnEnName + "= #{"
								+ column.columnEnName + "}\n";
						condition += "		</if>\n";
						conditionFirstColumn = false;
					} else {
						condition += "		<if test=\"" + column.columnEnName
								+ "!= null \">\n";
						condition += " and "
								+ shortTableName
								+ "." + column.columnEnName + "= #{"
								+ column.columnEnName + "}\n";
						condition += "		</if>\n";

					}

					
				}

				if (column.relateColumnBeans != null
						&& column.relateColumnBeans.size() > 0) {
					
					
					haveRelate = true;
					// 关联的
					for (TableColumnBean relateColumn : column.relateColumnBeans) {

					if(column.belongWhichTable.isMainTable && !relateColumn.belongWhichTable.isMainTable)
					{
						Map mainTableRelateChirldTable=new HashMap();
						mainTableRelateChirldTable.put(column, relateColumn);
						mainTableRelateChirldTableList.add(mainTableRelateChirldTable);
					}else if(!column.belongWhichTable.isMainTable && relateColumn.belongWhichTable.isMainTable)
					{
						Map mainTableRelateChirldTable=new HashMap();
						mainTableRelateChirldTable.put(relateColumn, column);
						mainTableRelateChirldTableList.add(mainTableRelateChirldTable);
					}else if(!column.belongWhichTable.isMainTable && !relateColumn.belongWhichTable.isMainTable)
					{
						
					}
						
						

						
					}

				}
			}
		}

		
		
		
		
		
		//多表

			int i=0;
			for(Map mainTableRelateChirldTable:mainTableRelateChirldTableList)
			{
			for(Object key : mainTableRelateChirldTable.keySet())
			{
				
				TableColumnBean mainTableColumn=(TableColumnBean)key;
				TableColumnBean chirldTableColumn=(TableColumnBean) mainTableRelateChirldTable.get(key);
				
				String mainName=StringUtil
						.tableName(mainTableColumn.belongWhichTable.tableEnName);
				String shortMainTableName=mainName.substring(mainName.lastIndexOf("_")+1);
				
				if(i==0)
				{
					
					relate += mainName+" "+shortMainTableName;
				}
				
				
				String chirldName=StringUtil
						.tableName(chirldTableColumn.belongWhichTable.tableEnName);
				String shortChirldTableName=chirldName.substring(chirldName.lastIndexOf("_")+1);
					relate += " inner join "
							+ chirldName+" "+shortChirldTableName
							+ " on "
							+ shortChirldTableName
							+ "."
							+ chirldTableColumn.columnEnName
							+ "="
							+shortMainTableName
							+ "." + mainTableColumn.columnEnName;
				
					i++;
			}
			}

		
		
		
		
		for (TableBean table : tables) {
			for (TableColumnBean column : table.columns) {
				if (!haveRelate) {// 单个表
					relate = StringUtil
							.tableName(column.belongWhichTable.tableEnName);
				}

			}
		}

		// 查询
		String sql = "";
		String sqlcount = "";
		String sqlMax = "";
		if ("".equals(show) && "".equals(condition)) {
			sql = "select * from " + relate;
			sqlcount = "select count(*) from " + relate;
			sqlMax = "select max(${columnName}) from " + relate;
		}

		else if ("".equals(show) && !"".equals(condition)) {
			sql = "select * from " + relate + " \n<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">\n " + condition+"\n</trim>\n";
			sqlcount = "select count(*) from " + relate + " \n<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">\n " + condition+"\n</trim>\n";
			sqlMax = "select max(${columnName}) from " + relate + " \n<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">\n " + condition+"\n</trim>\n";
		} else if (!"".equals(show) && "".equals(condition)) {
			sql = "select " + show.substring(0, show.lastIndexOf(","))
					+ " from " + relate;
			sqlcount = "select count(*) from " + relate;
			sqlMax = "select max(${columnName}) from " + relate;
		} else {
			sql = "select " + show.substring(0, show.lastIndexOf(","))
					+ " from " + relate + " \n<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">\n " + condition+"\n</trim>\n";
			sqlcount = "select count(*) from " + relate + " \n<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">\n " + condition+"\n</trim>\n";
			sqlMax = "select  max(${columnName}) from " + relate + " \n<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">\n " + condition+"\n</trim>\n";

		}

		String m = "";
		m += "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
		m += "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n";
        List<TableColumnBean> resultColumns=getResultColumns(tables);
		for (TableBean table : tables) {

			if (table.isMainTable) {
				mainTableName = table.tableEnName;
				m += "<mapper namespace=\"com.company.dao.impl."
						+ interfaceName + "Mapper\">\n";
				
				m+="	<!-- oracle 分页 -->\n";
				m+="	<sql id=\"Oracle_Pagination_Head\">\n";
				m+="		<if test=\"currIndex!=null and pageSize!=null\">\n";
				m+="            <![CDATA[select y.* from(select z.*,rownum as rn from (]]>\n";
				m+="		</if>\n";
				m+="	</sql>\n";

				m+="	<sql id=\"Oracle_Pagination_Tail\">\n";
				m+="		<if test=\"currIndex != null and pageSize != null\">\n";
				m+="            <![CDATA[ ) z where rownum <= #{pageSize}+#{currIndex} ) y where y.rn > #{currIndex} ]]>\n";
				m+="		</if>\n";
				m+="	</sql>\n";
				m+="	<!-- end oracle 分页 -->\n";

				m+="	<!-- mysql 分页 -->\n";
				m+="	<sql id=\"MySql_Pagination_Head\">\n";
				m+="	</sql>\n";
				m+="	<sql id=\"MySql_Pagination_Tail\">\n";
				m+="		<if test=\"pageSize != 0\">\n";
				m+="            <![CDATA[ limit #{currIndex},#{pageSize} ]]>\n";
				m+="		</if>\n";
				m+="	</sql>\n";
				m+="	<!-- end mysql 分页 -->\n";
				
				m += "	<resultMap id=\"" + interfaceName
						+ "ResultMap\" type=\"com.company.pojo."
						+ interfaceName + "Bean\">\n";
				for (TableColumnBean column : resultColumns) {
					if (column.belongWhichTable.tableEnName
							.equals(table.tableEnName)) {
						if (column.columnEnName.contains("id")) {
							m += "<id column=\"" + column.columnEnName
									+ "\" property=\"" + column.columnEnName
									+ "\" />\n";
						} else {
							m += "<result column=\"" + column.columnEnName
									+ "\" property=\"" + column.columnEnName
									+ "\" />\n";
						}
					}
				}
			}
		}

		for (TableBean table : tables) {

			if (!table.isMainTable) {
				m += "	 <collection property=\""
						+ StringUtil.firstCharToLower(table.tableEnName)
						+ "s\" ofType=\"com.company.pojo." + table.tableEnName
						+ "Bean\">\n";
				for (TableColumnBean column : resultColumns) {
					if (column.belongWhichTable.tableEnName
							.equals(table.tableEnName)) {
						if (column.columnEnName.contains("id")) {
							m += "<id column=\"" + column.columnEnName
									+ "\" property=\"" + column.columnEnName
									+ "\" />\n";
						} else {
							m += "<result column=\"" + column.columnEnName
									+ "\" property=\"" + column.columnEnName
									+ "\" />\n";
						}
					}
				}

				m += "</collection>\n";
			}
		}

		m += "	</resultMap>\n";

		for (TableBean table : tables) {

			if (table.isMainTable) {
			
				m += "	<select id=\"" + interfaceName
						+ "Select\" resultMap=\"" + interfaceName
						+ "ResultMap\" >\n";
				m+=" <include refid=\"Oracle_Pagination_Head\" />\n";

			}
		}
		m += sql;
		m+=" <include refid=\"Oracle_Pagination_Tail\" />\n";
		m += "	</select>\n";
		
		
		//count
		for (TableBean table : tables) {

			if (table.isMainTable) {
				m += "	<select id=\"" + interfaceName
						+ "SelectCount\" resultType=\"java.lang.Integer\" >\n";

			}
		}
		m += sqlcount+"";
		m += "	</select>\n";
		
		
		//Max
		for (TableBean table : tables) {

					if (table.isMainTable) {
						m += "	<select id=\"" + interfaceName
								+ "SelectMax\" resultType=\"java.lang.Integer\" >\n";
					}
				}
				m += sqlMax+"";
				m += "	</select>\n";
		

		m += "</mapper>\n";

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				interfaceName + "Mapper", "xml", m);

	}
	
	
	public List<TableColumnBean> getQueryConditionColumns(List<TableBean> tables)
	{
	
		List<TableColumnBean> queryConditionColumns=new ArrayList();
	
		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("right".equals(column.rightClickSelected)) {
					String tablename=StringUtil
							.tableName(column.belongWhichTable.tableEnName);
					String shortTableName=tablename.substring(tablename.lastIndexOf("_")+1);

					queryConditionColumns.add(column);
				}

			}
		}
		return queryConditionColumns;
	}
	
	public List<TableColumnBean> getResultColumns(List<TableBean> tables)
	{
	
		
		List<TableColumnBean> resultColumns=new ArrayList();
		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("left".equals(column.leftClickSelected)) {
					String tablename=StringUtil
							.tableName(column.belongWhichTable.tableEnName);
					String shortTableName=tablename.substring(tablename.lastIndexOf("_")+1);
					
					resultColumns.add(column);

				}

				
			}
		}
		return resultColumns;
	}
	
}
