


#import <Foundation/Foundation.h>
/*黑名单表112*/
@interface RespondParam112:NSObject
/* ID 备注:X*/
@property ( nonatomic) int id;
/* 渠道代号 备注:*/
@property ( nonatomic) NSString *thd_sys_id;
/* 用户OpenId 备注:*/
@property ( nonatomic) NSString *open_id;
/* 黑名单类别: 01 -- 活动类 备注:*/
@property ( nonatomic) select black_type;
/* 开始日期 备注:*/
@property ( nonatomic) time begin_time;
/* 结束日期 备注:*/
@property ( nonatomic) time end_time;
/* 活动禁止列表 备注:*/
@property ( nonatomic) NSString *activity_list;
/* 更新时间 备注:*/
@property ( nonatomic) time update_time;
/* 创建时间 备注:*/
@property ( nonatomic) time create_time;
/* 操作员 备注:*/
@property ( nonatomic) NSString *oper_no;
/* 机构号 备注:*/
@property ( nonatomic) NSString *brch_no;
/*  备注:*/
@property ( nonatomic) NSString *oth_msg1;
/*  备注:*/
@property ( nonatomic) NSString *oth_msg2;
@end


