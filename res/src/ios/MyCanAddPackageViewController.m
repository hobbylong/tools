


#import "MyCanAddPackageViewController.h"
#import "UIImageView+WebCache.h"
#import <Foundation/Foundation.h>
#import <PublicFramework/JSONKit.h>
#import <objc/runtime.h>
#import "UIButton+EnlargeTouchArea.h"

@implementation MyCanAddPackageViewController

<<<<<<< HEAD
//可加倍套餐
@synthesize titleTextView;
//我的套餐
@synthesize mypackageButton;
=======
//可加办套餐
@synthesize titleTextView;
//我的套餐
@synthesize mypackageButton;
//Y60元
@synthesize moneyTextView;
//check
@synthesize checkCheckBox;
//2013秋天
@synthesize titlevalueTextView;
//周期
@synthesize monthtitleTextView;
//6个月
@synthesize monthvalueTextView;
//总计
@synthesize totaltitleTextView;
//123
@synthesize toalvalueTextView;
//办理
@synthesize doitButton;
//首页
@synthesize shouyeTextView;
//套餐
@synthesize packageTextView;
//我的
@synthesize myTextView;
>>>>>>> 141a12c7031b19f7ad7fb0af62925724bfc2a729

- (void)viewDidLoad
{
    [super viewDidLoad];
<<<<<<< HEAD
//可加倍套餐
=======
//可加办套餐
>>>>>>> 141a12c7031b19f7ad7fb0af62925724bfc2a729
[self.titleTextView setText:null];

//我的套餐
[self.mypackageButton addTarget:self action:@selector(mypackageButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
<<<<<<< HEAD
=======
//Y60元
[self.moneyTextView setText:null];

//check
[self.checkCheckBox setSelected:];

[self.checkCheckBox addTarget:self action:@selector(checkCheckBoxCheck:) forControlEvents:UIControlEventTouchUpInside];
[self.checkCheckBox setBackgroundImage:[UIImage imageNamed:@"check.png"] forState:UIControlStateSelected];
 [self.checkCheckBox setBackgroundImage:[UIImage imageNamed:@"uncheck.png"] forState:UIControlStateNormal];
[self.checkCheckBox setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域



[self.checkCheckBoxCover setText:nil ];
//2013秋天
[self.titlevalueTextView setText:null];
//周期
[self.monthtitleTextView setText:null];
//6个月
[self.monthvalueTextView setText:null];
//总计
[self.totaltitleTextView setText:null];
//123
[self.toalvalueTextView setText:null];

//办理
[self.doitButton addTarget:self action:@selector(doitButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
//首页
[self.shouyeTextView setText:null];
//套餐
[self.packageTextView setText:null];
//我的
[self.myTextView setText:null];
>>>>>>> 141a12c7031b19f7ad7fb0af62925724bfc2a729
}

-(void) viewWillAppear:(BOOL)animated{
}

-(void)mypackageButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据
}

<<<<<<< HEAD
=======
-(void)checkCheckBoxCheck:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");//取绑定数据
int mId2 = btn.tag;//取绑定数据
  btn.selected = !btn.selected ;//用与button做checkBox
 OrderForm *orderform=datas[mId];
 if (orderform.invoiceCheck ) {//选中
  orderform.invoiceCheck=false;
}else
{
   orderform.invoiceCheck=true;
}
[self refreshUi];
//[tableView reloadData];
}

-(void)doitButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据
}

>>>>>>> 141a12c7031b19f7ad7fb0af62925724bfc2a729
-(bool)checkInput{

return true;
}

 -(void) ReturnError:(MsgReturn*)msgReturn
 {
 }//end ReturnError
  -(void) ReturnData:(MsgReturn*)msgReturn
  {
  }//end ReturnData

@end//end viewController

