//
//  Sql.h
//  Philately
//
//  Created by gdpost on 15/6/25.
//  Copyright (c) 2015年 gdpost. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "sqlite3.h"

@interface SqlApp : NSObject
{

 sqlite3 *db;
}

-(BOOL) openDB;


@end

@interface SqlRow : NSObject

@property (strong, nonatomic) NSString  *ID;
@property (strong, nonatomic) NSString  *KEY;
@property (strong, nonatomic) NSString  *CODE;
@property (strong, nonatomic) NSString  *NAME;
@property (strong, nonatomic) BOOL  isSelect;

@end

