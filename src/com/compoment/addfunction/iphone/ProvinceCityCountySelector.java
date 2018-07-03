package com.compoment.addfunction.iphone;

import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class ProvinceCityCountySelector {
	
	public static void main(String[] args) {
		ProvinceCityCountySelector dateTimeSelect=new ProvinceCityCountySelector();
		dateTimeSelect.createH();
		dateTimeSelect.createM();
	}

	public void createH()
	{
		
		  String m2="";
		    m2+="#import <Foundation/Foundation.h>\n";
		    m2+="#import \"sqlite3.h\"\n";
		    m2+="@interface SqlApp : NSObject\n";
		    m2+="{\n";
		    m2+="    \n";
		    m2+="    sqlite3 *db;\n";
		    m2+="}\n";

		    
		    m2+="-(BOOL) openDB;\n";
		    m2+="-(NSMutableArray*) queryCityMSG:(NSString*) code withLevel:(NSString*)level;\n";
		    m2+="-(NSMutableArray*) queryCountyNameWithCountyCode:(NSString*) arearCode;\n";
		    m2+="-(NSMutableArray*) queryCityNameWithCountyCode:(NSString*) arearCode;\n";
		    m2+="-(NSMutableArray*) queryProNameWithCountyCode:(NSString*) arearCode;\n";
		    m2+="-(NSString*) queryCityNameWithRegionid:(NSString*) code;\n";
		    m2+="@end\n";
		    
		    m2+="@interface SqlRow : NSObject\n";
		    m2+="@property (nonatomic) NSString *superId;\n";
		    m2+="@property (nonatomic) NSString *rowId;\n";
		    m2+="@property (nonatomic) NSString *rowMsg;\n";
		    m2+="@property (nonatomic) Bool isSelect;\n";
		    
		    m2+="@end\n";
		    
			FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/ios",   "SqlApp", "h", m2);
	}
	
	public void createM()
	{
		
		String m="";
		m+="//SqlApp.m\n";
		m+="@implementation SqlApp\n";
		
		m+="-(BOOL) openDB{\n";
		m+="    \n";
		m+="    \n";
		m+="    NSString *path = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];   \n";
		m+="    path = [path stringByAppendingPathComponent:@\"securedDirectory/postservice_IOS.db\"];\n";
		m+="    \n";
		m+="//    NSString *path =[[NSBundle mainBundle] pathForResource:@\"POST_JY\" ofType:@\"db\"];\n";
		m+="    \n";
		m+="    //获取数据库路径\n";
		m+="    //    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);\n";
		m+="    //    NSString *documents = [paths objectAtIndex:0];\n";
		m+="    //    NSString *database_path = [documents stringByAppendingPathComponent:DBNAME];\n";
		m+="    \n";
		m+="    //如果数据库存在，则用sqlite3_open直接打开（不要担心，如果数据库不存在sqlite3_open会自动创建）\n";
		m+="    //打开数据库，这里的[path UTF8String]是将NSString转换为C字符串，因为SQLite3是采用可移植的C(而不是\n";
		m+="    //Objective-C)编写的，它不知道什么是NSString.\n";
		m+="    if (sqlite3_open([path UTF8String], &db) == SQLITE_OK) {\n";
		m+="        return YES;\n";
		m+="    }else{\n";
		m+="        NSLog(@\"数据库打开失败\");\n";
		m+="        sqlite3_close(db);\n";
		m+="        return NO;\n";
		m+="    }\n";
		m+="}\n";

		
		
		
		m+="-(NSMutableArray*) queryCityMSG:(NSString*) code withLevel:(NSString*)level{\n";
		m+="   //level 2省  3市  4县     code:上一级的编码(如查市时传入省的code)\n";
		m+="    [self openDB];\n";
		m+="    \n";
		m+="     NSString *sqlQuery=@\"\";\n";
		m+="    if ([level isEqualToString:@\"2\"]) {//省\n";
		m+="         sqlQuery = [NSString stringWithFormat:\n";
		m+="                              @\"SELECT * FROM PM_CITYCODE  where not CITYCODE = '000000000000000' and CITYCODE like '%%000000000' \"];\n";
		m+="        \n";
		m+="    }else  if ([level isEqualToString:@\"3\"]) {//市\n";
		m+="        \n";
		m+="       NSString *part1= [code substringWithRange:NSMakeRange(0, 6)];\n";
		m+="        NSString *part2=[code substringWithRange:NSMakeRange(9, 6)];\n";
		m+="        \n";
		m+="         sqlQuery = [NSString stringWithFormat:\n";
		m+="                              @\"SELECT * FROM PM_CITYCODE a where not a.CITYCODE = '%@' and a.CITYCODE like '%@'  \",code,[NSString stringWithFormat:@\"%@___%@\",part1,part2]];\n";
		m+="        \n";
		m+="    }else  if ([level isEqualToString:@\"4\"]) {//县\n";
		m+="        \n";
		m+="        NSString *part1= [code substringWithRange:NSMakeRange(0, 9)];\n";
		m+="  \n";
		m+="        \n";
		m+="         sqlQuery = [NSString stringWithFormat:\n";
		m+="                              @\"SELECT * FROM PM_CITYCODE a where not a.CITYCODE = '%@' and a.CITYCODE like '%@%%' \",code,part1];\n";
		m+="        \n";
		m+="    }\n";
		m+="    \n";
		m+=" \n";
		m+="    \n";
		m+="    sqlite3_stmt * statement;\n";
		m+="    NSMutableArray *rows=[[NSMutableArray alloc]init];\n";
		m+="    \n";
		m+="    \n";
		m+="    if (sqlite3_prepare_v2(db, [sqlQuery UTF8String], -1, &statement, nil) == SQLITE_OK) {\n";
		m+="        \n";
		m+="        //查询结果集中一条一条的遍历所有的记录，这里的数字对应的是列值,注意这里的列值\n";
		m+="        \n";
		m+="        while (sqlite3_step(statement) == SQLITE_ROW) {\n";
		m+="            \n";
		m+="           SqlRow *sqlRow=[[SqlRow alloc]init]; \n\n";
		
		m+="            char *superioridCHAR = (char*)sqlite3_column_text(statement, 0);\n";
		m+="            NSString *strsuperiorid = [[NSString alloc]initWithUTF8String:superioridCHAR];\n";
		m+="            sqlRow.superId=strsuperiorid;\n";
		m+="            \n";
		m+="            char *regionidchar = (char*)sqlite3_column_text(statement, 1);\n";
		m+="            NSString *regionidstring = [[NSString alloc]initWithUTF8String:regionidchar];\n";
		m+="            sqlRow.rowId=regionidstring;\n";

		m+="            \n";
		m+="            char *regionnamechar = (char*)sqlite3_column_text(statement, 2);\n";
		m+="            NSString *regionnamestring = [[NSString alloc]initWithUTF8String:regionnamechar];\n";
		m+="            sqlRow.rowMsg =regionnamestring;\n";
		m+="            \n";
		m+="           [rows addObject:sqlRow]; \n";
		m+="        }\n";
		m+="    }else{\n";
		m+="        NSLog(@\"select error:%@\",sqlQuery);\n";
		m+="        \n";
		m+="    }\n";
		m+="    sqlite3_close(db);\n";

		m+="    return rows;\n";
		m+="}\n";
		
		
		
		
		
		m+="-(NSMutableArray*) queryProNameWithCountyCode:(NSString*) arearCode{\n";
		m+="    \n";
		m+="    \n";
		m+="    NSString *pro=[arearCode substringWithRange:NSMakeRange(0, 6)];\n";
		m+="    \n";
		m+="    NSMutableArray *propro= [self  queryCityNameWithRegionid:[NSString stringWithFormat:@\"%@000000000\",pro]];\n";
		m+="    return propro;\n";
		m+="    \n";
		m+="}\n";

		m+="-(NSMutableArray*) queryCityNameWithCountyCode:(NSString*) arearCode{\n";
		m+="    \n";
		m+="    \n";
		m+="    NSString *city=[arearCode substringWithRange:NSMakeRange(0, 9)];\n";
		m+="    \n";
		m+="    NSMutableArray *citycity= [self queryCityNameWithRegionid:[NSString stringWithFormat:@\"%@000000\",city]];\n";
		m+="    return citycity;\n";
		m+="}\n";


		m+="-(NSMutableArray*) queryCountyNameWithCountyCode:(NSString*) arearCode{\n";
		m+="    \n";
		m+="    \n";
		m+="    \n";
		m+="    NSMutableArray *countcount= [self queryCityNameWithRegionid:arearCode];\n";
		m+="    \n";
		m+="    return countcount;\n";
		m+="    \n";
		m+="}\n";

		m+="-(NSMutableArray*) queryCityNameWithRegionid:(NSString*) code{\n";
		m+="    \n";
		m+="    [self openDB];\n";
		m+="    NSString *sqlQuery = [NSString stringWithFormat:\n";
		m+="                @\"SELECT * FROM PM_CITYCODE  where  CITYCODE = '%@' \",code];\n";
		m+="    \n";
		m+="    NSLog(@\"sqlQuery:%@\",sqlQuery);\n";
		m+="    sqlite3_stmt * statement;\n";
		m+="    \n";
		m+="    NSString * cityName=nil;\n";
		m+="    NSString * cityCode=nil;\n";
		m+="    NSMutableArray *temp=[[NSMutableArray alloc] init];\n";
		m+="    \n";
		m+="    if (sqlite3_prepare_v2(db, [sqlQuery UTF8String], -1, &statement, nil) == SQLITE_OK) {\n";
		m+="        \n";
		m+="        //查询结果集中一条一条的遍历所有的记录，这里的数字对应的是列值,注意这里的列值\n";
		m+="        \n";
		m+="        while (sqlite3_step(statement) == SQLITE_ROW) {\n";
		m+="            \n";
		m+="            char *regionnamechar = (char*)sqlite3_column_text(statement, 0);\n";
		m+="            NSString *regionnamestring = [[NSString alloc]initWithUTF8String:regionnamechar];\n";
		m+="            cityCode = regionnamestring;\n";
		m+="            \n";
		m+="            char *regionnamechar2 = (char*)sqlite3_column_text(statement, 1);\n";
		m+="            NSString *regionnamestring2 = [[NSString alloc]initWithUTF8String:regionnamechar2];\n";
		m+="            cityName = regionnamestring2;\n";
		m+="            \n";
		m+="            [temp addObject:cityCode];\n";
		m+="            [temp addObject:cityName];\n";

		m+="        }\n";
		m+="    }else{\n";
		m+="        NSLog(@\"select error:%@\",sqlQuery);\n";
		m+="        \n";
		m+="    }\n";
		m+="    sqlite3_close(db);\n";

		m+="    return temp;\n";
		m+="}\n";

        m+="@end\n\n";
        m+="@implementation SqlRow \n";
        m+="@synthesize superId;\n";
        m+="@synthesize rowId;\n";
        m+="@synthesize rowMsg;\n";
        m+="@synthesize isSelect;\n";
        
        m+="@end\n";
        
        FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/ios",   "SqlApp", "m", m);
		
		System.out.println(m);
		
	}
}
