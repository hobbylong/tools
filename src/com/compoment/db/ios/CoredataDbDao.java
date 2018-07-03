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
import com.compoment.gbkToUtf8.creater.GbkToUtf_FileOrDir;

public class CoredataDbDao {
	List propertys = new ArrayList();
	String className = null;

	public static void main(String[] args) throws IOException {

		CoredataDbDao mark = new CoredataDbDao();
		mark.init(mark);
		 mark.hfile();
		mark.mfile();

	}

	public void init(CoredataDbDao mark) {

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

		m += "#import <Foundation/Foundation.h>\n";
		m += "#import \"" + className + ".h\"\n";
		m += "#import \"BaseCoreDataManager.h\"\n";

		m += "@interface " + className + "Manager : NSObject\n\n";

		m += "+ (instancetype)sharedInstance;\n\n";

		m += "//网络同步时使用 插入 \n";
		m += "- (" + className + " *)createWith";
		int k=0;
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("ada") ||property.type.equals("NSSet"))
					{
				continue;
					}
			if (k == 0) {

				m += firstCharToUpper(property.name) + ":(" + property.type
						+ " *)" + property.name + "\n";
			} else {
				m += property.name + ":(" + property.type + " *)"
						+ property.name + "\n";
			}
			k++;
		}
		m += ";\n\n";
		
		
		m += "//网络同步时使用  更新 \n";
		m += "- (" + className + " *)updateWith"+ className + ":(" + className
				+ "*)" + className.toLowerCase() +"\n";
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("ada") ||property.type.equals("NSSet") )
					{
				continue;
					}
			
				m += property.name + ":(" + property.type + " *)"
						+ property.name + "\n";
		
		}
		m += ";\n\n";

		
		m += "//插入 \n";
		m += "- (" + className + " *)createWith";
		int j=0;
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("serverID") || property.name.equals("terminalID")||property.name.equals("status")||property.name.equals("ada") ||property.type.equals("NSSet") )
			{
				continue;
			}
				if (j == 0) {

				m += firstCharToUpper(property.name) + ":(" + property.type
						+ " *)" + property.name + "\n";
			} else {
				m += property.name + ":(" + property.type + " *)"
						+ property.name + "\n";
			}
				j++;
		}
		m += ";\n\n";
		
		
		m += "//更新\n";
		m += "- (" + className + " *)updateWith" + className + ":(" + className
				+ "*)" + className.toLowerCase() + ";\n\n";

		m += "//删除\n";
		m += "- (void)deleteWith" + className + ":(" + className + " *)"
				+ className.toLowerCase() + ";\n\n";

		m += "//查询\n";
		m += "- (NSArray *)findAll;\n\n";

		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if (property.name.equals("ada") || property.name.equals("serverID")
					|| property.name.equals("status")) {
			} else {
				m += "//查询By " + property.name + "\n";
				if(property.name.equals("terminalID"))
				{
					m += "- ("+className+" *)findBy" + firstCharToUpper(property.name)
							+ ":(" + property.type + " *)" + property.name
							+ ";\n\n";
				}else
				{
				m += "- (NSArray *)findBy" + firstCharToUpper(property.name)
						+ ":(" + property.type + " *)" + property.name
						+ ";\n\n";
				}

			}
		}

		m += "@end\n";

		System.out.println(m);
	}

	public void mfile() {
		String m = "＃＃＃＃＃＃＃＃＃＃＃＃＃\n\n";
		m += "#import \"" + className + "Manager.h\"\n";
		m += "#import \"CRM_CoreDataManager.h\"\n";
		m += "#import \"ShellSDK.h\"\n";
		m += "#import \"CRM_UUID.h\"\n";
		m += "#import \"CRM.h\"\n\n";
		m += "@implementation " + className + "Manager\n\n";

		m += "static " + className + "Manager *sharedInstance;\n\n";

		m += "+ (instancetype)sharedInstance {\n";
		m += "    if (sharedInstance == nil) {\n";
		m += "        sharedInstance = [[" + className
				+ "Manager alloc] init];\n";
		m += "    }\n";
		m += "    return sharedInstance;\n";
		m += "}\n\n";
		
		
		
        m+="//同步时使用 插入\n";
		m += "- (" + className + " *)createWith";
		int k=0;
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("ada"))
			{
				continue;
			}
			if (k == 0) {
				m += firstCharToUpper(property.name) + ":(" + property.type
						+ " *)" + property.name + "\n";
			} else {
				m += property.name + ":(" + property.type + " *)"
						+ property.name + "\n";
			}
			k++;
		}
		m += "{\n";
		m += "    \n";
		m += "    NSString *currentAda = [[ShellSDK sharedInstance] currentAda];\n";
		m += "    \n";
		m += "    " + className + " *" + className.toLowerCase()
				+ " = [[CRM_CoreDataManager sharedInstance]\n";
		m += "                        createWithEntityClass:[" + className
				+ " class]];\n";
		m += "    " + className.toLowerCase() + ".ada = currentAda;\n";
	    
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("ada"))
			{
				continue;
			}
			if (property.type.equals("NSDate")) {
				m += "" + className.toLowerCase() + "." + property.name
						+ " = [NSDate date];\n";
			}
			else {
				m += "" + className.toLowerCase() + "." + property.name + " = "
						+ property.name + ";\n";
			}
		}

		m += "    [[CRM_CoreDataManager sharedInstance] save:"
				+ className.toLowerCase() + "];\n";
		m += "    return " + className.toLowerCase() + ";\n";
		m += "}\n\n";
		
		
		 m+="//同步时使用 更新\n";
			m += "- (" + className + " *)updateWith"+ className + ":(" + className
					+ " *)" + className.toLowerCase() +"\n";
		
			for (int i = 0; i < propertys.size(); i++) {
				PropertyBean property = (PropertyBean) propertys.get(i);
				if(property.name.equals("ada"))
				{
					continue;
				}
				
			
					m += property.name + ":(" + property.type + " *)"
							+ property.name + "\n";
				
			}
			m += "{\n";
			m+="  if (["+className.toLowerCase()+".status isEqualToNumber:@(CRM_ENTITY_DELETED)]) {\n";
			m+="        return "+className.toLowerCase()+";\n";
			m+="    }\n";

			m += "    \n";
			for (int i = 0; i < propertys.size(); i++) {
				PropertyBean property = (PropertyBean) propertys.get(i);
				if(property.name.equals("ada"))
				{
					continue;
				}
			
				
					m += "" + className.toLowerCase() + "." + property.name + " = "
							+ property.name + ";\n";
				
			}

			m += "    [[CRM_CoreDataManager sharedInstance] save:"
					+ className.toLowerCase() + "];\n";
			m += "    return " + className.toLowerCase() + ";\n";
			m += "}\n\n";
		
		
		m+="//插入\n";
		m += "- (" + className + " *)createWith";
		int j=0;
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("serverID") || property.name.equals("terminalID")||property.name.equals("status") ||	property.name.equals("ada") )
			{
				continue;
			}
			
			if (j == 0) {
				m += firstCharToUpper(property.name) + ":(" + property.type
						+ " *)" + property.name + "\n";
			} else {
				m += property.name + ":(" + property.type + " *)"
						+ property.name + "\n";
			}
			
			j++;
		}
		m += "{\n";
		m += "    \n";
		m += "    NSString *currentAda = [[ShellSDK sharedInstance] currentAda];\n";
		m += "    \n";
		m += "    " + className + " *" + className.toLowerCase()
				+ " = [[CRM_CoreDataManager sharedInstance]\n";
		m += "                        createWithEntityClass:[" + className
				+ " class]];\n";
		m += "    " + className.toLowerCase() + ".ada = currentAda;\n";
	
		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if(property.name.equals("serverID")  ||property.name.equals("ada"))
			{
				continue;
			}
			if (property.type.equals("NSDate")) {
				m += "" + className.toLowerCase() + "." + property.name
						+ " = [NSDate date];\n";
			}else if(property.name.equals("status")) {
				m += "" + className.toLowerCase() + "." + property.name
						+ " = [NSNumber numberWithInt:CRM_ENTITY_NEW];\n";
				
			}
			
			else if(property.name.equals("terminalID"))
			{
				m += className.toLowerCase()+".terminalID = [CRM_UUID UUID];\n";
			}
			else {
				m += "" + className.toLowerCase() + "." + property.name + " = "
						+ property.name + ";\n";
			}
		}

		m += "    [[CRM_CoreDataManager sharedInstance] save:"
				+ className.toLowerCase() + "];\n";
		m += "    return " + className.toLowerCase() + ";\n";
		m += "}\n\n";

		m += "//更新\n";
		m += "- (" + className + " *)updateWith" + className + ":(" + className
				+ " *)" + className.toLowerCase() + "\n";
		m += "  {\n";
		m += "    \n";
		m += "    if ([" + className.toLowerCase()
				+ ".status isEqualToNumber:@(CRM_ENTITY_DELETED)]) {\n";
		m += "        return " + className.toLowerCase() + ";\n";
		m += "    }\n";
		m += "    \n";

		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);
			if (property.type.equals("NSDate")) {
				m += "" + className.toLowerCase() + "." + property.name
						+ " = [NSDate date];\n";
			} else {
				m += "" + className.toLowerCase() + "." + property.name + " = "
						+ className.toLowerCase() + "." + property.name + ";\n";
			}
		}

		m += "    \n";
		m += "    if ([" + className.toLowerCase()
				+ ".status isEqualToNumber:@(CRM_ENTITY_NORMAL)]) {\n";
		m += "        \n";
		m += "        " + className.toLowerCase()
				+ ".status = @(CRM_ENTITY_CHANGED);\n";
		m += "    }\n";
		m += "    \n";
		m += "    [[CRM_CoreDataManager sharedInstance] save:"
				+ className.toLowerCase() + "];\n";
		m += "    \n";
		m += "    return " + className.toLowerCase() + ";\n";
		m += "}\n\n";

		m += "//删除\n";
		m += "- (void)deleteWith" + className + ":(" + className + " *)"
				+ className.toLowerCase() + " {\n";
		m += "    \n";
		m += "    if ([" + className.toLowerCase()
				+ ".status isEqualToNumber:@(CRM_ENTITY_DELETED)]) {\n";
		m += "        return;\n";
		m += "    }\n";
		m += "    \n";
		m += "    if ([" + className.toLowerCase()
				+ ".status isEqualToNumber:@(CRM_ENTITY_NEW)]) {\n";
		m += "        [[CRM_CoreDataManager sharedInstance] delete:"
				+ className.toLowerCase() + "];\n";
		m += "    } else {\n";
		m += "        " + className.toLowerCase()
				+ ".status = @(CRM_ENTITY_DELETED);\n";
		m += "        [[CRM_CoreDataManager sharedInstance] save:"
				+ className.toLowerCase() + "];\n";
		m += "    }\n";
		m += "}\n\n";

		m += "//查询全部\n";
		m += "- (NSArray *)findAll {\n";
		m += "    NSManagedObjectContext *context =\n";
		m += "    [[CRM_CoreDataManager sharedInstance] managedObjectContext];\n";
		m += "    NSFetchRequest *fetchRequest = [[CRM_CoreDataManager sharedInstance]\n";
		m += "                                    generateFetchRequestWithEntityClass:["
				+ className + " class]];\n";
		m += "    NSString *currentAda = [[ShellSDK sharedInstance] currentAda];\n";
		m += "    // where\n";
		m += "    NSPredicate *predicate =\n";
		m += "    [NSPredicate predicateWithFormat:@\"ada = %@ AND status!=%@\", currentAda,\n";
		m += "     @(CRM_ENTITY_DELETED)];\n";
		m += "    [fetchRequest setPredicate:predicate];\n";
		m += "    \n";
		m += "    return [context executeFetchRequest:fetchRequest error:nil];\n";
		m += "}\n\n";

		for (int i = 0; i < propertys.size(); i++) {
			PropertyBean property = (PropertyBean) propertys.get(i);

			if (property.type.equals("NSDate")) {
				m += "//查询By " + property.name + "\n";
				m += "- (NSArray *)findBy"
						+ this.firstCharToUpper(property.name) + ":("
						+ property.type + " *)" + property.name + " {\n";
				m += "    \n";
				m += "    NSManagedObjectContext *context =\n";
				m += "    [[CRM_CoreDataManager sharedInstance] managedObjectContext];\n";
				m += "    NSFetchRequest *fetchRequest = [[CRM_CoreDataManager sharedInstance]\n";
				m += "                                    generateFetchRequestWithEntityClass:["
						+ className + " class]];\n";
				m += "    NSString *currentAda = [[ShellSDK sharedInstance] currentAda];\n";
				m += "    \n";
				m += "    //本月有多少天\n";
				m += "    NSCalendar *calendar =\n";
				m += "    [[NSCalendar alloc] initWithCalendarIdentifier:NSGregorianCalendar];\n";
				m += "    NSRange range = [calendar rangeOfUnit:NSDayCalendarUnit\n";
				m += "                                   inUnit:NSMonthCalendarUnit\n";
				m += "                                  forDate:date];\n";
				m += "    NSUInteger numberOfDaysInMonth = range.length;\n";
				m += "    \n";
				m += "    //取得年月日\n";
				m += "    NSDateComponents *components = [[NSCalendar currentCalendar]\n";
				m += "                                    components:NSDayCalendarUnit | NSMonthCalendarUnit | NSYearCalendarUnit\n";
				m += "                                    fromDate:date];\n";
				m += "    \n";
				m += "    NSInteger month = [components month];\n";
				m += "    NSInteger year = [components year];\n";
				m += "    \n";
				m += "    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];\n";
				m += "    \n";
				m += "    [dateFormatter setDateFormat:@\"yyyy-MM-dd HH:mm:ss\"];\n";
				m += "    \n";
				m += "    NSString *startS = [NSString\n";
				m += "                        stringWithFormat:@\"%@-%@-%@ 00:00:00\", [NSNumber numberWithInteger:year],\n";
				m += "                        [NSNumber numberWithInteger:month],\n";
				m += "                        [NSNumber numberWithInt:1]];\n";
				m += "    NSDate *startDate = [dateFormatter dateFromString:startS];\n";
				m += "    \n";
				m += "    NSString *endS = [NSString\n";
				m += "                      stringWithFormat:@\"%@-%@-%@ 00:00:00\", [NSNumber numberWithInteger:year],\n";
				m += "                      [NSNumber numberWithInteger:month],\n";
				m += "                      [NSNumber numberWithInteger:numberOfDaysInMonth]];\n";
				m += "    NSDate *endDate = [dateFormatter dateFromString:endS];\n";
				m += "    \n";
				m += "    // where\n";
				m += "    NSPredicate *predicate =\n";
				m += "    [NSPredicate predicateWithFormat:\n";
				m += "     @\"date >= %@ AND date <= %@ AND ada = %@ AND status!=%@\",\n";
				m += "     startDate, endDate, currentAda, @(CRM_ENTITY_DELETED)];\n";
				m += "    [fetchRequest setPredicate:predicate];\n";
				m += "    \n";
				m += "    NSArray *objs = [context executeFetchRequest:fetchRequest error:nil];\n";
				m += "    return objs;\n";
				m += "}\n\n";
			} else {
				if (property.name.equals("ada")
						|| property.name.equals("serverID")
						|| property.name.equals("status")) {

				} else {
					m += "//查询By " + property.name + "\n";
					if(property.name.equals("terminalID"))
					{
					m += "- ("+className+" *)findBy"
							+ this.firstCharToUpper(property.name) + ":("
							+ property.type + " *)" + property.name + " {\n";
					}else
					{
						m += "- (NSArray *)findBy"
								+ this.firstCharToUpper(property.name) + ":("
								+ property.type + " *)" + property.name + " {\n";
					}
					m += "    NSManagedObjectContext *context =\n";
					m += "    [[CRM_CoreDataManager sharedInstance] managedObjectContext];\n";
					m += "    NSFetchRequest *fetchRequest = [[CRM_CoreDataManager sharedInstance]\n";
					m += "                                    generateFetchRequestWithEntityClass:["
							+ className + " class]];\n";
					m += "    NSString *currentAda = [[ShellSDK sharedInstance] currentAda];\n";
					m += "    // where\n";
					m += "    NSPredicate *predicate = [NSPredicate\n";
					m += "                              predicateWithFormat:@\""
							+ property.name
							+ " = %@ AND ada = %@ AND status!=%@\",\n";
					m += "                              " + property.name
							+ ", currentAda, @(CRM_ENTITY_DELETED)];\n";
					m += "    [fetchRequest setPredicate:predicate];\n";
					m += "    \n";
					m += "    NSArray *objs = [context executeFetchRequest:fetchRequest error:nil];\n";
					if(property.name.equals("terminalID"))
					{
					  m+="  if(objs!=nil && [objs count]>0)\n";
					  m+= "{\n";
					  m+="      return  [objs objectAtIndex:0];\n";
					  m+="  }\n";
						m += "    return nil;\n";
					}else
					{
						m += "    return objs;\n";
					}
					
					
					m += "}\n\n";

				}
			}

		}
		m += "@end\n";

		System.out.println(m);

	}
}
