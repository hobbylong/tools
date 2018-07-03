package com.compoment.cut.android;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.compoment.cut.CompomentBean;
import com.compoment.remote.AndroidLayoutXmlInterface;
import com.compoment.remote.IphoneViewControllerXibInterface;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.SerializeToFile;

public class AndroidLayoutXml extends UnicastRemoteObject implements AndroidLayoutXmlInterface{

	public AndroidLayoutXml() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}




	int maxW = 0;
	int maxH = 0;
	String m = "";

	List<CompomentBean> beans;
	public List  getBeans() throws RemoteException
	{
		
		return beans;
	}
	
	public String analyseRelative(String filename, List<CompomentBean> abeans) throws RemoteException{
		 maxW = 0;
		 maxH = 0;
	      m = "";
		
	     if(beans!=null)
	     {
	    	 beans.clear();
	    	 beans=null;
	     }
		beans=abeans;
		
		List<CompomentBean> layouts = new ArrayList<CompomentBean>();
		CompomentBean maxBean = null;
		
		// 找出容器
		for (CompomentBean bean : beans) {
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
		
		if(maxBean==null)
		{
			beans.clear();
			return "no have layout";
		}

		// 儿子找父亲 （子控件找容器）
		for (CompomentBean bean : beans) {
			if (!bean.type.contains("Layout")) {
				CompomentBean nearLayout = null;
				Integer minDistance = null;
				for (CompomentBean layout : layouts) {
					if (bean.x >= layout.x && bean.x <= layout.w + layout.x
							&& bean.y >= layout.y
							&& bean.y <= layout.h + layout.y) {
						if (minDistance == null) {
							minDistance = bean.x - layout.x;
							nearLayout = layout;
						} else {
							if (minDistance > bean.x - layout.x) {
								minDistance = bean.x - layout.x;
								nearLayout = layout;
							}
						}
					}
				}

				if (nearLayout != null)
					nearLayout.setChirld(bean);
			}

		}

		// 儿子找父亲（子容器找父容器）
		for (CompomentBean bean : layouts) {

			CompomentBean nearLayout = null;
			Integer minDistance = null;
			for (CompomentBean layout : layouts) {

				if (!layout.enname.equals(bean.enname) && bean.x >= layout.x
						&& bean.x <= layout.w + layout.x && bean.y >= layout.y
						&& bean.y <= layout.h + layout.y) {

					if (minDistance == null) {
						minDistance = bean.x - layout.x;
						nearLayout = layout;
					} else {
						if (minDistance > bean.x - layout.x) {
							minDistance = bean.x - layout.x;
							nearLayout = layout;
						}
					}

				}

			}

			if (nearLayout != null)
				nearLayout.setChirld(bean);

		}

		// LinearLayout RelativeLayout 儿子们的位置关系(排版方向 水平或垂直)
		for (CompomentBean bean : layouts) {
			if (bean.type.contains("Layout")) {
				if (bean.chirlds != null && bean.chirlds.size() > 1) {
					CompomentBean firstChirld = null;
					CompomentBean secondChirld = null;

					int count = 0;
					for (CompomentBean chirld : bean.chirlds) {
						if (count == 0) {
							firstChirld = chirld;
						} else if (count == 1) {
							secondChirld = chirld;
						}
						count++;
					}

					if (firstChirld.x > secondChirld.x) {
						if (firstChirld.x >= secondChirld.x + secondChirld.w) {
							bean.orientation = "horizontal";
							if (bean.type.contains("RelativeLayout")) {
								bean.gravity = "";
							} else if (bean.type.contains("LinearLayout")) {
								bean.gravity = "center_vertical";
							}

						} else {
							bean.orientation = "vertical";
						}
					} else if (firstChirld.x < secondChirld.x) {
						if (secondChirld.x >= firstChirld.x + firstChirld.w) {
							bean.orientation = "horizontal";

							if (bean.type.contains("RelativeLayout")) {
								bean.gravity = "";
							} else if (bean.type.contains("LinearLayout")) {
								bean.gravity = "center_vertical";
							}

						} else {
							bean.orientation = "vertical";
						}
					} else if (firstChirld.x == secondChirld.x) {
						bean.orientation = "vertical";

					}

				} else {
					bean.orientation = "horizontal";
					if (bean.type.contains("RelativeLayout")) {
						bean.gravity = "";
					} else if (bean.type.contains("LinearLayout")) {
						bean.gravity = "center_vertical";
					}

				}
			}

		}

		// RelativeLayout 儿子们的位置关系
		for (CompomentBean bean : layouts) {
			if (bean.type.equals("RelativeLayout")) {
				if (bean.chirlds != null && bean.chirlds.size() > 1) {

					if (bean.chirlds.size() == 2) {
						if (bean.orientation.equals("horizontal")) {

							CompomentBean chirld1 = bean.chirlds.get(0);
							CompomentBean chirld2 = bean.chirlds.get(1);
							if (chirld1.x < chirld2.x) {
								chirld1.relative += " android:layout_alignParentLeft=\"true\"  ";
								chirld2.relative += " android:layout_alignParentRight=\"true\"  ";
							} else if (chirld1.x > chirld2.x) {
								chirld2.relative += " android:layout_alignParentLeft=\"true\"  ";
								chirld1.relative += " android:layout_alignParentRight=\"true\"  ";
							}

							chirld1.relative += " android:layout_centerVertical=\"true\" ";

							chirld2.relative += " android:layout_centerVertical=\"true\" ";

						} else if (bean.orientation.equals("vertical")) {
							CompomentBean chirld1 = bean.chirlds.get(0);
							CompomentBean chirld2 = bean.chirlds.get(1);
							if (chirld1.y < chirld2.y) {
								chirld1.relative += " android:layout_alignParentTop=\"true\"   ";
								chirld2.relative += " android:layout_alignParentBottom=\"true\"  ";
							} else if (chirld1.y > chirld2.y) {

								chirld2.relative += " android:layout_alignParentTop=\"true\"  ";
								chirld1.relative += " android:layout_alignParentBottom=\"true\"  ";

							}

							chirld1.relative += " android:layout_centerHorizontal=\"true\" ";

							chirld2.relative += " android:layout_centerHorizontal=\"true\" ";

						}

					} else if (bean.chirlds.size() == 3) {

						if (bean.orientation.equals("horizontal")) {

							Collections.sort(bean.chirlds, comparatorX);

							int i = 0;
							for (CompomentBean chirld : bean.chirlds) {
								if (i == 0) {
									chirld.relative += " android:layout_alignParentLeft=\"true\" ";

								} else if (i == 1) {
									chirld.relative += " android:layout_centerInParent=\"true\"  ";

								} else if (i == 2) {
									chirld.relative += " android:layout_alignParentRight=\"true\"  ";
								}

								chirld.relative += " android:layout_centerVertical=\"true\"";

								i++;
							}

							Collections.sort(bean.chirlds, comparatorDate);
						} else if (bean.orientation.equals("vertical")) {
							Collections.sort(bean.chirlds, comparatorY);

							int i = 0;
							for (CompomentBean chirld : bean.chirlds) {
								if (i == 0) {
									chirld.relative += " android:layout_alignParentTop=\"true\"  ";

								} else if (i == 1) {
									chirld.relative += " android:layout_centerInParent=\"true\"  ";

								} else if (i == 2) {
									chirld.relative += " android:layout_alignParentBottom=\"true\"  ";
								}

								if (chirld.type.contains("Layout")) {
									chirld.relative += " android:layout_centerHorizontal=\"true\"";
								}
								i++;
							}

							Collections.sort(bean.chirlds, comparatorDate);
						}

					} else if (bean.chirlds.size() > 3) {

						if (bean.orientation.equals("horizontal")) {
							Collections.sort(bean.chirlds, comparatorX);

							int i = 0;
							CompomentBean toRightOf = null;
							for (CompomentBean chirld : bean.chirlds) {
								if (i == 0) {
									chirld.relative += " android:layout_alignParentLeft=\"true\" android:layout_centerVertical=\"true\" ";
									toRightOf = chirld;
								} else if (i == bean.chirlds.size() - 1) {
									chirld.relative += " android:layout_alignParentRight=\"true\" android:layout_centerVertical=\"true\" ";
								} else {
									chirld.relative += " android:layout_toRightOf=\"@id/"
											+ toRightOf.enname
											+ "\" android:layout_centerVertical=\"true\" ";
									toRightOf = chirld;
								}
								i++;
							}

							Collections.sort(bean.chirlds, comparatorDate);
						} else if (bean.orientation.equals("vertical")) {
							Collections.sort(bean.chirlds, comparatorY);
							int i = 0;
							CompomentBean below = null;
							for (CompomentBean chirld : bean.chirlds) {
								if (i == 0) {
									chirld.relative += " android:layout_alignParentTop=\"true\" android:layout_centerHorizontal=\"true\" ";
									below = chirld;
								} else if (i == bean.chirlds.size() - 1) {
									chirld.relative += " android:layout_alignParentBottom=\"true\" android:layout_centerHorizontal=\"true\" ";
								} else {
									chirld.relative += " android:layout_below=\"@id/"
											+ below.enname
											+ "\" android:layout_centerHorizontal=\"true\" ";

									below = chirld;
								}
								i++;
							}

							Collections.sort(bean.chirlds, comparatorDate);
						}
					}

				}
			}
		}

		m += "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
		m += "<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n";
		m += "android:layout_width=\"match_parent\"\n";
		m += "android:layout_height=\"match_parent\"\n";
		m += "android:orientation=\"vertical\" >\n";

		xml(maxBean);

		m += "</LinearLayout>\n\n\n\n";

		//String xmlFileName = FileUtil.makeFile(KeyValue.readCache("picPath"),"xml", filename, "xml", m);
		
		System.out.println(m);
		
		
		return m;
	}

	public void xml(CompomentBean bean) {

		String width = "";
		String height = "";
		if ((maxW - bean.w >= 0) && (maxW - bean.w < 100)) {
			width = "fill_parent";
		} else {
			width = "wrap_content";
		}

		if ((maxH - bean.h >= 0) && (maxH - bean.h < 100)) {
			height = "fill_parent";
		} else {
			height = "wrap_content";
		}

		// if (bean.chirlds != null && bean.chirlds.size() > 0) {
		 if (bean.type.contains("Layout")) {
			if(bean.type.contains("DrawerLayout"))
			{
				m += "<android.support.v4.widget." + bean.type + " android:id=\"@+id/"
					+ bean.enname+"\" android:layout_width=\"" + width
						+ "\"  android:layout_height=\"" + height
						+ "\"    android:background=\"" + bean.bgRgb16.trim()
						+ "\"  >\n   <!-- The main content view 主页布局--> \n";
			}
			else if (bean.bgRgb16.contains("颜色")) {
				m += "<" + bean.type + "  android:layout_width=\"" + width
						+ "\"  android:layout_height=\"" + height
						+ "\"    android:background=\"#" + "ffffff"
						+ "\"  android:orientation=\"" + bean.orientation
						+ "\"  >\n";
			} else {
				m += "<" + bean.type + "  android:layout_width=\"" + width
						+ "\"  android:layout_height=\"" + height
						+ "\"    android:background=\"" + bean.bgRgb16.trim()
						+ "\"   android:orientation=\"" + bean.orientation
						+ "\"  " + bean.relative + "android:gravity=\""
						+ bean.gravity + "\">\n";
			}

		}
		// } else {
		if (bean.type.equals("TextView")) {
			m += "<TextView   android:id=\"@+id/"
					+ bean.enname
					+ "\" android:layout_width=\""
					+ width
					+ "\"  android:layout_height=\""
					+ height
					+ "\"  android:layout_marginLeft=\"5dp\" android:layout_marginRight=\"5dp\" android:layout_marginTop=\"5dp\" android:layout_marginBottom=\"5dp\" android:text=\""
					+ bean.cnname + "\"   android:textSize=\"" + bean.textSize
					+ "sp\" android:textColor=\"" + bean.rgb16
					+ "\"   android:gravity=\"center\" " + bean.relative
					+ ">\n";
			m += "</TextView>\n";
		}

		if (bean.type.equals("Button")) {
			m += "<Button   android:id=\"@+id/"
					+ bean.enname
					+ "\" android:layout_width=\""
					+ width
					+ "\"  android:layout_height=\""
					+ height
					+ "\"  android:layout_marginLeft=\"10dp\" android:layout_marginRight=\"10dp\" android:layout_marginTop=\"5dp\" android:layout_marginBottom=\"5dp\" android:text=\""
					+ bean.cnname + "\"   android:textSize=\"" + bean.textSize
					+ "sp\" android:textColor=\"" + bean.rgb16
					+ "\"   android:background=\"" + bean.bgRgb16 + "\"  "
					+ bean.relative + ">\n";
			m += "</Button>\n";
		}

		if (bean.type.equals("EditText")) {
			m += "<EditText   android:id=\"@+id/"
					+ bean.enname
					+ "\" android:layout_width=\""
					+ width
					+ "\"  android:layout_height=\""
					+ height
					+ "\"  android:layout_marginLeft=\"5dp\" android:layout_marginRight=\"5dp\" android:layout_marginTop=\"5dp\" android:layout_marginBottom=\"5dp\" android:hint=\""
					+ bean.cnname
					+ "\"   android:textSize=\""
					+ bean.textSize
					+ "sp\" android:textColor=\""
					+ bean.rgb16
					+ "\"   android:gravity=\"center\"   android:singleLine=\"true\"   android:maxLength=\"40\"  "
					+ bean.relative + ">\n";
			m += "</EditText>\n";
		}

		if (bean.type.equals("CheckBox")) {
			m += "<CheckBox   android:id=\"@+id/"
					+ bean.enname
					+ "\" android:layout_width=\""
					+ width
					+ "\"  android:layout_height=\""
					+ height
					+ "\"  android:layout_marginLeft=\"5dp\" android:layout_marginRight=\"5dp\" android:layout_marginTop=\"5dp\" android:layout_marginBottom=\"5dp\" android:text=\""
					+ bean.cnname + "\"   android:textSize=\"" + bean.textSize
					+ "sp\" android:textColor=\"" + bean.rgb16 + "\"   "
					+ bean.relative + ">\n";
			m += "</CheckBox>\n";
		}

		if (bean.type.equals("ListView")) {
			m += "<ListView   android:id=\"@+id/"
					+ bean.enname
					+ "\" android:layout_width=\""
					+ width
					+ "\"  android:layout_height=\""
					+ height
					+ "\"  android:layout_marginLeft=\"10dp\" android:layout_marginRight=\"10dp\" android:layout_marginTop=\"5dp\" android:layout_marginBottom=\"5dp\""
					+ " android:cacheColorHint=\"@null\" android:divider=\""
					+ bean.rgb16
					+ "\"  android:dividerHeight=\"0.5dip\" android:fadingEdge=\"none\"  android:fastScrollEnabled=\"false\" "
					+ bean.relative + ">\n";
			m += "</ListView>\n";
		}

		if (bean.type.equals("ImageView")) {
			m += "<ImageView   android:id=\"@+id/"
					+ bean.enname
					+ "\" android:layout_width=\""
					+ width
					+ "\"  android:layout_height=\""
					+ height
					+ "\"  android:layout_marginLeft=\"5dp\" android:layout_marginRight=\"5dp\" android:layout_marginTop=\"5dp\" android:layout_marginBottom=\"5dp\" "

					+ "  android:background=\"@drawable/" + bean.picName
					+ "\"  " + bean.relative + ">\n";
			m += "</ImageView>\n";
		}

		if (bean.type.equals("ExpandableListView")) {
			m += "<ExpandableListView   android:id=\"@+id/"
					+ bean.enname
					+ "\" android:layout_width=\""
					+ width
					+ "\"  android:layout_height=\""
					+ height
					+ "\"  android:layout_marginLeft=\"10dp\" android:layout_marginRight=\"10dp\" android:layout_marginTop=\"5dp\" android:layout_marginBottom=\"5dp\""
					+ " android:cacheColorHint=\"@null\" android:groupIndicator=\"@null\" android:divider=\""
					+ bean.rgb16
					+ "\"  android:dividerHeight=\"0.5dip\" android:childDivider=\""
					+ bean.rgb16
					+ "\"   android:fadingEdge=\"none\"  android:fastScrollEnabled=\"false\" "
					+ bean.relative + ">\n";
			m += "</ExpandableListView>\n";
		}
		
		if (bean.type.equals("Line")) {
			m+=" <View android:layout_width=\"fill_parent\" android:layout_height=\"1dip\" android:background=\""+bean.bgRgb16+"\" />\n";

		}
		// }

		if (bean.chirlds != null && bean.chirlds.size() > 0) {
			for (CompomentBean chirld : bean.chirlds) {
				xml(chirld);
			}

		}

		// if (bean.chirlds != null && bean.chirlds.size() > 0) {
		if (bean.type.contains("Layout")) {
			if(bean.type.contains("DrawerLayout"))
			{
				m+="         <!-- The navigation drawer 左抽屉 由ActivityChirld提供 -->\n";
				m+="         <LinearLayout\n";
				m+="            android:id=\"@+id/leftDrawerContain\"\n";
				m+="            android:layout_width=\"240dp\"\n";
				m+="            android:layout_gravity=\"start\" \n";
				m+="            android:background=\"#111\"\n";
				m+="            android:layout_height=\"fill_parent\"\n";
				m+="            android:orientation=\"vertical\"\n";
				m+="             >\n";
				m+="        </LinearLayout>\n";
				m+="    </android.support.v4.widget."+bean.type+">\n";
			}else
			{
			m += "</" + bean.type + ">\n";
			}
		}
		// }
	}
	
	


	Comparator<CompomentBean> comparatorX = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 先排年龄
			if (s1.x != s2.x) {
				return s1.x - s2.x;
			}
			return 0;
		}
	};

	Comparator<CompomentBean> comparatorY = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 先排年龄
			if (s1.y != s2.y) {
				return s1.y - s2.y;
			}
			return 0;
		}
	};

	Comparator<CompomentBean> comparatorDate = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 先排年龄
			if (s1.time != s2.time) {
				return (int) (s1.time - s2.time);
			}
			return 0;
		}
	};

}
