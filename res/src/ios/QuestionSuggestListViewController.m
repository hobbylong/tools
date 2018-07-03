


#import "QuestionSuggestListViewController.h"
#import "UIImageView+WebCache.h"
#import <Foundation/Foundation.h>
#import <PublicFramework/JSONKit.h>
#import <objc/runtime.h>
#import "UIButton+EnlargeTouchArea.h"

@implementation QuestionSuggestListViewController

//back
@synthesize backTextView;
//需求及故障
@synthesize titleTextView;
//查询
@synthesize queryButton;
//处理人
@synthesize dealNameEditText;
//至
@synthesize zhiTextView;
//提交
@synthesize summitButton;
//模块名称
@synthesize modelNameTextView;
//系统名称
@synthesize applicationNameTextView;
//|
@synthesize lineTextView;
//需求及建议
@synthesize questionTypeTextView;
//2018
@synthesize feedBackDateTimeTextView;
//sfds
@synthesize questionTextView;

- (void)viewDidLoad
{
    [super viewDidLoad];
//back
[self.backTextView setText:null];
//需求及故障
[self.titleTextView setText:null];

//查询
[self.queryButton addTarget:self action:@selector(queryButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
[self.queryButton setBackgroundImage:[UIImage imageNamed:@"querySelect.png"] forState:UIControlStateSelected];
 [self.queryButton setBackgroundImage:[UIImage imageNamed:@"query.png"] forState:UIControlStateNormal];
[self.queryButton setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域


//处理人
 
self.dealNameEditText.returnKeyType=UIReturnKeyDone;

[self.dealNameEditText addTarget:self action:@selector(dealNameEditTextDidEndOnExit:) forControlEvents:UIControlEventEditingDidEndOnExit];

[self.dealNameEditText addTarget:self action:@selector(dealNameEditTextDidEnd:) forControlEvents:UIControlEventEditingDidEnd];

[  self.dealNameEditText  addTarget:self action:@selector(textFieldDidChange_dealNameEditText:) forControlEvents:UIControlEventEditingChanged];
    UITapGestureRecognizer *dealNameEditTextTapGuest=[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(editTextTapHandle:)];
dealNameEditTextTapGuest.delegate = self;//头文件<UIGestureRecognizerDelegate>
    [ self.dealNameEditText  addGestureRecognizer:dealNameEditTextTapGuest];

//至
[self.zhiTextView setText:null];

//提交
[self.summitButton addTarget:self action:@selector(summitButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
[self.summitButton setBackgroundImage:[UIImage imageNamed:@"Select.png"] forState:UIControlStateSelected];
 [self.summitButton setBackgroundImage:[UIImage imageNamed:@".png"] forState:UIControlStateNormal];
[self.summitButton setEnlargeEdgeWithTop:5 right:5 bottom:5 left:5];//扩大点击区域

//模块名称
[self.modelNameTextView setText:null];
//系统名称
[self.applicationNameTextView setText:null];
//|
[self.lineTextView setText:null];
//需求及建议
[self.questionTypeTextView setText:null];
//2018
[self.feedBackDateTimeTextView setText:null];
//sfds
[self.questionTextView setText:null];
 
//键盘顶起
   UITapGestureRecognizer* closeKeyboardtap =[[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(closeKeyboardBlankPlaceTapHandle:)];
    [self.view addGestureRecognizer:closeKeyboardtap];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillShow:) name:UIKeyboardDidShowNotification object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillHide:) name:UIKeyboardDidHideNotification object:nil];
}

-(void) viewWillAppear:(BOOL)animated{
}

-(void)queryButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据
}

-(void)dealNameEditTextDidEndOnExit:(UITextField *)textField{
 [self.view becomeFirstResponder];//把焦点给别人 键盘消失
 int  orderFormIndex= textField.tag;
     OrderForm *orderform=orderForms[orderFormIndex ];
     orderform.invoiceMsg=textField.text;
}

-(void)dealNameEditTextDidEnd:(UITextField *)textField{
 [self.view becomeFirstResponder];//把焦点给别人 键盘消失
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据 int  orderFormIndex= textField.tag;
     OrderForm *orderform=orderForms[orderFormIndex ];
     orderform.invoiceMsg=textField.text;
}

- (void)textFieldDidChange_dealNameEditText:(UITextField *)textField
{
  if (textField.text.length > 7) {
   textField.text = [textField.text substringToIndex:7];
 }
}
-(void)summitButtonClicked:(UIButton *)btn{
//id mId = objc_getAssociatedObject(btn, "mId");
//取绑定数据int mId2 = btn.tag;
//取绑定数据
}

-(bool)checkInput{
    beannull.null=self..dealNameEditText.text;


return true;
}
//编辑框键盘顶起start
   float touchy1;
   int   movelength1;
   int  keyboardHeight1;
   bool keyboardOpen;

//注销键盘监听
-(void)viewDidDisappear:(BOOL)animated
{
    
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardDidShowNotification object:nil];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardDidHideNotification object:nil];
    
}

