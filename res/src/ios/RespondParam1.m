


#import <Foundation/Foundation.h>
/*问题及建议表1*/
@interface RespondParam1:NSObject
/* 上报ID 备注:key*/
@property ( nonatomic) int QuestionID;
/* 上报类型 备注: */
@property ( nonatomic) NSString *QuestionType;
/*  备注:*/
@property ( nonatomic) NSString *;
/* 涉及系统 备注: */
@property ( nonatomic) NSString *ApplicationName;
/* 功能模块名称 备注: */
@property ( nonatomic) NSString *ModelName;
/* 问题或需求描述 备注: */
@property ( nonatomic) NSString *Question;
/* 附件上传路径 备注: */
@property ( nonatomic) NSString *UploadfileUrl;
/* 反馈人姓名 备注: */
@property ( nonatomic) NSString *FeedbackName;
/* 反馈人联系方式 备注: */
@property ( nonatomic) NSString *FeedbackPhone;
/* 反馈人机构名称 备注: */
@property ( nonatomic) NSString *FeedbackOrg;
/* 提交日期 备注: */
@property ( nonatomic) NSString *FeedbackDateTime;
/* 处理人电话 备注: */
@property ( nonatomic) NSString *DealPhone;
/* 处理时间 备注: */
@property ( nonatomic) NSString *DealDateTime;
/* 处理进度 备注: */
@property ( nonatomic) NSString *DealProgress;
/* 处理意见（反馈意见） 备注: */
@property ( nonatomic) NSString *DealMsg;
/* 处理状态 备注: */
@property ( nonatomic) NSString *DealState;
/* 最后修改时间 备注: */
@property ( nonatomic) NSString *lastModifyTime;
/* 最后修改人 备注: */
@property ( nonatomic) NSString *lastModifyTimeName;
/* 最后修改渠道 备注: */
@property ( nonatomic) NSString *lastModifyTimeChannel;
@end


