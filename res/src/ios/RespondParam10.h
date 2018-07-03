


#import <Foundation/Foundation.h>
/*商品分类表10*/
@interface RespondParam10:NSObject
/* 级别代号顺序号，从1开始 备注:X*/
@property ( nonatomic) int productCategoryId;
/* 级别名称 备注:*/
@property ( nonatomic) NSString *productCategoryName;
/* 父级别代号 备注:*/
@property ( nonatomic) int parentId;
/* 父级别名称 备注:*/
@property ( nonatomic) NSString *parentName;
/* 级别等级0：根级别1：第1级2：第2级 备注:*/
@property ( nonatomic) select productCategoryGrade;
/* 树路径 备注:*/
@property ( nonatomic) NSString *path;
@end


