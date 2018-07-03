


#import <Foundation/Foundation.h>
/*专题信息表2*/
@interface RespondParam2:NSObject
/* 专题id 备注:Key*/
@property ( nonatomic) int topic_id;
/* 专题名称 备注:*/
@property ( nonatomic) NSString *topic_name;
/* 考题数量 备注: */
@property ( nonatomic) int question_num;
/* 专题图片 备注: */
@property ( nonatomic) NSString *topic_pic;
/* 考试时间 备注: */
@property ( nonatomic) NSString *exam_time;
/* 考试状态 备注: */
@property ( nonatomic) NSString *exam_state;
/* 最后更新操作员 备注: */
@property ( nonatomic) NSString *last_modify_tlr_id;
/* 最后更新程序 备注: */
@property ( nonatomic) NSString *last_modify_prg_id;
/* 最后更新时间 备注: */
@property ( nonatomic) NSString *last_modify_tm;
@end


