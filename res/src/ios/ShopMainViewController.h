


//ios界面 object-c 
#import <UIKit/UIKit.h>
#import "ServiceInvoker.h"
@interface ShopMainViewController : UIViewController<UITableViewDataSource,UITableViewDelegate,ServiceInvokerDelegate>

{

}
//广州信源大厦
@property (weak, nonatomic) IBOutlet UILabel *shopNameTextView;
//广州大道中100号
@property (weak, nonatomic) IBOutlet UILabel *shopAddressTextView;
//大堂经理
@property (weak, nonatomic) IBOutlet UILabel *shopManagerTitleTextView;
//张三
@property (weak, nonatomic) IBOutlet UILabel *shopManagerValueTextView;
//电话
@property (weak, nonatomic) IBOutlet UILabel *shopTelTitleTextView;
//12323
@property (weak, nonatomic) IBOutlet UILabel *shopTelValueTextView;
//维护网点信息
@property (weak, nonatomic) IBOutlet UIButton *updateShopButton;
//维护个人信息
@property (weak, nonatomic) IBOutlet UIButton *updateShopManagerButton;
@end

