package com.compoment.workflow;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.compoment.cut.CheckProblem;
import com.compoment.cut.CompomentBean;
import com.compoment.cut.CutCompomentsTypeImg;
import com.compoment.cut.CutImg;
import com.compoment.cut.CutCompomentsTypeImg.CutCompomentsTypeImgCallBack;
import com.compoment.cut.CutImg.CutImgCallBack;
import com.compoment.cut.android.AndroidLayoutXml;
import com.compoment.cut.iphone.IphoneTableViewCellXib;
import com.compoment.cut.iphone.IphoneViewControllerXib;
import com.compoment.cut.swing.SwingLayout;
import com.compoment.cut.web.WebJsp;
import com.compoment.cut.web.WebJspListViewItem;
import com.compoment.remote.AndroidLayoutXmlInterface;
import com.compoment.remote.CheckProblemInterface;
import com.compoment.remote.IphoneViewControllerXibInterface;
import com.compoment.remote.RemoteUtil;
import com.compoment.ui.CreateActivityChirldView;
import com.compoment.ui.CreateActivityView;
import com.compoment.ui.CreaterAdapter;
import com.compoment.ui.CreaterExpandAdapter;
import com.compoment.ui.ios.creater.TableViewCellAddToViewController;
import com.compoment.ui.ios.creater.TableViewCellH;
import com.compoment.ui.ios.creater.TableViewCellM;
import com.compoment.ui.ios.creater.ViewControllerH;
import com.compoment.ui.ios.creater.ViewControllerM;
import com.compoment.ui.web.WebServlet;
import com.compoment.ui.web.WebSpringMvc;
import com.compoment.ui.web.WebStruct2;
import com.compoment.util.DeepCopy;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.SerializeToFile;
import com.compoment.util.StringUtil;
import com.compoment.workflow.CompomentDialog2.CompomentDialogCallBack;



import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

public class PageFrame2 extends JFrame implements CutImgCallBack,CompomentDialogCallBack,CutCompomentsTypeImgCallBack{

