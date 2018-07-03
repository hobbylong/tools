package com.compoment.addfunction.webmanage;

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

public class QueryJsp {

	
	public  QueryJsp(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		for (InterfaceBean interfaceBean : interfaceBeans) {
			
			queryjspStruct1(interfaceBean, "Respond");
		}
	}
	
	

	public void queryjspStruct1(InterfaceBean interfaceBean,String type)
	{
		String m="";
		m+="<%@ page contentType=\"text/html; charset=GBK\" pageEncoding=\"utf-8\"%>\n";
		//m+="<%@page import=\"com.gdpost.cbp.basedata.sellerman.bm.SellerManSubVo\"%>\n";
		m+="<%@taglib uri=\"/WEB-INF/tld/struts-logic.tld\" prefix=\"logic\"%>\n";
		m+="<%@taglib uri=\"/WEB-INF/tld/struts-bean.tld\" prefix=\"bean\"%>\n";
		m+="<%@taglib uri=\"/WEB-INF/tld/struts-html.tld\" prefix=\"html\"%>\n";
		//m+="<%@ include file=\"/gdpost/common/taglibs.jsp\"%>\n";
		//m+="<%@ include file=\"/gdpost/common/meta.jsp\"%>\n";
		m+="<%@ page import=\"org.apache.struts.util.LabelValueBean\"%>\n\n\n";

		
		
		//头部
		m+="<html>\n";
		m+="	<head>\n";
		m+="		<link href=\"/css/inside.css\" type=\"text/css\" rel=\"stylesheet\">\n";
		m+="		<title>"+interfaceBean.title+"</title>\n\n";
		
		m+="		<jsp:include flush=\"true\" page=\"/gdpost/common/newstyle.jsp\"></jsp:include>\n";
		m+="		<!-- 树形菜单样式 -->\n";
		m+="		<script type=\"text/javascript\"\n";
		m+="			src=\"/gdpost/pub/combotree/js/xtree2.js\"></script>\n";
		m+="		<script type=\"text/javascript\"\n";
		m+="			src=\"/gdpost/pub/combotree/js/xloadtree2.js\"></script>\n";
		m+="		<script type=\"text/javascript\"\n";
		m+="			src=\"/gdpost/pub/combotree/js/treeselect.js\"></script>\n";
		m+="		<link type=\"text/css\" rel=\"stylesheet\"\n";
		m+="			href=\"/gdpost/pub/combotree/css/xtree2.css\" />\n";
		m+="		<link type=\"text/css\" rel=\"stylesheet\"\n";
		m+="			href=\"/gdpost/pub/combotree/css/treeselect.css\" />\n";
		m+="		<script src=\"/js/validation.js\"></script>\n";
		m+="		\n";
		m+="		<script src=\"/js/default/jquery.toggle.js\" type=\"text/javascript\"></script>\n";
		m+="		<!-- end树形菜单样式 -->\n\n";
		
		
		m+="		<script>\n";
		m+="		<!-- 树形菜单 -->\n";
		m+="		function createbrchtree(index)\n";
		m+="		{\n";
		m+="			var tree1 = new WebFXTree(\"Root\");\n";
		m+="   			tree1.add(new WebFXLoadTreeItem(\"root\", \"/gd/tree.do?method=getSubBrchNodes&treename=\"+index)); \n";
		m+="   			tree1.showRootNode = false; \n";
		m+="   			tree1.showRootLines = false; \n";
		m+="			CreateTreeSelect(index,tree1,\"\",\"\",200);\n";
		m+="		}\n";
		m+="		\n";
		m+="		//点击节点\n";
		m+="		function selNode(id,name,treename)\n";
		m+="		{ \n";
		m+="			var vf=eval(\"document.all.ValueField_\"+treename); \n";
		m+="			var tf=eval(\"document.all.TextField_\"+treename);\n";
		m+="			//alert(id);\n";
		m+="			//alert(treename);\n";
		m+="			//alert(document.getElementsByName('d44_70_citycoe')[treename]);\n";
		m+="			document.getElementsByName('d44_70_dealbrchno')[treename].value = id;\n";
		m+="			document.getElementsByName('d44_70_dealbrchname')[treename].value = name;\n";
		m+="			vf.value= id;\n";
		m+="			tf.value=name;\n";
		m+=" 			var tDiv=document.all[\"TreeDiv_\"+treename];\n";
		m+=" 			tDiv.style.display='none'; \n";
		m+="		}\n";
		m+="		\n";
		m+="		//设置默认选择的值\n";
		m+="		function setTreeSelValue(index)\n";
		m+="		{\n";
		m+="			var value = document.getElementsByName('d44_70_dealbrchno')[index].value;\n";
		m+="			var name = document.getElementsByName('d44_70_dealbrchname')[index].value;\n";
		m+="			setTreeSelectValue(index,value,name);\n";
		m+="		}\n";
		m+="		function changemode()\n";
		m+="		{\n";
		m+="			//var selects = document.getElementsByName(\"d44_70_cityorgan\")(rowi);\n";
		m+="				var rowi = \"0\";\n";
		m+="		    	var tdid = \"treetd\"+rowi;\n";
		m+="		    	\n";
		m+="		    	var treetd = document.getElementById(tdid);\n";
		m+="		    	var tree1 = new WebFXTree(\"Root\");\n";
		m+="   				tree1.add(new WebFXLoadTreeItem(\"root\", \"/gd/tree.do?method=getSubBrchNodes&treename=\"+rowi)); \n";
		m+="   				tree1.showRootNode = false; \n";
		m+="   				tree1.showRootLines = false; \n";
		m+="				treetd.innerHTML = CreateTreeSelectHtml(rowi,tree1,\"\",\"\",200);\n";
		m+="				//setTreeSelValue(rowi);\n";
		m+="		    \n";
		m+="		}\n";
		m+="	\n";
		m+="</script>\n\n";
		
		m+="//起止日期\n";
		m+="<script type=\"text/javascript\" src=\"/gdpost/javascript/setday.js\"></script>\n";
		
		m+="<script>\n\n";
		
		m+="//查询\n";
		m+="function query()\n";
		m+="{\n";
		m+="	\n";
		m+="	var myform = document.forms[0];\n";
		m+="	myform.action=\"/gd/"+interfaceBean.enName.toLowerCase()+"action.do?method=query\";\n";
		m+="	myform.submit();\n";
		m+="}\n\n";
		
		
		m+="//新增 \n";
		m+="function add(){\n";
		m+="	var myform = document.forms[0];\n";
		m+="	myform.action=\"/gd/"+interfaceBean.enName.toLowerCase()+"action.do?method=addJsp\";\n";
		m+="	myform.submit();\n";
		m+="}\n\n";

		

		m+="//删除 \n";
		m+="function del()\n";
		m+="{	\n";
		m+="	if (check()==1) return;\n";
		m+="	if(confirm(\"确定删除吗?\")){\n";
		m+="	var myform = document.forms[0];\n";
		m+="	myform.action=\"/gd/"+interfaceBean.enName.toLowerCase()+"action.do?method=del\";\n";
		m+="	myform.submit();\n";
		m+="	}\n";
		m+="}\n\n";


		m+="//修改 \n";	
		m+="function update(i)\n";
		m+="{\n";
		
		String parm="";
		List<Group> groups = interfaceBean.respondGroups;
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
						parm+="document.getElementsByName('"+row.enName.toLowerCase()+"_table_row')[i].value+\"|\"+";
					}
					i++;
				}
			}

		}
		parm=parm.substring(0, parm.length()-1);
		
		m+="	var parm = "+parm+";\n";
		m+="	var mform = document.forms[0];\n";
		m+="	mform.action=\"/gd/"+interfaceBean.enName.toLowerCase()+"action.do?method=updateJsp&parm=\"+parm;\n";
		m+="	mform.submit();\n";
		m+="}\n\n";
		

		m+="//全选 \n";	
		m+="function allcheck()\n";
		m+="{	\n";
		m+="	var checkbox = document.getElementsByName('checkbox_row_id');\n";
		m+="	var checkall = document.getElementById(\"checkall\");\n";
		m+="	for(var i = 0;i<checkbox.length;i++)\n";
		m+="	{\n";
		m+="		checkbox[i].checked = checkall.checked;\n";
		m+="	}\n";
		m+="}\n\n";
		

		m+="//选中 \n";
		m+="function check() {\n";
		m+="	var rowLength=newtable.rows.length-1;\n";
		m+="	if (rowLength < 1)\n";
		m+="	{\n";
		m+="		alert(\"没有记录，请先查询！\");\n";
		m+="		return 1;\n";
		m+="	}	\n";
		m+="	var checkbox = document.getElementsByName('checkbox_row_id');\n";
		m+="	var check = false;\n";
		m+="	for(i=0;i<rowLength;i++){\n";
		m+="		if (document.getElementsByName('checkbox_row_id')[i].checked == true)\n";
		m+="		{\n";
		m+="			var parm = "+parm+";\n";
		m+="			\n";
		m+="			document.getElementsByName('checkbox_row_id')[i].value =parm;\n";
		m+="			check = true;\n";
		m+="		}\n";
		m+="	}\n";
		m+="	if (check == false)	\n";
		m+="	{\n";
		m+="	alert(\"请选择记录！\");\n";
		m+="	return 1;\n";
		m+="	}\n";
		m+="	return 0;\n";
		m+="}\n\n";
		
		
		


		m+="//详情 \n";
		m+="function detail(index){\n";
		m+="	var parm = "+parm+";\n";
		m+="	var mform = document.forms[0];\n";
		m+="	mform.action=\"/gd/"+interfaceBean.enName.toLowerCase()+"action.do?method=detail&parm=\"+parm;\n";
		m+="	mform.submit();\n";
		m+="}\n\n";

	

		m+="</script>\n";

		m+="<!-- 引入pagebreak.jsp -->\n";
		m+="<jsp:include flush=\"true\" page=\"/gdpost/javascript/pub/pagebreak.jsp\"></jsp:include>\n";
		m+="</head>\n";

		
		
		m+="<body onload=\"repage();\">\n";
		m+="		<html:form action=\"/gd/"+interfaceBean.enName.toLowerCase()+"action.do?method=query\"\n";
		m+="			styleId=\""+interfaceBean.enName+"ActionForm\" method=\"post\">\n";
		m+="			<div id=\"container\" class=\"container\">\n";
		m+="				<div class=\"titleBar\">\n";
		m+="					"+interfaceBean.title+"\n";
		m+="				</div>\n";


		m+="				<div class=\"subTitleBar\">\n";
		m+="					<a href=\"#\" class=\"subTitle expandTitle\">查询条件</a>\n";
		m+="				</div>\n";
		m+="				<div class=\"subContentBar\">\n";
		m+="					<div class=\"formTable\">\n";
		m+="						<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"tab\"\n";
		m+="							id=\"edittable\">\n";



		
		
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=3;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
						if(columnCount==3)
						{
							m+="							<tr>\n";
							columnCount=1;
						}
						m+="								<th width=\"10%\">\n";
						m+="									"+row.cnName+":\n";
						m+="								</th>\n";
						m+="								<td nowrap class=\"tab_td\" width=\"20%\">\n";
						m+="									<%--<html:select name=\""+interfaceBean.enName+"ActionForm\"\n";
						m+="										property=\""+row.enName.toLowerCase()+"\">\n";
						m+="										<html:option value=\"\">全部</html:option>\n";
						m+="										<html:optionsCollection name=\""+interfaceBean.enName+"ActionForm\"\n";
						m+="											property=\""+row.enName.toLowerCase()+"list\" />\n";
						m+="									</html:select>--%>\n\n";

						
						m+="<%--<html:text property=\"d44_70_begindate\" size=\"8\"\n";
						m+="							maxlength=\"8\" onclick=\"setday(this)\"\n";
						m+="							onkeypress=\"return event.keyCode>=48&&event.keyCode<=57\" />～\n";
						m+="<html:text property=\"d44_70_enddate\" size=\"8\"\n";
						m+="							maxlength=\"8\" onclick=\"setday(this)\"\n";
						m+="							onkeypress=\"return event.keyCode>=48&&event.keyCode<=57\" />--%>\n\n";

						
						
						m+="									<html:text name=\""+interfaceBean.enName+"ActionForm\" size=\"30\"\n";
						m+="										property=\""+row.enName.toLowerCase()+"\" maxlength=\"20\"></html:text>\n";
						
						m+="								</td>\n";
					if(columnCount==2)
					{
						m+="							</tr>\n";
					
					}
					columnCount++;
					}
					i++;		
				}
			}
		}
		
		
		m+="							<tr>\n";
		m+="								<td colspan=\"2\" class=\"tab_td\"\n";
		m+="									style=\"text-align: center; width: 120px; padding-left: 15px; padding-right: 15px\">\n";
		m+="									<input type=\"button\" value=\"查询\" name=\"btn\"\n";
		m+="										onmouseover=\"this.style.cursor='hand'\" class=\"btn\"\n";
		m+="										onclick=\"query()\">\n";
		m+="								</td>\n";

		m+="							</tr>\n";

		m+="						</table>\n";
		m+="						\n";
		m+="						<input type=\"hidden\"  name=\"d44_70_dealbrchname\" />\n";
		m+="										<input type=\"hidden\"  name=\"d44_70_dealbrchno\"/>\n";
		m+="					</div>\n";
		m+="				</div>\n";



		m+="				<div class=\"subBar\">\n";
		m+="					<div class=\"subTitleBar\">\n";
		m+="						<a href=\"#\" class=\"subTitle expandTitle\">查询结果</a>\n";
		m+="					</div>\n";
		m+="					<div class=\"subContentBar\">\n";
		m+="						<div class=\"dataTable\">\n";
		m+="							<table align=\"center\" width=\"90%\" border=\"0\">\n";
		m+="								<tr\n";
		m+="									style=\"font-weight: bold; background: transparent; color: red; align: left;\">\n";
		m+="									<td align=\"left\"><%=(String) request.getAttribute(\"optret\") == null\n";
		m+="						? \"\"\n";
		m+="						: (String) request.getAttribute(\"optret\")%></td>\n";
		m+="									<td align=\"right\"></td>\n";
		m+="								</tr>\n";
		m+="							</table>\n";





		m+="							<table id=\"newtable\">\n";

		m+="								<thead class=trhead id=\"tblHeader\">\n";
		m+="									<tr>\n";
		m+="										<th width=\"5%\">\n";
		m+="											<input type=\"checkbox\" id=\"checkall\"\n";
		m+="												onclick=\"allcheck()\">\n";
		m+="											选择\n";
		m+="											<br>\n";
		m+="										</th>\n";
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
						m+="										<th width=\"8%\">\n";
						m+="											"+row.cnName+"\n";
						m+="										</th>\n";
					}
					i++;
				}
			}
		}
		m+="	<th width=\"6%\" >\n";
		m+="						详情修改\n";
		m+="					</th>\n";
		m+="									</tr>\n";
		m+="								</thead>\n";
		
		
		
		
		
		
		m+="								<tbody id=\"records\">\n";
		m+="									<logic:notEmpty name=\""+interfaceBean.enName+"ActionForm\" property=\"redo\">\n";
		m+="										<logic:iterate name=\""+interfaceBean.enName+"ActionForm\" id=\"redo\"\n";
		m+="											property=\"redo\" type=\""+interfaceBean.enName+"Bean\" indexId=\"pc\">\n";
		m+="											<tr class='<%=(pc.intValue() % 2 == 0)\n";
		m+="								? \"even\"\n";
		m+="								: \"odd\"%>'>\n";

		m+="												<td align=\"center\">\n";
		m+="													<input type=\"checkbox\" name=\"checkbox_row_id\"\n";
		m+="														value=\"\" />\n";
		m+="												</td>\n";
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
						m+="												<td>\n";
						m+="													<bean:write name=\"redo\" property=\""+row.enName.toLowerCase()+"\" />\n";
						m+="													<input type=\"hidden\" name=\""+row.enName.toLowerCase().toLowerCase()+"_table_row\"\n";
						m+="														value=\"<bean:write name=\"redo\" property=\""+row.enName.toLowerCase()+"\"/>\" />\n";
						m+="												</td>\n";
					}
					i++;
				}
			}
		}
		
		m+="												<td align=\"center\">\n";
		m+="							<a href=\"#\" name=\"changeinfo\" onclick=\"update(<%=pc.intValue()%>);\">修改</a>\n";
		m+="						</td>\n";

		m+="											</tr>\n";
		m+="											\n";
		m+="										\n";
		m+="										</logic:iterate>\n";
		m+="									</logic:notEmpty>\n";
		m+="								</tbody>\n";
		m+="							</table>\n";







		m+="							<table id=\"mytable\" align=\"center\" style=\"margin-bottom: 5px\"\n";
		m+="								width=\"90%\">\n";
		m+="								<tr style=\"display: block\">\n";
		m+="									<td colspan=\"14\">\n";
		m+="										<div id=\"form1_pageset\"></div>\n";
		m+="									</td>\n";
		m+="								</tr>\n";
		m+="								<tr align=\"center\">\n";
		m+="									<td colspan=\"4\">\n";
		m+="										<input type=\"button\" value=\"增加\" name=\"btn3\"\n";
		m+="											onclick=\"add()\" class=\"btn\"\n";
		m+="											onmouseover=\"this.style.cursor='hand'\"\n";
		m+="											style=\"margin-right: 30px\">\n";
		m+="										<input type=\"button\" value=\"删除\" name=\"btn2\" onclick=\"del();\"\n";
		m+="											class=\"btn\" onmouseover=\"this.style.cursor='hand'\"\n";
		m+="											style=\"margin-right: 30px\" />\n";
		m+="									</td>\n";
		m+="								</tr>\n";

		m+="								<tr>\n";
		m+="									<td colspan=\"10\">\n";
		m+="										<div id=\"form1_pageset\"></div>\n";
		m+="									</td>\n";
		m+="								</tr>\n";

		m+="							</table>\n";

		m+="							<!-- 页码 -->\n";
		m+="							<html:hidden styleId=\"form1_curpage_no_submit\"\n";
		m+="								property=\"b88_imp_num\" />\n";
		m+="							<!-- 每页记录数 -->\n";
		m+="							<html:hidden styleId=\"form1_number_per_page_submit\"\n";
		m+="								property=\"b88_unsend_num\" />\n";
		m+="							<!-- 总记录数 -->\n";
		m+="							<html:hidden styleId=\"form1_totalnum\" property=\"b88_mail_cnt\" />\n";
		m+="							<script type=\"text/javascript\"\n";
		m+="								src=\"/gdpost/javascript/pub/showtip.js\"></script>\n";



		m+="							<%=(String) request.getAttribute(\"errmsg\") == null\n";
		m+="						? \"\"\n";
		m+="						: \"<script language ='JavaScript'>alert('\"\n";
		m+="								+ (String) request.getAttribute(\"errmsg\")\n";
		m+="								+ \"');</script>\"%>\n";
		m+="  </div>\n";
		m+="	</div>\n";
		m+="	</div>\n";
		m+="	</div>\n";
		m+="		</html:form>\n";


		m+="<script language=\"javascript\">           \n";
		m+="			    var rowi = 0;\n";
		m+="		    	var tdid = \"treetd\"+rowi;\n";
		m+="		    	var treetd = document.getElementById(tdid);\n";
		m+="		    	var tree1 = new WebFXTree(\"Root\");\n";
		m+="   				tree1.add(new WebFXLoadTreeItem(\"机构列表\", \"/gd/tree.do?method=getSubBrchNodes&treename=\"+rowi)); \n";
		m+="   				tree1.showRootNode = false; \n";
		m+="   				tree1.showRootLines = false; \n";
		m+="				treetd.innerHTML = CreateTreeSelectHtml(rowi,tree1,\"\",\"\",200);\n";
		m+="				setTreeSelValue(rowi);\n";
		m+="		</script>\n";
		m+="	</body>\n";
		m+="</html>\n";

		
		

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/webManager", interfaceBean.enName+"QueryJsp", "jsp", m);
		System.out.println(m);
	}
	
	

	public void queryjspSpringMVC()
	{
		String m="";
		m+="<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>\n";
		m+="<%@ page session=\"false\" %>\n";
		

		
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





