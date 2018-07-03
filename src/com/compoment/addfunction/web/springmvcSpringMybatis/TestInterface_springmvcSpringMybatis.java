package com.compoment.addfunction.web.springmvcSpringMybatis;

import java.util.ArrayList;
import java.util.List;

import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class TestInterface_springmvcSpringMybatis {

	public void testJsp(String interfaceName, String interfaceCnName,
			List<TableBean> tables) {

		List<TableColumnBean> queryConditionColumns = new ArrayList();

		String querywhere = "";
		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {


				if ("right".equals(column.rightClickSelected)) {
					queryConditionColumns.add(column);
				}
			}
		}

		// update para
		String querypara = "?";

		for (TableColumnBean column : queryConditionColumns) {	
			querypara += "" + column.columnEnName + "=&";
		}

		if(querypara.lastIndexOf("&")!=-1)
		{
			querypara=querypara.substring(0, querypara.lastIndexOf("&"));
		}
		
		// insert para
		String insertpara = "?";
		for (TableBean table : tables) {
			if (tables.size() == 1) {
				for (TableColumnBean column : table.columns) {
					insertpara += "" + column.columnEnName + "=&";
				}
			}
		}
		if(insertpara.lastIndexOf("&")!=-1)
		{
			insertpara=insertpara.substring(0, insertpara.lastIndexOf("&"));
		}

		// update para
		String updatepara = "?";
		for (TableBean table : tables) {
			if (tables.size() == 1) {
				for (TableColumnBean column : table.columns) {

					// if (column.key != null
					// && column.key.toLowerCase().contains("key")) {

					updatepara += "" + column.columnEnName + "=&";

					// }
				}
			}
		}
		if(updatepara.lastIndexOf("&")!=-1)
		{
			updatepara=updatepara.substring(0, updatepara.lastIndexOf("&"));
		}
		

		// delete para
		String deletepara = "?";
		for (TableBean table : tables) {
			if (tables.size() == 1) {
				for (TableColumnBean column : table.columns) {

					if (column.key != null
							&& column.key.toLowerCase().contains("key")) {

						deletepara += "" + column.columnEnName + "=&";

					}
				}
			}
		}
		if(deletepara.lastIndexOf("&")!=-1)
		{
			deletepara=deletepara.substring(0, deletepara.lastIndexOf("&"));
		}
		

		String m = "";
		m += "<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"utf-8\"%>\n";
		m += "<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\n";
		m += "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n";
		m += "<html>\n";
		m += "<head>\n";
		m += "<title>" + interfaceCnName + "</title>\n";
		m += "<script\n";
		m += "	type=\"text/javascript\" src=\"../js/jquery-1.7.1.js\"></script>\n";
		m += "	<script>\n";
		m += "		\n";
		m += "		\n";
		m += "		$(document).on('ready', function() {\n";

		m += "			var searchInp=\""+querypara+"\";\n";
		m += "			$(\"#searchIn\").val(searchInp);\n";

		m += "			var addInp=\""+insertpara+"\";\n";
		m += "			$(\"#addIn\").val(addInp);\n";

		m += "			var updateInp=\""+updatepara+"\";\n";
		m += "			$(\"#updateIn\").val(updateInp);\n";

		m += "			var deleteInp=\""+deletepara+"\";\n";
		m += "			$(\"#deleteIn\").val(deleteInp);\n";

		m += "		});\n";
		m += "		\n";
		m += "		\n";
		m += "		function doSearch(){\n";
		m += "		var searchInp=	$(\"#searchIn\").val();\n";
		m+="var host=window.document.location.host;\n";
		m+="var pathName=window.document.location.pathname;\n";
		m+="var projectName=pathName.substring(1,pathName.substr(1).indexOf('/')+1);\n";
		m+="var requesturl='http://'+host+'/'+projectName+'/"+interfaceName.toLowerCase()+"/query.do'+searchInp;\n";
		m += "			\n";
		m += "				$.ajax({\n";
		m += "					type:'POST',\n";
		m += "					url:requesturl,\n";
		m += "					data:{\n";

		m += "					},\n";
		m += "					success:function(result){\n";
		m += "							var returndata = result.returnData;\n";
		m+="var jsonstr=JSON.stringify(result); \n";
		m+="$(\"#pageRequest\").html(requesturl);\n";
		m+="$(\"#pageContent\").html(jsonstr);\n";
		
		m += "					},\n";
		m += "					error : function() {\n";
		m += "						alert(\"对不起，系统错误，请稍候重试！\")\n";
		m += "					}\n";
		m += "				});\n";
		m += "			\n";
		m += "		}\n";
		m += "		\n";
		m += "		\n";
		m += "		function doAdd(){\n";
		m += "		var addInp=	$(\"#addIn\").val();\n";
		m+="var host=window.document.location.host;\n";
		m+="var pathName=window.document.location.pathname;\n";
		m+="var projectName=pathName.substring(1,pathName.substr(1).indexOf('/')+1);\n";
		m+="var requesturl='http://'+host+'/'+projectName+'/"+interfaceName.toLowerCase()+"/insert.do'+addInp;\n";
		m += "			\n";
		m += "				$.ajax({\n";
		m += "					type:'POST',\n";
		m += "					url:requesturl,\n";
		m += "					data:{\n";

		m += "					},\n";
		m += "					success:function(result){\n";
		m += "							var returndata = result.returnData;\n";
		m+="var jsonstr=JSON.stringify(result); \n";
		m+="$(\"#pageRequest\").html(requesturl);\n";
		m+="$(\"#pageContent\").html(jsonstr);\n";
		m += "					},\n";
		m += "					error : function() {\n";
		m += "						alert(\"对不起，系统错误，请稍候重试！\")\n";
		m += "					}\n";
		m += "				});\n";
		m += "			\n";
		m += "		}\n";
		m += "		\n";
		m += "		\n";
		m += "		function doUpdate(){\n";
		m += "			var updateInp= $(\"#updateIn\").val();\n";
		m+="var host=window.document.location.host;\n";
		m+="var pathName=window.document.location.pathname;\n";
		m+="var projectName=pathName.substring(1,pathName.substr(1).indexOf('/')+1);\n";
		m+="var requesturl='http://'+host+'/'+projectName+'/"+interfaceName.toLowerCase()+"/update.do'+updateInp;\n";
		m += "			\n";
		m += "				$.ajax({\n";
		m += "					type:'POST',\n";
		m += "					url:requesturl,\n";
		m += "					data:{\n";

		m += "					},\n";
		m += "					success:function(result){\n";
		m += "							var returndata = result.returnData;\n";
		m+="var jsonstr=JSON.stringify(result); \n";
		m+="$(\"#pageRequest\").html(requesturl);\n";
		m+="$(\"#pageContent\").html(jsonstr);\n";
		m += "					},\n";
		m += "					error : function() {\n";
		m += "						alert(\"对不起，系统错误，请稍候重试！\")\n";
		m += "					}\n";
		m += "				});\n";
		m += "			\n";
		m += "		}\n";
		m += "		function doDelete(code){\n";
		m += "		var 	deleteInp=$(\"#deleteIn\").val();\n";
		m+="var host=window.document.location.host;\n";
		m+="var pathName=window.document.location.pathname;\n";
		m+="var projectName=pathName.substring(1,pathName.substr(1).indexOf('/')+1);\n";
		m+="var requesturl='http://'+host+'/'+projectName+'/"+interfaceName.toLowerCase()+"/delete.do'+deleteInp;\n";
		m += "			\n";
		m += "				$.ajax({\n";
		m += "					type:'POST',\n";
		m += "					url:requesturl,\n";
		m += "					data:{\n";

		m += "					},\n";
		m += "					success:function(result){\n";
		m += "							var returndata = result.returnData;\n";
		m+="var jsonstr=JSON.stringify(result); \n";
		m+="$(\"#pageRequest\").html(requesturl);\n";
		m+="$(\"#pageContent\").html(jsonstr);\n";
		m += "					},\n";
		m += "					error : function() {\n";
		m += "						alert(\"对不起，系统错误，请稍候重试！\")\n";
		m += "					}\n";
		m += "				});\n";
		m += "			\n";
		m += "		}\n";
		m += "		\n";
		m += "		\n";
		m += "		\n";
		m += "	</script>\n";

		m += "</head>\n";
		m += "<body>\n";
		m += "	<div> " + interfaceCnName + "接口</div>\n";
		m += "	<div style=\"padding-left:20px;margin-bottom:10px;\" >\n";
		m += "	<input type=\"hidden\" id=\"thd_sys_id\" name=\"thd_sys_id\" value=\"\" />\n";
		m += "	查询urlPara：<input type=\"text\" id=\"searchIn\" style=\"margin-left:10px;width:800px;height:20px; \" value=\"\"/>\n";
		m += "	<input type=\"button\" value=\"查询\" id=\"search\" name = \"search\" onmouseover=\"this.style.cursor='hand'\" style=\"width:50px;height:20px;font-size:12px;\"  onclick=\"doSearch()\">\n";

		m += "	</div>\n";
		
		
		String visiable="";
		if(tables!=null && tables.size()>1)
		{
			visiable="display:none;";
		}else
		{
			visiable="";
		}
		m += "	\n";
		m += "	<div style=\"padding-left:20px;margin-bottom:10px;"+visiable+"\" >\n";
		m += "	<input type=\"hidden\" id=\"thd_sys_id\" name=\"thd_sys_id\" value=\"\" />\n";
		m += "	新增urlPara：<input type=\"text\" id=\"addIn\" style=\"margin-left:10px;width:800px;height:20px; \" value=\"\"/>\n";
		m += "	<input type=\"button\" value=\"新增\" id=\"search\" name = \"search\" onmouseover=\"this.style.cursor='hand'\" style=\"width:50px;height:20px;font-size:12px;\"  onclick=\"doAdd()\">\n";

		m += "	</div>\n";
		m += "	\n";
		m += "	<div style=\"padding-left:20px;margin-bottom:10px;"+visiable+"\" >\n";
		m += "	<input type=\"hidden\" id=\"thd_sys_id\" name=\"thd_sys_id\" value=\"\" />\n";
		m += "	修改urlPara：<input type=\"text\" id=\"updateIn\" style=\"margin-left:10px;width:800px;height:20px; \" value=\"\"/>\n";
		m += "	<input type=\"button\" value=\"修改\" id=\"search\" name = \"search\" onmouseover=\"this.style.cursor='hand'\" style=\"width:50px;height:20px;font-size:12px;\"  onclick=\"doUpdate()\">\n";

		m += "	</div>\n";
		m += "	<div style=\"padding-left:20px;margin-bottom:10px;"+visiable+"\" >\n";
		m += "	<input type=\"hidden\" id=\"thd_sys_id\" name=\"thd_sys_id\" value=\"\" />\n";
		m += "	删除urlPara：<input type=\"text\" id=\"deleteIn\" style=\"margin-left:10px;width:800px;height:20px; \" value=\"\"/>\n";
		m += "	<input type=\"button\" value=\"删除\" id=\"search\" name = \"search\" onmouseover=\"this.style.cursor='hand'\" style=\"width:50px;height:20px;font-size:12px;\"  onclick=\"doDelete()\">\n";

		m += "	</div>\n";
		m+="<div> 请求Url</div>\n";
		m+="<div id=\"pageRequest\"></div>\n";
		m += "	<div> 接口输出</div>\n";
		m += "	<div id=\"pageContent\"></div>\n";
		m += "  </div>\n";
		m += "	\n";
		m += "</body>\n";
		m += "</html>\n";

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web", interfaceName+"Test",
				"jsp", m);

	}
}
