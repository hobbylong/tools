package com.compoment.addfunction.webmanage.jspServletMybatis;

import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class MutiSelect {

	public   MutiSelect()
	{
		String m="";
		m+="<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n";
		m+="	pageEncoding=\"UTF-8\"%>\n";
		m+="<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\n";
		m+="<%@ taglib prefix=\"fn\" uri=\"http://java.sun.com/jsp/jstl/functions\"%>\n";
		m+="<%\n";
		m+="String path = request.getContextPath();\n";
		m+="String basePath = request.getScheme()+\"://\"+request.getServerName()+\":\"+request.getServerPort()+path+\"/\";\n";
		m+="%>\n";
		m+="<!DOCTYPE HTML>\n";
		m+="<html>\n";
		m+="<head>\n";
		m+="<title>编辑问卷</title>\n";
		m+="<link href=\"${pageContext.request.contextPath}/css/multiselect/bootstrap/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"	/>\n";
	
		m+="<link href=\"${pageContext.request.contextPath}/css/multiselect/style.css\" rel=\"stylesheet\" type=\"text/css\"	/>\n";
		
		m+="<link href=\"${pageContext.request.contextPath}/css/multiselect/jyyx.css\" rel=\"stylesheet\" type=\"text/css\"	/>\n";

		m+="<link href=\"${pageContext.request.contextPath}/css/multiselect/jquery-ui.min.css\" rel=\"stylesheet\" type=\"text/css\"	/>\n";
		
		m+="<link href=\"${pageContext.request.contextPath}/css/multiselect/zTree3/css/zTreeStyle/zTreeStyle.css\"	type=\"text/css\" rel=\"stylesheet\">\n";

		m+="<script	src=\"${pageContext.request.contextPath}/js/jquery.js\"></script>\n";
		m+="<script	src=\"${pageContext.request.contextPath}/js/multiselect/jquery-ui.min.js\"></script-->\n";


		m+="<script src=\"${pageContext.request.contextPath}/js/multiselect/se.js\"></script>\n";
		m+="<script src=\"${pageContext.request.contextPath}/js/multiselect/common.js\"></script>\n";
		m+="<script	src=\"${pageContext.request.contextPath}/css/multiselect/zTree3/js/jquery.ztree.all.min.js\"></script>\n";

		m+="<style type=\"text/css\">\n";
		m+=".btn {\n";
		m+="	padding: 2px 6px;\n";
		m+="	font-size: 12px;\n";
		m+="}\n";
		m+="</style>\n";

	
		m+="<script type=\"text/javascript\">\n";
		m+="	var zTree;\n";

		m+="	///-------树基础配置--------//   \n";
		m+="	var setting = {\n";
		m+="		check : {\n";
		m+="			enable : true\n";
		m+="		},\n";
		m+="		data : {\n";
		m+="			simpleData : {\n";
		m+="				enable : true\n";
		m+="			}\n";
		m+="		},\n";
		m+="		view : {\n";
		m+="			showIcon : false\n";
		m+="		},\n";
		m+="		callback : {\n";
		m+="			onCheck : zTreeOnCheck,\n";
		m+="			onClick : function(e, treeId, treeNode, clickFlag) {\n";
		m+="				zTree.checkNode(treeNode, !treeNode.checked, true);\n";
		m+="				zTreeOnCheck(e, treeId, treeNode);\n";
		m+="			}\n";
		m+="		}\n";
		m+="	};\n";

		
		
		m+="	$(document).ready(function() {\n";

		m+="//弹框内查询按钮\n";
		m+="		$(\"#dialogQueryBtn\").click(function() {\n";
		m+="			searchNodes();\n";
		m+="		});\n";

		m+="//弹框内编辑框输入监听\n";
		m+="		$(\"#keyword\").keyup(function(event) {\n";
		m+="			if (event.keyCode == 13) {\n";
		m+="				searchNodes();\n";
		m+="			}\n";
		m+="		});\n";

		
		m+="		$(\"#show_names_btn,#show_names_edit\").click(function() {\n";
		m+="			//初始化弹出窗口\n";
		m+="			\n";
		m+="			$(\"#dialog\").dialog({\n";
		m+="				autoOpen : false,\n";
		m+="				modal : true,\n";
		m+="				height : 480,\n";
		m+="				width : 600,\n";
		m+="				closeOnEscape : true,\n";
		m+="				closeText : '',\n";
		m+="				hide : 'blind',\n";
		m+="				show : 'scale'\n";
		m+="			});\n";
		m+="			$(\"#dialog\").dialog('open');\n";
		m+="		});\n";

		m+="	//弹框内完成按钮\n";
		m+="		$(\"#btnOk\").click(function() {\n";
		m+="			var orgIds = \"\";\n";
		m+="			var orgNames = \"\";\n";

		m+="			$(\"#org_checked\").children(\"li\").each(function(index, data) {\n";
		m+="				orgIds += $(data).attr(\"id\") + \",\";\n";
		m+="				orgNames += $(data).attr('name') + \",\";\n";
		m+="			});\n";
	
		m+="			$(\"#show_ids\").val(orgIds);\n";
		m+="			\n";
		m+="			$(\"#show_names_edit\").val(orgNames);\n";
		m+="			$(\"#dialog\").dialog('close');\n";
		m+="		});\n";

		m+="		var asn = 0;\n";

		m+="		$(\"#allOrg\").change(function() {\n";
		m+="			var checked = $(this).is(':checked');\n";
		m+="			if (checked) {\n";
		m+="				disableSelectAllTree();\n";
		m+="			} else {\n";
		m+="				$(\"#show_names_edit\").attr(\"disabled\", false);\n";
		m+="				$(\"#show_names_btn\").attr(\"disabled\", false);\n";
		
		m+="				$(\"#show_ids\").val(\"\");\n";
		m+="				$(\"#show_names_edit\").val(\"\");\n";
		m+="			}\n";
		m+="		});\n";

		m+="		//初始化树数据\n";
		m+="		   treeDataQuery();\n";
		m+="	});//ready function end\n";

		
		m+="	//全取消\n";
		m+="	function disableSelectAllTree() {\n";
		m+="		$(\"#show_names_edit\").attr(\"disabled\", true);\n";
		m+="		$(\"#show_names_btn\").attr(\"disabled\", true);\n";
		m+="		$(\"#allOrg\").attr(\"checked\", true);\n";

		m+="		$(\"#show_ids\").val(\"\");\n";
		m+="		$(\"#show_names_edit\").val(\" \");\n";
		m+="	}\n";


	
		m+="//弹框右侧 已选中Html\n";
		m+="	function checkedHtml(nid, nname) {\n";
		m+="		var result = \"<li id='\"+nid+\"' name='\"+nname+\"' style=\"padding:0px 3px 3px 0px;\">\"\n";
		m+="				+ nname\n";
		m+="				+ \"(\"\n";
		m+="				+ nid\n";
		m+="				+ \")<span onclick=\"delChecked(this)\" title=\"删除\" style=\"float:right;color:blue;cursor:pointer;border-radius:15%; background-color:#efefef;text-align:center;width:20px;\">X</span></li>\";\n";
		m+="		return result;\n";
		m+="	};\n";

		m+="	function zTreeOnCheck(event, treeId, treeNode) {\n";
		m+="		var nid = treeNode.id;\n";
		m+="		var nname = treeNode.name;\n";
		m+="		var select_count = parseInt($(\"#select_count\").val());\n";

		m+="		if (treeNode.checked) {\n";
		m+="			$(\"#select_count\").val(select_count + 1);\n";
		m+="			$(\"#org_checked\").append(checkedHtml(nid, nname));\n";
		m+="		} else {\n";
		m+="			var del_org = \"#org_checked #\" + nid\n";
		m+="			$(del_org).remove();\n";
		m+="			$(\"#select_count\").val(select_count - 1);\n";
		m+="		}\n";
		m+="	};\n";

		m+="//弹框右侧 删除选中\n";
		m+="	function delChecked(obj) {\n";
		m+="		$(obj).parent().remove();\n";

		m+="		var select_count = parseInt($(\"#select_count\").val());\n";
		m+="		$(\"#select_count\").val(select_count - 1);\n";

		m+="		var node_id = $(obj).parent().attr(\"id\");\n";
		m+="		var node = zTree.getNodeByParam(\"id\", node_id);\n";
		m+="		zTree.checkNode(node, !node.checked, true);\n";
		m+="	};\n";

		

		m+="  \n";
		m+="	\n";
		m+="	var code;\n";
		m+="	function setCheck() {\n";
		m+="		zTree, py = $(\"#py\").attr(\"checked\") ? \"p\" : \"\", sy = $(\"#sy\").attr(\n";
		m+="				\"checked\") ? \"s\" : \"\",\n";
		m+="				pn = $(\"#pn\").attr(\"checked\") ? \"p\" : \"\", sn = $(\"#sn\").attr(\n";
		m+="						\"checked\") ? \"s\" : \"\", type = {\n";
		m+="					\"Y\" : py + sy,\n";
		m+="					\"N\" : pn + sn\n";
		m+="				};\n";
		m+="		zTree.setting.check.chkboxType = type;\n";
		m+="		setting.check.chkboxType = {\n";
		m+="			\"Y\" : \"s\",\n";
		m+="			\"N\" : \"ps\"\n";
		m+="		};\n";
		m+="		showCode('setting.check.chkboxType = { \"Y\" : \"' + type.Y + '\", \"N\" : \"'\n";
		m+="				+ type.N + '\" };');\n";
		m+="	}\n";

		m+="	function showCode(str) {\n";
		m+="		if (!code)\n";
		m+="			code = $(\"#code\");\n";
		m+="		code.empty();\n";
		m+="		code.append(\"<li>\" + str + \"</li>\");\n";
		m+="	}\n";

	
		m+="	function searchNodes() {\n";
		m+="		var keywords = $(\"#keyword\").val();\n";
		m+="		var nodes = zTree.getNodesByParamFuzzy(\"name\", keywords, null);\n";
		m+="		if (nodes.length > 0) {\n";
		m+="			zTree.selectNode(nodes[0]);\n";
		m+="		}\n";
		m+="	}\n";
		m+="</script>\n";

		m+="</head>\n";
		m+="<body>\n";
		m+="						<div id=\"dialog\" title=\"选择调查对象机构\"\n";
		m+="							style=\"width: 100%; display: none; padding: 10px 0px;\">\n";
		m+="							<div>\n";
		m+="								<input class=\"input_1 ml20\" name=\"keyword\"\n";
		m+="									style=\"margin-left: 2px;\" size=\"26\" id=\"keyword\" maxlength=\"26\"\n";
		m+="									placeholder=\"请输入调查对象机构名\" value=\"\" type=\"text\"> <input\n";
		m+="									type=\"button\" id=\"dialogQueryBtn\"\n";
		m+="									style=\"background: url(<%=basePath%>images/search.jpg); width: 25px; height: 25px;\"\n";
		m+="									value=\" \"> <span style=\"margin-left: 5px;\">\n";
		m+="									已选调查机构 <input name=\"select_count\" size=\"2\"\n";
		m+="									style=\"text-align: center; border: 0px solid #fff; color: red; font-size: 13px;\"\n";
		m+="									id=\"select_count\" maxlength=\"10\" value=\"0\" type=\"text\">个 <input\n";
		m+="									type=\"button\" id=\"btnOk\" value=\"确定\" class=\"btn btn-default\"\n";
		m+="									style=\"margin-left: 20px;\">\n";
		m+="								</span>\n";
		m+="							</div>\n";
		m+="							<div\n";
		m+="								style=\"width: 250px; height: 390px; padding: 0px; margin-right: 5px; float: left; border: 1px solid #c0c0c0; overflow: scroll;\">\n";
		m+="								<ul id=\"tree_all\" class=\"ztree\"></ul>\n";
		m+="							</div>\n";
		m+="							<div id=\"org_checked\"\n";
		m+="								style=\"width: 230; height: 390px; font-size: 11px; border: 1px solid #c0c0c0; overflow-y: scroll\">\n";
		m+="							</div>\n";
		m+="						</div>\n";

		m+="</body>\n";
		m+="</html>\n";






		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/webManager", "mutiSelect", "jsp", m);
	
	

	


		

	}
}
