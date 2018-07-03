package com.compoment.cut.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;

import com.compoment.cut.CompomentBean;

//http://stevencjh.blog.163.com/blog/static/1218614612010101775336729/
//http://www.cnblogs.com/lionden/archive/2012/12/11/grouplayout.html
public class SwingLayout {

	String m = "\n\n\n";

	public void createJFrame(List<CompomentBean> oldBeans,
			List<CompomentBean> newBeans) {

		// JFrame
		m += "import java.awt.*;\n";
		m += "import java.awt.event.*;\n";
		m += " import javax.swing.*;\n";

		m += " public class Frame extends JFrame\n";
		m += " {\n\n";
		
		for(CompomentBean bean:oldBeans)
		{
			m+="public "+typeChange(bean.type)+" "+bean.enname+"\n;";
		}
		
		m += " public Frame()\n";
		m += " {\n\n";
		m += "super(\"\");\n";
		m += " setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n";

		m += "String laf = UIManager.getSystemLookAndFeelClassName();\n";

		m += " try {\n";
		m += " UIManager.setLookAndFeel(laf);\n";
		m += " } catch (UnsupportedLookAndFeelException exc) {\n";

		m += "} catch (Exception exc) {\n";

		m += "}\n\n";

		m += "Container c = getContentPane();\n";
		m += "GroupLayout layout = new GroupLayout(c);\n";
		m += "c.setLayout(layout);\n";

		// 自动设定组件、组之间的间隙
		m += "layout.setAutoCreateGaps(true);\n";
		m += "layout.setAutoCreateContainerGaps(true);\n\n";

		analyseRelativeForVertical(oldBeans);

		analyseRelativeForSwingHorizontal(newBeans);

		m += " setLocation(200,200);\n";
		m += " pack();\n";
		m += " setVisible(true);\n";
		m += " }\n";
		m += " public static void main(String[] args)\n";
		m += " {\n";
		m += " new Frame();\n";
		m += " }\n";
		m += " }\n";
		System.out.println(m);

	}
	
	public void createJDialog(List<CompomentBean> oldBeans,
			List<CompomentBean> newBeans) {
		m="\n";
		m+="JDialog jdialog = new JDialog(null, \"title\", true);\n";
		m+="jdialog.setSize(800, 800);\n";
		m+="Panel panel=new Panel();\n";
		m+="jdialog.add(panel);\n";
		m+="jdialog.setLocationRelativeTo(null);\n";
		m+="jdialog.setVisible(true);\n";
		System.out.println(m);
		createJPanel(oldBeans,newBeans);
	}
	

	public void createJPanel(List<CompomentBean> oldBeans,
			List<CompomentBean> newBeans) {
		// JPanel
		m="\n";
		m += " public class Panel \n";
		m += " {\n\n";
		
		for(CompomentBean bean:oldBeans)
		{
			if(!typeChange(bean.type).equals(""))
			{
				m+="/** "+bean.cnname+"*/\n";
			m+="public "+typeChange(bean.type)+" "+bean.enname+";\n";
			}
		}
		
		m+="public JPanel create() {\n";

		m+="	JPanel panel = new JPanel();\n";
		m+="	GroupLayout layout = new GroupLayout(panel);\n";
		m+="	panel.setLayout(layout);\n";
		// 自动设定组件、组之间的间隙
		m+="	layout.setAutoCreateGaps(true);\n";
		m+="	layout.setAutoCreateContainerGaps(true);\n";
		
		analyseRelativeForVertical(oldBeans);

		analyseRelativeForSwingHorizontal(newBeans);

		m+="return panel;\n";
		m+="}\n";
		m+="}\n";
		System.out.println(m);
	}