	private JPanel contentPane;
	
	
	/**
	 * 全部控件对象
	 * */
	public List<CompomentBean> beans = new ArrayList<CompomentBean>();
	public List<CompomentBean> beansForSwing = null;
	public String pageType;
	public String pagePath;
	public String pageName;
	ArrayList components = new ArrayList();
	InterfaceDocDialog projectDocPanel;
	CutImg cutImg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageFrame2 frame = new PageFrame2("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PageFrame2(String pagePath) {
		this.pagePath=pagePath;
		pageName=getPageName(pagePath);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		cutImg= new CutImg(this, new File(pagePath));
		JPanel picPanel=cutImg;
		
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(picPanel, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(picPanel, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)))
					.addContainerGap())
		);
		GroupLayout gl_picPanel = new GroupLayout(picPanel);
		gl_picPanel.setHorizontalGroup(
			gl_picPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 447, Short.MAX_VALUE)
		);
		gl_picPanel.setVerticalGroup(
			gl_picPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 562, Short.MAX_VALUE)
		);
		picPanel.setLayout(gl_picPanel);
		
		
		
		
		
		JPanel panel_4 = new JPanel();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
						.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(20)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		
		 projectDocPanel=new InterfaceDocDialog(false,"接口文档");
		JPanel interfaceDocPanel = projectDocPanel.contentPanel;
		scrollPane_1.setViewportView(interfaceDocPanel);
		
		JButton resetButton = new JButton("用缓存直接生成");
		
		JButton createButton = new JButton("生成");
		
		JButton cancelButton = new JButton("撤销组件");
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(resetButton)
					.addGap(29)
					.addComponent(createButton)
					.addGap(18)
					.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(105, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(resetButton)
						.addComponent(createButton)
						.addComponent(cancelButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		panel_2.setLayout(gl_panel_2);
		
		JLabel label = new JLabel("页面类型");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
						.addComponent(label))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JList pageTypeList = new JList();
		//初始化
		pageTypeListView(pageTypeList);

		scrollPane.setViewportView(pageTypeList);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SerializeToFile serializeToFile = new SerializeToFile();
				serializeToFile.serializeToXml(beans,pageName+"_"+pageType);
				createPageCode();
			}

		});
		

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(beans!=null && beans.size()>0)
				{
				beans.remove(beans.size()-1);
				}
				cutImg.repaint();
				
			}

		});
		

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				beans.clear();
				if (beansForSwing != null)
					beansForSwing.clear();

				SerializeToFile serializeToFile = new SerializeToFile();
				beans = serializeToFile.deSerializeFromXml();
				createPageCode();
			}

		});
	}
	
	
	//页面类型列表
	public void pageTypeListView(final JList pageTypeListListView)
	{
		
		/** 页面类型列表 */
		
		ArrayList listDate = new ArrayList();
		listDate.add("---Android---");
		listDate.add("Activity-Android");
		listDate.add("Item-Android");
		listDate.add("Fragment-Android");
		listDate.add("Activity-ChirldView-Android");
		listDate.add("---Swing---");
		listDate.add("JFrame-Swing");
		listDate.add("JDialog-Swing");
		listDate.add("JPanel-Swing");
		listDate.add("---IOS---");
		listDate.add("ChirldViewController-IOS");
		listDate.add("ViewController-IOS");
		listDate.add("TableViewCell-IOS");
		listDate.add("TableViewHeadCell-IOS");
		listDate.add("CommonCell-IOS");
		listDate.add("---Web---");
		listDate.add("ViewController-Web");
		
	
		pageTypeListListView
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pageTypeListListView.setListData(listDate.toArray());
		pageTypeListListView
				.addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent even) {
						String value = pageTypeListListView.getSelectedValue()
								.toString();
						if(!value.contains("---"))
						{
						pageType=value;
						}else
						{
						pageType=null;
						}
					}
				});

		
		
	}
	
	
	
	public void createPageCode() {
		// 拷贝保留原始位置数据
		try {
			beansForSwing = DeepCopy.copyBySerialize(beans);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (pageType == null) {
			JOptionPane.showMessageDialog(this, "请选择页面类型", "",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		} else if (pageType.contains("Activity-Android")) {
			// android页面分析生成
			CreateAndroidLayoutXml("");
		
			
			savePublicCompoment();

			CreateActivityView createView = new CreateActivityView(
					pageName);
			createView.create();
		} else if (pageType.contains("Item-Android")) {

			Object[] options = { "listViewItem",
					"ExpandableListViewParentItem",
					"ExpandableListViewChildItem" };
			int response = JOptionPane.showOptionDialog(null, "", "",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				// android页面分析生成
				
				CreateAndroidLayoutXml("_item");
				
				
				savePublicCompoment();

				com.compoment.ui.CreaterAdapter createrAdapter = new CreaterAdapter(
						pageName + "_item", beans);
				createrAdapter.create();

			} else if (response == 1) {
				// android页面分析生成
				CreateAndroidLayoutXml("_parentitem");
				
				
			} else if (response == 2) {
				// android页面分析生成
				CreateAndroidLayoutXml("_childitem");
				
				savePublicCompoment();

				com.compoment.ui.CreaterExpandAdapter createrAdapter = new CreaterExpandAdapter(
						pageName , beans);
				createrAdapter.create();

			}

		} else if (pageType.contains("Fragment-Android")) {

		} else if (pageType.contains("Activity-ChirldView-Android")) {
			// android页面分析生成
			CreateAndroidLayoutXml("");
			
			savePublicCompoment();

			com.compoment.ui.CreateActivityChirldView createrAdapter = new CreateActivityChirldView(
					pageName);
			createrAdapter.create();
		} else if (pageType.contains("JFrame-Swing")) {
			// android页面分析生成
			CreateAndroidLayoutXml("");
			
			savePublicCompoment();

			// Swing页面水平方向 再次取得分组信息
			JPanel swingPanel = new CutCompomentsTypeImg(this,
					beansForSwing);
			JDialog jdialog = new JDialog(this, "swing水平方向截取", true);
			jdialog.setSize(800, 800);
			jdialog.getContentPane().add(swingPanel);
			jdialog.setLocation(10, 10);
			jdialog.setVisible(true);

			SwingLayout swingLayout = new SwingLayout();
			swingLayout.createJFrame(beans, beansForSwing);

		} else if (pageType.contains("JDialog-Swing")) {

			// android页面分析生成
			CreateAndroidLayoutXml("");
			
			savePublicCompoment();

			// Swing页面水平方向 再次取得分组信息
			JPanel swingPanel = new CutCompomentsTypeImg(this,
					beansForSwing);
			JDialog jdialog = new JDialog(this, "swing水平方向截取", true);
			jdialog.setSize(800, 800);
			jdialog.getContentPane().add(swingPanel);
			jdialog.setLocation(10, 10);
			jdialog.setVisible(true);

			SwingLayout swingLayout = new SwingLayout();
			swingLayout.createJDialog(beans, beansForSwing);

		} else if (pageType.contains("JPanel-Swing")) {
			// android页面分析生成
			CreateAndroidLayoutXml("");
			
			savePublicCompoment();

			// Swing页面水平方向 再次取得分组信息
			JPanel swingPanel = new CutCompomentsTypeImg(this,
					beansForSwing);
			JDialog jdialog = new JDialog(this, "swing水平方向截取", true);
			jdialog.setSize(800, 800);
			jdialog.getContentPane().add(swingPanel);
			jdialog.setLocation(10, 10);
			jdialog.setVisible(true);

			SwingLayout swingLayout = new SwingLayout();
			swingLayout.createJPanel(beans, beansForSwing);
		}else if (pageType.equals("ViewController-IOS")) {
			// 页面分析生成
			CreateAndroidLayoutXml("");
			
			savePublicCompoment();

			//ios
			

				//rmi://120.76.232.114:1099/checkProblem
		try {
			IphoneViewControllerXibInterface  iphoneLayout = (IphoneViewControllerXibInterface) Naming.lookup(RemoteUtil.rmiurl+"IphoneViewControllerXib");
			
				String m=iphoneLayout.IphoneViewControllerXib(pageName,beans);
				String className=StringUtil.firstCharToUpperAndJavaName(pageName);
				FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/ios", className + "ViewController", "xib", m);

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ViewControllerH viewControllerH=new ViewControllerH(pageName,beans,false);
			ViewControllerM viewControllerM=new ViewControllerM(pageName,beans,false);
			
			
			
			//android
			CreateActivityView createView = new CreateActivityView(
					pageName);
			createView.create();
			
			}
		
		else if (pageType.equals("ChirldViewController-IOS")) {
			// 页面分析生成
			CreateAndroidLayoutXml("");
			
			savePublicCompoment();

			//ios
			
			//rmi://120.76.232.114:1099/checkProblem
			try {
				IphoneViewControllerXibInterface  iphoneLayout = (IphoneViewControllerXibInterface) Naming.lookup(RemoteUtil.rmiurl+"IphoneViewControllerXib");
				
					String m=iphoneLayout.IphoneViewControllerXib(pageName,beans);
					String className=StringUtil.firstCharToUpperAndJavaName(pageName);
					FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/ios", className + "ViewController", "xib", m);

				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			ViewControllerH viewControllerH=new ViewControllerH(pageName,beans,true);
			ViewControllerM viewControllerM=new ViewControllerM(pageName,beans,true);
			
			
			
			//android
			com.compoment.ui.CreateActivityChirldView createrAdapter = new CreateActivityChirldView(
					pageName);
			createrAdapter.create();
			
			}
		
		
		
		else if (pageType.contains("TableViewHeadCell-IOS")) {
			// 页面分析生成
			CreateAndroidLayoutXml("");
			
			savePublicCompoment();

			TableViewCellH viewControllerH=new TableViewCellH(pageName,beans,"TableViewHeadCell");
			TableViewCellM viewControllerM=new TableViewCellM(pageName,beans,"TableViewHeadCell");
			
			IphoneTableViewCellXib  iphoneLayout = new IphoneTableViewCellXib(pageName,beans,"TableViewHeadCell");
			
			String 	fileName = KeyValue.readCache("projectPath") + "/" + "src/ios" + "/" + StringUtil.firstCharToUpperAndJavaName(pageName)+"ViewController"
					+ "." + "m";
			TableViewCellAddToViewController TableViewCellAddViewController=new TableViewCellAddToViewController(pageName,beans,fileName,true);
			
			}
		else if (pageType.contains("CommonCell-IOS")) {
			// 页面分析生成
			CreateAndroidLayoutXml("");
			
			savePublicCompoment();

			TableViewCellH viewControllerH=new TableViewCellH(pageName,beans,"TableViewHeadCell");
			TableViewCellM viewControllerM=new TableViewCellM(pageName,beans,"TableViewHeadCell");
			
			IphoneTableViewCellXib  iphoneLayout = new IphoneTableViewCellXib(pageName,beans,"TableViewHeadCell");
			
			}
		
	
		
		
		else if (pageType.contains("TableViewCell-IOS")) {
			// 页面分析生成
			CreateAndroidLayoutXml("");
			
			savePublicCompoment();

			TableViewCellH tableViewCellH=new TableViewCellH(pageName,beans,"TableViewCell");
			TableViewCellM tableViewCellM=new TableViewCellM(pageName,beans,"TableViewCell");
			
			IphoneTableViewCellXib iphoneLayout = new IphoneTableViewCellXib(pageName,beans,"TableViewCell");
			
			String 	fileName = KeyValue.readCache("projectPath") + "/" + "src/ios" + "/" + StringUtil.firstCharToUpperAndJavaName(pageName)+"ViewController"
					+ "." + "m";
			TableViewCellAddToViewController TableViewCellAddViewController=new TableViewCellAddToViewController(pageName,beans,fileName,false);
			
		}
	
		else if (pageType.equals("ViewController-Web")) {
			// 页面分析生成
			CreateAndroidLayoutXml("");
			
			savePublicCompoment();

			//ios
			
			//rmi://120.76.232.114:1099/checkProblem
			try {
				IphoneViewControllerXibInterface  iphoneLayout = (IphoneViewControllerXibInterface) Naming.lookup(RemoteUtil.rmiurl+"IphoneViewControllerXib");
				
					String m=iphoneLayout.IphoneViewControllerXib(pageName,beans);
					String className=StringUtil.firstCharToUpperAndJavaName(pageName);
					FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/ios", className + "ViewController", "xib", m);

				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			ViewControllerH viewControllerH=new ViewControllerH(pageName,beans,false);
			ViewControllerM viewControllerM=new ViewControllerM(pageName,beans,false);
			
			
			
			
			
		   //Web
			WebJsp  webJsp = new WebJsp(pageName,beans);
			
			WebSpringMvc webaction=new WebSpringMvc(pageName,beans);
			WebServlet webServlet=new WebServlet(pageName,beans);
			WebStruct2 webStruct2=new WebStruct2(pageName,beans);
			
			//android
			CreateActivityView createView = new CreateActivityView(
					pageName);
			createView.create();
			
			}
	
		

		beans.clear();
		beansForSwing.clear();

		JOptionPane.showMessageDialog(this,
				"到" + KeyValue.readCache("projectPath") + "/src目录查看生成的文件",
				"", JOptionPane.INFORMATION_MESSAGE);

		new CodeFunctionAdd();
	}

	public void savePublicCompoment() {
		SerializeToFile serializeToFile = new SerializeToFile();
		List temps = new ArrayList();
		String fileName = "";
		for (CompomentBean bean : beans) {

			if (bean.isPublicCompoment) {
				fileName = bean.enname;

				for (CompomentBean unmodifybean : beansForSwing) {
					if (unmodifybean.enname.equals(fileName)) {
						temps.add(unmodifybean);
					}
				}

				if (bean.chirlds != null && bean.chirlds.size() > 0) {

					for (CompomentBean chrild : bean.chirlds) {

						for (CompomentBean unmodifybean : beansForSwing) {
							if (unmodifybean.enname.equals(chrild.enname)) {
								temps.add(unmodifybean);
							}
						}

					}
				}
			}
		}

		String xmlFileName = FileUtil.makeFile(KeyValue.readCache("picPath"),
				"publiccompoment", fileName, "xml", "");
		serializeToFile.serializeToXmlForPublicCompoment(temps, xmlFileName);
	}
	
	
	
	/**
	 * 获得截取图片的相关属性（x,y,w,h）
	 * */
	public void cutCompomentsTypeImgCallBack( final int x, final int y,
			final int w, final int h) {
		
		CompomentBean bean = new CompomentBean();
		long id=System.currentTimeMillis();
		bean.cnname = "bg"+id;
		bean.enname = "bg"+id;
		bean.rgb16 = "";
		bean.bgRgb16 = "";
		bean.picName ="";
		bean.textSize = "";
		bean.x = x;
		bean.y = y;
		bean.w = w;
		bean.h = h;
		bean.type = "Layout";
		bean.time=System.currentTimeMillis();
		
		beansForSwing.add(bean);
	}

	/**
	 * 根据截取图片生成控件对象
	 * */
	public void compomentDialogCallBack(CompomentBean bean) {
		
		
		beans.add(bean);
	
		
		CheckProblemInterface checkProblem;
		try {
			//rmi://120.76.232.114:1099/checkProblem
			checkProblem = (CheckProblemInterface)Naming.lookup(RemoteUtil.rmiurl+"CheckProblem");
		
		
			if(checkProblem.check(beans))
			{
				
			}else
			{
				beans.remove(bean);
				JOptionPane.showMessageDialog(null, bean.enname+"没有父窗体,请先生成", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
	/**
	 * CutImgCallBack   获得截取图片的相关属性（x,y,w,h,Image）
	 * */
	
	@Override
	public void cutImgCallBack(Image image, int x, int y, int w, int h) {
		// TODO Auto-generated method stub
	
		if(pageType==null)
		{
			JOptionPane.showMessageDialog(this,
					"请选择页面类型", "", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		
		
		//CompomentDialog dialog;
		//dialog = new CompomentDialog(this,this, image, x, y, w, h,components);
		
		
		
		CompomentDialog2 jdialog = new CompomentDialog2(projectDocPanel.selectInterfaceBeans,beans,pageName,pageType);
		//jdialog.setSize(1000, 800);
		jdialog.init(this,this, image, x, y, w, h,pageType);
	
		jdialog.setLocationRelativeTo(null);
		jdialog.setVisible(true);
	}
	
	
	
	public String  getPageName(String pagePath)
	{
			int p=pagePath.lastIndexOf("/");
			if(p==-1)
			{
				p=pagePath.lastIndexOf("\\");
			}
			int p2=pagePath.lastIndexOf(".");
			String pageName=pagePath.substring(p+1,p2);
			String path=pagePath.substring(0,p);
			
		
			
			
			 
				File drawablefile=new File(path+"/drawable/");
				 if(! drawablefile.exists()) {  
			         makeDir(drawablefile);  
			     } 
				 
//					File javafile=new File(path+"/java/");
//					 if(! javafile.exists()) {  
//				         makeDir(javafile);  
//				     } 
	
		        return pageName;
		    
	}

	
	public void CreateAndroidLayoutXml(String pageNamePart)
	{
		try {
			
			AndroidLayoutXmlInterface androidLayoutXml = (AndroidLayoutXmlInterface) Naming.lookup(RemoteUtil.rmiurl+"AndroidLayoutXml");
			;
			String xmlFileName = androidLayoutXml.analyseRelative(
					pageName+pageNamePart , beans);
			  if(xmlFileName.equals("no have layout"))
	            {
	            	JOptionPane.showMessageDialog(this, "请添加父布局", "",
	    					JOptionPane.INFORMATION_MESSAGE);
	            	return;
	            }
			  beans=androidLayoutXml.getBeans();
			 FileUtil.makeFile(KeyValue.readCache("projectPath"),"src/android/xml", pageName+pageNamePart , "xml", xmlFileName);
			  
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public  void makeDir(File dir) {  
	    if(! dir.getParentFile().exists()) {  
	        makeDir(dir.getParentFile());  
	    }  
	    dir.mkdir();  
	}  
}
