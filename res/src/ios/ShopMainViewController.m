


#import "ShopMainViewController.h"
#import "UIImageView+WebCache.h"
#import <Foundation/Foundation.h>
#import <PublicFramework/JSONKit.h>
#import <objc/runtime.h>
#import "UIButton+EnlargeTouchArea.h"

@implementation ShopMainViewController

//广州信源大厦
@synthesize shopNameTextView;
//广州大道中100号
@synthesize shopAddressTextView;
//大堂经理
@synthesize shopManagerTitleTextView;
//张三
@synthesize shopManagerValueTextView;
//电话
@synthesize shopTelTitleTextView;
//12323
@synthesize shopTelValueTextView;
//维护网点信息
@synthesize updateShopButton;
//维护个人信息
@synthesize updateShopManagerButton;

- (void)viewDidLoad
{
    [super viewDidLoad];
//广州信源大厦
[self.shopNameTextView setText:null];
//广州大道中100号
[self.shopAddressTextView setText:null];
//大堂经理
[self.shopManagerTitleTextView setText:null];
//张三
[self.shopManagerValueTextView setText:null];
//电话
[self.shopTelTitleTextView setText:null];
//12323
[self.shopTelValueTextView setText:null];

//维护网点信息
[self.updateShopButton addTarget:self action:@selector(updateShopButtonClicked:) forControlEvents:UIControlEventTouchUpInside];

//维护个人信息
[self.updateShopManagerButton addTarget:self action:@selector(updateShopManagerButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
}

-(void) viewWillAppear:(BOOL)animated{
}

-(void)updateShopButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据
}

-(void)updateShopManagerButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据
}

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

