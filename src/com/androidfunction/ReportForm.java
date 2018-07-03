package com.androidfunction;

import com.compoment.util.FileUtil;
 
public class ReportForm {

	String formNameCn="故障终端";
	String formNameEn="ErrorPackageCabinet";
	String formSearchInputPart1="";
	String formSearchInputPart1Cn="";
	
	String results[]={"dept_code","dept_name","time","gekou"};
	String resultsCn[]={"dept_code","单位名称","报错小时数","格口数"};
	
	String outPath="C:/CreateCode/ReportForm";
	String javapackage="com.chinapost.palmpost.smartpackage";
	
	public static void main(String []arg)
	{
		
		ReportForm reportForm=new ReportForm();
		reportForm.activity();
		reportForm.chscrollview();
		reportForm.mainXml();
		reportForm.xmlItem();
		
	}
	
	
	
	public void activity()
	{
		
	
		
		String m="";
		  m+="package "+javapackage+";\n";
		m+="import java.io.IOException;\n";
		m+="import java.net.ConnectException;\n";
		m+="import java.net.SocketTimeoutException;\n";
		m+="import java.text.DateFormat;\n";
		m+="import java.text.ParseException;\n";
		m+="import java.text.SimpleDateFormat;\n";
		m+="import java.util.ArrayList;\n";
		m+="import java.util.Calendar;\n";
		m+="import java.util.Date;\n";
		m+="import java.util.HashMap;\n";
		m+="import java.util.List;\n";
		m+="import java.util.Map;\n";

		m+="import org.json.JSONArray;\n";
		m+="import org.json.JSONException;\n";
		m+="import org.json.JSONObject;\n";
		m+="import org.xmlpull.v1.XmlPullParserException;\n";

		m+="import com.chinapost.BaseServiceInterface;\n";
		m+="import com.chinapost.library.BaseConnectionCallBack;\n";
		m+="import com.chinapost.library.BaseServiceConnection;\n";

		
		m+="import com.chinapost.utils.Constants;\n";
		m+="import com.chinapost.utils.JsonUtils;\n";
		m+="import com.chinapost.view.date.JudgeDate;\n";
		m+="import com.chinapost.view.date.ScreenInfo;\n";
		m+="import com.chinapost.view.date.WheelMain;\n";
		m+="import com.chinapost.webservice.WebServiceUtils;\n";

		m+="import android.annotation.SuppressLint;\n";
		m+="import android.app.Activity;\n";
		m+="import android.app.AlertDialog;\n";
		m+="import android.app.DatePickerDialog;\n";
		m+="import android.app.ProgressDialog;\n";
		m+="import android.app.DatePickerDialog.OnDateSetListener;\n";
		m+="import android.content.Context;\n";
		m+="import android.content.DialogInterface;\n";
		m+="import android.graphics.Paint;\n";
		m+="import android.os.AsyncTask;\n";
		m+="import android.os.Bundle;\n";
		m+="import android.os.Handler;\n";
		m+="import android.os.Build.VERSION;\n";
		m+="import android.os.RemoteException;\n";
		m+="import android.util.Log;\n";
		m+="import android.view.KeyEvent;\n";
		m+="import android.view.LayoutInflater;\n";
		m+="import android.view.View;\n";
		m+="import android.view.ViewGroup;\n";
		m+="import android.view.View.OnClickListener;\n";
		m+="import android.widget.BaseAdapter;\n";
		m+="import android.widget.Button;\n";
		m+="import android.widget.DatePicker;\n";
		m+="import android.widget.HorizontalScrollView;\n";
		m+="import android.widget.LinearLayout;\n";
		m+="import android.widget.ListView;\n";
		m+="import android.widget.RadioButton;\n";
		m+="import android.widget.RadioGroup;\n";
		m+="import android.widget.RelativeLayout;\n";
		m+="import android.widget.SimpleAdapter;\n";
		m+="import android.widget.TextView;\n";
		m+="import android.widget.Toast;\n";
		m+="import android.widget.RadioGroup.OnCheckedChangeListener;\n";

		m+="/**\n";
		m+=" * "+formNameCn+"\n";
		m+=" * \n";
		m+=" */\n";
		m+="public class "+formNameEn+"Activity extends Activity implements\n";
		m+="		BaseConnectionCallBack {\n";

		m+="	public static final String PERMISSION = \"palmpostAndroid_DlvAmount\";// 菜单权限\n";

		m+="	private ListView mListView;\n";

		m+="	public HorizontalScrollView mTouchView;\n";

		m+="	protected List<"+formNameEn+"CHScrollView> mHScrollViews;\n";

		m+="	private BaseServiceInterface mBaseService;\n";
		m+="	private BaseServiceConnection mBaseServiceConnection = new BaseServiceConnection(\n";
		m+="			this);\n";
		m+="	private String userPara = null;\n";
		m+="	private ArrayList<HashMap<String, String>> lists = new ArrayList<HashMap<String, String>>();// 当前页数据集合\n";
		m+="	private Handler handler = new Handler();\n";

		m+="	TextView startTimeTextView;\n";
		m+="	TextView endTimeTextView;\n";

		if(!formSearchInputPart1.equals(""))
		{
		m+="	String "+formSearchInputPart1+" = \"initValue\";\n";
		}
		

		m+="	private ListView listView;// 展示列表\n";
		m+="	private Button refreshBtn;// 刷新数据按钮\n";
		m+="	RelativeLayout ll_listlayout;\n";
		m+="	TextView title_title;\n";
		m+="	private List<List<HashMap<String, String>>> pages = new ArrayList<List<HashMap<String, String>>>();\n";
		m+="	SimpleDateFormat sdfYMD = new SimpleDateFormat(\"yyyy年MM月dd日\");\n";
		m+="	Calendar calendar = Calendar.getInstance();\n";
		m+="	private LinearLayout ll_start_date_layout;// 开始日期\n";
		m+="	private LinearLayout ll_end_date_layout;// 结束日期\n";
		m+="	private TextView tv_start_date, tv_end_date;\n";

		m+="	boolean isBack = false;\n";

		m+="	@Override\n";
		m+="	protected void onCreate(Bundle savedInstanceState) {\n";
		m+="		super.onCreate(savedInstanceState);\n";
		m+="		setContentView(R.layout.activity_"+formNameEn.toLowerCase()+");\n";

		m+="		// refreshList();\n";

		m+="		Bundle extras = getIntent().getExtras();\n";
		m+="		if (extras != null) {\n";
		m+="			userPara = extras.getString(\"userPara\");\n";
		m+="		}\n";

		m+="		listView = (ListView) findViewById(R.id.scroll_list);\n";
		m+="		refreshBtn = (Button) findViewById(R.id.refresh_btn);\n";
		m+="		ll_listlayout = (RelativeLayout) findViewById(R.id.ll_listlayout);\n";

		m+="		/** title */\n";
		m+="		title_title = (TextView) findViewById(R.id.title_title);\n";
		m+="		title_title.setText(extras.getString(\"title\"));\n";

		m+="		/** 开始时间与结束时间 **/\n";
		m+="		ll_start_date_layout = (LinearLayout) findViewById(R.id.ll_start_date_layout);\n";
		m+="		ll_end_date_layout = (LinearLayout) findViewById(R.id.ll_end_date_layout);\n";
		m+="		tv_start_date = (TextView) findViewById(R.id.tv_start_date);\n";
		m+="		tv_end_date = (TextView) findViewById(R.id.tv_end_date);\n";
		m+="		calendar.add(Calendar.DATE, -1);// 结束日期是昨天\n";
		m+="		tv_end_date.setText(sdfYMD.format(calendar.getTime()));\n";
		m+="		calendar.add(Calendar.DATE, -3);// 开始日期是昨天的前三天\n";
		m+="		tv_start_date.setText(sdfYMD.format(calendar.getTime()));\n";
		m+="		OnDateClickListener clickListener = new OnDateClickListener();\n";
		m+="		ll_start_date_layout.setOnClickListener(clickListener);\n";
		m+="		ll_end_date_layout.setOnClickListener(clickListener);\n";

		if(!formSearchInputPart1Cn.equals(""))
		{
			
		
		m+="		// "+formSearchInputPart1Cn+"\n";
		m+="		RadioGroup group = (RadioGroup) this.findViewById(R.id.type_radiogroup);\n";
		m+="		// 绑定一个匿名监听器\n";
		m+="		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {\n";

		m+="			@Override\n";
		m+="			public void onCheckedChanged(RadioGroup arg0, int arg1) {\n";
		m+="				// TODO Auto-generated method stub\n";
		m+="				// 获取变更后的选中项的ID\n";
		m+="				int radioButtonId = arg0.getCheckedRadioButtonId();\n";

		m+="				// 根据ID获取RadioButton的实例\n";
		m+="				RadioButton rb = (RadioButton) findViewById(radioButtonId);\n";
		m+="				// 更新文本内容，以符合选中项\n";
		m+="				if (rb.getText().equals(\"国内小包\")) {\n";
		m+="					"+formSearchInputPart1+" = \"318\";\n";
		m+="				} else if (rb.getText().equals(\"约投挂号\")) {\n";
		m+="					"+formSearchInputPart1+" = \"21\";\n";
		m+="				} \n";
		m+="			}\n";
		m+="		});\n";
		
		}

		m+="		mListView = (ListView) findViewById(R.id.scroll_list);\n";

		m+="		/** 查询 **/\n";
		m+="		findViewById(R.id.title_right_button).setVisibility(View.VISIBLE);\n";
		m+="		findViewById(R.id.title_right_button).setOnClickListener(\n";
		m+="				new OnClickListener() {\n";
		m+="					@Override\n";
		m+="					public void onClick(View v) {\n";
		m+="						\n";
		m+="						pages.clear();\n";
		m+="						lists = new ArrayList<HashMap<String, String>>();\n";

		m+="						requestData(userPara.split(\"#\")[0], \"0\");\n";
		m+="					}\n";
		m+="				});\n";

		m+="		/** 返回上一级 **/\n";
		m+="		findViewById(R.id.title_back_button).setOnClickListener(\n";
		m+="				new OnClickListener() {\n";
		m+="					@Override\n";
		m+="					public void onClick(View v) {\n";
		m+="						if(pages!=null && pages.size()==0)\n";
		m+="						{\n";
		m+="							"+formNameEn+"Activity.this.finish();\n";
		m+="						}else\n";
		m+="						{\n";
		m+="							int count=pages.size();\n";
		m+="							pages.remove(count-1);\n";
		m+="							 count=pages.size();\n";
		m+="							 if(count==0)\n";
		m+="							 {\n";
		m+="								 "+formNameEn+"Activity.this.finish();\n";
		m+="							 }else\n";
		m+="							 {\n";
		m+="							lists=(ArrayList<HashMap<String, String>>) pages.get(count-1);\n";
		m+="							refreshList();\n";
		m+="							 }\n";
		m+="						}\n";
		m+="						\n";
		m+="						\n";

		m+="					}\n";
		m+="				});\n";

		m+="		/** 刷新 **/\n";
		m+="		refreshBtn.setOnClickListener(new OnClickListener() {\n";
		m+="			@Override\n";
		m+="			public void onClick(View v) {\n";
		m+="				\n";
		m+="				lists = new ArrayList<HashMap<String, String>>();\n";
		m+="				// pages = new ArrayList<ArrayList<HashMap<String, String>>>();\n";
		m+="				requestData(userPara.split(\"#\")[0], \"0\");\n";
		m+="			}\n";
		m+="		});\n";

		m+="		boolean result = mBaseServiceConnection.bindService();\n";
		m+="		if (result) {\n";
		m+="			setBaseService(mBaseServiceConnection.getBaseService());\n";

		m+="		}\n";

		m+="	}\n";
		
		
		for(int i=0;i<results.length;i++)
		{
		m+="	/**"+resultsCn[i]+"*/\n";
		m+="	static String "+results[i]+" = \""+results[i]+"\".toUpperCase();\n";
		}

		


		
		m+="	private void requestData(final String deptCode, String queryType) {\n";

		m+="		new AnsyTry().execute(deptCode, queryType);\n";
		m+="	}\n";
	


		m+="	class AnsyTry extends AsyncTask<String, TextView, String> {\n";

		m+="		ProgressDialog progressDialog;\n";
		m+="		String hintString = null;\n";

		m+="		@Override\n";
		m+="		protected String doInBackground(String... deptCode) {\n";
		m+="			// TODO Auto-generated method stub\n";
		m+="		\n";
		m+="			String result = null;\n";
		m+="			try {\n";
		m+="				String param = \"\";\n";
		m+="				// 请求 BaseServiceInterface mBaseService, String\n";
		m+="				// userPara,String startDate, String endDate, String\n";
		m+="				// queryType,String deptCode, String mailType, String\n";
		m+="				// delType\n";

		m+="				SimpleDateFormat sdfYMD = new SimpleDateFormat(\"yyyyMMdd\");\n";
		m+="				SimpleDateFormat sdfYMD2 = new SimpleDateFormat(\"yyyy年MM月dd日\");\n";

		m+="				// BaseServiceInterface mBaseService, String userPara,\n";
		m+="				// String startDate, String endDate, String queryType,\n";
		m+="				// String deptCode, String mailType\n";
		
		m+="				/**"+formNameCn+"**/\n";
		m+="				param = request"+formNameEn+"(mBaseService,\n";
		m+="						userPara, sdfYMD.format(sdfYMD2.parse(tv_start_date\n";
		m+="								.getText().toString()))\n";

		m+="						, sdfYMD.format(sdfYMD2.parse(tv_end_date.getText()\n";
		m+="								.toString())), deptCode[1], deptCode[0],\n";
		m+="						mailType);\n";
		m+="				Log.i(\"Tang\", \"request--->\" + param);\n";
		m+="				// 返回\n";
		m+="				result = WebServiceUtils.getReturnInfo(param);\n";

		m+="			} catch (SocketTimeoutException e) {\n";
		m+="				e.printStackTrace();\n";
		m+="				hintString = \"请求超时，请稍后再试\";\n";
		m+="			} catch (ConnectException e) {\n";
		m+="				e.printStackTrace();\n";
		m+="				hintString = \"网络连接出错，请确保手机已联网\";\n";
		m+="			} catch (IOException e) {\n";
		m+="				e.printStackTrace();\n";
		m+="				hintString = \"暂无数据，请稍后再试\";\n";
		m+="			} catch (XmlPullParserException e) {\n";
		m+="				e.printStackTrace();\n";
		m+="				hintString = \"暂无数据，请稍后再试\";\n";
		m+="			} catch (Exception e) {\n";
		m+="				e.printStackTrace();\n";
		m+="				hintString = \"暂无数据，请稍后再试\";\n";
		m+="			}\n";
		m+="			if (hintString != null) {\n";
		m+="				handler.post(new Runnable() {\n";
		m+="					@Override\n";
		m+="					public void run() {\n";
		m+="						toastError(hintString);\n";
		m+="					}\n";
		m+="				});\n";
		m+="			}\n";
		m+="			progressDialog.dismiss();\n";

		m+="			return result;\n";
		m+="		}\n";

		m+="		@Override\n";
		m+="		protected void onPostExecute(String result) {\n";
		m+="			// TODO Auto-generated method stub\n";
		m+="			super.onPostExecute(result);\n";

		m+="			Log.i(\"Tang\", \"result--->\" + result);\n";
		m+="			if (result == null) {\n";
		m+="				hintString = \"暂无数据，请稍后再试\";\n";

		m+="				toastError(hintString);\n";
		m+="				return;\n";
		m+="			}\n";

		m+="			JSONObject jsonResult;\n";
		m+="			try {\n";
		m+="				jsonResult = new JSONObject(result);\n";

		m+="				boolean success = jsonResult.getBoolean(\"success\");\n";

		m+="				final String data = jsonResult.getString(\"data\");\n";
		m+="				if (success) {\n";
		m+="					\n";
		m+="					// 解密des\n";
		m+="					String decryptStr = mBaseService.decrypt(data,\n";
		m+="							Constants.ENCRYPT_KEY);\n";
		m+="					Log.i(\"Tang\", \"decryptStr--->\" + decryptStr);\n";
		m+="					lists = reSpondExpectParse(decryptStr);\n";

		m+="					if (lists.isEmpty()) {\n";
		m+="						hintString = \"暂无数据，请稍后再试\";\n";
		m+="						\n";
		m+="						listView.setVisibility(View.INVISIBLE);\n";
		m+="						\n";
		m+="						toastError(hintString);\n";

		m+="						// progressDialog.dismiss();\n";
		m+="						// return;\n";
		m+="						\n";
		m+="					} else {\n";
		m+="						pages.add(lists);\n";
		m+="						listView.setVisibility(View.VISIBLE);\n";
		m+="						ll_listlayout.setVisibility(View.VISIBLE);\n";
		m+="						refreshBtn.setVisibility(View.GONE);\n";
		m+="						refreshList();\n";

		m+="					\n";
		m+="					}\n";

		m+="				} else {\n";

		m+="				}\n";

		m+="			} catch (JSONException e) {\n";
		m+="				// TODO Auto-generated catch block\n";
		m+="				e.printStackTrace();\n";
		m+="			} catch (RemoteException e) {\n";
		m+="				// TODO Auto-generated catch block\n";
		m+="				e.printStackTrace();\n";
		m+="			}\n";

		m+="		}\n";

		m+="		@Override\n";
		m+="		protected void onPreExecute() {\n";
		m+="			// TODO Auto-generated method stub\\n";

		m+="			super.onPreExecute();\n";

		m+="			progressDialog = ProgressDialog.show("+formNameEn+"Activity.this,\n";
		m+="					\"查询\", \"查询中...\", true, false);\n";
		m+="		}\n";

		m+="		@Override\n";
		m+="		protected void onProgressUpdate(TextView... values) {\n";

		m+="			super.onProgressUpdate(values);\n";
		m+="		}\n";

		m+="	}\n";

	
		m+="/**请求"+formNameCn+"**/\n";
		m+="	public  String request"+formNameEn+"(\n";
		m+="			BaseServiceInterface mBaseService, String userPara,\n";
		m+="			String startDate, String endDate, String queryType,\n";
		m+="			String deptCode, String mailType) {\n";
		
		m+="		return null;\n";
		m+="	}\n";
		
		
		m+="	public static ArrayList<HashMap<String, String>> reSpondExpectParse(\n";
		m+="			String reSpondparams) {\n";
		m+="		reSpondparams = reSpondparams.replaceAll(\"null\", \"0\");\n";
		m+="		ArrayList<HashMap<String, String>> lists = new ArrayList<HashMap<String, String>>();\n";

		m+="		try {\n";
		m+="			JSONObject jsonResult = new JSONObject(reSpondparams);\n";

		m+="			JSONObject jsonResponse = jsonResult.getJSONObject(\"response\");\n";
		m+="			if (jsonResponse != null) {\n";
		m+="				boolean success = jsonResponse\n";
		m+="						.optBoolean(Constants.RESULT_EXPECT_KEY_SUCCESS);\n";
		m+="				if (!success) {\n";
		m+="					return lists;\n";
		m+="				}\n";
		m+="				if (jsonResponse.has(Constants.RESULT_CHART_KEY_GROUPS_ITEMS)) {\n";
		m+="					JSONArray arrays = jsonResponse\n";
		m+="							.getJSONArray(Constants.RESULT_CHART_KEY_GROUPS_ITEMS);\n";
		m+="					if (arrays == null || arrays.length() == 0) {\n";
		m+="						return lists;\n";
		m+="					}\n";

		
		
		
		m+="					for (int i = 0; i < arrays.length(); i++) {\n";
		m+="						JSONObject item = arrays.optJSONObject(i);\n";
		m+="						if (item != null) {\n";
		m+="							HashMap<String, String> mapItem = new HashMap<String, String>();\n";
		m+="							// =====================code and name\n";
	
		
		
		for(int i=0;i<results.length;i++)
		{
			 if(i==1)
			{
				m+="//"+resultsCn[i]+"\n";
				m+="							if (item.has("+results[i]+")) {\n";
				m+="								mapItem.put("+results[i]+",\n";
				m+="										item.optString("+results[i]+"));\n";
				m+="							}\n";
			}
		}
		
		for(int i=0;i<results.length;i++)
		{
			 if(i==0)
			{
				m+="//"+resultsCn[i]+"\n";
				m+="							if (item.has("+results[0]+")) {\n";
				m+="								String name = mapItem.get("+results[1]+");\n";
				m+="								mapItem.put(\n";
				m+="										"+results[1]+",\n";
				m+="										name + \"#\"\n";
				m+="												+ item.optString("+results[0]+"));\n";
			
				m+="							}\n";
			}
		}
		
	
		
		m+="							// =====================code and name\n";

		
		
		for(int i=0;i<results.length;i++)
		{
			if(i==0||i==1)
			{
				continue;
			}
			
			m+="//"+resultsCn[i]+"\n";
			m+="							if (item.has("+results[i]+")) {\n";

			m+="								mapItem.put("+results[i]+",\n";
			m+="										item.optString("+results[i]+"));\n";
			m+="							}\n";
		}
		


		m+="							lists.add(mapItem);\n";
		m+="						}\n";
		m+="					}\n";

		m+="					// HashMap<String, String> mapItem = new HashMap<String,\n";
		m+="					// String>();\n";
		m+="					// mapItem.put(Constants.DLV_ORG_NAME, \"总计\");\n";
		m+="					// // ====================24======================//\n";
		m+="					// mapItem.put(Constants.DLV_MAIL_COUNT_24,\n";
		m+="					// String.valueOf(dlv_mail_count_24));\n";
		m+="					// mapItem.put(Constants.DLV_MAIL_COUNT_24_8,\n";
		m+="					// String.valueOf(dlv_mail_count_24_8));\n";
		m+="					// mapItem.put(Constants.DLV_MAIL_COUNT_24_15,\n";
		m+="					// String.valueOf(dlv_mail_count_24_15));\n";
		m+="					// // ===================48=======================//\n";
		m+="					// mapItem.put(Constants.DLV_MAIL_COUNT_48,\n";
		m+="					// String.valueOf(dlv_mail_count_48));\n";
		m+="					// mapItem.put(Constants.DLV_MAIL_COUNT_48_8,\n";
		m+="					// String.valueOf(dlv_mail_count_48_8));\n";
		m+="					// mapItem.put(Constants.DLV_MAIL_COUNT_48_15,\n";
		m+="					// String.valueOf(dlv_mail_count_48_15));\n";
		m+="					// // ===================72=======================//\n";
		m+="					// mapItem.put(Constants.DLV_MAIL_COUNT_72,\n";
		m+="					// String.valueOf(dlv_mail_count_72));\n";
		m+="					// mapItem.put(Constants.DLV_MAIL_COUNT_72_8,\n";
		m+="					// String.valueOf(dlv_mail_count_72_8));\n";
		m+="					// mapItem.put(Constants.DLV_MAIL_COUNT_72_15,\n";
		m+="					// String.valueOf(dlv_mail_count_72_15));\n";
		m+="					// lists.add(mapItem);\n";

		m+="					return lists;\n";
		m+="				}\n";
		m+="			}\n";

		m+="		} catch (JSONException e) {\n";
		m+="			e.printStackTrace();\n";
		m+="			return new ArrayList<HashMap<String, String>>();\n";
		m+="		}\n";

		m+="		return lists;\n";
		m+="	}\n";
		

		m+="	private void toastError(String hintString) {\n";
		m+="//		refreshBtn.setText(\"点击屏幕刷新数据\");\n";
		m+="//		refreshBtn.setVisibility(View.VISIBLE);\n";
		m+="//		listView.setVisibility(View.GONE);\n";
		m+="//		ll_listlayout.setVisibility(View.GONE);\n";
		m+="		Toast.makeText(this, hintString, Toast.LENGTH_LONG).show();\n";
		m+="	}\n";
		m+="	\n";
		m+="	\n";
		m+="	\n";


	
	

		m+="	ScrollAdapter adapter;\n";

		m+="	private void refreshList() {\n";

		m+="		if (mHScrollViews != null) {\n";
		m+="			iniScroll();\n";
		m+="		}\n";

		m+="		mHScrollViews = new ArrayList<"+formNameEn+"CHScrollView>();\n";

		m+="		"+formNameEn+"CHScrollView headerScroll = ("+formNameEn+"CHScrollView) findViewById(R.id.item_scroll_title);\n";
		m+="		mHScrollViews.add(headerScroll);\n";

		m+="		adapter = new ScrollAdapter(this, lists,\n";
		m+="				R.layout.activity_"+formNameEn.toLowerCase()+"_item, new String[] {\n";
		
		for(int i=0;i<results.length;i++)
		{
			if(i==0)
			{
				continue;
			}else if(i==results.length-1)
			{
				m+=results[i];
			}else
			{
			
		m+=results[i]+",";
			}
		}
		
		m+="}, new int[] {";
		m+=" R.id.item_title,\n";
		
		for(int i=0;i<results.length;i++)
		{
			if(i==0||i==1)
			{
				continue;
			}else if(i==results.length-1)
			{
				m+="R.id.item_data"+(i);
			}else
			{
			
		m+="R.id.item_data"+(i)+",";
			}
		}
		
		m+= "});\n";
		m+="		mListView.setAdapter(adapter);\n";

		m+="	}\n";

		m+="	public void addHViews(final "+formNameEn+"CHScrollView hScrollView) {\n";
		m+="		if (!mHScrollViews.isEmpty()) {\n";
		m+="			int size = mHScrollViews.size();\n";
		m+="			"+formNameEn+"CHScrollView scrollView= mHScrollViews\n";
		m+="					.get(size - 1);\n";
		m+="			final int scrollX = scrollView.getScrollX();\n";

		m+="			if (scrollX != 0) {\n";
		m+="				mListView.post(new Runnable() {\n";
		m+="					@Override\n";
		m+="					public void run() {\n";

		m+="						hScrollView.scrollTo(scrollX, 0);\n";
		m+="					}\n";
		m+="				});\n";
		m+="			}\n";
		m+="		}\n";
		m+="		mHScrollViews.add(hScrollView);\n";
		m+="	}\n";

		m+="	public void onScrollChanged(int l, int t, int oldl, int oldt) {\n";
		m+="		if(mHScrollViews==null)\n";
		m+="			return;\n";
		m+="		for ("+formNameEn+"CHScrollView scrollView : mHScrollViews) {\n";

		m+="			if (mTouchView != scrollView)\n";
		m+="				scrollView.smoothScrollTo(l, t);\n";
		m+="		}\n";
		m+="	}\n";

		m+="	public void iniScroll() {\n";
		m+="		for ("+formNameEn+"CHScrollView scrollView : mHScrollViews) {\n";

		m+="			if (mTouchView != scrollView)\n";
		m+="				scrollView.smoothScrollTo(0, 0);\n";
		m+="		}\n";
		m+="	}\n";

		m+="	class ScrollAdapter extends BaseAdapter {\n";

		m+="		private List<? extends Map<String, ?>> datas;\n";
		m+="		private int res;\n";
		m+="		private String[] from;\n";
		m+="		private int[] to;\n";
		m+="		private Context context;\n";

		m+="		public ScrollAdapter(Context context,\n";
		m+="				List<? extends Map<String, ?>> data, int resource,\n";
		m+="				String[] from, int[] to) {\n";
		m+="			// super(context, data, resource, from, to);\n";
		m+="			this.context = context;\n";
		m+="			this.datas = data;\n";
		m+="			this.res = resource;\n";
		m+="			this.from = from;\n";
		m+="			this.to = to;\n";
		m+="		}\n";

		m+="		public void refreshData(List<? extends Map<String, ?>> data) {\n";
		m+="			datas = data;\n";
		m+="		}\n";

		m+="		@Override\n";
		m+="		public View getView(int position, View convertView, ViewGroup parent) {\n";
		m+="			View v = convertView;\n";
		m+="			if (v == null) {\n";
		m+="				v = LayoutInflater.from(context).inflate(res, null);\n";

		m+="				addHViews(("+formNameEn+"CHScrollView) v\n";
		m+="						.findViewById(R.id.item_scroll));\n";
		m+="				View[] views = new View[to.length];\n";
		m+="				for (int i = 0; i < to.length; i++) {\n";
		m+="					View tv = v.findViewById(to[i]);\n";
		m+="					;\n";
		m+="					v.setOnClickListener(clickListener);\n";
		m+="					views[i] = tv;\n";
		m+="				}\n";
		m+="				v.setTag(views);\n";
		m+="			}\n";
		m+="			View[] holders = (View[]) v.getTag();\n";
		m+="			int len = holders.length;\n";
		m+="			for (int i = 0; i < len; i++) {\n";

		m+="				if (i == 0) {\n";
		m+="					((TextView) holders[i]).setText(this.datas.get(position)\n";
		m+="							.get(from[i]).toString().split(\"#\")[0]);\n";
		m+="					((TextView) holders[i]).setTag(this.datas.get(position)\n";
		m+="							.get(from[i]).toString().split(\"#\")[1]);\n";
		m+="					((TextView) holders[i]).getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线\n";
		m+="				} else {\n";
		m+="					((TextView) holders[i]).setText(this.datas.get(position)\n";
		m+="							.get(from[i]).toString());\n";
		m+="				}\n";

		m+="			}\n";
		m+="			return v;\n";
		m+="		}\n";

		m+="		@Override\n";
		m+="		public int getCount() {\n";
		m+="			// TODO Auto-generated method stub\n";
		m+="			return datas.size();\n";
		m+="		}\n";

		m+="		@Override\n";
		m+="		public Object getItem(int position) {\n";
		m+="			// TODO Auto-generated method stub\n";
		m+="			return null;\n";
		m+="		}\n";

		m+="		@Override\n";
		m+="		public long getItemId(int position) {\n";
		m+="			// TODO Auto-generated method stub\n";
		m+="			return 0;\n";
		m+="		}\n";
		m+="	}\n";


		m+="	protected View.OnClickListener clickListener = new View.OnClickListener() {\n";
		m+="		@Override\n";
		m+="		public void onClick(View v) {\n";
		m+="			isBack = false;\n";
		m+="			View[] holders = (View[]) v.getTag();\n";
		m+="			int len = holders.length;\n";
		m+="			for (int i = 0; i < len; i++) {\n";
		m+="				if (i == 0) {\n";
		m+="					String beforeLevelDepcode;\n";
		m+="					String beforeLevelname;\n";
		m+="					beforeLevelDepcode = ((TextView) holders[i]).getTag()\n";
		m+="							.toString();\n";

		m+="					beforeLevelname = ((TextView) holders[i]).getText()\n";
		m+="							.toString();\n";
		m+="					\n";
		m+="					\n";
		m+="					\n";
		m+="					requestData(beforeLevelDepcode, \"1\");\n";
		m+="					\n";
		m+="				\n";
		m+="					\n";

		m+="				}\n";
		m+="			}\n";
		m+="		}\n";
		m+="	};\n";

		m+="	@Override\n";
		m+="	public void connectionCallBack() {\n";
		m+="		// TODO Auto-generated method stub\n";
		m+="		if (mBaseService != null) {\n";

		m+="			requestData(userPara.split(\"#\")[0], \"0\");\n";
		m+="		}\n";
		m+="	}\n";

		m+="	@Override\n";
		m+="	public Handler getHandler() {\n";
		m+="		// TODO Auto-generated method stub\n";
		m+="		return null;\n";
		m+="	}\n";

		m+="	@Override\n";
		m+="	public void setBaseService(BaseServiceInterface baseService) {\n";
		m+="		// TODO Auto-generated method stub\n";
		m+="		mBaseService = baseService;\n";
		m+="	}\n";

		
		
		
		m+="//点击返回按钮 到报表上层\n";
		m+="	@Override\n";
		m+="	public boolean onKeyDown(int keyCode, KeyEvent event) {\n";
		m+="		if ((keyCode == KeyEvent.KEYCODE_BACK)) {\n";

		m+="			if(pages!=null && pages.size()==0)\n";
		m+="			{\n";
		m+="				"+formNameEn+"Activity.this.finish();\n";
		m+="			}else\n";
		m+="			{\n";
		m+="				int count=pages.size();\n";
		m+="				pages.remove(count-1);\n";
		m+="				 count=pages.size();\n";
		m+="				 if(count==0)\n";
		m+="				 {\n";
		m+="					 "+formNameEn+"Activity.this.finish();\n";
		m+="				 }else\n";
		m+="				 {\n";
		m+="				lists=(ArrayList<HashMap<String, String>>) pages.get(count-1);\n";
		m+="				refreshList();\n";
		m+="				 }\n";
		m+="			}\n";
		m+="			\n";
		m+="			\n";
		m+="			return false;\n";

		m+="		} else {\n";
		m+="			return super.onKeyDown(keyCode, event);\n";
		m+="		}\n";

		m+="	}\n";

		m+="	WheelMain wheelMain;\n";
		m+="	DateFormat dateFormat = new SimpleDateFormat(\"yyyy-MM-dd\");\n";

		m+="	private void showToast(String text) {\n";
		m+="		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();\n";
		m+="	}\n";

		m+="	/**\n";
		m+="	 * 开始&结束 日期点击时间\n";
		m+="	 * \n";
		m+="	 */\n";
		m+="	private class OnDateClickListener implements OnClickListener {\n";

		m+="		TextView txttime;\n";

		m+="		@Override\n";
		m+="		public void onClick(final View v) {\n";

		m+="			if (v.getId() == R.id.ll_start_date_layout) {\n";
		m+="				txttime = tv_start_date;\n";
		m+="			} else {\n";
		m+="				txttime = tv_end_date;\n";
		m+="			}\n";

		m+="			LayoutInflater inflater = LayoutInflater\n";
		m+="					.from("+formNameEn+"Activity.this);\n";
		m+="			final View timepickerview = inflater.inflate(\n";
		m+="					R.layout.ipo_dialog_date_picker, null);\n";
		m+="			ScreenInfo screenInfo = new ScreenInfo(\n";
		m+="					"+formNameEn+"Activity.this);\n";
		m+="			wheelMain = new WheelMain(timepickerview);// 设置日期控件\n";
		m+="			wheelMain.screenheight = screenInfo.getHeight();\n";
		m+="			String time = txttime.getText().toString();\n";
		m+="			try {\n";
		m+="				time = dateFormat.format(sdfYMD.parse(time));// 日期格式转换\n";
		m+="																// yyyy年MM月dd日\n";
		m+="																// from\n";
		m+="																// yyyy-MM-dd\n";
		m+="			} catch (ParseException e1) {\n";
		m+="				e1.printStackTrace();\n";
		m+="			}\n";
		m+="			Calendar calendar = Calendar.getInstance();\n";
		m+="			if (JudgeDate.isDate(time, \"yyyy-MM-dd\")) {\n";
		m+="				try {\n";
		m+="					calendar.setTime(dateFormat.parse(time));\n";
		m+="				} catch (ParseException e) {\n";
		m+="					e.printStackTrace();\n";
		m+="				}\n";
		m+="			}\n";
		m+="			int year = calendar.get(Calendar.YEAR);\n";
		m+="			int month = calendar.get(Calendar.MONTH);\n";
		m+="			int day = calendar.get(Calendar.DAY_OF_MONTH);\n";
		m+="			wheelMain.initDateTimePicker(year, month, day);\n";
		m+="			new AlertDialog.Builder("+formNameEn+"Activity.this)\n";
		m+="					.setTitle(\"选择时间\")\n";
		m+="					.setView(timepickerview)\n";
		m+="					.setPositiveButton(\"确定\",\n";
		m+="							new DialogInterface.OnClickListener() {\n";
		m+="								@Override\n";
		m+="								public void onClick(DialogInterface dialog,\n";
		m+="										int which) {\n";
		m+="									try {\n";
		m+="										if (v.getId() == R.id.ll_start_date_layout) {\n";

		m+="											Date endDate = sdfYMD\n";
		m+="													.parse(tv_end_date\n";
		m+="															.getText()\n";
		m+="															.toString());\n";
		m+="											int compareTo = dateFormat.parse(\n";
		m+="													wheelMain.getTime())\n";
		m+="													.compareTo(endDate);\n";

		m+="										 if (compareTo < 0 ||compareTo==0) {// 开始时间小于结束时间\n";
		m+="												txttime.setText(sdfYMD.format(dateFormat\n";
		m+="														.parse(wheelMain\n";
		m+="																.getTime())));// 开始日期\n";
		m+="											} else {// 开始时间大于结束时间\n";
		m+="												showToast(\"开始时间不能大于结束时间.\");\n";
		m+="											}\n";
		m+="										} else {\n";
		m+="											Date startDate = sdfYMD\n";
		m+="													.parse(tv_start_date\n";
		m+="															.getText()\n";
		m+="															.toString());\n";
		m+="											int compareTo = dateFormat.parse(\n";
		m+="													wheelMain.getTime())\n";
		m+="													.compareTo(startDate);\n";
		m+="                                             if (compareTo < 0) {// 开始时间小于结束时间\n";
		m+="												showToast(\"结束时间不能小于开始时间.\");\n";
		m+="											} else {// 开始时间大于结束时间\n";
		m+="												txttime.setText(sdfYMD.format(dateFormat\n";
		m+="														.parse(wheelMain\n";
		m+="																.getTime())));// 开始日期\n";
		m+="											}\n";
		m+="										}\n";

		m+="									} catch (ParseException e) {\n";
		m+="										e.printStackTrace();\n";
		m+="									}\n";
		m+="								}\n";
		m+="							})\n";
		m+="					.setNegativeButton(\"取消\",\n";
		m+="							new DialogInterface.OnClickListener() {\n";
		m+="								@Override\n";
		m+="								public void onClick(DialogInterface dialog,\n";
		m+="										int which) {\n";
		m+="								}\n";
		m+="							}).show();\n";
		m+="		}\n";

		m+="	}\n";

		m+="}\n";
		
		
		String n="AndroidManifest.xml加入新Activity声明\n\n";
		 n+="<activity\n";
         n+="android:name=\""+javapackage+"."+formNameEn+"Activity\"\n";
         n+="android:label=\"@string/contacts_text\"\n";
         n+="android:screenOrientation=\"portrait\"\n";
         n+="android:theme=\"@android:style/Theme.Black.NoTitleBar\" >\n";
        n+=" </activity>\n";
        
        System.out.println(n);
		
		String filename = FileUtil.makeFile(outPath+"/"+formNameEn+"Activity.java", m);

	}
	
	
	
	
	
	
	
	
	
	
	public void chscrollview()
	{
		
		String m="";
		
        m+="package "+javapackage+";\n";
		m+="import android.content.Context;\n";
		m+="import android.util.AttributeSet;\n";
		m+="import android.view.MotionEvent;\n";
		m+="import android.widget.HorizontalScrollView;\n";

		m+="public class "+formNameEn+"CHScrollView extends HorizontalScrollView {\n";
		m+="	\n";
		m+="	"+formNameEn+"Activity activity;\n";
		m+="	\n";
		m+="	public "+formNameEn+"CHScrollView(Context context, AttributeSet attrs, int defStyle) {\n";
		m+="		super(context, attrs, defStyle);\n";
		m+="		activity = ("+formNameEn+"Activity) context;\n";
		m+="	}\n";

		m+="	\n";
		m+="	public "+formNameEn+"CHScrollView(Context context, AttributeSet attrs) {\n";
		m+="		super(context, attrs);\n";
		m+="		if(context!=null && context instanceof "+formNameEn+"Activity)\n";
		m+="		activity = ("+formNameEn+"Activity) context;\n";
		m+="	}\n";

		m+="	public "+formNameEn+"CHScrollView(Context context) {\n";
		m+="		super(context);\n";
		m+="		activity = ("+formNameEn+"Activity) context;\n";
		m+="	}\n";
		m+="	\n";
		m+="	@Override\n";
		m+="	public boolean onTouchEvent(MotionEvent ev) {\n";
		m+="		activity.mTouchView = this;\n";
		m+="		return super.onTouchEvent(ev);\n";
		m+="	}\n";
		m+="	\n";
		m+="	@Override\n";
		m+="	protected void onScrollChanged(int l, int t, int oldl, int oldt) {\n";
		m+="	\n";
		m+="		if(activity.mTouchView == this) {\n";
		m+="			activity.onScrollChanged(l, t, oldl, oldt);\n";
		m+="		}else{\n";
		m+="			super.onScrollChanged(l, t, oldl, oldt);\n";
		m+="		}\n";
		m+="	}\n";
		m+="}\n";
		
		String filename = FileUtil.makeFile(outPath+"/"+formNameEn+"CHScrollView.java", m);

	}
	
	
	
