


//ios界面 object-c 
#import <UIKit/UIKit.h>
#import "ServiceInvoker.h"
@interface QuestionSuggestListViewController : UIViewController<UITableViewDataSource,UITableViewDelegate,ServiceInvokerDelegate>

{

}
//back
@property (weak, nonatomic) IBOutlet UILabel *backTextView;
//需求及故障
@property (weak, nonatomic) IBOutlet UILabel *titleTextView;
//查询
@property (weak, nonatomic) IBOutlet UIButton *queryButton;
//处理人
@property (weak, nonatomic) IBOutlet UITextField *dealNameEditText;
//至
@property (weak, nonatomic) IBOutlet UILabel *zhiTextView;
//提交
@property (weak, nonatomic) IBOutlet UIButton *summitButton;
//模块名称
@property (weak, nonatomic) IBOutlet UILabel *modelNameTextView;
//系统名称
@property (weak, nonatomic) IBOutlet UILabel *applicationNameTextView;
//|
@property (weak, nonatomic) IBOutlet UILabel *lineTextView;
//需求及建议
@property (weak, nonatomic) IBOutlet UILabel *questionTypeTextView;
//2018
@property (weak, nonatomic) IBOutlet UILabel *feedBackDateTimeTextView;
//sfds
@property (weak, nonatomic) IBOutlet UILabel *questionTextView;
@end

