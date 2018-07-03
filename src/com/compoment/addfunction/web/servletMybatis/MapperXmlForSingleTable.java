package com.compoment.addfunction.web.servletMybatis;

import java.util.ArrayList;
import java.util.List;

import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class MapperXmlForSingleTable {


	public void mapperXmlForSingleTable(List<TableBean> tables) {

		if (tables != null && tables.size() > 1) {
			return;
		}

		String show = "";
		String condition = "";
		String relate = "";
		String mainTableName = "";

		boolean haveRelate = false;

		boolean conditionFirstColumn = true;

		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("left".equals(column.leftClickSelected)) {
					show += " "
							+ StringUtil
									.tableName(column.belongWhichTable.tableEnName)
							+ "." + column.columnEnName + ",";
					

				}

				if ("right".equals(column.rightClickSelected)) {

					if (conditionFirstColumn) {
						condition += "		<if test=\"" + column.columnEnName
								+ "!= null \">\n";
						condition += " "
								+ StringUtil
										.tableName(column.belongWhichTable.tableEnName)
								+ "." + column.columnEnName + "= #{"
								+ column.columnEnName + "}\n";
						condition += "		</if>\n";
						conditionFirstColumn = false;
					} else {
						condition += "		<if test=\"" + column.columnEnName
								+ "!= null \">\n";
						condition += " and "
								+ StringUtil
										.tableName(column.belongWhichTable.tableEnName)
								+ "." + column.columnEnName + "= #{"
								+ column.columnEnName + "}\n";
						condition += "		</if>\n";

					}

				
				} else {

				}
				
		
			
			}
		}
		
		
		
		//单表没选条件 
		if("".equals(condition))
		{
		
			for (TableBean table : tables) {

				for (TableColumnBean column : table.columns) {

						if (conditionFirstColumn) {
							condition += "		<if test=\"" + column.columnEnName
									+ "!= null \">\n";
							condition += " "
									+ StringUtil
											.tableName(column.belongWhichTable.tableEnName)
									+ "." + column.columnEnName + "= #{"
									+ column.columnEnName + "}\n";
							condition += "		</if>\n";
							conditionFirstColumn = false;
						} else {
							condition += "		<if test=\"" + column.columnEnName
									+ "!= null \">\n";
							condition += " and "
									+ StringUtil
											.tableName(column.belongWhichTable.tableEnName)
									+ "." + column.columnEnName + "= #{"
									+ column.columnEnName + "}\n";
							condition += "		</if>\n";

						}
				}
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
		String sqlMax="";
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
			sqlMax = "select max(${columnName}) from " + relate + " \n<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \">\n " + condition+"\n</trim>\n";
		}

		String m = "";
		m += "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
		m += "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n";

		List<TableColumnBean> resultColumns=getResultColumns(tables);
		for (TableBean table : tables) {

			if (table.isMainTable || tables.size() == 1) {
				mainTableName = table.tableEnName;
				m += "<mapper namespace=\"com.company.dao.impl."
						+ table.tableEnName + "Mapper\">\n";
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
				m += "	<resultMap id=\"" + table.tableEnName
						+ "ResultMap\" type=\"com.company.pojo."
						+ table.tableEnName + "Bean\">\n";
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

			if (!table.isMainTable && tables.size() > 1) {
				m += "	 <collection property=\"" + table.tableEnName
						+ "\" ofType=\"com.company.bean." + table.tableEnName
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

			if (table.isMainTable || tables.size() == 1) {
				
				m += "	<select id=\"" + table.tableEnName
						+ "Select\" resultMap=\"" + table.tableEnName
						+ "ResultMap\" >\n";
				m+=" <include refid=\"Oracle_Pagination_Head\" />\n";

			}
		}
		m += sql;
		m+=" <include refid=\"Oracle_Pagination_Tail\" />\n";
		m += "	</select>\n";
		
		//count
		for (TableBean table : tables) {

			if (table.isMainTable|| tables.size() == 1) {
				m += "	<select id=\"" + table.tableEnName
						+ "SelectCount\" resultType=\"java.lang.Integer\" >\n";

			}
		}
		m += sqlcount+"";
		m += "	</select>\n";
		
		
		//max
		for (TableBean table : tables) {

			if (table.isMainTable|| tables.size() == 1) {
				m += "	<select id=\"" + table.tableEnName
						+ "SelectMax\" resultType=\"java.lang.Integer\" >\n";

			}
		}
		m += sqlMax+"";
		m += "	</select>\n";
		
		

		// 插入
		for (TableBean table : tables) {
			m += "\n\n	<insert id=\"" + table.tableEnName
					+ "Insert\" parameterType=\"com.company.pojo." + table.tableEnName
					+ "Bean\">\n";
			m += "		insert into " + StringUtil.tableName(table.tableEnName)
					+ " \n";
			m += "		<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n";
			for (TableColumnBean column : table.columns) {

				m += "			<if test=\"" + column.columnEnName + " != null\">\n";
				m += "				" + column.columnEnName + ",\n";
				m += "			</if>\n";

			}
		}

		m += "		</trim>\n";

		m += "		<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n";

		for (TableBean table : tables) {
			for (TableColumnBean column : table.columns) {

				if (!column.type.toLowerCase().contains("int")) {
					m += "			<if test=\"" + column.columnEnName
							+ " != null\">\n";
					m += "				#{" + column.columnEnName
							+ ",jdbcType=VARCHAR},\n";
					m += "			</if>\n";
				} else {
					m += "			<if test=\"" + column.columnEnName
							+ " != null\">\n";
					m += "				#{" + column.columnEnName
							+ ",jdbcType=INTEGER},\n";
					m += "			</if>\n";
				}

			}
		}
		m += "		</trim>\n";
		m += "	</insert>\n\n";

		// 删除
		for (TableBean table : tables) {
			m += "	<delete id=\"" + table.tableEnName
					+ "Delete\" parameterType=\"com.company.pojo." + table.tableEnName
					+ "Bean\">\n";
			m += "		delete from " + StringUtil.tableName(table.tableEnName)
					+ "";
			m += "		where \n";
			m+="<trim  suffix=\"\" suffixOverrides=\"and\">\n";
			for (TableColumnBean column : table.columns) {

				if (column.key != null
						&& column.key.toLowerCase().contains("key")) {
					if (!column.type.toLowerCase().contains("int")) {
						m += "			<if test=\"" + column.columnEnName
								+ " != null\">\n";
						m += "				" + column.columnEnName + "=#{"
								+ column.columnEnName + ",jdbcType=VARCHAR} and \n";
						m += "			</if>\n";
					} else {
						m += "			<if test=\"" + column.columnEnName
								+ " != null\">\n";
						m += "				" + column.columnEnName + "=#{"
								+ column.columnEnName + ",jdbcType=INTEGER} and \n";
						m += "			</if>\n";
					}
				}
			}
		}
m+="</trim>\n";
		m += "	</delete>\n\n";

		// 更新

		for (TableBean table : tables) {
			m += "	<update id=\"" + table.tableEnName
					+ "Update\" parameterType=\"com.company.pojo." + table.tableEnName
					+ "Bean\">\n";
			m += "		update " + StringUtil.tableName(table.tableEnName) + "\n";
			m += "		<set>\n";

			for (TableColumnBean column : table.columns) {

				if (column.key == null
						|| !column.key.toLowerCase().contains("key")) {
					if (!column.type.toLowerCase().contains("int")) {
						m += "			<if test=\"" + column.columnEnName
								+ " != null\">\n";
						m += "				" + column.columnEnName + "=#{"
								+ column.columnEnName + ",jdbcType=VARCHAR},\n";
						m += "			</if>\n";
					} else {
						m += "			<if test=\"" + column.columnEnName
								+ " != null\">\n";
						m += "				" + column.columnEnName + "=#{"
								+ column.columnEnName + ",jdbcType=INTEGER},\n";
						m += "			</if>\n";
					}
				}
			}

			m += "		</set>\n";

			m += "		where \n";
			m+="<trim  suffix=\"\" suffixOverrides=\"and\">\n";
			for (TableColumnBean column : table.columns) {

				if (column.key != null
						&& column.key.toLowerCase().contains("key")) {
					if (!column.type.toLowerCase().contains("int")) {
						m += "			<if test=\"" + column.columnEnName
								+ " != null\">\n";
						m += "				" + column.columnEnName + "=#{"
								+ column.columnEnName + ",jdbcType=VARCHAR} and \n";
						m += "			</if>\n";
					} else {
						m += "			<if test=\"" + column.columnEnName
								+ " != null\">\n";
						m += "				" + column.columnEnName + "=#{"
								+ column.columnEnName + ",jdbcType=INTEGER} and \n";
						m += "			</if>\n";
					}
				}
			}
		}
m+="</trim>\n";
		m += "	</update>\n";

		m += "</mapper>\n";

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				mainTableName + "Mapper", "xml", m);

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