//<UIGestureRecognizerDelegate>
- (BOOL)gestureRecognizer:(UIGestureRecognizer *)gestureRecognizer shouldRecognizeSimultaneouslyWithGestureRecognizer:(UIGestureRecognizer *)otherGestureRecognizer
{
    return YES;
    
    
}

//点空白区域键盘消失
-(void)closeKeyboardBlankPlaceTapHandle:(UITapGestureRecognizer *)sender
{
  
    if (keyboardOpen) {
        [[[UIApplication sharedApplication] keyWindow] endEditing:YES];
    }
    
}

//touchY 触摸位置
-(void)editTextTapHandle:(UITapGestureRecognizer *)sender
{
    
     if (keyboardOpen) {
         return;
     }
    
    CGPoint point = [sender locationInView:self.view];
    touchy1=point.y+15;
    
    if(keyboardHeight1>0)
    {
        if(touchy1>keyboardHeight1)
        {
            movelength1=touchy1-keyboardHeight1;
            [self MoveView:(-movelength1)];
        }else
        {
            movelength1=0;
            [self MoveView:(-movelength1)];
        }
    }
    
}

//键盘打开监听回调
- (void)keyboardWillShow:(NSNotification *)notification {
    
    
    keyboardOpen=true;
    /*
     Reduce the size of the text view so that it's not obscured by the keyboard.
     Animate the resize so that it's in sync with the appearance of the keyboard.
     */
    
    NSDictionary *userInfo = [notification userInfo];
    
    // Get the origin of the keyboard when it's displayed.
    NSValue* aValue = [userInfo objectForKey:UIKeyboardFrameEndUserInfoKey];
    
    // Get the top of the keyboard as the y coordinate of its origin in self's view's coordinate system. The bottom of the text view's frame should align with the top of the keyboard's final position.
    CGRect keyboardRect = [aValue CGRectValue];
    keyboardRect = [self.view convertRect:keyboardRect fromView:nil];
    
    float keyboardTop = keyboardRect.origin.y;
    
    
    
    if(keyboardHeight1==0)
    {
        // Get the duration of the animation.
        NSValue *animationDurationValue = [userInfo objectForKey:UIKeyboardAnimationDurationUserInfoKey];
        NSTimeInterval animationDuration;
        [animationDurationValue getValue:&animationDuration];
        //
        //    // Animate the resize of the text view's frame in sync with the keyboard's appearance.
        [UIView beginAnimations:nil context:NULL];
        [UIView setAnimationDuration:animationDuration];
        
        keyboardHeight1=keyboardTop;
        
        if(touchy1>keyboardHeight1)
        {
            movelength1=touchy1-keyboardHeight1;
            [self MoveView:(-movelength1)];
        }else
        {
            movelength1=0;
            [self MoveView:(-movelength1)];
        }
    
    
        
           [UIView commitAnimations];
    }
    
    
 
    
    
}

//键盘关闭监听回调
- (void)keyboardWillHide:(NSNotification *)notification {
    
      keyboardOpen=false;
    NSDictionary* userInfo = [notification userInfo];
    
    
    
    /*
     Restore the size of the text view (fill self's view).
     Animate the resize so that it's in sync with the disappearance of the keyboard.
     */
    NSValue *animationDurationValue = [userInfo objectForKey:UIKeyboardAnimationDurationUserInfoKey];
    NSTimeInterval animationDuration;
    [animationDurationValue getValue:&animationDuration];
    
    [UIView beginAnimations:nil context:NULL];
    [UIView setAnimationDuration:animationDuration];
    
    if(movelength1!=0){
    [self MoveView:(movelength1)];
    movelength1=0;
}
    
    [UIView commitAnimations];
}

-(void)MoveView:(int)h{
    
    
    if (h<0) {
        [UIView beginAnimations:nil context:nil];
        [UIView setAnimationDuration:0.3];
        [UIView setAnimationBeginsFromCurrentState: YES];
        [self.contain setFrame: CGRectMake(self.contain.frame.origin.x, self.contain.frame.origin.y + h, self.contain.frame.size.width, self.contain.frame.size.height)];
        [UIView commitAnimations];
    }else
    {
        [UIView beginAnimations:nil context:nil];
        [UIView setAnimationDuration:0.3];
        [UIView setAnimationBeginsFromCurrentState: YES];
        [self.contain setFrame: CGRectMake(self.contain.frame.origin.x, self.contain.frame.origin.y+h , self.contain.frame.size.width, self.contain.frame.size.height)];
        [UIView commitAnimations];
    }
    
    
    
}
//编辑框键盘顶起end


 -(void) ReturnError:(MsgReturn*)msgReturn
 {
 }//end ReturnError
  -(void) ReturnData:(MsgReturn*)msgReturn
  {
  }//end ReturnData

@end//end viewController

