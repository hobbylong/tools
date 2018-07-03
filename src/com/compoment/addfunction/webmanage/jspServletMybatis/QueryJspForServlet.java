package com.compoment.addfunction.webmanage.jspServletMybatis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class QueryJspForServlet {

	
	public  QueryJspForServlet(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		for (InterfaceBean interfaceBean : interfaceBeans) {
			
			queryjspStruct2(interfaceBean, "Respond");
		}
	}
	
	

	public void queryjspStruct2(InterfaceBean interfaceBean,String type)
	{
		String inKeyString="";
		String indataKeyString="";
		String ajaxdataKeyString="";
		String urlKeyString="";
		String requestKeyString="";
		String updateKeyString="";
		String deleteKeyString="";
		
		String cacheValueHiddenString="";
		
		List<Group> groups = interfaceBean.respondGroups;
		
		
		int maincount=0;
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if(row.remarks.toLowerCase().contains("key"))
					{
						if(row.remarks.toLowerCase().contains("main"))
						{
							maincount++;
						}
					}
					}
				}
			}
		
		
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if(row.remarks.toLowerCase().contains("key"))
					{
						
						if(row.remarks.toLowerCase().contains("main")||maincount==0)
						{
							deleteKeyString+="'+data[i]."+StringUtil.underline2Camel(row.enName.toLowerCase(), true)+"+',";
							updateKeyString+=row.enName.toLowerCase()+"='+data[i]."+StringUtil.underline2Camel(row.enName.toLowerCase(), true)+"+'%26";
						
						}else
						{
							deleteKeyString+="\'+${"+row.enName.toLowerCase()+"}+\',";
						updateKeyString+=row.enName.toLowerCase()+"=${"+row.enName.toLowerCase()+"}%26";
					
						}
								
						indataKeyString+=row.enName.toLowerCase()+":m"+row.enName.toLowerCase()+",\n";
						
					
						
						
						inKeyString+="m"+row.enName.toLowerCase()+",";
						urlKeyString+=""+row.enName.toLowerCase()+"=${"+row.enName.toLowerCase()+"}%26";
						
						requestKeyString+="			var "+row.enName.toLowerCase()+"=\"${"+row.enName.toLowerCase()+"}\";\n";
						requestKeyString+="			$(\"#"+row.enName.toLowerCase()+"\").val("+row.enName.toLowerCase()+");\n";
						
						
						cacheValueHiddenString+="	<input type=\"hidden\" id=\""+row.enName.toLowerCase()+"\" name=\""+row.enName.toLowerCase()+"\" value=\"\" />\n";
					}
				}
			}
			}
		
		if(inKeyString.lastIndexOf(",")!=-1)
		inKeyString=inKeyString.substring(0, inKeyString.lastIndexOf(","));
		
		if(indataKeyString.lastIndexOf(",")!=-1)
		indataKeyString=indataKeyString.substring(0, indataKeyString.lastIndexOf(","));
		
		if(urlKeyString.lastIndexOf("%26")!=-1)
		urlKeyString=urlKeyString.substring(0, urlKeyString.lastIndexOf("%26"));
		
		if(updateKeyString.lastIndexOf("%26")!=-1)
		updateKeyString=updateKeyString.substring(0, updateKeyString.lastIndexOf("%26"));
		
		if(deleteKeyString.lastIndexOf(",")!=-1)
		deleteKeyString=deleteKeyString.substring(0, deleteKeyString.lastIndexOf(","));
		
		
		
		
		String m="";
		m+="<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"utf-8\"%>\n";
		m += "<%\n";
		m += "String path = request.getContextPath();\n";
		m += "String basePath = request.getScheme()+\"://\"+request.getServerName()+\":\"+request.getServerPort()+path+\"/\";\n";
		m += "%>\n";
		m+="<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\n";
		m+="<%@ taglib prefix=\"fn\" uri=\"http://java.sun.com/jsp/jstl/functions\"%>\n";
		m+="<%@ include file=\"mutiSelect.jsp\"%>\n";
		m+="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n";
		m+="<html>\n";
		m+="<head>\n";
		
		m+="<title>"+interfaceBean.title+"</title>\n";
		m+="<link rel=\"stylesheet\" href=\"<%=basePath%>css/admin_style.css\" type=\"text/css\" />\n";
		m+="<script type=\"text/javascript\" language=\"javascript\" src=\"<%=basePath%>js/My97DatePicker/WdatePicker.js\"></script>\n";
		m += "	<script type=\"text/javascript\" src=\"<%=basePath%>js/jquery.js\"></script>\n";
		m+="	<script>\n";
		
		
		
		m+="		function toAdd(){\n";
		
		String urlKeyStringForAdd="";
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if(row.remarks.toLowerCase().contains("key"))
					{
						m+="			var "+row.enName.toLowerCase()+"=\"${"+row.enName.toLowerCase()+"}\";\n";
						m+="			$(\"#"+row.enName.toLowerCase()+"\").val("+row.enName.toLowerCase()+");\n";
					}
				}
			}
		 }
		
		m+="			window.location.href=\"<%=basePath%>"+interfaceBean.enName+"Servlet?method=toAdd\";\n";
		m+="		}\n";
		m+="		\n";
		
		m+="		\n";
		m+="		$(document).on('ready', function() {\n";
		m+=requestKeyString;

		m+="			getAll('"+interfaceBean.enName+"Servlet?method=list');\n";
		m+="		});\n";
		m+="		\n";
		m+="		\n";
		m+="		function search(){\n";
		m+="			getAll('"+interfaceBean.enName+"Servlet?method=list');\n";
		m+="		}\n";
		m+="		\n";
		
		
		
		
		
		m+="		function dodel("+inKeyString+"){\n";
		m+="			var r=confirm(\"确定要删除吗？\");\n";
		m+="			if(r){\n";
		m+="				$.ajax({\n";
		m+="					type:'POST',\n";
		m+="					url:'<%=basePath%>"+interfaceBean.enName+"Servlet?method=doDelete',\n";
		m+="					data:{\n";
		
		
		m+=indataKeyString;
	
		
		m+="					\n},\n";
		m+="					success:function(k){\n";
		m+="							alert(\"删除成功！\")\n";
		m+="							window.location.href = \"<%=basePath%>"+interfaceBean.enName+"Servlet?method=index&"+urlKeyString+"\";\n";
		m+="					},\n";
		m+="					error : function() {\n";
		m+="						alert(\"对不起，系统错误，请稍候重试！\")\n";
		m+="					}\n";
		m+="				});\n";
		m+="			}\n";
		m+="			\n";
		m+="			\n";
		m+="		}\n";
		
		
		
		
		
		
		
		m+="		function getAll(tzurl){\n";
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if(row.remarks.toLowerCase().contains("key"))
					{
						
					}else
					{
						m+="		var "+row.enName.toLowerCase()+"=$('#"+row.enName.toLowerCase()+"').val();\n";
						
						if(row.cnName.contains("开始时间")||row.cnName.contains("开始日期"))
						{
						
							m+="		if($(\"#"+row.enName.toLowerCase()+"End\").val()!=\"\" && $(\"#"+row.enName.toLowerCase()+"\").val() > $(\"#"+row.enName.toLowerCase()+"End\").val()){\n";
							m+="				alert(\"开始时间不能大于结束时间\");\n";
							m+="				return ;\n";
							m+="		}\n";
						}
						
						if(row.type.toLowerCase().contains("int")||row.type.contains("整形")||row.type.contains("整数"))
						{
					
							m+="		if("+row.enName.toLowerCase()+"!=\"\"){  \n";
							m+="		if( !/^\\d+$/.test("+row.enName.toLowerCase()+")){  \n";
							m+="	        alert(\""+row.cnName+"必须是正整数!\"); \n";
							m+="	        return ;\n";
							m+="	    }  \n";
							m+="	    }  \n";
							m+="		\n";
						}
						
						ajaxdataKeyString+=row.enName.toLowerCase()+":"+"$(\"#"+row.enName.toLowerCase()+"\").val(),\n";
					}
					i++;
				}
			}

		}
		
	

		if(ajaxdataKeyString.lastIndexOf(",")!=-1)
		ajaxdataKeyString=ajaxdataKeyString.substring(0, ajaxdataKeyString.lastIndexOf(","));
		
	
		
	
		m+="	\n\n			$.ajax({\n";
		m+="					type:'POST',\n";
		m+="					dataType:'json',\n";
		m+="					url:'<%=basePath%>'+tzurl,\n";
		m+="					data:{\n";
		m+=ajaxdataKeyString;
		m+="					},\n";
		m+="					success:function(result){\n";
		m+="	\n";
		m+="							var divtext = '';\n";
		m+="							var data =result.list;\n";
		m+="							var pagenational = result.pageString;\n";
		m+="						\n";
		m+="							for(var i=0;i<data.length;i++){\n";
		m+="								divtext += '<tr class=\"even\" style=\"white-space:nowrap; overflow:hidden; text-align:center\">';\n";
		
		
	
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
				
					if(row.remarks.toLowerCase().contains("key"))
					{
						
					}else
					{
						
						if(row.type.toLowerCase().equals("bool")||row.type.toLowerCase().equals("boolean"))
						{
							m+="								if(data[i]."+StringUtil.underline2Camel(row.enName.toLowerCase(), true)+"=='01'){\n";
							m+="									divtext +=  '"+row.cnName.replaceAll("", "")+"' ;\n";
							m+="								}else if(data[i]."+StringUtil.underline2Camel(row.enName.toLowerCase(), true)+"=='02'){\n";
							m+="									divtext +=  '"+row.cnName.replaceAll("", "")+"' ;\n";
							m+="								}\n";
							m+="								divtext += '</td>';\n";
							
						}else
						{
					
						m+="	 divtext += '<td>' + data[i]."+StringUtil.underline2Camel(row.enName.toLowerCase(), true)+" + '</td>';\n";
						
						}
					}
					
					i++;
				}
			}

		}
		
		


		m+="								divtext += '<td ><a href=\"<%=basePath%>"+interfaceBean.enName+"Servlet?method=toUpdate&"+updateKeyString+"\"> [修改] </a>'\n";
		m+="								divtext +='|<a href=\"javascript:void(0);\" onclick=\"dodel("+deleteKeyString+")\"> [删除] </a></td>';\n";
		m+="								divtext += '</tr>';\n";
		m+="							}\n";
		m+="							//divtext += pagenational;\n";
		m+="							$(\"#newtable tbody\").html(divtext);\n";
		m+="							$(\"#pageContent\").html(pagenational);\n";

		m+="					}\n";
		m+="				});\n";
		m+="		}\n";
		m+="	</script>\n";

		

		
	m+="	<script>\n";
		m+="	//多选   获取树数据   \n";
		m+="   function treeDataQuery(){\n";
		m+="	$.ajax({\n";
		m+="		type:'POST',\n";
		m+="		dataType:'json',\n";
		m+="		url:'<%=basePath%>TbCommTagServlet?method=orgs',\n";
		m+="		data:param,\n";
		m+="		success:function(json){\n";
		m+="			var zNodes = json.list;\n";
		m+="			if(zNodes==null)\n";
		m+="			{\n";
		m+="			return;\n";
		m+="			}\n";
		m+="			var jsonstr= JSON.stringify(zNodes);\n";
		m+="			 jsonstr = jsonstr.replace(/ID/g, 'id'); \n";
		m+="			 jsonstr = jsonstr.replace(/Pid/g, 'pId'); \n";
		m+="			 jsonstr = jsonstr.replace(/NAME/g, 'name'); \n";
		m+="			 zNodes = eval(\"(\" + jsonstr + \")\");\n";

		m+="			$.fn.zTree.init($(\"#tree_all\"), setting, zNodes);\n";
		m+="			zTree = $.fn.zTree.getZTreeObj(\"tree_all\");\n";
		m+="			setCheck();\n";
		m+="			$(\"#py\").bind(\"change\", setCheck);\n";
		m+="			$(\"#sy\").bind(\"change\", setCheck);\n";
		m+="			$(\"#pn\").bind(\"change\", setCheck);\n";
		m+="			$(\"#sn\").bind(\"change\", setCheck);\n";

		m+="			\n";
		m+="			treeDataSelectQuery();\n";
		m+="			\n";
		m+="		}\n";
		m+="	});\n";
		m+="   }\n";
		
		m+="	function treeDataSelectQuery(){\n";
		m+="	$.ajax({\n";
		m+="		type:'POST',\n";
		m+="		dataType:'json',\n";
		m+="		url:'<%=basePath%>TbCommTagServlet?method=orgsSelect',\n";
		m+="		data:{\"tag_id\":$(\"#tag_id\").val()},\n";
		m+="		success:function(json){\n";
		m+="			if(json==null)\n";
		m+="				return;\n";
		m+="			\n";
		m+="			var zNodes = json.list;\n";
		m+="			if(zNodes==null)\n";
		m+="				{\n";
		m+="				return;\n";
		m+="				}\n";
		m+="			var jsonstr= JSON.stringify(zNodes);\n";
		m+="			 jsonstr = jsonstr.replace(/ID/g, 'id'); \n";
		m+="			 jsonstr = jsonstr.replace(/Pid/g, 'pId'); \n";
		m+="			 jsonstr = jsonstr.replace(/NAME/g, 'name'); \n";
		m+="			 zNodes = eval(\"(\" + jsonstr + \")\");\n";

		m+="			\n";

		m+="			var len = zNodes.length;\n";
		m+="			if (len == 0) {\n";
		m+="				return;\n";
		m+="			}\n";
		m+="			$(\"#select_count\").val(len);\n";
		m+="			var i = 0;\n";
		m+="			var nid = 0;\n";
		m+="			var checkedOrg = \"\";\n";
		m+="			var checkedOrgs;\n";
		m+="			var name=\"\";\n";
		m+="			var code=\"\";\n";
		m+="			for (i = 0; i < len; i++) {\n";
		m+="				checkedOrgs = zNodes[i];\n";
		m+="				checkedOrg = checkedHtml( checkedOrgs.id,checkedOrgs.name);\n";
		m+="				$(\"#org_checked\").append(checkedOrg);\n";
		m+="				name+=checkedOrgs.name+\",\"\n";
		m+="				code+=checkedOrgs.id+\",\"\n";
		m+="				\n";
		m+="			}\n";
		m+="			\n";
		m+="			$(\"#show_ids\").val(code);\n";
		m+="			$(\"#show_names_edit\").val(name);\n";
		m+="		}\n";
		m+="	});\n";
		m+="	}\n";
		m+="	\n";
		m+="	\n";
		m+="	\n";
		m+="	</script>\n";
		
		m+="</head>\n";
		m+="<body style=\"overflow:auto\">\n";
		
		m+="	<div style=\"padding-left:20px;margin-bottom:10px;\" >\n";
		
		
		m+=cacheValueHiddenString;
		
		
		m+="	"+interfaceBean.title+"\n";
		
		//m+="	"+interfaceBean.title+"：<input type=\"text\" id=\"searchInput\" style=\"margin-left:10px;width:100px;height:20px; \"/>\n";
		
		m+="<div>\n";
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
				
					if(row.remarks.toLowerCase().contains("key"))
					{
						
					}else
					{

						if(row.cnName.contains("是否")||row.cnName.contains("状态")||row.type.toLowerCase().equals("boolean")||row.type.toLowerCase().equals("bool"))
						{
							
							m+="\n<font size=\"2\" color=\"\">"+row.cnName.replaceAll("", "")+":</font>";
						    m+="<select id=\""+row.enName.toLowerCase()+"\" name=\""+row.enName.toLowerCase()+"\"><option value=\"1\">是</option>\n";
							m+="					<option value=\"0\">否</option></select>\n";
							
						}else if(row.cnName.contains("日期")||row.cnName.contains("时间")||row.type.toLowerCase().contains("time")||row.type.toLowerCase().contains("date"))
						{
							
						
							m+="	\n<font size=\"2\" color=\"\">"+row.cnName.replaceAll("", "")+":</font>";
							m+="	<input id=\""+row.enName.toLowerCase()+"\" name=\""+row.enName.toLowerCase()+"\" value=\"${ "+row.enName.toLowerCase()+"}\" style=\"margin-right:10px;width: 150px\" class=\"Wdate\" \n";
							m+="					 onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})\"/>\n";
						
							m+="\n"+row.cnName.replaceAll("", "")+"结束:";
							m+="	<input id=\""+row.enName.toLowerCase()+"End\" name=\""+row.enName.toLowerCase()+"End\" value=\"${ "+row.enName.toLowerCase()+"End}\" style=\"margin-right:10px;width: 150px\" class=\"Wdate\" \n";
							m+="					 onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})\"/>\n";
							
						}
						else if(row.cnName.contains("图片")||row.cnName.contains("文件")||row.type.toLowerCase().equals("image")||row.type.toLowerCase().equals("file"))
						{
							
							
							m+="	\n<font size=\"2\" color=\"\">"+row.cnName.replaceAll("", "")+":</font>";
							m+="	<input type=\"file\" id=\""+row.enName.toLowerCase()+"\" name=\""+row.enName.toLowerCase()+"\"  />\n";
						
						}else if(row.cnName.contains("单选")||row.cnName.contains("选择")||row.remarks.contains("单选")||row.remarks.contains("选择")||row.type.toLowerCase().equals("select"))
						{
							
							m+="	\n<font size=\"2\" color=\"\">"+row.cnName.replaceAll("", "")+":</font>";
							m+="<select id=\""+row.enName.toLowerCase()+"\" name=\""+row.enName.toLowerCase()+"\" class=\"form-control\" style=\"width: 187px;height:23px;margin-bottom:10px;\">\n";
							m+="				<option value=\"\">请选择</option>\n";
							m+="					<c:forEach var=\"item\" items=\"${"+row.enName.toLowerCase()+"SelectList}\">	\n";
							m+="							<option value='${fn:substringBefore(item,\"-\")}'>${fn:substringAfter(item,\"-\")}</option>\n";
							m+="					</c:forEach>\n";
							m+="					\n";
							m+="</select>\n";
						
						}
						else if(row.cnName.contains("多选")||row.remarks.contains("多选")||row.type.toLowerCase().equals("mutiselect"))
						{
							
		
							
							m+="<div height=\"60px\" style=\"display:inline-block\">\n";
							m+="					<td align=\"right\">\n";
							m+="						<input type=\"hidden\" id=\"show_ids\" name=\"show_ids\" value=\"\"/>\n";
							m+="						机构：\n";
							m+="						<INPUT name=\"allOrg\" id=\"allOrg\" type=\"checkbox\"\n";
							m+="							style=\"padding: 0px; margin-top: 0px;\" value=\"1\"/>\n";
							m+="						<label for=\"allOrg\">全省&nbsp;</label>\n";
							m+="						</td>\n";
							m+="						<td>\n";
							m+="						<input type=\"text\" class=\"input_hkl02\" placeholder=\"选择机构\"\n";
							m+="							readonly id=\"show_names_edit\" name=\"show_names_edit\" size=\"60\" />\n";
							m+="						<INPUT id=\"show_names_btn\" type=\"button\" style=\"display: none;\"\n";
							m+="							class=\"btn btn-default\" value=\"选择机构\">\n";
							m+="						</td>\n";
							m+="					</div>\n";
							
							new MutiSelect();
						
						}
						else
						{
						
							m+="\n<font size=\"2\" color=\"\">"+row.cnName.replaceAll("", "")+":</font>";
							m+="<input type=\"text\" class=\"input-text wid400 bg\" id=\""+row.enName.toLowerCase()+"\" name=\""+row.enName.toLowerCase()+"\" value=\"${ "+row.enName.toLowerCase()+"}\"/>\n";
						
						}
						
					}
					i++;
				}
			}

		}
		

		
		m+="	\n<input type=\"button\" value=\"查询\" name = \"btn_search\" onmouseover=\"this.style.cursor='hand'\" style=\"width:50px;height:20px;font-size:12px;\" class=\"subBtn\" onclick=\"search()\">\n";
		
		m+="	\n<input type=\"button\" value=\"新增\" name = \"btn_search\" onmouseover=\"this.style.cursor='hand'\" style=\"width:50px;height:20px;font-size:12px;\" class=\"subBtn\" onclick=\"toAdd()\">\n";
		m+="</div>\n";
		
		
		m+="	\n";
		m+="	</div>\n";
		m+="	<div id=\"signContent\">\n";
		m+="	  <div class=\"table-list lr10\">\n";
		m+="	      <table width=\"100%\">\n";
		m+="	      <tr>\n";
		
		m+="	        <td style=\"vertical-align: top;\">\n";
		m+="	        <table id=\"newtable\" width=\"100%\">\n";
		m+="	          <thead class=trhead id=\"tblHeader\">\n";
		m+="	            \n";
		m+="				<tr> ";
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
				
					if(row.remarks.toLowerCase().contains("key"))
					{
						
					}else
					{
						m+="				<th  style=\"text-align:center;\">"+row.cnName.replaceAll("", "")+"</th>\n";
						
					}
					i++;
				}
			}

		}
	
		
		
		m+="				<th  style=\"text-align:center;\">操作</th></tr>\n";
		m+="				</thead>\n";
		m+="				<tbody id=\"records\">\n";
		m+="			    </tbody>\n";
		m+="		</table>\n";
		m+="	       </td>\n";
		m+="	      </tr>\n";
		m+="	      </table>\n";
		m+="	</div>\n";
		m+="	<div id=\"pageContent\"></div>\n";
		m+="  </div>\n";
		m+="	\n";
		m+="</body>\n";
		m+="</html>\n";

		
		
		

		
		
		
	
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/webManager", interfaceBean.enName.toLowerCase(), "jsp", m);
		System.out.println(m);
	}
	
	

	
	
	
	
	public boolean isCommonType(String type) {
		if (type.equals("String") || type.equals("int") || type.equals("long")||type.equals("float")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNum(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	
}





