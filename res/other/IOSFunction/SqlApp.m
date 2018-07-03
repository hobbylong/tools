//
//  Sql.m
//  Philately
//
//  Created by gdpost on 15/6/25.
//  Copyright (c) 2015年 gdpost. All rights reserved.
//

#import "SqlApp.h"
#import "ErrorObject.h"
#import "DropDownViewController.h"

@implementation SqlApp




-(BOOL) openDB{
   
    
    NSString *path = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
    
    
  path = [path stringByAppendingPathComponent:@"securedDirectory/postservice_IOS.db"];
    
      //如果数据库存在，则用sqlite3_open直接打开（不要担心，如果数据库不存在sqlite3_open会自动创建）
    //打开数据库，这里的[path UTF8String]是将NSString转换为C字符串，因为SQLite3是采用可移植的C(而不是
    //Objective-C)编写的，它不知道什么是NSString.
    if (sqlite3_open([path UTF8String], &db) == SQLITE_OK) {
        return YES;
    }else{
        return NO;
        NSLog(@"数据库打开失败");
        sqlite3_close(db);
    }
}



//查询业务类型中文
-(NSString*) selectPM_SHOPPINGCHECK:(NSString*) code{
    
    
    [self openDB];
    NSString *sqlQuery = [NSString stringWithFormat:
                          @"SELECT * FROM %@ where BUSINO='%@'",@"PM_SHOPPINGCHECK",code];
    sqlite3_stmt * statement;
    
   
    NSString *businCn=@"";
  
    if (sqlite3_prepare_v2(db, [sqlQuery UTF8String], -1, &statement, nil) == SQLITE_OK) {
        
        //查询结果集中一条一条的遍历所有的记录，这里的数字对应的是列值,注意这里的列值
        
        while (sqlite3_step(statement) == SQLITE_ROW) {
            rowApp *r=[[rowApp alloc] init ];
            
            
          
            
            char *SERVICEKEYchar = (char*)sqlite3_column_text(statement, 1);
            NSString *SERVICEKEYstring = [[NSString alloc]initWithUTF8String:SERVICEKEYchar];
            businCn=SERVICEKEYstring;
          
        }
    }else{
        NSLog(@"select error:%@",sqlQuery);
        
    }
    sqlite3_close(db);
    return businCn;
}





@end



@implementation SqlRow
@synthesize  ID;
@synthesize  KEY;
@synthesize  CODE;
@synthesize  NAME;
@synthesize  isSelect;
@end

