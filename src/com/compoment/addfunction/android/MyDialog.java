package com.compoment.addfunction.android;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.RegexUtil;

public class MyDialog {


	String sourceAddress = KeyValue.readCache("compomentProjectAddress");//"C:\\Documents and Settings\\Administrator\\My Documents\\下载\\mobile-android";
	String destinationAddress = KeyValue.readCache("projectPath");
	String waitByModifyFileName;
    int appendline;
    
    /**1."title:msg:leftBtn:rightBtn"    2."title:list"*/
    String type="1";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Paging("");
	}

	public MyDialog(String waitByModifyFileName,int appendline,String type) {
		this.waitByModifyFileName = waitByModifyFileName;
		this.appendline=appendline;
		this.type=type;
		add();
	}



	

	public void add() {
	
		
		
		List addedLines =new ArrayList();
	
		RegexUtil regex = new RegexUtil();

		List lines = FileUtil.fileContentToArrayList(waitByModifyFileName);


		String content = "";
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}

		
	     
			
			content += line + "\n";
			
			  if (appendline==i){
			    	 
				  String m="";
			    	if(type.equals("1"))
			    	{
			    		m=type1();
			    		
			    	}else if(type.equals("2"))
			    	{
			    		m=type2();
			    	}else if(type.equals("3"))
			    	{
			    		m=type3();
			    	}
			    	
					content += m;
				}
			
			

		}

	 String filename=FileUtil.makeFile(waitByModifyFileName, content);
	}
	
	
	 /**1."title:msg:leftBtn:rightBtn:rightBtn2""*/
	public String type1()
	{
		String m="";
    	m+="	AlertDialog.Builder builder = new AlertDialog.Builder(this);\n";
    	m+="		builder.setTitle(\"title\");\n";
    	m+="		builder.setMessage(\"msg\").setCancelable(false);\n";
    	m+="		builder.setPositiveButton(\"确定\",\n";
    	m+="				new DialogInterface.OnClickListener() {\n";
    	m+="					public void onClick(DialogInterface dialog, int id) {\n";
    	m+="						\n";
    	m+="					}\n";
    	m+="				});\n";
    	m+="		builder.setNegativeButton(\"取消\",\n";
    	m+="				new DialogInterface.OnClickListener() {\n";
    	m+="					public void onClick(DialogInterface dialog, int id) {\n";
    	m+="						dialog.cancel();\n";

    	m+="					}\n";
    	m+="				});\n";
    	
    	m+="		builder.setNegativeButton(\"取消2\",\n";
    	m+="				new DialogInterface.OnClickListener() {\n";
    	m+="					public void onClick(DialogInterface dialog, int id) {\n";
    	m+="						dialog.cancel();\n";

    	m+="					}\n";
    	m+="				});\n";
    	
    	m+="		builder.show();\n";
    	System.out.println(m);
    	return m;
	}
	
	 /**  2."title:list"*/
	public String  type2()
	{
		String m="";
		m+=" final String[] arrayFruit = new String[] { \"苹果\", \"橘子\", \"草莓\", \"香蕉\" };\n";
		m+=" final String[] arrayFruitIndex = new String[] { \"01\", \"02\", \"03\", \"04\" };\n";
		m+="		\n";
		m+="			  Dialog alertDialog = new AlertDialog.Builder(this).\n";
		m+="			    setTitle(\"你喜欢吃哪种水果？\").\n";
		m+="			    setIcon(R.drawable.ic_launcher)\n";
		m+="			    .setItems(arrayFruit, new DialogInterface.OnClickListener() {\n";
		m+="			 \n";
		m+="			     @Override\n";
		m+="			     public void onClick(DialogInterface dialog, int which) {\n";
		m+="			      Toast.makeText(Dialog_AlertDialogDemoActivity.this, arrayFruit[which]+\"\"+arrayFruitIndex[which], Toast.LENGTH_SHORT).show();\n";
		m+="			     }\n";
		m+="			    }).\n";
		m+="			    setNegativeButton(\"取消\", new DialogInterface.OnClickListener() {\n";
		m+="		\n";
		m+="			     @Override\n";
		m+="			     public void onClick(DialogInterface dialog, int which) {\n";
		m+="			      // TODO Auto-generated method stub\n";
		m+="			     }\n";
		m+="			    }).\n";
		m+="			    create();\n";
		m+="			  alertDialog.show();\n";
		System.out.println(m);
		return m;

	}
	
	//EditText
	public String type3()
	{
String m="";
m+="            EditText  view=new EditText(this);	   \n";
m+="			Builder builder =new AlertDialog.Builder(this);\n";
m+="			builder.setTitle(\"title\");\n";
m+="			builder.setView(view);                                           \n";
m+="			builder.setPositiveButton(\"确定\", new OnClickListener()\n";
m+="			{\n";
m+="				@Override\n";
m+="				public void onClick(DialogInterface dialog,int which)\n";
m+="				{\n";

m+="                                   String   inputNum=(EditText)view.getText().toString();\n";
m+="				}\n";
m+="			});\n";
m+="			builder.setNegativeButton(\"取消\", new OnClickListener()\n";
m+="			{\n";
m+="				@Override\n";
m+="				public void onClick(DialogInterface dialog, int which) {\n";
m+="				\n";
m+="				}\n";
m+="			});\n";
m+="			builder.create().show();\n";
System.out.println(m);
return m;
	}
	
	
}
