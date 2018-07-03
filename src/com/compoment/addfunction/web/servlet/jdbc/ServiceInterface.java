package com.compoment.addfunction.web.servlet.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class ServiceInterface {
	
	
	String sql = "";
	String sqlcount = "";
	String sqlMax = "";
	
	
	public void serviceInterface(List<TableBean> tables,String interfaceName,String interfaceCnName) {
		
		sqlbody(tables,interfaceName);
		
		String m = "";

		String servicename = "";
		String resultType = "";
		String queryCondition = "";
		//String queryCondition2 = "";
		String queryCondition3 = "";
		String mainTableName = "";
		String mappername = "";

		for (TableBean table : tables) {
			servicename += table.tableEnName + "_";
			if (table.isMainTable && tables.size() > 1) {
				resultType = interfaceName + "Bean";
				mainTableName = interfaceName;
				mappername = interfaceName;
				queryCondition="Map para";
				queryCondition3="para";
			} else if (tables.size() == 1) {
				resultType = table.tableEnName + "Bean";
				mainTableName = table.tableEnName;
				mappername = table.tableEnName;
				queryCondition="Map para";
				queryCondition3="para";
			}
		}

		if (servicename.lastIndexOf("_") != -1) {
			servicename = servicename
					.substring(0, servicename.lastIndexOf("_"));
		}

//		List<TableColumnBean> queryConditionColumns=getQueryConditionColumns(tables);
//		for (TableColumnBean column : queryConditionColumns) {
//			queryCondition2 += "m.put(\"" + column.columnEnName + "\", "
//					+ column.columnEnName + ");\n";
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
				m += "	int getMax(" + queryCondition
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
		
		
			m += "//"+interfaceCnName+"\n";
		
			m += "public class " + interfaceName + "ServiceImpl implements "
					+ interfaceName + "Service {\n";
			m += "	private final static Logger logger = LoggerFactory.getLogger("
					+ interfaceName + "ServiceImpl.class);\n";
			m += "	\n";

		
			

			for (TableBean table : tables) {

				if (table.isMainTable && tables.size() > 1) {

					m += "	@Override\n";
					m += "	public List<" + resultType + "> get("
							+ queryCondition + ",boolean isCount) throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
				
					m+="		ArrayList<"+resultType+"> beans = new ArrayList();\n";
					
					m+="		DataOperation don = new DataOperation();\n";

					m+="		String where = \"\";\n";
					m+="		int pageSize = 10;\n";
					m+="		int currIndex = 0;\n";
					m+="		\n";
				
					for (TableBean table2 : tables) {

						for (TableColumnBean column : table2.columns) {

							if ("right".equals(column.rightClickSelected)) {
								String tablename=StringUtil
										.tableName(column.belongWhichTable.tableEnName);
								String shortTableName=tablename.substring(tablename.lastIndexOf("_")+1);

									m+="	String "+column.columnEnName+"=(String)para.get(\""+column.columnEnName+"\");\n";
							}
					}
					}
					
					m+="		Iterator it = para.entrySet().iterator();\n";
					m+="		while (it.hasNext()) {\n";
					m+="			Map.Entry entry = (Map.Entry) it.next();\n";
					m+="			Object key = entry.getKey();\n";
					m+="			Object value = entry.getValue();\n";
					m+="			log.info(\"key=\" + key + \" value=\" + value);\n";
					m+="			if (\"pageSize\".equals(key)) {\n";
					m+="				if (value != null) {\n";

					m+="					pageSize = Integer.valueOf(value + \"\");\n";
					m+="				}\n";

					m+="			} else if (\"currIndex\".equals(key)) {\n";
					m+="				if (value != null)\n";
					m+="					currIndex = Integer.valueOf(value + \"\");\n";
					m+="			}\n";
					m+="			else {\n";
					m+="				where += key + \" like '%\" + value + \"%' and \";\n";
					m+="			}\n";

					m+="		}\n";

					m+="		if (where.lastIndexOf(\"and\") != -1)\n";
					m+="			where = (String) where.subSequence(0, where.lastIndexOf(\"and\"));\n";
					m+="		\n";
					m+="		if(where.equals(\"\"))\n";
					m+="		{\n";
					m+="			\n";
					m+="		}else\n";
					m+="		{\n";
					m+="			where=\" where \"+where;\n";
					m+="		}\n";
					m+="		//oracle 分页\n";
					m+="		String pageHead = \"select y.* from(select z.*,rownum as rn from (\";\n";

					m+="		String pageEnd = \") z where rownum <= \" + (pageSize + currIndex) + \" ) y where y.rn > \" + currIndex;\n";

					m+="		//mysql 分页\n";
					m+="		//String pageHead = \"\";";
					m+="		//String pageEnd = \" limit #{currIndex},#{pageSize} \";";

					
					m+="		if(isCount==true){\n";
					m+="		String sql=\""+sqlcount+"\";\n";
					m+="		List result = don.get(sql);\n";
				
					m+="return result;\n";
				
				    m+="}\n";
					
					m+="		String sql=pageHead+\""+sql+"\"+pageEnd;\n";
					m+="		\n";
					m+="		\n";
					m+="		List result = don.get(sql);\n";
					m+="		\n";
					m+="		for (int i = 0; i < result.size(); i++) {\n";
					m+="			Map map = (Map) result.get(i);\n";
					m+="			"+resultType+" bean = new "+resultType+"();\n";
					m+="			Iterator itresult = map.entrySet().iterator();\n";
					m+="			while (itresult.hasNext()) {\n";
					m+="				Map.Entry entry = (Map.Entry) itresult.next();\n";
					m+="				String key = (String) entry.getKey();\n";
					m+="				Object value = entry.getValue();\n";
					
					
					for (TableBean table1 : tables) {

						for (TableColumnBean column : table1.columns) {

							if ("left".equals(column.leftClickSelected)) {
							
								m+="				if (key.toLowerCase().equals(\""+column.columnEnName+"\".toLowerCase())) {\n";
								m+="					bean.set"+column.columnEnName+"(value + \"\");\n";
								m+="				}\n";

							}
						}
					}
							
					m+="			}\n";
					m+="			beans.add(bean);\n";
					m+="		}\n";
					m+="		\n";
					m+="		\n";
					m+="		return beans;\n";
					
					m += "	}\n";
					
					
					
					m += "	@Override\n";
					m += "	public int getCount("
							+ queryCondition + ") throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
				
					m+="List<" + resultType + "> result=get("+queryCondition+",true);\n";
					m+=" if(result!=null && result.size()>0){\n";
					m+="return Integer.valueOf(result.get(0));\n";
					m+="}\n";
					m+="		return 0;\n";

					
					
					m += "	}\n";
					
					
					m += "	@Override\n";
					m += "	public int getMax("
							+ queryCondition + ") throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
				
					m+="DataOperation don = new DataOperation();\n";
					m+="int max = don.getMax(\""+table.tableEnName+"\", \"ID\");\n";
					m+="return max;\n";
					
					m += "	}\n";

				} else if (tables.size() == 1) {

					
					m += "	@Override\n";
					m += "	public List<" + resultType + "> get("
							+ queryCondition + ",boolean isCount) throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
				
					m+="		ArrayList<"+resultType+"> beans = new ArrayList();\n";
					
					m+="		DataOperation don = new DataOperation();\n";

					m+="		String where = \"\";\n";
					m+="		int pageSize = 10;\n";
					m+="		int currIndex = 0;\n";
					m+="		\n";
				
					for (TableBean table2 : tables) {

						for (TableColumnBean column : table2.columns) {

							if ("right".equals(column.rightClickSelected)) {
								String tablename=StringUtil
										.tableName(column.belongWhichTable.tableEnName);
								String shortTableName=tablename.substring(tablename.lastIndexOf("_")+1);

									m+="	String "+column.columnEnName+"=(String)para.get(\""+column.columnEnName+"\");\n";
							}
					}
					}
					
					m+="		Iterator it = para.entrySet().iterator();\n";
					m+="		while (it.hasNext()) {\n";
					m+="			Map.Entry entry = (Map.Entry) it.next();\n";
					m+="			Object key = entry.getKey();\n";
					m+="			Object value = entry.getValue();\n";
					m+="			log.info(\"key=\" + key + \" value=\" + value);\n";
					m+="			if (\"pageSize\".equals(key)) {\n";
					m+="				if (value != null) {\n";

					m+="					pageSize = Integer.valueOf(value + \"\");\n";
					m+="				}\n";

					m+="			} else if (\"currIndex\".equals(key)) {\n";
					m+="				if (value != null)\n";
					m+="					currIndex = Integer.valueOf(value + \"\");\n";
					m+="			}\n";
					m+="			else {\n";
					m+="				where += key + \" like '%\" + value + \"%' and \";\n";
					m+="			}\n";

					m+="		}\n";

					m+="		if (where.lastIndexOf(\"and\") != -1)\n";
					m+="			where = (String) where.subSequence(0, where.lastIndexOf(\"and\"));\n";
					m+="		\n";
					m+="		if(where.equals(\"\"))\n";
					m+="		{\n";
					m+="			\n";
					m+="		}else\n";
					m+="		{\n";
					m+="			where=\" where \"+where;\n";
					m+="		}\n";
					m+="		//oracle 分页\n";
					m+="		String pageHead = \"select y.* from(select z.*,rownum as rn from (\";\n";

					m+="		String pageEnd = \") z where rownum <= \" + (pageSize + currIndex) + \" ) y where y.rn > \" + currIndex;\n";

					m+="		//mysql 分页\n";
					m+="		//String pageHead = \"\";";
					m+="		//String pageEnd = \" limit #{currIndex},#{pageSize} \";";

					
					m+="		if(isCount==true){\n";
					m+="		String sql=\""+sqlcount+"\";\n";
					m+="		List result = don.get(sql);\n";
				
					m+="return result;\n";
				
				    m+="}\n";
					
					m+="		String sql=pageHead+\""+sql+"\"+pageEnd;\n";
					m+="		\n";
					m+="		\n";
					m+="		List result = don.get(sql);\n";
					m+="		\n";
					m+="		for (int i = 0; i < result.size(); i++) {\n";
					m+="			Map map = (Map) result.get(i);\n";
					m+="			"+resultType+" bean = new "+resultType+"();\n";
					m+="			Iterator itresult = map.entrySet().iterator();\n";
					m+="			while (itresult.hasNext()) {\n";
					m+="				Map.Entry entry = (Map.Entry) itresult.next();\n";
					m+="				String key = (String) entry.getKey();\n";
					m+="				Object value = entry.getValue();\n";
					
					
					for (TableBean table1 : tables) {

						for (TableColumnBean column : table1.columns) {

							if ("left".equals(column.leftClickSelected)) {
							
								m+="				if (key.toLowerCase().equals(\""+column.columnEnName+"\".toLowerCase())) {\n";
								m+="					bean.set"+column.columnEnName+"(value + \"\");\n";
								m+="				}\n";

							}
						}
					}
							
					m+="			}\n";
					m+="			beans.add(bean);\n";
					m+="		}\n";
					m+="		\n";
					m+="		\n";
					m+="		return beans;\n";
					
					m += "	}\n";
					
					
					
					m += "	@Override\n";
					m += "	public int getCount("
							+ queryCondition + ") throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
				
					m+="List result=get("+queryCondition+",true);\n";
					m+=" if(result!=null && result.size()>0){\n";
					m+="int count = ((Long) result.get(0)).intValue();\n";
					m+="return count;\n";
					m+="}\n";
					m+="		return 0;\n";

					
					
					m += "	}\n";
					
					
					m += "	@Override\n";
					m += "	public int getMax("
							+ queryCondition + ") throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
				
					m+="DataOperation don = new DataOperation();\n";
					m+="int max = don.getMax(\""+table.tableEnName+"\", \"ID\");\n";
					m+="return max;\n";
					
					m += "	}\n";
					
					
					
					

					m += "	@Override\n";
					m += "public void " + " insert(" + table.tableEnName
							+ "Bean bean){\n";
					m+="		DataOperation don = new DataOperation();\n";

					m+="		Map<String, Object> entity = new LinkedHashMap<String, Object>();\n";
					m+="		\n";
					
					for(int i=0;i<table.columns.size();i++)
					{
						TableColumnBean columnbean=table.columns.get(i);
						
						m+="		entity.put(\""+columnbean.columnEnName+"\", bean.get"+columnbean.columnEnName+"());\n";
					}
					

					m+="	\n";
					m+="		try {\n";
					m+="			don.add(\""+table.tableEnName+"\", entity);\n";
					m+="			\n";
					m+="		\n";
					m+="		} catch (Exception e) {\n";
					m+="			// TODO Auto-generated catch block\n";
					m+="			e.printStackTrace();\n";
					m+="		}\n";
					m+="		\n";
				

					m+="		return;\n";

					
					
					m += "	}\n";

					m += "	@Override\n";
					m += "public void update(" + table.tableEnName
							+ "Bean bean){\n";
			
					m+="DataOperation don = new DataOperation();\n";

					m+="		String column = \"\";\n";
					m+="		String where = \"\";\n";

					m+="		where += \"TAG_ID='\" + bean.getTag_id() + \"'\";\n";

					
					for(int i=0;i<table.columns.size();i++)
					{
						TableColumnBean columnbean=table.columns.get(i);
						
					
						m+="		column += \""+columnbean.columnEnName+"='\" + bean.get"+columnbean.columnEnName+"() + \"',\";\n";
					}
				
					

					m+="		try {\n";
					m+="			don.update(\""+table.tableEnName+"\", where, column);\n";
					m+="		} catch (Exception e) {\n";
					m+="			// TODO Auto-generated catch block\n";
					m+="			e.printStackTrace();\n";
					m+="		}\n";

					

					
					
					m += "	}\n\n";

					m += "	@Override\n";
					m += "public void   delete(" + table.tableEnName
							+ "Bean bean){\n";
			
					m+="DataOperation don = new DataOperation();\n";
					m+="try {\n";
					m+="	don.delete(\""+table.tableEnName+"\", \"TAG_ID='\" + bean.getTag_id() + \"'\");\n";
					m+="} catch (Exception e) {\n";
					m+="	e.printStackTrace();\n";
					m+="}\n";
					
					
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
	
	
	
	
	

	public void sqlbody(List<TableBean> tables,String interfaceName) {
		//批量生成时tables里只有一个tablebean
				
				if (tables != null && tables.size() <= 1) {
					TableBean mainTable = tables.get(0);
					String mainName=StringUtil
							.tableName(mainTable.tableEnName);
					String shortMainTableName=mainName.substring(mainName.lastIndexOf("_")+1);
					
					
					sql = "select * from " +mainTable.tableEnName ;
					sqlcount = "select count(*) from " + mainTable.tableEnName;
					sqlMax = "select max(${columnName}) from " + mainTable.tableEnName;
					return;
				}

				String show = "";
				String condition = "";
				String relate = "";
			

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
								condition += "where ";
								condition += " "
										+ shortTableName
										+ "." + column.columnEnName + " like '%\"+"
										+ column.columnEnName + "+\"%' ";
								condition += "";
								conditionFirstColumn = false;
							} else {
								condition += "";
								condition += " and "
										+ shortTableName
										+ "." + column.columnEnName + " like '%\"+"
										+ column.columnEnName + "+\"%' ";
								condition += " ";

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
				
				if ("".equals(show) && "".equals(condition)) {
					sql = "select * from " + relate;
					sqlcount = "select count(*) from " + relate;
					sqlMax = "select max(${columnName}) from " + relate;
				}

				else if ("".equals(show) && !"".equals(condition)) {
					sql = "select * from " + relate + " " + condition+"\n";
					sqlcount = "select count(*) from " + relate + " " + condition+"\n";
					sqlMax = "select max(${columnName}) from " + relate + "  " + condition+"\n";
				} else if (!"".equals(show) && "".equals(condition)) {
					sql = "select " + show.substring(0, show.lastIndexOf(","))
							+ " from " + relate;
					sqlcount = "select count(*) from " + relate;
					sqlMax = "select max(${columnName}) from " + relate;
				} else {
					sql = "select " + show.substring(0, show.lastIndexOf(","))
							+ " from " + relate + "  " + condition+"\n";
					sqlcount = "select count(*) from " + relate + "  " + condition+"\n";
					sqlMax = "select  max(${columnName}) from " + relate + "  " + condition+"\n";

				}




			}
			
			
		
			
		
	
}
