package com.compoment.ui;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



/**
 * <?xml version="1.0" encoding="utf-8"?> <LinearLayout
 * xmlns:android="http://schemas.android.com/apk/res/android"
 * android:layout_width="fill_parent" android:layout_height="fill_parent"
 * android:padding="20dp"
 * 
 * android:gravity="center_horizontal" android:orientation="vertical" >
 * 
 * <LinearLayout android:layout_width="wrap_content"
 * android:layout_height="wrap_content"
 * 
 * android:background="#eee" android:gravity="center_horizontal"
 * android:orientation="vertical" >
 * 
 * <LinearLayout android:layout_width="fill_parent"
 * android:layout_height="wrap_content"
 * android:background="@drawable/title_bg_g" android:gravity="center_horizontal"
 * android:padding="10dp" >
 * 
 * <TextView android:id="@+id/order_prompt_add_shoppingcar_title_txtview"
 * android:layout_width="wrap_content" android:layout_height="wrap_content"
 * android:layout_alignParentTop="true" android:layout_centerHorizontal="true"
 * android:text="@string/dialog_title" android:textColor="#B2CBA6"
 * android:textSize="20sp" /> </LinearLayout>
 * 
 * 
 * <LinearLayout android:background="#eee" android:gravity="center_horizontal"
 * android:layout_width="wrap_content" android:layout_height="wrap_content"
 * android:layout_margin="20dp" >
 * 
 * <TextView android:id="@+id/msg_textview" android:layout_width="wrap_content"
 * android:layout_height="wrap_content" android:text="购物车中，以下产品库存不足，是否只购买所有库存量。"
 * android:scrollbars="vertical" android:textColor="#151515"
 * android:maxLines="15" android:textSize="18sp" /> </LinearLayout>
 * 
 * 
 * <LinearLayout android:layout_width="match_parent"
 * android:layout_height="wrap_content" android:gravity="center_horizontal"
 * android:orientation="horizontal" android:padding="5dp" >
 * 
 * <Button android:id="@+id/ok_btn" android:layout_width="wrap_content"
 * android:layout_height="wrap_content"
 * 
 * android:text="确定" android:layout_weight="1" android:textColor="#B2CBA6"
 * android:textSize="14sp" />
 * 
 * <Button android:id="@+id/cancel_btn" android:layout_width="wrap_content"
 * android:layout_height="wrap_content" android:layout_marginLeft="5dp"
 * 
 * android:text="取消" android:layout_weight="1" android:textColor="#B2CBA6"
 * android:textSize="14sp" /> </LinearLayout> </LinearLayout> </LinearLayout>
 * 
 * */
public class CreaterPopupWindow {

	String xmlfile = "popup.xml";// 修改就行
	static String classDir = null;
	static String xmlFilePath = null;
	static String xmlfilename = null;
	static String className = null;
	String[] controls = { "Button", "TextView", "EditText", "ImageView",
			"ExpandableListView", "ListView", "GridView", "Spinner" };
	Element root = null;
	String m = "";

	public static void main(String[] args) throws SAXException, IOException {
		CreaterPopupWindow createView = new CreaterPopupWindow();
		createView.create();
	}

