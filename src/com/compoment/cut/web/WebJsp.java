package com.compoment.cut.web;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;

import org.apache.poi.poifs.property.Child;

import com.compoment.cut.CompomentBean;
import com.compoment.ui.ios.creater.ScrollViewCells;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

//http://www.ruanyifeng.com/blog/2015/07/flex-examples.html
//http://www.ruanyifeng.com/blog/2015/07/flex-grammar.html

public class WebJsp {

	        //选择器 1.选择器keyvalue(key中文value数字)数据写死  选择一个保存值到数据库
			//      2.选择器keyvalue数据动态   选择一个保存值到数据库
			//		3.选择器keyvalue数据动态    选择一个另外个选择器跟着变
			//		4.选择器keyvalue数据写死    选择一个另外一个或多个控件隐藏
			//      5.选择器keyvalue数据写死     选择一个另外一个图片控件图片切换 ，文字控件值变化，列表控件值变化，超链接控件变
			
			
			
			//必填项左边标红星
			
			
			
			//按钮      1.保存
			//       2.返回
			//       3.清空  图片，文件选择器
			//       4.加入购物车
			//		 5.确认订单
			//		 6.支付(跳转保存接口或数据库)
			//       7.查询(不跳转查询列表)
			//		 8.新增(跳转保存接口或数据库)
			//		 9.修改(跳转查询填值)
			//       10.奖状 (跳转查询列表)
			//       11.订单(跳转查询列表)
			//       12.地址(跳转查询列表)
			
			
			
			
			
			//图片      1.url写死
			//       2.url来源选择器 ，  文件上传器     
		    //       3.点击图片放大
			
			
			
			
			
			
			
			//文字控件
			//          1.动态
			//			2.写死
			
			
			
			//文字编辑器
			//           1.输入时检查是否为数字
			//           2.输入时检查是否为手机号
			//			 3.输入时检查是否为中文
			//		     4.输入时检查是否为身份证号
			//		     5.保存时检查是否为空
			//           6.默认值
			//           7.输入长度限制
			//			 8.提示语
			//			 9.初始值
			
			
			
			
			//日期选择器
			          //1.开始时间小于结束时间
			
			
			
			
			//文件上传器
		    //1.预览图片
	
	

	String bodym = "\n\n\n";
	String style = "";
	String js = "";
	String connection = "";
	String pageName = "";
	String className = "";

	int rootViewWidth = 320;
	int rootViewHeight = 568;

	CompomentBean newParent;

	String jsString = "";
	String styleString = "";

	String parentChirld = "";
	CompomentBean listItemBean = null;
	boolean haveListView=false;

	public WebJsp(int cellWidth, int cellHeight) {
		rootViewWidth = cellWidth;
		rootViewHeight = cellHeight;
	}

	public WebJsp(String pageName, List<CompomentBean> oldBeans) {
		this.pageName = pageName;
		className = firstCharToUpperAndJavaName(pageName);

		String body = analyse(oldBeans);

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web", className + "Jsp", "jsp", bodym);

		System.out.println(bodym);

		// FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/ios",
		// className
		// + "ViewController", "xib", m);

	}

	public static String firstCharToUpperAndJavaName(String string) {
		// buy_typelist
		String[] ss = string.split("_");
		String temp = "";
		for (String s : ss) {
			if (!s.equals("item"))
				temp += s.substring(0, 1).toUpperCase() + s.substring(1);
		}
		return temp;
	}

	CompomentBean maxBean = null;

	public String analyse(List<CompomentBean> oldBeans) {
		// Collections.sort(oldBeans, comparatorDate);

		int maxW = 0;
		int maxH = 0;
		List<CompomentBean> layouts = new ArrayList<CompomentBean>();

		// 找出容器
		for (CompomentBean bean : oldBeans) {
			if (bean.type.contains("Layout")) {
				if (bean.w >= maxW) {
					maxW = bean.w;
					maxBean = bean;
				}

				if (bean.h >= maxH) {
					maxH = bean.h;
					maxBean = bean;
				}

				layouts.add(bean);
			}
		}

		// 2.修正属于哪个父亲
		newParent = maxBean;
		parentModifyParent(maxBean);

		Collections.sort(layouts, comparatorDate);
		// changePosition(maxBean);

		bodym += "<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>\n";
		bodym += "<%\n";
		bodym += "String path = request.getContextPath();\n";
		bodym += "String basePath = request.getScheme()+\"://\"+request.getServerName()+\":\"+request.getServerPort()+path+\"/\";\n";
		bodym += "%>\n";
		bodym += "<!DOCTYPE html>\n";
		bodym += "<html>\n";

		bodym += "<head>\n";
		bodym += "	<meta charset='utf-8'>\n";
		bodym += "	<meta name=\"viewport\" content=\"width=device-width,initial-scale=1, maximum-scale=1\">\n";
		bodym += "	<meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n";
		bodym += "	<meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n";
		bodym += "	<meta Http-Equiv=\"Cache-Control\" Content=\"no-cache\"/>\n";
		bodym += "	<meta Http-Equiv=\"Pragma\" Content=\"no-cache\"/>\n";
		bodym += "	<meta Http-Equiv=\"Expires\" Content=\"-1\"/>\n";

		bodym += "  <link rel=\"stylesheet\" href=\"<%=basePath%>css/frozen.css\">\n";
		bodym += "	<script type=\"text/javascript\" src=\"<%=basePath%>js/jquery.js\"></script>\n";
		bodym += "	<script type=\"text/javascript\" src=\"<%=basePath%>js/jquery.form.js\"></script>\n";
		bodym += "	<script src=\"<%=basePath%>lib/zepto.min.js\"></script>\n";
		bodym += "	<script src=\"<%=basePath%>js/frozen.js\"></script>\n";
		
		bodym += "<link href=\"<%=basePath%>js/calendar_control/mobiscroll.css\" rel=\"stylesheet\" type=\"text/css\">\n";
		bodym += "<script src=\"<%=basePath%>js/calendar_control/mobiscroll.js\" type=\"text/javascript\"></script>\n";
		
	
		bodym+="	\n";
		bodym+="	<script\n";
		bodym+="	src=\"<%=basePath%>js/calendar_control/mobiscroll_002.js\"\n";
		bodym+="	type=\"text/javascript\"></script>\n";
		bodym+="<script\n";
		bodym+="	src=\"<%=basePath%>js/calendar_control/mobiscroll_004.js\"\n";
		bodym+="	type=\"text/javascript\"></script>\n";
		bodym+="<link\n";
		bodym+="	href=\"<%=basePath%>js/calendar_control/mobiscroll_002.css\"\n";
		bodym+="	rel=\"stylesheet\" type=\"text/css\">\n";
		bodym+="<script\n";
		bodym+="	src=\"<%=basePath%>js/calendar_control/mobiscroll_003.js\"\n";
		bodym+="	type=\"text/javascript\"></script>\n";
		bodym+="<script\n";
		bodym+="	src=\"<%=basePath%>js/calendar_control/mobiscroll_005.js\"\n";
		bodym+="	type=\"text/javascript\"></script>\n";
		bodym+="<link\n";
		bodym+="	href=\"<%=basePath%>js/calendar_control/mobiscroll_003.css\"\n";
		bodym+="	rel=\"stylesheet\" type=\"text/css\">\n";
		bodym+="<script src=\"<%=basePath%>js/calendar_control/date.js\"></script>\n";
		bodym+="<link\n";
		bodym+="	href=\"<%=basePath%>js/calendar_control/mobiscroll.css\"\n";
		bodym+="	rel=\"stylesheet\" type=\"text/css\">\n";
		bodym+="<script\n";
		bodym+="	src=\"<%=basePath%>js/calendar_control/mobiscroll.js\"\n";
		bodym+="	type=\"text/javascript\"></script>\n";

		

		bodym += "<script>\n";
		bodym += "//js取request值  var contentWidth = <s:property value=\"#request.cut_img_content_info.contentWidth\"/>;\n";
		bodym += "//html取request值  <input type=\"hidden\"  name=\"busiNo\" id=\"busiNo\" value=\"<s:property value=\"#request.busiNo\"/>\" />\n\n";

		bodym += "//appendjs\n";
		bodym += "</script>\n";

		bodym += "</head>\n";

		bodym += "<body id=\"body\" class=\"h-body\" style=\"display:flex;flex-direction: column;\">\n";

		bodym += "<div id=\"emptyOrErrorMsg\"></div>\n";

		parent(maxBean);

		bodym += "</body>\n";

		bodym += styleString;

		
		bodym += "<script type=\"text/javascript\">\n";
		bodym += "$(document).ready(function(){\n";
		bodym += "     init();\n";
		bodym += "});\n\n";
		
		bodym += "function getUrlParam(name) {\n";
		bodym += "var reg = new RegExp(\"(^|&)\" + name + \"=([^&]*)(&|$)\"); //构造一个含有目标参数的正则表达式对象\n";
		bodym += "var r = window.location.search.substr(1).match(reg); //匹配目标参数\n";
		bodym += "if (r != null)\n";
		bodym += "return unescape(r[2]);\n";
		bodym += "return null; //返回参数值\n";
		bodym += "}\n";
		bodym += "</script> \n\n";
		
		bodym += "<script type=\"text/javascript\">\n";
		bodym += "//body宽高等于窗体\n";
		bodym += " var screenHeight=document.documentElement.clientHeight;\n";
		bodym += " var screenWidth=document.documentElement.clientWidth; \n";
		bodym += "var body = $(\".h-body\");\n";
		bodym += "body.width(screenWidth + \"px\");\n";
		bodym += "body.height(screenHeight + \"px\");\n";
		bodym += " </script> \n";
         
		if(!haveListView)
		{
		this.commonViewNeedValue(maxBean);
		}
		bodym += jsString;

		bodym += "</html>\n";

		bodym = bodym.replace("//appendjs", js);

		return bodym;
	}