	public void mainXml()
	{
		String m="";
		
		m+="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
		m+="<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n";
		m+="    android:layout_width=\"fill_parent\"\n";
		m+="    android:layout_height=\"fill_parent\"\n";
		m+="    android:background=\"#f8f8f8\"\n";
		m+="    android:orientation=\"vertical\" >\n";

		m+="    <!-- title -->\n";

		m+="    <include\n";
		m+="        android:layout_width=\"wrap_content\"\n";
		m+="        android:layout_height=\"wrap_content\"\n";
		m+="        layout=\"@layout/public_title_bar\" />\n";

		m+="    <!-- 日期 -->\n";

		m+="    <LinearLayout\n";
		m+="        android:layout_width=\"fill_parent\"\n";
		m+="        android:layout_height=\"wrap_content\" >\n";

		m+="        <RelativeLayout\n";
		m+="            android:layout_width=\"fill_parent\"\n";
		m+="            android:layout_height=\"wrap_content\"\n";
		m+="            android:background=\"@color/col_text4\"\n";
		m+="            android:padding=\"8dip\" >\n";

		m+="            <LinearLayout\n";
		m+="                android:id=\"@+id/ll_start_date_layout\"\n";
		m+="                android:layout_width=\"wrap_content\"\n";
		m+="                android:layout_height=\"wrap_content\"\n";
		m+="                android:gravity=\"center\"\n";
		m+="                android:orientation=\"horizontal\" >\n";

		m+="                <ImageView\n";
		m+="                    android:layout_width=\"wrap_content\"\n";
		m+="                    android:layout_height=\"wrap_content\"\n";
		m+="                    android:layout_marginRight=\"5dip\"\n";
		m+="                    android:src=\"@drawable/icon_text\" />\n";

		m+="                <TextView\n";
		m+="                    android:id=\"@+id/tv_start_date\"\n";
		m+="                    android:layout_width=\"wrap_content\"\n";
		m+="                    android:layout_height=\"wrap_content\"\n";
		m+="                    android:text=\"2014年10月22日 \"\n";
		m+="                    android:textColor=\"@color/col_text1\"\n";
		m+="                    android:textSize=\"12dip\" />\n";

		m+="                <ImageView\n";
		m+="                    android:layout_width=\"wrap_content\"\n";
		m+="                    android:layout_height=\"wrap_content\"\n";
		m+="                    android:src=\"@drawable/abs__ic_go_search_api_holo_light\" />\n";
		m+="            </LinearLayout>\n";

		m+="            <TextView\n";
		m+="                android:layout_width=\"wrap_content\"\n";
		m+="                android:layout_height=\"wrap_content\"\n";
		m+="                android:layout_centerInParent=\"true\"\n";
		m+="                android:text=\"至 \"\n";
		m+="                android:textColor=\"@color/col_text3\"\n";
		m+="                android:textSize=\"12dip\" />\n";

		m+="            <LinearLayout\n";
		m+="                android:id=\"@+id/ll_end_date_layout\"\n";
		m+="                android:layout_width=\"wrap_content\"\n";
		m+="                android:layout_height=\"wrap_content\"\n";
		m+="                android:layout_alignParentRight=\"true\"\n";
		m+="                android:gravity=\"center\"\n";
		m+="                android:orientation=\"horizontal\" >\n";

		m+="                <ImageView\n";
		m+="                    android:layout_width=\"wrap_content\"\n";
		m+="                    android:layout_height=\"wrap_content\"\n";
		m+="                    android:layout_marginRight=\"5dip\"\n";
		m+="                    android:src=\"@drawable/icon_text\" />\n";

		m+="                <TextView\n";
		m+="                    android:id=\"@+id/tv_end_date\"\n";
		m+="                    android:layout_width=\"wrap_content\"\n";
		m+="                    android:layout_height=\"wrap_content\"\n";
		m+="                    android:text=\"2014年10月22日 \"\n";
		m+="                    android:textColor=\"@color/col_text1\"\n";
		m+="                    android:textSize=\"12dip\" />\n";

		m+="                <ImageView\n";
		m+="                    android:layout_width=\"wrap_content\"\n";
		m+="                    android:layout_height=\"wrap_content\"\n";
		m+="                    android:src=\"@drawable/abs__ic_go_search_api_holo_light\" />\n";
		m+="            </LinearLayout>\n";
		m+="        </RelativeLayout>\n";
		m+="    </LinearLayout>\n";

		m+="    <!-- 种类 -->\n";

		if(!formSearchInputPart1.equals(""))
		{
		m+="    <LinearLayout\n";
		m+="        android:layout_width=\"fill_parent\"\n";
		m+="        android:layout_height=\"wrap_content\"\n";
		m+="        android:gravity=\"center\"\n";
		m+="        android:background=\"#FFFFFF\"\n";
		m+="        android:padding=\"3dip\" >\n";

		m+="        <TextView\n";
		m+="            android:id=\"@+id/packagetrend_endtime\"\n";
		m+="            android:layout_width=\"wrap_content\"\n";
		m+="            android:layout_height=\"wrap_content\"\n";
		m+="            android:text=\"种类\"\n";
		m+="             android:layout_marginRight=\"7dp\"\n";
		m+="            android:layout_marginLeft=\"7dp\"\n";
		m+="            android:textColor=\"@color/col_text1\"\n";
		m+="            android:textSize=\"@dimen/normal_space16\" />\n";

		m+="        <com.chinapost.view.FlowRadioGroup\n";
		m+="            android:id=\"@+id/type_radiogroup\"\n";
		m+="            android:layout_width=\"fill_parent\"\n";
		m+="            android:layout_height=\"wrap_content\"\n";
		m+="            android:layout_alignParentBottom=\"true\"\n";
		m+="            android:layout_gravity=\"bottom\"\n";
		m+="            android:gravity=\"center_vertical\"\n";
		m+="            android:orientation=\"horizontal\" >\n";

		m+="            <RadioButton\n";
		m+="                android:id=\"@+id/type_radio_xiaobao\"\n";
		m+="                style=\"@style/EnregisterCheckboxTheme\"\n";
		m+="                android:layout_height=\"match_parent\"\n";
		m+="                android:checked=\"true\"\n";
		m+="                android:layout_marginLeft=\"3dp\"\n";
		m+="                    android:background=\"@android:color/transparent\"\n";
		m+="                 android:paddingLeft=\"2dp\"\n";
		m+="                android:text=\"国内小包\"\n";
		m+="                android:textColor=\"@color/black\"\n";
		m+="                android:textSize=\"14dip\" />\n";

		m+="            <RadioButton\n";
		m+="                android:id=\"@+id/type_radio_guahao\"\n";
		m+="                style=\"@style/EnregisterCheckboxTheme\"\n";
		m+="                android:layout_height=\"match_parent\"\n";
		m+="                android:text=\"约投挂号\"\n";
		m+="                  android:layout_marginLeft=\"3dp\"\n";
		m+="                    android:background=\"@android:color/transparent\"\n";
		m+="                 android:paddingLeft=\"2dp\"\n";
		m+="                android:textColor=\"@color/black\"\n";
		m+="                android:textSize=\"14dip\" />\n";

		m+="            <RadioButton\n";
		m+="                android:id=\"@+id/type_radio_shudi\"\n";
		m+="                style=\"@style/EnregisterCheckboxTheme\"\n";
		m+="                android:layout_height=\"match_parent\"\n";
		m+="                android:text=\"代投速递\"\n";
		m+="                  android:layout_marginLeft=\"3dp\"\n";
		m+="                    android:background=\"@android:color/transparent\"\n";
		m+="                 android:paddingLeft=\"2dp\"\n";
		m+="                android:textColor=\"@color/black\"\n";
		m+="                android:textSize=\"14dip\" />\n";
		m+="        </com.chinapost.view.FlowRadioGroup>\n";
		m+="    </LinearLayout>\n";
		}
		
		
		m+="   \n";

		m+="    <RelativeLayout\n";
		m+="        android:id=\"@+id/ll_listlayout\"\n";
		m+="        android:layout_width=\"fill_parent\"\n";
		m+="        android:layout_height=\"wrap_content\"\n";
		m+="        android:orientation=\"vertical\" >\n";

		m+="         <!-- 报表头 -->\n";
		m+="        <LinearLayout\n";
		m+="            android:layout_width=\"fill_parent\"\n";
		m+="            android:layout_height=\"wrap_content\"\n";
		m+="            android:id=\"@+id/formhead\"\n";
		m+="            android:background=\"#C5D7CF\"\n";
		m+="  android:gravity=\"center\"\n";
		m+="            android:orientation=\"horizontal\"\n";
		m+="            android:padding=\"5dp\" >\n";

		for(int i=0;i<resultsCn.length;i++)
		{
			 if(i==1)
			{
		m+="            <TextView\n";
		m+="                android:layout_width=\"fill_parent\"\n";
		m+="                android:layout_height=\"wrap_content\"\n";
		m+="                android:layout_weight=\"2\"\n";
		m+="                android:gravity=\"center\"\n";
		m+="                android:text=\""+resultsCn[i]+"\"\n";
		m+="                 android:textSize=\"14dp\"\n";
		m+="                android:textColor=\"#90AD9F\" />\n";
		
		 m+="<View android:layout_width=\"1dp\" android:layout_height=\"fill_parent\" android:background=\"@drawable/links_icon\" />\n";
			
			}
	   }

		m+="            <"+javapackage+"."+formNameEn+"CHScrollView\n";
		m+="                android:id=\"@+id/item_scroll_title\"\n";
		m+="                android:layout_width=\"fill_parent\"\n";
		m+="                android:layout_height=\"wrap_content\"\n";
		m+="                android:layout_weight=\"1\"\n";
		m+="                android:scrollbars=\"none\" >\n";

		m+="                <LinearLayout\n";
		m+="                    android:layout_width=\"fill_parent\"\n";
		m+="                    android:layout_height=\"wrap_content\"\n";
		m+="  android:gravity=\"center\"\n";
		m+="                    android:orientation=\"horizontal\" >\n";

		for(int i=0;i<resultsCn.length;i++)
		{
			 if(i==0||i==1)
			{
				 continue;
			}
		m+="                    <TextView\n";
		m+="                        android:layout_width=\"100dip\"\n";
		m+="                        android:layout_height=\"wrap_content\"\n";
		m+="                        android:gravity=\"center\"\n";
		m+="                        android:text=\""+resultsCn[i]+"\"\n";
		m+="                        android:textSize=\"14dp\"\n";
		m+="                        android:textColor=\"#90AD9F\" />\n";
		
		if(i==resultsCn.length-1)
		{
			
		}else
		{
			 m+="<View android:layout_width=\"1dp\" android:layout_height=\"fill_parent\" android:background=\"@drawable/links_icon\" />\n";
				
		}
			
		}

	
		
		m+="                </LinearLayout>\n";
		m+="            </"+javapackage+"."+formNameEn+"CHScrollView>\n";
		m+="        </LinearLayout>\n";
		m+="          <!-- 报表头end -->\n";

		m+="        <ListView\n";
		m+="            android:layout_below=\"@id/formhead\"\n";
		m+="         \n";
		m+="            android:id=\"@+id/scroll_list\"\n";
		m+="            android:layout_width=\"fill_parent\"\n";
		m+="            android:layout_height=\"fill_parent\"\n";
		m+="            android:cacheColorHint=\"#00000000\"\n";
		m+="            android:divider=\"@drawable/item_line\"\n";
		m+="            android:dividerHeight=\"1dip\"\n";
		m+="            android:layoutAnimation=\"@anim/layout_anim_ctrl\"\n";
		m+="            android:persistentDrawingCache=\"animation|scrolling\"\n";
		m+="            android:scrollbars=\"none\" >\n";
		m+="        </ListView>\n";

		m+="    </RelativeLayout>\n";
		m+="    <!-- 报表 end-->\n";
		m+="    \n";

		m+="    <Button\n";
		m+="        android:id=\"@+id/refresh_btn\"\n";
		m+="        android:layout_width=\"fill_parent\"\n";
		m+="        android:layout_height=\"fill_parent\"\n";
		m+="        android:layout_gravity=\"center\"\n";
		m+="        android:visibility=\"gone\"\n";
		m+="        android:background=\"@drawable/item_blue_selector\"\n";
		m+="        android:text=\"@string/refresh_btn\" />\n";

		m+="</LinearLayout>\n";
		String filename = FileUtil.makeFile(outPath+"/activity_"+formNameEn.toLowerCase()+".xml", m);
		
		
		
	}
	
	
	
