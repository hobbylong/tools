package com.compoment.addfunction.web.springmvcSpringMybatis;

import java.util.ArrayList;
import java.util.List;

import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class ServiceInterface {

	public void serviceInterface(List<TableBean> tables,String interfaceName,String interfaceCnName) {
		String m = "";

		String servicename = "";
		String resultType = "";
		String queryCondition = "";
		String queryCondition2 = "";
		String queryCondition3 = "";
		String mainTableName = "";
		String mappername = "";

		for (TableBean table : tables) {
			servicename += table.tableEnName + "_";
			if (table.isMainTable && tables.size() > 1) {
				resultType = interfaceName + "Bean";
				mainTableName = interfaceName;
				mappername = interfaceName;
				queryCondition+="Map para";
				queryCondition3+="para";
			} else if (tables.size() == 1) {
				resultType = table.tableEnName + "Bean";
				mainTableName = table.tableEnName;
				mappername = table.tableEnName;
				queryCondition+="Map para";
				queryCondition3+="para";
			}
		}

		if (servicename.lastIndexOf("_") != -1) {
			servicename = servicename
					.substring(0, servicename.lastIndexOf("_"));
		}

		List<TableColumnBean> queryConditionColumns=getQueryConditionColumns(tables);
		for (TableColumnBean column : queryConditionColumns) {
			//queryCondition += typeCheck(column.type) + " "+ column.columnEnName + ",";
		//	queryCondition3 += " " + column.columnEnName + ",";
			queryCondition2 += "m.put(\"" + column.columnEnName + "\", "
					+ column.columnEnName + ");\n";
		}

//		if (queryCondition.lastIndexOf(",") != -1) {
//			queryCondition = queryCondition.substring(0,
//					queryCondition.lastIndexOf(","));
//		}

//		if (queryCondition3.lastIndexOf(",") != -1) {
//			queryCondition3 = queryCondition3.substring(0,
//					queryCondition3.lastIndexOf(","));
//		}

		m += "package com.company.service.impl;\n";
		m += "import java.util.List;\n";
		m += "import java.util.Map;\n";
		
		m += "//"+interfaceCnName+"\n";
		m += "public interface " + interfaceName + "Service {\n";

		for (TableBean table : tables) {

			if (table.isMainTable && tables.size() > 1) {

				m += "	List<" + resultType + "> get(" + queryCondition
						+ ") throws Exception;\n";

			} else if (tables.size() == 1) {

				m += "	List<" + resultType + "> get(" + queryCondition
						+ ") throws Exception;\n";
				m += "	int getCount(" + queryCondition
						+ ") throws Exception;\n";

				m += "void " + "insert(" + table.tableEnName + "Bean bean);\n";
				m += "void " + "update(" + table.tableEnName + "Bean bean);\n";
				m += "void " + "delete(" + table.tableEnName + "Bean bean);\n";

			}
		}

		m += "}\n";

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				interfaceName + "Service", "java", m);

		// 接口实现
		{

			m = "";
			m += "package com.company.service.impl;\n";
			m += "import java.util.HashMap;\n";
			m += "import java.util.Map;\n";
			m += "import java.util.List;\n";
			m += "import javax.annotation.Resource;\n";

			m += "import org.slf4j.Logger;\n";
			m += "import org.slf4j.LoggerFactory;\n";
			m += "import org.springframework.stereotype.Service;\n";

			m += "import com.framework.dao.BaseDao;\n";
			m += "import com.framework.dao.common.DaoTools;\n";
			m += "import com.framework.exception.CommonException;\n";
			m += "//"+interfaceCnName+"\n";
			m += "@Service(\"" + interfaceName + "Service\")\n";
			m += "public class " + interfaceName + "ServiceImpl implements "
					+ interfaceName + "Service {\n";
			m += "	private final static Logger logger = LoggerFactory.getLogger("
					+ interfaceName + "ServiceImpl.class);\n";
			m += "	\n";

			m += "	@Resource\n";
			m += "	private " + mappername + "Mapper mapper;\n";
			m += "	\n";

			for (TableBean table : tables) {

				if (table.isMainTable && tables.size() > 1) {

					m += "	@Override\n";
					m += "	public List<" + resultType + "> get("
							+ queryCondition + ") throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
					m += "	/*	Map<String,Object> m = new HashMap();\n";
					
					m += queryCondition2;
					m+="*/\n";

					m += "return mapper." + mainTableName + "Select("
							+ queryCondition3 + ");\n";
					m += "	}\n";
					
					
					
					m += "	@Override\n";
					m += "	public int getCount("
							+ queryCondition + ") throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
					m += "	/*	Map<String,Object> m = new HashMap();\n";
					
					m += queryCondition2;
					m+="*/\n";

					m += "return mapper." + mainTableName + "SelectCount("
							+ queryCondition3 + ");\n";
					m += "	}\n";

				} else if (tables.size() == 1) {

					m += "	@Override\n";
					m += "	public List<" + resultType + "> get("
							+ queryCondition + ") throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
					m += "	/*	Map<String,Object> m = new HashMap();\n";
					m += queryCondition2;
					m+="*/\n";
					m += "return mapper." + mainTableName + "Select("
							+ queryCondition3 + ");\n";
					m += "	}\n\n";
					
					
					
					m += "	@Override\n";
					m += "	public int getCount("
							+ queryCondition + ") throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
					m += "	/*	Map<String,Object> m = new HashMap();\n";
					m += queryCondition2;
					m+="*/\n";
					m += "return mapper." + mainTableName + "SelectCount("
							+ queryCondition3 + ");\n";
					m += "	}\n\n";

					
					
					m += "	@Override\n";
					m += "public void " + " insert(" + table.tableEnName
							+ "Bean bean){\n";

					m += " mapper." + mainTableName + "Insert(bean);\n";
					m += "	}\n";

					m += "	@Override\n";
					m += "public void update(" + table.tableEnName
							+ "Bean bean){\n";
					m += " mapper." + mainTableName + "Update(bean);\n";
					m += "	}\n\n";

					m += "	@Override\n";
					m += "public void   delete(" + table.tableEnName
							+ "Bean bean){\n";

					m += " mapper." + mainTableName + "Delete(bean);\n";
					m += "	}\n";

				}
			}

			m += "}\n";

			FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
					interfaceName + "ServiceImpl", "java", m);
		}

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