	public String getConnection() {
		return connection;
	}

	public void parentModifyParent(CompomentBean bean) {

		Collections.sort(bean.chirlds, comparatorDate);

		// 有 儿子
		if (bean.chirlds != null && bean.chirlds.size() > 0) {

			if (bean.type.equals("ScrollViewLayout")) {

			} else {

				//
				for (CompomentBean chirld : bean.chirlds) {

					// 这个儿子是容器 layout
					if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

						if (chirld.layoutNoUseForIos == true) {// 隐藏
							newParent = bean;
						} else {
							newParent = chirld;
						}

						parentModifyParent(chirld);

					} else {// 这个儿子是非容器

						chirld.parent = newParent;
					}
				}

			}
		}

	}


	public void parent(CompomentBean bean) {

		Collections.sort(bean.chirlds, comparatorDate);

		// 有 儿子
		if (bean.chirlds != null && bean.chirlds.size() > 0) {

			for (CompomentBean chirld : bean.chirlds) {

				// 这个儿子是容器 layout
				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					// visibility:hidden; display:none; height: 42px;
					// line-height: 42px; background-color: #fd5f28; font-size:
					// 18px; color: #fff; text-align: center; z-index: 2;
					// position: relative; width: 100%; position:fixed; top:0;
					// left:0

					String start = "";
					String end = "";

					String relate = "";
					String border = "";

					String expand = "";

					if ("leftLeft".equals(chirld.compomentForWeb)) {
						relate = "justify-content:flex-start;align-items:center;";
					}
					if ("left_Center_Right".equals(chirld.compomentForWeb)) {
						relate = "justify-content: space-between;align-items:center;";
					}
					if ("leftRight".equals(chirld.compomentForWeb)) {
						relate = "justify-content: space-between;align-items:center;";
					}
					if ("left[C e n t e r]Right".equals(chirld.compomentForWeb)) {
						relate = "justify-content:flex-start;align-items:center;";
					}
					if ("centerCenter".equals(chirld.compomentForWeb)) {
						relate = "justify-content:center;align-items:center;";
					}

					if ("rightRight".equals(chirld.compomentForWeb)) {
						relate = "justify-content:flex-end;align-items:center;";
					}

					if ("topBottom".equals(chirld.compomentForWeb)) {
						relate = "width:100%;justify-content:flex-start;";
					}
					if ("topBottomAndCenter".equals(chirld.compomentForWeb)) {
						relate = "width:100%;justify-content:flex-start;align-items:center;";
					}

					if ("boderTop".equals(chirld.compmentBorderForWeb)) {
						border = "border-top: 1px solid " + chirld.rgb16 + ";";
					}

					if ("boderLeft".equals(chirld.compmentBorderForWeb)) {
						border = "border-left: 1px solid " + chirld.rgb16 + ";";
					}

					if ("boderRight".equals(chirld.compmentBorderForWeb)) {
						border = "border-right: 1px solid " + chirld.rgb16 + ";";
					}

					if ("boderBottom".equals(chirld.compmentBorderForWeb)) {
						border = "border-bottom: 1px solid " + chirld.rgb16 + ";";
					}

					if ("boderTopBottom".equals(chirld.compmentBorderForWeb)) {
						border = "border-top: 1px solid " + chirld.rgb16
								+ ";border-bottom: 1px solid \"+chirld.rgb16+\";";
					}

					if ("boderAll".equals(chirld.compmentBorderForWeb)) {
						border = "border: 1px solid " + chirld.rgb16 + ";";
					}

					if ("1bei".equals(chirld.compmentExpandForWeb)) {
						expand = "flex:1;";
					}
					if ("2bei".equals(chirld.compmentExpandForWeb)) {
						expand = "flex:2;";
					}
					if ("3bei".equals(chirld.compmentExpandForWeb)) {
						expand = "flex:3;";
					}

					if (chirld.type.equals("DivLayout_Horizon") || chirld.type.equals("HeaderLayout_Horizon")
							|| chirld.type.equals("FooterLayout_Horizon")) {
						String classString = "";
						if (chirld.type.contains("Header")) {
							classString = "h-header";
						} else if (chirld.type.contains("Footer")) {
							classString = "h-footer";
						}

						start += "<div id=\"" + chirld.enname + "\" class=\"" + classString
								+ "\"  style=\"background-color:" + chirld.bgRgb16 + ";display:flex;flex-direction:row;"
								+ relate + border + expand + "\" >\n";

						end += "  </div>\n";
					}

					else if (chirld.type.equals("DivLayout_Vertical") || chirld.type.equals("HeaderLayout_Vertical")
							|| chirld.type.equals("FooterLayout_Vertical")) {

						String classString = "";
						if (chirld.type.contains("Header")) {
							classString = "header";
						} else if (chirld.type.contains("Footer")) {
							classString = "footer";
						}

						start += "<div id=\"" + chirld.enname + "\" class=\"" + classString
								+ "\" style=\"background-color:" + chirld.bgRgb16
								+ ";display:flex;flex-direction:column;" + relate + border + expand + "\" >\n";

						end += "  </div>\n";
					}

					else if (chirld.type.equals("FormLayout")) {
						
						start += "\n\n<div   style=\"margin:5px 5px;display:none;\" id=\"tip-info\">\n";
						start += "<button  onclick=\"$('#tip-info').css('display','none')\">\n";
						start += "<span aria-hidden=\"true\">&times;</span>\n";
						start += "</button>\n";
						start += "<span></span>\n";
						start += "</div>\n";
						start += "<form id=\"" + chirld.enname + "\"  enctype=\"multipart/form-data\" method=\"post\" action=\"${pageContext.request.contextPath}/___\" class=\"" + ""
								+ "\" style=\"background-color:" + chirld.bgRgb16
								+ ";display:flex;flex-direction:column;" + relate + border + expand + "\" >\n";
						start += "<input type=\"hidden\" name=\"formBeanJson\" id=\"formBeanJson\" value=\"\"/>\n";
						
						end += "<input type=\"submit\" value=\"确定\"/>\n"; 
						end += "  </form>\n";

						
						
						jsString += "\n<script type=\"text/javascript\">\n";
						jsString += "//表单提交\n";
						jsString+="var form = $(\"#"+chirld.enname+"\");\n";
						jsString+="form.ajaxForm({\n";
						jsString+="beforeSubmit : function() {\n";
						
						if (chirld.chirlds != null && chirld.chirlds.size() > 0) {
							WebJspFormItem webJspFormItem = new WebJspFormItem();
							String itemString=webJspFormItem.analyse(chirld);
							jsString+=itemString;
						}
							
								
						jsString+="	},\n";
						jsString+="dataType : \"json\",\n";
						jsString+="	success : function(data) {\n";
						jsString+="	if (data.status == \"true\") {\n";
						jsString+="	$(\"#tip-info\").removeClass(\"alert-danger\").addClass(\"alert-danger\").css(\"display\", \"block\");\n";
					    jsString+="	$(\"#tip-info > span\").text(data.info);\n";	
						jsString+="	}\n";
						jsString+=" }\n";
						jsString+="	});\n";
						jsString += "</script>\n";
				
						
					} else if (chirld.type.equals("Form_ItemLayout")) {

						start += " <div id=\"" + chirld.enname + "\"  class=\"ui-form-item ui-border-b\">\n";

						end += "  </div>\n";
					}

					else if (chirld.type.equals("ListLayout")) {
						haveListView=true;
						start += "<ul id=\"" + chirld.enname
								+ "\" class=\"h-list ui-border-tb\" style=\"flex:1;padding:10px;overflow-y:scroll\">\n";

						end += "  </ul>\n";

						if (chirld.chirlds != null && chirld.chirlds.size() > 0) {
							WebJspListViewItem webListItemJsp = new WebJspListViewItem();
							String itemString=webListItemJsp.analyse(chirld);

							jsString += "\n<script type=\"text/javascript\">\n";
							jsString += "// 分页\n";

							jsString += "window.resultTotal=0;\n";
							jsString += "window.pageSize=10;\n";
							jsString += "window.currentPage=1;\n";
							jsString += "window.requestIng=false;\n";
						
							jsString += " $(\".h-list\").scroll(function(){\n";
							jsString += " var list = $(\".h-list\");\n";
							jsString += "var listScrollTop = list.scrollTop();//滚动条下滚多少==内容隐藏部分多少\n";
							jsString += "var listScrollHeight = list[0].scrollHeight;//内容总高度\n";
							jsString += "var listClientHeight = list[0].clientHeight;//可见区域高度\n";
							jsString += "var listOffsetHeight = list[0].offsetHeight;//可见区域高度(包含边框高度)\n";
							jsString += "if(listScrollTop + listClientHeight == listScrollHeight){\n";

							jsString += "if(requestIng==false)\n";
							jsString += "{\n";
							jsString += " alert(\"\");\n";
							jsString += "getListData(false);\n";
							jsString += "}\n";
							jsString += "}\n";
							jsString += "});\n";

							/*
							 * { "resultCode": "1001", "resultSize": 1, "resultTotal":10, "resultData": [ {
							 * "name": "bootstrap-table", "stargazers_count": "526", "forks_count": "122",
							 * "description":
							 * "An extended Bootstrap table with radio, checkbox, sort, pagination, and other added features. (supports twitter bootstrap v2 and v3) "
							 * } ] }
							 */
							jsString += " function getListData(isInitQuery) {\n";
							jsString += "requestIng=true;\n";
							jsString += "if(isInitQuery)\n";
							jsString += "{\n";
							jsString += "	window.currentPage=1;\n";
							jsString += "$(\".h-list\").find(\"li\").remove(); \n";
							jsString += " }\n";
							jsString += "$.ajax({\n";
							jsString += "url:url,\n";
							jsString += "type:'post',\n";
							jsString += "dataType:'json',\n";
							jsString += "async:true,\n";
							jsString += "data:{pageNo:currentPage,pageSize:pageSize},\n";
							jsString += "timeout:1000,\n";
							jsString += "error:function(){\n";
							jsString += "requestIng=false;\n";
							jsString += "alert(\"ajax error出错\");\n";
							jsString += "}, \n";
							jsString += "success:function(rsObj)\n";
							jsString += "{\n";
							jsString += "requestIng=false;\n";
							jsString += "var resultSize=rsObj.resultSize;\n";
							jsString += "window.resultTotal=rsObj.resultTotal;\n";
							jsString += "var resultData=rsObj.resultData;\n";
							jsString += "if(resultSize>0){\n";
							jsString += "for(var i=0;i<resultData.length;i++){ \n";
							
							jsString +="var itemHtml='';\n";
							jsString +=itemString;
							jsString += " $(\".h-list\").append(itemHtml); \n";
							jsString += " }\n";
							jsString += " }\n";
							jsString += " currentPage++;\n";
							jsString += " }\n";
							jsString += "});\n";
							jsString += "}\n";
							jsString += "</script>\n";
						}
					}

					else if (chirld.type.equals("List_ItemLayout_Horizon")) {

						start += " <li id=\"" + chirld.enname
								+ "\" class=\"ui-border-t\" style=\"display:flex;flex-direction:row;width: 100%; "
								+ relate + border + "\" onclick=\"\">\n";

						end += "  </li>\n";
					} else if (chirld.type.equals("List_ItemLayout_Vertical")) {

						start += " <li id=\"" + chirld.enname
								+ "\" class=\"ui-border-t\" style=\"display:flex;flex-direction:column;width: 100%; "
								+ relate + border + "\" onclick=\"\">\n";

						end += "  </li>\n";
					}

					else if (chirld.type.equals("DialogLayout")) {

						start += "<div id=\"" + chirld.enname + "\"  class=\"ui-dialog\">\n";
						start += "<div class=\"ui-dialog-cnt\">\n";

						start += " <header class=\"ui-dialog-hd ui-border-b\">\n";
						start += "<h3>新手任务</h3>\n";
						start += "<i class=\"ui-dialog-close\" data-role=\"button\"></i>\n";
						start += "</header>\n";

						start += " <div class=\"ui-dialog-bd\">\n";
						start += " <h4>标题标题</h4>\n";
						start += "<div>开通年费QQ会员即可领取欢乐斗地主感恩节回馈礼包！</div>\n";
						start += "</div>\n";

						start += " <div class=\"ui-dialog-ft\">\n";
						start += " <button type=\"button\" data-role=\"button\">取消</button>\n";
						start += " <button type=\"button\" data-role=\"button\">确定</button>\n";
						start += "</div>\n";

						end += "</div>\n";
						end += "</div>\n";
						jsString += "\n<script class=\"demo-script\">\n";
						jsString += "$(\".ui-dialog\").dialog(\"show\");\n";
						jsString += "</script>\n";

					} else if (chirld.type.equals("TabLayout")) {

						start += "<div id=\"" + chirld.enname + "\" class=\"h-tabbar\" style=\"background-color:"
								+ chirld.bgRgb16 + ";display:flex;flex-direction:row;" + relate + border + expand
								+ "\" >\n";

						end += "  </div>\n";

						jsString += "\n<script type=\"text/javascript\">\n";
						jsString += "//tabbar\n";
						jsString += " $(function () {\n";
						jsString += "     var collection = $(\".h-tabbar\").children();\n";
						jsString += "  $.each(collection, function () {\n";
						jsString += "     $(this).addClass(\"tabBtn\");\n";
						jsString += " });\n";
						jsString += " });\n";
						jsString += " //单击事件\n";
						jsString += " function tabBtnPress(btn) {\n";
						jsString += "   var collection = $(\".h-tabbar\").children();\n";
						jsString += " $.each(collection, function () {\n";
						jsString += "    $(this).removeClass(\"tabBtnPress\");\n";
						jsString += "  $(this).addClass(\"tabBtn\");\n";
						jsString += " });\n";
						jsString += " $(btn).removeClass(\"tabBtn\");\n";
						jsString += " $(btn).addClass(\"tabBtnPress\");\n";
						jsString += " }\n";
						jsString += "</script>\n";

						styleString += "<style>\n";
						styleString += ".tabBtn\n";
						styleString += " {\n";
						styleString += " cursor: pointer;\n";
						styleString += "  border-bottom: 2px solid transparent;\n";
						styleString += "}\n";
						styleString += ".tabBtnPress\n";
						styleString += " {\n";
						styleString += " cursor: pointer;\n";
						styleString += "color: #00a5e0;\n";
						styleString += "background: #ffffff;\n";
						styleString += "border-bottom: 2px #00a5e0 solid;\n";
						styleString += "}\n";
						styleString += "</style>\n";

					} else if (chirld.type.equals("SliderLayout")) {// 轮播
						start += "<div id=\"" + chirld.enname + "\" class=\"" + chirld.enname + "Style\" >\n";

						end += "  </div>\n";

						start += "<div class=\"ui-slider\" id=\"slider1\">\n";
						start += "<ul class=\"ui-slider-content\" style=\"width: 300%\">\n";
						start += "<li><span style=\"background-image:url(http://placeholder.qiniudn.com/640x200)\"></span></li>\n";
						start += "<li><span style=\"background-image:url(http://placeholder.qiniudn.com/640x200)\"></span></li>\n";

						end += "</ul>\n";
						end += "</div>\n";

					} else if (chirld.type.equals("ActionSheetLayout")) {

						start += "<div class=\"ui-actionsheet\">\n";
						start += "<div class=\"ui-actionsheet-cnt\">\n";

						end += "</div>\n";
						end += "</div>\n";
						end += "<script type=\"text/javascript\">\n";
						end += "$('.ui-actionsheet').addClass('show');\n";
						end += "</script>\n";
					}

					bodym += "\n" + start;

					parent(chirld);

					bodym += end + "\n";
				} else {// 这个儿子是非容器

					// if (bean.compomentForWeb.equals("tr")) {
					// bodym +=
					// " <td
					// style=\"width:"+((float)1/(float)bean.chirlds.size())*100+"%;
					// text-align: center;\">\n";
					// }

					chirld(chirld, bean);

					// if (bean.compomentForWeb.equals("tr")) {
					// bodym += " </td>\n";
					// }
				}

			}

		}

	}

	public void chirld(CompomentBean chirld, CompomentBean parent) {// 这个儿子是非容器

		

		if (chirld.type.equals("Span")) {
			bodym+=Span(chirld,false);
		}

		if (chirld.type.equals("H1-9")) {

			bodym+=H19(chirld,false);
		}

		if (chirld.type.equals("TextView")) {
			
			bodym+=TextView(chirld,false);

		}
		
		if (chirld.type.equals("TextView_MutiLine")) {
			bodym+=TextView_MutiLine(chirld,false);
		}
		
		if (chirld.type.equals("Label")) {

			// h4 label
			bodym+=Label(chirld,false);

		}

		if (chirld.type.equals("Progress")) {
		
			bodym+=Progress(chirld,false);
		}
		if (chirld.type.equals("Loading")) {

			bodym+=Loading(chirld,false);
			jsString += "<script type=\"text/javascript\" class=\"demo-script\">\n";
			jsString += " // var el = $.loading({content:'加载中...'});\n";
			jsString += " // el.on(\"loading:hide\",function(){\n";
			jsString += " //     console.log(\"loading hide\");\n";
			jsString += " // });\n";
			jsString += "</script>\n";

		}

		if (chirld.type.equals("PopTips")) {
			bodym+=PopTips(chirld,false);
		
		}

		if (chirld.type.equals("Selecter")) {

			bodym+=Selecter(chirld,false);

			jsString += "\n<script type=\"text/javascript\">\n";
			jsString+="//select选择\n";
			jsString += "$(\"#"+chirld.enname+"\").change(function () {  \n";
			jsString += "var s = $(this).children('option:selected').val();  \n";
			jsString+="window.where.set(\""+chirld.enname+"\",s);\n";
			jsString += " }); \n"; 
			jsString+="</script>\n";

		}
		
		
		if(chirld.type.equals("DateSelecter"))
		{
			bodym+=DateSelecter(chirld,false);
		}

		if (chirld.type.equals("Button")) {
			bodym+=Button(chirld,false);
		}

		if (chirld.type.equals("Button_Close")) {
			bodym+=Button_Close(chirld,false);
		}
		
		if (chirld.type.equals("Button_Query")) {
			bodym+=Button_Query(chirld,false);
		
			jsString+="<style type=\"text/css\">\n";
			jsString+="			.dis_no {\n";
			jsString+="			    display: none;\n";
			jsString+="			}\n";
			jsString+="			</style>\n";
			jsString+="			<script>\n";
			jsString+="			function query() {\n";
			jsString+="				$(\"#dialog\").toggleClass(\"dis_no\");\n";
			jsString+="				\n";
			jsString+="				var flag = $(\"#dialog\").hasClass(\"dis_no\");\n";
			jsString+="				if (flag) {\n";
			jsString+="					getListData(true);\n";
			jsString+="				}else\n";
			jsString+="				{\n";
			jsString+="					//弹出\n";
			jsString+="				}\n";
			jsString+="			}\n";
			jsString+="		</script>\n";
		
		}
		
		if (chirld.type.equals("Button_A")) {
			bodym+=Button_A(chirld,false);
		}
		

		if (chirld.type.equals("leftArrow")) {
			bodym+=leftArrow(chirld,false);
		}

		if (chirld.type.equals("rightArrow")) {
			bodym+=rightArrow(chirld,false);
		}

		if (chirld.type.equals("CheckBox")) {
			bodym+=CheckBox(chirld,false);
			
			jsString+="\n<script>\n";
			jsString+="//check\n";
			jsString+="function checkWhich()\n";
			jsString+="{\n";
			jsString+="var checkboxs=document.getElementsByName(\"checkbox\");\n";
			jsString+="var count=checkboxs.length;\n";
			jsString+="var chestr=\"\";\n";
			jsString+="for (i=0;i<count;i++)\n";
			jsString+="{\n";
			jsString+="if(checkboxs[i].checked == true)\n";
			jsString+="{\n";
			jsString+="chestr+=checkboxs[i].value+\",\";\n";
			jsString+="}\n";
			jsString+="}\n";
			jsString+="if(chestr == \"\")\n";
			jsString+="{\n";
			jsString+="alert(\"请先选择复选框～！\");\n";
			jsString+="}\n";
			jsString+="}\n\n";
			
			jsString+="function checkAll(){\n";
			jsString+="$(document.getElementsByName(\"checkbox\")).each(function(i){\n";
		    jsString+="  this.checked = true;\n";
			jsString+="})\n";
			jsString+="}\n\n";
			
			jsString+="function unCheckAll(){\n";
			jsString+="$(document.getElementsByName(\"checkbox\")).each(function(i){\n";
		    jsString+="  this.checked = false;\n";
			jsString+="})\n";
			jsString+="}\n";
			
			
			jsString+="</script>\n";

		}

		if (chirld.type.equals("CheckBox_Switch")) {
			bodym+=CheckBox_Switch(chirld,false);
		}

		if (chirld.type.equals("Radio")) {
			bodym+=Radio(chirld,false);

			
			jsString+="\n<script>\n";
			jsString+="var bFlag = false;\n";
		    jsString+="var valueString=\"\";\n";
			jsString+="var gender = document.getElementsByName('radio');\n";
			jsString+="for (var i = 0; i < gender.length; i++) {\n";
			jsString+="if (gender[i].checked) {\n";
			jsString+="bFlag = true;\n";
			jsString+="valueString=gender[i].value;\n";
			jsString+="break;\n";
			jsString+="}\n";
			jsString+="}\n";
			jsString+="if (bFlag == false) {\n";
			jsString+="alert('不能为空，请选择！')\n";
			jsString+="return false;\n";
			jsString+="}\n";
			jsString+="</script>\n";

		}
		
		
		if (chirld.type.equals("File")) {
			bodym+=File(chirld,false);
		}
		

		if (chirld.type.equals("EditText")) {
			bodym+=EditText(chirld,false);
		}
		
		if (chirld.type.equals("EditText_MutiLine")) {
			bodym+=EditTextMutiLine(chirld,false);
		}
		

		if (chirld.type.equals("ImageView")) {
			bodym+=ImageView(chirld,false);
		}

		if (chirld.type.equals("ExpandableListView")) {
			bodym+=ExpandableListView(chirld,false);
		}
	}
	



		public static String Span(CompomentBean chirld,boolean ajax)
		{
			String bodym="";
		    String startAjax="";
		    String endAjax="";
		    if(ajax)
		    {
		    	startAjax="itemHtml+='";
		    	endAjax="'";
		    }
		bodym += ""+startAjax+"<span id=\"" + chirld.enname + "\"  >" + chirld.cnname + "</span>"+endAjax+"\n";
		// h4 label
		return bodym;

	}


		public static String H19(CompomentBean chirld,boolean ajax)
		{
			String bodym="";
		    String startAjax="";
		    String endAjax="";
		    if(ajax)
		    {
		    	startAjax="itemHtml+='";
		    	endAjax="'";
		    }
		bodym += ""+startAjax+"<h4 id=\"" + chirld.enname + "\"  >" + chirld.cnname + "</h4>"+endAjax+"\n";
		return bodym;

	}


		public static String TextView(CompomentBean chirld,boolean ajax)
		{

			String expand = "";
			if ("1bei".equals(chirld.compmentExpandForWeb)) {
				expand = "flex:1;";
			}
			if ("2bei".equals(chirld.compmentExpandForWeb)) {
				expand = "flex:2;";
			}
			if ("3bei".equals(chirld.compmentExpandForWeb)) {
				expand = "flex:3;";
			}
			
			String bodym="";
		    String startAjax="";
		    String endAjax="";
		    if(ajax)
		    {
		    	startAjax="itemHtml+='";
		    	endAjax="'";
		    }
		bodym += ""+startAjax+"<span id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" style=\"font-size:"
						+ chirld.textSize + "px;color:" + chirld.rgb16 + ";" + expand + "\">" + chirld.cnname
						+ "</span>"+endAjax+"\n";
			return bodym;

	}
	

		public static String TextView_MutiLine(CompomentBean chirld,boolean ajax)
		{
			String bodym="";
		    String startAjax="";
		    String endAjax="";
		    if(ajax)
		    {
		    	startAjax="itemHtml+='";
		    	endAjax="'";
		    }
		bodym +=""+startAjax+"<div id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" style=\"margin:10px;padding:10px;border-radius: 0.1rem;font-size: 12px; color: "+chirld.rgb16+"; background-color:"+ chirld.bgRgb16 + ";width: 90%; word-break: break-all; text-overflow: ellipsis; display: -webkit-box; /** 对象作为伸缩盒子模型显示 **/ -webkit-box-orient: vertical; /** 设置或检索伸缩盒对象的子元素的排列方式 **/ -webkit-line-clamp: 3; /** 显示的行数 **/ overflow: hidden;\">"+chirld.cnname+"</div>"+endAjax+"\n";
		bodym += ""+startAjax+"</div>"+endAjax+"\n";
		return bodym;
	}
	

		public static String Label(CompomentBean chirld,boolean ajax)
		{
			String bodym="";
		    String startAjax="";
		    String endAjax="";
		    if(ajax)
		    {
		    	startAjax="itemHtml+='";
		    	endAjax="'";
		    }
		bodym += ""+startAjax+"<label id=\"" + chirld.enname + "\" name =\"" + chirld.enname + "\" >" + chirld.cnname
				+ "</label>"+endAjax+"\n";
		
		// h4 label
		return bodym;

	}


		public static String Progress(CompomentBean chirld,boolean ajax)
		{
			String bodym="";
		    String startAjax="";
		    String endAjax="";
		    if(ajax)
		    {
		    	startAjax="itemHtml+='";
		    	endAjax="'";
		    }
		bodym += ""+startAjax+"<div class=\"ui-progress\">"+endAjax+"\n";
		bodym += ""+startAjax+"<span style=\"width:50%\"></span>"+endAjax+"\n";
		bodym += ""+startAjax+"</div>"+endAjax+"\n";
		return bodym;
		
	}

		public static String Loading(CompomentBean chirld,boolean ajax)
		{
			String bodym="";
		    String startAjax="";
		    String endAjax="";
		    if(ajax)
		    {
		    	startAjax="itemHtml+='";
		    	endAjax="'";
		    }
		bodym += ""+startAjax+"<div class=\"ui-loading-block show\">"+endAjax+"\n";
		bodym += ""+startAjax+"<div class=\"ui-loading-cnt\">"+endAjax+"\n";
		bodym += ""+startAjax+"<i class=\"ui-loading-bright\"></i>"+endAjax+"\n";
		bodym += ""+startAjax+"<p>正在加载中...</p>"+endAjax+"\n";
		bodym += ""+startAjax+"</div>"+endAjax+"\n";
		bodym += ""+startAjax+"</div>"+endAjax+"\n";

		return bodym;
		}

	

	
		public static String PopTips(CompomentBean chirld,boolean ajax)
		{
			String bodym="";
		    String startAjax="";
		    String endAjax="";
		    if(ajax)
		    {
		    	startAjax="itemHtml+='";
		    	endAjax="'";
		    }
		bodym += ""+startAjax+"<div class=\"ui-poptips ui-poptips-info\">"+endAjax+"\n";
		bodym += ""+startAjax+"<div class=\"ui-poptips-cnt\"><i></i>" + chirld.cnname + "</div>"+endAjax+"\n";
		bodym += ""+startAjax+"</div>"+endAjax+"\n";
		
		return bodym;
		}
	

	
	public static String Selecter(CompomentBean chirld,boolean ajax)
	{
		String bodym="";
	    String startAjax="";
	    String endAjax="";
	    if(ajax)
	    {
	    	startAjax="itemHtml+='";
	    	endAjax="'";
	    }
		bodym += ""+startAjax+"<div  style=\"display:flex;\">"+endAjax+"\n";
		bodym += ""+startAjax+"<div>" + chirld.cnname + "</div>"+endAjax+"\n";
		bodym += ""+startAjax+"<div class=\"ui-select\" style=\"border: 0.02rem solid #ddd;border-radius: 0.1rem; margin-left: 2px;margin-right:2px;\">"+endAjax+"\n";
		bodym += ""+startAjax+"<select id=\""+chirld.enname+"\">"+endAjax+"\n";
		bodym += ""+startAjax+"<option>2014</option>"+endAjax+"\n";
		bodym += ""+startAjax+"<option selected>2015</option>"+endAjax+"\n";
		bodym += ""+startAjax+"<option>2016</option>"+endAjax+"\n";
		bodym += ""+startAjax+"</select>"+endAjax+"\n";
		bodym += ""+startAjax+"</div>"+endAjax+"\n";
		bodym += ""+startAjax+"</div>"+endAjax+"\n";
		
		return bodym;
		
	}
	
	

		public static String DateSelecter(CompomentBean chirld,boolean ajax)
		{
			String bodym="";
		    String startAjax="";
		    String endAjax="";
		    if(ajax)
		    {
		    	startAjax="itemHtml+='";
		    	endAjax="'";
		    }
			bodym += ""+startAjax+"<div class=\"daily_date\">"+endAjax+"\n";
			bodym += ""+startAjax+"<input class=\"mobile_date\" value=\"\" readonly=\"\" name=\"appDate\" id=\"start_date\" type=\"text\">"+endAjax+"\n";
			bodym += ""+startAjax+"</div>"+endAjax+"\n";
			return bodym;
		}
		
	public static String Button(CompomentBean chirld,boolean ajax)
	{

		String expand = "";
		if ("1bei".equals(chirld.compmentExpandForWeb)) {
			expand = "flex:1;";
		}
		if ("2bei".equals(chirld.compmentExpandForWeb)) {
			expand = "flex:2;";
		}
		if ("3bei".equals(chirld.compmentExpandForWeb)) {
			expand = "flex:3;";
		}
		
		String bodym="";
	    String startAjax="";
	    String endAjax="";
	    if(ajax)
	    {
	    	startAjax="itemHtml+='";
	    	endAjax="'";
	    }
	    if (chirld.picName.equals("图片名")) {

			String bgcolor = "";
			if (chirld.bgRgb16.contains("#")) {
				bgcolor = chirld.bgRgb16;
			}
			String actionstring = "";
			if (chirld.actionString != null) {
				actionstring = chirld.actionString;
			}

			if ((chirld.parent != null && chirld.parent.type != null)) {

				if (chirld.parent.type.toLowerCase().contains("tab")) {
					actionstring = "tabBtnPress(this);";
					expand = "flex:1;";
				}
			}
			bodym += ""+startAjax+"<button  style=\"border-radius: 0.1rem;padding:6px;background-color:" + bgcolor + ";color:" + chirld.rgb16 + ";"
					+ expand + "\"  onclick=\"" + actionstring + "\" >" + chirld.cnname + "</button>"+endAjax+"\n";

		} else {

			bodym += ""+startAjax+"<button  src= \"/images/" + chirld.picName + ".png\" onclick=\"" + chirld.actionString
					+ "\"  style=\"" + expand + "\">" + chirld.cnname + "</button>"+endAjax+"\n";
		}
	    return bodym;
	    
	}
	public static String Button_Close(CompomentBean chirld,boolean ajax)
	{
		String bodym="";
	    String startAjax="";
	    String endAjax="";
	    if(ajax)
	    {
	    	startAjax="itemHtml+='";
	    	endAjax="'";
	    }
	    bodym += ""+startAjax+"<a href=\"#\" class=\"ui-icon-close\"></a>"+endAjax+"\n";

	    return bodym;
	}
	public static String Button_Query(CompomentBean chirld,boolean ajax)
	{
		String bodym="";
	    String startAjax="";
	    String endAjax="";
	    if(ajax)
	    {
	    	startAjax="itemHtml+='";
	    	endAjax="'";
	    }
		bodym +=""+startAjax+"<div style=\" top: 0.2rem;right: 0.2rem;width: 0.4rem;height: 0.4rem;background: url(<%=basePath%>images/icon-search.png) no-repeat;background-position: center center;background-size: 100% 100%;\" onclick=\"query()\"></div>"+endAjax+"\n";
		return bodym;
	}
	
	public static String Button_A(CompomentBean chirld,boolean ajax)
	{
		String bodym="";
	    String startAjax="";
	    String endAjax="";
	    if(ajax)
	    {
	    	startAjax="itemHtml+='";
	    	endAjax="'";
	    }
	    
		bodym +=""+startAjax+"<a href=\"#\" style=\"text-decoration:none;font-size:" + chirld.textSize + "px; color:"+chirld.rgb16+";\">"+chirld.cnname+"</a>"+endAjax+"\n";
		return bodym;
	}
	
	
	public static String leftArrow(CompomentBean chirld,boolean ajax)
	{
		String bodym="";
	    String startAjax="";
	    String endAjax="";
	    if(ajax)
	    {
	    	startAjax="itemHtml+='";
	    	endAjax="'";
	    }
		bodym += ""+startAjax+"<div onclick=\"history.back()\" style=\"margin:10px; width: 10px; height: 10px; border-top: 2px solid #dfdfdf; border-right: 2px solid #dfdfdf; transform: rotate(225deg);\"></div>"+endAjax+"\n";

		return bodym;
	}
	public static String rightArrow(CompomentBean chirld,boolean ajax)
	{
		String bodym="";
	    String startAjax="";
	    String endAjax="";
	    if(ajax)
	    {
	    	startAjax="itemHtml+='";
	    	endAjax="'";
	    }
	    bodym += ""+startAjax+"<div style=\"margin:10px; width: 7px; height: 7px; border-top: 2px solid #dfdfdf; border-right: 2px solid #dfdfdf; transform: rotate(45deg);\"></div>"+endAjax+"\n";
        return bodym;
	}
	public static String CheckBox(CompomentBean chirld,boolean ajax)
	{
		String bodym="";
	    String startAjax="";
	    String endAjax="";
	    if(ajax)
	    {
	    	startAjax="itemHtml+='";
	    	endAjax="'";
	    }
	    bodym +=  ""+startAjax+"<label class=\"ui-checkbox\" style=\"margin:10px\">"+endAjax+"\n";
		bodym +=  ""+startAjax+"<input type=\"checkbox\" name=\"checkbox\" value=\"\">"+endAjax+"\n";
		bodym +=  ""+startAjax+"</label>"+endAjax+"\n";
		bodym +=  ""+startAjax+"<p>" + chirld.cnname + "</p>"+endAjax+"\n";
		return bodym;
	    
	}
	
	public static String CheckBox_Switch(CompomentBean chirld,boolean ajax)
	{
		String bodym="";
	    String startAjax="";
	    String endAjax="";
	    if(ajax)
	    {
	    	startAjax="itemHtml+='";
	    	endAjax="'";
	    }
		bodym += ""+startAjax+"<label class=\"ui-switch\">"+endAjax+"\n";
		bodym += ""+startAjax+"<input type=\"checkbox\">"+endAjax+"\n";
		bodym += ""+startAjax+"</label>"+endAjax+"\n";
		return bodym;
	}
	
	public static String Radio(CompomentBean chirld,boolean ajax)
	{
		String bodym="";
	    String startAjax="";
	    String endAjax="";
	    if(ajax)
	    {
	    	startAjax="itemHtml+='";
	    	endAjax="'";
	    }
		bodym += ""+startAjax+"<div class=\"ui-form-item ui-form-item-radio ui-border-b\">"+endAjax+"\n";
		bodym += ""+startAjax+"<label class=\"ui-radio\" for=\"radio\">"+endAjax+"\n";
		bodym += ""+startAjax+"<input type=\"radio\" name=\"radio\" value=\"\">"+endAjax+"\n";
		bodym += ""+startAjax+"</label>"+endAjax+"\n";
		bodym += ""+startAjax+"<p>"+chirld.cnname+"</p>"+endAjax+"\n";
		bodym += ""+startAjax+"</div>"+endAjax+"\n";
		return bodym;
	}
	
	
	public static String File(CompomentBean chirld,boolean ajax)
	{
		String bodym="";
	    String startAjax="";
	    String endAjax="";
	    if(ajax)
	    {
	    	startAjax="itemHtml+='";
	    	endAjax="'";
	    }
		bodym += ""+startAjax+"<input type=\"file\" id=\""+chirld.enname+"\" name=\""+chirld.enname+"\"/>"+endAjax+"\n";
		return bodym;
	}
	
	
	public static String EditText(CompomentBean chirld,boolean ajax)
	{
		String expand = "";
		if ("1bei".equals(chirld.compmentExpandForWeb)) {
			expand = "flex:1;";
		}
		if ("2bei".equals(chirld.compmentExpandForWeb)) {
			expand = "flex:2;";
		}
		if ("3bei".equals(chirld.compmentExpandForWeb)) {
			expand = "flex:3;";
		}
		
		String bodym="";
	    String startAjax="";
	    String endAjax="";
	    if(ajax)
	    {
	    	startAjax="itemHtml+='";
	    	endAjax="'";
	    }
	    bodym += ""+startAjax+"<input style=\" border: 0.02rem solid #ddd;border-radius: 0.1rem; line-height: " + chirld.h + "px; height: " + chirld.h
				+ "px;  font-size: " + chirld.textSize + "px;" + expand + "\"  type=\"text\"  id=\"" + chirld.enname
				+ "\" name=\"" + chirld.enname + "\" placeholder=\"" + chirld.cnname + "\">"+endAjax+"\n";
		
		return bodym;
		
	}
	
	
	
	public static String EditTextMutiLine(CompomentBean chirld,boolean ajax)
	{
		String expand = "";
		if ("1bei".equals(chirld.compmentExpandForWeb)) {
			expand = "flex:1;";
		}
		if ("2bei".equals(chirld.compmentExpandForWeb)) {
			expand = "flex:2;";
		}
		if ("3bei".equals(chirld.compmentExpandForWeb)) {
			expand = "flex:3;";
		}
		
		String bodym="";
	    String startAjax="";
	    String endAjax="";
	    if(ajax)
	    {
	    	startAjax="itemHtml+='";
	    	endAjax="'";
	    }
	    
	    bodym += ""+startAjax+"		<textarea ";

	    bodym += "	style=\"border: 0.02rem solid #ddd; border-radius: 0.1rem; height: 1rem; width: 100%; font-size: "+chirld.textSize+"px; margin-bottom: 1rem;"+expand+"\"";

	    bodym += "	type=\"text\" id=\""+chirld.enname+"\" name=\""+chirld.enname+"\" ";

	    bodym += "	placeholder=\""+chirld.cnname+"\"></textarea>"+endAjax+"\n";
		

		
		return bodym;
		
	}
	
	public static String ImageView(CompomentBean chirld,boolean ajax)
	{String bodym="";
    String startAjax="";
    String endAjax="";
    if(ajax)
    {
    	startAjax="itemHtml+='";
    	endAjax="'";
    }
	if ((chirld.parent!=null && chirld.parent.type.toLowerCase().contains("grid"))
			|| (chirld.parent.parent!=null && chirld.parent.parent.type.toLowerCase().contains("grid"))) {
		String columncount = "trisect";
		String picsize = "190x284";
		if (chirld.parent.type.toLowerCase().contains("halve")
				|| chirld.parent.parent.type.toLowerCase().contains("halve")) {
			columncount = "halve";
			picsize = "290x160";
		}
		bodym += ""+startAjax+"<div class=\"ui-grid-" + columncount + "-img\">"+endAjax+"\n";
		bodym += ""+startAjax+"<span style=\"background-image:url(http://placeholder.qiniudn.com/" + picsize
				+ "/"+chirld.picName+".png)\"></span>"+endAjax+"\n";
		bodym += ""+startAjax+"</div>"+endAjax+"\n";
	}

	else if ((chirld.parent!=null && chirld.parent.type.toLowerCase().contains("list"))
			|| (chirld.parent.parent!=null &&chirld.parent.parent.type.toLowerCase().contains("list"))) {

		bodym += ""+startAjax+"<div class=\"ui-list-img\">"+endAjax+"\n";
		bodym += ""+startAjax+"<!--class=\"ui-avatar\"  圆形框-->"+endAjax+"\n";
		bodym += ""+startAjax+"<span style=\"background-image:url(http://placeholder.qiniudn.com/200x136/"+chirld.picName+".png)\"></span>"+endAjax+"\n";
		bodym += ""+startAjax+"</div>\n";
	}else
	{

		
		bodym += ""+startAjax+"<img src=\"<%=basePath%>images/"+chirld.picName+".png\" style=\"width:20px;height:20px;\"></img>"+endAjax+"\n";

		
	}
	return bodym;
	}
	
	public static String  ExpandableListView(CompomentBean chirld,boolean ajax)
	{
	    String bodym="";
	    String startAjax="";
	    String endAjax="";
	    if(ajax)
	    {
	    	startAjax="itemHtml+='";
	    	endAjax="'";
	    }
	    
	    bodym += ""+startAjax+"<div class=\"ui-selector-content\" style=\"display:\">"+endAjax+"\n";
		bodym += ""+startAjax+"<ul>"+endAjax+"\n";
		bodym += ""+startAjax+"<li class=\"ui-selector-item active\">"+endAjax+"\n";
		bodym += ""+startAjax+"<h3 class=\"ui-border-b\">"+endAjax+"\n";
		bodym += ""+startAjax+"<p>最近在玩的好友</p><span class=\"ui-txt-info\">11</span>"+endAjax+"\n";
		bodym += ""+startAjax+"</h3>"+endAjax+"\n";
		bodym += ""+startAjax+"<ul class=\"ui-list ui-border-b\">"+endAjax+"\n";
		bodym += ""+startAjax+"<li>"+endAjax+"\n";
		bodym += ""+startAjax+"<div class=\"ui-avatar-s\">"+endAjax+"\n";
		bodym += ""+startAjax+"<span style=\"background-image:url(../img/ava1.png)\"></span>"+endAjax+"\n";
		bodym += ""+startAjax+"</div>"+endAjax+"\n";
		bodym += ""+startAjax+"<div class=\"ui-list-info ui-border-t\"><h4>飞翔的企鹅</h4></div>"+endAjax+"\n";
		bodym += ""+startAjax+"</li>"+endAjax+"\n";
		bodym += ""+startAjax+"</ul>"+endAjax+"\n";
		bodym += ""+startAjax+"</li>"+endAjax+"\n";
		bodym += ""+startAjax+"<li class=\" ui-selector-item\">"+endAjax+"\n";
		bodym += ""+startAjax+"<h3 class=\"ui-border-b\">"+endAjax+"\n";
		bodym += ""+startAjax+"<p>最近在玩的好友</p><span class=\"ui-txt-info\">11</span>"+endAjax+"\n";
		bodym += ""+startAjax+"</h3>"+endAjax+"\n";
		bodym += ""+startAjax+"<ul class=\"ui-list ui-border-b\">"+endAjax+"\n";
		bodym += ""+startAjax+"<li>"+endAjax+"\n";
		bodym += ""+startAjax+"<div class=\"ui-avatar-s\">"+endAjax+"\n";
		bodym += ""+startAjax+"<span style=\"background-image:url(../img/ava1.png)\"></span>"+endAjax+"\n";
		bodym += ""+startAjax+"</div>"+endAjax+"\n";
		bodym += ""+startAjax+"<div class=\"ui-list-info ui-border-t\"><h4>飞翔的企鹅</h4></div>"+endAjax+"\n";
		bodym += ""+startAjax+"</li>"+endAjax+"\n";

		bodym += ""+startAjax+"</ul>"+endAjax+"\n";
		bodym += ""+startAjax+"</li>"+endAjax+"\n";

		bodym += ""+startAjax+"</ul>"+endAjax+"\n";
		bodym += ""+startAjax+"</div>"+endAjax+"\n";
		return bodym;
		}
	
	
	
	
	
	
	
	public void commonViewNeedValue(CompomentBean chirld)
	{
		
		
		if (chirld.chirlds != null && chirld.chirlds.size() > 0) {
			WebJspListViewItem webListItemJsp = new WebJspListViewItem();
			String itemString=webListItemJsp.analyse(chirld);

			jsString += "\n<script type=\"text/javascript\">\n";
			jsString += "//进入页面向后台取数据,初始化页面\n";

	

			/*
			 * { "resultCode": "1001", "resultSize": 1, "resultTotal":10, "resultData": [ {
			 * "name": "bootstrap-table", "stargazers_count": "526", "forks_count": "122",
			 * "description":
			 * "An extended Bootstrap table with radio, checkbox, sort, pagination, and other added features. (supports twitter bootstrap v2 and v3) "
			 * } ] }
			 */
			jsString += " function init() {\n";
			jsString += "$.ajax({\n";
			jsString += "url:${pageContext.request.contextPath}/__,\n";
			jsString += "type:'post',\n";
			jsString += "dataType:'json',\n";
			jsString += "async:true,\n";
			jsString += "data:{where:window.where},\n";
			jsString += "timeout:1000,\n";
			jsString += "error:function(){\n";
			jsString += "requestIng=false;\n";
			jsString += "alert(\"ajax error\");\n";
			jsString += "}, \n";
			jsString += "success:function(rsObj)\n";
			jsString += "{\n";
			jsString += "requestIng=false;\n";
			jsString += "var resultSize=rsObj.resultSize;\n";
			jsString += "window.resultTotal=rsObj.resultTotal;\n";
			jsString += "var resultData=rsObj.resultData;\n";
			jsString += "if(resultSize>0){\n";
			jsString += "for(var i=0;i<resultData.length;i++){ \n";
			
			jsString +="var itemHtml='';\n";
			jsString +=itemString;
			jsString += " $(\".h-body\").append(itemHtml); \n";
			jsString += " }\n";
			jsString += " }\n";
			jsString += "});\n";
			jsString += "}\n";
			jsString += "</script>\n";
		}
	
	}
	
	Comparator<CompomentBean> comparatorDate = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 按日期排
			if (s1.time != s2.time) {
				return (int) (s1.time - s2.time);
			}
			return 0;
		}
	};

	Comparator<CompomentBean> comparatorY = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 按y排
			if (s1.y != s2.y) {
				return s1.y - s2.y;
			}
			return 0;
		}
	};

	Comparator<CompomentBean> comparatorX = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 按X排
			if (s1.x != s2.x) {
				return s1.x - s2.x;
			}
			return 0;
		}
	};

	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String genID(int length) // 参数为返回随机数的长度
	{
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}

	public static String id() {

		return genID(3) + "-" + genID(2) + "-" + genID(3);
	}

}