	public void analyseRelativeForVertical(List<CompomentBean> oldBeans) {
		// Collections.sort(oldBeans, comparatorDate);
		m += "//垂直\n";
		int maxW = 0;
		int maxH = 0;
		List<CompomentBean> layouts = new ArrayList<CompomentBean>();
		CompomentBean maxBean = null;
		// 找出容器
		for (CompomentBean bean : oldBeans) {
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

		parent(maxBean);

		m += "layout.setVerticalGroup(" + maxBean.enname + ");\n\n";

	}

	public void parent(CompomentBean bean) {

		if (bean.type.contains("Layout")) {

			m += "/**";
			if (bean.chirlds != null && bean.chirlds.size() > 0) {
				for (CompomentBean chirld : bean.chirlds) {
					m += chirld.enname + " ";
				}
			}
			m += "*/\n";
			// 水平
			if (bean.orientation.equals("horizontal")) {
				m += "GroupLayout.ParallelGroup " + bean.enname
						+ " = layout.createParallelGroup();\n";
			} else if (bean.orientation.equals("vertical")) {
				m += "GroupLayout.SequentialGroup " + bean.enname
						+ " = layout.createSequentialGroup();\n";
			}
		}

		if (bean.chirlds != null && bean.chirlds.size() > 0) {
			for (CompomentBean chirld : bean.chirlds) {

				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					parent(chirld);
					m += bean.enname + ".addGroup(" + chirld.enname + ");\n\n\n";
				} else {
					chirld(chirld, bean);
				}
			}

		}

	}

	public void chirld(CompomentBean chirld, CompomentBean parent) {

		if (chirld.type.equals("TextView")) {
			m += "/**" + chirld.cnname + "*/\n";
			m += " " + chirld.enname + " = new JLabel(\"" + chirld.cnname
					+ "\");\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";
		}

		if (chirld.type.equals("Button")) {
			m += "/**" + chirld.cnname + "*/\n";
			m += " " + chirld.enname + " = new JButton(\""
					+ chirld.cnname + "\");\n";
			
			m+=chirld.enname +".addActionListener(new ActionListener() {\n";
			m+="public void actionPerformed(ActionEvent event) {\n";
			m+="}\n";
			m+="});\n";
			
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";
		}

		if (chirld.type.equals("EditText")) {
			m += "/**" + chirld.cnname + "*/\n";
			m += " " + chirld.enname + " = new JTextField(\""
					+ chirld.cnname + "\");\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";

		
		
			m+= chirld.enname+".getDocument().addDocumentListener(new DocumentListener() {\n";
			m+="public void changedUpdate(DocumentEvent e) {\n";
				m+="}\n";
				m+="public void insertUpdate(DocumentEvent e) {\n";
				m+="String s = "+chirld.enname+".getText();\n";
				m+="}\n";
				m+="public void removeUpdate(DocumentEvent arg0) {\n";
				m+="}\n";
			m+="});\n";
			
		}

		if (chirld.type.equals("CheckBox")) {
			m += "/**" + chirld.cnname + "*/\n";
			m += " " + chirld.enname + " = new JCheckBox(\""
					+ chirld.cnname + "\");\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";

		}

		if (chirld.type.equals("ListView")) {
			m += "/**" + chirld.cnname + "*/\n";
			m += " " + chirld.enname + " = new JList();\n";
			m+=" JScrollPane "+chirld.enname +"ScrollPane = new JScrollPane("+chirld.enname +"); \n";
				
			m += " ArrayList listDate = new ArrayList();\n";
			m += " listDate.add(\"RelativeLayout\");\n";
			m += " listDate.add(\"LinearLayout\");\n";
			m += chirld.enname
					+ ".setSelectionMode(ListSelectionModel.SINGLE_SELECTION);\n";
			m += chirld.enname + ".setListData(listDate.toArray());\n";
			m += chirld.enname
					+ ".addListSelectionListener(new ListSelectionListener() {\n";

			m += "	@Override\n";
			m += "	public void valueChanged(ListSelectionEvent even) {\n";

			m += "	String value = " + chirld.enname
					+ ".getSelectedValue().toString();\n";
			m += "	}\n";

			m += "});\n";

			m += parent.enname + ".addComponent(" + chirld.enname + "ScrollPane);\n\n";
		}

		if (chirld.type.equals("JPanel")) {
			m += "/**" + chirld.cnname + "*/\n";
			m += " " + chirld.enname + " = new JPanel();\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";
		}

		if (chirld.type.equals("ExpandableListView")) {

		}
	}

