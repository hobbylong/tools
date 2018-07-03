package com.compoment.addfunction.web.jspServlet;

/**
 * 微信端报表
 * */
public class report {

	public void report()
	{
		String m="";
		m+="<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"\n";
		m+="	isELIgnored=\"false\"%>\n";
		m+="<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\"%>\n";
		m+="<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\"	prefix=\"fn\"%>\n";
		m+="<%\n";
		m+="  	request.setCharacterEncoding(\"UTF-8\") ; \n";
		m+="%>\n";
		m+="<!DOCTYPE html>\n";
		m+="<html> \n";
		m+="<head>\n";
		m+="    <meta charset=\"utf-8\">\n";
		m+="    <meta name=\"viewport\" content=\"width=device-width, user-scalable=no,initial-scale=1\">\n";
		m+="    <title>title</title>\n";

		m+="<script src=\"${pageContext.request.contextPath}/jquery-2.1.0.min.js\" type=\"text/javascript\"></script>\n";
		m+="<script src=\"${pageContext.request.contextPath}/jquery.mobile.custom.min.js\" type=\"text/javascript\"></script>\n";



		m+="</head>\n";
		m+="<body style=\"background-color: transparent; min-width: 80px;padding: 0;margin: 0;color: #666;display: block;\">\n";
		m+="    <div data-role=\"page\" id=\"id-getBlanceInsData-page\" data-cache=\"never\" style=\"background-color: transparent\">\n";


		m+="        <div role=\"main\"  style=\"padding: 4px 4px 0px 4px\">\n";
		m+="            <table  id=\"financial-table\"  style=\"border-collapse: separate;border-spacing: 2px;\" >\n";
		m+="                <thead>\n";
		m+="                <tr >\n";
		m+="                    <th  style=\"text-align: left;background-color: #e9f8e4;color: #55a07b;padding: 9px 5px 9px 5px;\">机构名称</th>\n";
		m+="                    <th  style=\"text-align: center;background-color: #e9f8e4;color: #55a07b;padding: 9px 5px 9px 5px;\">年增</th>\n";
		m+="                    <th  style=\"text-align: center;background-color: #e9f8e4;color: #55a07b;padding: 9px 5px 9px 5px;\">年同比</th>\n";
		m+="                    <th  style=\"text-align: center;background-color: #e9f8e4;color: #55a07b;padding: 9px 5px 9px 5px;\">月增</th>\n";
		m+="                    <th  style=\"text-align: center;background-color: #e9f8e4;color: #55a07b;padding: 9px 5px 9px 5px;\">月同比</th>\n";
		m+="                    <th  style=\"text-align: center;background-color: #e9f8e4;color: #55a07b;padding: 9px 5px 9px 5px;\">日增</th>\n";
		m+="                </tr>\n";
		m+="                </thead>\n";
		m+="                <tbody>\n";
		m+="               \n";
		m+="                    <tr>     \n";
		m+="                        <td style=\"text-align: center;background-color:#e9f8e4; color:#55a07b;;font-size:16px;padding: 9px 5px 9px 5px;\">\n";
		m+="                                	 \"item.day_new_amt\"\n";
		m+="                        </td>\n";
		m+="                       \n";
		m+="                        <td style=\"text-align: center;background-color: #f7f7f7;\">\"item.day_new_amt\"</td>\n";
		m+="                       \n";
		m+="                        <td style=\"text-align: center;background-color: #f7f7f7;\">\"item.day_new_amt\"</td>\n";

		m+="                        <td style=\"text-align: center;background-color: #f7f7f7;\">\"item.day_new_amt\"</td>\n";

		m+="                        <td style=\"text-align: center;background-color: #f7f7f7;\">\"item.day_new_amt\"</td>\n";

		m+="                        <td style=\"text-align: center;background-color: #f7f7f7;\">\"item.day_new_amt\"</td>\n";

		m+="                    </tr>\n";
		m+="               \n";
		m+="                </tbody>\n";
		m+="            </table>\n";

		m+="            \n";
		m+="        </div>\n";
		m+="       \n";
		m+="    </div>\n";
		m+="   \n";
		m+="</body>\n";
		m+="<script type=\"text/javascript\">\n";
		m+="    $('#id-getBlanceInsData-page').on('pagehide', function(event, ui){\n";
		m+="        $(this).remove();\n";
		m+="    });\n";

		m+="    $(document).on('pageshow', function() {\n";
		m+="    	\n";
		m+="        FixTable(\"financial-table\", 1, 350, 450);\n";

		m+="        $(\"#financial-table tbody tr:last-child td\").css({\"background-color\":\"#f7f7f7\"});\n";
		m+="     \n";
		m+="        $(\"#financial-table tbody tr:first-child td\").css({\"background-color\":\"#FFF3E1\"});\n";

		m+="    });\n";

		m+="   \n";
		m+="</script>\n";


		m+="<script type=\"text/javascript\">\n";
		m+="function FixTable(TableID, FixColumnNumber, width, height) {\n";
		m+="	// / <summary>\n";
		m+="	// / 锁定表头和列\n";
		m+="	// / <para> sorex.cnblogs.com </para>\n";
		m+="	// / </summary>\n";
		m+="	// / <param name=\"TableID\" type=\"String\">\n";
		m+="	// / 要锁定的Table的ID\n";
		m+="	// / </param>\n";
		m+="	// / <param name=\"FixColumnNumber\" type=\"Number\">\n";
		m+="	// / 要锁定列的个数\n";
		m+="	// / </param>\n";
		m+="	// / <param name=\"width\" type=\"Number\">\n";
		m+="	// / 显示的宽度\n";
		m+="	// / </param>\n";
		m+="	// / <param name=\"height\" type=\"Number\">\n";
		m+="	// / 显示的高度\n";
		m+="	// / </param>\n";
		m+="	var curWidth = $.mobile.window.width() | $(window).width();\n";
		m+="	//if (width > curWidth) {\n";
		m+="		width = curWidth * 0.98;\n";
		m+="	//}\n";
		m+="	\n";
		m+="	var cnt = $(\"#financial-table\").find(\"tr\").length;\n";
		m+="	if (cnt==0) {\n";
		m+="		return;\n";
		m+="	} else if (cnt <= 9) {\n";
		m+="		height = 38 * cnt+20;\n";
		m+="	}\n";
		m+="	\n";
		m+="	if ($(\"#\" + TableID + \"_tableLayout\").length != 0) {\n";
		m+="		$(\"#\" + TableID + \"_tableLayout\").before($(\"#\" + TableID));\n";
		m+="		$(\"#\" + TableID + \"_tableLayout\").empty();\n";
		m+="	} else {\n";
		m+="		$(\"#\" + TableID).after(\n";
		m+="				\"<div id='\" + TableID\n";
		m+="						+ \"_tableLayout' style='overflow:hidden;height:\"\n";
		m+="						+ height + \"px; width:\" + width + \"px;'></div>\");\n";
		m+="	}\n";
		m+="	$(\n";
		m+="			'<div id=\"' + TableID + '_tableFix\"></div>' + '<div id=\"' + TableID\n";
		m+="					+ '_tableHead\"></div>' + '<div id=\"' + TableID\n";
		m+="					+ '_tableColumn\"></div>' + '<div id=\"' + TableID\n";
		m+="					+ '_tableData\"></div>').appendTo(\n";
		m+="			\"#\" + TableID + \"_tableLayout\");\n";
		m+="	var oldtable = $(\"#\" + TableID);\n";
		m+="	var tableFixClone = oldtable.clone(true);\n";
		m+="	tableFixClone.attr(\"id\", TableID + \"_tableFixClone\");\n";
		m+="	$(\"#\" + TableID + \"_tableFix\").append(tableFixClone);\n";
		m+="	var tableHeadClone = oldtable.clone(true);\n";
		m+="	tableHeadClone.attr(\"id\", TableID + \"_tableHeadClone\");\n";
		m+="	$(\"#\" + TableID + \"_tableHead\").append(tableHeadClone);\n";
		m+="	var tableColumnClone = oldtable.clone(true);\n";
		m+="	tableColumnClone.attr(\"id\", TableID + \"_tableColumnClone\");\n";
		m+="	$(\"#\" + TableID + \"_tableColumn\").append(tableColumnClone);\n";
		m+="	$(\"#\" + TableID + \"_tableData\").append(oldtable);\n";
		m+="	$(\"#\" + TableID + \"_tableLayout table\").each(function() {\n";
		m+="		$(this).css(\"margin\", \"0\");\n";
		m+="	});\n";
		m+="	var HeadHeight = $(\"#\" + TableID + \"_tableHead thead\").height();\n";
		m+="	HeadHeight += 2;\n";
		m+="	$(\"#\" + TableID + \"_tableHead\").css(\"height\", HeadHeight);\n";
		m+="	$(\"#\" + TableID + \"_tableFix\").css(\"height\", HeadHeight);\n";
		m+="	var ColumnsWidth = 0;\n";
		m+="	var ColumnsNumber = 0;\n";
		m+="	$(\"#\" + TableID + \"_tableColumn tr:first th:lt(\" + FixColumnNumber + \")\")\n";
		m+="			.each(function() {\n";
		m+="				ColumnsWidth += $(this).outerWidth(true);\n";
		m+="				ColumnsNumber++;\n";
		m+="			});\n";
		m+="	ColumnsWidth += 2;\n";
		m+="	$(\"#\" + TableID + \"_tableColumn\").css(\"width\", ColumnsWidth);\n";
		m+="	$(\"#\" + TableID + \"_tableFix\").css(\"width\", ColumnsWidth);\n";
		m+="	$(\"#\" + TableID + \"_tableData\").scroll(\n";
		m+="			function() {\n";
		m+="				$(\"#\" + TableID + \"_tableHead\").scrollLeft(\n";
		m+="						$(\"#\" + TableID + \"_tableData\").scrollLeft());\n";
		m+="				$(\"#\" + TableID + \"_tableColumn\").scrollTop(\n";
		m+="						$(\"#\" + TableID + \"_tableData\").scrollTop());\n";
		m+="			});\n";
		m+="	$(\"#\" + TableID + \"_tableFix\").css({\n";
		m+="		\"overflow\" : \"hidden\",\n";
		m+="		\"position\" : \"relative\",\n";
		m+="		\"z-index\" : \"50\",\n";
		m+="		\"white-space\" : \"nowrap\"\n";
		m+="	});\n";
		m+="	$(\"#\" + TableID + \"_tableHead\").css({\n";
		m+="		\"overflow\" : \"hidden\",\n";
		m+="		\"width\" : width,\n";
		m+="		\"position\" : \"relative\",\n";
		m+="		\"z-index\" : \"45\",\n";
		m+="		\"white-space\" : \"nowrap\",\n";
		m+="	    \"background\" : \"white\"\n";
		m+="	});\n";
		m+="	$(\"#\" + TableID + \"_tableColumn\").css({\n";
		m+="		\"overflow\" : \"hidden\",\n";
		m+="		\"height\" : height ,\n";
		m+="		\"position\" : \"relative\",\n";
		m+="		\"z-index\" : \"40\",\n";
		m+="		\"white-space\" : \"nowrap\",\n";
		m+="	    \"background\" : \"white\"\n";
		m+="	});\n";
		m+="	$(\"#\" + TableID + \"_tableData\").css({\n";
		m+="		\"overflow\" : \"scroll\",\n";
		m+="		\"width\" : width,\n";
		m+="		\"height\" : height,\n";
		m+="		\"position\" : \"relative\",\n";
		m+="		\"z-index\" : \"35\",\n";
		m+="		\"white-space\" : \"nowrap\"\n";
		m+="	});\n";
		m+="	if ($(\"#\" + TableID + \"_tableHead\").width() > $(\n";
		m+="			\"#\" + TableID + \"_tableFix table\").width()) {\n";
		m+="		$(\"#\" + TableID + \"_tableHead\").css(\"width\",\n";
		m+="				$(\"#\" + TableID + \"_tableFix table\").width());\n";
		m+="		$(\"#\" + TableID + \"_tableData\").css(\"width\",\n";
		m+="				$(\"#\" + TableID + \"_tableFix table\").width() + 17);\n";
		m+="	}\n";
		m+="	if ($(\"#\" + TableID + \"_tableColumn\").height() > $(\n";
		m+="			\"#\" + TableID + \"_tableColumn table\").height()) {\n";
		m+="		$(\"#\" + TableID + \"_tableColumn\").css(\"height\",\n";
		m+="				$(\"#\" + TableID + \"_tableColumn table\").height());\n";
		m+="		$(\"#\" + TableID + \"_tableData\").css(\"height\",\n";
		m+="				$(\"#\" + TableID + \"_tableColumn table\").height() + 17);\n";
		m+="	}\n";
		m+="	$(\"#\" + TableID + \"_tableFix\").offset(\n";
		m+="			$(\"#\" + TableID + \"_tableLayout\").offset());\n";
		m+="	$(\"#\" + TableID + \"_tableHead\").offset(\n";
		m+="			$(\"#\" + TableID + \"_tableLayout\").offset());\n";
		m+="	$(\"#\" + TableID + \"_tableColumn\").offset(\n";
		m+="			$(\"#\" + TableID + \"_tableLayout\").offset());\n";
		m+="	$(\"#\" + TableID + \"_tableData\").offset(\n";
		m+="			$(\"#\" + TableID + \"_tableLayout\").offset());\n";
		m+="}\n";
		m+="</script>\n";
		m+="</html>\n";

	}
	
	
	public void aa()
	{
		
		String m="";
		m+="<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>\n";
		m+="<%@ taglib prefix=\"fn\" uri=\"http://java.sun.com/jsp/jstl/functions\"%>\n";
		m+="<%@ page import=\"java.util.*\"%> \n";
		m+="<%@ page import=\"java.text.*\"%> \n";

		m+="<!DOCTYPE HTML>\n";
		m+="<html>\n";
		m+="<head>\n";
		m+="<meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n";
		m+="<meta content=\"telephone=no\" name=\"format-detection\" />\n";
		m+="<title></title>\n";
		m+="<link href=\"${pageContext.request.contextPath}/chinapost/salescenter/css/css.css\" rel=\"stylesheet\" type=\"text/css\">\n";
		m+="<script src=\"${pageContext.request.contextPath}/chinapost/salescenter/js/jquery-1.9.1.min.js\"></script>\n";

		m+="<%\n";
		m+="SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd\");\n";
		m+="Calendar cd = Calendar.getInstance();\n";
		m+="String curr = sdf.format(cd.getTime());//获取当前日期\n";
		m+="cd.roll(Calendar.DATE, -7);\n";
		m+="String pre = sdf.format(cd.getTime());//获取当前日期\n";
		m+="%>\n";

		m+="</head>\n";
		m+="<body>\n";
		m+="<input type=\"hidden\"  name=\"appid\" id=\"appid\" value=\"0\" />\n";
		m+="<div class=\"search_box\" onClick=\"$('.lightBox_new').toggle();$('#test').slideToggle()\">\n";
		m+="<div class=\"next\"><p id=\"brch\"></p>\n";
		m+="<span id=\"time\"><span id=\"begin\"/></span>&nbsp;&nbsp;至&nbsp;&nbsp;<span id=\"end\"/></span></span>&nbsp;&nbsp;<span id=\"protypcd\"></span></div>\n";
		m+="</div>\n";
		m+=" \n";
		m+="<div class=\"lightBox_new\"></div>\n";
		m+="<div class=\"slideUp\" id=\"test\"> \n";

		m+="<ul class=\"rank_input\">\n";

		m+=" <li class=\"li_2\">\n";
		m+=" 	<select class=\"new_select\" id=\"instLst\" name=\"instLst\" onchange=\"query_brch('1', $(this))\">\n";
		m+="	    <c:forEach begin=\"1\" end=\"${fn:length(sessionScope.myLstInstId)}\" var=\"i\">\n";
		m+="	    	<option value=\"${sessionScope.myLstInstId[i-1] }\">${ sessionScope.myLstInstNm[i-1] } </option>\n";
		m+="	  </c:forEach>\n";
		m+=" 	</select>\n";
		m+=" </li>\n";
		m+=" \n";


		m+=" <li class=\"li_3\">\n";
		m+="<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"new_table\">\n";
		m+="<tr>\n";
		m+="   <td width=\"45%\"><input id=\"begin_date\"  class=\"rank_date\"  style=\"height:40px; line-height:40px;\" type=\"date\" value=\"<%=pre%>\"/></td>\n";
		m+="   <td  valign=\"middle\" width=\"10%\" align=\"center\"><span class=\"rank_date_2\" style=\" text-align: center;height:40px; line-height:60px;\">至</span></td>\n";
		m+="   <td width=\"45%\"><input id=\"end_date\" class=\"rank_date\"  style=\"height:40px; line-height:40px; float:right;\" type=\"date\" value=\"<%=curr%>\"/></td>\n";
		m+="</tr>\n";
		m+="</table>\n";
		m+="</li>\n";



		m+="<li class=\"li_1\"><span class=\"btn_gray\" onClick=\"$('.lightBox_new').toggle();$('#test').slideToggle()\">关闭</span><span class=\"btn_red\" onclick=\"search()\">搜索</span></li>ß\n";
		m+="</ul>\n";
		m+="</div>\n";
		m+="<input type=\"hidden\" id=\"instNmLst\" value=\"\" />\n";

		m+="<script>\n";
		m+="	$(document).ready(function() {\n";
		m+="		query_brch(\"\", $(\"#instLst\"));\n";
		m+="	\n";
		m+="		// 判断是否显示汇总下拉框\n";
		m+="		if ($(\"#pageType\").val() == \"1\") {\n";
		m+="			$(\"#huizLi\").show();\n";
		m+="		} else {\n";
		m+="			$(\"#huizLi\").hide();\n";
		m+="		}\n";
		m+="		  $(\".ui-loader h1\").html(\"\");\n";
		m+="	});\n";

		m+="	var level = \"0\";\n";
		m+="	function query_brch(type, obj){ \n";
		m+="		$.ajax({  \n";
		m+="		    url:'/jyqy/wx/QyAppReportAction!queryBrch.do',  \n";
		m+="		    type:\"POST\",\n";
		m+="		    cache:false,\n";
		m+="		    dataType:'json', //预期服务器返回的数据类型  \n";
		m+="		    data: { \"type\": type,\"brch\":obj.val()} , \n";
		m+="		    error: function() {\n";
		m+="		    	only_one_sub = true;  ////防重复提交\n";
		m+="				$(\"#show_msg_info\").html(\"查询出错了!!!\"); \n";
		m+="				$(\"#list\").html(\"\"); \n";
		m+="		    },  \n";
		m+="	        success:function(data){\n";
		m+="	        	$(\"#show_msg_info\").html(\"\");\n";
		m+="	        	if (data != null ){\n";
		m+="	        		if(data.no_login == 'yes'){\n";
		m+="	        			window.location.href = '/chinapost/salesman/salesman_login.jsp';\n";
		m+="	            	}\n";
		m+="	            	// 先把下面所有下拉框的删除掉\n";
		m+="		        	obj.parent().nextAll().each(function() {\n";
		m+="		        		if ($(this).find(\"#synInstLst\").size() > 0) {\n";
		m+="		        			$(this).remove();\n";
		m+="		        		}\n";
		m+="		        	});\n";
		m+="		        	// 创建下拉框\n";
		m+="		        	bulidSelect(obj, data);\n";
		m+="		        	// 初次搜索时，保存上级机构的值\n";
		m+="		        	if (type == \"\") {\n";
		m+="		        		var brch = \"\"\n";
		m+="	        			for(var i = 0; i < data.level - 1; i++) {\n";
		m+="							brch = brch + data.lstInstNm[i] + \"  \";\n";
		m+="						}\n";
		m+="						$(\"#instNmLst\").val(brch);\n";
		m+="						// 搜索数据\n";
		m+="		        	\n";
		m+="						\n";
		m+="		        		query_app(\"\",$(\"#app_1\"));\n";
		m+="		        	}\n";
		m+="		        }\n";
		m+="	        }\n";
		m+="	    });\n";
		m+="}\n";
		m+="	\n";
		m+="	\n";
		m+="	\n";
		m+="	\n";
		m+="	\n";
		m+="	\n";
		m+="	function query_class(type, obj){ \n";
		m+="		\n";
		m+="		var agentid=$(\"#app_2\").val();\n";
		m+="		var appOrFunction=$(\"#app_1\").val();\n";
		m+="		$.ajax({  \n";
		m+="		    url:'/jyqy/wx/QyAppReportAction!queryQYAppClass2.do',  \n";
		m+="		    type:\"POST\",\n";
		m+="		    cache:false,\n";
		m+="		    dataType:'json', //预期服务器返回的数据类型  \n";
		m+="		    data:{\"agentid\":agentid},\n";
		m+="		    error: function() {\n";
		m+="		    	only_one_sub = true;  ////防重复提交\n";
		m+="				$(\"#show_msg_info\").html(\"查询出错了!!!\"); \n";
		m+="				$(\"#list\").html(\"\"); \n";
		m+="		    },  \n";
		m+="	        success:function(data){\n";
		m+="	        	$(\"#show_msg_info\").html(\"\");\n";
		m+="	        	if (data != null ){\n";
		m+="	        		if(data.no_login == 'yes'){\n";
		m+="	        			//window.location.href = '/chinapost/salesman/salesman_login.jsp';\n";
		m+="	            	}\n";
		m+="	        		\n";
		m+="	        		\n";
		m+="	        		\n";
		m+="	        		\n";
		m+="	        		obj.parent().nextAll().each(function() {\n";
		m+="		        		if ($(this).find(\"#app_3\").size() > 0) {\n";
		m+="		        			$(this).remove();\n";
		m+="		        		}\n";
		m+="		        	});\n";
		m+="		    		//功能\n";
		m+="		    		if(appOrFunction==2 && data.S_CR_ITEM_CD!=null)\n";
		m+="		    			{\n";
		m+="		    		\n";
		m+="		    		\n";
		m+="		        	// 新的li、select、option标签\n";
		m+="		    		var newLi, newSelect, newOption;\n";
		m+="		    		newLi = $(\"<li>\").attr(\"class\", \"li_2\");\n";
		m+="		    		newSelect = $(\"<select>\").attr(\"class\", \"new_select\").attr(\"name\", \"app_3\").attr(\"id\", \"app_3\");\n";
		m+="		    		newOption = $(\"<option>\").val(\"000\").text(\"请选择功能\");\n";
		m+="		    		newSelect.append(newOption);\n";
		m+="		    		// 产生下拉框列表\n";
		m+="		    		for(var i = 0; i < data.S_CR_ITEM_CD.length; i++) {\n";
		m+="		    			newOption = $(\"<option>\").val(data.S_CR_ITEM_CD[i]).text(data.S_CORP_NM[i]);\n";
		m+="		    			newSelect.append(newOption);\n";
		m+="		    		}\n";
		m+="		    		// 绑定下拉框变化事件\n";
		m+="		    		//newSelect.change(function() {\n";
		m+="		    			//query_app(\"1\", $(this));\n";
		m+="		    		//});\n";
		m+="		    		// 将下拉框放到LI里\n";
		m+="		    		newLi.append(newSelect);\n";
		m+="		    		// 将LI放在上个下拉框的下面\n";
		m+="		    		obj.parent().after(newLi);\n";
		m+="		    			}\n";
		m+="	        		\n";
		m+="		        	\n";
		m+="		        \n";
		m+="		    	\n";
		m+="		        }\n";
		m+="	        }\n";
		m+="	    });\n";
		m+="}\n";
		m+="	\n";
		m+="	\n";
		m+="	\n";
		m+="	\n";

		m+="function search(){\n";
		m+="	var brch = \"\";\n";
		m+="	var selectObj, procodeLabel;\n";
		m+="	\n";
		m+="	brch = $(\"#instNmLst\").val() + $(\"#instLst\").find(\"option:selected\").text() + \"  \";\n";
		m+="	// 循环所有动态下拉框\n";
		m+="	$(\"#synInstLst\").each(function(){\n";
		m+="		if ($(this).val() != \"\") {\n";
		m+="			brch = brch + $(this).find(\"option:selected\").text() + \"  \";\n";
		m+="		}\n";
		m+="	});\n";
		m+="	\n";
		m+="	$('.lightBox_new').hide();$('#test').hide();\n";

		m+="	$(\"#brch\").html(brch);\n";
		m+="	$(\"#begin\").html($(\"#begin_date\").val());\n";
		m+="	$(\"#end\").html($(\"#end_date\").val());\n";
		m+="	if ($(\"#pageType\").val() == \"1\") {\n";
		m+="		$(\"#protypcd\").text($(\"#pro_type\").find(\"option:selected\").text());\n";
		m+="	} else {\n";
		m+="		$(\"#protypcd\").text(\"\");\n";
		m+="	}\n";
		m+="	queryRank(0);\n";
		m+="}\n";
		m+="</script>\n";
		m+="</body>\n";
		m+="</html>\n";












		m+="<%@ page language=\"java\" import=\"java.util.*\" contentType=\"text/html;charset=UTF-8\" isELIgnored=\"false\" %>\n";
		m+="<%@page import=\"java.util.*\"%>\n";
		m+="<%@ page import=\"java.text.*\"%> \n";

		m+="<html>\n";
		m+="<head>\n";
		m+="<meta charset='utf-8'>\n";
		m+="<meta name=\"viewport\" content=\"width=device-width,initial-scale=1, maximum-scale=1 \">\n";
		m+="<meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n";
		m+="<meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n";
		m+="<meta content=\"telephone=no\" name=\"format-detection\" />\n";
		m+="<title>我的业绩</title>\n";
		m+="<link href=\"/chinapost/salescenter/css/css.css\" rel=\"stylesheet\" type=\"text/css\">\n";
		m+="<script src=\"/chinapost/salescenter/js/jquery-1.9.1.min.js\"></script>\n";
		m+="</head>\n";
		m+="<body>\n";





		m+="<ul class=\"list act_list\" id=\"list\">\n";
		m+="</ul>\n";
		m+="<a href=\"#\" onclick=\"gohome()\" class=\"home_2\"></a>\n";
		m+="<br>\n";
		m+="<div id=\"show_msg_info\" style=\"color:red;text-align:center; font-size:18px;\"></div>\n";
		m+="<div id=\"more\" style=\"color:red;text-align:center; font-size:18px;\" onclick=\"query_ajx(1)\"></div>\n";
		m+="<br>\n";
		m+="<br>\n";
		m+="<input type=\"hidden\"  name=\"page_code\" id=\"page_code\" value=\"1\" />\n";

		m+="<script>\n";

		m+="$(document).ready(function() {\n";
		m+="	query_ajx(0);\n";
		m+="});\n";

		m+="var only_one_sub = true; //防重复提交\n";
		m+="function query_ajx(flg){  \n";
		m+="    if(!only_one_sub){\n";
		m+="        return;//防重复提交\n";
		m+="    }  \n";
		m+="    only_one_sub = false;//防重复提交\n";
		m+="    \n";
		m+="    if(flg == 0){//首次查询时\n";
		m+="    	$(\"#list\").html(\"\");\n";
		m+="    	$(\"#page_code\").val(1); \n";
		m+="    }\n";
		m+="    \n";
		m+="  var num=10;//每页10条数据\n";
		m+="    $(\"#more\").html(\"\");\n";
		m+="    $(\"#show_msg_info\").html(\"页面加载中...\");\n";
		m+="    $.ajax({  \n";
		m+="        url:'/jyqy/wx/SalesCenterAction!myMoney.do?',  \n";
		m+="        data: { page_code: $(\"#page_code\").val()} ,  \n";
		m+="        type:\"POST\",\n";
		m+="        cache:false,\n";
		m+="        //async:false, \n";
		m+="        dataType:'json', //预期服务器返回的数据类型  \n";
		m+="        error: function() {\n";
		m+="        	only_one_sub = true;  ////防重复提交\n";
		m+="   			$(\"#show_msg_info\").html(\"查询出错了!!!\"); \n";
		m+="   			$(\"#list\").html(\"\"); \n";
		m+="        },  \n";
		m+="        success:function(data){\n";
		m+="        	$(\"#show_msg_info\").html(\"\");\n";
		m+="        	if (data != null ){\n";
		m+="            	// 判断是否有错误\n";
		m+="            	var errMsg = data.errMsg;\n";
		m+="            	if (errMsg != null && errMsg != \"\") {\n";
		m+="            		//防重复提交\n";
		m+="				    only_one_sub = true;\n";
		m+="            		$(\"#show_msg_info\").html(errMsg);\n";
		m+="            		return;\n";
		m+="            	}\n";
		m+="        		\n";
		m+="            	if(data.no_login == 'yes'){\n";
		m+="            		goLogin();\n";
		m+="            	}\n";
		m+="            	\n";
		m+="    			var page =  $(\"#page_code\").val();\n";
		m+="    			\n";
		m+="    			\n";
		m+="				\n";
		m+="    			\n";
		m+="            	var cnt = data.totalCnt;\n";
		m+="            	if(cnt == 0) {\n";
		m+="                	$(\"#more\").removeAttr('onclick');\n";
		m+="	        		$(\"#more\").html(\"暂无用户购买商品！\");\n";
		m+="	        		$(\"#more\").attr(\"style\",\"color:gray;text-align:center; font-size:18px;\");	\n";
		m+="	        		\n";
		m+="            	} else {\n";
		m+="                	var opt = \"\";\n";
		m+="    				for(var i=0; i < cnt; i++){\n";

		m+="    				}\n";

		m+="    				\n";
		m+="    				$(\"#page_code\").val(Number($(\"#page_code\").val()) + 1);		\n";
		m+="    				\n";
		m+="    				if(cnt >= parseInt(num)){\n";
		m+="		        		$(\"#more\").html(\"显示更多\");\n";
		m+="		        		$(\"#more\").click(function() {\n";
		m+="		        			query_ajx($(\"#page_code\").val());\n";
		m+="		        		})\n";
		m+="		        	}\n";
		m+="		        	\n";
		m+="		        	if(parseInt(page) != 1 && cnt < parseInt(num)){\n";
		m+="		        		$(\"#more\").removeAttr('onclick');\n";
		m+="		        		$(\"#more\").html(\"暂无更多数据！\");\n";
		m+="		        		$(\"#more\").attr(\"style\",\"color:gray;text-align:center; font-size:18px;\");\n";
		m+="		        	} \n";
		m+="	        		$(opt).appendTo($(\"#list\"));\n";
		m+="	        	\n";
		m+="	        	}\n";
		m+="			    only_one_sub = true;  ////防重复提交\n";
		m+="	        }  \n";
		m+="        }\n";
		m+="    });\n";
		m+="}\n";

		m+="function gohome(){\n";
		m+="	window.location.href = '/jyqy/wx/SalesCenterAction!homePage.do?' + 'appid=' + $(\"#appid\").val();\n";
		m+="}\n";

		m+="function goLogin() {\n";
		m+="	window.location.href = '/chinapost/salesman/salesman_login.jsp';\n";
		m+="}\n";

		m+="function fresh(){\n";
		m+="	query_ajx(0);\n";
		m+="}\n";

		m+="/* 重置日期输入框 */\n";
		m+="function reset(){\n";
		m+="	\n";
		m+="	$(\"#list\").html(\"\");\n";
		m+="	$(\"#more\").removeAttr('onclick');\n";
		m+="	$(\"#more\").html(\"暂无更多数据！\");\n";
		m+="	$(\"#more\").attr(\"style\",\"color:gray;text-align:center; font-size:18px;\");\n";
		m+="	\n";
		m+="	$(\"#page_code\").val(1);\n";
		m+="}\n";
		m+="</script>\n";
		m+="</body>\n";
		m+="</html>\n";

	}
}
