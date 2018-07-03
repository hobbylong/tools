


#import <Foundation/Foundation.h>
/*买家退款表6*/
@interface RespondParam6:NSObject
/* 退款单号 顺序号 备注:X*/
@property ( nonatomic) int refundId;
/* 会员号 备注:*/
@property ( nonatomic) NSString *userId;
/* 订单号 备注:*/
@property ( nonatomic) NSString *orderNo;
/* 退款原因 备注:*/
@property ( nonatomic) NSString *refundReason;
/* 是否与卖家已协商0：是1：否 备注:*/
@property ( nonatomic) select isConsultSeller;
/* 退款方式0：系统退款1：人工退款 备注:*/
@property ( nonatomic) select refundStyle;
/* 订单原金额 备注:*/
@property ( nonatomic) float orderMoney;
/* 退款申请金额 备注:*/
@property ( nonatomic) float applyMoney;
/* 申请时 间 格式：yyyymmdd hh24miss 备注:*/
@property ( nonatomic) NSString *applyTime;
/* 最后退款时间 格式：yyyymmdd hh24miss 备注:*/
@property ( nonatomic) NSString *refundTime;
@end


