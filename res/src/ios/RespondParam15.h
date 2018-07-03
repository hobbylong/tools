


#import <Foundation/Foundation.h>
/*平台商品推荐表15*/
@interface RespondParam15:NSObject
/* Id 备注:*/
@property ( nonatomic) int Id;
/* 商品代号 备注:*/
@property ( nonatomic) int productId;
/* 推荐渠道 备注:*/
@property ( nonatomic) NSString *channelNo;
/* 推荐标识0：推荐1：热门 备注:*/
@property ( nonatomic) select saleType;
/* 有效开始时间格式：yyyymmdd hh24miss 备注:*/
@property ( nonatomic) NSString *startTime;
/* 有效结束时间格式：yyyymmdd hh24miss 备注:*/
@property ( nonatomic) NSString *endTime;
/* 记录创建时间格式：yyyymmdd hh24miss 备注:*/
@property ( nonatomic) NSString *createTime;
/* 备注 备注:*/
@property ( nonatomic) NSString *remark1;
@end