	public CreaterPopupWindow() {
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
		m += "import java.util.ArrayList;\n";
		m += "import java.util.List;\n";
		m += "import android.app.Activity;\n";
		m += "import android.content.Context;\n";
		m += "import android.content.Intent;\n";
		m += "import android.graphics.Color;\n";
		m += "import android.graphics.drawable.BitmapDrawable;\n";
		m += "import android.graphics.drawable.ColorDrawable;\n";
		m += "import android.text.method.ScrollingMovementMethod;\n";
		m += "import android.view.Gravity;\n";
		m += "import android.widget.ArrayAdapter;\n";
		m += "import android.view.KeyEvent;\n";
		m += "import android.view.LayoutInflater;\n";
		m += "import android.view.MotionEvent;\n";
		m += "import android.view.View;\n";
		m += "import android.view.View.OnClickListener;\n";
		m += "import android.view.View.OnTouchListener;\n";
		m += "import android.view.ViewGroup;\n";
		m += "import android.view.ViewGroup.LayoutParams;\n";
		m += "import android.widget.AdapterView;\n";
		m += "import android.widget.AdapterView.OnItemSelectedListener;\n";
		m += "import android.widget.BaseAdapter;\n";
		m += "import android.widget.Button;\n";
		m += "import android.widget.CheckBox;\n";
		m += "import android.widget.EditText;\n";
		m += "import android.widget.GridView;\n";
		m += "import android.widget.ImageView;\n";
		m += "import android.widget.LinearLayout;\n";
		m += "import android.widget.ListView;\n";
		m += "import android.widget.PopupWindow;\n";
		m += "import android.widget.TextView;\n";
		m += "import android.widget.AdapterView;\n";
		m += "import android.widget.AdapterView.OnItemClickListener;\n";
		m += "import android.widget.Spinner;\n";
		m += "/*" + className + "PopupWindow menuPopupWindow = new "
				+ className + "PopupWindow(context, new OnClickListener() {\n";
		m += "	@Override\n";
		m += "	public void onClick(View arg0) {\n";
		m += "menuPopupWindow.dismiss();\n";
		m += "}});\n";
		m += "menuPopupWindow.show();*/\n\n";

		m += "public class " + className + "PopupWindow {\n";
		m += "	private PopupWindow menuPopUp;\n";

		// m+="	private TextView menu_pop_title;\n";
		// m+="	public ListView listView;\n";

		boolean hasListViewOrGridView = false;
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
					hasListViewOrGridView = true;
				}
				m += "public " + control + " "
						+ firstCharToLowerAndJavaName(idToName[1]) + ";\n";

			}
		}

		m += "	private LayoutInflater inflater;\n";
		m += "    /**false在中部显示     true在底部显示*/\n";
		m += "	private boolean isBottomOrCenter = false;\n\n";

		if (hasListViewOrGridView) {
			m += "	public "
					+ className
					+ "PopupWindow(Context context,List<"
					+ className
					+ "AdapterBean> list,OnClickListener onClicklistener,OnItemClickListener onItemClickListener) {\n";
		} else {
			m += "	public "
					+ className
					+ "PopupWindow(Context context,OnClickListener onClicklistener) {\n";
		}

		m += "		inflater = LayoutInflater.from(context);\n";
		m += "		View view = inflater.inflate(R.layout." + xmlfilename
				+ ", null);\n";

		boolean hasButton = false;
		for (String control : controls) {
			// control为Button TextView....
			NodeList buttonItems = root.getElementsByTagName(control);
			for (int i = 0; i < buttonItems.getLength(); i++) {
				Element personNode = (Element) buttonItems.item(i);
				String id = personNode.getAttribute("android:id");
				String text = personNode.getAttribute("android:text");
				String[] idToName = id.split("/");
				m += "//" + text + "\n";
				if (control.equals("TextView") || control.equals("EditText")) {
					m += firstCharToLowerAndJavaName(idToName[1]) + "=("
							+ control + ")view.findViewById(R.id."
							+ idToName[1] + ");\n";
				} else if (control.equals("Button")) {
					hasButton = true;
					m += firstCharToLowerAndJavaName(idToName[1]) + "=("
							+ control + ")view.findViewById(R.id."
							+ idToName[1] + ");\n";
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnClickListener(onClicklistener);\n";
				} else if (control.equals("ListView")
						|| control.equals("GridView")) {
					m += firstCharToLowerAndJavaName(idToName[1]) + "= ("
							+ control + ") view.findViewById(R.id."
							+ idToName[1] + ");\n";
					m += "		final " + className + "Adapter adapter = new "
							+ className + "Adapter( context);\n";
					m += "adapter.setList(list);\n";
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setAdapter(adapter);\n";
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnItemClickListener(onItemClickListener);\n";
				} else if (control.equals("Spinner")) {
					m += firstCharToLowerAndJavaName(idToName[1]) + "= ("
							+ control + ") view.findViewById(R.id."
							+ idToName[1] + ");\n";

					m += "List "
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "_data = Arrays.asList(new String[] { \"\" ,\"\"});\n";
					m += "ArrayAdapter "
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "_adapter= new ArrayAdapter<String>(context,  android.R.layout.simple_spinner_item, "
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "_data);\n";

					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setAdapter("
							+ firstCharToLowerAndJavaName(idToName[1])
							+ "_adapter);\n";
					m += firstCharToLowerAndJavaName(idToName[1])
							+ ".setOnItemSelectedListener(new OnItemSelectedListener(){";

					m += "		@Override\n";
					m += "		public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {\n";
					m += "			String value=(String) arg0.getItemAtPosition(arg2);\n";
					m += "	}\n";
					m += "public void onNothingSelected(AdapterView<?> arg0) {\n";
					m += "}\n";
					m += "});\n";
				}

			}
		}

		m += "		initPopWindow(view);\n";

		if (!hasButton) {
			m += "		//轻触退出\n";
			m += "		TosatTouchInterceptor tosatTouchInterceptor = new TosatTouchInterceptor();\n";
			m += "		menuPopUp.setTouchInterceptor(tosatTouchInterceptor);\n";
		}
		m += "}\n";

		m += "	//not need change\n";

		m += "	private void initPopWindow(View view) {\n";
		m += "		menuPopUp = new PopupWindow(view, LayoutParams.FILL_PARENT,\n";
		m += "				LayoutParams.WRAP_CONTENT, true);//\n";

		m += "		menuPopUp.setFocusable(true);\n";

		m += "		menuPopUp.setOutsideTouchable(true);\n";
		m += "		//\n";
		m += "		menuPopUp.setBackgroundDrawable(new BitmapDrawable());// 注掉 点 PopupWindow之外的区域后， PopupWindow不会消失。\n";
		m += "		//\n";
		m += "		menuPopUp.setBackgroundDrawable(new ColorDrawable(R.color.prompt_menupopupwindow_bg));//#c0000000 ColorDrawable dw = new ColorDrawable(-00000);全透明 // 注掉 点 PopupWindow之外的区域后， PopupWindow不会消失。  \n";

		m += "		view.setFocusableInTouchMode(true);\n";

		m += "		view.setOnKeyListener(new View.OnKeyListener() {\n";

		m += "				@Override\n";
		m += "				public boolean onKey(View v, int keyCode, KeyEvent event) {\n";

		m += "					if(keyCode == KeyEvent.KEYCODE_MENU && menuPopUp.isShowing()){\n";
		m += "						menuPopUp.dismiss();\n";
		m += "						return true;\n";
		m += "					}\n";
		m += "					return false;\n";
		m += "				}\n";
		m += "			});\n";

		m += "}\n";

		m += "	private class TosatTouchInterceptor implements OnTouchListener {\n";
		m += "		@Override\n";
		m += "		public boolean onTouch(View v, MotionEvent event) {\n";

		m += "			menuPopUp.dismiss();\n";
		m += "			return false;\n";
		m += "		}\n";
		m += "}\n";

		m += "	public void show(View root) {\n";

		m += "		if (!menuPopUp.isShowing() && !isBottomOrCenter) {\n";
		m += "			menuPopUp.showAtLocation(root, Gravity.CENTER, 0, 0);\n";
		m += "		}else if(!menuPopUp.isShowing() && isBottomOrCenter){\n";
		m += "			menuPopUp.showAtLocation(root, Gravity.CENTER | Gravity.BOTTOM, 0, 0);\n";
		m += "		}\n";
		m += "}\n";

		m += "	public void hide() {\n";
		m += "		if (menuPopUp.isShowing()) {\n";
		m += "			menuPopUp.dismiss();\n";
		m += "		}\n";
		m += "}\n";

		m += "	/**\n";
		m += "	 *\n";
		m += "	 */\n";
		m += "	public void dismiss(){\n";
		m += "		if(menuPopUp != null){\n";
		m += "			menuPopUp.dismiss();\n";
		m += "		}\n";
		m += "}\n";

		m += "	public boolean isShow(){\n";
		m += "		return menuPopUp.isShowing();\n";
		m += "}\n";
		m += "}\n";

		System.out.println(m);

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
				temp += s.substring(0, 1).toUpperCase() + s.substring(1);
			}
			i++;
		}
		return temp;
	}
}
