 package com.compoment.workflow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.compoment.cut.ColorPanel;
import com.compoment.cut.CompomentBean;
import com.compoment.cut.EdgeDetector;
import com.compoment.cut.CompomentDialog.CompomentDialogCallBack;
import com.compoment.cut.EdgeDetector.EdgeDetectorException;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.jsonToJava.creater.WordtableToJavaObject.*;
import com.compoment.util.FileUtil;
import com.compoment.util.ImageUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.SerializeToFile;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;

public class CompomentDialog2 extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField cnNameEdit;
	private JTextField enNameEdit;
	private JTextField colorEdit;
	private JTextField bgColorEdit;
	private JTextField picNameEdit;
	private JTextField textSizeEdit;
	JButton ok;
	JCheckBox imgCacheCheckBox;
	JCheckBox setPublicCheckBox;
	JCheckBox circularCheckBox;
	//"是否九宫格"
	JCheckBox isNineListCheck ;
	//"是否分页"
	JCheckBox isMutiPageListCheck;
	
	JButton cancel;
	JList baseListListView;
	JList webCompomentListView;
	JButton gaveBgColor;
	JScrollPane basePicScrollPane;
	JList interfaceList;
	JList interfaceColumnList;
	JList actionList;
	JComboBox jumpToViewComboBox;
	JCheckBox isRunTimeHeight;
	String compomentType;
	String webCompomentType = "";
	ArrayList listDate = null;
	JFrame frame;

	List<InterfaceBean> tempInterfaceBeans;
	String interfaceid;
	String interfaceColumnEnName;
	String actionString;
	String actionDetailString;
	String jumpToWhichPage;

	JCheckBox runTimeAddScrollView;
	private JLabel colorLabel;

	JButton color1Btn;
	JButton color2Btn;
	JButton color3Btn;
	JButton color4Btn;
	JButton color5Btn;
	JButton color6Btn;
	private JPanel webCompomentPanel;
	private JLabel lblWeb;
	JCheckBox layoutNoUseBool;
	List<InterfaceBean> interfaceBeans;
	
	JCheckBox sameGroupWithBeforCompomentCheckbox;
	
	List<CompomentBean> allCompoments;
	CompomentBean bean = new CompomentBean();
	
	String pageName;
	int runTimeAddCount=0;
	JList borderList;
	String compmentBorderForWeb;
	String compmentExpandForWeb;
	JList expandList;
	String pageType;//android ios  web webmanage
	JPanel borderPanel;
	JPanel expandPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CompomentDialog2 dialog = new CompomentDialog2(null,null,null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CompomentDialog2(List<InterfaceBean> interfaceBeans,List<CompomentBean> allCompoments,final String pageName,final String pageType) {
		this.interfaceBeans = interfaceBeans;
		this.allCompoments=allCompoments;
		this.pageName=pageName;
		this.pageType=pageType;
		setBounds(100, 100, 1170, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel baseTitleTextView = new JLabel("基本组件");

		basePicScrollPane = new JScrollPane();

		interfaceList = new JList();

		JLabel label_1 = new JLabel("输出参数");

		JLabel label_2 = new JLabel("事件");
		interfaceColumnList = new JList();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(interfaceColumnList);

		jumpToViewComboBox = new JComboBox();

		JLabel label = new JLabel("跳到的页面");

		JPanel panel = new JPanel();
		panel.setForeground(Color.LIGHT_GRAY);

		JPanel panel_1 = new JPanel();

		webCompomentPanel = new JPanel();
		
		JLabel lblNewLabel = new JLabel("输入参数");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		 borderPanel = new JPanel();
		
		JLabel label_3 = new JLabel("边框");
		
		JScrollPane borderScrollPane = new JScrollPane();
		GroupLayout gl_borderPanel = new GroupLayout(borderPanel);
		gl_borderPanel.setHorizontalGroup(
			gl_borderPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 136, Short.MAX_VALUE)
				.addGroup(gl_borderPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_borderPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(borderScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
						.addComponent(label_3))
					.addContainerGap())
		);
		gl_borderPanel.setVerticalGroup(
			gl_borderPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 126, Short.MAX_VALUE)
				.addGroup(gl_borderPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(borderScrollPane, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
					.addContainerGap())
		);
		
	     borderList = new JList();
		borderScrollPane.setViewportView(borderList);
		borderPanel.setLayout(gl_borderPanel);
		
		 expandPanel = new JPanel();
		
		JScrollPane scrollPane_4 = new JScrollPane();
		
		JLabel label_5 = new JLabel("扩大");
		GroupLayout gl_expandPanel = new GroupLayout(expandPanel);
		gl_expandPanel.setHorizontalGroup(
			gl_expandPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 136, Short.MAX_VALUE)
				.addGroup(gl_expandPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_expandPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
						.addComponent(label_5))
					.addContainerGap())
		);
		gl_expandPanel.setVerticalGroup(
			gl_expandPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 126, Short.MAX_VALUE)
				.addGroup(gl_expandPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_5)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		 expandList = new JList();
		scrollPane_4.setViewportView(expandList);
		expandPanel.setLayout(gl_expandPanel);

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(baseTitleTextView)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(panel, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
												.addComponent(basePicScrollPane, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))))
									.addGap(12)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addGap(165)
											.addComponent(label_1))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(interfaceList, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(label)
										.addComponent(label_2)
										.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
										.addComponent(jumpToViewComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 1208, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(webCompomentPanel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(borderPanel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(expandPanel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)))
					.addGap(37))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(baseTitleTextView)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(label_2)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGap(19)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
									.addComponent(interfaceList, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(basePicScrollPane, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(expandPanel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
								.addComponent(borderPanel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
								.addComponent(webCompomentPanel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jumpToViewComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(74, Short.MAX_VALUE))
		);
		
				baseListListView = new JList();
				scrollPane_2.setViewportView(baseListListView);
		
				actionList = new JList();
				scrollPane_1.setViewportView(actionList);

		lblWeb = new JLabel("位置（左中右）");
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_webCompomentPanel = new GroupLayout(webCompomentPanel);
		gl_webCompomentPanel.setHorizontalGroup(
			gl_webCompomentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_webCompomentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_webCompomentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
						.addComponent(lblWeb))
					.addContainerGap())
		);
		gl_webCompomentPanel.setVerticalGroup(
			gl_webCompomentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_webCompomentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWeb)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
					.addContainerGap())
		);
		
				webCompomentListView = new JList();
				scrollPane_3.setViewportView(webCompomentListView);
		webCompomentPanel.setLayout(gl_webCompomentPanel);

		cnNameEdit = new JTextField();
		cnNameEdit.setText("中文名");
		cnNameEdit.setColumns(10);

		enNameEdit = new JTextField();
		enNameEdit.setText("英文名");
		enNameEdit.setColumns(10);

		picNameEdit = new JTextField();
		picNameEdit.setText("图片名");
		picNameEdit.setColumns(10);

		textSizeEdit = new JTextField();
		textSizeEdit.setText("字体大小");
		textSizeEdit.setColumns(10);

		
		runTimeAddScrollView = new JCheckBox("是否动态加入ScrollView(独立一个Cell页面)IOS");
		runTimeAddScrollView.setVisible(false);
		runTimeAddScrollView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (runTimeAddScrollView.isSelected()) {
					cnNameEdit.setText(pageName+"Part"+runTimeAddCount);
					enNameEdit.setText(pageName+"Part"+runTimeAddCount);
					runTimeAddCount++;
				}
			}
		});

		circularCheckBox = new JCheckBox("是否圆角(Android)");
		circularCheckBox.setVisible(false);

		imgCacheCheckBox = new JCheckBox("是否二级缓存");
		imgCacheCheckBox.setVisible(false);

		setPublicCheckBox = new JCheckBox("是否公共组件");
		setPublicCheckBox.setVisible(false);

		isRunTimeHeight = new JCheckBox("是否动态高度(IOS)");
		isRunTimeHeight.setVisible(false);

		ok = new JButton("ok");

		cancel = new JButton("cancel");

		layoutNoUseBool = new JCheckBox("使布局无效ForIos(隐藏布局留里边子控件)");
		layoutNoUseBool.setVisible(false);
		
		 sameGroupWithBeforCompomentCheckbox = new JCheckBox("同上一组");
		
		 isNineListCheck = new JCheckBox("是否九宫格");
		
		 isMutiPageListCheck = new JCheckBox("是否分页");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(sameGroupWithBeforCompomentCheckbox)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cnNameEdit, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(enNameEdit, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(picNameEdit, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textSizeEdit, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(runTimeAddScrollView)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(274)
											.addComponent(isNineListCheck)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(isMutiPageListCheck)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(ok)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(cancel))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(circularCheckBox)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(imgCacheCheckBox)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(setPublicCheckBox)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(isRunTimeHeight))))))
						.addComponent(layoutNoUseBool))
					.addContainerGap(184, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(cnNameEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(enNameEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(picNameEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textSizeEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(sameGroupWithBeforCompomentCheckbox))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(runTimeAddScrollView)
						.addComponent(circularCheckBox)
						.addComponent(imgCacheCheckBox)
						.addComponent(setPublicCheckBox)
						.addComponent(isRunTimeHeight))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(ok)
						.addComponent(cancel))
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(69)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(layoutNoUseBool)
						.addComponent(isNineListCheck)
						.addComponent(isMutiPageListCheck))
					.addGap(6))
		);
		panel_1.setLayout(gl_panel_1);

		color1Btn = new JButton("");
		color1Btn.setVisible(false);

		color2Btn = new JButton("");
		color2Btn.setVisible(false);
		color3Btn = new JButton("");
		color3Btn.setVisible(false);

		color4Btn = new JButton("");
		color4Btn.setVisible(false);

		color5Btn = new JButton("");
		color5Btn.setVisible(false);

		color6Btn = new JButton("");
		color6Btn.setVisible(false);

		colorLabel = new JLabel("颜色最大值");

		colorEdit = new JTextField();
		colorEdit.setForeground(Color.WHITE);
		colorEdit.setText("边框色");
		colorEdit.setColumns(10);

		gaveBgColor = new JButton("->");

		bgColorEdit = new JTextField();
		bgColorEdit.setText("背景色");
		bgColorEdit.setColumns(10);
		
		JLabel label_4 = new JLabel("背景色");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(color1Btn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(color2Btn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(color3Btn))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(colorLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(colorEdit, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(color4Btn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(color5Btn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(color6Btn))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(gaveBgColor, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(bgColorEdit, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(170, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(color1Btn)
						.addComponent(color2Btn)
						.addComponent(color3Btn))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(color4Btn)
						.addComponent(color5Btn)
						.addComponent(color6Btn))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(colorEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(colorLabel))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(gaveBgColor)
						.addComponent(label_4)
						.addComponent(bgColorEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);

		contentPanel.setLayout(gl_contentPanel);
	}

	public void init(final CompomentDialogCallBack implementInterfaceFrame, JFrame frame, final Image image,
			final int x, final int y, final int w, final int h, String pageType) {
		this.frame = frame;
		ArrayList components=new ArrayList();
		if(pageType.contains("Android"))
		{
			components.clear();
			
			components.add("RelativeLayout");
			components.add("LinearLayout");
			components.add("DrawerLayout");
			components.add("Line");
			
			components.add("TextView");
			components.add("Button");
			components.add("CheckBox");
			components.add("EditText");
		
			components.add("ImageView");
			components.add("ListView");
			components.add("ExpandableListView");
			components.add("Spinner");
		}else if(pageType.contains("IOS"))
		{
			components.clear();
			
			
			components.add("LinearLayout");
			components.add("ScrollViewLayout");
			components.add("Line");
			
			components.add("TextView");
			components.add("Button");
			components.add("CheckBox");
			components.add("EditText");
		
			components.add("ImageView");
			components.add("ListView");
		

		}
		else if(pageType.contains("Web"))
		{
			components.clear();
			components.add("DivLayout_Horizon");	
			components.add("DivLayout_Vertical");
			
			
			
			components.add("FormLayout");
			components.add("Form_ItemLayout");
		
			components.add("ListLayout");
			components.add("List_ItemLayout_Horizon");
			components.add("List_ItemLayout_Vertical");
		
			
			components.add("DialogLayout");
			components.add("TabLayout");
			components.add("SliderLayout");//轮播
			components.add("ActionSheetLayout");// 底部弹出菜单
			
			components.add("TextView");
			components.add("TextView_MutiLine");
			components.add("Span");
			components.add("H1-9");
			components.add("Label");
			
			components.add("EditText");
			components.add("EditTextMutiLine");
			
			components.add("Button");
			components.add("Button_Close");
			components.add("Button_Query");
			components.add("Button_A");
			components.add("leftArrow");
			components.add("rightArrow");
			components.add("CheckBox");
			components.add("CheckBox_Switch");
			components.add("Radio");
			components.add("File");
			
			components.add("PopTips");
			
			
			components.add("Progress");
			
			components.add("Loading");
			
			components.add("ImageView");
			
			components.add("Selecter");
			components.add("DateSelecter");
			
			components.add("ExpandableListView");
			
		}
		
		else if(pageType.contains("JFrame-Swing"))
		{
			components.clear();
			
			components.add("LinearLayout");
			components.add("JPanel");
			
		}
		else if(pageType.contains("JDialog-Swing"))
		{
			components.clear();
		
			components.add("LinearLayout");
			components.add("TextView");
			components.add("Button");
			components.add("CheckBox");
			components.add("EditText");
			components.add("ImageView");
			components.add("ListView");
			
		}
		else if(pageType.contains("JPanel-Swing"))
		{
			components.clear();
		
			components.add("LinearLayout");
			components.add("TextView");
			components.add("Button");
			components.add("CheckBox");
			components.add("EditText");
			components.add("ImageView");
			components.add("ListView");
			
		}
		setView(implementInterfaceFrame, frame, image, x, y, w, h, components);

	}

	public void setView(final CompomentDialogCallBack implementInterfaceFrame, JFrame frame, final Image image,
			final int x, final int y, final int w, final int h, ArrayList listDate) {

		
		sameGroupWithBeforCompomentCheckbox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			if(sameGroupWithBeforCompomentCheckbox.isSelected()){//chekbox声明为final型才能在内部使用
				if(allCompoments!=null &&allCompoments.size()>0)
				{
					CompomentBean before=allCompoments.get(allCompoments.size()-1);
					enNameEdit.setText(before.enname+"Value");
					bean.interfaceColumnEnName=before.interfaceColumnEnName;
					bean.interfaceId=before.interfaceId;
					before.interfaceColumnEnName=null;
					before.interfaceId=null;
					
					bean.isNineListCheck=isNineListCheck.isSelected();
					bean.isMutiPageListCheck=isMutiPageListCheck.isSelected();
					
					boolean isOkRequest=false;
					int i=0;
					for(int k=0;k<interfaceBeans.size();k++)
					{
					for (Group group : interfaceBeans.get(k).requestGroups) {

						for (Row row : group.rows) {
							
							String column = row.enName + ":" + row.cnName;
							if(row.enName.contains(bean.interfaceColumnEnName)||bean.interfaceColumnEnName.contains(row.enName))
							{
								
								isOkRequest=true;
								interfaceList.setSelectedIndex(i);
							}
							i++;
						}

					}
					}
					boolean isOkRespond=false;
					int j=0;
					for(int k=0;k<interfaceBeans.size();k++)
					{
					for (Group group : interfaceBeans.get(k).respondGroups) {

						for (Row row : group.rows) {
							String column = row.enName + ":" + row.cnName;
							if(row.enName.contains(bean.interfaceColumnEnName)||bean.interfaceColumnEnName.contains(row.enName))
							{
								
								isOkRespond=true;
								interfaceColumnList.setSelectedIndex(j);
								j++;
							}
						}

					}
					}
					
				}else
				{
				enNameEdit.setText("英文名");
				}
			}
			}
			});
		
	
		
		List picnames = searchPics(KeyValue.readCache("picPath"));

		for (int i = 0; i < picnames.size(); i++) {
			if (i == 0) {
				jumpToWhichPage = (String) picnames.get(i);
			}
			jumpToViewComboBox.addItem(picnames.get(i));
		}

		jumpToViewComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				jumpToWhichPage = jumpToViewComboBox.getSelectedItem().toString();
			}
		});

		List actions = new ArrayList();
		actions.add("---Button---");
		actions.add("跳到");
		actions.add("跳回到上个");
		actions.add("跳回到上几个");
		actions.add("单选");
		actions.add("发请求");
		actions.add("弹出");
		actions.add("日期选择器");
		actions.add("省市县选择器");
		actions.add("省简称选择器");
		actions.add("通用选择器");
		
		actions.add("---TextView---");
		actions.add("数字转义成汉字");
		actions.add("时间格式化");
		actions.add("金额格式化");
		

		actionList.setListData(actions.toArray());
		actionList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		actionList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent even) {
				// TODO Auto-generated method stub

				if (actionList.getSelectedValue() == null)
					return;

				String value = actionList.getSelectedValue().toString();

				actionString = value;
			}
		});

		// 接口
		if (interfaceBeans != null) {

			// 输入参数接口字段
			List<String> tempinputs = new ArrayList();

			interfaceList.setListData(tempinputs.toArray());
			interfaceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			interfaceList.addMouseListener(new MouseAdapter(){  
			    public void mouseClicked(MouseEvent e){  
			        if(e.getClickCount()==2){ 
			        	
			
							interfaceList.clearSelection();
							bean.interfaceColumnEnName=null;
							bean.interfaceId=null;
						

			        }else
			        {
			        	interfaceColumnList.clearSelection();
						if (interfaceList.getSelectedValue() == null)
							return;

						String value = interfaceList.getSelectedValue().toString();
						String id = value.split(":")[0];
					
						
						bean.interfaceColumnEnName=id;
						//bean.interfaceId=interfaceBeans.get(0).id;
						

			        }
			        
			    }});
		

			for(int k=0;k<interfaceBeans.size();k++)
			{
			for (Group group : interfaceBeans.get(k).requestGroups) {

				for (Row row : group.rows) {
					String column = row.enName + ":" + row.cnName;
					tempinputs.add(column);
				}

			}}

			interfaceList.setListData(tempinputs.toArray());

			// 输出参数接口字段
			List<String> tempoutputs = new ArrayList();

			interfaceColumnList.setListData(tempoutputs.toArray());
			interfaceColumnList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			interfaceColumnList.addMouseListener(new MouseAdapter(){  
			    public void mouseClicked(MouseEvent e){  
			        if(e.getClickCount()==2){   //When double click JList  
			  
							interfaceColumnList.clearSelection();
							bean.interfaceColumnEnName=null;
							bean.interfaceId=null;
						
			        }else
			        {
			        	interfaceList.clearSelection();
						if (interfaceColumnList.getSelectedValue() == null)
							return;

						String value = interfaceColumnList.getSelectedValue().toString();
						String id = value.split(":")[0];
						
						
						bean.interfaceColumnEnName=id;
						
						//bean.interfaceId=interfaceBeans.get(0).id;
						
			        }
			        
			    }  
			});  


			for(int k=0;k<interfaceBeans.size();k++)
			{
			for (Group group : interfaceBeans.get(k).respondGroups) {

				for (Row row : group.rows) {
					String column = row.enName + ":" + row.cnName;
					tempoutputs.add(column);
				}

			}
			}

			interfaceColumnList.setListData(tempoutputs.toArray());

			CompomentDialog2.this.setVisible(true);

		}

		ColorPanel basePicPanel = new ColorPanel(frame, image, this);

		basePicScrollPane.setViewportView(basePicPanel);

		final Map xyzMap = getNearXYZ(image, x, y, w, h);

		this.listDate = listDate;

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if (compomentType.contains(".xml")) {
					List<CompomentBean> beans = SerializeToFile.deSerializeFromXml(compomentType);// filepath
					for (CompomentBean bean : beans) {
						implementInterfaceFrame.compomentDialogCallBack(bean);
					}
					CompomentDialog2.this.setVisible(false);
					return;
				}

				if (compomentType == null) {
					JOptionPane.showMessageDialog(null, "请选择类型", "", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (cnNameEdit.getText().equals("中文名")) {
					JOptionPane.showMessageDialog(null, "请输入中文名", "", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (enNameEdit.getText().equals("英文名")) {
					JOptionPane.showMessageDialog(null, "请输入英文名", "", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (colorEdit.getText().contains("颜色")) {
					JOptionPane.showMessageDialog(null, "请选择颜色", "", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (compomentType.equals("ImageView") && picNameEdit.getText().contains("图片名")) {
					JOptionPane.showMessageDialog(null, "请输入图片名", "", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				
				bean.actionString = actionString;
				
				
				

				if ("跳到".equals(actionString) && jumpToWhichPage == null) {
					JOptionPane.showMessageDialog(null, "请选择跳到哪个页面", "", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				bean.jumpToWhichPage = jumpToWhichPage;

				
				bean.cnname = cnNameEdit.getText().trim().replace(" ", "");
				bean.enname = (enNameEdit.getText().trim() + compomentType).replace(" ", "");
				bean.rgb16 = "" + colorEdit.getText().trim();
				bean.bgRgb16 = "" + bgColorEdit.getText().trim();

				bean.rgb16ios = "" + colorEdit.getText().trim();
				bean.bgRgb16ios = "" + bgColorEdit.getText().trim();

				bean.compomentForWeb = webCompomentType;
				bean.compmentBorderForWeb=compmentBorderForWeb;
				bean.compmentExpandForWeb=compmentExpandForWeb;
				bean.layoutNoUseForIos = layoutNoUseBool.isSelected();

				if (!colorEdit.getText().trim().contains("色")) {
					if (!KeyValue.readCache("recentUseColor").contains(colorEdit.getText().trim() + ","))
						KeyValue.writeCache("recentUseColor",
								"" + colorEdit.getText().trim() + "," + KeyValue.readCache("recentUseColor"));
				}

				if (!bgColorEdit.getText().trim().contains("色")) {
					if (!KeyValue.readCache("recentUseColor").contains(bgColorEdit.getText().trim() + ","))
						KeyValue.writeCache("recentUseColor",
								"" + bgColorEdit.getText().trim() + "," + KeyValue.readCache("recentUseColor"));
				}

				String tempRecentUseColors[] = KeyValue.readCache("recentUseColor").split(",");

				if (tempRecentUseColors.length > 6) {
					String temp = "";
					for (int i = 0; i < 6; i++) {

						temp += tempRecentUseColors[i] + ",";
					}
					KeyValue.writeCache("recentUseColor", temp);
				}

				bean.picName = picNameEdit.getText().trim().toLowerCase().replace(" ", "");
				bean.textSize = textSizeEdit.getText().trim();

				if (!compomentType.contains("Layout")) {
					bean.x = x + (Integer) xyzMap.get("leftoffset");
					bean.y = y + (Integer) xyzMap.get("topoffset");
					bean.w = w - (Integer) xyzMap.get("rightoffset") - (Integer) xyzMap.get("leftoffset");
					bean.h = h - (Integer) xyzMap.get("bottomoffset") - (Integer) xyzMap.get("topoffset");
				} else {
					bean.x = x;
					bean.y = y;
					bean.w = w;
					bean.h = h;

				}
				bean.type = compomentType;
				bean.time = System.currentTimeMillis();
				if (bean.type.equals("ImageView") || bean.type.equals("Button")) {
					if (!bean.picName.equals("图片名"))
						savePic(image, bean.picName);
					if (imgCacheCheckBox.isSelected()) {
						bean.isImgCache = true;
					}

				}
				if (setPublicCheckBox.isSelected()) {
					savePic(image, bean.enname);
					bean.isPublicCompoment = true;
				}

				if (runTimeAddScrollView.isSelected()) {
					bean.isRunTimeAddScrollView = true;
				}

				if (isRunTimeHeight.isSelected()) {

					bean.isRunTimeHeightTextview = true;
				}

				circula(bean);
				implementInterfaceFrame.compomentDialogCallBack(bean);
				CompomentDialog2.this.setVisible(false);
				// SureChangeTestFrame.panel.setBackground(Color.YELLOW);//
				// 这里panel.setBackground(Color.YELLOW);改成SureChangeTestFrame.panel.setBackground(Color.YELLOW);
			}
		});

		// JButton cancel = new JButton("cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CompomentDialog2.this.setVisible(false);
			}

		});

		// colorEdit = new JTextField();

		colorEdit.setText((String) xyzMap.get("textColor"));
		// colorEdit.setBackground();
		colorEdit.setForeground(new Color(Integer.parseInt(xyzMap.get("textColor").toString().substring(1), 16)));

		gaveBgColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				bgColorEdit.setText(colorEdit.getText());
				bgColorEdit
						.setForeground(new Color(Integer.valueOf(bgColorEdit.getText().toString().substring(1), 16)));
			}

		});

		// bgColorEdit = new JTextField();
		bgColorEdit.setText("背景颜色");

		// picNameEdit = new JTextField();
		picNameEdit.setText("图片名");
		picNameEdit.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (picNameEdit.getText().equals("图片名")) {
					picNameEdit.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		// cnNameEdit = new JTextField();
		cnNameEdit.setText("中文名");
		cnNameEdit.getDocument().addDocumentListener(new DocumentListener(){
 
            public void changedUpdate(DocumentEvent e) {
                //文本改变时响应
                 
            }
 
            public void insertUpdate(DocumentEvent e) {
                //插入时响应
            	Document doc = e.getDocument();  
                try {
					String s = doc.getText(0, doc.getLength());
					
					if(CompomentDialog2.this.sameGroupWithBeforCompomentCheckbox.isSelected())
						return;
					
					boolean isOkRequest=false;
					int i=0;
					
					if(interfaceBeans==null || interfaceBeans.size()<=0)
					{
						return;
					}
					
					for(int k=0;k<interfaceBeans.size();k++)
					{
					for (Group group : interfaceBeans.get(k).requestGroups) {

						for (Row row : group.rows) {
							
							String column = row.enName + ":" + row.cnName;
							if(row.cnName.contains(s))
							{
								
								isOkRequest=true;
								interfaceList.setSelectedIndex(i);
							}
							i++;
						}

					}}
					
					if(!isOkRequest)
					{
						interfaceList.clearSelection();
						
					}
					
					boolean isOkRespond=false;
					int j=0;
					for(int k=0;k<interfaceBeans.size();k++)
					{
					for (Group group : interfaceBeans.get(k).respondGroups) {

						for (Row row : group.rows) {
							String column = row.enName + ":" + row.cnName;
							if(row.cnName.contains(s))
							{
								
								isOkRespond=true;
								interfaceColumnList.setSelectedIndex(j);
								
							}
							
							j++;
						}

					}
					}
					
					if(!isOkRespond)
					{
						
						interfaceColumnList.clearSelection();
						
					}
					
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} //返回文本框输入的内容  
                
            }
 
            public void removeUpdate(DocumentEvent e) {
                //删除时响应
                 
            }
             
        });
         
    
		cnNameEdit.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(final FocusEvent arg0) {
				if (cnNameEdit.getText().equals("中文名")) {
					cnNameEdit.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (cnNameEdit.getText().equals("")) {
					cnNameEdit.setText("中文名");
				}
			}
		});

		// enNameEdit = new JTextField();
		
		enNameEdit.setText("英文名");
		
		enNameEdit.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(final FocusEvent arg0) {
				if (enNameEdit.getText().equals("英文名")) {
					enNameEdit.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (enNameEdit.getText().equals("")) {
					enNameEdit.setText("英文名");
				}
			}
		});

		gaveBgColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				bgColorEdit.setText(colorEdit.getText());
			}

		});

		// textSizeEdit = new JTextField();
		textSizeEdit.setText("16");

		ArrayList webCompomentString = new ArrayList();
		webCompomentString.add("leftLeft");
		webCompomentString.add("centerCenter");
		webCompomentString.add("leftRight");
		webCompomentString.add("left_Center_Right");
		webCompomentString.add("left[C e n t e r]Right");
		webCompomentString.add("rightRight");
		webCompomentString.add("topBottom");
		webCompomentString.add("topBottomAndCenter");

		ArrayList borderString = new ArrayList();
		
		borderString.add("boderTop");
		borderString.add("boderLeft");
		borderString.add("boderRight");
		borderString.add("boderBottom");
		borderString.add("boderTopBottom");
		borderString.add("boderAll");
		
		ArrayList expandString = new ArrayList();
		expandString.add("1bei");
		expandString.add("2bei");
		expandString.add("3bei");
	
		
		borderList.setListData(borderString.toArray());
		borderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		borderList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent even) {
				// TODO Auto-generated method stub

				String value = borderList.getSelectedValue().toString();
				compmentBorderForWeb = value;
			}
		});
		

		webCompomentListView.setListData(webCompomentString.toArray());
		
		webCompomentListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		webCompomentListView.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent even) {
				// TODO Auto-generated method stub

				String value = webCompomentListView.getSelectedValue().toString();
				webCompomentType = value;
			}
		});
		
		expandList.setListData(expandString.toArray());
		
		expandList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		expandList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent even) {
				// TODO Auto-generated method stub

				String value = expandList.getSelectedValue().toString();
				compmentExpandForWeb = value;
			}
		});
		
		

		webCompomentPanel.setVisible(false);
		final JList list = baseListListView;
		
		list.setListData(this.listDate.toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent even) {
				// TODO Auto-generated method stub

				String value = list.getSelectedValue().toString();
				compomentType = value;

				if (compomentType.contains("Layout")) {
					webCompomentPanel.setVisible(true);
					borderPanel.setVisible(true);
					expandPanel.setVisible(true);
					long id = System.currentTimeMillis();
					cnNameEdit.setText("bg" + id);
					enNameEdit.setText("bg" + id);

					bgColorEdit.setText(colorEdit.getText());

					colorLabel.setText("边框颜色");

					colorEdit.setVisible(true);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(true);
					textSizeEdit.setVisible(false);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(false);
					circularCheckBox.setVisible(true);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(true);
					runTimeAddScrollView.setVisible(true);
					isRunTimeHeight.setVisible(false);
					layoutNoUseBool.setVisible(true);
				} else if (compomentType.contains("ImageView")) {

					colorLabel.setText("颜色");
					colorEdit.setVisible(false);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(false);
					textSizeEdit.setVisible(false);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(true);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(true);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(false);
					layoutNoUseBool.setVisible(false);
					isNineListCheck.setVisible(false);
					isMutiPageListCheck.setVisible(false);
				} else if (compomentType.contains("ListView") || compomentType.contains("ScrollView")) {

					colorLabel.setText("行间距颜色");
					colorEdit.setVisible(true);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(false);
					textSizeEdit.setVisible(false);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					isNineListCheck.setVisible(true);
					isMutiPageListCheck.setVisible(true);
					picNameEdit.setVisible(false);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(false);
					layoutNoUseBool.setVisible(false);
				} else if (compomentType.contains("EditText")) {
					colorLabel.setText("文字颜色");

					colorEdit.setVisible(true);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(true);
					textSizeEdit.setVisible(true);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(false);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(false);
					layoutNoUseBool.setVisible(false);
					isNineListCheck.setVisible(false);
					isMutiPageListCheck.setVisible(false);
				} else if (compomentType.contains("Button")) {
					colorLabel.setText("文字颜色");

					colorEdit.setVisible(true);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(true);
					textSizeEdit.setVisible(true);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(true);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(false);
					layoutNoUseBool.setVisible(false);
					isNineListCheck.setVisible(false);
					isMutiPageListCheck.setVisible(false);
				}

				else if (compomentType.contains("CheckBox")) {
					colorLabel.setText("文字颜色");

					colorEdit.setVisible(true);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(false);
					textSizeEdit.setVisible(true);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(false);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(false);
					layoutNoUseBool.setVisible(false);
					isNineListCheck.setVisible(false);
					isMutiPageListCheck.setVisible(false);
				} else if (compomentType.contains("TextView")) {
					colorLabel.setText("文字颜色");

					colorEdit.setVisible(true);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(false);
					textSizeEdit.setVisible(true);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(false);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(true);
					layoutNoUseBool.setVisible(false);
					isNineListCheck.setVisible(false);
					isMutiPageListCheck.setVisible(false);
				}

				else if (compomentType.equals("Line")) {
					colorLabel.setText("文字颜色");

					colorEdit.setVisible(false);
					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(true);
					textSizeEdit.setVisible(false);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(false);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(false);
					layoutNoUseBool.setVisible(false);
					isNineListCheck.setVisible(false);
					isMutiPageListCheck.setVisible(false);
				} else {
					colorLabel.setText("颜色");

					colorEdit.setVisible(true);

					bgColorEdit.setText("背景颜色");
					bgColorEdit.setVisible(true);
					textSizeEdit.setVisible(true);
					cnNameEdit.setVisible(true);
					enNameEdit.setVisible(true);
					picNameEdit.setVisible(true);
					circularCheckBox.setVisible(false);
					imgCacheCheckBox.setVisible(false);
					setPublicCheckBox.setVisible(false);
					runTimeAddScrollView.setVisible(false);
					isRunTimeHeight.setVisible(false);
					layoutNoUseBool.setVisible(false);
					isNineListCheck.setVisible(false);
					isMutiPageListCheck.setVisible(false);
					borderPanel.setVisible(false);
					expandPanel.setVisible(false);
					webCompomentPanel.setVisible(false);
				}

				CompomentDialog2.this.setVisible(true);

			}

		});

		String tempRecentUseColors[] = KeyValue.readCache("recentUseColor").split(",");

		int tempRecentUseColorsCount = 1;
		if (tempRecentUseColors.length >= 1 && !tempRecentUseColors[0].equals("")) {
			for (String color : tempRecentUseColors) {
				if (tempRecentUseColorsCount == 1) {

					color1Btn.setForeground(new Color(Integer.parseInt(color.substring(1), 16)));
					color1Btn.setText(color);
					color1Btn.setVisible(true);
				}
				if (tempRecentUseColorsCount == 2) {
					color2Btn.setForeground(new Color(Integer.parseInt(color.substring(1), 16)));
					color2Btn.setText(color);
					color2Btn.setVisible(true);
				}
				if (tempRecentUseColorsCount == 3) {
					color3Btn.setForeground(new Color(Integer.parseInt(color.substring(1), 16)));
					color3Btn.setText(color);
					color3Btn.setVisible(true);
				}
				if (tempRecentUseColorsCount == 4) {
					color4Btn.setForeground(new Color(Integer.parseInt(color.substring(1), 16)));
					color4Btn.setText(color);
					color4Btn.setVisible(true);
				}
				if (tempRecentUseColorsCount == 5) {
					color5Btn.setForeground(new Color(Integer.parseInt(color.substring(1), 16)));
					color5Btn.setText(color);
					color5Btn.setVisible(true);
				}
				if (tempRecentUseColorsCount == 6) {
					color6Btn.setForeground(new Color(Integer.parseInt(color.substring(1), 16)));
					color6Btn.setText(color);
					color6Btn.setVisible(true);
				}

				tempRecentUseColorsCount++;
			}
		}

		color1Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorEdit.setText(color1Btn.getText());
				colorEdit.setForeground(new Color(Integer.parseInt(color1Btn.getText().substring(1), 16)));

			}
		});

		color2Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorEdit.setText(color2Btn.getText());
				colorEdit.setForeground(new Color(Integer.parseInt(color2Btn.getText().substring(1), 16)));
			}
		});

		color3Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorEdit.setText(color3Btn.getText());
				colorEdit.setForeground(new Color(Integer.parseInt(color3Btn.getText().substring(1), 16)));
			}
		});

		color4Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorEdit.setText(color4Btn.getText());
				colorEdit.setForeground(new Color(Integer.parseInt(color4Btn.getText().substring(1), 16)));
			}
		});

		color5Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorEdit.setText(color5Btn.getText());
				colorEdit.setForeground(new Color(Integer.parseInt(color5Btn.getText().substring(1), 16)));
			}
		});

		color6Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorEdit.setText(color6Btn.getText());
				colorEdit.setForeground(new Color(Integer.parseInt(color6Btn.getText().substring(1), 16)));
			}
		});

	}

	public void setRgb(String rgb16) {
		colorEdit.setText("#" + rgb16);
		colorEdit.setForeground(new Color(Integer.parseInt(colorEdit.getText().substring(1), 16)));

	}

	public void setBgRgb(String bgrgb16) {
		bgColorEdit.setText("#" + bgrgb16);
		bgColorEdit.setForeground(new Color(Integer.parseInt(bgColorEdit.getText().substring(1), 16)));

	}

	public void savePic(Image iamge, String picName) {
		int w = iamge.getWidth(frame);
		int h = iamge.getHeight(frame);

		// 首先创建一个BufferedImage变量，因为ImageIO写图片用到了BufferedImage变量。
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);

		// 再创建一个Graphics变量，用来画出来要保持的图片，及上面传递过来的Image变量
		Graphics g = bi.getGraphics();
		try {
			g.drawImage(iamge, 0, 0, null);

			// 将BufferedImage变量写入文件中。
			if (!setPublicCheckBox.isSelected()) {
				ImageIO.write(bi, "png", new File(KeyValue.readCache("picPath") + "/drawable/" + picName + ".png"));

				// ImageIO.write(bi, "png", new
				// File(KeyValue.readCache("projectPath")
				// + "/res/drawable-hdpi/" + picName + ".png"));
			} else {
				FileUtil.makeDir(new File(KeyValue.readCache("picPath") + "/publiccompoment/"));
				ImageIO.write(bi, "png",
						new File(KeyValue.readCache("picPath") + "/publiccompoment/" + picName + ".png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 生成圆角drawable文件
	 */
	public void circula(CompomentBean bean) {
		String m = "";
		if (circularCheckBox.isSelected()) {
			bean.isFilletedCorner = true;
			if (bean.bgRgb16.equals(bean.rgb16)) {
				// 填充 没描边
				m += "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
				m += "<shape xmlns:android=\"http://schemas.android.com/apk/res/android\">\n";
				m += " <solid android:color=\"" + bean.bgRgb16 + "\" /> \n";
				m += " <corners android:radius=\"10dp\" />\n";
				m += "</shape>\n";
			} else { // 填充 描边
				m += "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
				m += "<shape xmlns:android=\"http://schemas.android.com/apk/res/android\">\n";
				m += " <solid android:color=\"" + bean.bgRgb16 + "\" /> \n";
				m += "<stroke android:width=\"0.5dp\" android:color=\"" + bean.rgb16 + "\"/>\n";
				m += "<corners android:radius=\"10dp\" />\n";
				m += "</shape>\n";

			}
			stringToFile(m,
					KeyValue.readCache("picPath") + "/drawable/" + "corner_" + bean.enname.toLowerCase() + ".xml");
			bean.bgRgb16 = "@drawable/corner_" + bean.enname.toLowerCase();

		}

	}

	public static File stringToFile(String name, String path) {
		byte[] b = name.getBytes();
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(path);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return file;
	}

	public interface CompomentDialogCallBack {
		public void compomentDialogCallBack(CompomentBean bean);
	}

	/** 修正xyz值 边缘在哪 更准确的xyz值 */
	public Map getNearXYZ(Image image, int x, int y, int w, int h) {

		// 原图
		BufferedImage bufImgOld = ImageUtil.imageToBufferedImage(image);

		int rgbsOld[] = new int[w * h];
		rgbsOld = bufImgOld.getRGB(0, 0, w, h, rgbsOld, 0, w);

		// Canny算法 边缘
		EdgeDetector edgeDetector = new EdgeDetector();
		edgeDetector.setSourceImage(bufImgOld);
		edgeDetector.setThreshold(128);
		edgeDetector.setWidGaussianKernel(5);
		try {
			edgeDetector.process();
		} catch (EdgeDetectorException e) {
			System.out.println(e.getMessage());
		}
		Image edgeImage = edgeDetector.getEdgeImage();

		// 边缘图 黑白图
		BufferedImage edgeBufImg = ImageUtil.imageToBufferedImage(edgeImage);

		// BufferedImage bi2;
		// File f2 = new
		// File("/Users/admin/Documents/workspace/gg/pic/testCanny.png");
		// try {
		// ImageIO.write(edgeBufImg, "png", f2);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		int rgbs[] = new int[w * h];
		rgbs = edgeBufImg.getRGB(0, 0, w, h, rgbs, 0, w);

		// Map map=buildMap(rgbs);

		// int max1keyvalue[]=getMax( map);

		int leftoffset = 0;// 偏移量
		int topoffset = 0;
		int rightoffset = 0;
		int bottomoffset = 0;

		// x
		boolean xok = false;
		for (int j = 0; j < w; j++) {

			for (int i = 0; i < h; i++) {
				if (xok) {
					break;
				}
				if (edgeBufImg.getRGB(j, i) != -1) {// -1 白色

					leftoffset = j;
					xok = true;
					break;
				}
			}
		}

		// w
		boolean wok = false;
		for (int j = w - 1; j > 1; j--) {
			if (wok) {
				break;
			}
			for (int i = 0; i < h; i++) {

				if (edgeBufImg.getRGB(j, i) != -1) {// -1 白色
					rightoffset = (w - 1) - j;
					wok = true;
					break;
				}
			}
		}

		// y
		boolean yok = false;
		for (int j = 0; j < h - 1; j++) {
			for (int i = 0; i < w - 1; i++) {
				if (yok) {
					break;
				}

				if (edgeBufImg.getRGB(i, j) != -1) {// -1 白色
					topoffset = j;
					yok = true;
					break;
				}
			}
		}

		// h
		boolean hok = false;
		for (int j = h - 1; j > 1; j--) {

			if (hok) {
				break;
			}

			for (int i = 0; i < w - 1; i++) {

				if (edgeBufImg.getRGB(i, j) != -1) {// -1 白色
					bottomoffset = (h - 1) - j;
					hok = true;
					break;
				}
			}
		}

		HashMap temp = new HashMap();
		temp.put("leftoffset", leftoffset);// left
		temp.put("topoffset", topoffset);// top
		temp.put("rightoffset", rightoffset);// right;
		temp.put("bottomoffset", bottomoffset);// bottom

		temp.put("textColor", "#" + rgbString16(maxOne(rgbsOld)));

		return temp;

	}

	public static String rgbString16(int pixel) {

		int rgb[] = new int[3];
		rgb[0] = (pixel & 0xff0000) >> 16;
		rgb[1] = (pixel & 0xff00) >> 8;
		rgb[2] = (pixel & 0xff);

		String color16 = ColorPanel.getColorInHexFromRGB(rgb[0], rgb[1], rgb[2]);
		return color16;

	}

	public static int maxOne(int[] strArr) {

		int min = 0;
		for (int i : strArr) {

			if (i < min) {

				min = i;
			}
		}

		return min;

	}

	/************* 建立哈希映射 ***************/
	public static Map<Integer, Integer> buildMap(int[] strArr) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int str : strArr) {
			if (map.containsKey(str)) {
				map.put(str, map.get(str) + 1);
			} else {
				map.put(str, 0);
			}
		}
		return map;
	}

	/************* 得到最大出现次数的映射 ***************/
	public static int[] getMax(Map<Integer, Integer> map) {
		int max = 0;
		Integer result = null;
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			// System.out.println(rgbString16(entry.getKey())+"
			// "+entry.getKey());
			if (entry.getValue() > max) {
				result = entry.getKey();
				if (result != null)
					max = entry.getValue();
			}
		}
		int keyvalue[] = new int[2];
		keyvalue[0] = result;
		keyvalue[1] = map.get(result);

		return keyvalue;
	}

	public ArrayList searchPics(String root)

	{

		ArrayList temp = new ArrayList();
		File file = new File(root);

		File[] files = file.listFiles(new FileFilter() {

			public boolean accept(File fl) {
				return !fl.isDirectory();
			}

		});

		for (File f : files) {
			String picname = f.getName();
			if (picname.indexOf(".") != -1) {
				int p = picname.indexOf(".");

				temp.add(picname.substring(0, p));
			} else

			{
				temp.add(f.getName());
			}

		}

		return temp;
	}
}
