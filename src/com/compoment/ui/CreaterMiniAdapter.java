package com.compoment.ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//http://www.189works.com/article-37835-1.html

public class CreaterMiniAdapter {

	String xmlfile = "order_payway_coupon_item.xml";// 修改就行
	static String classDir = null;
	static String xmlFilePath = null;
	static String xmlfilename = null;
	static String className = null;
	String[] controls = { "Button", "TextView", "EditText", "ImageView",
			"ExpandableListView", "ListView", "GridView", "Spinner", "CheckBox" };
	Element root = null;
	String m = "";

	public static void main(String[] args) throws SAXException, IOException {
		CreaterMiniAdapter createrAdapter = new CreaterMiniAdapter();
		createrAdapter.create();

	}

	public CreaterMiniAdapter() {

		classDir = this.getClass().getResource("/").getPath();
		int pos = xmlfile.indexOf(".");
		xmlfilename = xmlfile.substring(0, pos);
		xmlFilePath = classDir + "com/compoment/ui/xml/" + xmlfilename;
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
		m += "import java.util.List;\n";
		m += "import android.content.Context;\n";
		m += "import android.view.LayoutInflater;\n";
		m += "import android.view.View;\n";
		m += "import android.view.ViewGroup;\n";
		m += "import android.widget.BaseAdapter;\n";
		m += "import android.widget.ImageView;\n";
		m += "import android.widget.TextView;\n";
		m += "import android.widget.Button;\n";
		m += "import android.widget.EditText;\n";
		m += "import android.widget.CheckBox;\n";

		m += "public class " + className + "Adapter  {\n";

		m += "	private static final String TAG = \"" + className
				+ "Adapter\";\n";
		m += "	/** 列表是否滑动中，如果是滑动状态则仅从内存中获取图片，否则开启线程去外存或网络获取图片。 */\n";
		m += "	private boolean isScrolling = false;\n";
		m += "	public List<" + className + "AdapterBean> list;\n";
		m += "	public ImageLoader mImageLoader;\n";
		m += "	private Context mContext;\n";
		m += "ViewGroup parent;";
		m += "	int defaultImg = R.drawable.ic_launcher;\n";

		m += "	public void setFlagIsScrolling(boolean isScrolling) {\n";
		m += "		this.isScrolling = isScrolling;\n";
		m += "	}\n";

		m += "	public " + className + "Adapter(Context context) {\n";
		m += "		this.mContext = context;\n";
		m += "		mImageLoader= ((..Activity)context).mImageLoader;//建议在Acitivty中new 公用  new ImageLoader(context);\n";
		m += "	}\n";

		m += "	public ImageLoader getImageLoader() {\n";
		m += "		return mImageLoader;\n";
		m += "	}\n";

		m += "	public void setList(List<" + className
				+ "AdapterBean> list) {\n";
		m += "		this.list = list;\n";

		m += "	}\n";

		m += "	public void getView(ViewGroup parent) {\n";

		m += "this.parent=parent;\n";
		m += "this.parent.removeAllViews();\n";
		m += "int position=0;\n";
		m += "for (" + className + "AdapterBean adapterbean : list) {\n";
		m += "		ViewHolder viewHolder = null;\n";
		m += "			View convertView= LayoutInflater.from(mContext).inflate(\n";
		m += "					R.layout." + xmlfilename + ", null);\n";
		m += "			viewHolder = new ViewHolder();\n";

		// m += "			viewHolder.mTextView = (TextView) convertView\n";
		// m += "					.findViewById(R.id.tv_tips);\n";

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
				m += "			viewHolder."
						+ firstCharToLowerAndJavaName(idToName[1]) + " = ("
						+ control + ") convertView";
				m += ".findViewById(R.id." + idToName[1] + ");\n";

				if (control.equals("CheckBox")) {
					m += " viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnClickListener(new View.OnClickListener() {\n";
					m += "	                      @Override \n";
					m += "	                      public void onClick(View v) {\n";
					m += "\n int position=Integer.valueOf(v.getTag().toString());\n";
					
					m += "		" + className + "AdapterBean adapterbean = null\n";	
					m+="	if(list!=null && list.size()>position)\n";
					m += " adapterbean = list.get(position);\n";
					
					m += "	                          CheckBox cb = (CheckBox)v;\n";

					m += "adapterbean."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "CheckBoxState=cb.isChecked()" + ";\n";
					m += "}\n";
					m += "});\n";
				} else if (control.equals("Button")) {
					m += " viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnClickListener( \n new View.OnClickListener() {\n"
							+ "public void onClick(View v) {";
					m += btnXmlnameTodbnameOrjavaname(idToName[1]);
					m += "}\n});\n\n";
				}
			}
		}

		m += "		if (adapterbean != null) {\n";

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
				if (control.equals("ImageView")) {
					m += "			mImageLoader.setImgToImageView(adapterbean."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Url,\n";
					m += "					viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ", defaultImg, isScrolling);\n";
				} else if (control.equals("CheckBox")) {
					m += "viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".setTag(position);\n";
					m += "viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".setChecked(adapterbean."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "CheckBoxState);\n";

				} else if (control.equals("EditText")) {
					m += "viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".setText(adapterbean."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Value);\n";
				} else if (control.equals("Button")) {
					m += "viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".setTag(position);\n";

				} else if (control.equals("TextView")) {
					m += "viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".setText(adapterbean."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Value);\n";
				}

			}
		}

		m += "		}\n";

		m += "viewHolder.position=position;\n";
		m += "	convertView.setTag(viewHolder);\n";
		m += "	parent.addView(convertView);\n";
		m += "	position++;\n";
		m += "}\n";
		m += "	}\n";

		m += "public void notifyDataSetChanged(){\n";
		m += "	for(int i=0;i< parent.getChildCount();i++)\n";
		m += "	{\n";
		m += "		View view=parent.getChildAt(i);\n";
		m += "		ViewHolder viewHolder =(ViewHolder)view.getTag();\n";
		
		m += "		" + className + "AdapterBean adapterbean = null\n";	
		m+="	if(list!=null && list.size()>viewHolder.position)\n";
		m += " adapterbean = list.get(viewHolder.position);\n";


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
				if (control.equals("ImageView")) {
					m += "			mImageLoader.setImgToImageView(adapterbean."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Url,\n";
					m += "					viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ", defaultImg, isScrolling);\n";
				} else if (control.equals("CheckBox")) {

					m += "viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".setChecked(adapterbean."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "CheckBoxState);\n";

				} else if (control.equals("EditText")) {
					m += "viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".setText(adapterbean."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Value);\n";
				} else if (control.equals("Button")) {

				} else if (control.equals("TextView")) {
					m += "viewHolder."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ ".setText(adapterbean."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Value);\n";
				}

			}
		}

		m += "}\n";
		m += "}\n";

		m += "	static class ViewHolder {\n";
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
				m += control + " " + firstCharToLowerAndJavaName(idToName[1])
						+ ";\n";
			}
		}
		m += "int position;\n";
		m += "	}\n";

		m += "public	static class " + className + "AdapterBean {\n";
		m += "public String id;\n";
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

				if (control.equals("ImageView")) {
					m += "		public String "
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Url;\n";
				} else if (control.equals("TextView")) {
					m += "		public String "
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Value;\n";
				} else if (control.equals("EditText")) {
					m += "		public String "
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Value;\n";
				} else if (control.equals("CheckBox")) {
					m += "		public boolean "
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "CheckBoxState;\n";
				}
			}
		}

		m += "	}\n";

		m += beanChange();
		m += checkBoxSelectAll();
		m += "	}\n";

		System.out.println(m);
		stringToFile("d:\\" + className + "Adapter.java", m);
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

	public String beanChange() {
		String temp = "";
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

		String tableName = "";
		for (String tablename : set) {
			tableName = tablename;
		}

		temp += "	public  void beanChange(List<" + className
				+ "AdapterBean> adapterBeans,List<" + tableName
				+ "Bean>  dbBeans)\n";

		temp += "{\n";

		temp += "	for(" + tableName + "Bean dbBean:dbBeans)\n";
		temp += "{\n";

		temp += className + "AdapterBean adapterBean=new " + className
				+ "AdapterBean();\n";

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
				String[] ss = idToName[1].split("_");

				if (control.equals("ImageView")) {
					temp += "adapterBean."
							+ firstCharToLowerAndJavaName(idToName[1]) + "Url"
							+ "=dbBean.get" + ss[1] + "();\n";
				} else if (control.equals("TextView")) {
					temp += "adapterBean."
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "Value" + "=dbBean.get" + ss[1] + "();\n";
				} else if (control.equals("EditText")) {

				} else if (control.equals("CheckBox")) {

				}
			}
		}

		temp += "		adapterBeans.add(adapterBean);\n";
		temp += "}\n";

		temp += "}\n";

		return temp;
	}

	public static String btnXmlnameTodbnameOrjavaname(String string) {
		// buy_typelist
		String[] ss = string.split("_");

		String temp = "";
		int i = 0;

		for (String s : ss) {
			if (s.equals("jump")) {

				for (int j = 0; j < i; j++) {

					temp += ss[j].substring(0, 1).toUpperCase()
							+ ss[j].substring(1);
				}
				temp = "//((...FragmentActivity)context).pushFragment(new "
						+ temp + "());\n";

			} else if (s.equals("back")) {
				temp = "//((...FragmentActivity)context).popFragment();\n";

			}

			else if (s.equals("update")) {

				temp += "\n int position=Integer.valueOf(v.getTag().toString());\n";
				temp+= "		" + className + "AdapterBean adapterbean = null\n";	
				temp+="	if(list!=null && list.size()>position)\n";
				temp += " adapterbean = list.get(position);\n";
				temp += ss[0] + "DBContentResolver dBContentResolver = new "
						+ ss[0] + "DBContentResolver(mContext);\n";

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

			}

			i++;
		}
		return temp;
	}

	public String checkBoxSelectAll() {
		String temp = "";
		String adapterbeanCheckBoxName = "";
		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");

				if (control.equals("CheckBox")) {
					adapterbeanCheckBoxName += ""
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "CheckBoxState";
				}
			}
		}

		if (adapterbeanCheckBoxName.equals(""))
			return "";
		temp += "public void selectAllCheckBox() { // 全选checkBox\n";
		temp += "    for (int i = 0; i < list.size(); i++) {\n";
		temp += className + "AdapterBean adapterbean = list.get(i);\n";
		temp += "     	adapterbean." + adapterbeanCheckBoxName + "=true;\n";
		temp += "   }\n";
		temp += "     notifyDataSetChanged();\n";
		temp += " }\n";

		temp += " public void disSelectAllCheckBox() { // 全不选checkBox\n";
		temp += "     for (int i = 0; i < list.size(); i++) {\n";
		temp += className + "AdapterBean adapterbean = list.get(i);\n";
		temp += "     	adapterbean." + adapterbeanCheckBoxName + "=false;\n";
		temp += "    }\n";
		temp += "   notifyDataSetChanged();\n";
		temp += "}\n";

		temp += " public void switchSelectCheckBox() { // 反选checkBox\n";
		temp += "  for (int i = 0; i < list.size(); i++) {\n";
		temp += className + "AdapterBean adapterbean = list.get(i);\n";
		temp += "   boolean select =adapterbean." + adapterbeanCheckBoxName
				+ ";\n";
		temp += "  adapterbean." + adapterbeanCheckBoxName + "=(!select);\n";

		temp += "   }\n";
		temp += "  notifyDataSetChanged();\n";
		temp += " }\n";

		return temp;
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
}
