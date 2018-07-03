package com.compoment.ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class CreateActivityView {

	String xmlfile = "quota_detail.xml";// 修改就行
	static String classDir = null;
	static String xmlFilePath = null;
	static String xmlfilename = null;
	static String className = null;
	String[] controls = { "Button", "TextView", "EditText", "ImageView",
			"ExpandableListView", "ListView", "GridView", "Spinner",
			"AutoCompleteTextView", "CheckBox","android.support.v4.widget.DrawerLayout" };
	Element root = null;
	String m = "";

	public static void main(String[] args) throws SAXException, IOException {
		CreateActivityView createView = new CreateActivityView("");
		createView.create();
	}

	public CreateActivityView(String filename) {

		if (filename.equals("")) {
			classDir = this.getClass().getResource("/").getPath();
			int pos = xmlfile.indexOf(".");
			xmlfilename = xmlfile.substring(0, pos);
			xmlFilePath = classDir + "com/compoment/ui/xml/" + xmlfilename;
		} else {

			xmlfilename = filename;
			xmlFilePath = KeyValue.readCache("projectPath") + "/src/android/xml/" + filename;
		}
		className = firstCharToUpperAndJavaName(xmlfilename);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		try {
			builder = dbf.newDocumentBuilder();
			Document doc = builder.parse(xmlFilePath + ".xml");
			root = doc.getDocumentElement();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void create() {
		m+="import android.app.Activity;\n";
		m+="import android.content.Context;\n";
		m+="import android.content.Intent;\n";
		m+="import android.os.AsyncTask;\n";
		m+="import android.os.Bundle;\n";
		m+="import android.os.Handler;\n";
		m+="import android.os.Message;\n";
		m+="import android.support.v4.app.Fragment;\n";
		m+="import android.view.LayoutInflater;\n";
		m+="import android.view.View;\n";
		m+="import android.view.ViewGroup;\n";
		m+="import android.view.inputmethod.InputMethodManager;\n";
		m+="import android.widget.AbsListView;\n";
		m+="import android.widget.AbsListView.OnScrollListener;\n";
		m+="import android.widget.Adapter;\n";
		m+="import android.widget.AdapterView;\n";
		m+="import android.widget.Button;\n";
		m+="import android.widget.ListView;\n";
		m+="import android.widget.TextView;\n";
		m+="import java.util.ArrayList;\n";
		m+="import java.util.List;\n";

		m+="import org.json.JSONException;\n";
		m+="import org.json.JSONObject;\n";
		
		/*
		 * private static final String[] URLS = {
		 * "http://lh5.ggpht.com/_mrb7w4gF8Ds/TCpetKSqM1I/AAAAAAAAD2c/Qef6Gsqf12Y/s144-c/_DSC4374%20copy.jpg"
		 * ,
		 * "http://lh5.ggpht.com/_Z6tbBnE-swM/TB0CryLkiLI/AAAAAAAAVSo/n6B78hsDUz4/s144-c/_DSC3454.jpg"
		 * ,
		 * "http://lh3.ggpht.com/_GEnSvSHk4iE/TDSfmyCfn0I/AAAAAAAAF8Y/cqmhEoxbwys/s144-c/_MG_3675.jpg"
		 * ,
		 * "http://lh6.ggpht.com/_Nsxc889y6hY/TBp7jfx-cgI/AAAAAAAAHAg/Rr7jX44r2Gc/s144-c/IMGP9775a.jpg"
		 * ,
		 * "http://lh3.ggpht.com/_lLj6go_T1CQ/TCD8PW09KBI/AAAAAAAAQdc/AqmOJ7eg5ig/s144-c/Juvenile%20Gannet%20despute.jpg"
		 * ,
		 * "http://lh6.ggpht.com/_ZN5zQnkI67I/TCFFZaJHDnI/AAAAAAAABVk/YoUbDQHJRdo/s144-c/P9250508.JPG"
		 * ,
		 * "http://lh4.ggpht.com/_XjNwVI0kmW8/TCOwNtzGheI/AAAAAAAAC84/SxFJhG7Scgo/s144-c/0014.jpg"
		 * ,
		 * "http://lh6.ggpht.com/_lnDTHoDrJ_Y/TBvKsJ9qHtI/AAAAAAAAG6g/Zll2zGvrm9c/s144-c/000007.JPG"
		 * ,
		 * "http://lh6.ggpht.com/_qvCl2efjxy0/TCIVI-TkuGI/AAAAAAAAOUY/vbk9MURsv48/s144-c/DSC_0844.JPG"
		 * ,
		 * "http://lh4.ggpht.com/_TPlturzdSE8/TBv4ugH60PI/AAAAAAAAMsI/p2pqG85Ghhs/s144-c/_MG_3963.jpg"
		 * ,
		 * "http://lh4.ggpht.com/_4f1e_yo-zMQ/TCe5h9yN-TI/AAAAAAAAXqs/8X2fIjtKjmw/s144-c/IMG_1786.JPG"
		 * ,
		 * "http://lh6.ggpht.com/_iFt5VZDjxkY/TB9rQyWnJ4I/AAAAAAAADpU/lP2iStizJz0/s144-c/DSCF1014.JPG"
		 * ,
		 * "http://lh5.ggpht.com/_hepKlJWopDg/TB-_WXikaYI/AAAAAAAAElI/715k4NvBM4w/s144-c/IMG_0075.JPG"
		 * ,
		 * "http://lh6.ggpht.com/_OfRSx6nn68g/TCzsQic_z3I/AAAAAAABOOI/5G4Kwzb2qhk/s144-c/EASTER%20ISLAND_Hanga%20Roa_31.5.08_46.JPG"
		 * ,
		 * "http://lh6.ggpht.com/_ZGv_0FWPbTE/TB-_GLhqYBI/AAAAAAABVxs/cVEvQzt0ke4/s144-c/IMG_1288_hf.jpg"
		 * ,
		 * "http://lh6.ggpht.com/_a29lGRJwo0E/TBqOK_tUKmI/AAAAAAAAVbw/UloKpjsKP3c/s144-c/31012332.jpg"
		 * ,
		 * "http://lh3.ggpht.com/_55Lla4_ARA4/TB6xbyxxJ9I/AAAAAAABTWo/GKe24SwECns/s144-c/Bluebird%20049.JPG"
		 * ,
		 * "http://lh3.ggpht.com/_iVnqmIBYi4Y/TCaOH6rRl1I/AAAAAAAA1qg/qeMerYQ6DYo/s144-c/Kwiat_100626_0016.jpg"
		 * ,
		 * "http://lh6.ggpht.com/_QFsB_q7HFlo/TCItd_2oBkI/AAAAAAAAFsk/4lgJWweJ5N8/s144-c/3705226938_d6d66d6068_o.jpg"
		 * ,
		 * "http://lh5.ggpht.com/_JTI0xxNrKFA/TBsKQ9uOGNI/AAAAAAAChQg/z8Exh32VVTA/s144-c/CRW_0015-composite.jpg"
		 * ,
		 * "http://lh6.ggpht.com/_loGyjar4MMI/S-InVNkTR_I/AAAAAAAADJY/Fb5ifFNGD70/s144-c/Moving%20Rock.jpg"
		 * ,
		 * "http://lh4.ggpht.com/_L7i4Tra_XRY/TBtxjScXLqI/AAAAAAAAE5o/ue15HuP8eWw/s144-c/opera%20house%20II.jpg"
		 * ,
		 * "http://lh3.ggpht.com/_rfAz5DWHZYs/S9cstBTv1iI/AAAAAAAAeYA/EyZPUeLMQ98/s144-c/DSC_6425.jpg"
		 * ,
		 * "http://lh6.ggpht.com/_iGI-XCxGLew/S-iYQWBEG-I/AAAAAAAACB8/JuFti4elptE/s144-c/norvig-polar-bear.jpg"
		 * ,
		 * "http://lh3.ggpht.com/_M3slUPpIgmk/SlbnavqG1cI/AAAAAAAACvo/z6-CnXGma7E/s144-c/mf_003.jpg"
		 * ,
		 * "http://lh4.ggpht.com/_loGyjar4MMI/S-InQvd_3hI/AAAAAAAADIw/dHvCFWfyHxQ/s144-c/Rainbokeh.jpg"
		 * ,
		 * "http://lh4.ggpht.com/_yy6KdedPYp4/SB5rpK3Zv0I/AAAAAAAAOM8/mokl_yo2c9E/s144-c/Point%20Reyes%20road%20.jpg"
		 * ,
		 * "http://lh5.ggpht.com/_6_dLVKawGJA/SMwq86HlAqI/AAAAAAAAG5U/q1gDNkmE5hI/s144-c/mobius-glow.jpg"
		 * ,
		 * "http://lh3.ggpht.com/_QFsB_q7HFlo/TCItc19Jw3I/AAAAAAAAFs4/nPfiz5VGENk/s144-c/4551649039_852be0a952_o.jpg"
		 * ,
		 * "http://lh6.ggpht.com/_TQY-Nm7P7Jc/TBpjA0ks2MI/AAAAAAAABcI/J6ViH98_poM/s144-c/IMG_6517.jpg"
		 * ,
		 * "http://lh3.ggpht.com/_rfAz5DWHZYs/S9cLAeKuueI/AAAAAAAAeYU/E19G8DOlJRo/s144-c/DSC_4397_8_9_tonemapped2.jpg"
		 * ,
		 * "http://lh4.ggpht.com/_TQY-Nm7P7Jc/TBpi6rKfFII/AAAAAAAABbg/79FOc0Dbq0c/s144-c/david_lee_sakura.jpg"
		 * ,
		 * "http://lh3.ggpht.com/_TQY-Nm7P7Jc/TBpi8EJ4eDI/AAAAAAAABb0/AZ8Cw1GCaIs/s144-c/Hokkaido%20Swans.jpg"
		 * ,
		 * "http://lh3.ggpht.com/_1aZMSFkxSJI/TCIjB6od89I/AAAAAAAACHM/CLWrkH0ziII/s144-c/079.jpg"
		 * ,
		 * "http://lh5.ggpht.com/_loGyjar4MMI/S-InWuHkR9I/AAAAAAAADJE/wD-XdmF7yUQ/s144-c/Colorado%20River%20Sunset.jpg"
		 * ,
		 * "http://lh3.ggpht.com/_0YSlK3HfZDQ/TCExCG1Zc3I/AAAAAAAAX1w/9oCH47V6uIQ/s144-c/3138923889_a7fa89cf94_o.jpg"
		 * ,
		 * "http://lh6.ggpht.com/_K29ox9DWiaM/TAXe4Fi0xTI/AAAAAAAAVIY/zZA2Qqt2HG0/s144-c/IMG_7100.JPG"
		 * ,
		 * "http://lh6.ggpht.com/_0YSlK3HfZDQ/TCEx16nJqpI/AAAAAAAAX1c/R5Vkzb8l7yo/s144-c/4235400281_34d87a1e0a_o.jpg"
		 * ,
		 * "http://lh4.ggpht.com/_8zSk3OGcpP4/TBsOVXXnkTI/AAAAAAAAAEo/0AwEmuqvboo/s144-c/yosemite_forrest.jpg"
		 * ,
		 * "http://lh4.ggpht.com/_6_dLVKawGJA/SLZToqXXVrI/AAAAAAAAG5k/7fPSz_ldN9w/s144-c/coastal-1.jpg"
		 * ,
		 * "http://lh4.ggpht.com/_WW8gsdKXVXI/TBpVr9i6BxI/AAAAAAABhNg/KC8aAJ0wVyk/s144-c/IMG_6233_1_2-2.jpg"
		 * ,
		 * "http://lh3.ggpht.com/_loGyjar4MMI/S-InS0tJJSI/AAAAAAAADHU/E8GQJ_qII58/s144-c/Windmills.jpg"
		 * ,
		 * "http://lh4.ggpht.com/_loGyjar4MMI/S-InbXaME3I/AAAAAAAADHo/4gNYkbxemFM/s144-c/Frantic.jpg"
		 * ,
		 * "http://lh5.ggpht.com/_loGyjar4MMI/S-InKAviXzI/AAAAAAAADHA/NkyP5Gge8eQ/s144-c/Rice%20Fields.jpg"
		 * ,
		 * "http://lh3.ggpht.com/_loGyjar4MMI/S-InZA8YsZI/AAAAAAAADH8/csssVxalPcc/s144-c/Seahorse.jpg"
		 * ,
		 * "http://lh3.ggpht.com/_syQa1hJRWGY/TBwkCHcq6aI/AAAAAAABBEg/R5KU1WWq59E/s144-c/Antelope.JPG"
		 * ,
		 * "http://lh5.ggpht.com/_MoEPoevCLZc/S9fHzNgdKDI/AAAAAAAADwE/UAno6j5StAs/s144-c/c84_7083.jpg"
		 * ,
		 * "http://lh4.ggpht.com/_DJGvVWd7IEc/TBpRsGjdAyI/AAAAAAAAFNw/rdvyRDgUD8A/s144-c/Free.jpg"
		 * ,
		 * "http://lh6.ggpht.com/_iO97DXC99NY/TBwq3_kmp9I/AAAAAAABcz0/apq1ffo_MZo/s144-c/IMG_0682_cp.jpg"
		 * ,
		 * "http://lh4.ggpht.com/_7V85eCJY_fg/TBpXudG4_PI/AAAAAAAAPEE/8cHJ7G84TkM/s144-c/20100530_120257_0273-Edit-2.jpg"
		 * };
		 */

		m += "public class " + className
				+ " extends Activity implements OnScrollListener {\n";

		m += "Context context;\n";
		m += "public View containView;\n";
		m += "String searchText;\n";
		// m += "LoadingProgressDialog loading ;\n";

		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				if (idToName == null || idToName.length < 2)
					continue;

				m += "/**" + text + "*/\n";
				if (control.equals("ListView") || control.equals("GridView")||control.equals("ExpandableListView")) {
					m += control + " "
							+ firstCharToLowerAndJavaName(idToName[1]) + ";\n";
					m += className + "Adapter " + "adapter;\n";
				} else if (control.equals("AutoCompleteTextView")) {
					m += "//在xml文件中，AutoCompleteTextView上层中加入属性 避免自动弹出  android:focusable=\"true\" android:focusableInTouchMode=\"true\" ";
					m += control + " "
							+ firstCharToLowerAndJavaName(idToName[1]) + ";\n";
					m += firstCharToUpperAndJavaName(idToName[1]) + "Adapter "
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Adapter;\n";
				}
				else if (control.equals("android.support.v4.widget.DrawerLayout")) {
					
					m += control + " "
							+ firstCharToLowerAndJavaName(idToName[1]) + ";\n";
					m+="/**左抽屉布局*/\n";
					m+="LinearLayout leftDrawerContain;\n";
				}
				else {

					m += control + " "
							+ firstCharToLowerAndJavaName(idToName[1]) + ";\n";
				}
			}
		}

		m += "public " + className + "()\n";
		m += "{\n";
		m += "}\n";

		m += "  @Override\n";
		m += "    public void onDestroy() {\n";
		m += "        super.onDestroy();\n";
		
		m += " }\n";


		m += "@Override\n";
		m += "public void onCreate(Bundle savedInstanceState) {\n";
		m += "     super.onCreate(savedInstanceState);\n";
		m+="	requestWindowFeature(Window.FEATURE_NO_TITLE);\n";
		m+="	Bundle extras = getIntent().getExtras();\n";
		m+="	if (extras != null) {\n";
		m+="		//userPara = extras.getString(\"userPara\");\n";
		m+="	}\n";
		m += "context = this;\n";
		m += "View view = init();\n";
		m += "this.setContentView(view);\n";
		m += " }\n";

		m += "public View init() {\n";
		m += "		if (containView == null) {\n";
		m += "			containView = inflateView(R.layout." + xmlfilename + ");\n";

		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				if (idToName == null || idToName.length < 2)
					continue;

				m += "//" + text + "\n";
				if (control.equals("Button")) {
					m += firstCharToLowerAndJavaName(idToName[1])
							+ " = (Button) containView\n";
					m += "					.findViewById(R.id." + idToName[1] + ");\n";
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnClickListener(new View.OnClickListener() {\n";
					m += "						public void onClick(View v) {\n";

					m += "}\n";
					m += "					});\n";
				} else if (control.equals("ListView")
						|| control.equals("GridView")||control.equals("ExpandableListView")) {
					m += firstCharToLowerAndJavaName(idToName[1]) + "= ("
							+ control + ") containView.findViewById(R.id."
							+ idToName[1] + ");\n";
					m += "adapter= new " + className + "Adapter(context);\n";

				} else if (control.equals("AutoCompleteTextView")) {
					m += firstCharToLowerAndJavaName(idToName[1])
							+ "= (AutoCompleteTextView) containView.findViewById(R.id."
							+ idToName[1] + ");\n";

					m += firstCharToLowerAndJavaName(idToName[1])
							+ "Adapter = new "
							+ firstCharToUpperAndJavaName(idToName[1])
							+ "Adapter(context, null, 0);\n";
					// 实例化自定义的适配器，代码在下面
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setAdapter("
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Adapter);\n";
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setThreshold(1);//第一个字开始搜索\n";

					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnItemClickListener(new OnItemClickListener() {\n";
					m += "@" + "Override\n";
					m += "		public void onItemClick(AdapterView<?> arg0, View view,int position, long arg3) {\n";
					m += "			InputMethodManager m = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);\n";
					m += "			m.hideSoftInputFromWindow("
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);\n";
					m += "			searchText=((TextView)view).getText().toString();\n";
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".clearFocus();\n";
					m += "	}\n";
					m += "	});\n";
				} else if (control.equals("CheckBox")) {

					m += firstCharToLowerAndJavaName(idToName[1])
							+ "= (CheckBox) containView.findViewById(R.id."
							+ idToName[1] + ");\n";

					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnClickListener(new OnClickListener() {\n";
					m += "       @Override\n";
					m += "       public void onClick(View v) {\n";
					m += "if (" + firstCharToLowerAndJavaName(idToName[1])
							+ ".isChecked()) {\n";
					m += " adapter.selectAllCheckBox();\n";
					m += " }else{\n";
					m += " adapter.disSelectAllCheckBox();\n";
					m += "}";
					m += "      }\n";
					m += "    });\n";
				} else if (control.equals("EditText")) {
					m += firstCharToLowerAndJavaName(idToName[1]) + "= ("
							+ control + ") containView.findViewById(R.id."
							+ idToName[1] + ");\n";
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".addTextChangedListener(\n";
					m += "new TextWatcher() {\n";
					m += "		@Override\n";
					m += "		public void afterTextChanged(Editable s) {\n";
					m += "			\n";
					m += "		}\n";

					m += "		@Override\n";
					m += "		public void beforeTextChanged(CharSequence s, int start, int count,\n";
					m += "				int after) {\n";
					m += "			\n";
					m += "		}\n";

					m += "		@Override\n";
					m += "		public void onTextChanged(CharSequence s, int start, int before,\n";
					m += "				int count) {\n";
					m += "				\n";
					m += "		}\n";
					m += "}\n";
					m += ");\n";
				} else if (control.equals("android.support.v4.widget.DrawerLayout")) {
					
					m += firstCharToLowerAndJavaName(idToName[1]) + "= ("
							+ control + ") containView.findViewById(R.id."
							+ idToName[1] + ");//将抽屉打开方法 mDrawerLayout.openDrawer(Gravity.LEFT);\n";
					m+=" leftDrawerContain=(LinearLayout)containView.findViewById(R.id.leftDrawerContain);\n";
				    m+="new ActivityChirldView(leftDrawerContain,this);\n";
				}
				else {
					m += firstCharToLowerAndJavaName(idToName[1]) + "= ("
							+ control + ") containView.findViewById(R.id."
							+ idToName[1] + ");\n";
				}
			}
		}

		m += "		}\n";
		m += "request...();\n";
		m += "return containView;\n";
		m += "}\n";

		

	

	
		m += "public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {\n";
		m += "		// TODO Auto-generated method stub\n";

		m += "}\n";

		
		m += "public void onScrollStateChanged(AbsListView view, int scrollState) {\n";
		m += "		// TODO Auto-generated method stub\n";

		m += "}\n";

	
		m += "private View inflateView(int resource) {\n";
		m += "		LayoutInflater vi = (LayoutInflater) context\n";
		m += "				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);\n";
		m += "		return vi.inflate(resource, null);\n";
		m += "}\n";
		
		m += "{\n";
		
		m += "		Thread thread = new Thread(new Runnable() {\n";
		m += "			public void run() {\n";

		m += "			}\n";
		m += "		});\n";
		m += "		thread.start();\n\n";
		
		
		m += "Handler handler = new Handler() {\n";
		m += "	public void handleMessage(Message msg) {\n";
		m += "		switch (msg.what) {\n";
		m += "			// case SHOW_LOCKER:\n";
		m += "			// break;\n";
		m += "			default:\n";
		m += "				super.handleMessage(msg);\n";

		m += "			}\n";

		m += "		}\n";
		m += "	};\n\n";
		
		m += " new  AsyncTask<String, Integer, Boolean>() {\n";
		m += "	@Override\n";
		m += "	protected void onPreExecute() {\n";
		m += "	}\n";
		m += "	@Override\n";
		m += "	protected Boolean doInBackground(String... params) {\n";
		m += "return true;\n";
		m += "	}\n";
		m += "	@Override\n";
		m += "	protected void onPostExecute(Boolean isOk) {\n";
		m += "		//Intent intent = new Intent();\n";
		m += "		//intent.setClass(" + className
				+ ".this,OperatorModify.class);\n";
		m += "		//Bundle bundle = new Bundle();\n";
		m += "		//bundle.putSerializable(\"operator\",((RespondParam4463604) listData.get(position)));\n";
		m += "		//intent.putExtras(bundle);\n";
		m += "		//startActivityForResult(intent,n0000);\n";
		m += "	}\n";
		m += "}.execute(\"\");}\n";

		m += "public void setView(){\n";
		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				if (idToName == null || idToName.length < 2)
					continue;
				m += "//" + text + "\n";
				if (control.equals("TextView")) {
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setText(\"\");\n";
				}
			}
		}
		m += "}\n";

		m += "/**返回本页刷新数据*/\n";
		m += "int n0000=0;\n";
		m += "protected void onActivityResult(int requestCode, int resultCode, Intent data) {\n";
		m += "		if (requestCode == n0000) {\n";
		m += "				// 页面刷新  重新查询一次\n";
		m += "				if (listData != null)\n";
		m += "					listData.clear();\n";

		m += "				if (adapter != null)\n";
		m += "					adapter.notifyDataSetChanged();\n";
		m += "				//page = 1;//分页使用\n";
		m += "				//recordCount = 1;//分页使用\n";
		m += "				request...();\n";
		m += "			return;\n";
		m += "		}\n";

		m += "		if (data == null)\n";
		m += "			return;\n";
		m += "	\n";
		m += "		Bundle bundle = data.getExtras();\n";
		m += "		String body = bundle.getString(\"jsonString\");\n";
		m += "	\n";
		m += "		if (body!=null) {\n";
		m += "			//有数据 且头没错\n";
		m += "				try {\n";
	

		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				m += "//" + text + "\n";
				if (control.equals("ListView") || control.equals("GridView")||control.equals("ExpandableListView")) {

					m += "adapter.setList(listData);\n";
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setAdapter(adapter);\n";
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnScrollListener("+className+".this);\n";
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnItemClickListener(new OnItemClickListener() {\n";
					m += "	@Override\n";
					m += "	public void onItemClick(\n";
					m += "	AdapterView<?> arg0,\n";
					m += "	View view, int position,\n";
					m += "	long id) {\n";

					m += "		Intent intent = new Intent();\n";
					m += "		intent.setClass(" + className
							+ ".this,OperatorModify.class);\n";
					m += "		Bundle bundle = new Bundle();\n";
					m += "		bundle.putSerializable(\"operator\",((RespondParam4463604) listData.get(position)));\n";
					m += "		intent.putExtras(bundle);\n";
					m += "		startActivityForResult(intent,n0000);\n";
					m += "	}\n";
					m += "});\n";

				}
			}
		}

		m += "//注入RequestRespond\n";
		m += "//End注入RequestRespond\n";

		m += "} catch (Exception e) {\n";
		m += "e.printStackTrace();\n";
		m += "}\n";
		m += "\n";

		m += "} else  {\n";
		m += "//page--;//分页使用\n";
		m += "} \n";
		m += "}\n";

		m += "}\n";

		System.out.println(m);
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/android", className,
				"java", m);
		//FileUtil.makeFile(KeyValue.readCache("projectPath"), "src", className,"java", m);
		// stringToFile("d:\\" + className + ".java", m);
	}

	public void stringToFile(String fileName, String str) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			fw.write(str);
			fw.flush();// 加上这句
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public static String firstCharToLowerAndJavaName(String string) {
		// buy_typelist
		String[] ss = string.split("_");
		String temp = "";
		int i = 0;
		for (String s : ss) {
			if (i == 0) {
				temp += s.substring(0, 1).toLowerCase() + s.substring(1);
			} else {
				temp += "_" + s.substring(0, 1).toUpperCase() + s.substring(1);
			}
			i++;
		}
		return temp;
	}

	public static List btnXmlnameTodbnameOrjavaname(String string) {
		// buy_typelist
		String[] ss = string.split("_");
		List<String> result = new ArrayList();
		String temp = "";
		int i = 0;

		for (String s : ss) {
			if (s.equals("jump")) {
				String fragmentName = "";
				for (int j = 0; j < i; j++) {

					fragmentName += ss[j].substring(0, 1).toUpperCase()
							+ ss[j].substring(1);
				}

				temp += "Fragment fragment = new " + fragmentName + "();\n";

				temp += "Bundle bundle = new Bundle();\n";
				temp += "bundle.putString(\"" + fragmentName + "\", value);\n";
				temp += "fragment.setArguments(bundle);\n";
				temp += "//...FragmentActivity activity = (...FragmentActivity) getActivity();\n";
				temp += "activity.pushFragment(fragment);\n";

				result.add(temp);

			} else if (s.equals("back")) {

				temp += "//...FragmentActivity fragmentActivity=((...FragmentActivity)context);\n";
				temp += "fragmentActivity.popFragment();\n";
				result.add(temp);
			}

			else if (s.equals("update")) {

				temp += ss[0] + "DBContentResolver dBContentResolver = new "
						+ ss[0] + "DBContentResolver(context);\n";

				temp += "List<" + ss[0]
						+ "Bean> beans = dBContentResolver.query" + ss[0]
						+ "By" + ss[1] + "(\"ProductId\");\n";

				temp += "if(beans!=null && beans.size()>0)\n";
				temp += "{\n";
				temp += ss[0] + "Bean bean=beans.get(0);\n";
				temp += "	if(bean.get" + ss[2] + "().equals(\"true\"))\n";
				temp += "	{\n";

				temp += "	}\n";
				temp += "	bean.set" + ss[2] + "(\"true\");\n";

				temp += "	dBContentResolver.update(bean);\n";
				temp += "}\n";
				result.add(temp);

			}

			i++;
		}
		return result;
	}

	public static String listXmlnameTodbnameOrjavaname(String string) {
		// buy_typelist
		String[] ss = string.split("_");
		String temp = "";
		int i = 0;
		for (String s : ss) {
			if (s.equals("select")) {
				temp += ss[0] + "DBContentResolver dBContentResolver = new "
						+ ss[0] + "DBContentResolver(context);\n";
				temp += "List<" + ss[0]
						+ "Bean> dbBeans = dBContentResolver.query" + ss[0]
						+ "By" + ss[1] + "(\"ProductId\");\n";
				temp += "adapter.beanChange(list, dbBeans);\n";
			}
			i++;
		}
		return temp;
	}

	public String textviewXmlnameTodbnameOrjavaname() {
		String temp = "";
		boolean isHaveListView = false;
		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");

				if (control.equals("ListView") || control.equals("GridView")||control.equals("ExpandableListView")) {
					isHaveListView = true;
				} else if (control.equals("TextView")) {

				}
			}
		}

		if (!isHaveListView) {
			HashSet<String> set = new HashSet<String>();
			for (String control : controls) {
				// control为Button TextView....
				NodeList buttonItems = root.getElementsByTagName(control);
				for (int i = 0; i < buttonItems.getLength(); i++) {
					Element personNode = (Element) buttonItems.item(i);
					String id = personNode.getAttribute("android:id");
					String text = personNode.getAttribute("android:text");
					String[] idToName = id.split("/");
					if (idToName == null || idToName.length < 2)
						continue;
					if (control.equals("TextView")) {

						String[] ss = idToName[1].split("_");

						for (String s : ss) {
							if (s.equals("select")) {

								set.add(new String(ss[0]));

							}

						}
					}
				}
			}

			for (String tablename : set) {

				temp += tablename
						+ "DBContentResolver dBContentResolver = new "
						+ tablename + "DBContentResolver(context);\n";
				temp += "List<" + tablename
						+ "Bean> beans = dBContentResolver.query" + tablename
						+ "By(\"ProductId\");\n";
			}
		}
		return temp;
	}

}
