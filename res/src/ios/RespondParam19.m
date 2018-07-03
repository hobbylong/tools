


#import <Foundation/Foundation.h>
/*消息表19*/
@interface RespondParam19:NSObject
/* 主键 备注:X*/
@property ( nonatomic) int messageId;
/* 内容 备注:*/
@property ( nonatomic) NSString *messageDetail;
/* 01：业务通知02：系统变更通知03：业务进展通知04：其它通知 备注:*/
@property ( nonatomic) select messageType;
/* 0：未读 1：已读 备注:*/
@property ( nonatomic) int readMessageFlag;
/* 发送时间 备注:*/
@property ( nonatomic) time messageTime;
/* 系统用户 备注:*/
@property ( nonatomic) int systemNo;
/* 消息渠道01：微信02：安卓03：IOS 备注:*/
@property ( nonatomic) NSString *messageChannel;
/* 消息链接类型 备注:*/
@property ( nonatomic) NSString *messageLinkType;
/* 消息链接业务参数 备注:*/
@property ( nonatomic) NSString *messageLinkPara;
@end


