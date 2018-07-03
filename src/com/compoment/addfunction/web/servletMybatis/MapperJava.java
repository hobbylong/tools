package com.compoment.addfunction.web.servletMybatis;

import java.util.List;

import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class MapperJava {
	
	public void mapperJava(List<TableBean> tables,String interfaceName,String interfaceCnName) {

		String show = "";
		String condition = "";
		String relate = "";
		String mainTableName = "";

		boolean haveRelate = false;

		String selectPara = "";
		
		for (TableBean table : tables) {

			if (table.isMainTable && tables.size() > 1) {
				mainTableName = interfaceName;
				selectPara+="Map para";
			} else if (tables.size() == 1) {
				mainTableName = table.tableEnName;
				selectPara+="Map para";
			}
		}

		
//		for (int i = 0; i < queryConditionColumns.size(); i++) {
//			TableColumnBean column = queryConditionColumns.get(i);
//			if (i == queryConditionColumns.size() - 1) {
//				selectPara += "@Param(\""+column.columnEnName+"\")"+typeCheck(column.type) + " "
//						+ column.columnEnName + "";
//			} else {
//				selectPara += "@Param(\""+column.columnEnName+"\")"+typeCheck(column.type) + " "
//						+ column.columnEnName + ",";
//			}
//		}
		
		

		String m = "";
		m += "package com.company.dao.impl;\n";
		m += "import java.util.List;\n";
		m += "import java.util.Map;\n";
		m += "//"+interfaceCnName+"\n";
		m += "public interface " + mainTableName + "Mapper" + " {\n";

		for (TableBean table : tables) {

			if (table.isMainTable && tables.size() > 1) {

				m += "List<" + interfaceName + "Bean> " + interfaceName
						+ "Select(" + selectPara + ");\n";

			} else if (tables.size() == 1) {

				m += "List<" + table.tableEnName + "Bean> " + table.tableEnName
						+ "Select(" + selectPara + ");\n";
				m += "int " + table.tableEnName
						+ "SelectCount(" + selectPara + ");\n";
				m += "int " + table.tableEnName
						+ "SelectMax(" + selectPara + ");\n";
				m += "void " + table.tableEnName + "Insert("
						+ table.tableEnName + "Bean bean);\n";
				m += "void " + table.tableEnName + "Update("
						+ table.tableEnName + "Bean bean);\n";
				m += "void " + table.tableEnName + "Delete("
						+ table.tableEnName + "Bean bean);\n";

			}
		}

		m += "}\n";

		
		if(tables!=null && tables.size()>1)
		{
			FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
					interfaceName + "Mapper", "java", m);
		}else
		{
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				mainTableName + "Mapper", "java", m);
		}

	}

}
