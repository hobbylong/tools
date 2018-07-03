


#import <Foundation/Foundation.h>
/*买家购物车表5*/
@interface RespondParam5:NSObject
/* 购物车id 备注:X*/
@property ( nonatomic) int shopCarId;
/* 会员号 备注:*/
@property ( nonatomic) int userId;
/* 卖家商品代号 备注:*/
@property ( nonatomic) int shopProductId;
/* 卖家店铺代号 备注:*/
@property ( nonatomic) int shopId;
/* 卖家市场代号 备注:*/
@property ( nonatomic) int marketId;
/* 购买规格 备注:*/
@property ( nonatomic) int normsId;
/* 购买数量 备注:*/
@property ( nonatomic) int buyNum;
/* 购买价格 备注:*/
@property ( nonatomic) float normsPrice;
/* 加入购物车时间格式：yyyymmdd hh24miss 备注:*/
@property ( nonatomic) NSString *createTime;
/* 备注 备注:*/
@property ( nonatomic) NSString *remark;
@end


