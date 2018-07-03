


#import <Foundation/Foundation.h>
/*订单状态操作表24*/
@interface RespondParam24:NSObject
/* 订单号 备注:X*/
@property ( nonatomic) int orderNo;
/* 当前订单状态 备注:*/
@property ( nonatomic) NSString *orderStatus;
/* 可操作标志01：支付02：订单取消 03：配货取消 04：退款 备注:*/
@property ( nonatomic) select opFalg;
@end


