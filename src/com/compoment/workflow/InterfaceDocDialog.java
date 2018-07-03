package com.compoment.workflow;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;

import com.compoment.jsonToJava.creater.RequestRespond;
import com.compoment.jsonToJava.creater.RequestRespondForIphone;
import com.compoment.jsonToJava.creater.RequestRespondParamBean;
import com.compoment.jsonToJava.creater.RequestRespondParamBeanForIphone;
import com.compoment.jsonToJava.creater.WordtableToJavaObject;
import com.compoment.remote.RemoteUtil;
import com.compoment.remote.WordtableToJavaObjectInterface;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.server.Serverlet;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.google.gson.Gson;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class InterfaceDocDialog extends JDialog {

	public final JPanel contentPanel = new JPanel();
	private JTextField pathValueEditText;
	private JTextField cnNameValueEditText;
	private JTextField enNameValueEditText;
	private JTextField typeValueEditText;
	private JTextField remarksValueEditText;
	private JTextField requestJsonValueEditText;
	private JTextField respondJsonValueEditText;
	private JTextField respondJsonDetailValueEditText;
	JButton tojavaButton;
	JList listListView;
	 String	sourceFile="";
	 String sourceFileexample="";
	
	public ArrayList listDate = new ArrayList();
	
	/**word文档解析出来的接口对象*/
	public List<InterfaceBean> interfaceBeans;//返回文档里的全部接口
	
	
	/**
	 * 接口列表多选的值   (f.id + "" + f.title);  列表页面里显示的   用于判断用户有没选  
	 * */
	public Object[] selects;
	
	
	public List selectInterfaceBeans;//返回选择了的接口
	
	
	
	boolean isTable;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InterfaceDocDialog dialog = new InterfaceDocDialog(false,"接口文档");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InterfaceDocDialog(boolean isTable,String title) {
	
		this.isTable=isTable;
		setBounds(100, 100, 567, 542);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		if(title==null)
		{
			title="接口文档";
		}
		JLabel lblNewLabel = new JLabel(title);
		
		JLabel lblNewLabel_1 = new JLabel(title+"路径      ");
		
		pathValueEditText = new JTextField();
		pathValueEditText.setColumns(10);
		
		String classDir = "";
		File directory = new File("");// 参数为空
		try {
			classDir = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
		if(this.isTable==false)
		{
	   	sourceFile = classDir + "/res/InterfaceDoc/"
				+ "wordTableToJaveObject.doc"; 
	   	sourceFileexample = classDir + "/res/InterfaceDoc/"
				+ "wordTableToJaveObject_example.doc"; 
		}else
		{
		  	sourceFile = classDir + "/res/InterfaceDoc/"
					+ "wordTableToJaveObject2.doc"; 
		   	sourceFileexample = classDir + "/res/InterfaceDoc/"
					+ "wordTableToJaveObject_example2.doc"; 
			
		}
	   KeyValue.writeCache("docPath",sourceFile);
		
		JLabel lblindex = new JLabel("中文名Index       ");
		
		JLabel lblindex_1 = new JLabel("变量Index          ");
		
		JLabel lblindex_2 = new JLabel("变量类型Index    ");
		
		JLabel lblindex_3 = new JLabel("备注Index/primary key");
		
		JLabel lblRequestjson = new JLabel("RequestJson       ");
		
		JLabel lblRespondjson = new JLabel("RespondJson简化");
		
		JLabel lblRespondjson_1 = new JLabel("RespondJson详细");
		
		cnNameValueEditText = new JTextField();
		cnNameValueEditText.setColumns(10);
		
		enNameValueEditText = new JTextField();
		enNameValueEditText.setColumns(10);
		
		typeValueEditText = new JTextField();
		typeValueEditText.setColumns(10);
		
		remarksValueEditText = new JTextField();
		remarksValueEditText.setColumns(10);
		
		requestJsonValueEditText = new JTextField();
		requestJsonValueEditText.setColumns(10);
		
		respondJsonValueEditText = new JTextField();
		respondJsonValueEditText.setColumns(10);
		
		respondJsonDetailValueEditText = new JTextField();
		respondJsonDetailValueEditText.setColumns(10);
		
		JPanel panel = new JPanel();
		
		JButton btnNewButton = new JButton("打开");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Desktop desktop=Desktop.getDesktop();
				
				try {
					desktop.open(new File(sourceFile));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_2 = new JButton("例子");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
Desktop desktop=Desktop.getDesktop();
				
				try {
					desktop.open(new File(sourceFileexample));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblindex_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(remarksValueEditText))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblRespondjson_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(respondJsonDetailValueEditText))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pathValueEditText, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblindex)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cnNameValueEditText))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblindex_1)
								.addComponent(lblindex_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(typeValueEditText, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
								.addComponent(enNameValueEditText)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRequestjson)
								.addComponent(lblRespondjson))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(respondJsonValueEditText, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
								.addComponent(requestJsonValueEditText)))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(pathValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblindex)
						.addComponent(cnNameValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblindex_1)
						.addComponent(enNameValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblindex_2)
						.addComponent(typeValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblindex_3)
						.addComponent(remarksValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRequestjson)
						.addComponent(requestJsonValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRespondjson)
						.addComponent(respondJsonValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRespondjson_1)
						.addComponent(respondJsonDetailValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
		);
		
		 tojavaButton = new JButton("生成");
		
		 
		JButton selectButton = new JButton("确定");
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(tojavaButton)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(selectButton)
					.addGap(43))
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tojavaButton)
						.addComponent(selectButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		 listListView = new JList();
		 scrollPane.setViewportView(listListView);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
		
		init();
	}
	
	
	
	public void init()
	{
		//接口文档路径
	
		Document doc = pathValueEditText.getDocument();

		// 添加DocumentListener监听器
		doc.addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				KeyValue.writeCache("docPath", pathValueEditText.getText());
				
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		
		
		read();
		
		
		//请求json格式
		requestJsonValueEditText.setText("{\"STARTRECORD\": \"0\",\"MAXRECORD\": \"6\",\"PAGE\":\"1\",\"PAGENUM\":\"6\"}");
		
		//响应json简化版
		String simpleRespondJson=KeyValue.readCache("SimpleRespondJson");
		if(simpleRespondJson==null ||(simpleRespondJson!=null && simpleRespondJson.equals("")))
		{
			simpleRespondJson="{\"returnCode\": \"0\",\"returnData\": {\"head\": { \"ret_msg\": \"成功\", \"ret_errcode\": \"000000\"}, \"body\":{} }}";
		}
		respondJsonValueEditText.setText(simpleRespondJson);

		//响应json详细版
		respondJsonDetailValueEditText.setText("{\"returnCode\": \"0\",\"returnMsg\": \"\",\"returnDate\": {\"HEAD\": {\"RET_MSG\": \"成功\",\"RET_ERR\": \"000000\"},\"product\": [{\"code\": \"13\",\"totalPrice\": 6},{\"code\": \"3\",\"totalPrice\": 3.22}]}}");
		
		
		
		//Button
		tojavaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KeyValue.writeCache("SimpleRespondJson",respondJsonValueEditText.getText());
				
				write();
				String s = pathValueEditText.getText();
                if(s==null || s.equals(""))
                {
                	
                JOptionPane.showMessageDialog(null, "接口文档路径不能为空");
					
                	return;
                }
				searchDoc(s);
			}
		});
		
		
	
		
			listListView.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
		
		
		listListView.setListData(listDate.toArray());
		listListView.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent even) {
				if(even.getValueIsAdjusting()==true)
				{//按下时
				selects = listListView.getSelectedValues();
				selectInterfaceBeans=new ArrayList();
				for(Object row:selects)
				{
					
				for(int i=0;i<interfaceBeans.size();i++)
				{
					
					if(((String)row).contains(interfaceBeans.get(i).id))
					{
						
						selectInterfaceBeans.add(interfaceBeans.get(i));
					}
				}
				}
			
				}
			}
		});
		
		
		
	}
	
	
	
	
	public void read() {
		pathValueEditText.setText(KeyValue.readCache("docPath"));
		
		String cn=KeyValue.readCache("中文名");
		if(cn!=null && !cn.equals(""))
		{
			cnNameValueEditText.setText(cn);
		}else
		{
			//中文名index
			cnNameValueEditText.setText("0");
		}
		
	
		
        String value=KeyValue.readCache("变量名");
        if(value!=null && !value.equals(""))
        {
		enNameValueEditText.setText(value);
        }else
        {
        	//变量index
			enNameValueEditText.setText("1");	
        }
        
        String type=KeyValue.readCache("类型");
        if(type!=null && !type.equals(""))
        {
		typeValueEditText.setText(type);
        }else
        {
        	
			//变量类型index
			typeValueEditText.setText("2");
        }

        String remark=KeyValue.readCache("备注");
        if(remark!=null && !remark.equals(""))
        {
		remarksValueEditText.setText(remark);
        }else
        {
        	
			//备注index
			remarksValueEditText.setText("4");
        }
        
        
	}
	
	public void write() {
		KeyValue.writeCache("docPath", pathValueEditText.getText().toString());
		KeyValue.writeCache("中文名", cnNameValueEditText.getText().toString());// 中文注解在表格的哪一列
		KeyValue.writeCache("变量名", enNameValueEditText.getText().toString());
		KeyValue.writeCache("类型", typeValueEditText.getText().toString());// 类型在表格的哪一列
		KeyValue.writeCache("备注", remarksValueEditText.getText().toString());
	}

	public void searchDoc(String filePath) {

		Map point = new HashMap();
		point.put("中文名",
				Integer.valueOf(cnNameValueEditText.getText().toString()));// 中文注解在表格的哪一列
		point.put("变量名",
				Integer.valueOf(enNameValueEditText.getText().toString()));
		point.put("类型", Integer.valueOf(typeValueEditText.getText().toString()));// 类型在表格的哪一列
		point.put("备注",
				Integer.valueOf(remarksValueEditText.getText().toString()));

		WordtableToJavaObjectInterface wordtable;
		try {
			wordtable = (WordtableToJavaObjectInterface) Naming.lookup(RemoteUtil.rmiurl+"WordtableToJavaObject");
			
			FileUtil fileUtil=new FileUtil();
			//WordtableToJavaObject wordtable2=new WordtableToJavaObject();
			interfaceBeans=wordtable.wordAnalyse(fileUtil.fileToByte(filePath),point,isTable);
			
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
		;
		

		listDate.clear();
		listListView.setListData(listDate.toArray());

		if (interfaceBeans == null) {

			return;
		}

		
		
		// 接口数据异常处理
		for (InterfaceBean interfaceBean : interfaceBeans) {
			if (interfaceBean != null && !interfaceBean.isTable) {
				if (interfaceBean.requestGroups != null
						&& interfaceBean.requestGroups.size() < 1) {// 请检查word中参数表是否有标题"传入参数"

					String err = "请检查word中接口 " + interfaceBean.id
							+ " 是否有标题\"传入参数\"";

					Object[] options = {"检查","不检查"};
					int response=JOptionPane.showOptionDialog(null, err, "温馨提示",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{ 
						return;
					}
					else if(response==1)
					{ 
						
					}
				}

				else if (interfaceBean.respondGroups != null
						&& interfaceBean.respondGroups.size() < 1) {// 请检查word中参数表是否有标题"传出参数"

					String err = "请检查word中接口 " + interfaceBean.id
							+ " 是否有标题\"传出参数\"";
					
					
					Object[] options = {"检查","不检查"};
					int response=JOptionPane.showOptionDialog(null, err, "温馨提示",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{ 
						return;
					}
					else if(response==1)
					{ 
						
					}
				}
			}

		}

		
		
		for (InterfaceBean f : interfaceBeans) {
			listDate.add(f.id + ":" + f.title);

		}
		listListView.setListData(listDate.toArray());
		
		
		
		RequestRespondParamBean requestRespond = new RequestRespondParamBean();
		RequestRespondParamBeanForIphone requestRespondForIphone = new RequestRespondParamBeanForIphone();
		requestRespond.requestRespondParamBean(interfaceBeans);
		requestRespondForIphone.requestRespondParamBean(interfaceBeans);
		
		
		for(InterfaceBean bean:interfaceBeans )
		{
		
				Serverlet serverlet=new Serverlet();
				serverlet.create(respondJsonValueEditText.getText(), bean);	  
			 
		  
		}
		
		//InterfaceDoc.this.setVisible(false);

	}
	
	/**android request*/
	public String  requestFunction(String id)
	{
		for(InterfaceBean bean:interfaceBeans )
		{
		  if(bean.id.equals(id))
		  {

		RequestRespond rr = new RequestRespond();
		return rr.request(bean);
		

		  }
		}
		return "";
	}
	
	/**android respond*/
	public String  respondFunction(String id)
	{
		for(InterfaceBean bean:interfaceBeans )
		{
		  if(bean.id.equals(id))
		  {
		RequestRespond rr = new RequestRespond();
		return rr.respond(bean);
		  }
		}
		return "";
	}
	
	
	/**iphone request*/
	public String  requestFunctionForIphone(String id)
	{
		for(InterfaceBean bean:interfaceBeans )
		{
		  if(bean.id.equals(id))
		  {

		RequestRespondForIphone rr = new RequestRespondForIphone();
		return rr.request(bean);
		

		  }
		}
		return "";
	}
	
	/**iphone respond*/
	public String  respondFunctionForIphone(String id,boolean isNineList)
	{
		for(InterfaceBean bean:interfaceBeans )
		{
		  if(bean.id.equals(id))
		  {
			  RequestRespondForIphone rr = new RequestRespondForIphone();
		      return rr.respond(respondJsonValueEditText.getText(),bean,isNineList);
		  }
		}
		return "";
	}
	
	
	
	public String  serverlet(String id)
	{
		for(InterfaceBean bean:interfaceBeans )
		{
		  if(bean.id.equals(id))
		  {
				Serverlet serverlet=new Serverlet();
				serverlet.create(respondJsonValueEditText.getText(), bean);	  
			 
		  }
		}
		return "";
	}
}
