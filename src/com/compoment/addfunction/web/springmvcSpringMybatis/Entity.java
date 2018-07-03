package com.compoment.addfunction.web.springmvcSpringMybatis;

import java.util.List;

import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class Entity {

	public void entity(List<TableBean> tables,String interfaceName,String interfaceCnName) {
		String m = "";
		String m1 = "";
		String noMainTable = "";
		String noMainTable1 = "";

		for (TableBean table : tables) {

			if(tables.size()>1)
			{
			if (table.isMainTable) {

			} else {
				noMainTable += "List<"
						+ StringUtil
								.firstCharToUpperAndJavaName(table.tableEnName)
						+ "Bean> "
						+ StringUtil.firstCharToLower(table.tableEnName)
						+ "s;/**" + table.tableCnName + "*/\n";
				noMainTable1 += "";

				noMainTable1 += "	public List get"
						+ StringUtil
								.firstCharToUpperAndJavaName(table.tableEnName)
						+ "s() {\n";
				noMainTable1 += "		return "
						+ StringUtil.firstCharToLower(table.tableEnName)
						+ "s;\n";
				noMainTable1 += "	}\n";

				noMainTable1 += "	public void set"
						+ StringUtil
								.firstCharToUpperAndJavaName(table.tableEnName)
						+ "s(List "
						+ StringUtil.firstCharToLower(table.tableEnName)
						+ "s) {\n";
				noMainTable1 += "		this."
						+ StringUtil.firstCharToLower(table.tableEnName)
						+ "s = "
						+ StringUtil.firstCharToLower(table.tableEnName)
						+ "s;\n";
				noMainTable1 += "	}\n";
			}
			}
		}

		for (TableBean table : tables) {
			m="";
			m += "package com.company.pojo;\n";
			m += "import java.util.List;\n";
			m += "import java.util.Map;\n";
			m += "//"+interfaceCnName+"\n";
			m1 = "";
			if (tables.size()>1 && table.isMainTable) {
				m += "public class "
						+ interfaceName
						+ "Bean {\n";
			}else
			{
			m += "public class "
					+ StringUtil.firstCharToUpperAndJavaName(table.tableEnName)
					+ "Bean {\n";
			}
			for (TableColumnBean column : table.columns) {

				if (column.type.toLowerCase().contains("int")) {
					m += "	public Integer " + column.columnEnName + ";/**"
							+ column.columnCnName + "*/\n";
					m1 += "	public Integer get"
							+ StringUtil
									.firstCharToUpperAndJavaName(column.columnEnName)
							+ "() {\n";
					m1 += "		return " + column.columnEnName + ";\n";
					m1 += "	}\n";

					m1 += "	public void set"
							+ StringUtil
									.firstCharToUpperAndJavaName(column.columnEnName)
							+ "(Integer " + column.columnEnName + ") {\n";
					m1 += "		this." + column.columnEnName + " = "
							+ column.columnEnName + ";\n";
					m1 += "	}\n";
				} else {
					m += "	public String " + column.columnEnName + ";/**"
							+ column.columnCnName + "*/\n";
					m1 += "	public String get"
							+ StringUtil
									.firstCharToUpperAndJavaName(column.columnEnName)
							+ "() {\n";
					m1 += "		return " + column.columnEnName + ";\n";
					m1 += "	}\n";

					m1 += "	public void set"
							+ StringUtil
									.firstCharToUpperAndJavaName(column.columnEnName)
							+ "(String " + column.columnEnName + ") {\n";
					m1 += "		this." + column.columnEnName + " = "
							+ column.columnEnName + ";\n";
					m1 += "	}\n";
				}
			}

			if (tables.size()>1 && table.isMainTable) {
				FileUtil.makeFile(
						KeyValue.readCache("projectPath"),
						"src/web",
						interfaceName
								+ "Bean", "java", m + noMainTable + m1
								+ noMainTable1 + "}\n");
			} else {
				FileUtil.makeFile(
						KeyValue.readCache("projectPath"),
						"src/web",
						StringUtil
								.firstCharToUpperAndJavaName(table.tableEnName)
								+ "Bean", "java", m + m1 + "}\n");
			}

		}

	}
}
