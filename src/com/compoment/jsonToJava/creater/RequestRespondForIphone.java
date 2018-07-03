package com.compoment.jsonToJava.creater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;


import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.google.gson.Gson;

/**
 * 请求 接收 函数
 * */
public class RequestRespondForIphone {

//	public void requestRespond(List<InterfaceBean> interfaceBeans) {
//		if (interfaceBeans == null)
//			return;
//
//		for (InterfaceBean interfaceBean : interfaceBeans) {
//
//			request(interfaceBean);
//			respond(interfaceBean);
//		}
//	}

	public static boolean isMutiPage=false;
	public String request(InterfaceBean interfaceBean) {
		String m = "\n\n\n";
		List<String> mChirldClass = new ArrayList();
		String className="RequestParam" + interfaceBean.id ;	
		
	
		
		m += "/*" + interfaceBean.title + interfaceBean.id + "*/\n";
		m+="NSString  *n"+interfaceBean.id+"=@\""+interfaceBean.id +"\";\n";
		m += "/*" + interfaceBean.title + interfaceBean.id + "*/\n";
		String ismoreString="";
		m += "-(void) request"+interfaceBean.id+ismoreString+"{\n";
		
		String mutiPage="";
		isMutiPage=false;
		
		m+=mutiPage;
		m+="NSMutableDictionary *businessparam=[[NSMutableDictionary alloc] init];\n";
		
		
		 
		List<Group> groups = interfaceBean.requestGroups;
		int groupCount=0;
		for (Group group : groups) {
		
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {

				int i = 0;
				boolean isCustomerClass = false;
				for (Row row : group.rows) {
					
					if (i == 0) {
						// 循环域开始
						if (row.type != null && !isCommonType(row.type)) {//自定义对象
							
							isCustomerClass = true;
						} else {//非自定义对象
							
							m += "/* " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m+="[businessparam setValue:@\"\" forKey:@\""+row.enName+"\"];\n";
							isCustomerClass = false;
						}
					} else {
						if (isCustomerClass) {

						} else {
							m += "/* " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							m+="NSMutableArray *"+row.enName+"List=[[NSMutableArray alloc]init];\n";
							m+="// ["+row.enName+"List addObject:@\"\"];\n";
							m+="[businessparam setValue:"+row.enName+"List forKey:@\""+row.enName+"\"];\n";

						}
					}
					i++;
				}
			
				m+="}\n\n";
				
			} else {
				
				for (Row row : group.rows) {
					if(row.enName.toLowerCase().contains("page") && row.cnName.contains("页"))
					{
						isMutiPage=true;
						
						
					}
					
					m += "/* " + row.cnName + " 备注:" + row.remarks + "*/\n";
					m+="[businessparam setValue:@\"\" forKey:@\""+row.enName+"\"];\n";
				}
			}
		}

		
		//分页
		if(isMutiPage)
		{
		ismoreString+=":(BOOL)ismore";
		mutiPage+="\n//分页Start\n";
		mutiPage+="if(ismore)\n";
		mutiPage+="{\n";
		mutiPage+="if (requestUnComplete==false) {\n";
		mutiPage+="requestUnComplete=true;\n";
		mutiPage+="}else\n";
		mutiPage+="{\n";
		mutiPage+="return;\n";
		mutiPage+="}\n";
		mutiPage+="}else //分页End \n\n";
		mutiPage+="{\n";
		
		mutiPage+="if (requestUnComplete==false) {\n";
		mutiPage+="requestUnComplete=true;\n";
		mutiPage+="}else\n";
		mutiPage+="{\n";
		mutiPage+="return;\n";
		mutiPage+="}\n";
		
		mutiPage+="totalRowCount=0;\n";
		mutiPage+="currentRowCount=0;\n";
		mutiPage+="page=1;\n";
		         
		mutiPage+=" [rows removeAllObjects];\n";
		         
		mutiPage+="//if(allIndexpaths!=nil && [allIndexpaths count]>0)\n";
		mutiPage+="//{\n";
		mutiPage+="// [self.tableView deleteRowsAtIndexPaths:allIndexpaths withRowAnimation:UITableViewRowAnimationFade];\n";
		mutiPage+="//}\n";
		mutiPage+=" //[ allIndexpaths  removeAllObjects];\n\n";
		         
		mutiPage+="}\n\n";
		}
		
		
		m+=" ServiceInvoker *serviceInvoker=[[ServiceInvoker alloc ]init];\n";
		
		m+=" [serviceInvoker callWebservice:businessparam otherParam:nil  formName:n"+interfaceBean.id+"  delegate:self   viewController:self];\n";
		
		

		m += "}\n\n";

		System.out.println(m);
		return m;
	}
	
	
	public String  respond(String baseJson,InterfaceBean interfaceBean,boolean isNineList) {
		String m = "\n\n\n";
	
		String className="RespondParam" + interfaceBean.id ;	
		
		
		m+="NSMutableArray *listData"+interfaceBean.id+"=[[NSMutableArray alloc]init];\n";
		
	
		
		m += "/*" + interfaceBean.title + interfaceBean.id + "*/\n";
		m += "if ([msgReturn.formName isEqualToString:n"+interfaceBean.id +"]){\n";

		if(isMutiPage)
		{
		m+="//分页Start\n";
		m+="requestUnComplete=false;//避免重复请求 一个发完下一个再发\n";
		m+="totalRowCount=commonItem.totalNum;\n";
		m+="currentRowCount+=commonItem.recordNum;\n";

		m+="if (commonItem.recordNum>0) {\n";
		m+="    if (currentRowCount< totalRowCount) {\n";
		m+="        page++;\n";
		      
		m+="    }\n";
		m+="}else if(commonItem.recordNum==0)\n";
		m+="{\n";
		m+="// 暂无数据\n";
		m+="}\n";
		m+="//分页End\n\n";
		}

		JsonToIosBeanForSimple jsonToIosBeanForSimple=new JsonToIosBeanForSimple(baseJson);
		m+=jsonToIosBeanForSimple.getJaveBeanClass();
		
		List<Group> groups = interfaceBean.respondGroups;
		

		int groupCount=0;
		for (Group group : groups) {
		
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {

				int i = 0;
				boolean isCustomerClass = false;
				for (Row row : group.rows) {
					
					if (i == 0) {
						// 循环域开始
						if (row.type != null && !isCommonType(row.type)) {//自定义对象
							
							isCustomerClass = true;
						} else {//非自定义对象
							m += "/* " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
						  
							m+="int "+row.enName+"= [[returnDataBody objectForKey:@\""+row.enName+"\"]intValue];\n";
							m+="for(int i=0;i<"+row.enName+";i++)\n{\n";
							  m+=className+" *item"+groupCount+"=[["+className+" alloc]init];\n";
							isCustomerClass = false;
						}
					} else {
						if (isCustomerClass) {

						} else {
							m += "/* " + row.cnName + " 备注:" + row.remarks
									+ "*/\n";
							if(row.type.toLowerCase().equals("int"))
							{
								m += "item"+groupCount+"."+row.enName+"=[[[returnDataBody objectForKey:@\""+row.enName+"\"] objectAtIndex:i]intValue];\n";
								
							}else if(row.type.toLowerCase().equals("float"))
							{
								m += "item"+groupCount+"."+row.enName+"=[[[returnDataBody objectForKey:@\""+row.enName+"\"] objectAtIndex:i]floatValue];\n";
								
							}else
							{
							m += "item"+groupCount+"."+row.enName+"=[[returnDataBody objectForKey:@\""+row.enName+"\"] objectAtIndex:i];\n";
							}
						}
					}
					i++;
				}
				m+="[listData"+interfaceBean.id+" addObject:item"+groupCount+"];\n";
				m+="}\n\n";
				
			} else {
				 m+=className+" *commonItem"+"=[["+className+" alloc]init];\n";
				for (Row row : group.rows) {
					m += "/* " + row.cnName + " 备注:" + row.remarks + "*/\n";
					if(row.type.toLowerCase().equals("int"))
					{
						m +=  "commonItem." + row.enName + "=[[returnDataBody objectForKey:@\""+row.enName+"\"]intValue];\n";
						
					}else if(row.type.toLowerCase().equals("float"))
					{
						m +=  "commonItem." + row.enName + "=[[returnDataBody objectForKey:@\""+row.enName+"\"]floatValue];\n";
					}else
					{
					
					m +=  "commonItem." + row.enName + "=[returnDataBody objectForKey:@\""+row.enName+"\"];\n";
					}
					
				}
			}
          groupCount++;
		}
		
		if(isNineList)
		{
			
			
				m+="//九宫图列表数据Start\n";
				m+="Row *sectionRow;\n";
				m+="NSMutableArray *thisPageRows=[[NSMutableArray alloc] init];\n";
				m+="			    for (int i=0; i<[mdata count]; i++) {\n";
				m+="			        RespondParam"+interfaceBean.id+" *commonItem2=mdata[i];\n";
				m+="			        \n";
				m+="			        \n";
				m+="			        if (i==0 || i%3==0) {//每行3个\n";
				m+="			            sectionRow=[[Row alloc ] init];\n";
				m+="			            sectionRow.rowChirlds=[[NSMutableArray alloc]init];\n";
				m+="			            [thisPageRows addObject:sectionRow];\n";
				m+="			            [rows addObject:sectionRow];\n";
				m+="			        }\n";
				m+="			        \n";
				m+="			        Chirld *rowChirld=[[Chirld alloc] init ];\n";
				m+="			        rowChirld.productId=commonItem2.merchID;\n";
				m+="			        rowChirld.pic=commonItem2.merchPicID;\n";
				m+="			        rowChirld.picName=commonItem2.merchName;\n";
				m+="			        rowChirld.picPrice=[NSString stringWithFormat:@\"%.2f\",commonItem2.merchPrice] ;\n";
				m+="			        \n";
				m+="			        //chirld add\n";
				m+="			        [sectionRow.rowChirlds addObject:rowChirld];\n";
				m+="			        \n";
				m+="			        \n";
				m+="			    }\n\n";
				m+="//九宫图列表数据End\n\n";
				
		}
		
		if(isMutiPage)
		{
			m+="[tableView reloadData];\n";
			m+="//NSMutableArray *insertIndexPaths = [[NSMutableArray alloc]init];\n";
			m+="//for (int ind = 0; ind < [thisPageRows count]; ind++) {\n";
			m+="  //  NSIndexPath    *newPath =  [NSIndexPath indexPathForRow:[rows indexOfObject:[thisPageRows objectAtIndex:ind]] inSection:0];\n";
			m+="   // [allIndexpaths addObject:newPath];\n";
			m+="   // [insertIndexPaths addObject:newPath];\n";
			m+="//}\n";
			m+="//[self.tableView insertRowsAtIndexPaths:insertIndexPaths withRowAnimation:UITableViewRowAnimationFade];\n";
		}
		m += "}\n\n";

	

		System.out.println(m);
		return m;
	}

	
	
	

	public boolean isCommonType(String type) {
		if (type.equals("String") || type.equals("int") || type.equals("long")||type.equals("float")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNum(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	
	
	public void serviceInvoker()
	{
		
		
		
	}
}
