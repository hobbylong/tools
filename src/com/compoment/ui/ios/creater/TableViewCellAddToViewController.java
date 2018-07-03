package com.compoment.ui.ios.creater;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compoment.addfunction.android.FileBean;
import com.compoment.cut.CompomentBean;
import com.compoment.util.FileUtil;
import com.compoment.util.ImportString;
import com.compoment.util.KeyValue;
import com.compoment.util.RegexUtil;
import com.compoment.util.RegexUtil.ControllerBean;


/***
 * TableViewCell
 * */
public class TableViewCellAddToViewController {


	


	String waitByModifyFileName;
    String className="";
    String viewDidLoad_Implement="";
    String editTextCheck="";
	String closeKeyboardDeclare="";
	String closeKeyboardImplement="";
	CompomentDeclareImplement compomentDeclareImplement=new CompomentDeclareImplement();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

    String pageName="";
    boolean isHeadCell=false;

        
	public TableViewCellAddToViewController(String pageName,List<CompomentBean> oldBeans,String waitByModifyFileName,boolean isHeadCell) {
		this.isHeadCell=isHeadCell;
		this.waitByModifyFileName = waitByModifyFileName;
		this.pageName=pageName;
        className=firstCharToUpperAndJavaName(pageName);
		//copyFile();
        analyse(oldBeans);
		add();
	}

	public static String firstCharToUpperAndJavaName(String string) {
		// buy_typelist
		String[] ss = string.split("_");
		String temp = "";
		for (String s : ss) {
			if (!s.equals("item"))
				temp += s.substring(0, 1).toUpperCase() + s.substring(1);
		}
		return temp;
	}
	
	
	public void copyFile() {
		
	}

	

	public void add() {
		
		List addedLines =new ArrayList();
	
		RegexUtil regex = new RegexUtil();

		List lines = FileUtil.fileContentToArrayList(waitByModifyFileName);

		

		
		//是否已注入过此功能
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}

