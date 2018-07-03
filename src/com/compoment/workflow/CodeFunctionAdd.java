package com.compoment.workflow;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.compoment.addfunction.android.DateSelect;
import com.compoment.addfunction.android.MyDialog;
import com.compoment.addfunction.android.Paging;
import com.compoment.addfunction.android.ReportList;
import com.compoment.addfunction.android.RequestAndroid;
import com.compoment.addfunction.iphone.RequestIphone;
import com.compoment.addfunction.swing.SystemDialog;
import com.compoment.addfunction.web.WebRequestRespond;
import com.compoment.addfunction.webmanage.StructActionForm;
import com.compoment.addfunction.webmanage.TableToHibernateEntity;
import com.compoment.addfunction.webmanage.UpdateJspStruct2;
import com.compoment.addfunction.webmanage.jspServletMybatis.AddJspForServlet;
import com.compoment.addfunction.webmanage.jspServletMybatis.LocalApplicationTest;
import com.compoment.addfunction.webmanage.jspServletMybatis.QueryJspForServlet;
import com.compoment.addfunction.webmanage.jspServletMybatis.ServletActionForWebManage;
import com.compoment.addfunction.webmanage.jspServletMybatis.UpdateJspForServlet;
import com.compoment.addfunction.webmanage.jspStruct2Mybatis.AddJspForWebManage;
import com.compoment.addfunction.webmanage.jspStruct2Mybatis.QueryJspForWebManage;
import com.compoment.addfunction.webmanage.jspStruct2Mybatis.Struct2ActionForWebManage;
import com.compoment.addfunction.webmanage.jspStruct2Mybatis.UpdateJspForWebManage;
import com.compoment.addfunction.webmanage.ActionStruct2;
import com.compoment.addfunction.webmanage.AddJsp;
import com.compoment.addfunction.webmanage.AddJspStruct2;
//import com.compoment.addfunction.webmanage.MenuJsp;
import com.compoment.addfunction.webmanage.StructAction;
import com.compoment.addfunction.webmanage.QueryJsp;
import com.compoment.addfunction.webmanage.QueryJspStruct2;
import com.compoment.db.tabledocinterfacedoc.TableDocToInterfaceService;
import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

/**
 * 
 * 功能添加页
 * */
public class CodeFunctionAdd extends JFrame {

	class Function {
		String id;
		String name;

		public Function(String id, String name) {
			this.id = id;
			this.name = name;
		}

		ArrayList<Function> chirlds = new ArrayList<Function>();
	}

	ArrayList<Function> functionParents = new ArrayList<Function>();
	ArrayList<Function> functionChirlds;
	JList functionList2ListView;
	JList functionListListView;

	ArrayList codeFileListDate = new ArrayList();
	JList codeFileListListView;

	/** 编辑器 */
	JTextArea editorValueEditText;
	JLabel imgTextView;
	int lineno;

	String currentCodeFileFullPath = "";
	String backupBeforeModify = "";

	JList codeTypeListListView;
	String fileName;
	JTextField codePathValueEditText;

	public CodeFunctionAdd() {

		super("");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		String laf = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(laf);
		} catch (UnsupportedLookAndFeelException exc) {
		} catch (Exception exc) {
		}

		Container c = getContentPane();
		GroupLayout layout = new GroupLayout(c);
		c.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		// 垂直
		/** part1JPanel part2JPanel part3JPanel part4JPanel */
		GroupLayout.ParallelGroup bg1422277541235LinearLayout = layout
				.createParallelGroup();
		/** part1 */
		JPanel part1JPanel = createPart1();
		bg1422277541235LinearLayout.addComponent(part1JPanel);

		/** part2 */
		JPanel part2JPanel = createPart2();
		bg1422277541235LinearLayout.addComponent(part2JPanel);

		/** part3 */
		JPanel part3JPanel = createFunctionPanel();
		bg1422277541235LinearLayout.addComponent(part3JPanel);

		/** part4 */
		JPanel part4JPanel = new JPanel();
		bg1422277541235LinearLayout.addComponent(part4JPanel);

		layout.setVerticalGroup(bg1422277541235LinearLayout);

		// 水平
		/** part1JPanel part2JPanel part3JPanel part4JPanel */
		GroupLayout.SequentialGroup bg1422277614475 = layout
				.createSequentialGroup();
		/** part1 */
		bg1422277614475.addComponent(part1JPanel);

		/** part2 */
		bg1422277614475.addComponent(part2JPanel);

		/** part3 */
		bg1422277614475.addComponent(part3JPanel);

		/** part4 */
		bg1422277614475.addComponent(part4JPanel);

		layout.setHorizontalGroup(bg1422277614475);

		setLocation(200, 200);
		pack();

		setVisible(true);

		String classDir = "";
		File directory = new File("");// 参数为空
		try {
			classDir = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		KeyValue.writeCache("projectPath", classDir+"/res");
		
		
		KeyValue.writeCache("compomentProjectAddress",
				KeyValue.readCache("projectPath") + "/other/mobile-android");

		if (KeyValue.readCache("compomentProjectAddress") == null
				|| KeyValue.readCache("compomentProjectAddress").equals("")) {
			String inputValue = JOptionPane
					.showInputDialog("请输入(mobile-android)Project路径");
			KeyValue.writeCache("compomentProjectAddress", inputValue);
		} else {

			String projectPath = KeyValue.readCache("compomentProjectAddress");
			if (FileUtil.isDirectory(projectPath + "/src/com/compoment")) {

			} else {
				String inputValue = JOptionPane
						.showInputDialog("请输入(mobile-android)Project路径");
				KeyValue.writeCache("compomentProjectAddress", inputValue);
			}
		}

		
	
	}

