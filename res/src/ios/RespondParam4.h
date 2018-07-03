


#import <Foundation/Foundation.h>
/*用户答题情况表4*/
@interface RespondParam4:NSObject
/* 专题id 备注:Key*/
@property ( nonatomic) int topic_id;
/* 用户id 备注:Key*/
@property ( nonatomic) NSString *user_id;
/* 题目id 备注:Key*/
@property ( nonatomic) int question_id;
/* 选择的答案 备注: */
@property ( nonatomic) NSString *question_answer;
/* 考试时间 备注: */
@property ( nonatomic) NSString *exam_time;
/* 最后更新操作员 备注: */
@property ( nonatomic) NSString *last_modify_tlr_id;
/* 最后更新程序 备注: */
@property ( nonatomic) NSString *last_modify_prg_id;
/* 最后更新时间 备注: */
@property ( nonatomic) NSString *last_modify_tm;
@end


