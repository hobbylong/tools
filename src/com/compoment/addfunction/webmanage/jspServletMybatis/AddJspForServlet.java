package com.compoment.addfunction.webmanage.jspServletMybatis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class AddJspForServlet {

	public AddJspForServlet(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		for (InterfaceBean interfaceBean : interfaceBeans) {
			
			updateJspStruct2(interfaceBean, "Respond");
		}
	}
	
	public void updateJspStruct2(InterfaceBean interfaceBean ,String type)

	{
		List<Group> groups = interfaceBean.respondGroups;
		
		
		
		String m="";
		m+="<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"utf-8\"%>\n";
		m += "<%\n";
		m += "String path = request.getContextPath();\n";
		m += "String basePath = request.getScheme()+\"://\"+request.getServerName()+\":\"+request.getServerPort()+path+\"/\";\n";
		m += "%>\n";
		m+="<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\n";
	
		m+="<%@ taglib prefix=\"fn\" uri=\"http://java.sun.com/jsp/jstl/functions\"%>\n";
		
		m+="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n";
		m+="<html>\n";
		m+="<head>\n";
		m+="<title>"+interfaceBean.title+"</title>\n";
		m+="<link rel=\"stylesheet\" href=\"<%=basePath%>css/admin_style.css\" type=\"text/css\" />\n";
		m+="<script src=\"<%=basePath%>kindeditor/kindeditor.js\" type=\"text/javascript\"></script>\n";
		m+="<script src=\"<%=basePath%>kindeditor/lang/zh_CN.js\" type=\"text/javascript\"></script>\n";
	
		m+="<script type=\"text/javascript\" language=\"javascript\" src=\"<%=basePath%>js/My97DatePicker/WdatePicker.js\"></script>\n";
		m += "	<script type=\"text/javascript\" src=\"<%=basePath%>js/jquery.js\"></script>\n";
		m+="<script type=\"text/javascript\">\n";
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					
					if(row.type.toLowerCase().equals("string")||row.type.equals("字符"))
					{
						if(row.cnName.contains("编辑")||row.remarks.contains("编辑")||row.remarks.toLowerCase().contains("long"))
						{
							
							m+="var "+row.enName.toLowerCase()+"Editor;\n";
							m+="KindEditor.ready(function(K) {\n";
							m+=""+row.enName.toLowerCase()+"Editor = K.create('#"+row.enName.toLowerCase()+"', {\n";
							m+="resizeType : 1,\n";
							m+="allowPreviewEmoticons : false,\n";
							m+="allowImageUpload : true,\n";
							m+="afterBlur:function(){this.sync();},   //关键  同步KindEditor的值到textarea文本框   解决了多个editor的取值问题\n";
							m+="uploadJson : '/kindeditor/jsp/upload_json.jsp',\n";
							m+="fileManagerJson : '/kindeditor/jsp/file_manager_json.jsp',\n";

							m+="items : [\n";
							m+="'source','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',\n";
							m+="'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',\n";
							m+="'insertunorderedlist', '|', ,'media' ,'link']\n";
							m+="});\n";
							m+="});\n";
						}
					}
				}
				}
			}
		
		

		m+="$(document).on('ready', function() {\n";
		m+="	\n";
		
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					
					    if(row.remarks.toLowerCase().contains("key"))
					    {
					    	m+="	var "+row.enName.toLowerCase()+"='${"+row.enName.toLowerCase()+"}';\n";
							m+="	$(\"#"+row.enName.toLowerCase()+"\").val("+row.enName.toLowerCase()+");\n";
					    }else
					    {
						//m+="	var "+row.enName.toLowerCase()+"='${"+row.enName.toLowerCase()+"}';\n";
						//m+="	$(\"#"+row.enName.toLowerCase()+"\").val("+row.enName.toLowerCase()+");\n";
					    }
					
					
					i++;
				}
			}

		}
		
		m+="	\n";
		m+="});\n";
		
		
		m+="	function doSave() {\n";
		m+="	  \n";
		
	
	
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
				
						
						
						m+="		if ($(\"#"+row.enName.toLowerCase()+"\").val() == \"\") {\n";
						m+="			alert(\"请输入"+row.cnName.replaceAll("", "").trim()+"！\");\n";
						m+="			return false;\n";
						m+="		}\n";
						
						if(row.cnName.contains("开始时间")||row.cnName.contains("开始日期"))
						{
							m+="		if($(\"#end\").val()!=\"\" && $(\"#"+row.enName.toLowerCase()+"\").val() > $(\"#end\").val()){\n";
							m+="				alert(\"开始时间不能大于结束时间\");\n";
							m+="				return ;\n";
							m+="		}\n";
						}
						
						if(row.type.toLowerCase().contains("int")||row.type.contains("整形")||row.type.contains("整数"))
						{
							m+="		var "+row.enName.toLowerCase()+"=$('#"+row.enName.toLowerCase()+"').val();\n";
							m+="		if("+row.enName.toLowerCase()+"==\"\" || !/^\\d+$/.test("+row.enName.toLowerCase()+")){  \n";
							m+="	        alert(\""+row.cnName+"必须是正整数!\"); \n";
							m+="	        return false;\n";
							m+="	    }  \n";
							m+="		\n";
						}
					
					i++;
				}
			}

		}
		


		
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					
					if(row.type.toLowerCase().equals("string")||row.type.equals("字符"))
					{
						if(row.cnName.contains("编辑")||row.remarks.contains("编辑")||row.remarks.toLowerCase().contains("long"))
						{
							
							m+="		 if("+row.enName.toLowerCase()+"Editor.count('text')>2000){\n";
							m+="            alert('"+row.cnName+"字数超过限制');\n";
							m+="             return;\n";
							m+="         }\n";
						}
					}
				}
				}
			}
		
		m+="		 myForm.submit();\n";
		m+="	}\n";
		m+="	 \n";
		m+="</script>\n";
		m+="</head>\n";
		m+="<body>\n";
		

		m+=" <form action=\"<%=basePath%>"+interfaceBean.enName+"Servlet?method=doAdd\" method=\"post\" enctype=\"multipart/form-data\" name=\"myForm\">\n";
		m+="	<div style=\"margin-left: 20px;\">"+interfaceBean.title+"</div>\n";
		m+="	<div class=\"table_form lr10\">\n";
		m+="		<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n";
		m+="			<tbody>\n";
		

	
		
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
			
					
					if(row.remarks.toLowerCase().contains("key"))
					{
						m+="<input type=\"hidden\" id=\""+row.enName.toLowerCase()+"\" name=\""+row.enName.toLowerCase()+"\" value=\"${"+row.enName.toLowerCase()+"}\" />\n";
						
					}
						
					
					i++;
				}
			}

		}
		
	int filecount=0;
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
							m+="				 <tr>\n";
							m+="					<td align=\"right\" style=\"width: 120px\"><font color=\"red\">*</font>"+row.cnName.replaceAll("", "")+"：</td>\n";
							m+="					<td><select id=\""+row.enName.toLowerCase()+"\" name=\""+row.enName.toLowerCase()+"\"><option value=\"1\">是</option>\n";
							m+="					<option value=\"0\">否</option></select></td>\n";
							m+="					\n";
							m+="				</tr>\n";
						}else if(row.cnName.contains("日期")||row.cnName.contains("时间")||row.type.toLowerCase().contains("time")||row.type.toLowerCase().contains("date"))
						{
							
							m+="				<tr>\n";
							m+="					<td align=\"right\" style=\"width: 120px\"><font color=\"red\">*</font>"+row.cnName.replaceAll("", "")+"：</td>\n";
							m+="					<td><input id=\""+row.enName.toLowerCase()+"\" name=\""+row.enName.toLowerCase()+"\" value=\"${ "+row.enName.toLowerCase()+"}\" style=\"margin-right:10px;width: 150px\" class=\"Wdate\" \n";
							m+="					 onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})\"/></td>\n";
							m+="				</tr>\n";
						}
						else if(row.cnName.contains("图片")||row.cnName.contains("文件")||row.type.toLowerCase().equals("image")||row.type.toLowerCase().equals("file"))
						{
							filecount++;
							m+="					<tr >\n";
							m+="						<td align=\"right\" style=\"width: 120px\">"+row.cnName.replaceAll("", "")+"：\n";
							m+="						</td>\n";
							m+="						<td>\n";
							m+="							<input type=\"file\" id=\""+row.enName.toLowerCase()+"\" name=\""+row.enName.toLowerCase()+"\"  />\n";
							m+="								\n";
							m+="						</td>\n";
							m+="						\n";
							m+="					</tr>\n";
						}else if(row.cnName.contains("选择")||row.remarks.contains("选择")||row.type.toLowerCase().equals("select"))
						{
							m+="					<tr >\n";
							m+="						<td align=\"right\" style=\"width: 120px\">"+row.cnName.replaceAll("", "")+"：\n";
							m+="						</td>\n";
							m+="						<td>\n";
							
							m+="<select id=\""+row.enName.toLowerCase()+"\" name=\""+row.enName.toLowerCase()+"\" class=\"form-control\" style=\"width: 187px;height:28px;margin-bottom:10px;\">\n";
							m+="				<option value=\"\">请选择</option>\n";
							m+="					<c:forEach var=\"item\" items=\"${"+row.enName.toLowerCase()+"SelectList}\">	\n";
						
						
							m+="							<option value='${fn:substringBefore(item,\"-\")}'>${fn:substringAfter(item,\"-\")}</option>\n";
						
							
							m+="					</c:forEach>\n";
							m+="					\n";
							m+="</select>\n";
							
							m+="						</td>\n";
							m+="						\n";
							m+="					</tr>\n";
						}
						else
						{
						
							if(row.cnName.contains("编辑")||row.remarks.contains("编辑")||row.remarks.toLowerCase().contains("long"))
							{
								m+="	<tr>\n";
								m+="					<td align=\"right\" style=\"width: 120px\">"+row.cnName+"：</td>\n";
								m+="						<td><textarea type=\"text\" style=\"width:400px;height:50px;\"\n";
								m+="							id=\""+row.enName.toLowerCase()+"\" name=\""+row.enName.toLowerCase()+"\" >${"+row.enName.toLowerCase()+"}</textarea></td>\n";
								m+="				</tr>\n";
								
							}else
							{
							
							m+="				<tr>\n";
							m+="					<td align=\"right\" style=\"width: 120px\"><font color=\"red\">*</font>"+row.cnName.replaceAll("", "")+"：</td>\n";
							m+="					<td><input type=\"text\" class=\"input-text wid400 bg\"\n";
							m+="						id=\""+row.enName.toLowerCase()+"\" name=\""+row.enName.toLowerCase()+"\" value=\"${ "+row.enName.toLowerCase()+"}\"/></td>\n";
							m+="					\n";
							m+="				</tr>\n";
							}
							
						}
						
					}
					i++;
				}
			}

		}
		


	
		m+="				<tr height=\"60px\">\n";
		m+="					<td align=\"right\" style=\"width: 120px\"></td>\n";
		m+="					<td><input type=\"button\" value=\"保存\" name=\"btn\"\n";
		m+="						onmouseover=\"this.style.cursor='hand'\" class=\"subBtn\"\n";
		m+="						onclick=\"doSave()\"> <input type=\"button\" value=\"返回\"\n";
		m+="						name=\"btn2\" onmouseover=\"this.style.cursor='hand'\" class=\"subBtn\"\n";
		m+="						onclick=\"history.go(-1)\">\n";
		m+="				</tr>\n";
		m+="			</tbody>\n";
		m+="		</table>\n";
		m+="	</div>\n";
		m+="	</form>\n";
		m+="</body>\n";
		m+="</html>\n";

		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/webManager",interfaceBean.enName.toLowerCase()+"Add", "jsp", m);
		System.out.println(m);
		
	}
	
	
}





