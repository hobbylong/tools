package com.compoment.addfunction.web.servlet.hibernate;

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
	String resultType="";
	
	
	public void serviceInterface(List<TableBean> tables,String interfaceName,String interfaceCnName) {
		
		sqlbody(tables,interfaceName);
		
		String m = "";

		String servicename = "";
	
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
						+ ",boolean isCount) throws Exception;\n";
				
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
			m += "	private final static Logger log = LoggerFactory.getLogger("
					+ interfaceName + "ServiceImpl.class);\n";
			m += "	\n";

		
			

			for (TableBean table : tables) {

				if (table.isMainTable && tables.size() > 1 || tables.size() == 1) {

					m += "	@Override\n";
					m += "	public List<" + resultType + "> get("
							+ queryCondition + ",boolean isCount) throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
				
					m+="		ArrayList<"+resultType+"> beans = new ArrayList();\n";
					
				
					m+="WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();\n";
				    m+="ObjectDao dao=(ObjectDao) wac.getBean(\"objectDao\");\n";
				 

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
					m+="		//String pageHead = \"\";\n";
					m+="		//String pageEnd = \" limit #{currIndex},#{pageSize} \";\n";
					
					
					
					m+="		List tableBeans=new ArrayList();\n";
					m+="		List tableBeanShortName=new ArrayList();\n";
					m+=tableBeansString+"\n";
					m+=tableBeanShortNameString+"\n";
					
					//query count
					m+="		if(isCount==true){\n";
					m+="		String sql=\""+sqlcount+"\";\n";
					m+="		List result = dao.findBySql(sql,new HashMap());\n";
				
					m+="return result;\n";
				    m+="}\n";
					
				    
				    //query
					m+="		String sql=pageHead+\""+sql+"\"+pageEnd;\n";
					m+="		\n";
				
					m+="		List rows = dao.findBySql(sql,new HashMap(),tableBeans,tableBeanShortName);\n";
					m+="		\n";
					m+="		for (int i = 0; i < rows.size(); i++) {\n";

					m+="//多个表时\n";
			        m+="//Object[] objects = (Object[]) rows.get(i);\n";
			        m+="//ABean aBean = (ABean) objects[0];  \n";
			        m+="//BBean bBean = (BBean) objects[1];  \n";
			        
			        m+=resultType+" bean=("+resultType+")rows.get(i);\n";
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
					m+="Object obj=result.get(0);\n";
					m+="if(obj instanceof BigDecimal )\n";
					m+="{\n";
					m+="return ((BigDecimal) result.get(0)).intValue();\n";
					m+="}else{\n";
					m+="return ((Long) result.get(0)).intValue();\n";
					m+="}\n";
				
					m+="}\n";
					m+="		return 0;\n";

					
					
					m += "	}\n";
					
					
					m += "	@Override\n";
					m += "	public int getMax("
							+ queryCondition + ") throws Exception {\n";
					m += "		// TODO Auto-generated method stub\n";
				

					m+="WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();\n";
				    m+="ObjectDao dao=(ObjectDao) wac.getBean(\"objectDao\");\n";
					m+="String sql="+sqlMax+";\n";
					m+="List max = dao.findBySql(sql,new HashMap());\n";
					m+="if(max!=null && max.size()>0){\n";
					
					m+="Object obj=max.get(0);\n";
					m+="if(obj instanceof BigDecimal )\n";
					m+="{\n";
					m+="return ((BigDecimal) max.get(0)).intValue();\n";
					m+="}else{\n";
					m+="return ((Long) max.get(0)).intValue();\n";
					m+="}\n";
					
				
					m+="}\n";
					m+="return 0;\n";
					
					m += "	}\n";

				} 
					
					if (tables.size() == 1) {

					m += "	@Override\n";
					m += "public void " + " insert(" + table.tableEnName
							+ "Bean bean){\n";

					m+="WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();\n";
				    m+="ObjectDao dao=(ObjectDao) wac.getBean(\"objectDao\");\n";
					
					m+="	\n";
					m+="		try {\n";
					m+="			dao.save(bean);\n";
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
			

					m+="WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();\n";
				    m+="ObjectDao dao=(ObjectDao) wac.getBean(\"objectDao\");\n";

					m+="		try {\n";
					m+="			dao.saveOrUpdate(bean);\n";
					m+="		} catch (Exception e) {\n";
					m+="			// TODO Auto-generated catch block\n";
					m+="			e.printStackTrace();\n";
					m+="		}\n";

					

					
					
					m += "	}\n\n";

					m += "	@Override\n";
					m += "public void   delete(" + table.tableEnName
							+ "Bean bean){\n";
					

					m+="WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();\n";
				    m+="ObjectDao dao=(ObjectDao) wac.getBean(\"objectDao\");\n";
					m+="try {\n";
					m+="	dao.delete(bean);\n";
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
	
	
	
	


	String tableBeansString="";
	String tableBeanShortNameString="";
	
	public void sqlbody(List<TableBean> tables,String interfaceName) {
		
		//批量生成时tables里只有一个tablebean
				if (tables != null && tables.size() <= 1) {
					TableBean mainTable = tables.get(0);
					String mainName=StringUtil
							.tableName(mainTable.tableEnName);
					String shortMainTableName=mainName.substring(mainName.lastIndexOf("_")+1);
					
					tableBeansString+="tableBeans.add("+mainTable.tableEnName+"Bean.class);\n";
					tableBeanShortNameString+="tableBeanShortName.add(\""+shortMainTableName+"\");\n";
					
					
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
							tableBeansString+="tableBeans.add("+mainTableColumn.belongWhichTable.tableEnName+"Bean.class);\n";
							tableBeanShortNameString+="tableBeanShortName.add(\""+shortMainTableName+"\");\n";
							
						}
						
						
						String chirldName=StringUtil
								.tableName(chirldTableColumn.belongWhichTable.tableEnName);
						String shortChirldTableName=chirldName.substring(chirldName.lastIndexOf("_")+1);
						
						tableBeansString+="tableBeans.add("+mainTableColumn.belongWhichTable.tableEnName+"Bean.class);\n";
						tableBeanShortNameString+="tableBeanShortName.add(\""+shortMainTableName+"\");\n";
						
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
