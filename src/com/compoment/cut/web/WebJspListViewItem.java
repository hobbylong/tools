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

public class WebJspListViewItem {

	// <!--
	// http://blog.csdn.net/topviewers/article/details/21644305
	// relative相对自己进行top，right，bottom，left移动 ，占位，文档流不变。
	//
	// absolute
	// 有父辈(父亲或爷爷)为absolute,relative，就相对父辈。没父辈,就相对浏览器。不占位，文档流改变。忽略padding
	// fixed 特殊absolute
	//
	// static 文档流
	// -->

	// visibility:hidden;隐藏占位 display:none; 隐藏不占位
	// height: 42px; line-height: 42px; 多行时行高,单行时垂直居中
	// background-color: #fd5f28;背景色 font-size: 18px;字体大小 color: #fff;字体颜色
	// text-align: center;文本居中

	// float:left;right center

	// width: 100%;

	// position: relative; 正常占位

	// position: absolute; 相对父亲浮起来，不占位，父亲不指定position: relative则相对<body>浮起来
	// z-index:1; 多个浮起来，数值大的在上面

	// position: fixed; 相对浏览器浮起来，不占位
	// 后跟 top:0; left:0; bottom:0; right:0;

	// overflow: scroll;滚动 auto自动处理 hidden隐藏

	// margin-top:3px;

	String bodym = "\n\n\n";
	String style = "";
	String js = "";
	String connection = "";
	String pageName = "";
	String className = "";




	CompomentBean newParent;
	
	String jsString="";
	String styleString="";
	
	String parentChirld="";
	CompomentBean listItemBean = null;
	




	public String analyse(CompomentBean itemBean) {
		// Collections.sort(oldBeans, comparatorDate);


		parent(itemBean);
       

	
		
//		bodym+=styleString;

//		bodym+=jsString;



	

		return bodym;
	}


	
	

	public void parent(CompomentBean bean) {

	

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
					String border="";
					
					String expand="";
				
					
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
						border="border-top: 1px solid "+chirld.rgb16+";";
					}

					if ("boderLeft".equals(chirld.compmentBorderForWeb)) {
						border="border-left: 1px solid "+chirld.rgb16+";";
					}

					if ("boderRight".equals(chirld.compmentBorderForWeb)) {
						border="border-right: 1px solid "+chirld.rgb16+";";
					}

					if ("boderBottom".equals(chirld.compmentBorderForWeb)) {
						border="border-bottom: 1px solid "+chirld.rgb16+";";
					}

					if ("boderTopBottom".equals(chirld.compmentBorderForWeb)) {
						border="border-top: 1px solid "+chirld.rgb16+";border-bottom: 1px solid \"+chirld.rgb16+\";";
					}

					if ("boderAll".equals(chirld.compmentBorderForWeb)) {
						border="border: 1px solid "+chirld.rgb16+";";
					}
					
					if ("1bei".equals(chirld.compmentExpandForWeb)) {
						expand="flex:1;";
					}
					if ("2bei".equals(chirld.compmentExpandForWeb)) {
						expand="flex:2;";
					}
					if ("3bei".equals(chirld.compmentExpandForWeb)) {
						expand="flex:3;";
					}
					
					

					if (chirld.type.equals("DivLayout_Horizon")||chirld.type.equals("HeaderLayout_Horizon")||chirld.type.equals("FooterLayout_Horizon")) {
						String classString="";
						if(chirld.type.contains("Header"))
						{
							classString="h-header";
						}else if(chirld.type.contains("Footer"))
						{
							classString="h-footer";
						}
						
						start += "itemHtml+='<div id=\"" + chirld.enname  + "\" class=\""+classString+"\"  style=\"background-color:" + chirld.bgRgb16
								+ ";display:flex;flex-direction:row;" + relate +border+expand+ "\" >'\n";

						end += "  itemHtml+='</div>'\n";
					}


					else if (chirld.type.equals("DivLayout_Vertical")||chirld.type.equals("HeaderLayout_Vertical")||chirld.type.equals("FooterLayout_Vertical")) {

						String classString="";
						if(chirld.type.contains("Header"))
						{
							classString="header";
						}else if(chirld.type.contains("Footer"))
						{
							classString="footer";
						}
						
						start += "itemHtml+='<div id=\"" + chirld.enname + "\" class=\""+classString+"\" style=\"background-color:" + chirld.bgRgb16
								+ ";display:flex;flex-direction:column;" + relate +border+expand+ "\" >'\n";

						end += "  itemHtml+='</div>'\n";
					}
					
				
					
					

				   else if (chirld.type.equals("FormLayout")) {

						start += "<div id=\"" + chirld.enname + "\" class=\"ui-form ui-border-t\">\n";
						start += "<form action=\"#\">\n";

						end += " </form>\n";
						end += "  </div>\n";
					} else if (chirld.type.equals("Form_ItemLayout")) {

						start += " <div id=\"" + chirld.enname + "\"  class=\"ui-form-item ui-border-b\">\n";

						end += "  </div>\n";
					}

					else if (chirld.type.equals("ListLayout")) {

						
						start += "<ul id=\"" + chirld.enname + "\" class=\"h-list ui-border-tb\" style=\"flex:1;padding:10px;overflow-y:scroll\">\n";
					

						end += "  </ul>\n";
						
					}

