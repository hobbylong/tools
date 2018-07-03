package com.compoment.ui.ios.creater;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compoment.addfunction.android.FileBean;
import com.compoment.cut.CompomentBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.RegexUtil;
import com.compoment.util.RegexUtil.ControllerBean;


/***
 * TableViewCell
 * */
public class TableViewCellM {


	
    String className="";
    
  /**type= TableViewCell  TableViewHeadCell   ScrollViewCell */
    String type="";
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

    String pageName="";
  

        
	public TableViewCellM(String pageName,List<CompomentBean> oldBeans,String type) {
		//type= TableViewCell  TableViewHeadCell   ScrollViewCell  
        this.type=type;
		this.pageName=pageName;
        className=firstCharToUpperAndJavaName(pageName);
		//copyFile();
        analyse(oldBeans);
	
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
	
	public void copyFile() {
		
	}

	

	

	
	String n="";
	public void analyse(List<CompomentBean> oldBeans) {
		
		n+="#import <Foundation/Foundation.h>\n";
		n+="#import \""+className.trim()+type.trim()+".h\"\n";
		n+="@implementation "+className+type+"\n";

		

		
		
		int maxW = 0;
		int maxH = 0;
		List<CompomentBean> layouts = new ArrayList<CompomentBean>();
		CompomentBean maxBean = null;
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
		
		
		parent(maxBean);
	
		
		n+="@end\n";
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/ios", className+type,
				"m", n);
	}

	public void parent(CompomentBean bean) {

		if (bean.chirlds != null && bean.chirlds.size() > 0) {
			for (CompomentBean chirld : bean.chirlds) {

				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					parent(chirld);
				} else {
					chirld(chirld, bean);
					
				}
			}

		}

	}

	public void chirld(CompomentBean chirld, CompomentBean parent) {

		if (chirld.type.equals("TextView")) {
			n+="//"+chirld.cnname+"\n";
			n+="@synthesize "+chirld.enname+";\n";
	
		
		}

		if (chirld.type.equals("Button")) {
			n+="//"+chirld.cnname+"\n";
			n+="@synthesize "+chirld.enname+";\n";
			
		
		}

		if (chirld.type.equals("EditText")) {
			n+="//"+chirld.cnname+"\n";
			n+="@synthesize "+chirld.enname+";\n";
			
			

		}

		if (chirld.type.equals("CheckBox")) {
			n+="//"+chirld.cnname+"\n";
			n+="@synthesize "+chirld.enname+";\n";

		}

		if (chirld.type.equals("ListView")) {
			
		}

		if (chirld.type.equals("ImageView")) {
			n+="//"+chirld.cnname+"\n";
			n+="@synthesize "+chirld.enname+";\n";
		}

		if (chirld.type.equals("ExpandableListView")) {

		}
	}
	
	
  
}
