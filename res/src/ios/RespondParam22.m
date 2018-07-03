


#import <Foundation/Foundation.h>
/*订单关联商品表22*/
@interface RespondParam22:NSObject
/* 订单号 备注:X*/
@property ( nonatomic) NSString *orderNo;
/* 卖家商品代号 备注:X*/
@property ( nonatomic) int shopProductId;
/* 商品名称 备注:*/
@property ( nonatomic) NSString *shopProductName;
/* 商品所属分类代号 备注:*/
@property ( nonatomic) int levelId;
/* 商品类型0：单个商品:1：套餐 备注:*/
@property ( nonatomic) select productType;
/* 购买规格 备注:*/
@property ( nonatomic) NSString *shopNormsId;
/* 购买数量 备注:*/
@property ( nonatomic) int shopNormsNum;
/* 购买价格 备注:*/
@property ( nonatomic) float shopNormsPrice;
@end


