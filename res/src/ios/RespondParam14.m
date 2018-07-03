


#import <Foundation/Foundation.h>
/*平台商品图片表14*/
@interface RespondParam14:NSObject
/* 商品代号 备注:X*/
@property ( nonatomic) int productId;
/* 图片名称 备注:*/
@property ( nonatomic) NSString *picName;
/* 图片显示顺序 备注:*/
@property ( nonatomic) int picShowNo;
/* 图片规格0:大1：中2：小 备注:*/
@property ( nonatomic) select picNorms;
/* 图片地址 备注:*/
@property ( nonatomic) file picUrl;
/* 备注 备注:*/
@property ( nonatomic) NSString *remark1;
@end