					else if (chirld.type.equals("List_ItemLayout_Horizon")) {

						start += "itemHtml+='<li id=\"" + chirld.enname
								+ "\" class=\"ui-border-t\" style=\"display:flex;flex-direction:row;width: 100%; " + relate+border
								+ "\">'\n";

						end += "  itemHtml+='</li>'\n";
					} else if (chirld.type.equals("List_ItemLayout_Vertical")) {

						start += " itemHtml+='<li id=\"" + chirld.enname
								+ "\" class=\"ui-border-t\" style=\"display:flex;flex-direction:column;width: 100%; " + relate+border
								+ "\">'\n";

						end += "  itemHtml+='</li>'\n";
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
						end += "<script class=\"demo-script\">\n";
						end += "$(\".ui-dialog\").dialog(\"show\");\n";
						end += "</script>\n";

					} else if (chirld.type.equals("TabLayout")) {

						start += "<div id=\"" + chirld.enname + "\" class=\"h-tabbar\" style=\"background-color:" + chirld.bgRgb16
								+ ";display:flex;flex-direction:row;" + relate +border+expand+ "\" >\n";

						end += "  </div>\n";
						
					
						jsString+="<script type=\"text/javascript\">\n";
						jsString+="//tabbar\n";
						jsString+=" $(function () {\n";
						jsString+="     var collection = $(\".h-tabbar\").children();\n";
						jsString+="  $.each(collection, function () {\n";
						jsString+="     $(this).addClass(\"tabBtn\");\n";
						jsString+=" });\n";
						jsString+=" });\n";
						jsString+=" //单击事件\n";
						jsString+=" function tabBtnPress(btn) {\n";
						jsString+="   var collection = $(\".h-tabbar\").children();\n";
						jsString+=" $.each(collection, function () {\n";
						jsString+="    $(this).removeClass(\"tabBtnPress\");\n";
						jsString+="  $(this).addClass(\"tabBtn\");\n";
						jsString+=" });\n";
						jsString+=" $(btn).removeClass(\"tabBtn\");\n";
						jsString+=" $(btn).addClass(\"tabBtnPress\");\n";
						jsString+=" }\n";
					    jsString+="</script>\n";
					    
					    styleString+="<style>\n";
					    styleString+=".tabBtn\n";
					    	styleString+=" {\n";
					    	styleString+=" cursor: pointer;\n";
					    	styleString+="  border-bottom: 2px solid transparent;\n";
					    	styleString+="}\n";
					    	styleString+=".tabBtnPress\n";
					    	styleString+=" {\n";
					    	styleString+=" cursor: pointer;\n";
					    	styleString+="color: #00a5e0;\n";
					    	styleString+="background: #ffffff;\n";
					    	styleString+="border-bottom: 2px #00a5e0 solid;\n";
					    	styleString+="}\n";
					    	styleString+="</style>\n";

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

	public void chirld(CompomentBean chirld, CompomentBean parent) {
		
		// 这个儿子是非容器

		

				if (chirld.type.equals("Span")) {
					bodym+=WebJsp.Span(chirld,true);
				}

				if (chirld.type.equals("H1-9")) {

					bodym+=WebJsp.H19(chirld,true);
				}

				if (chirld.type.equals("TextView")) {
					
					bodym+=WebJsp.TextView(chirld,true);

				}
				
				if (chirld.type.equals("TextView_MutiLine")) {
					bodym+=WebJsp.TextView_MutiLine(chirld,true);
				}
				
				if (chirld.type.equals("Label")) {

					// h4 label
					bodym+=WebJsp.Label(chirld,true);

				}

				if (chirld.type.equals("Progress")) {
				
					bodym+=WebJsp.Progress(chirld,true);
				}
				if (chirld.type.equals("Loading")) {

					bodym+=WebJsp.Loading(chirld,true);

				}

				if (chirld.type.equals("PopTips")) {
					bodym+=WebJsp.PopTips(chirld,true);
				
				}

				if (chirld.type.equals("Selecter")) {

					bodym+=WebJsp.Selecter(chirld,true);

				}
				
				
				if(chirld.type.equals("DateSelecter"))
				{
					bodym+=WebJsp.DateSelecter(chirld,true);
				}

				if (chirld.type.equals("Button")) {
					bodym+=WebJsp.Button(chirld,true);
				}

				if (chirld.type.equals("Button_Close")) {
					bodym+=WebJsp.Button_Close(chirld,true);
				}
				
				if (chirld.type.equals("Button_Query")) {
					bodym+=WebJsp.Button_Query(chirld,true);
				}
				
				if (chirld.type.equals("Button_A")) {
					bodym+=WebJsp.Button_A(chirld,false);
				}

				if (chirld.type.equals("leftArrow")) {
					bodym+=WebJsp.leftArrow(chirld,true);
				}

				if (chirld.type.equals("rightArrow")) {
					bodym+=WebJsp.rightArrow(chirld,true);
				}

				if (chirld.type.equals("CheckBox")) {
					bodym+=WebJsp.CheckBox(chirld,true);

				}

				if (chirld.type.equals("CheckBox_Switch")) {
					bodym+=WebJsp.CheckBox_Switch(chirld,true);
				}

				if (chirld.type.equals("Radio")) {
					bodym+=WebJsp.Radio(chirld,true);

				}
				
				
				if (chirld.type.equals("File")) {
					bodym+=WebJsp.File(chirld,true);
				}
				

				if (chirld.type.equals("EditText")) {
					bodym+=WebJsp.EditText(chirld,true);
				}

				if (chirld.type.equals("ImageView")) {
					bodym+=WebJsp.ImageView(chirld,true);
				}

				if (chirld.type.equals("ExpandableListView")) {
					bodym+=WebJsp.ExpandableListView(chirld,true);
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
