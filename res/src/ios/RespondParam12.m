


#import <Foundation/Foundation.h>
/*市场表12*/
@interface RespondParam12:NSObject
/* 市场ID 备注:X*/
@property ( nonatomic) int marketId;
/* 市场名字 备注:*/
@property ( nonatomic) NSString *marketName;
/* 市场经度 备注:*/
@property ( nonatomic) float lonValue;
/* 市场纬度 备注:*/
@property ( nonatomic) float latValue;
/* 省份代号 备注:*/
@property ( nonatomic) NSString *provCode;
/* 市局代号 备注:*/
@property ( nonatomic) NSString *cityCode;
/* 区县代号 备注:*/
@property ( nonatomic) NSString *countyCode;
/* 市场所属片区 备注:*/
@property ( nonatomic) NSString *marketArea;
/* 市场详细地址 备注:*/
@property ( nonatomic) NSString *marketAddr;
@end


