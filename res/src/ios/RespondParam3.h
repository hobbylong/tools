


#import <Foundation/Foundation.h>
/*专题试题关联信息表3*/
@interface RespondParam3:NSObject
/* 专题id 备注:Key*/
@property ( nonatomic) int topic_id;
/* 题目id 备注:Key*/
@property ( nonatomic) int question_id;
/* 最后更新操作员 备注: */
@property ( nonatomic) NSString *last_modify_tlr_id;
/* 最后更新程序 备注: */
@property ( nonatomic) NSString *last_modify_prg_id;
/* 最后更新时间 备注: */
@property ( nonatomic) NSString *last_modify_tm;
@end


