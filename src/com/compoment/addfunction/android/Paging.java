package com.compoment.addfunction.android;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.RegexUtil;
import com.compoment.util.RegexUtil.ControllerBean;


/***
 * 分页
 * */
public class Paging {


	

	String sourceAddress = KeyValue.readCache("compomentProjectAddress");//"C:\\Documents and Settings\\Administrator\\My Documents\\下载\\mobile-android";
	String destinationAddress = KeyValue.readCache("projectPath");
	String waitByModifyFileName;


	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Paging("");
	}

	public Paging(String waitByModifyFileName) {
		this.waitByModifyFileName = waitByModifyFileName;
		copyFile();
		add();
	}

	public void copyFile() {
		
		List<FileBean> fileBeans = new ArrayList();

		
		fileBeans.add(new FileBean("/res/layout", null,
				"listfooter", "xml"));



		for (FileBean bean : fileBeans) {

			File wantFile = FileUtil.findFile(new File(sourceAddress
					+ bean.sourcePath), bean.name + "." + bean.type);

			if (wantFile == null)
				System.out.println("找不到此文件" + bean.name + "." + bean.type);

			// 图片文件
			if (bean.type.equals("png") || bean.type.equals("jpg")) {
				try {
					FileUtil.copyFile(wantFile, new File(destinationAddress
							+ bean.destinationPath + bean.name + "."
							+ bean.type));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {// 其它文件

				List lines = FileUtil.fileContentToArrayList(wantFile
						.getAbsolutePath());

				String content = "";
				for (int i = 0; i < lines.size(); i++) {
					String line = "";
					if (lines.get(i) == null) {
						line = "";
					} else {
						line = lines.get(i).toString();
					}

					content += line + "\n";
				}

				String filename = FileUtil.makeFile(destinationAddress
						+ bean.destinationPath, null, bean.name, bean.type,
						content);
				System.out.println(filename);
			}
		}

	}

	

	public void add() {
	
		
		
		List addedLines =new ArrayList();
	
		RegexUtil regex = new RegexUtil();

		List lines = FileUtil.fileContentToArrayList(waitByModifyFileName);

		
		String className="";
		
		//是否已注入过此功能
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}

			if(line.contains("//注入分页功能"))
			{
				return;
			}
		}

		
		boolean findViewByIdFirst=true;
		
		//
		String content = "";
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}

		
	       if ( regex.constructFunctionRegex(line)!=null) {
	    	   className=regex.constructFunctionRegex(line);
		    	String m="";
		    	m+="\n//注入分页功能\n";
		    	m+="private int recordCount = 1;//发分页请求时用（起始记录号 ）\n";
		    	m+="private int page = 1;//页码\n";
		    	m+="private int totalPage;\n";
		    	m+="private int pageSize = 5;// 每页5条数据\n";
		    	m+="private View footerView;\n";
		    	m+="private View load;\n";
		    	m+="private View more;\n";
		    	m+="private View progress;\n\n\n";
		    	
				content += m;
			}else  if (findViewByIdFirst&& regex.findViewByIdRegex(line)!=null) {
				
				String m="\n//分页\n";
				m+="footerView =inflateView(R.layout.listfooter);\n";
		    	m+="more =footerView.findViewById(R.id.more);\n";
		    	m+="progress =footerView.findViewById(R.id.progress);\n";
		    	m+="load =footerView.findViewById(R.id.load);\n\n";
		    	content += m;
		    	findViewByIdFirst=false;
			}else  if (regex.functionRegex(line)!=null && regex.functionRegex(line).equals("onScroll")) {
				
				String m="//分页\n";
		    	m+="	public void countPage(int maxCounts) {\n";
		    	m+="		if (maxCounts % pageSize == 0) {\n";
		    	m+="			totalPage = maxCounts / pageSize;\n";
		    	m+="		} else {\n";
		    	m+="			totalPage = maxCounts / pageSize + 1;\n";
		    	m+="		}\n";
		    	m+="		\n";
		    	m+="		if(page==totalPage)\n";
		    	m+="		{\n";
		    	m+="			footerView.setVisibility(View.GONE);\n";
		    	m+="		}else\n";
		    	m+="		{\n";
		    	m+="			more.setVisibility(View.VISIBLE);\n";
		    	m+="			progress.setVisibility(View.GONE);\n";
		    	m+="			load.setVisibility(View.GONE);\n";
		    	m+="			footerView.setVisibility(View.VISIBLE);\n";
		    	m+="		}\n";
		    	m+="	}\n\n";

		    	content += m;
		    	
			}else if(line.contains(".setOnScrollListener"))
				
			{
				String m="//分页\n";
				m+="(listData.add()之前加入  int oldSize = listData.size();)\n";
		    	m+="countPage(maxCount);\n";
		    	m+="listListView.setSelection(oldSize);\n";
		    	content += m;
			}else if(line.contains("//分页使用"))
			{
				int p=line.indexOf("//");
				if(p!=-1)
				{
					line=line.substring(p+2);
				}
			}
			
			content += line + "\n";
			
			 if (regex.functionRegex(line)!=null && regex.functionRegex(line).equals("onScrollStateChanged")) {
				 
					String m="//分页\n";
				    m+="		if (scrollState == SCROLL_STATE_IDLE) {\n";
			    	m+="			if (view.getLastVisiblePosition() == (view.getCount() - 1)) {\n";
			    	m+="				if ((page) < totalPage) {\n";
			    	m+="					page++;\n";
			    	m+="					recordCount += pageSize;\n";
			    	m+="					request...();\n";
			    	m+="					Toast.makeText("+className+".this, \"数据已加载...\" + page + \" 页\",\n";
			    	m+="							Toast.LENGTH_SHORT).show();\n";
			    	m+="					\n";
			    	m+="					more.setVisibility(view.GONE);\n";
			    	m+="					progress.setVisibility(View.VISIBLE);\n";
			    	m+="					load.setVisibility(View.VISIBLE);\n";
			    	m+="					footerView.setVisibility(View.VISIBLE);\n";
			    	m+="				} else {\n";

			    	m+="					Toast.makeText("+className+".this, \"数据已加载完...\" + page + \" 页\",\n";
			    	m+="							Toast.LENGTH_SHORT).show();\n";
			    	m+="					\n";
			    	m+="					footerView.setVisibility(View.GONE);\n";

			    	m+="				}\n";
			    	m+="			}\n";
			    	m+="		}\n";
			    	content += m;
			 }

		}

	 String filename=FileUtil.makeFile(waitByModifyFileName, content);
	}

	
	
	
  
}
