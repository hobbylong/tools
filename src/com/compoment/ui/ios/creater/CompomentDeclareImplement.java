package com.compoment.ui.ios.creater;

import com.compoment.addfunction.iphone.BaseSelecter;
import com.compoment.addfunction.iphone.DateTimeSelect;
import com.compoment.cut.CompomentBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class CompomentDeclareImplement {
	
	
	public String i="";
	public String viewDidLoad_Declare="";
	public String viewDidLoad_Implement="";
	public String editTextCheck;

	public String closeKeyboardDeclare="";
	public String closeKeyboardImplement="";
	public String tablem="";
	

	

	public void chirld(CompomentBean chirld, CompomentBean parent,String selfString ,String comeFromWhere ) {

		i="";
		viewDidLoad_Declare="";
		viewDidLoad_Implement="";
		editTextCheck="";
	
			closeKeyboardDeclare="";
			closeKeyboardImplement="";
		 tablem="";


		if (chirld.type.equals("TextView")) {

			i += "//" + chirld.cnname + "\n";
			i += "@synthesize " + chirld.enname + ";\n";

			viewDidLoad_Declare += "//" + chirld.cnname + "\n";
			
            if ("数字转义成汉字".equals( chirld.actionString)) {
				
				viewDidLoad_Declare += "SqlApp *sqlApp=[[SqlApp alloc ]init ];\n";
				viewDidLoad_Declare += "NSString *"+chirld.interfaceColumnEnName+"Cn=[sqlApp selectTransferredMeaningByCode:"+chirld.interfaceColumnEnName+"];\n";
				viewDidLoad_Declare += "[" +selfString +chirld.enname + " setText:[NSString stringWithFormat:@\"%@\","+chirld.interfaceColumnEnName+"Cn]];\n";
            }else if("时间格式化".equals(chirld.actionString))
            {
            	
            	viewDidLoad_Declare += "{\n";
            	viewDidLoad_Declare += "NSDateFormatter* sourceDateFormat = [[NSDateFormatter alloc] init];\n";
               
            	viewDidLoad_Declare += "[sourceDateFormat setDateFormat:@\"yyyyMMddHHmmss\"];\n";
         
            	viewDidLoad_Declare += "NSDate *date=[sourceDateFormat dateFromString:[NSString stringWithFormat:@\"%@\","+chirld.interfaceColumnEnName+"]];\n";
                
                
            	viewDidLoad_Declare += "NSDateFormatter* desDateFormat = [[NSDateFormatter alloc] init];\n";
         
                
            	viewDidLoad_Declare += "[desDateFormat setDateFormat:@\"yyyy年MM月dd日HH:mm:ss\"];//设定时间格式\n";
                
            	viewDidLoad_Declare += "NSString *"+chirld.interfaceColumnEnName+"Time=[desDateFormat stringFromDate:date];\n";
            	
            	viewDidLoad_Declare += "[" +selfString +chirld.enname + " setText:[NSString stringWithFormat:@\"￥%@元\","+chirld.interfaceColumnEnName+"Time]];\n";
            	
            	viewDidLoad_Declare += "}\n";
            	
            }else if("金额格式化".equals(chirld.actionString))
            {
            	
            	viewDidLoad_Declare += "[" +selfString +chirld.enname + " setText:[NSString stringWithFormat:@\"￥%.2f元\","+chirld.interfaceColumnEnName+"]];\n";
                
            }
            else
			{
				
				viewDidLoad_Declare += "[" +selfString +chirld.enname + " setText:"+chirld.interfaceColumnEnName+"];\n";
			}
			
			
			if(chirld.isRunTimeHeightTextview)
			{
			viewDidLoad_Declare += "//start换行高度\n";
			viewDidLoad_Declare += "   ["+selfString+chirld.enname+" setNumberOfLines:0];\n";
			viewDidLoad_Declare += ""+selfString+chirld.enname+".lineBreakMode = NSLineBreakByWordWrapping;\n";
			viewDidLoad_Declare += " CGSize   size"+chirld.enname+" = [ "+selfString+chirld.enname+"  sizeThatFits:CGSizeMake("+selfString+chirld.enname+".frame.size.width, MAXFLOAT)];\n";
			viewDidLoad_Declare += " ["+selfString+chirld.enname+" setFrame:CGRectMake("+selfString+chirld.enname+".frame.origin.x \n";
			viewDidLoad_Declare += "         , "+selfString+chirld.enname+".frame.origin.y, "+selfString+chirld.enname+".frame.size.width, size"+chirld.enname+".height)];\n";
			viewDidLoad_Declare += "//end换行高度\n\n";
			}
			
			
			
			
		}

		if (chirld.type.equals("Button")) {
			
			uibuttonEnlarge();
			
			i += "//" + chirld.cnname + "\n";
			i += "@synthesize " + chirld.enname + ";\n";

			viewDidLoad_Declare += "\n//" + chirld.cnname + "\n";
			if(!comeFromWhere.equals("ViewController_viewdidload"))
			{
			viewDidLoad_Declare += selfString + chirld.enname + ".tag=;\n";
			viewDidLoad_Declare += "// objc_setAssociatedObject(" + selfString + chirld.enname
					+ ", \"mId\", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
			}
			viewDidLoad_Declare += "[" + selfString + chirld.enname + " addTarget:self action:@selector("
					+ chirld.enname + "Clicked:) forControlEvents:UIControlEventTouchUpInside];\n";
			
			if(!chirld.picName.equals("图片名"))
			{
			viewDidLoad_Declare += "[" + selfString + chirld.enname
					+ " setBackgroundImage:[UIImage imageNamed:@\""+chirld.picName+"Select.png\"] forState:UIControlStateSelected];\n";
			viewDidLoad_Declare += " [" + selfString + chirld.enname
					+ " setBackgroundImage:[UIImage imageNamed:@\""+chirld.picName+".png\"] forState:UIControlStateNormal];\n";
			viewDidLoad_Declare += "[" + selfString + chirld.enname + " setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域\n\n";
			}

			
			 if("日期选择器".equals( chirld.actionString))
			{
				
				 viewDidLoad_Declare+=" if (.timeValue==nil) {\n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="            NSDateFormatter* dateFormat = [[NSDateFormatter alloc] init];\n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="            [dateFormat setDateFormat:@\"yyyy年MM月dd日\"];\n";
				 viewDidLoad_Declare+="            NSString *currentDateTimeCn = [dateFormat stringFromDate:[NSDate date]];\n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="        \n";
				 viewDidLoad_Declare+="            [ "+ selfString + chirld.enname +" setTitle:currentDateTimeCn forState:UIControlStateNormal];\n";
				 viewDidLoad_Declare+="            [ "+ selfString + chirld.enname +" setTitle:currentDateTimeCn forState:UIControlStateSelected];\n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="            NSDateFormatter* dateFormat1 = [[NSDateFormatter alloc] init];\n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="            [dateFormat1 setDateFormat:@\"yyyyMMdd\"];\n";
				 viewDidLoad_Declare+="            NSString *currentDateTimeEn = [dateFormat1 stringFromDate:[NSDate date]];\n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="            .timeValue=currentDateTimeEn;\n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="        }else\n";
				 viewDidLoad_Declare+="        {\n";
				 viewDidLoad_Declare+="        \n";
				 viewDidLoad_Declare+="            NSDateFormatter* dateFormat = [[NSDateFormatter alloc] init];\n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="            [dateFormat setDateFormat:@\"yyyyMMdd\"];\n";
				 viewDidLoad_Declare+="            NSDate  *date  = [dateFormat dateFromString:.timeValue];\n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="            NSDateFormatter* dateFormat1 = [[NSDateFormatter alloc] init];\n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="            [dateFormat1 setDateFormat:@\"yyyy年MM月dd日\"];\n";
				 viewDidLoad_Declare+="            NSString *dateTimeCn = [dateFormat1 stringFromDate:date];\n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="            [ "+ selfString + chirld.enname +" setTitle:dateTimeCn forState:UIControlStateNormal];\n";
				 viewDidLoad_Declare+="            [ "+ selfString + chirld.enname +" setTitle:dateTimeCn forState:UIControlStateSelected];\n";
				 viewDidLoad_Declare+="            \n";
				 viewDidLoad_Declare+="        }\n\n";

				DateTimeSelect dateTimeSelect=new DateTimeSelect();
				viewDidLoad_Implement+=dateTimeSelect.dateSelect(chirld.enname + "Clicked");
			}else
			{
			
			
			viewDidLoad_Implement += "-(void)" + chirld.enname + "Clicked:(UIButton *)btn{\n";
			viewDidLoad_Implement += "//id mId = objc_getAssociatedObject(btn, \"mId\");\n//取绑定数据";
			viewDidLoad_Implement += "int mId2 = btn.tag;\n//取绑定数据";

			// ("跳到") ("单选") ("发请求") ("弹出")
			if ("跳到".equals( chirld.actionString)) {
				viewDidLoad_Implement += " self.hidesBottomBarWhenPushed=YES;\n";
				viewDidLoad_Implement += firstCharToUpperAndJavaName(chirld.jumpToWhichPage)
						+ "ViewController *" + chirld.jumpToWhichPage + "ViewController=[["
						+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage)
						+ "ViewController alloc ] initWithNibName:@\""
						+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage) + "ViewController\" bundle:nil];\n";
				viewDidLoad_Implement += "    [self.navigationController pushViewController:"
						+ chirld.jumpToWhichPage + "ViewController animated:YES];\n";
			} else if ("跳回到上几个".equals( chirld.actionString)) {
				viewDidLoad_Implement += "//方法1.回到上几个页面\n";
				viewDidLoad_Implement += " for (UIViewController *controller in self.navigationController.viewControllers) {\n";
				viewDidLoad_Implement += "       if (![controller isKindOfClass:[FirstPageViewController class]]) {\n";
				viewDidLoad_Implement += "            [controller removeFromParentViewController];\n";
				viewDidLoad_Implement += "  }\n";
				viewDidLoad_Implement += "//方法2.回到上几个页面\n";
				viewDidLoad_Implement += "//  [self.navigationController popToViewController:controller animated:YES];\n\n";
				viewDidLoad_Implement += " }\n";
				viewDidLoad_Implement += "	//    AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];\n";
				viewDidLoad_Implement += "	 //   [appDelegate.tabbar setSelectedIndex:0];\n\n";

				viewDidLoad_Implement += "//方法3.回到上几个页面\n";
				viewDidLoad_Implement += "  [self.navigationController popToRootViewControllerAnimated:YES];\n";

			} else if ("跳回到上个".equals( chirld.actionString)) {

				viewDidLoad_Implement += "//方法3.回到上页面\n";
				viewDidLoad_Implement += "  [self.navigationController popViewControllerAnimated:YES];\n";

			} else if ( "单选".equals( chirld.actionString)) {

			} else if ( "发请求".equals( chirld.actionString)) {
				viewDidLoad_Implement += " MsgReturn *msgReturn=[[MsgReturn alloc]init];\n";
				viewDidLoad_Implement += " msgReturn.errorCode=@\"-1\";//-1显示自定义内容\n";
				viewDidLoad_Implement += " msgReturn.errorType=@\"02\";\n";
				viewDidLoad_Implement += " msgReturn.errorDesc=@\"请输入搜索内容\";\n";
				viewDidLoad_Implement += " [PromptError changeShowErrorMsg:msgReturn title:@\"\"  viewController:self block:^(BOOL OKCancel){}];\n\n";
				viewDidLoad_Implement += "[self request" + chirld.interfaceId + "];\n";

			} else if ("弹出".equals( chirld.actionString)) {

				viewDidLoad_Implement += "if(menu==nil){";
				viewDidLoad_Implement += "menu=[[" + firstCharToUpperAndJavaName(chirld.jumpToWhichPage)
						+ "MenuViewController alloc ] initWithNibName:@\""
						+ firstCharToUpperAndJavaName(chirld.jumpToWhichPage) + "MenuViewController\" bundle:nil];\n";

				viewDidLoad_Implement += "[menu.view setFrame:CGRectMake(menu.frame.origin.x, menu.frame.origin.y-menu.frame.size.height , .frame.size.width, .frame.size.height)];\n";

				viewDidLoad_Implement += "[self.view addSubview:menu.view];\n";

				viewDidLoad_Implement += "[menu setUiValue:datas  delegate:self];\n";
				viewDidLoad_Implement += "menu.view.hidden=YES;\n";
				viewDidLoad_Implement += "}\n\n";

				viewDidLoad_Implement += "if (menu.view.hidden) {\n";
				viewDidLoad_Implement += "    menu.view.hidden=NO;\n";
				viewDidLoad_Implement += "}else\n";
				viewDidLoad_Implement += "{\n";
				viewDidLoad_Implement += "    menu.view.hidden=YES;\n";
				viewDidLoad_Implement += "}\n";

			}else if("通用选择器".equals( chirld.actionString)){
				BaseSelecter baseSelecter=new BaseSelecter(chirld.enname);
				viewDidLoad_Implement+=baseSelecter.commonSelector();
			}
			else if("省市县选择器".equals( chirld.actionString)){
				BaseSelecter baseSelecter=new BaseSelecter(chirld.enname);
				viewDidLoad_Implement+=baseSelecter.proCityCountySqlApp();
			}
			

			viewDidLoad_Implement += "\n}\n\n";
			}

		}

		if (chirld.type.equals("EditText")) {
			
			

			i += "//" + chirld.cnname + "\n";
			i += "@synthesize " + chirld.enname + ";\n";

		
			viewDidLoad_Declare += "\n//" + chirld.cnname + "\n";
			viewDidLoad_Declare += " \n" + selfString + chirld.enname + ".returnKeyType=UIReturnKeyDone;\n\n";
			
			if(!comeFromWhere.equals("ViewController_viewdidload"))
			{
			viewDidLoad_Declare += "" + selfString + chirld.enname + ".tag=;\n";
			viewDidLoad_Declare += "// objc_setAssociatedObject(" + selfString + chirld.enname
					+ ", \"mId\", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
			}
			
			
			viewDidLoad_Declare += "[" + selfString + chirld.enname + " addTarget:self action:@selector("
					+ chirld.enname + "DidEndOnExit:) forControlEvents:UIControlEventEditingDidEndOnExit];\n\n";

			viewDidLoad_Implement += "-(void)" + chirld.enname + "DidEndOnExit:(UITextField *)textField{\n";
			viewDidLoad_Implement += " [self.view becomeFirstResponder];//把焦点给别人 键盘消失\n";
			viewDidLoad_Implement += " int  orderFormIndex= textField.tag;\n";
			viewDidLoad_Implement += "     OrderForm *orderform=orderForms[orderFormIndex ];\n";
			viewDidLoad_Implement += "     orderform.invoiceMsg=textField.text;\n";
			viewDidLoad_Implement += "}\n\n";

		
			viewDidLoad_Declare += "[" + selfString + chirld.enname + " addTarget:self action:@selector("
					+ chirld.enname + "DidEnd:) forControlEvents:UIControlEventEditingDidEnd];\n\n";
		

			viewDidLoad_Implement += "-(void)" + chirld.enname + "DidEnd:(UITextField *)textField{\n";
			viewDidLoad_Implement += " [self.view becomeFirstResponder];//把焦点给别人 键盘消失\n";
			viewDidLoad_Implement += "//id mId = objc_getAssociatedObject(btn, \"mId\");\n//取绑定数据";
			viewDidLoad_Implement += " int  orderFormIndex= textField.tag;\n";
			viewDidLoad_Implement += "     OrderForm *orderform=orderForms[orderFormIndex ];\n";
			viewDidLoad_Implement += "     orderform.invoiceMsg=textField.text;\n";
			viewDidLoad_Implement += "}\n\n";
			
			
//			viewDidLoad_Declare+="[[NSNotificationCenter defaultCenter] addObserver:self selector:@selector("+chirld.enname+"DidValueChanged:) name:UITextFieldTextDidChangeNotification object:"+selfString+"."+chirld.enname+"];\n";
//			
//			viewDidLoad_Implement += "-(void)"+chirld.enname+"DidValueChanged:(NSNotification *)notification\n";
//			viewDidLoad_Implement += "{\n";
//			viewDidLoad_Implement += "   UITextField *textfield=[notification object];\n";
//			viewDidLoad_Implement += "   NSString *text=textfield.text;\n";
//			viewDidLoad_Implement += "}\n\n";
			
			
			viewDidLoad_Declare+="[  self."+chirld.enname+"  addTarget:self action:@selector(textFieldDidChange_"+chirld.enname+":) forControlEvents:UIControlEventEditingChanged];\n";
			
			viewDidLoad_Implement += "- (void)textFieldDidChange_"+chirld.enname+":(UITextField *)textField\n";
			viewDidLoad_Implement += "{\n";
			viewDidLoad_Implement += "  if (textField.text.length > 7) {\n";
            viewDidLoad_Implement += "   textField.text = [textField.text substringToIndex:7];\n";
			viewDidLoad_Implement += " }\n";
		    viewDidLoad_Implement += "}\n";
			
			viewDidLoad_Declare+="    UITapGestureRecognizer *"+chirld.enname+"TapGuest=[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(editTextTapHandle:)];\n";
			viewDidLoad_Declare+=chirld.enname+"TapGuest.delegate = self;//头文件<UIGestureRecognizerDelegate>\n"; 
			viewDidLoad_Declare+="    [ "+ selfString + chirld.enname+"  addGestureRecognizer:"+chirld.enname+"TapGuest];\n\n";
			
		
	
				
			
			
			closeKeyboardDeclare+=" \n//键盘顶起\n   UITapGestureRecognizer* closeKeyboardtap =[[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(closeKeyboardBlankPlaceTapHandle:)];\n";
		
			closeKeyboardDeclare+="    [self.view addGestureRecognizer:closeKeyboardtap];\n";
		
			closeKeyboardDeclare+="    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillShow:) name:UIKeyboardDidShowNotification object:nil];\n";
			closeKeyboardDeclare+="    \n";
			closeKeyboardDeclare+="    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillHide:) name:UIKeyboardDidHideNotification object:nil];\n";

					
					
				
			
			
			
			closeKeyboardImplement+="//编辑框键盘顶起start\n";

			closeKeyboardImplement+="   float touchy1;\n";
			closeKeyboardImplement+="   int   movelength1;\n";
			closeKeyboardImplement+="   int  keyboardHeight1;\n";
			closeKeyboardImplement+="   bool keyboardOpen;\n\n";
			
			closeKeyboardImplement+="//注销键盘监听\n";
			closeKeyboardImplement+="-(void)viewDidDisappear:(BOOL)animated\n";
			closeKeyboardImplement+="{\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardDidShowNotification object:nil];\n";
			closeKeyboardImplement+="    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardDidHideNotification object:nil];\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="}\n\n";



			closeKeyboardImplement+="//<UIGestureRecognizerDelegate>\n";
			closeKeyboardImplement+="- (BOOL)gestureRecognizer:(UIGestureRecognizer *)gestureRecognizer shouldRecognizeSimultaneouslyWithGestureRecognizer:(UIGestureRecognizer *)otherGestureRecognizer\n";
			closeKeyboardImplement+="{\n";
			closeKeyboardImplement+="    return YES;\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="}\n\n";


			closeKeyboardImplement+="//点空白区域键盘消失\n";
			closeKeyboardImplement+="-(void)closeKeyboardBlankPlaceTapHandle:(UITapGestureRecognizer *)sender\n";

			closeKeyboardImplement+="{\n";
			closeKeyboardImplement+="  \n";
			closeKeyboardImplement+="    if (keyboardOpen) {\n";
			closeKeyboardImplement+="        [[[UIApplication sharedApplication] keyWindow] endEditing:YES];\n";
			closeKeyboardImplement+="    }\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="}\n\n";

			closeKeyboardImplement+="//touchY 触摸位置\n";
			closeKeyboardImplement+="-(void)editTextTapHandle:(UITapGestureRecognizer *)sender\n";

			closeKeyboardImplement+="{\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="     if (keyboardOpen) {\n";
			closeKeyboardImplement+="         return;\n";
			closeKeyboardImplement+="     }\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    CGPoint point = [sender locationInView:self.view];\n";
			closeKeyboardImplement+="    touchy1=point.y+15;\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    if(keyboardHeight1>0)\n";
			closeKeyboardImplement+="    {\n";
			closeKeyboardImplement+="        if(touchy1>keyboardHeight1)\n";
			closeKeyboardImplement+="        {\n";
			closeKeyboardImplement+="            movelength1=touchy1-keyboardHeight1;\n";
			closeKeyboardImplement+="            [self MoveView:(-movelength1)];\n";
			closeKeyboardImplement+="        }else\n";
			closeKeyboardImplement+="        {\n";
			closeKeyboardImplement+="            movelength1=0;\n";
			closeKeyboardImplement+="            [self MoveView:(-movelength1)];\n";
			closeKeyboardImplement+="        }\n";
			closeKeyboardImplement+="    }\n";
			closeKeyboardImplement+="    \n";

			closeKeyboardImplement+="}\n\n";




			closeKeyboardImplement+="//键盘打开监听回调\n";
			closeKeyboardImplement+="- (void)keyboardWillShow:(NSNotification *)notification {\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    keyboardOpen=true;\n";
			closeKeyboardImplement+="    /*\n";
			closeKeyboardImplement+="     Reduce the size of the text view so that it's not obscured by the keyboard.\n";
			closeKeyboardImplement+="     Animate the resize so that it's in sync with the appearance of the keyboard.\n";
			closeKeyboardImplement+="     */\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    NSDictionary *userInfo = [notification userInfo];\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    // Get the origin of the keyboard when it's displayed.\n";
			closeKeyboardImplement+="    NSValue* aValue = [userInfo objectForKey:UIKeyboardFrameEndUserInfoKey];\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    // Get the top of the keyboard as the y coordinate of its origin in self's view's coordinate system. The bottom of the text view's frame should align with the top of the keyboard's final position.\n";
			closeKeyboardImplement+="    CGRect keyboardRect = [aValue CGRectValue];\n";
			closeKeyboardImplement+="    keyboardRect = [self.view convertRect:keyboardRect fromView:nil];\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    float keyboardTop = keyboardRect.origin.y;\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    if(keyboardHeight1==0)\n";
			closeKeyboardImplement+="    {\n";
			closeKeyboardImplement+="        // Get the duration of the animation.\n";
			closeKeyboardImplement+="        NSValue *animationDurationValue = [userInfo objectForKey:UIKeyboardAnimationDurationUserInfoKey];\n";
			closeKeyboardImplement+="        NSTimeInterval animationDuration;\n";
			closeKeyboardImplement+="        [animationDurationValue getValue:&animationDuration];\n";
			closeKeyboardImplement+="        //\n";
			closeKeyboardImplement+="        //    // Animate the resize of the text view's frame in sync with the keyboard's appearance.\n";
			closeKeyboardImplement+="        [UIView beginAnimations:nil context:NULL];\n";
			closeKeyboardImplement+="        [UIView setAnimationDuration:animationDuration];\n";
			closeKeyboardImplement+="        \n";
			closeKeyboardImplement+="        keyboardHeight1=keyboardTop;\n";
			closeKeyboardImplement+="        \n";
			closeKeyboardImplement+="        if(touchy1>keyboardHeight1)\n";
			closeKeyboardImplement+="        {\n";
			closeKeyboardImplement+="            movelength1=touchy1-keyboardHeight1;\n";
			closeKeyboardImplement+="            [self MoveView:(-movelength1)];\n";
			closeKeyboardImplement+="        }else\n";
			closeKeyboardImplement+="        {\n";
			closeKeyboardImplement+="            movelength1=0;\n";
			closeKeyboardImplement+="            [self MoveView:(-movelength1)];\n";
			closeKeyboardImplement+="        }\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="        \n";
			closeKeyboardImplement+="           [UIView commitAnimations];\n";
			closeKeyboardImplement+="    }\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+=" \n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="}\n\n";

			closeKeyboardImplement+="//键盘关闭监听回调\n";
			closeKeyboardImplement+="- (void)keyboardWillHide:(NSNotification *)notification {\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="      keyboardOpen=false;\n";
			closeKeyboardImplement+="    NSDictionary* userInfo = [notification userInfo];\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    /*\n";
			closeKeyboardImplement+="     Restore the size of the text view (fill self's view).\n";
			closeKeyboardImplement+="     Animate the resize so that it's in sync with the disappearance of the keyboard.\n";
			closeKeyboardImplement+="     */\n";
			closeKeyboardImplement+="    NSValue *animationDurationValue = [userInfo objectForKey:UIKeyboardAnimationDurationUserInfoKey];\n";
			closeKeyboardImplement+="    NSTimeInterval animationDuration;\n";
			closeKeyboardImplement+="    [animationDurationValue getValue:&animationDuration];\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    [UIView beginAnimations:nil context:NULL];\n";
			closeKeyboardImplement+="    [UIView setAnimationDuration:animationDuration];\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    if(movelength1!=0){\n";
			closeKeyboardImplement+="    [self MoveView:(movelength1)];\n";
			closeKeyboardImplement+="    movelength1=0;\n}\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    [UIView commitAnimations];\n";
			closeKeyboardImplement+="}\n\n";




			closeKeyboardImplement+="-(void)MoveView:(int)h{\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    if (h<0) {\n";
			closeKeyboardImplement+="        [UIView beginAnimations:nil context:nil];\n";
			closeKeyboardImplement+="        [UIView setAnimationDuration:0.3];\n";
			closeKeyboardImplement+="        [UIView setAnimationBeginsFromCurrentState: YES];\n";
			closeKeyboardImplement+="        [self.contain setFrame: CGRectMake(self.contain.frame.origin.x, self.contain.frame.origin.y + h, self.contain.frame.size.width, self.contain.frame.size.height)];\n";
			closeKeyboardImplement+="        [UIView commitAnimations];\n";
			closeKeyboardImplement+="    }else\n";
			closeKeyboardImplement+="    {\n";
			closeKeyboardImplement+="        [UIView beginAnimations:nil context:nil];\n";
			closeKeyboardImplement+="        [UIView setAnimationDuration:0.3];\n";
			closeKeyboardImplement+="        [UIView setAnimationBeginsFromCurrentState: YES];\n";
			closeKeyboardImplement+="        [self.contain setFrame: CGRectMake(self.contain.frame.origin.x, self.contain.frame.origin.y+h , self.contain.frame.size.width, self.contain.frame.size.height)];\n";
			closeKeyboardImplement+="        [UIView commitAnimations];\n";
			closeKeyboardImplement+="    }\n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="    \n";
			closeKeyboardImplement+="}\n";

			closeKeyboardImplement+="//编辑框键盘顶起end\n\n";
			
		




		
			
			editTextCheck=" \n if("+selfString +"."+ chirld.enname +".text==nil || ["+selfString+"."+chirld.enname+".text isEqualToString:@\"\"])\n";
			editTextCheck=" {\n";
			editTextCheck="   MsgReturn *msgReturn=[[MsgReturn alloc]init];\n";
		    editTextCheck="   msgReturn.errorDesc=@\"请输入"+chirld.cnname+"\";//不能为空\n";
			editTextCheck="   msgReturn.errorType=@\"02\";//自动消失提示\n";
			editTextCheck="   msgReturn.errorCode=@\"-3\";\n";
			editTextCheck="   msgReturn.errorPic=true;//图片为X \n";
			editTextCheck="     [PromptError changeShowErrorMsg:msgReturn title:@\"\" viewController:self block:^(BOOL OKCancel){} ];\n";
			editTextCheck="       return false;\n";
			editTextCheck="     }\n";
		    editTextCheck="    bean"+chirld.interfaceId+"."+chirld.interfaceColumnEnName+"="+selfString +"."+ chirld.enname +".text;\n\n";
			

		}

		if (chirld.type.equals("CheckBox")) {

			uibuttonEnlarge();
			
			i += "//" + chirld.cnname + "\n";
			i += "@synthesize " + chirld.enname + ";\n";

			
			viewDidLoad_Declare += "\n//" + chirld.cnname + "\n";
			
			if(!comeFromWhere.equals("ViewController_viewdidload"))
			{
			viewDidLoad_Declare += "" + selfString + chirld.enname + ".tag=;\n";
			viewDidLoad_Declare += " objc_setAssociatedObject(" + selfString + chirld.enname
					+ ", \"mId\", productId, OBJC_ASSOCIATION_RETAIN_NONATOMIC);//控件与数据绑定\n";
			}
			viewDidLoad_Declare += "[" + selfString + chirld.enname + " setSelected:];\n";
			viewDidLoad_Declare += "\n[" + selfString + chirld.enname + " addTarget:self action:@selector("
					+ chirld.enname + "Check:) forControlEvents:UIControlEventTouchUpInside];\n";
			viewDidLoad_Declare += "[" + selfString + chirld.enname
					+ " setBackgroundImage:[UIImage imageNamed:@\"check.png\"] forState:UIControlStateSelected];\n";
			viewDidLoad_Declare += " [" + selfString + chirld.enname
					+ " setBackgroundImage:[UIImage imageNamed:@\"uncheck.png\"] forState:UIControlStateNormal];\n";
			viewDidLoad_Declare += "[" + selfString + chirld.enname + " setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域\n\n";

			viewDidLoad_Declare += "\n\n[" + selfString + chirld.enname + "Cover setText:nil ];\n";
			
			viewDidLoad_Implement += "-(void)" + chirld.enname + "Check:(UIButton *)btn{\n";
			viewDidLoad_Implement += "//id mId = objc_getAssociatedObject(btn, \"mId\");//取绑定数据\n";
			viewDidLoad_Implement += "int mId2 = btn.tag;//取绑定数据\n";
			viewDidLoad_Implement += "  btn.selected = !btn.selected ;//用与button做checkBox\n";
			viewDidLoad_Implement += " OrderForm *orderform=datas[mId];\n";
			viewDidLoad_Implement += " if (orderform.invoiceCheck ) {//选中\n";
			viewDidLoad_Implement += "  orderform.invoiceCheck=false;\n";
			viewDidLoad_Implement += "}else\n";
			viewDidLoad_Implement += "{\n";
			viewDidLoad_Implement += "   orderform.invoiceCheck=true;\n";
			viewDidLoad_Implement += "}\n";
			viewDidLoad_Implement += "[self refreshUi];\n";
			viewDidLoad_Implement += "//[tableView reloadData];\n";
			viewDidLoad_Implement += "}\n\n";

		}

		if (chirld.type.equals("ListView")) {
			i += "//" + chirld.cnname + "\n";
			i += "@synthesize tableView;\n";

			
			
			tablem += "-(void)viewWillLayoutSubviews\n";
			tablem += "{\n";
			tablem += "int startY=self.headView.frame.origin.y+self.headView.frame.size.height;\n";
			tablem += " [self.tableView setFrame:CGRectMake(0, startY, self.tableView.frame.size.width, self.view.frame.size.height-startY )];\n";
			tablem += "}\n\n";
			
			
			

			tablem += "//指定有多少个分区(Section)，默认为1\n";
			tablem += "- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {\n";
			tablem += "    \n";
			tablem += "    return 1;//返回标题数组中元素的个数来确定分区的个数   return [sections count];\n";
			tablem += "}\n\n";

			tablem += "//指定每个分区中有多少行，默认为1\n";
			tablem += "- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{\n";
			tablem += "    \n";

			tablem += "     return  [rows count]+1;\n";
			tablem += "    \n";
			tablem += "}\n\n";

			tablem += "//绘制Cell\n";
			tablem += "-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {";
			
			if(chirld.isMutiPageListCheck)
			{
			tablem += " //分页 ";
			}
			if(chirld.isNineListCheck)
			{
				tablem += "//九宫格";
			}
			tablem += "   \n";
			

			tablem += "}\n\n";

			tablem += "//关键方法，获取复用的Cell后模拟赋值，然后取得Cell高度\n";
			tablem += "- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{  ";

			if(chirld.isMutiPageListCheck)
			{
			tablem += " //分页 ";
			}
			if(chirld.isNineListCheck)
			{
				tablem += "//九宫格";
			}
			tablem += "   \n";
			
			
			tablem += "}\n\n";

	

			tablem += "- (CGFloat)tableView:(UITableView *)tableView estimatedHeightForRowAtIndexPath:(NSIndexPath *)indexPath {\n";
			tablem += "    return 88;\n";
			tablem += "}\n\n";

			tablem += "//点击后，过段时间cell自动取消选中\n";
			tablem += "- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{\n";
			tablem += "if (indexPath.row == [rows count]  && [rows count]>0) {\n";
		    tablem += "[self request9999:YES];\n";
			tablem += "   return;\n";
			tablem += "}else{\n}\n";
					
			tablem += "    //消除cell选择痕迹\n";
			tablem += "    [self performSelector:@selector(deselect) withObject:nil afterDelay:0.05f];\n";
			tablem += "}\n";
			tablem += "- (void)deselect\n";
			tablem += "{\n";
			tablem += "    [self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];\n";
			tablem += "}\n";
		}

		if (chirld.type.equals("ImageView")) {

			i += "//" + chirld.cnname + "\n";
			i += "@synthesize " + chirld.enname + ";\n";

			viewDidLoad_Declare += "//" + chirld.cnname + "\n";
			viewDidLoad_Declare += "[" + chirld.enname + " setImage:[UIImage imageNamed:@\"1.jpeg\"]]\n";
			viewDidLoad_Declare += "[" + chirld.enname
					+ " setImageWithURL:[NSURL URLWithString:  placeholderImage:[UIImage imageNamed:@\"default.jpg\"]];\n";

		}
		
		if (chirld.type.equals("ScrollView")) {

		}

		

		if (chirld.type.equals("ExpandableListView")) {

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
	
	/**扩大点击区域*/
	public void uibuttonEnlarge()
	{
		
		
		String m="";
		m+="#import <Foundation/Foundation.h>\n";
		m+="#import <UIKit/UIKit.h>\n";
		m+="@interface UIButton(EnlargeTouchArea)\n";

		m+="- (void)setEnlargeEdgeWithTop:(CGFloat) top right:(CGFloat) right bottom:(CGFloat) bottom left:(CGFloat) left;\n";
		m+="@end\n";
		
		
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/ios", "UIButton+EnlargeTouchArea.",
				"h", m);

		m="";
		m+="#import \"UIButton+EnlargeTouchArea.h\"\n";
		m+="#import <objc/runtime.h>\n";

		m+="@implementation  UIButton(EnlargeTouchArea)\n";




		m+="- (void) setEnlargeEdgeWithTop:(CGFloat) top right:(CGFloat) right bottom:(CGFloat) bottom left:(CGFloat) left\n";
		m+="{\n";
		m+="    objc_setAssociatedObject(self, \"topNameKey\", [NSNumber numberWithFloat:top], OBJC_ASSOCIATION_COPY_NONATOMIC);\n";
		m+="    objc_setAssociatedObject(self, \"rightNameKey\", [NSNumber numberWithFloat:right], OBJC_ASSOCIATION_COPY_NONATOMIC);\n";
		m+="    objc_setAssociatedObject(self, \"bottomNameKey\", [NSNumber numberWithFloat:bottom], OBJC_ASSOCIATION_COPY_NONATOMIC);\n";
		m+="    objc_setAssociatedObject(self, \"leftNameKey\", [NSNumber numberWithFloat:left], OBJC_ASSOCIATION_COPY_NONATOMIC);\n";
		m+="}\n";

		m+="- (CGRect) enlargedRect\n";
		m+="{\n";
		m+="    NSNumber* topEdge = objc_getAssociatedObject(self, \"topNameKey\");\n";
		m+="    NSNumber* rightEdge = objc_getAssociatedObject(self, \"rightNameKey\");\n";
		m+="    NSNumber* bottomEdge = objc_getAssociatedObject(self, \"bottomNameKey\");\n";
		m+="    NSNumber* leftEdge = objc_getAssociatedObject(self, \"leftNameKey\");\n";
		m+="    if (topEdge && rightEdge && bottomEdge && leftEdge)\n";
		m+="    {\n";
		m+="        return CGRectMake(self.bounds.origin.x - leftEdge.floatValue,\n";
		m+="                          self.bounds.origin.y - topEdge.floatValue,\n";
		m+="                          self.bounds.size.width + leftEdge.floatValue + rightEdge.floatValue,\n";
		m+="                          self.bounds.size.height + topEdge.floatValue + bottomEdge.floatValue);\n";
		m+="    }\n";
		m+="    else\n";
		m+="    {\n";
		m+="        return self.bounds;\n";
		m+="    }\n";
		m+="}\n";

		m+="- (UIView*) hitTest:(CGPoint) point withEvent:(UIEvent*) event\n";
		m+="{\n";
		m+="    CGRect rect = [self enlargedRect];\n";
		m+="    if (CGRectEqualToRect(rect, self.bounds))\n";
		m+="    {\n";
		m+="        return [super hitTest:point withEvent:event];\n";
		m+="    }\n";
		m+="    return CGRectContainsPoint(rect, point) ? self : nil;\n";
		m+="}\n";

		m+="@end\n";
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/ios", "UIButton+EnlargeTouchArea.",
				"m", m);
		
	}

}
