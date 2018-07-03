package com.compoment.addfunction.web.springmvcSpringMybatis;

import java.util.ArrayList;
import java.util.List;

import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class Controller {

	
	public void controller(List<TableBean> tables,String interfaceName,String interfaceCnName) {

		String servicename = "";
		String serviceCnName = "";
		String resultType = "";
		
		String queryCondition2 = "";
		String mainTableName = "";
		for (TableBean table : tables) {
			servicename += table.tableEnName + "_";
			serviceCnName += table.tableCnName + "_";
			if ( tables.size() > 1) {
			
				resultType = interfaceName + "Bean";
				mainTableName = interfaceName;
				queryCondition2="paraMap";
			}else
			{
				
				resultType = table.tableEnName + "Bean";
				mainTableName = table.tableEnName;
				queryCondition2="reqMap";
			}
		}

		if (servicename.lastIndexOf("_") != -1) {
			servicename = servicename
					.substring(0, servicename.lastIndexOf("_"));
		}

		if (interfaceName == null || "".equals(interfaceName)) {
			interfaceName = servicename;
		}

	

		String m = "";
		m += "package com.company.controller;\n";
		m += "import java.util.Locale;\n";
		m += "import java.util.Map;\n";
		m += "import java.util.Set;\n";
		m += "import java.util.List;\n";

		m += "import javax.validation.ConstraintViolation;\n";
		m += "import javax.validation.Validator;\n";
		m += "import org.springframework.beans.factory.annotation.Autowired;\n";
		m += "import org.springframework.context.annotation.Scope;\n";
		m += "import org.springframework.context.i18n.LocaleContextHolder;\n";
		m += "import org.springframework.stereotype.Controller;\n";
		m += "import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;\n";
		m += "import org.springframework.web.bind.annotation.RequestBody;\n";
		m += "import org.springframework.web.bind.annotation.RequestMapping;\n";
		m += "import org.springframework.web.bind.annotation.RequestParam;\n";
		m += "import org.springframework.web.bind.annotation.ResponseBody;\n";

		m += "import org.springframework.web.servlet.support.RequestContext;\n";

		m += "import com.framework.controller.BaseController;\n";
		m += "import com.framework.utils.SpringBeanManger;\n";
		m += "import com.framework.utils.Constant;\n";
		m += "import com.framework.utils.CommonUtil;\n";

		m += "\n/**" + interfaceCnName + "*/\n";
		m += "@Controller\n";
		m += "@Scope(\"prototype\")\n";
		m += "@RequestMapping(\"/" + interfaceName.toLowerCase() + "\")\n";
		m += "public class " + interfaceName
				+ "Controller extends BaseController {\n";

		m += "@Autowired\n";
		m += "private " + interfaceName + "Service "
				+ StringUtil.firstCharToLower(interfaceName) + "Service;\n";

		m += "	@RequestMapping(\"/query.do\")\n";
		m += "	public @ResponseBody Map<String, Object> query(@RequestParam Map reqMap) {\n";

		m += "		//if (null == reqMap || reqMap.isEmpty())\n";
		m += "			//return CommonUtil.ReturnWarp(Constant.TRAN_PARAERCODE, Constant.ERRORTYPE);\n";

		m+="Map paraMap=new HashMap();\n";
		m+="		String pageNo = reqMap.get(\"pageNo\");\n";
		
		m+="		if (StringUtils.isBlank(pageNo)) {//判断某字符串是否为空或长度为0或由空白符(whitespace) 构成\n";
		m+="			pageNo = \"1\";\n";
		m+="			request.setAttribute(\"pageNo\", pageNo);\n";
		m+="		}\n";
		
		m+="		String pageSize = reqMap.get(\"pageSize\");\n";
		m+="		if (StringUtils.isBlank(pageSize)) {\n";
		m+="			pageSize = \"10\";\n";
		m+="			request.setAttribute(\"pageSize\", pageSize);\n";
		m+="		}\n";
		
		m+="Map paraMap=new HashMap();\n";
		m+="paraMap.put(\"currIndex\", (pageNo-1)*pageSize);\n";
		m+="paraMap.put(\"pageSize\", pageSize);\n";
		
		int i = 0;
		String n="";
		List<TableColumnBean> queryConditionColumns=getQueryConditionColumns(tables);
		for (TableColumnBean column : queryConditionColumns) {

			if (column.type.toLowerCase().contains("int")) {
				m += "		int " + column.columnEnName
						+ " = reqMap.get(\"" + column.columnEnName
						+ "\") == null ? 0 :Integer.valueOf( reqMap.get(\""
						+ column.columnEnName + "\").toString());\n";
				
			}else
			{
				m += "		String " + column.columnEnName
						+ " = reqMap.get(\"" + column.columnEnName
						+ "\") == null ? null : reqMap.get(\""
						+ column.columnEnName + "\").toString();\n";
			}
			
			
			
		
				m +="paraMap.put(\""+ column.columnEnName + "\","+column.columnEnName+");\n";
			

			i++;
		}
		
		m += "List<"+mainTableName + "Bean> " + mainTableName.toLowerCase()
				+ "Beans=null;\n";
		
		
		m += "try {\n";
		m += mainTableName.toLowerCase() + "Beans="
				+ StringUtil.firstCharToLower(interfaceName) + "Service.get("
				+ queryCondition2 + ");\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";

		m+="Map r=new HashMap();\n";
		m+="r.put(\"returnData\","+ mainTableName.toLowerCase()+"Beans);\n";
		m += "	  return CommonUtil.ReturnWarp(Constant.TRAN_SUCCESS, Constant.ERRORTYPE,\"\",r);\n";
		m += "	}\n";

		for (TableBean table : tables) {

			if (table.isMainTable && tables.size() > 1) {

			} else if (tables.size() == 1) {

				m += "	@RequestMapping(\"/insert.do\")\n";
				m += "	public @ResponseBody Map<String, Object> insert(@RequestParam Map reqMap) {\n";

				m += "		if (null == reqMap || reqMap.isEmpty())\n";
				m += "			return CommonUtil.ReturnWarp(Constant.TRAN_PARAERCODE, Constant.ERRORTYPE);\n";

				 n="";
				for (TableColumnBean column : table.columns) {
					

					if (column.type.toLowerCase().contains("int")) {
						m += "		int " + column.columnEnName
								+ " = reqMap.get(\"" + column.columnEnName
								+ "\") == null ? 0 :Integer.valueOf( reqMap.get(\""
								+ column.columnEnName + "\").toString());\n";
						
					}else
					{
						m += "		String " + column.columnEnName
								+ " = reqMap.get(\"" + column.columnEnName
								+ "\") == null ? null : reqMap.get(\""
								+ column.columnEnName + "\").toString();\n";
					}
						n+=mainTableName.toLowerCase()+ "Bean."+column.columnEnName+"="+column.columnEnName+";\n";
					
				}

				m += mainTableName + "Bean " + mainTableName.toLowerCase()
						+ "Bean=new "+mainTableName+"Bean();\n";
				m+=n;
				
				m += "try {\n";
				m += StringUtil.firstCharToLower(interfaceName)
						+ "Service.insert(" + mainTableName.toLowerCase()
						+ "Bean);\n";
				m += "} catch (Exception e) {\n";
				m += "	e.printStackTrace();\n";
				m += "}\n";

				m += "		return CommonUtil.ReturnWarp(Constant.TRAN_SUCCESS, Constant.ERRORTYPE);\n";
				m += "	}\n";

				m += "	@RequestMapping(\"/update.do\")\n";
				m += "	public @ResponseBody Map<String, Object> update(@RequestParam Map reqMap) {\n";

				m += "		if (null == reqMap || reqMap.isEmpty())\n";
				m += "			return CommonUtil.ReturnWarp(Constant.TRAN_PARAERCODE, Constant.ERRORTYPE);\n";

				 n="";
				for (TableColumnBean column : table.columns) {
				
					if (column.type.toLowerCase().contains("int")) {
						m += "		int " + column.columnEnName
								+ " = reqMap.get(\"" + column.columnEnName
								+ "\") == null ? 0 :Integer.valueOf( reqMap.get(\""
								+ column.columnEnName + "\").toString());\n";
						
					}else
					{
						m += "		String " + column.columnEnName
								+ " = reqMap.get(\"" + column.columnEnName
								+ "\") == null ? null : reqMap.get(\""
								+ column.columnEnName + "\").toString();\n";
					}
						n+=mainTableName.toLowerCase()+ "Bean."+column.columnEnName+"="+column.columnEnName+";\n";
					
				}

				m += mainTableName + "Bean " + mainTableName.toLowerCase()
						+ "Bean=new "+mainTableName+"Bean();\n";
				m+=n;
				
				m += "try {\n";
				m += StringUtil.firstCharToLower(interfaceName)
						+ "Service.update(" + mainTableName.toLowerCase()
						+ "Bean);\n";
				m += "} catch (Exception e) {\n";
				m += "	e.printStackTrace();\n";
				m += "}\n";

				m += "		return CommonUtil.ReturnWarp(Constant.TRAN_SUCCESS, Constant.ERRORTYPE);\n";
				m += "	}\n";

				
				
				
				
				m += "	@RequestMapping(\"/delete.do\")\n";
				m += "	public @ResponseBody Map<String, Object> delete(@RequestParam Map reqMap) {\n";

				m += "		if (null == reqMap || reqMap.isEmpty())\n";
				m += "			return CommonUtil.ReturnWarp(Constant.TRAN_PARAERCODE, Constant.ERRORTYPE);\n";

				
				 n="";
				for (TableColumnBean column : table.columns) {
					if (column.key != null
							&& column.key.toLowerCase().contains("key")) {

						if (column.type.toLowerCase().contains("int")) {
							m += "		int " + column.columnEnName
									+ " = reqMap.get(\"" + column.columnEnName
									+ "\") == null ? 0 :Integer.valueOf( reqMap.get(\""
									+ column.columnEnName + "\").toString());\n";
							
						}else
						{
							m += "		String " + column.columnEnName
									+ " = reqMap.get(\"" + column.columnEnName
									+ "\") == null ? null : reqMap.get(\""
									+ column.columnEnName + "\").toString();\n";
						}
						n+=mainTableName.toLowerCase()+ "Bean."+column.columnEnName+"="+column.columnEnName+";\n";
					}
				}

				m += mainTableName + "Bean " + mainTableName.toLowerCase()
						+ "Bean=new "+mainTableName+"Bean();\n";
				m+=n;
				m += "try {\n";
				m += StringUtil.firstCharToLower(interfaceName)
						+ "Service.delete(" + mainTableName.toLowerCase()
						+ "Bean);\n";
				m += "} catch (Exception e) {\n";
				m += "	e.printStackTrace();\n";
				m += "}\n";

				m += "		return CommonUtil.ReturnWarp(Constant.TRAN_SUCCESS, Constant.ERRORTYPE);\n";
				m += "	}\n";

			}
		}

		m += "}\n";

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				interfaceName + "Controller", "java", m);
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
