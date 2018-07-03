package com.compoment.addfunction.webmanage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class AddJsp {

	
	public AddJsp(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		for (InterfaceBean interfaceBean : interfaceBeans) {
			
			addJspStruct1(interfaceBean, "Respond");
		}
	}
	
	public void addJspStruct1(InterfaceBean interfaceBean ,String type)
	{
		
		String m="";
		
		m+="<%@ page contentType=\"text/html; charset=GBK\" pageEncoding=\"utf-8\"%>\n";
		m+="<%@taglib uri=\"/WEB-INF/tld/struts-logic.tld\" prefix=\"logic\"%>\n";
		m+="<%@taglib uri=\"/WEB-INF/tld/struts-bean.tld\" prefix=\"bean\"%>\n";
		m+="<%@taglib uri=\"/WEB-INF/tld/struts-html.tld\" prefix=\"html\"%>\n";
		//m+="<%@page import=\"com.gdpost.cbp.basedata.sellerman.bm.SellerManSubVo\"%>\n";
		m+="<%@ include file=\"/gdpost/common/taglibs.jsp\"%>\n";
		m+="<%@ include file=\"/gdpost/common/meta.jsp\"%>\n";
		m+="<%@ page import=\"org.apache.struts.util.LabelValueBean\"%>\n";

		

		m+="<html>\n";
		m+="	<head>\n";
		m+="		<link href=\"/css/inside.css\" type=\"text/css\" rel=\"stylesheet\">\n";
		m+="		<title>"+interfaceBean.title+"</title>\n";
		m+="		\n";
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
		m+="		\n\n\n";
		m+="<script>\n\n";
		
		
		m+="//保存\n";
		m+="function save()\n";
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
						m+="	var "+row.enName.toLowerCase()+" = document.getElementsByName(\""+row.enName.toLowerCase()+"\")[0].value;//"+row.cnName+"\n";
						
						m+="	if("+row.enName.toLowerCase()+" == \"\")\n";
						m+="	{\n";
						m+="		alert(\""+row.cnName+"不能为空！\");\n";
						m+="		return;\n";
						m+="	}\n";
						
						
						parm+=row.enName.toLowerCase()+"+\"|\"";
					}
					i++;
				}
			}

		}
		
		
	

	
		m+="	var parm ="+parm+";\n";

		m+="	var mform = document.forms[0];\n";
		m+="	mform.action=\"/gd/sellermanaction.do?method=add&parm=\"+parm;\n";
		m+="	\n";
		m+="	mform.submit();\n";
		
		m+="}\n\n\n";
		
		
		
		
	
		m+="//树形列表\n";
		m+="function createbrchtree(index)\n";
		m+="		{\n";
		m+="			var tree1 = new WebFXTree(\"Root\");\n";
		m+="   			tree1.add(new WebFXLoadTreeItem(\"机构列表\", \"/gd/tree.do?method=getSubBrchNodes&treename=\"+index)); \n";
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
		m+="   				tree1.add(new WebFXLoadTreeItem(\"机构列表\", \"/gd/tree.do?method=getSubBrchNodes&treename=\"+rowi)); \n";
		m+="   				tree1.showRootNode = false; \n";
		m+="   				tree1.showRootLines = false; \n";
		m+="				treetd.innerHTML = CreateTreeSelectHtml(rowi,tree1,\"\",\"\",200);\n";
		m+="				//setTreeSelValue(rowi);\n";
		m+="		    \n";
		m+="		}\n\n\n";

		m+="</script>\n\n";
		
		
		
		
		
		m+="	</head>\n";
		m+="	<body onload=\"\">\n";
		m+="		<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n";
		m+="			<tr>\n";
		m+="				<td class=\"tit_info3\">  \n";
		m+="					"+interfaceBean.title+" \n";
		m+="				</td>\n";
		m+="			</tr>\n";
		m+="		</table>\n";
		
		
		m+="		<html:form action=\"/gd/sellermanaction.do?method=add\" styleId=\""+interfaceBean.enName+"ActionForm\" method=\"post\" >\n";
		m+="		\n";
		m+="			<table align=\"center\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"tab_mid\" style=\"width:600px\"  id=\"edittable\">\n";
		
		
		
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
						m+="								<th class=\"tab_td2\" width=\"10%\">\n";
						m+="									"+row.cnName+":\n";
						m+="								</th>\n";
						m+="								<td nowrap class=\"tab_td\" width=\"20%\">\n";
						m+="									<!--<html:select name=\""+interfaceBean.enName+"ActionForm\"\n";
						m+="										property=\""+row.enName.toLowerCase()+"\">\n";
						m+="										<html:option value=\"\">全部</html:option>\n";
						m+="										<html:optionsCollection name=\""+interfaceBean.enName+"ActionForm\"\n";
						m+="											property=\""+row.enName.toLowerCase()+"list\" />\n";
						m+="									</html:select>-->\n";

						m+="									<html:text name=\""+interfaceBean.enName+"ActionForm\" size=\"30\"\n";
						m+="										property=\""+row.enName.toLowerCase()+"\" maxlength=\"20\"></html:text>\n";
						
						m+="								</td>";
					if(columnCount==2)
					{
						m+="							</tr>\n";
						columnCount=0;
					}
					columnCount++;
					}
					i++;		
				}
			}
		}
		
		
		
		m+="					\n";
		m+="					<tr>\n";
		m+="							<td  colspan=\"4\" class=\"tab_td\" align=\"center\" >\n";
		m+="							<input type=\"button\" value=\"保存\" name=\"btn\" \n";
		m+="								onmouseover=\"this.style.cursor='hand'\" class=\"btn\" \n";
		m+="								onclick=\"save()\" style=\"margin-right:50px\"/>\n";
		m+="							<input class=\"btn\" type=\"button\" onmouseover=\"this.style.cursor='hand'\"\n";
		m+="								onclick=\"history.go(-1)\"\n";
		m+="								value=\"返回\" style=\"margin-right:50px\"/>\n";
		m+="							</td>\n";
		m+="					</tr>\n";
		m+="					\n";
		m+="					\n";
		m+="			</table>\n";
		m+="			\n";
	
		m+="							\n";
		m+="		</html:form>\n";
		m+="		<script language=\"javascript\">           \n";
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

	
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/webManager",interfaceBean.enName+"AddJsp", "jsp", m);
		System.out.println(m);
		
	}
	

}