	public JPanel createPart1() {
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		// 垂直
		/**
		 * bg1422276551990LinearLayout bg1422276614897LinearLayout
		 * bg1422276676527LinearLayout
		 */
		GroupLayout.SequentialGroup bg1422276538785LinearLayout = layout
				.createSequentialGroup();
		/** codeTypeTitleTextView codeTypeListListView */
		GroupLayout.SequentialGroup bg1422276551990LinearLayout = layout
				.createSequentialGroup();
		/** 代码类型 */
		JLabel codeTypeTitleTextView = new JLabel("代码类型");
		bg1422276551990LinearLayout.addComponent(codeTypeTitleTextView);

		/** 代码类型列表 */
		codeTypeListListView = new JList();
		JScrollPane codeTypeListScrollPane = new JScrollPane(
				codeTypeListListView);
		ArrayList listDate = new ArrayList();
		listDate.add("Android");
		listDate.add("Iphone");
		listDate.add("Swing");
		listDate.add("Web");
		listDate.add("WebManage");
		codeTypeListListView
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		codeTypeListListView.setListData(listDate.toArray());
		codeTypeListListView
				.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent even) {
						String value = codeTypeListListView.getSelectedValue()
								.toString();

						if (value.equals("Android")) {

							KeyValue.writeCache("compomentProjectAddress",
									KeyValue.readCache("projectPath")
											+ "/other/mobile-android");

							if (KeyValue.readCache("compomentProjectAddress") == null
									|| KeyValue.readCache(
											"compomentProjectAddress").equals(
											"")) {

								String inputValue = JOptionPane
										.showInputDialog("请输入(mobile-android)Project路径");

								KeyValue.writeCache("compomentProjectAddress",
										inputValue);
							} else {

								String projectPath = KeyValue
										.readCache("compomentProjectAddress");
								if (FileUtil.isDirectory(projectPath
										+ "/src/com/compoment")) {

								} else {
									KeyValue.writeCache(
											"compomentProjectAddress",
											KeyValue.readCache("projectPath")
													+ "/other/mobile-android");

									// String inputValue =
									// JOptionPane.showInputDialog("请输入(mobile-android)Project路径");
									// KeyValue.writeCache("compomentProjectAddress",
									// inputValue);
								}
							}

							androidData();
							// 设值
							DefaultListModel functionModelList = new DefaultListModel();
							for (Function function : functionParents) {
								functionModelList.addElement(function);
							}
							// 自定义对象加入模型列表
							functionListListView.setModel(functionModelList);

							String projectPath = KeyValue
									.readCache("projectPath");
							codePathValueEditText.setText(projectPath
									+ "/src/android");

						} else if (value.equals("Iphone")) {
							iphoneData();
							// 设值
							DefaultListModel functionModelList = new DefaultListModel();
							for (Function function : functionParents) {
								functionModelList.addElement(function);
							}
							// 自定义对象加入模型列表
							functionListListView.setModel(functionModelList);

							String projectPath = KeyValue
									.readCache("projectPath");
							codePathValueEditText.setText(projectPath
									+ "/src/ios");
						} else if (value.equals("Swing")) {
							swingData();
							// 设值
							DefaultListModel functionModelList = new DefaultListModel();
							for (Function function : functionParents) {
								functionModelList.addElement(function);
							}
							// 自定义对象加入模型列表
							functionListListView.setModel(functionModelList);
						} else if (value.equals("WebManage")) {
							WebManageData();
							// 设值
							DefaultListModel functionModelList = new DefaultListModel();
							for (Function function : functionParents) {
								functionModelList.addElement(function);
							}
							// 自定义对象加入模型列表
							functionListListView.setModel(functionModelList);
							String projectPath = KeyValue
									.readCache("projectPath");
							codePathValueEditText.setText(projectPath
									+ "/src/webManager");
						} else if (value.equals("Web")) {
							WebData();
							// 设值
							DefaultListModel functionModelList = new DefaultListModel();
							for (Function function : functionParents) {
								functionModelList.addElement(function);
							}
							// 自定义对象加入模型列表
							functionListListView.setModel(functionModelList);

							String projectPath = KeyValue
									.readCache("projectPath");
							codePathValueEditText.setText(projectPath
									+ "/src/web");
						}

						else {
							DefaultListModel functionModelList = new DefaultListModel();

							// 自定义对象加入模型列表
							functionListListView.setModel(functionModelList);
						}
					}
				});
		bg1422276551990LinearLayout.addComponent(codeTypeListScrollPane);

		bg1422276538785LinearLayout.addGroup(bg1422276551990LinearLayout);

		/** codePathTitleTextView codePathValueEditText */
		GroupLayout.SequentialGroup bg1422276614897LinearLayout = layout
				.createSequentialGroup();
		/** 代码路径 */
		JLabel codePathTitleTextView = new JLabel("代码路径");
		bg1422276614897LinearLayout.addComponent(codePathTitleTextView);

		/** path */
		codePathValueEditText = new JTextField();
		bg1422276614897LinearLayout.addComponent(codePathValueEditText);

		// 添加DocumentListener监听器
		codePathValueEditText.getDocument().addDocumentListener(
				new DocumentListener() {

					@Override
					public void changedUpdate(DocumentEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void insertUpdate(DocumentEvent e) {

						String s = codePathValueEditText.getText();

						KeyValue.writeCache("codeFilePath", s);
						searchCodeFiles(s);
					}

					@Override
					public void removeUpdate(DocumentEvent arg0) {
						// TODO Auto-generated method stub

					}
				});

		bg1422276538785LinearLayout.addGroup(bg1422276614897LinearLayout);

		/** codeFileTitleTextView codeFileListListView */
		GroupLayout.SequentialGroup bg1422276676527LinearLayout = layout
				.createSequentialGroup();
		/** 代码文件 */
		JLabel codeFileTitleTextView = new JLabel("代码文件");
		bg1422276676527LinearLayout.addComponent(codeFileTitleTextView);

		/** 代码文件列表 */
		codeFileListListView = new JList();
		JScrollPane codeFileListListScrollPane = new JScrollPane(
				codeFileListListView);

		codeFileListListView
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		codeFileListListView
				.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent even) {
						backupBeforeModify = "";
						fileName = codeFileListListView.getSelectedValue()
								.toString();
						currentCodeFileFullPath = codePathValueEditText
								.getText() + "/" + fileName;
						editorValueEditText.setText(FileUtil
								.fileContent(currentCodeFileFullPath));

						String codePath = codePathValueEditText.getText();

						int p = codePath.lastIndexOf("\\");
						if (p == -1) {
							p = codePath.lastIndexOf("/");
						}

						String picPath = codePath.substring(0, p);
						String img = picPath + "/" + getPicName(fileName)
								+ ".png";
						File file = new File(img);
						if (file != null && file.isFile()) {

						} else {
							img = picPath + "/" + getPicName(fileName) + ".jpg";

						}
						imgTextView.setIcon(new ImageIcon(img));
						CodeFunctionAdd.this
								.setExtendedState(Frame.MAXIMIZED_BOTH);
						CodeFunctionAdd.this.setVisible(true);
					}
				});
		bg1422276676527LinearLayout.addComponent(codeFileListListScrollPane);

		bg1422276538785LinearLayout.addGroup(bg1422276676527LinearLayout);

		layout.setVerticalGroup(bg1422276538785LinearLayout);

		// codePathValueEditText.setText(KeyValue.readCache("codeFilePath"));

		// 水平
		/**
		 * codeTypeTitleTextView codeTypeListListView codePathTitleTextView
		 * codePathValueEditText codeFileTitleTextView codeFileListListView
		 */
		GroupLayout.ParallelGroup bg1422276744869 = layout
				.createParallelGroup();
		/** 代码类型 */
		bg1422276744869.addComponent(codeTypeTitleTextView);

		/** 代码类型列表 */
		bg1422276744869.addComponent(codeTypeListScrollPane);

		/** 代码路径 */
		bg1422276744869.addComponent(codePathTitleTextView);

		/** path */
		bg1422276744869.addComponent(codePathValueEditText);

		/** 代码文件 */
		bg1422276744869.addComponent(codeFileTitleTextView);

		/** 代码文件列表 */
		bg1422276744869.addComponent(codeFileListListScrollPane);

		layout.setHorizontalGroup(bg1422276744869);

		return panel;
	}

	public JPanel createPart2() {
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		// 垂直
		/** editorTitleTextView editorValueEditText */
		GroupLayout.SequentialGroup bg1422278266495LinearLayout = layout
				.createSequentialGroup();
		/** 编辑 */
		JLabel editorTitleTextView = new JLabel("编辑");
		bg1422278266495LinearLayout.addComponent(editorTitleTextView);

		/** 编辑 */
		editorValueEditText = new JTextArea("编辑");

		JScrollPane editorValueScrollPane = new JScrollPane(editorValueEditText);
		bg1422278266495LinearLayout.addComponent(editorValueScrollPane);
		// 设置文本域中文本的字体
		Font font = new Font("Courier", Font.TRUETYPE_FONT, 14);
		editorValueEditText.setFont(font);
		// 为文本域的插入光标设置颜色
		editorValueEditText.setCaretColor(Color.yellow);
		// 设置文本域的背景和前景颜色
		editorValueEditText.setBackground(new Color(70, 80, 91));
		editorValueEditText.setForeground(Color.pink);
		// 为文本域插入光标设置事件处理器
		editorValueEditText.addCaretListener(new CaretLis_lineNo());

		layout.setVerticalGroup(bg1422278266495LinearLayout);

		// 水平
		/** editorTitleTextView editorValueEditText */
		GroupLayout.ParallelGroup bg1422278330350 = layout
				.createParallelGroup();
		/** 编辑 */
		bg1422278330350.addComponent(editorTitleTextView);

		/** 编辑 */
		bg1422278330350.addComponent(editorValueScrollPane);

		layout.setHorizontalGroup(bg1422278330350);

		return panel;
	}

	public JPanel createFunctionPanel() {
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		// 垂直
		/** bg1422608973093LinearLayout bg1422609099562LinearLayout */
		GroupLayout.SequentialGroup bg1422608964921LinearLayout = layout
				.createSequentialGroup();
		/** bg1422608983625LinearLayout bg1422609048781LinearLayout */
		GroupLayout.ParallelGroup bg1422608973093LinearLayout = layout
				.createParallelGroup();
		/** functionTitleTextView functionListListView */
		GroupLayout.SequentialGroup bg1422608983625LinearLayout = layout
				.createSequentialGroup();
		/** 功能 */
		JLabel functionTitleTextView = new JLabel("功能");
		bg1422608983625LinearLayout.addComponent(functionTitleTextView);

		/** 功能列表 */
		functionListListView = new JList();
		JScrollPane functionListListViewScrollPane = new JScrollPane(
				functionListListView);
		// 自定义jlist的item单元格显示样子
		functionListListView.setCellRenderer(new FunctionListCell());
		// functionListListView.setListData(functionParents.toArray());

		// 双击
		functionListListView.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent me) {
				if (checkClickTime()) {

					String codeFile = (String) codeFileListListView
							.getSelectedValue();

					Function function = (Function) functionListListView
							.getSelectedValue();
					if (function == null)
						return;
					String value = codeTypeListListView.getSelectedValue()
							.toString();

					if (value.equals("Android")) {
						if (codeFile == null
								|| (codeFile != null && codeFile.equals(""))) {

							JOptionPane.showMessageDialog(null, "请选择代码文件",
									"温馨提示", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						androidFunction(function);
					} else if (value.equals("Iphone")) {
						if (codeFile == null
								|| (codeFile != null && codeFile.equals(""))) {

							JOptionPane.showMessageDialog(null, "请选择代码文件",
									"温馨提示", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						iphoneFunction(function);
					} else if (value.equals("WebManage")) {
						WebManageFunction(function);
					}

					else if (value.equals("Web")) {
						WebFunction(function);
					}

				}
			}
		});

		// 单击
		functionListListView
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		functionListListView
				.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent even) {
						// String value =
						// functionListListView.getSelectedValue()
						// .toString();

						Function function = (Function) functionListListView
								.getSelectedValue();
						if (function == null)
							return;
						functionChirlds = function.chirlds;
						if (functionChirlds == null)
							return;
						// 设值
						DefaultListModel functionModelList2 = new DefaultListModel();
						for (Function function2 : functionChirlds) {

							functionModelList2.addElement(function2);

						}
						// 自定义对象加入模型列表
						functionList2ListView.setModel(functionModelList2);

					}
				});
		bg1422608983625LinearLayout
				.addComponent(functionListListViewScrollPane);

		bg1422608973093LinearLayout.addGroup(bg1422608983625LinearLayout);

		/** function2TitleTextView functionList2ListView */
		GroupLayout.SequentialGroup bg1422609048781LinearLayout = layout
				.createSequentialGroup();
		/** 功能2 */
		JLabel function2TitleTextView = new JLabel("子功能");
		bg1422609048781LinearLayout.addComponent(function2TitleTextView);

		/** 功能列表2 */
		functionList2ListView = new JList();
		JScrollPane functionList2ListViewScrollPane = new JScrollPane(
				functionList2ListView);
		// 设值

		// 自定义对象加入模型列表
		// functionList2ListView.setModel(functionModelList);
		// 自定义jlist的item单元格显示样子
		functionList2ListView.setCellRenderer(new FunctionListCell());
		// functionListListView.setListData(functionParents.toArray());

		// 单击
		// functionList2ListView
		// .addListSelectionListener(new ListSelectionListener() {
		//
		// public void valueChanged(ListSelectionEvent even) {
		// // String value =
		// // functionList2ListView.getSelectedValue()
		// // .toString();
		// Function function = (Function) functionList2ListView
		// .getSelectedValue();
		// }
		// });

		// 双击
		functionList2ListView.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent me) {
				if (checkClickTime()) {
					Function function = (Function) functionList2ListView
							.getSelectedValue();
					if (function == null)
						return;
					String value = codeTypeListListView.getSelectedValue()
							.toString();

					if (value.equals("Swing")) {
						swingFunction(function);
					} else if (value.equals("Android")) {
						androidFunction(function);
					} else if (value.equals("Iphone")) {
						iphoneFunction(function);
					} else if (value.equals("WebManage")) {
						WebManageFunction(function);
					} else if (value.equals("Web")) {
						WebFunction(function);
					}

				}
			}
		});
		bg1422609048781LinearLayout
				.addComponent(functionList2ListViewScrollPane);

		bg1422608973093LinearLayout.addGroup(bg1422609048781LinearLayout);

		bg1422608964921LinearLayout.addGroup(bg1422608973093LinearLayout);

		/** imgTextView */
		GroupLayout.ParallelGroup bg1422609099562LinearLayout = layout
				.createParallelGroup();
		/** 图片 */
		imgTextView = new JLabel("");
		JScrollPane imgScrollPane = new JScrollPane(imgTextView);
		imgScrollPane.setPreferredSize(new Dimension(300, 300));
		bg1422609099562LinearLayout.addComponent(imgScrollPane);

		bg1422608964921LinearLayout.addGroup(bg1422609099562LinearLayout);

		layout.setVerticalGroup(bg1422608964921LinearLayout);

		// 水平
		/** imgTextView bg1422609123140 */
		GroupLayout.ParallelGroup bg1422609130625 = layout
				.createParallelGroup();
		/** 图片 */
		bg1422609130625.addComponent(imgScrollPane);

		/** bg1422609118578 bg1422609121187 */
		GroupLayout.SequentialGroup bg1422609123140 = layout
				.createSequentialGroup();
		/** functionTitleTextView functionListListView */
		GroupLayout.ParallelGroup bg1422609118578 = layout
				.createParallelGroup();
		/** 功能 */
		bg1422609118578.addComponent(functionTitleTextView);

		/** 功能列表 */
		bg1422609118578.addComponent(functionListListViewScrollPane);

		bg1422609123140.addGroup(bg1422609118578);

		/** function2TitleTextView functionList2ListView */
		GroupLayout.ParallelGroup bg1422609121187 = layout
				.createParallelGroup();
		/** 功能2 */
		bg1422609121187.addComponent(function2TitleTextView);

		/** 功能列表2 */
		bg1422609121187.addComponent(functionList2ListViewScrollPane);

		bg1422609123140.addGroup(bg1422609121187);

		bg1422609130625.addGroup(bg1422609123140);

		layout.setHorizontalGroup(bg1422609130625);

		return panel;
	}

	/**
	 * 定制swing里面dblist的cell单元格如何显示
	 * */
	class FunctionListCell extends JLabel implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			// TODO Auto-generated method stub
			Function fun = (Function) value;
			setText(fun.name);

			// 判断是否选中
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			setEnabled(list.isEnabled());
			setFont(list.getFont());
			setOpaque(true);
			return this;
		}

	}

	public void androidFunction(Function function) {
		if (function.id.equals("1")) {// 日期选择

			DateSelect dateSelect = new DateSelect(currentCodeFileFullPath);

			Object[] options = { "添加", "恢复" };
			int response = JOptionPane.showOptionDialog(this, "添加或删除"
					+ function.name + "功能", function.name,
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				backupBeforeModify = FileUtil
						.fileContent(currentCodeFileFullPath);
				dateSelect.add();
				editorValueEditText.setText(FileUtil
						.fileContent(currentCodeFileFullPath));
				JOptionPane.showMessageDialog(null, "请查看文件，有异常请进行\"恢复\"操作。",
						"温馨提示", JOptionPane.INFORMATION_MESSAGE);
			} else if (response == 1) {
				if (!backupBeforeModify.equals("")) {
					FileUtil.makeFile(currentCodeFileFullPath,
							backupBeforeModify);
					editorValueEditText.setText(FileUtil
							.fileContent(currentCodeFileFullPath));
					backupBeforeModify = "";
				}
			}

		} else if (function.id.equals("2")) {// 分页(列表)

			Object[] options = { "添加", "恢复" };
			int response = JOptionPane.showOptionDialog(this, "添加或删除"
					+ function.name + "功能", function.name,
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				backupBeforeModify = FileUtil
						.fileContent(currentCodeFileFullPath);
				Paging paging = new Paging(currentCodeFileFullPath);
				editorValueEditText.setText(FileUtil
						.fileContent(currentCodeFileFullPath));

				JOptionPane.showMessageDialog(null, "请查看文件，有异常请进行\"恢复\"操作。",
						"温馨提示", JOptionPane.INFORMATION_MESSAGE);
			} else if (response == 1) {
				if (!backupBeforeModify.equals("")) {
					FileUtil.makeFile(currentCodeFileFullPath,
							backupBeforeModify);
					editorValueEditText.setText(FileUtil
							.fileContent(currentCodeFileFullPath));
					backupBeforeModify = "";
				}
			}
		} else if (function.id.equals("3")) {// 网络请求,响应,等待提示
			Object[] options = { "添加", "恢复" };
			int response = JOptionPane.showOptionDialog(this, "添加或删除"
					+ function.name + "功能", function.name,
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {

				InterfaceDocDialog projectDocPanel = new InterfaceDocDialog(
						 false,"接口文档");
				projectDocPanel.setModal(true);
				projectDocPanel.setVisible(true);

				if (projectDocPanel.selects == null) {
					JOptionPane.showMessageDialog(null, "没有选择接口,请重试", "温馨提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				backupBeforeModify = FileUtil
						.fileContent(currentCodeFileFullPath);
				RequestAndroid paging = new RequestAndroid(
						currentCodeFileFullPath, projectDocPanel);
				editorValueEditText.setText(FileUtil
						.fileContent(currentCodeFileFullPath));

			} else if (response == 1) {

				if (!backupBeforeModify.equals("")) {
					FileUtil.makeFile(currentCodeFileFullPath,
							backupBeforeModify);
					editorValueEditText.setText(FileUtil
							.fileContent(currentCodeFileFullPath));
					backupBeforeModify = "";
				}
			}

		} else if (function.id.equals("10")) {// 报表列表(首列固定左右滑动 首行固定上下滑)
			Object[] options = { "添加", "恢复" };
			int response = JOptionPane.showOptionDialog(this, "添加或删除"
					+ function.name + "功能", function.name,
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {

				backupBeforeModify = FileUtil
						.fileContent(currentCodeFileFullPath);
				ReportList paging = new ReportList(currentCodeFileFullPath);
				editorValueEditText.setText(FileUtil
						.fileContent(currentCodeFileFullPath));

			} else if (response == 1) {

				if (!backupBeforeModify.equals("")) {
					FileUtil.makeFile(currentCodeFileFullPath,
							backupBeforeModify);
					editorValueEditText.setText(FileUtil
							.fileContent(currentCodeFileFullPath));
					backupBeforeModify = "";
				}
			}

		} else if (function.id.equals("41")) {// Dialog
												// msg:title:leftBtn:rightBtn:rightBtn2
			Object[] options = { "添加", "恢复" };
			int response = JOptionPane.showOptionDialog(this, "添加或删除"
					+ function.name + "功能", function.name,
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {

				backupBeforeModify = FileUtil
						.fileContent(currentCodeFileFullPath);
				MyDialog paging = new MyDialog(currentCodeFileFullPath,
						whichLine, "1");
				editorValueEditText.setText(FileUtil
						.fileContent(currentCodeFileFullPath));

			} else if (response == 1) {

				if (!backupBeforeModify.equals("")) {
					FileUtil.makeFile(currentCodeFileFullPath,
							backupBeforeModify);
					editorValueEditText.setText(FileUtil
							.fileContent(currentCodeFileFullPath));
					backupBeforeModify = "";
				}
			}

		} else if (function.id.equals("42")) {// Dialog msg:list
			Object[] options = { "添加", "恢复" };
			int response = JOptionPane.showOptionDialog(this, "添加或删除"
					+ function.name + "功能", function.name,
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {

				backupBeforeModify = FileUtil
						.fileContent(currentCodeFileFullPath);
				MyDialog paging = new MyDialog(currentCodeFileFullPath,
						whichLine, "2");
				editorValueEditText.setText(FileUtil
						.fileContent(currentCodeFileFullPath));

			} else if (response == 1) {

				if (!backupBeforeModify.equals("")) {
					FileUtil.makeFile(currentCodeFileFullPath,
							backupBeforeModify);
					editorValueEditText.setText(FileUtil
							.fileContent(currentCodeFileFullPath));
					backupBeforeModify = "";
				}
			}

		} else if (function.id.equals("43")) {// Dialog msg:editText
			Object[] options = { "添加", "恢复" };
			int response = JOptionPane.showOptionDialog(this, "添加或删除"
					+ function.name + "功能", function.name,
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {

				backupBeforeModify = FileUtil
						.fileContent(currentCodeFileFullPath);
				MyDialog paging = new MyDialog(currentCodeFileFullPath,
						whichLine, "3");
				editorValueEditText.setText(FileUtil
						.fileContent(currentCodeFileFullPath));

			} else if (response == 1) {

				if (!backupBeforeModify.equals("")) {
					FileUtil.makeFile(currentCodeFileFullPath,
							backupBeforeModify);
					editorValueEditText.setText(FileUtil
							.fileContent(currentCodeFileFullPath));
					backupBeforeModify = "";
				}
			}

		}

	}

	public void iphoneFunction(Function function) {

		if (function.id.equals("3")) {// 网络请求,响应,等待提示
			Object[] options = { "添加", "恢复" };
			int response = JOptionPane.showOptionDialog(this, "添加或删除"
					+ function.name + "功能", function.name,
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {

				InterfaceDocDialog projectDocPanel = new InterfaceDocDialog(
						 false,"接口文档");
				projectDocPanel.setModal(true);
				projectDocPanel.setVisible(true);

				if (projectDocPanel.selects == null) {
					JOptionPane.showMessageDialog(null, "没有选择接口,请重试", "温馨提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				backupBeforeModify = FileUtil
						.fileContent(currentCodeFileFullPath);
				RequestIphone paging = new RequestIphone(
						currentCodeFileFullPath, projectDocPanel);
				editorValueEditText.setText(FileUtil
						.fileContent(currentCodeFileFullPath));

			} else if (response == 1) {

				if (!backupBeforeModify.equals("")) {
					FileUtil.makeFile(currentCodeFileFullPath,
							backupBeforeModify);
					editorValueEditText.setText(FileUtil
							.fileContent(currentCodeFileFullPath));
					backupBeforeModify = "";
				}
			}

		}
	}

	public void WebManageFunction(Function function) {

		if (function.id.equals("1")) {//jsp struct2 spring hibernate 管理端
			InterfaceDocDialog projectDocPanel = new InterfaceDocDialog(
					true,"数据库文档");
			projectDocPanel.setModal(true);
			projectDocPanel.setVisible(true);

			if (projectDocPanel.interfaceBeans != null) {

				// 接口列表
				new ActionStruct2(projectDocPanel.interfaceBeans);

				new UpdateJspStruct2(projectDocPanel.interfaceBeans);

				new QueryJspStruct2(projectDocPanel.interfaceBeans);

				new TableToHibernateEntity(projectDocPanel.interfaceBeans);

				new AddJspStruct2(projectDocPanel.interfaceBeans);
				// new MenuJsp(projectDocPanel.interfaceBeans);
			}
		}
		
		if (function.id.equals("2")) {//jsp struct2 mybatis 管理端
			InterfaceDocDialog projectDocPanel = new InterfaceDocDialog(
					true,"数据库文档");
			projectDocPanel.setModal(true);
			projectDocPanel.setVisible(true);

			if (projectDocPanel.interfaceBeans != null) {

				// 接口列表
				new Struct2ActionForWebManage(projectDocPanel.interfaceBeans);

				new UpdateJspForWebManage(projectDocPanel.interfaceBeans);

				new QueryJspForWebManage(projectDocPanel.interfaceBeans);
				
				new AddJspForWebManage(projectDocPanel.interfaceBeans);
				// new MenuJsp(projectDocPanel.interfaceBeans);
			}
		}
		
		if (function.id.equals("3")) {//jsp servelet mybatis 管理端
			InterfaceDocDialog projectDocPanel = new InterfaceDocDialog(
					true,"数据库文档");
			projectDocPanel.setModal(true);
			projectDocPanel.setVisible(true);

			if (projectDocPanel.interfaceBeans != null) {

				// 接口列表
				new ServletActionForWebManage(projectDocPanel.interfaceBeans);
				new LocalApplicationTest(projectDocPanel.interfaceBeans);

				new UpdateJspForServlet(projectDocPanel.interfaceBeans);

				new QueryJspForServlet(projectDocPanel.interfaceBeans);
				
				new AddJspForServlet(projectDocPanel.interfaceBeans);
				// new MenuJsp(projectDocPanel.interfaceBeans);
			}
		}
	}

	public void WebFunction(Function function) {

		if (function.id.equals("1")) {// 网络请求,响应,等待提示

			// 网络请求,响应,等待提示
			Object[] options = { "添加", "恢复" };
			int response = JOptionPane.showOptionDialog(this, "添加或删除"
					+ function.name + "功能", function.name,
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				//

				InterfaceDocDialog projectDocPanel = new InterfaceDocDialog(
						 false,"接口文档");
				projectDocPanel.setModal(true);
				projectDocPanel.setVisible(true);

				if (projectDocPanel.selects == null) {
					JOptionPane.showMessageDialog(null, "没有选择接口,请重试", "温馨提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				backupBeforeModify = FileUtil
						.fileContent(currentCodeFileFullPath);
				new WebRequestRespond(currentCodeFileFullPath, projectDocPanel);
				editorValueEditText.setText(FileUtil
						.fileContent(currentCodeFileFullPath));

			} else if (response == 1) {

				if (!backupBeforeModify.equals("")) {
					FileUtil.makeFile(currentCodeFileFullPath,
							backupBeforeModify);
					editorValueEditText.setText(FileUtil
							.fileContent(currentCodeFileFullPath));
					backupBeforeModify = "";
				}
			}

		}

		if (function.id.equals("2")) {// ssm接口请求响应
			TableDocToInterfaceService frame = new TableDocToInterfaceService("springmvc_spring_mybatis");
			frame.setVisible(true);
		}
		
		if (function.id.equals("3")) {// servlet mybatis接口请求响应
			TableDocToInterfaceService frame = new TableDocToInterfaceService("servlet_mybatis");
			frame.setVisible(true);
		}
//		
//		if (function.id.equals("4")) {// struct2 mybatis接口请求响应
//			TableDocToInterfaceService frame = new TableDocToInterfaceService("struct2_mybatis");
//			frame.setVisible(true);
//		}
		
		if (function.id.equals("6")) {// servlet_jdbc接口请求响应
			TableDocToInterfaceService frame = new TableDocToInterfaceService("servlet_jdbc");
			frame.setVisible(true);
		}
		if (function.id.equals("7")) {// servlet_hibernate接口请求响应
			TableDocToInterfaceService frame = new TableDocToInterfaceService("servlet_hibernate");
			frame.setVisible(true);
		}
		if (function.id.equals("5")) {// struct2_hibernate接口请求响应
			TableDocToInterfaceService frame = new TableDocToInterfaceService("struct2_hibernate");
			frame.setVisible(true);
		}
	
	
	}

	public void swingFunction(Function function) {
		if (function.id.equals("41")) {// 系统对话框提示(单个按钮)
			SystemDialog dialog = new SystemDialog();
			Object[] options = { "添加", "恢复" };
			int response = JOptionPane.showOptionDialog(this, "添加或删除"
					+ function.name + "功能", function.name,
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				dialog.msg();
			} else if (response == 1) {

			}
		} else if (function.id.equals("42")) {// 系统对话框提示(左右按钮)
			SystemDialog dialog = new SystemDialog();
			Object[] options = { "添加", "恢复" };
			int response = JOptionPane.showOptionDialog(this, "添加或删除"
					+ function.name + "功能", function.name,
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				dialog.confirm();
			} else if (response == 1) {

			}
		} else if (function.id.equals("43")) {// 系统对话框选择(列表)"
			SystemDialog dialog = new SystemDialog();
			Object[] options = { "添加", "恢复" };
			int response = JOptionPane.showOptionDialog(this, "添加或删除"
					+ function.name + "功能", function.name,
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				dialog.inputListSelect();
			} else if (response == 1) {

			}
		} else if (function.id.equals("44")) {// 系统对话框输入
			SystemDialog dialog = new SystemDialog();
			Object[] options = { "添加", "恢复" };
			int response = JOptionPane.showOptionDialog(this, "添加或删除"
					+ function.name + "功能", function.name,
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[0]);
			if (response == 0) {
				dialog.inputText();
			} else if (response == 1) {

			}
		}
	}

	public void androidData() {
		functionParents.clear();

		Function dateParent = new Function("1", "日期选择");

		functionParents.add(dateParent);
		ArrayList<Function> dateChirlds = new ArrayList<Function>();
		dateParent.chirlds = dateChirlds;

		functionParents.add(new Function("3", "网络请求,响应,等待提示"));

		functionParents.add(new Function("2", "分页(列表)"));

		functionParents.add(new Function("10", "报表(首列固定左右滑动 首行固定上下滑)"));

		Function dialogParent = new Function("4", "对话框");
		functionParents.add(dialogParent);
		ArrayList<Function> dialogChirlds = new ArrayList<Function>();
		dialogParent.chirlds = dialogChirlds;
		dialogChirlds.add(new Function("41",
				"系统对话框提示(title:msg:leftBtn:rightBtn:rightBtn2)"));
		dialogChirlds.add(new Function("42", "系统对话框提示(title:list)"));
		dialogChirlds.add(new Function("43", "系统对话框输入"));
		dialogChirlds.add(new Function("45", "自定义对话框提示(单个按钮)"));
		dialogChirlds.add(new Function("46", "自定义对话框提示(左右按钮)"));
		dialogChirlds.add(new Function("47", "自定义对话框选择(列表)"));
		dialogChirlds.add(new Function("48", "自定义对话框输入"));

		Function pageListenerParent = new Function("5", "页面监听");
		functionParents.add(pageListenerParent);
		ArrayList<Function> pageListenerChirlds = new ArrayList<Function>();
		pageListenerParent.chirlds = pageListenerChirlds;
		pageListenerChirlds.add(new Function("51", "Button"));
		pageListenerChirlds.add(new Function("52", "EditText"));
		pageListenerChirlds.add(new Function("53", "list"));
		pageListenerChirlds.add(new Function("54", "ImageView"));

		Function pageJumpParent = new Function("6", "页面跳转");
		functionParents.add(pageJumpParent);
		ArrayList<Function> pageJumpChirlds = new ArrayList<Function>();
		pageJumpParent.chirlds = pageJumpChirlds;
		pageJumpChirlds.add(new Function("61", "带自定义对象"));
		pageJumpChirlds.add(new Function("62", "带普通对象(String,int)"));

		Function checkParent = new Function("7", "字符检查");
		functionParents.add(checkParent);
		ArrayList<Function> checkChirlds = new ArrayList<Function>();
		checkParent.chirlds = checkChirlds;
		checkChirlds.add(new Function("71", "数字检查"));
		checkChirlds.add(new Function("72", "中文检查"));
		checkChirlds.add(new Function("73", "英文检查"));

		functionParents.add(new Function("8", "不显示Title行"));
		functionParents.add(new Function("9", "日期格式化"));

	}

	public void iphoneData() {
		functionParents.clear();
		functionParents.add(new Function("3", "网络请求,响应,等待提示"));

	}

	public void swingData() {
		functionParents.clear();

		Function dialogParent = new Function("4", "对话框");
		functionParents.add(dialogParent);
		ArrayList<Function> dialogChirlds = new ArrayList<Function>();
		dialogParent.chirlds = dialogChirlds;
		dialogChirlds.add(new Function("41", "系统对话框提示(单个按钮)"));
		dialogChirlds.add(new Function("42", "系统对话框提示(左右按钮)"));
		dialogChirlds.add(new Function("43", "系统对话框选择(列表)"));
		dialogChirlds.add(new Function("44", "系统对话框输入"));
		dialogChirlds.add(new Function("45", "自定义对话框提示(单个按钮)"));
		dialogChirlds.add(new Function("46", "自定义对话框提示(左右按钮)"));
		dialogChirlds.add(new Function("47", "自定义对话框选择(列表)"));
		dialogChirlds.add(new Function("48", "自定义对话框输入"));

	}

	public void WebManageData() {
		functionParents.clear();
		functionParents.add(new Function("1", "ssh查询新增修改"));
		functionParents.add(new Function("2", "jspStruct2Mybatis查询新增修改"));
		functionParents.add(new Function("3", "jspServletMybatis查询新增修改"));
	}

	public void WebData() {
		functionParents.clear();
		functionParents.add(new Function("1", "网络请求,响应"));
		functionParents.add(new Function("2", "springmvc_spring_mybatis接口请求响应"));
		functionParents.add(new Function("3", "servlet_mybatis接口请求响应"));
		functionParents.add(new Function("6", "servlet_jdbc接口请求响应"));
		functionParents.add(new Function("7", "servlet_hibernate接口请求响应"));
		//functionParents.add(new Function("4", "struct2_mybatis接口请求响应"));
		functionParents.add(new Function("5", "struct2_hibernate接口请求响应"));
		
	}

	public void searchCodeFiles(String root) {
		File file = new File(root);
		File[] files = file.listFiles(new FileFilter() {
			public boolean accept(File fl) {
				return !fl.isDirectory();
			}
		});
		if (files == null) {
			codeFileListDate.clear();
			codeFileListListView.setListData(codeFileListDate.toArray());
			return;
		}
		codeFileListDate.clear();
		for (File f : files) {
			codeFileListDate.add(f.getName());
		}
		codeFileListListView.setListData(codeFileListDate.toArray());
		return;
	}

	/**
	 * 用于侦听文本组件插入符的位置更改的侦听器 获取当前光标在文件中的行号
	 */
	int whichLine = -1;

	class CaretLis_lineNo implements CaretListener {
		public void caretUpdate(CaretEvent e) {
			try {

				whichLine = editorValueEditText
						.getLineOfOffset(editorValueEditText.getCaretPosition()) + 1;
			} catch (BadLocationException eB) {
				System.out.println("IO Wrong");
			}
		}
	}

	public String getPicName(String fileName) {
		String picName = "";

		fileName = fileName.subSequence(0, fileName.indexOf(".")).toString();
		for (int i = 0; i < fileName.length(); i++) {

			if (fileName.charAt(i) >= 'A' && fileName.charAt(i) <= 'Z') {
				if (i == 0) {
					picName += String.valueOf(fileName.charAt(i)).toLowerCase();
				} else {
					picName += "_"
							+ String.valueOf(fileName.charAt(i)).toLowerCase();
				}
			} else if (fileName.charAt(i) >= 'a' && fileName.charAt(i) <= 'z') {
				picName += String.valueOf(fileName.charAt(i)).toLowerCase();
			}
		}

		return picName;
	}

	public static void main(String[] args) {
		new CodeFunctionAdd();
	}

	long clickTime = 0;

	public boolean checkClickTime() {
		long nowTime = (new Date()).getTime();

		if ((nowTime - clickTime) < 300) {

			clickTime = nowTime;
			return true;
		}
		clickTime = nowTime;
		return false;
	}
}