	public void analyseRelativeForSwingHorizontal(List<CompomentBean> newBeans) {

		m += "//水平\n";
		int maxW = 0;
		int maxH = 0;

		List<CompomentBean> layouts = new ArrayList<CompomentBean>();
		CompomentBean maxBean = null;
		// 找出容器
		for (CompomentBean bean : newBeans) {
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

		if (maxBean == null)
			return;

		// 儿子找父亲 （子控件找容器）
		for (CompomentBean bean : newBeans) {
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
						} else {
							bean.orientation = "vertical";
						}
					} else if (firstChirld.x < secondChirld.x) {
						if (secondChirld.x >= firstChirld.x + firstChirld.w) {
							bean.orientation = "horizontal";
						} else {
							bean.orientation = "vertical";
						}
					} else if (firstChirld.x == secondChirld.x) {
						bean.orientation = "vertical";
					}

				} else {
					bean.orientation = "horizontal";
				}
			}

		}

		parentForHorizontal(maxBean);

		m += "layout.setHorizontalGroup(" + maxBean.enname + ");\n\n";

	}

	public void parentForHorizontal(CompomentBean bean) {

		if (bean.type.contains("Layout")) {
			m += "/**";
			if (bean.chirlds != null && bean.chirlds.size() > 0) {
				for (CompomentBean chirld : bean.chirlds) {
					m += chirld.enname + " ";
				}
			}
			m += "*/\n";
			// 水平
			if (bean.orientation.equals("horizontal")) {
				m += "GroupLayout.SequentialGroup " + bean.enname
						+ " = layout.createSequentialGroup();\n";
			} else if (bean.orientation.equals("vertical")) {

				m += "GroupLayout.ParallelGroup " + bean.enname
						+ " = layout.createParallelGroup();\n";

			}
		}

		if (bean.chirlds != null && bean.chirlds.size() > 0) {
			for (CompomentBean chirld : bean.chirlds) {

				if (chirld.chirlds != null && chirld.chirlds.size() > 0) {

					parentForHorizontal(chirld);
					m += bean.enname + ".addGroup(" + chirld.enname
							+ ");\n\n\n";
				} else {
					chirldForHorizontal(chirld, bean);
				}
			}

		}

	}

	public void chirldForHorizontal(CompomentBean chirld, CompomentBean parent) {

		if (chirld.type.equals("TextView")) {
			m += "/**" + chirld.cnname + "*/\n";
			// m += "JLabel " + chirld.enname + " = new JLabel(\"" +
			// chirld.cnname
			// + "\");\n\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";
		}

		if (chirld.type.equals("Button")) {
			m += "/**" + chirld.cnname + "*/\n";
			// m += "JButton " + chirld.enname + " = new JButton(\""
			// + chirld.cnname + "\");\n\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";
		}

		if (chirld.type.equals("EditText")) {
			m += "/**" + chirld.cnname + "*/\n";
			// m += "JTextField " + chirld.enname + " = new JTextField(\""
			// + chirld.cnname + "\");\n\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";

		}

		if (chirld.type.equals("CheckBox")) {
			m += "/**" + chirld.cnname + "*/\n";
			// m += "JCheckBox " + chirld.enname + " = new JCheckBox(\""
			// + chirld.cnname + "\");\n\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";

		}

		if (chirld.type.equals("ListView")) {
			m += "/**" + chirld.cnname + "*/\n";
			// m += "JList " + chirld.enname + " = new JList(\"" + chirld.cnname
			// + "\");\n\n";
			m += parent.enname + ".addComponent(" + chirld.enname + "ScrollPane);\n\n";
		}

		if (chirld.type.equals("JPanel")) {
			m += "/**" + chirld.cnname + "*/\n";
			// m += "JPanel " + chirld.enname + " = new JPanel();\n";
			m += parent.enname + ".addComponent(" + chirld.enname + ");\n\n";
		}

		if (chirld.type.equals("ExpandableListView")) {

		}
	}
	
	
	
	
	public String typeChange(String type) {

		if (type.equals("TextView")) {
			
			return "JLabel";
		}

		if (type.equals("Button")) {
			
			return  "JButton";
		}

		if (type.equals("EditText")) {
			return "JTextField";
		}

		if (type.equals("CheckBox")) {
			
			return  "JCheckBox" ;
		}

		if (type.equals("ListView")) {
			
			return "JList" ;
		}

		if (type.equals("JPanel")) {
			return "JPanel" ;
		}
		return "";
	}


	Comparator<CompomentBean> comparatorDate = new Comparator<CompomentBean>() {
		public int compare(CompomentBean s1, CompomentBean s2) {
			// 先排年龄
			if (s1.time != s2.time) {
				return (int) (s2.time - s1.time);
			}
			return 0;
		}
	};
}
