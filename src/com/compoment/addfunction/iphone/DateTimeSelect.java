package com.compoment.addfunction.iphone;

/**时间选择*/
public class DateTimeSelect {
	
	
	public String dateSelect(String clickName)
	{
		String m="";

		m+="  //----日期选择Start----\n";
		m+="UIButton *selectDateTimeBtn=nil;\n";
		m+="      #pragma mark -日期选择 \n";
		m+="      -(void) "+clickName+":(UIButton *)btn{\n";
		m+="selectDateTimeBtn=btn;\n";
		m+="          UIDatePicker *datePicker = [[UIDatePicker alloc] init];\n";
		m+="          datePicker.tag = 101;\n";
		m+="          datePicker.locale = [[NSLocale alloc] initWithLocaleIdentifier:@\"zh_CN\"];// 设置区域为中国简体中文\n";
		m+="          datePicker.datePickerMode = UIDatePickerModeDate; // 设置picker的显示模式：只显示日期\n";
		m+="          [datePicker setDate:[NSDate date] animated:YES]; // 设置日期控件值\n";
		m+="          [datePicker addTarget:self\n";
		m+="                         action:@selector(dateTimeValueChange:)\n";
		m+="               forControlEvents:UIControlEventValueChanged];  // 时间改变时触发此事件\n";
		m+="          \n";
		m+="          \n";
		m+="          \n";
		m+="  if ([[[UIDevice currentDevice] systemVersion] floatValue] <= 7.0) {\n";
		m+="          NSString *title = UIDeviceOrientationIsLandscape([UIDevice currentDevice].orientation) ? @\"\\n\\n\\n\\n\\n\\n\\n\\n\\n\" : @\"\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\";\n";
		m+="          \n";
		m+="          UIActionSheet* startsheet = [[UIActionSheet alloc] initWithTitle:title\n";
		m+="                                                                  delegate:self\n";
		m+="                                                         cancelButtonTitle:@\"确定\"\n";
		m+="                                                    destructiveButtonTitle:nil\n";
		m+="                                                         otherButtonTitles:nil,\n";
		m+="                                       nil];\n";
		m+="          [startsheet addSubview:datePicker];\n";
		m+="          [startsheet showInView:self.view];\n";
		m+="          \n";
	
		m+="}else{\n";
	
		m+="          \n";
		m+="          UIAlertController *alert = [UIAlertController alertControllerWithTitle:@\"\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\\n\" message:nil 　　preferredStyle:UIAlertControllerStyleActionSheet];\n";
		m+="          \n";
		m+="          [alert.view addSubview:datePicker];\n";
		m+="          \n";
		m+="          UIAlertAction *ok = [UIAlertAction actionWithTitle:@\"确定\" style:UIAlertActionStyleDefault handler:^(UIAlertAction *action) {\n";
		m+="              \n";
		m+="              NSDateFormatter* dateFormat = [[NSDateFormatter alloc] init];\n";
		m+="              \n";
		m+="              //实例化一个NSDateFormatter对象\n";
		m+="              \n";
		m+="              [dateFormat setDateFormat:@\"yyyy年MM月dd日\"];//设定时间格式\n";
		m+="              \n";
		m+="              NSString *timestamp = [dateFormat stringFromDate:datePicker.date];\n";
		m+="              \n";
		m+="    [selectDateTimeBtn setTitle:timestamp forState:UIControlStateNormal];\n";
		m+="      [selectDateTimeBtn setTitle:timestamp forState:UIControlStateSelected];\n";
		m+="              \n";
		m+="          }];\n";
		m+="          \n";
		m+="          \n";
		m+="          UIAlertAction *cancel = [UIAlertAction actionWithTitle:@\"取消\" style:UIAlertActionStyleDefault handler:^(UIAlertAction *action) {\n";
		m+="              \n";
		m+="              　 }];\n";
		m+="          \n";
		m+="          [alert addAction:ok];\n";
		m+="          \n";
		m+="          [alert addAction:cancel];\n";
		m+="          \n";
		m+="          [self presentViewController:alert animated:YES completion:^{ }];\n";
		m+="          \n";
		m+="          \n";
		m+="}\n";
		m+="          \n";
		m+="          \n";
		m+="      }\n";
		m+="      \n";
	
		m+="      //点选择按钮时触发此事件\n";
		m+="      -(void) actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex{\n";
		m+="          UIDatePicker *datePicker = (UIDatePicker *)[actionSheet viewWithTag:101];\n";
		m+="          NSDateFormatter *formattor = [[NSDateFormatter alloc] init];\n";
		m+="          \n";
		m+="          \n";
		m+="          formattor.dateFormat = @\"yyyy年MM月dd日\";\n";
		m+="          \n";
		m+="          NSString *timestamp = [formattor stringFromDate:datePicker.date];\n";
		m+="          \n";
		m+="          \n";
		
		m+="    [selectDateTimeBtn setTitle:timestamp forState:UIControlStateNormal];\n";
		m+="      [selectDateTimeBtn setTitle:timestamp forState:UIControlStateSelected];\n";
	
		m+="      }\n\n";
		
		m+="      // 时间改变时触发此事件\n";
		m+="      -(void)dateTimeValueChange:(UIDatePicker*)datepick\n";
		m+="      {\n";
		m+="          NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];\n";
		m+="          [dateFormatter setDateFormat:@\"yyyy年MM月dd日\"];\n";
		m+="          NSString *timestamp =  [dateFormatter stringFromDate: [datepick date]];\n";
		m+="          \n";
		m+="    [selectDateTimeBtn setTitle:timestamp forState:UIControlStateNormal];\n";
		m+="      [selectDateTimeBtn setTitle:timestamp forState:UIControlStateSelected];\n";
		m+="      }\n";
		m+="      \n";
		m+="      \n";
		m+="      //----日期选择End----\n";
		
		
		return m;
	}

	
	public static void main(String[] args) {
		DateTimeSelect dateTimeSelect=new DateTimeSelect();
		dateTimeSelect.dateSelect("");
	}
}