	public void xmlItem()
	{
		String m="";
		m+="<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
		m+="<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n";
		m+="    android:layout_width=\"fill_parent\"\n";
		m+="    android:layout_height=\"fill_parent\"\n";
		m+="    android:minHeight=\"50dip\"\n";
		m+="    android:orientation=\"horizontal\" >\n";


		m+="    <TextView\n";
		m+="        android:id=\"@+id/item_title\"\n";
		m+="        android:layout_width=\"fill_parent\"\n";
		m+="        android:layout_height=\"fill_parent\"\n";
		m+="        android:layout_weight=\"2\"\n";
		m+="        android:gravity=\"center\"\n";
		m+="         android:textColor=\"#2B8F69\"\n";
		m+="        android:textSize=\"14dp\"\n";
		m+="        android:text=\"行头\" />\n";
		m+="    \n";
		m+="   \n";
		
		 m+="<View android:layout_width=\"1dp\" android:layout_height=\"fill_parent\" android:background=\"@drawable/links_icon\" />\n";
			

		m+="    <"+javapackage+"."+formNameEn+"CHScrollView\n";
		m+="        android:id=\"@+id/item_scroll\"\n";
		m+="        android:layout_width=\"fill_parent\"\n";
		m+="        android:layout_height=\"fill_parent\"\n";
		m+="        android:scrollbars=\"none\"\n";
		m+="        android:layout_weight=\"1\" >\n";

		m+="        <LinearLayout\n";
		m+="            android:layout_width=\"fill_parent\"\n";
		m+="            android:layout_height=\"fill_parent\"\n";
		m+="            android:orientation=\"horizontal\" >\n";
		
		
		for(int i=0;i<resultsCn.length;i++)
		{
			 if(i==0||i==1)
			{
				 continue;
			}

		m+="            <TextView\n";
		m+="                android:id=\"@+id/item_data"+i+"\"\n";
		m+="                android:layout_width=\"100dip\"\n";
		m+="                android:layout_height=\"fill_parent\"\n";
		m+="                android:textColor=\"#101010\"\n";
		m+="                  android:textSize=\"14dp\"\n";
		m+="                android:gravity=\"center\" />\n";
		
		if(i==resultsCn.length-1)
		{
			
		}else
		{

			 m+="<View android:layout_width=\"1dp\" android:layout_height=\"fill_parent\" android:background=\"@drawable/links_icon\" />\n";
		}
		
		}

		
		
		m+="       \n";
		m+="        </LinearLayout>\n";
		m+="    </"+javapackage+"."+formNameEn+"CHScrollView>\n";

		m+="</LinearLayout>\n";
		
		String filename = FileUtil.makeFile(outPath+"/activity_"+formNameEn.toLowerCase()+"_item.xml", m);

	}
}
