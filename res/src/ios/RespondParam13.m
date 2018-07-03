


#import <Foundation/Foundation.h>
/*平台商品信息表13*/
@interface RespondParam13:NSObject
/* 商品代号 备注:X*/
@property ( nonatomic) int productID;
/* 商品名称 备注:*/
@property ( nonatomic) NSString *productName;
/* 商品所属分类代号 备注:*/
@property ( nonatomic) int productCategoryId;
/* 商品类型0：单个商品1：套餐 备注:*/
@property ( nonatomic) select productType;
/* 商品状态0:上架1：下架 备注:*/
@property ( nonatomic) select productStatus;
/* 交易单位0：按斤:1：按块2 : 按只3：按支4：按/瓶 备注:*/
@property ( nonatomic) select tradingUnit;
/* 颜色 备注:*/
@property ( nonatomic) NSString *productColor;
/* 是否鲜活0：否1：是 （此属性是否可以不要，归类于商品状态？） 备注:*/
@property ( nonatomic) select isFresh;
/* 原产地名称 备注:*/
@property ( nonatomic) NSString *sourceArea;
/* 是否支持退货0：否 1：是 备注:*/
@property ( nonatomic) select isCanRefund;
/* 是否需要当场处理0：否1：是（是否支持当场处理？） 备注:*/
@property ( nonatomic) select isNeedSpot;
/* 详情URL地址 备注:*/
@property ( nonatomic) NSString *productDetail;
/* 配菜支持天数 备注:*/
@property ( nonatomic) int supportDay;
/* 商品发布渠道 多个渠道以“,”好隔开01：微信02：安卓03：IOS（应该没有渠道） 备注:*/
@property ( nonatomic) NSString *channelNo;
/* 备注1 备注:*/
@property ( nonatomic) NSString *remakr1;
/* 备注2 备注:*/
@property ( nonatomic) NSString *remakr2;
@end


