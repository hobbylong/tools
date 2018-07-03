package com.compoment.db.tabledocinterfacedoc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.compoment.addfunction.webmanage.StructAction;
import com.compoment.addfunction.webmanage.StructActionForm;
import com.compoment.addfunction.web.servlet.hibernate.InterfaceServiceController_servletHibernate;
import com.compoment.addfunction.web.servlet.jdbc.InterfaceServiceController_servletJdbc;
import com.compoment.addfunction.web.servletMybatis.InterfaceServiceController_servletMybatis;
import com.compoment.addfunction.web.springmvcSpringMybatis.InterfaceServiceController_springmvcSpringMybatis;
import com.compoment.addfunction.web.structs2.hibernate.InterfaceServiceController_stucts2Hibernate;
import com.compoment.addfunction.webmanage.AddJsp;
import com.compoment.addfunction.webmanage.QueryJsp;
import com.compoment.remote.AndroidLayoutXmlInterface;

import com.compoment.remote.RemoteUtil;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;
import com.compoment.workflow.InterfaceDocDialog;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class TableDocToInterfaceService extends JFrame {

	private JPanel contentPane;
	DBTablesPanel dbTablesPanel;
	DBTableRelativePanel dbTableRelativePanel;
	private JTextField sqlResultEditText;

	JButton addToRelateTablesView;
	JButton removeFromRelateTablesView;
	JButton queryRelateButton;
	JButton deleteRelateButton;
	JButton updateRelateButton;
	JButton addRelateButton;
	private JTextField interfaceName;
	private JTextField interfaceCnName;
	String type="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableDocToInterfaceService frame = new TableDocToInterfaceService("ssm");
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
	public TableDocToInterfaceService(String type) {
		this.type=type;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton getDBTableBtn = new JButton("DBTable");
		getDBTableBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getDBTables();
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		JPanel panel = new JPanel();

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		JLabel lblNewLabel = new JLabel("数据表（左键红色表示选中，右键绿色表示主表）");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel label = new JLabel("接口表关联(左键红色表示查询结果，右键绿色表示查询条件)");
		label.setFont(new Font("宋体", Font.PLAIN, 14));

		JPanel panel_1 = new JPanel();
		
		interfaceName = new JTextField();
        interfaceName.setColumns(15);
		
		JLabel lblNewLabel_1 = new JLabel("接口英文名:");
		
		JLabel label_1 = new JLabel("接口中文名:");
		
		interfaceCnName = new JTextField();
		interfaceCnName.setColumns(15);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED, 645, Short.MAX_VALUE)
										.addComponent(getDBTableBtn)
										.addGap(81))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1123, GroupLayout.PREFERRED_SIZE)
											.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1123, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(interfaceName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(interfaceCnName, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 677, GroupLayout.PREFERRED_SIZE)))
							.addGap(24))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(getDBTableBtn)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(interfaceName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(label_1))
						.addComponent(interfaceCnName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);

		queryRelateButton = new JButton("接口生成");

		deleteRelateButton = new JButton("批量接口生成");

		sqlResultEditText = new JTextField();
		sqlResultEditText.setColumns(10);

		JButton button = new JButton("连线回退");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int index = 0;

				if (dbTableRelativePanel.latestRelateColumnBean != null
						&& dbTableRelativePanel.latestRelateColumnBean.size() != 0) {
					int count = dbTableRelativePanel.latestRelateColumnBean.size();

					if (count < 1) {
						return;
					} else {
						index = count - 1;
					}
				} else {
					return;
				}

				TableColumnBean latestColumnBean = dbTableRelativePanel.latestRelateColumnBean.get(index);

				if (dbTableRelativePanel.tables != null && dbTableRelativePanel.tables.size() > 0) {
					for (TableBean table : dbTableRelativePanel.tables) {

						for (TableColumnBean column : table.columns) {

							if (latestColumnBean.columnCnName.equals(column.columnCnName)) {
								column.relateColumnBeans.clear();
								column.relateColumnBeans = new ArrayList();
								dbTableRelativePanel.latestRelateColumnBean.remove(index);

								dbTableRelativePanel.repaint();
								return;
							}

						}

					}
				}

			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(queryRelateButton)
							.addGap(18)
							.addComponent(deleteRelateButton)
							.addGap(170)
							.addComponent(button))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(6)
							.addComponent(sqlResultEditText, GroupLayout.DEFAULT_SIZE, 1113, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(queryRelateButton)
						.addComponent(deleteRelateButton)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sqlResultEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);

		dbTableRelativePanel = new DBTableRelativePanel();
		dbTableRelativePanel.setBackground(Color.LIGHT_GRAY);
		dbTableRelativePanel.setPreferredSize(new Dimension(2000, 1000));
		scrollPane_1.setViewportView(dbTableRelativePanel);

		// 选择到表关系页
		addToRelateTablesView = new JButton("");
		addToRelateTablesView
				.setIcon(new ImageIcon(TableDocToInterfaceService.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));

		// 从表关系页移除
		removeFromRelateTablesView = new JButton("");
		removeFromRelateTablesView
				.setIcon(new ImageIcon(TableDocToInterfaceService.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(116).addComponent(addToRelateTablesView)
						.addPreferredGap(ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
						.addComponent(removeFromRelateTablesView).addGap(136)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel
						.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(addToRelateTablesView).addComponent(removeFromRelateTablesView))
						.addContainerGap(11, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		dbTablesPanel = new DBTablesPanel();
		dbTablesPanel.setPreferredSize(new Dimension(2006, 1000));
		dbTablesPanel.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(dbTablesPanel);
		contentPane.setLayout(gl_contentPane);

		init();
	}

	public void init() {

		// 选择到表关系
		addToRelateTablesView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dbTableRelativePanel.setDBTables(dbTablesPanel.tables);

			
				
				dbTablesPanel.cleanSelectTables();
			}
		});

		// 从表关系页移除

		removeFromRelateTablesView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbTableRelativePanel.cleanDBTables();
			}
		});

		// 表关系查询
		queryRelateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Sql sql = new Sql();
				//String query = sql.createQuerySql(dbTableRelativePanel.tables);
				
				
			    if(interfaceName.getText()==null || "".equals(interfaceName.getText()))
			    {
			    	JOptionPane.showMessageDialog(null, "请填写接口英文名", "", JOptionPane.INFORMATION_MESSAGE);
					
			    	return;
			    }
			    if(interfaceCnName.getText()==null || "".equals(interfaceCnName.getText()))
			    {
			    	JOptionPane.showMessageDialog(null, "请填写接口中文名", "", JOptionPane.INFORMATION_MESSAGE);
					
			    	return;
			    }
			    
			    
			    boolean isMain=false;
			    if(dbTableRelativePanel.tables!=null &&dbTableRelativePanel.tables.size()>1)
			    {
			    	for(TableBean bean:dbTableRelativePanel.tables)
			    	{
			    		if(bean.isMainTable)
			    		{
			    			isMain=true;
			    			break;
			    		}
			    	}
			    }
			    if(isMain==false)
			    {
			    	JOptionPane.showMessageDialog(null, "缺少主表", "", JOptionPane.INFORMATION_MESSAGE);
					
			    	return;
			    }
			    
			    
			
			    try {
			    	if(type.equals("springmvc_spring_mybatis"))
			    	{
				InterfaceServiceController_springmvcSpringMybatis interfaceServiceController=new InterfaceServiceController_springmvcSpringMybatis();
				interfaceServiceController.createInterfaceService(interfaceName.getText(),interfaceCnName.getText(),dbTableRelativePanel.tables);
			    	}else if(type.equals("servlet_mybatis"))
			    	{
			    		InterfaceServiceController_servletMybatis interfaceServiceController=new InterfaceServiceController_servletMybatis();
					interfaceServiceController.createInterfaceService(interfaceName.getText(),interfaceCnName.getText(),dbTableRelativePanel.tables);
			    		
			    	}
//			    	else if(type.equals("struct2_mybatis"))
//			    	{
//			    		
//			    	}
			    	else if(type.equals("servlet_jdbc"))
			    	{
			    		InterfaceServiceController_servletJdbc interfaceServiceController=new InterfaceServiceController_servletJdbc();
					interfaceServiceController.createInterfaceService(interfaceName.getText(),interfaceCnName.getText(),dbTableRelativePanel.tables);
			    		
			    	}
			    	else if(type.equals("servlet_hibernate"))
			    	{
			    		InterfaceServiceController_servletHibernate interfaceServiceController=new InterfaceServiceController_servletHibernate();
					interfaceServiceController.createInterfaceService(interfaceName.getText(),interfaceCnName.getText(),dbTableRelativePanel.tables);
			    		
			    	}
			    	else if(type.equals("struct2_hibernate"))
			    	{
			    		InterfaceServiceController_stucts2Hibernate interfaceServiceController=new InterfaceServiceController_stucts2Hibernate();
					interfaceServiceController.createInterfaceService(interfaceName.getText(),interfaceCnName.getText(),dbTableRelativePanel.tables);
			    		
			    	}
  	
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "文件已生成查看目录", "", JOptionPane.INFORMATION_MESSAGE);
				

			}
		});

		// 批量接口生成
		
		deleteRelateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String indexjsp="";
				indexjsp+="<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"utf-8\"%>\n";
				indexjsp+="<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\n";
				indexjsp+="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n";
				indexjsp+="<html>\n";
				indexjsp+="<body>\n";
						
				    if(dbTableRelativePanel.tables!=null &&dbTableRelativePanel.tables.size()>0)
				    {
				    	for(TableBean bean:dbTableRelativePanel.tables)
				    	{
				    		List<TableBean> tables=new ArrayList();
				    		tables.add(bean);
				    		
				    		try {
				    			
				    		   	if(type.equals("springmvc_spring_mybatis"))
						    	{
				    		   		InterfaceServiceController_springmvcSpringMybatis interfaceServiceController=new InterfaceServiceController_springmvcSpringMybatis();
									interfaceServiceController.createInterfaceService(bean.tableEnName,bean.tableCnName,tables);
						    	}else if(type.equals("servlet_mybatis"))
						    	{
						    		InterfaceServiceController_servletMybatis interfaceServiceController=new InterfaceServiceController_servletMybatis();
									interfaceServiceController.createInterfaceService(bean.tableEnName,bean.tableCnName,tables);
						    		
						    	}
						      	else if(type.equals("servlet_jdbc"))
						    	{
						    		InterfaceServiceController_servletJdbc interfaceServiceController=new InterfaceServiceController_servletJdbc();
								interfaceServiceController.createInterfaceService(bean.tableEnName,bean.tableCnName,tables);
						    		
						    	}
						    	else if(type.equals("servlet_hibernate"))
						    	{
						    		InterfaceServiceController_servletHibernate interfaceServiceController=new InterfaceServiceController_servletHibernate();
								interfaceServiceController.createInterfaceService(bean.tableEnName,bean.tableCnName,tables);
						    		
						    	}
						    	else if(type.equals("struct2_hibernate"))
						    	{
						    		InterfaceServiceController_stucts2Hibernate interfaceServiceController=new InterfaceServiceController_stucts2Hibernate();
								interfaceServiceController.createInterfaceService(bean.tableEnName,bean.tableCnName,tables);
						    		
						    	}
				    		   	
				    		
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							indexjsp+="<a href=\"ProjectName/jsp/"+bean.tableEnName+"Test.jsp\">"+bean.tableCnName+"</a></h5>\n";
				    	}
				    }
				    
				    indexjsp+="</body>\n";
				    indexjsp+="</html>\n";
					FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
							 "index", "jsp", indexjsp);
			JOptionPane.showMessageDialog(null, "文件已生成查看目录", "", JOptionPane.INFORMATION_MESSAGE);
				
				
			}
		});

		

	}

	public void getDBTables() {
		InterfaceDocDialog projectDocPanel = new InterfaceDocDialog( true,"数据库文档");
		projectDocPanel.setModal(true);
		projectDocPanel.setVisible(true);

		if (projectDocPanel.interfaceBeans != null) {
			// 接口列表
			dbTablesPanel.setDBTables(projectDocPanel.interfaceBeans);

		}
	}
}
