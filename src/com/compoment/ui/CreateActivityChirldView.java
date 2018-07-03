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

public class CreateActivityChirldView {

	String xmlfile = "drawbackr_detail_chirld.xml";// 修改就行
	static String classDir = null;
	static String xmlFilePath = null;
	static String xmlfilename = null;
	static String className = null;
	String[] controls = { "Button", "TextView", "EditText", "ImageView",
			"Spinner", "AutoCompleteTextView", "CheckBox", "LinearLayout",
			"RadioButton", "RadioGroup","ListView","GirdView" };
	Element root = null;
	String m = "";

	public static void main(String[] args) throws SAXException, IOException {
		CreateActivityChirldView createView = new CreateActivityChirldView("");
		createView.create();
	}

	public CreateActivityChirldView(String filename) {
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
		m += "import android.app.Activity;\n";
		m += "import android.content.Context;\n";
		m += "import android.os.AsyncTask;\n";
		m += "import android.os.Bundle;\n";
		m += "import android.os.Handler;\n";
		m += "import android.os.Message;\n";
		m += "import android.support.v4.app.Fragment;\n";
		m += "import android.view.LayoutInflater;\n";
		m += "import android.view.View;\n";
		m += "import android.view.ViewGroup;\n";
		m += "import android.view.inputmethod.InputMethodManager;\n";
		m += "import android.widget.AbsListView;\n";
		m += "import android.widget.AdapterView;\n";
		m += "import android.widget.Button;\n";
		m += "import android.widget.ListView;\n";
		m += "import android.widget.TextView;\n";
		m += "import java.util.ArrayList;\n";
		m += "import java.util.List;\n";
		m += "import android.widget.AdapterView.OnItemClickListener;\n";
		m += "import android.view.View.OnClickListener;\n";
		m += "import android.widget.AbsListView.OnScrollListener;\n";
		m += "import android.widget.CheckBox;\n";

		m += "public class " + className + " implements OnScrollListener {\n";

		m += "	Context context;\n";
		m += "	public View containView;\n";
		m += "String searchText;\n";
		m += "public ViewGroup parentViewContain;\n";
		m += "public ... parentActivity;\n";
	
		// m += "	private OrderTypelistAdapter adapter;\n";
		// m += "	private ListView list;\n";

		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				m += "/**" + text + "*/\n";
				if (control.equals("ListView") || control.equals("GridView")) {
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
				} else if (control.equals("LinearLayout")) {
					if (idToName.length > 1
							&& idToName[1].contains("list_linearlayout")) {
						m += control + " "
								+ firstCharToLowerAndJavaName(idToName[1])
								+ ";\n";
						m += className + "Adapter " + "adapter;\n";
					}
				} else {
					if (idToName.length > 1)
						m += control + " "
								+ firstCharToLowerAndJavaName(idToName[1])
								+ ";\n";
				}
			}
		}

		m += "public "
				+ className
				+ "(ViewGroup parentViewContain,...Activity parentActivity)\n";
		m += "{\n";
		m += "this.parentViewContain=parentViewContain;\n";
		m += "this.context=parentActivity;\n";
		m += "this.parentActivity=parentActivity;\n";
		m += "init();\n";
		m+="  setView();\n";
		m += "}\n";

	

		m += "	public View init() {\n";
		m += "		if (containView == null) {\n";
		m += "			containView = inflateView(R.layout." + xmlfilename + ");\n";

		// m +=
		// "			list = (ListView) this.findViewById(R.id.listview); // 联系人ListView\n";
		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				m += "//" + text + "\n";
				if (control.equals("Button")) {
					m += firstCharToLowerAndJavaName(idToName[1])
							+ " = (Button) containView\n";
					m += "					.findViewById(R.id." + idToName[1] + ");\n";
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnClickListener(new View.OnClickListener() {\n";
					m += "						public void onClick(View v) {\n";
				
					m += "						}\n";
					m += "					});\n";
				} else if (control.equals("RadioGroup")) {
					m += firstCharToLowerAndJavaName(idToName[1])
							+ " = (RadioGroup) containView\n";
					m += "					.findViewById(R.id." + idToName[1] + ");\n";
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {\n";
					m += "		public void onCheckedChanged(RadioGroup group, int checkedId) {  \n";
					m += "  if(checkedId==((RadioButton)..).getId()) {}\n";
					m += "						}\n";
					m += "					});\n";
				}

				else if (control.equals("ListView")
						|| control.equals("GridView")) {
					m += firstCharToLowerAndJavaName(idToName[1]) + "= ("
							+ control + ") containView.findViewById(R.id."
							+ idToName[1] + ");\n";
					m += "adapter= new " + className + "Adapter(context);\n";

				} else if (control.equals("LinearLayout")) {
					if (idToName.length > 1
							&& idToName[1].contains("list_linearlayout")) {
						m += firstCharToLowerAndJavaName(idToName[1]) + "= ("
								+ control + ") containView.findViewById(R.id."
								+ idToName[1] + ");\n";
						m += "adapter= new " + className
								+ "Adapter(context);\n";
					}
				}

				else if (control.equals("AutoCompleteTextView")) {
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
				}

				else {
					if (idToName.length > 1)
						m += firstCharToLowerAndJavaName(idToName[1]) + "= ("
								+ control + ") containView.findViewById(R.id."
								+ idToName[1] + ");\n";
				}
			}
		}

		m += "		}\n";
		m += "parentViewContain.addView(containView);\n";
	
		m += "return containView;\n";
		m += "}\n";



		m += "	private View inflateView(int resource) {\n";
		m += "		LayoutInflater vi = (LayoutInflater) context\n";
		m += "				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);\n";
		m += "		return vi.inflate(resource, null);\n";
		m += "}\n";

		m += "	@Override\n";
		m += "	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {\n";
		m += "		// TODO Auto-generated method stub\n";

		m += "}\n";

		m += "	@Override\n";
		m += "	public void onScrollStateChanged(AbsListView arg0, int arg1) {\n";
		m += "		// TODO Auto-generated method stub\n";

		m += "}\n";


		
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
				if (control.equals("ListView") || control.equals("GridView")) {

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
		m += "}\n";

		m += "}\n";

		System.out.println(m);
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/android", className,
				"java", m);
	//	FileUtil.makeFile(KeyValue.readCache("projectPath"), "src", className,
		//		"java", m);
		
		//stringToFile("d:\\" + className + ".java", m);
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

	public static String linearLayoutXmlnameTodbnameOrjavaname(String string) {
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

				if (control.equals("ListView") || control.equals("GridView")) {
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

					if (idToName.length <= 1)
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
