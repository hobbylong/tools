package com.compoment.db.ios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compoment.creater.first.QuotationMarks;
import com.compoment.db.ios.CoredataDbDao.PropertyBean;
import com.compoment.gbkToUtf8.creater.GbkToUtf_FileOrDir;

public class CoredataDbSynchronize {
	List propertys = new ArrayList();
	String className = null;

	public static void main(String[] args) throws IOException {

		CoredataDbSynchronize mark = new CoredataDbSynchronize();
		mark.init(mark);
		 mark.hfile();
		mark.mfile();

	}

	public void init(CoredataDbSynchronize mark) {

		try {
			String classDir = mark.getClass().getResource("/").getPath();
			FileReader fr = new FileReader(classDir
					+ "com/compoment/db/ios/MarkBefor.txt");
			BufferedReader br = new BufferedReader(fr);

			String myreadline;

			while (br.ready()) {
				myreadline = br.readLine();

				if (myreadline.contains("@interface")) {
					int start = myreadline.indexOf("@");
					start = start + 10;
					int end = myreadline.indexOf(":");
					className = myreadline.substring(start, end).trim();
				} else if (myreadline.contains("@property")) {
					int start1 = myreadline.indexOf(")");
					int start2 = myreadline.indexOf("*");
					int end = myreadline.indexOf(";");

					String type = myreadline.substring(start1 + 1, start2)
							.trim();
					String name = myreadline.substring(start2 + 1, end).trim();
					PropertyBean bean = mark.new PropertyBean();
					bean.type = type;
					bean.name = name;
					propertys.add(bean);
				}

			}

			br.close();
			br.close();
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String firstCharToUpper(String string) {
		// buy_typelist

		string = string.substring(0, 1).toUpperCase() + string.substring(1);

		return string;
	}

	public class PropertyBean {

		public String type;// 类型
		public String name;// 属性名
	}

	public void hfile() {

		String m = "";

		m+="#import <Foundation/Foundation.h>\n";
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
		
			if(property.type.contains("NS"))
			{
			
			}else
			{
				
				m+="#import \""+property.type+".h\"\n";
				m+="#import \""+property.type+"Manager.h\"\n\n";
			}
			}

		m+="@interface "+className+"Synchronize : NSObject \n\n";
	
		m+="// 服务端数据同步到本地"+className+"\n";
		m+="- (void)sync"+className;
		int k=0;
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("ada") || property.type.equals("NSSet"))
			{
				continue;
			}
			if (k == 0) {
				m += ":(" + property.type
						+ " *)" + property.name + "\n";
			} else {
				m += property.name + ":(" + property.type + " *)"
						+ property.name + "\n";
			}
			k++;
		}
	
		m+=";\n\n";
		
		
		

		m+="- (void)sync"+className+"DicArray:(NSArray *)"+className.toLowerCase()+"DicArray;\n\n";

		m+="//查询待同步（"+className+"新增或修改的记录）\n";
		m+="- (NSArray *)unSync"+className+"Array;\n\n";

		m+="- (NSArray *)unSync"+className+"DicArray;\n\n";

		m+="@end\n";


