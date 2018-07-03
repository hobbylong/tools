package com.compoment.ui.ios.creater;

import java.util.ArrayList;
import java.util.List;

import com.compoment.cut.CompomentBean;
import com.compoment.cut.iphone.IphoneTableViewCellXib;

public class ScrollViewCells {

	String scrollDeclare="";
	String scrollImplement="";
	String scrollEditTextCheck="";
	
	String closeKeyboardDeclare="";
	String closeKeyboardImplement="";
	
	String isRunTimeAddScrollView_LayoutName="";
	CompomentDeclareImplement compomentDeclareImplement=new CompomentDeclareImplement();
	public void parent(CompomentBean bean) {// bean.type==ScrollViewLayout

		if (bean.chirlds != null && bean.chirlds.size() > 0) {

			if (bean.type.equals("ScrollViewLayout")) {
				// start
				scrollDeclare += "-(void) scrollUI{\n";
				
				scrollDeclare += "   for (UIView *view in views) {\n";
						scrollDeclare += "        [view removeFromSuperview];\n";
						scrollDeclare += "}\n";
								scrollDeclare += " [views removeAllObjects];\n";
				
				scrollDeclare += "int height=0;\n";
				scrollDeclare += "int width=self."+bean.enname.replace("Layout", "")+".frame.size.width;\n";
				scrollDeclare += "int x=0;\n";
				scrollDeclare += "int y=0;\n\n";

			}

			for (CompomentBean chirld : bean.chirlds) {

				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					if (bean.type.equals("ScrollViewLayout")) {

						if (chirld.isRunTimeAddScrollView) {
							isRunTimeAddScrollView_LayoutName=chirld.enname;
							
							List maxbean=new ArrayList();
							maxbean.add(chirld);

							TableViewCellH tableViewCellH = new TableViewCellH(chirld.enname, maxbean,
									"ScrollViewCell");
							TableViewCellM tableViewCellM = new TableViewCellM(chirld.enname, maxbean,
									"ScrollViewCell");

							IphoneTableViewCellXib iphoneLayout = new IphoneTableViewCellXib(chirld.enname,maxbean,"ScrollViewCell");
							
							scrollDeclare += firstCharToUpperAndJavaName(chirld.enname) + "ScrollViewCell *"
									+ chirld.enname + " = [[[NSBundle mainBundle] loadNibNamed:@\""
									+ firstCharToUpperAndJavaName(chirld.enname)
									+ "ScrollViewCell\"  owner:self options:nil] lastObject];\n";
							scrollDeclare += "  [" + chirld.enname + " setFrame:CGRectMake(x, y+height, width, "
									+ chirld.enname + ".frame.size.height)];\n";

							scrollDeclare += " height+=" + chirld.enname + ".frame.size.height;\n";

							scrollDeclare += " [self." + bean.enname.replace("Layout", "") + " addSubview:" + chirld.enname + "];\n\n";
							scrollDeclare += " [views addObject:" + chirld.enname + "];\n\n";
						} else

						{

							scrollDeclare += "  [" + chirld.enname + " setFrame:CGRectMake(x, y+height, width, "
									+ chirld.enname + ".frame.size.height)];\n";

							scrollDeclare += " height+=" + chirld.enname + ".frame.size.height;\n\n";
						}
					}
					parent(chirld);

				} else {
					String selfString = "self.";
					//if (bean.isRunTimeAddScrollView) {
						selfString = isRunTimeAddScrollView_LayoutName+".";
					//}
					compomentDeclareImplement.chirld(chirld, bean,selfString,"ScrollViewCell");
				
					scrollDeclare+=compomentDeclareImplement.viewDidLoad_Declare;
					
					if(closeKeyboardDeclare==null ||closeKeyboardDeclare.equals(""))
					{
					 closeKeyboardDeclare+=compomentDeclareImplement.closeKeyboardDeclare;
					 scrollImplement+="\n"+compomentDeclareImplement.closeKeyboardImplement+"\n";
					}
					
					scrollImplement+=compomentDeclareImplement.viewDidLoad_Implement;
					scrollEditTextCheck+=compomentDeclareImplement.editTextCheck;
					

				}
			}

			if (bean.type.equals("ScrollViewLayout")) {
				// end
				scrollDeclare += "//scrollView\n";
				scrollDeclare += "self."+bean.enname.replace("Layout", "")+".contentSize=CGSizeMake(width, height);\n\n";

				scrollDeclare += " UIEdgeInsets contentInsets = UIEdgeInsetsZero;\n";
				scrollDeclare += " self."+bean.enname.replace("Layout", "")+".contentInset = contentInsets;\n";
				scrollDeclare += " self."+bean.enname.replace("Layout", "")+".scrollIndicatorInsets = contentInsets;\n\n";

				scrollDeclare += " [self."+bean.enname.replace("Layout", "")+" setFrame:CGRectMake(0, self.headView.frame.size.height, self."+bean.enname.replace("Layout", "")+".frame.size.width, self.view.frame.size.height-self.headView.frame.size.height-self.bottom.frame.size.height)];\n";
				
				scrollDeclare+="\n\n//编辑框键盘顶起\n"+closeKeyboardDeclare+"\n//编辑框键盘顶起\n";
				
				scrollDeclare += "}\n";

			}

		}

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
}
