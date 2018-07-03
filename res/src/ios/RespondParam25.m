


#import <Foundation/Foundation.h>
/*销量统计表25*/
@interface RespondParam25:NSObject
/* 主键 备注:X*/
@property ( nonatomic) int salesReportId;
/* 时间（以天为单位） 备注:*/
@property ( nonatomic) NSString *dateStr;
/* 商品名称 备注:*/
@property ( nonatomic) NSString *merchName;
/* 重量 备注:*/
@property ( nonatomic) float weight;
/* 结算费用 备注:*/
@property ( nonatomic) float settSumPrice;
/* 计算时间 备注:*/
@property ( nonatomic) time createDate;
@end


