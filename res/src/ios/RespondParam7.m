


#import <Foundation/Foundation.h>
/*卖家商品信息表7*/
@interface RespondParam7:NSObject
/* 卖家商品代号 备注:X*/
@property ( nonatomic) int shopProductId;
/* 商品自定义名称 备注:*/
@property ( nonatomic) NSString *productName;
/* 商品自定义图片 备注:*/
@property ( nonatomic) NSString *productPic;
/* 商品简介 备注:*/
@property ( nonatomic) NSString *merchIntro;
/* 商品描述 备注:*/
@property ( nonatomic) NSString *description;
/* 商品状态 备注:*/
@property ( nonatomic) NSString *productStatus;
/* 关联的平台商品 备注:*/
@property ( nonatomic) int productId;
/* 关联店铺代号 备注:*/
@property ( nonatomic) int shopId;
/* 订单号格式：日期+10位流水号 备注:*/
@property ( nonatomic) NSString *orderNo;
/* 商品在线开始时间格式：yyyymmdd hh24miss 备注:*/
@property ( nonatomic) NSString *startTime;
/* 商品在线结束时间格式：yyyymmdd hh24miss 备注:*/
@property ( nonatomic) NSString *endTime;
/* 备注 备注:*/
@property ( nonatomic) NSString *remakr1;
/* 关联平台分类 备注:*/
@property ( nonatomic) int levelId;
@end