			if(line.contains("//注入table功能"))
			{
				return;
			}
		}

		
		boolean findViewByIdFirst=true;
		boolean isNineList=false;//九宫图列表
		//
		String content = "";
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}
			
			 if(line.contains("numberOfSectionsInTableView") &&isHeadCell==true)	
			 {
				 
				 String m="\n";
				
					m += "- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{\n";
					m+=" "+className+"TableViewCellHead *cell = ("+className+"TableViewCellHead*)[self.tableView dequeueReusableHeaderFooterViewWithIdentifier:"+className+"CellHeadIdentifier];\n";
					m+="    if (!cell)\n";
					m+="    {\n";
					m+="       cell = [[[NSBundle mainBundle] loadNibNamed:@\""+className+"TableViewCellHead\" owner:self options:nil] lastObject];\n";
					m+="    }\n";
					m+=controllers;
					m+="return cell;\n";
					m += "}\n\n";
					
					
					
		
					m += "-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{\n";
					
					m+=" "+className+"TableViewCellHead *cell = ("+className+"TableViewCellHead*)[self.tableView dequeueReusableHeaderFooterViewWithIdentifier:"+className+"CellHeadIdentifier];\n";
					m+="    if (!cell)\n";
					m+="    {\n";
					m+="       cell = [[[NSBundle mainBundle] loadNibNamed:@\""+className+"TableViewCellHead\" owner:self options:nil] lastObject];\n";
					m+="    }\n";
				    m+="   int height=cell.contentView.frame.size.height;//非动态高度(row1跟row2同样高)变化适用 不需配合上边使用   \n";
				    m+="return height+1;\n";;
				    
					m += "}\n\n";
					
			    	content += m;
			    	
			  
			    	
			 }
			
			 
			 if(line.contains("@implementation")&&isHeadCell==false)
			 {
				 String m="";
				     m+="#import \""+className+"TableViewCell.h\"\n";
				     m+="//注入table功能\n";
					 m+=" NSString *"+className+"CellIdentifier = @\""+className+"TableViewCell\";\n";	
					 m+=" NSString *"+className+"CellHeadIdentifier = @\""+className+"TableViewCellHead\";\n\n";	
					 
				
			    	content += m;
			 }
			 if(line.contains("@end//end viewController")&&isHeadCell==false)	
			 {
				 content+=closeKeyboardImplement;
				 content +=viewDidLoad_Implement;
				 
			 }
		
			 
			 //前
			content += line + "\n";
			 //后
			
			 if(line.contains("@implementation")&&isHeadCell==false)
			 {
					String m="@synthesize cacheCells;\n";	
			    	content += m;
			 }
			
			
		 if(line.contains("[super viewDidLoad]")&&isHeadCell==false)
					
				{
					String m="\n//start  TableView \n";
					
					m+="totalRowCount=0;\n";
					m+="currentRowCount=0;\n";
					m+="page=1;\n\n";
					m+="allIndexpaths=[[NSMutableArray alloc] init];\n";
					m+=" rows=[[NSMutableArray alloc] init];\n";
					
					m+="    [self.tableView setDelegate:self];//tableview委托\n";
					m+="    [self.tableView setDataSource:self];//tableview数据委托\n";
					m+="    self.tableView.tableFooterView=[[UIView alloc]init];//tableview去除多余的分隔线\n";
				
					m+="    //使用自定义的Cell,需要向UITableView进行注册\n";
					m+="    UINib *cellNib = [UINib nibWithNibName:@\""+className+"TableViewCell\" bundle:nil];\n";
					m+="    [tableView registerNib:cellNib forCellReuseIdentifier:"+className+"CellIdentifier];\n";
			    	
					m+="     cacheCells = [NSMutableDictionary dictionary];\n";
					m+="//end TableView \n\n";
					
					m+=closeKeyboardDeclare;
					content += m;
				}
	 if(line.contains("viewWillAppear:(BOOL)animated")&&isHeadCell==false)		
				{
					String m="//table\n";
					m+="[self.tableView deselectRowAtIndexPath:[self.tableView indexPathForSelectedRow] animated:YES];\n";
			    	content += m;
				}
	 
	 

	 
	 
	  if(line.contains("cellForRowAtIndexPath")&&isHeadCell==false)		
				{
		            
					String m="\n";
					if(line.contains("九宫图"))
					{
						isNineList=true;
						m+="// 九宫图数据处理\n";
						m+="Row *row=thisPageRows[indexPath.row];\n";
						m+="if ([row.rowChirlds count]>0) {//第一列 \n}\nelse{\n [cell.picButton setTitle:@\"\" forState:UIControlStateNormal ];\n[cell.picButton setTitle:@\"\" forState:UIControlStateSelected ];\n }\n"; 
						m+="if ([row.rowChirlds count]>1) {//第二列 } \n\n";
						m+="//end 九宫图\n";
					}
					 if(line.contains("分页"))
					 {
					m+="\n//分页Start(可注调)\n";
					m+=" if([indexPath row] == ([rows count])  && [rows count]>0) {\n";
				       m+=" if( currentRowCount<totalRowCount)\n";
					   m+=" {\n";
					      m+="    [self request9999:YES];\n";    
					      m+="  MoreTableViewCell *cell = [[[NSBundle mainBundle] loadNibNamed:@\"MoreTableViewCell\" owner:self options:nil] lastObject];\n";
					      m+="  return cell;\n";
				       m+=" }else\n";
					      m+=" {\n";
				          m+="    return [[UITableViewCell alloc] init ];\n";
					   m+=" }\n";
					   
				  m+="  }\n";
					    
				  m+=" else  if([indexPath row] == ([rows count]) && [rows count]==0)\n";
				  m+="{\n";
							 m+="return [[UITableViewCell alloc] init ];\n";
				  m+=" }\n";
				  m+=" else //分页End\n\n";
					 
				  
				  m+=" {\n";
					
					m+=" "+className+"TableViewCell *cell = ("+className+"TableViewCell*)[self.tableView dequeueReusableCellWithIdentifier:"+className+"CellIdentifier];\n";
					m+="    if (!cell)\n";
					m+="    {\n";
					m+="       cell = [[[NSBundle mainBundle] loadNibNamed:@\""+className+"TableViewCell\" owner:self options:nil] lastObject];\n";
					m+="    }\n";
					m+=controllers;
					m+="return cell;\n";
				  m+=" }\n";
					 }else
					 {
							m+=" "+className+"TableViewCell *cell = ("+className+"TableViewCell*)[self.tableView dequeueReusableCellWithIdentifier:"+className+"CellIdentifier];\n";
							m+="    if (!cell)\n";
							m+="    {\n";
							m+="       cell = [[[NSBundle mainBundle] loadNibNamed:@\""+className+"TableViewCell\" owner:self options:nil] lastObject];\n";
							m+="    }\n";
							m+=controllers;
							m+="return cell;\n"; 
					 }
			    	content += m;
				}
	  
			 if(line.contains("heightForRowAtIndexPath")&&isHeadCell==false)	
			 {
					String m="\n";
					m+="NSString *reuseIdentifier = "+className+"CellIdentifier;\n";
				    m+=""+className+"TableViewCell *cell= [self.cacheCells objectForKey:reuseIdentifier];\n";
				    m+="if (!cell) {\n";
				    m+="  cell=[self.tableView dequeueReusableCellWithIdentifier:"+className+"CellIdentifier];\n";
				    m+="  [self.cacheCells setObject:cell forKey:reuseIdentifier];\n";
				    m+="}\n\n";

				    m+=controllers;
				    
				    
				    if(line.contains("分页"))
				    {
					m+="\n//分页Start(可注调)\n";
				    m+="if([indexPath row] == ([rows count])  && [rows count]>0) {\n";
				        
				      m+="if( currentRowCount<totalRowCount)\n";
				      m+="  {//LoadMoreView\n";
				    	m+="     return 0;\n";
				      m+=" }else\n";
				      m+=" {\n";
				    		m+="    return 0;\n";
				      m+=" }\n";     
				   m+="}else  if([indexPath row] == ([rows count])  && [rows count]==0) {\n";
				    			m+="   return 0;\n";
				   m+="}\n";
				   m+=" else  //分页End\n\n";
				   m+=" {\n";
				 
				    m+="   int height=cell.contentView.frame.size.height;//非动态高度(row1跟row2同样高)变化适用 不需配合上边使用   \n";
				    m+="return height+1;\n";
				    m+=" }\n";
				    }else
				    {
				        m+="   int height=cell.contentView.frame.size.height;//非动态高度(row1跟row2同样高)变化适用 不需配合上边使用   \n";
					    m+="return height+1;\n";
				    }
			    	content += m;
			 }
		 
			 if(line.contains("@end//end viewController")  &&isHeadCell==false)	
			 {
				 
String m="";

if(isNineList)
{
m+="\n//九宫图列表数据(九宫图列表用到)Start\n";
m+="@interface Section : NSObject\n";
m+="@property (strong,nonatomic) NSString *title;\n";
m+="@property (strong,nonatomic) NSString *sectionId;\n";
m+="@property (strong,nonatomic) NSMutableArray *sectionRows;\n";
m+="@end\n\n";




m+="@interface Row : NSObject\n";
m+="@property (strong,nonatomic) NSMutableArray *rowChirlds;\n";
m+="@end\n\n";


m+="@interface Chirld : NSObject\n";
m+="@property (strong,nonatomic) NSString *pic;\n";
m+="@property (strong,nonatomic) NSString *picName;\n";
m+="@property (strong,nonatomic) NSString *picPrice;\n";
m+="@property (strong,nonatomic) NSString *productId;\n";
m+="@end\n\n";

m+="//九宫图列表数据(九宫图列表用到)End\n\n";
}




content += m;
			 }

		}

		content=ImportString.autoAddImportInMFileHead(content);
	 String filename=FileUtil.makeFile(waitByModifyFileName, content);
	}


	String controllers="";
	public void analyse(List<CompomentBean> oldBeans) {
		
		//n+="    NSMutableDictionary *sectionADic=[sectionAZDicArray objectAtIndex:indexPath.section];  \n";
		//n+="    \n";
		//n+="    NSMutableArray *sectionChirldsArray=[sectionADic objectForKey:@\"SectionChirldsArray\"];\n";
		//n+="    NSMutableDictionary *chirldDic=[sectionChirldsArray objectAtIndex:indexPath.row];\n";
		int maxW = 0;
		int maxH = 0;
		List<CompomentBean> layouts = new ArrayList<CompomentBean>();
		CompomentBean maxBean = null;
		// 找出容器
		for (CompomentBean bean : oldBeans) {
			if (bean.type.contains("Layout")) {
				if (bean.w >= maxW) {
					maxW = bean.w;
					maxBean = bean;
				}

				if (bean.h >= maxH) {
					maxH = bean.h;
					maxBean = bean;
				}

				layouts.add(bean);
			}
		}
		
		
		parent(maxBean);
	
		
  
	}

	public void parent(CompomentBean bean) {

		if (bean.chirlds != null && bean.chirlds.size() > 0) {
			for (CompomentBean chirld : bean.chirlds) {

				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					parent(chirld);
				} else {
					 	String selfString = "cell.";
					compomentDeclareImplement.chirld(chirld, bean, selfString,"TableViewCell");
					controllers+=compomentDeclareImplement.viewDidLoad_Declare;
					viewDidLoad_Implement+=compomentDeclareImplement.viewDidLoad_Implement;
					editTextCheck+=compomentDeclareImplement.editTextCheck;
					closeKeyboardDeclare+=compomentDeclareImplement.closeKeyboardDeclare;
					
				}
			}

		}

	}



	
  
}
