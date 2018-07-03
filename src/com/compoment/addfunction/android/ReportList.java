package com.compoment.addfunction.android;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.RegexUtil;

public class ReportList {
	String sourceAddress = KeyValue.readCache("compomentProjectAddress");// "C:\\Documents and Settings\\Administrator\\My Documents\\下载\\mobile-android";
	String destinationAddress = KeyValue.readCache("projectPath");
	String waitByModifyFileName;
	String horizontalScrollViewClassName = "";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ReportList("");
	}

	public ReportList(String waitByModifyFileName) {
		this.waitByModifyFileName = waitByModifyFileName;//包含路径

		
		if(waitByModifyFileName.contains("Adapter"))
		{
			addFunctionForAdapter(waitByModifyFileName);
		}else
		{
			addFunctionForActivity();
			int p=waitByModifyFileName.lastIndexOf(".");
			String pre=waitByModifyFileName.substring(0, p);
			String suffix=waitByModifyFileName.substring(p);
			
			if(FileUtil.isFileExist(pre+"Adapter"+suffix))
			{
				 addFunctionForAdapter(waitByModifyFileName);
			}else
			{
				JOptionPane.showMessageDialog(null, "Adapter也需添加此功能", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
				
			}
			
		}
		
			
			
		
	}



	public void addFunctionForActivity() {

		List addedLines = new ArrayList();

		RegexUtil regex = new RegexUtil();

		List lines = FileUtil.fileContentToArrayList(waitByModifyFileName);

		String activityClassName="";

		// 是否已注入过此功能
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}

			if (line.contains("//报表列表")) {
				return;
			}
		}

		boolean findViewByIdFirst = true;

		//
		String content = "";
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}

			if (regex.constructFunctionRegex(line) != null) {
				horizontalScrollViewClassName = regex.constructFunctionRegex(line)+"HorizontalScrollView";
				activityClassName=regex.constructFunctionRegex(line);
				String m = "";
				m += "\n//报表列表\n";
				m += "public HorizontalScrollView mTouchView;\n";
				m += "public List<"+horizontalScrollViewClassName+"> mHScrollViews = new ArrayList<"+horizontalScrollViewClassName+">();\n";

				content += m;
			} else if (regex.functionRegex(line) != null
					&& regex.functionRegex(line).equals("onScroll")) {

				String m = "//报表列表\n";
				m += "	public void addHViews(final "+horizontalScrollViewClassName+" hScrollView) {\n";
				m += "			if (!mHScrollViews.isEmpty()) {\n";
				m += "				int size = mHScrollViews.size();\n";
				m += "				"+horizontalScrollViewClassName+" scrollView = mHScrollViews.get(size - 1);\n";
				m += "				final int scrollX = scrollView.getScrollX();\n";

				m += "				if (scrollX != 0) {\n";
				m += "					listView.post(new Runnable() {\n";
				m += "						@Override\n";
				m += "						public void run() {\n";
				m += "							hScrollView.scrollTo(scrollX, 0);\n";
				m += "						}\n";
				m += "					});\n";
				m += "				}\n";
				m += "			}\n";
				m += "			mHScrollViews.add(hScrollView);\n";
				m += "		}\n\n";

				m += "//报表列表\n";
				m += "		public void onHorizontalScrollChanged (int l, int t, int oldl, int oldt) {\n";
				m += "			for ("+horizontalScrollViewClassName+" scrollView : mHScrollViews) {\n";
				m += "				if (mTouchView != scrollView)\n";
				m += "					scrollView.smoothScrollTo(l, t);\n";
				m += "			}\n";
				m += "		}\n";

				content += m;

			}

			content += line + "\n";
			
			if(line.contains("inflateView(R.layout."))
			{
			String m = "//报表列表\n";
			m+=horizontalScrollViewClassName+" headerScroll = ("+horizontalScrollViewClassName+") containView.findViewById(R.id.item_horizontalscroll);\n";
			m+="mHScrollViews.add(headerScroll);\n\n";
			content += m;
			}

		}

		String filename = FileUtil.makeFile(waitByModifyFileName, content);
		createHorizontalScrollView(activityClassName);
	}

	public void addFunctionForAdapter(String adapterFilePath) {

		List addedLines = new ArrayList();

		RegexUtil regex = new RegexUtil();

		List lines = FileUtil.fileContentToArrayList(adapterFilePath);

		String adapterClassName = "";

		// 是否已注入过此功能
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}

			if (line.contains("//报表列表")) {
				return;
			}
		}

	

		//
		String content = "";
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}

			if (regex.constructFunctionRegex(line) != null) {
				adapterClassName = regex.constructFunctionRegex(line);
				
			}

			content += line + "\n";

			if (line.contains("R.layout."))

			{
				String m = "//报表列表\n";
				m += "(("+adapterClassName.replace("Adapter", "")+")mContext)"+".addHViews(("+adapterClassName.replace("Adapter", "")+"HorizontalScrollView) convertView\n";
				m += "		.findViewById(R.id.item_horizontalscroll));\n";
				content += m;
			}

		}

		String filename = FileUtil.makeFile(adapterFilePath, content);
		
		
		 String xmlFilePath=KeyValue.readCache("projectPath")+"/src/android/xml/"+adapterClassName.replace("Adapter", "").toLowerCase()+"_item.xml";
		
		 
		 addFunctionForAdapterXml(adapterClassName,xmlFilePath);
		
	}

	public void addFunctionForAdapterXml(String adapterClassName,String xmlFilePath) {
		
	
	

		List addedLines = new ArrayList();

		RegexUtil regex = new RegexUtil();

		List lines = FileUtil.fileContentToArrayList(xmlFilePath);

		

		// 是否已注入过此功能
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}

			if (line.contains("注入报表列表")) {
				return;
			}
		}

		boolean findViewByIdFirst = true;

		//
		String content = "";
		for (int i = 0; i < lines.size(); i++) {
			String line = "";
			if (lines.get(i) == null) {
				line = "";
			} else {
				line = lines.get(i).toString();
			}
			content += line + "\n";

			if (i == lines.size() - 1)

			{
				String m = "<!--注入报表列表   插到固定列后面  后边所有做为滚动-->\n";
				m += "<com."+adapterClassName.replace("Adapter", "")+"HorizontalScrollView"+"\n";
				m += "android:id=\"@+id/item_horizontalscroll\"\n";
				m += "android:layout_width=\"wrap_content\"\n";
				m += "android:layout_height=\"wrap_content\"\n";
				m += "android:scrollbars=\"none\" >\n";

				m += "<LinearLayout\n";
				m += "    android:layout_width=\"wrap_content\"\n";
				m += "    android:layout_height=\"wrap_content\"\n";
				m += "    android:orientation=\"horizontal\" >\n";
				content += m;
			}
		}

		String filename = FileUtil.makeFile(xmlFilePath, content);
	}

	public void createHorizontalScrollView(String activityName) {
		

		
		String m = "";
		m+="import android.content.Context;\n";
		m+="import android.util.AttributeSet;\n";
		m+="import android.view.MotionEvent;\n";
		m+="import android.widget.HorizontalScrollView;\n";
		m += "public class "+horizontalScrollViewClassName+" extends HorizontalScrollView {\n";
		m += "	\n";
		m += "	Context context;\n";
		m += "	\n";
		m += "	public "+horizontalScrollViewClassName+"(Context context, AttributeSet attrs, int defStyle) {\n";
		m += "		super(context, attrs, defStyle);\n";
		m += "		if (context != null) {\n";
		m += "			this.context = context;\n";
		m += "		}\n";
		m += "	}\n";

		m += "	\n";
		m += "	public "+horizontalScrollViewClassName+"(Context context, AttributeSet attrs) {\n";
		m += "		super(context, attrs);\n";
		m += "		if (context != null) {\n";
		m += "			this.context = context;\n";
		m += "		}\n";
		m += "	}\n";

		m += "	public "+horizontalScrollViewClassName+"(Context context) {\n";
		m += "		super(context);\n";
		m += "		if (context != null) {\n";
		m += "			this.context = context;\n";
		m += "		}\n";
		m += "	}\n";
		m += "	\n";
		m += "	@Override\n";
		m += "	public boolean onTouchEvent(MotionEvent ev) {\n";
		m += "		if (context != null) {\n";
		m += "			if(context instanceof " + activityName + ") {\n";
		m += "				((" + activityName + ") context).mTouchView = this;\n";
		m += "			}\n";

		m += "		}\n";
		m += "		return super.onTouchEvent(ev);\n";
		m += "	}\n";
		m += "	\n";
		m += "	@Override\n";
		m += "	protected void onScrollChanged(int l, int t, int oldl, int oldt) {\n";
		m += "		if (context != null) {\n";
		m += "			if(context instanceof " + activityName + ") {\n";
		m += "				" + activityName + " activity = ((" + activityName
				+ ") context);\n";
		m += "				if(activity.mTouchView == this) {\n";
		m += "					activity.onHorizontalScrollChanged (l, t, oldl, oldt);\n";
		m += "				}else{\n";
		m += "					super.onScrollChanged(l, t, oldl, oldt);\n";
		m += "				}\n";
		m += "			}\n";
		m += "		}\n";
		m += "		\n";
		m += "	}\n";
		m += "}\n";
		
		//FileUtil.makeFile(KeyValue.readCache("picPath"), "src/android", horizontalScrollViewClassName,
				//"java", m);
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/android", horizontalScrollViewClassName,
				"java", m);

	}

	

}