		System.out.println(m);
	}

	public void mfile() {
		String m = "########################\n\n";
			
		m+="#import \""+className+"Synchronize.h\"\n";
		m+="#import \""+className+"Manager.h\"\n";
		m+="#import \"CRM.h\"\n";
		m+="#import \""+className+"CategoryManager.h\"\n\n";

		m+="@implementation "+className+"Synchronize\n\n";

		m+="// 服务端数据同步到本地"+className+"\n";
		m+="- (void)sync"+className;
		int k=0;
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("ada")||property.type.equals("NSSet"))
			{
				continue;
			}
			if (k == 0) {
				m +=  ":(" + property.type
						+ " *)" + property.name + "\n";
			} else {
				m += property.name + ":(" + property.type + " *)"
						+ property.name + "\n";
			}
			k++;
		}
		m+=" {\n";
		m+="    \n";
		m+="    "+className+"Manager *"+className.toLowerCase()+"Manager = ["+className+"Manager sharedInstance];\n";
		m+="    "+className+" *"+className.toLowerCase()+" = ["+className.toLowerCase()+"Manager findByTerminalID:terminalID];\n";
		m+="    \n";
		m+="    if ("+className.toLowerCase()+" != nil ) {\n";
		m+="        //本地有记录\n";
		
		m+="        if ([status isEqualToNumber:@(CRM_ENTITY_NORMAL)]) {\n";
		m+="            if (["+className.toLowerCase()+".status isEqualToNumber:@(CRM_ENTITY_DELETED)]) {\n";
		m+="                return;\n";
		m+="            }\n";
		m+="        }\n";
		m+="        \n";
		m+="        ["+className.toLowerCase()+"Manager updateWith"+className+":"+className.toLowerCase()+"\n";
		
	
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("ada") ||property.type.equals("NSSet"))
			{
				continue;
			}
				m += property.name + ":"
						+ property.name + "\n";
			
		}
		m+="                   ];\n";
		m+="        \n";
		m+="    } else {\n";
		m+="        \n";
		m+="        ["+className.toLowerCase()+"Manager createWith";
		
	     k=0;
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("ada") ||property.type.equals("NSSet"))
			{
				continue;
			}
			if (k == 0) {
				m += firstCharToUpper(property.name) + ":" + property.name + "\n";
			} else {
				m += property.name + ":"
						+ property.name + "\n";
			}
			k++;
		}
		
		m+=" ];\n";
		m+="    }\n";
		m+="    return;\n";
		m+="}\n\n";
		
		

		m+="- (void)sync"+className+"DicArray:(NSArray *)"+className.toLowerCase()+"DicArray {\n";
		m+="    \n";
		m+="    for (NSDictionary *item in "+className.toLowerCase()+"DicArray) {\n";
		
			for (int i = 0; i < propertys.size(); i++) {
				PropertyBean property = (PropertyBean) propertys.get(i);
			
				if(property.type.contains("NS") && !property.type.equals("NSSet") )
				{
					if(property.type.equals("NSNumber"))
					{
						m+=property.type+" *"+property.name+" =[NSNumber numberWithInt:[[item objectForKey:@\""+property.name+"\"] intValue]];\n\n";
					}else if(property.type.equals("NSDate"))
					{
						m+="long "+property.name+"Long = (long)[item objectForKey:@\""+property.name+"\"];\n\n";
					
				       m+=" NSDate *"+property.name+" = [NSDate dateWithTimeIntervalSince1970:"+property.name+"Long];\n";
					}
					
					else
					{
				m+=property.type+" *"+property.name+" = [item objectForKey:@\""+property.name+"\"];\n\n";
					}
				}else if(property.type.contains("NS") && property.type.equals("NSSet") )
				{
					continue;
				}
				
				else
				{
					m+="NSString *"+property.name+"TerminalID = [item objectForKey:@\""+property.name+"TerminalID\"];\n";
					m+="        "+property.type+"Manager *"+property.type.toLowerCase()+"Manager =\n";
					m+="        ["+property.type+"Manager sharedInstance];\n\n";
					m+="        "+property.type+" *"+property.name+" =\n";
					m+="        ["+property.type.toLowerCase()+"Manager findByTerminalID:"+property.name+"TerminalID];\n\n";
				}
				}
		
		
		m+="        \n";
		m+="        [self sync"+className;
	      k=0;
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("ada")|| property.type.equals("NSSet"))
			{
				continue;
			}
			if (k == 0) {
				m +=  ":" + property.name + "\n";
			} else {
				m += property.name + ":"
						+ property.name + "\n";
			}
			k++;
		}
		m+=" ];\n";
		m+="    }\n";
		m+="}\n\n";

		
		
		m+="//查询待同步（"+className+"新增或修改的记录）\n";
		m+="- (NSArray *)unSync"+className+"Array {\n";
		m+="    "+className+"Manager *"+className.toLowerCase()+"Manager = ["+className+"Manager sharedInstance];\n";
		m+="    \n";
		m+="    NSPredicate *filter =\n";
		m+="    [NSPredicate predicateWithFormat:@\"status != %@\", @(CRM_ENTITY_NORMAL)];\n";
		m+="    NSArray *result = [["+className.toLowerCase()+"Manager findAll] filteredArrayUsingPredicate:filter];\n";
		m+="    \n";
		m+="    return result;\n";
		m+="}\n\n";
		
		
		
		
		m+="//查询待同步（"+className+"新增或修改的记录）\n";
		m+="- (NSArray *)unSync"+className+"DicArray {\n";
		m+="    \n";
		m+="    // BeanArray 转成 DicArray\n";
		m+="    NSArray *"+className.toLowerCase()+"Array = [self unSync"+className+"Array];\n";
		m+="    \n";
		m+="    //参数\n";
		m+="    // "+className+"\n";
		m+="    NSMutableArray *"+className.toLowerCase()+"DicArray = [[NSMutableArray alloc] init];\n";
		m+="    for ("+className+" *"+className.toLowerCase()+" in "+className.toLowerCase()+"Array) {\n";
		m+="        \n";
		m+="        NSMutableDictionary *dic = [NSMutableDictionary dictionary];\n";
		m+="        \n";
		
		
		
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if( property.type.contains("NSSet"))
			{
				continue;
			}
			m+="[dic setObject:"+className.toLowerCase()+"."+property.name+" forKey:@\""+property.name+"\"];\n";
		
		}
		
		

		m+="        \n";
		m+="        ["+className.toLowerCase()+"DicArray addObject:dic];\n";
		m+="    }\n";
		m+=" return "+className.toLowerCase()+"DicArray;\n";
		m+="}\n";


		m+="@end\n";


		System.out.println(m);

	}
}
