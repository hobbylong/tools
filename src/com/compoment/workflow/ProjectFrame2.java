package com.compoment.workflow;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import com.compoment.remote.RemoteUtil;
import com.compoment.remote.VersionCheckInterface;
import com.compoment.util.DeviceUtil;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.ZipUtil;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JButton;

//工程设置
public class ProjectFrame2 extends JFrame implements ClipboardOwner, DropTargetListener {

	private JPanel contentPane;
	private JTextField androidPathValueEditText;
	private JTextField iphonePathValueEditText;
	private JTextField swingPathValueEditText;
	private JTextField webPathValueEditText;
	private JList picListListView;
	private JCheckBox androidCheckBox;
	JCheckBox iphoneCheckBox;
	JCheckBox webCheckBox;
	JPopupMenu popupMenu;
	
	Object rightBtnSelectedName;

	ArrayList listDate = new ArrayList();

	String path;

	String osName;
	private Clipboard clipboard;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectFrame2 frame = new ProjectFrame2();
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
	public ProjectFrame2() {

		if(blackList())
		{
			System.exit(0);
			return ;
		}
		
		if(checkVersion())
		{
			System.exit(0);
			return ;
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 410);
		this.setTitle("IOS Android Web开发流程助手V1.0");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("项目路径");

		androidCheckBox = new JCheckBox("Android");

		androidPathValueEditText = new JTextField();
		androidPathValueEditText.setColumns(10);

		iphonePathValueEditText = new JTextField();
		iphonePathValueEditText.setColumns(10);

		iphoneCheckBox = new JCheckBox("Iphone");

		JCheckBox swingCheckBox = new JCheckBox("Swing");

		webCheckBox = new JCheckBox("Web");

		swingPathValueEditText = new JTextField();
		swingPathValueEditText.setColumns(10);

		webPathValueEditText = new JTextField();
		webPathValueEditText.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("原型图片");
		
		JScrollPane scrollPane = new JScrollPane();
		
		popupMenu = new JPopupMenu();
		JMenuItem menuitem=new JMenuItem("删除");
		popupMenu.add(menuitem); //添加菜单项Open
		menuitem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				FileUtil.deleteFile(KeyValue.readCache("picPath")+"/"+rightBtnSelectedName);
				
				
				searchPics(KeyValue.readCache("picPath"));
				
			}});
		
		JButton button = new JButton("功能管理");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new CodeFunctionAdd();
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNewLabel))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(swingCheckBox))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(iphoneCheckBox))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(webCheckBox))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(androidCheckBox))
								.addComponent(androidPathValueEditText, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
								.addComponent(iphonePathValueEditText, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
								.addComponent(swingPathValueEditText, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
								.addComponent(webPathValueEditText, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(button)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_5))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(androidCheckBox)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(androidPathValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(iphoneCheckBox)
							.addGap(5)
							.addComponent(iphonePathValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(swingCheckBox)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(swingPathValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(webCheckBox)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(webPathValueEditText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(scrollPane)))
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addComponent(button)
					.addContainerGap())
		);
		
				
				
				picListListView = new JList();
				scrollPane.setViewportView(picListListView);
		contentPane.setLayout(gl_contentPane);

		
		Properties props=System.getProperties(); //获得系统属性集    
		 osName = props.getProperty("os.name"); //操作系统名称    
		 String osArch = props.getProperty("os.arch"); //操作系统构架    
		 String osVersion = props.getProperty("os.version"); //操作系统版本 
		
		
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// 将剪贴板中内容的ClipboardOwner设置为自己
		// 这样当其中内容变化时，就会触发lostOwnership事件
		if(!osName.toLowerCase().contains("Windows".toLowerCase()))
		{
		//粘贴板重置
		String str = "";// 设置字符串
		StringSelection selection = new StringSelection(str);// 构建String数据类型
		clipboard.setContents(selection, selection);// 添加文本到系统剪切板
		clipboard.setContents(clipboard.getContents(null), this);
		}
	
		
		String classDir = "";
		File directory = new File("");// 参数为空
		try {
			classDir = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		KeyValue.writeCache("projectPath", classDir+"/res");
		//if(KeyValue.readCache("picPath")==null||"".equals(KeyValue.readCache("picPath")))
		KeyValue.writeCache("picPath", classDir+"/res/pic");
		
		init();

		
		
	  
	}

	public void init() {

		androidCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				String s = androidPathValueEditText.getText();
				if (s == null || s.equals("")) {
					if (androidCheckBox.isSelected())
						JOptionPane.showMessageDialog(null, "请输入android项目工程路径", null, JOptionPane.ERROR_MESSAGE);
					androidCheckBox.setSelected(false);
					return;
				}

				searchPics(KeyValue.readCache("picPath"));

				
				KeyValue.writeCache("compomentProjectAddress",KeyValue.readCache("projectPath")+"/other/mobile-android");
				if (KeyValue.readCache("compomentProjectAddress") == null
						|| KeyValue.readCache("compomentProjectAddress").equals("")) {
					String inputValue = JOptionPane.showInputDialog("请输入(mobile-android)Project路径");
					KeyValue.writeCache("compomentProjectAddress", inputValue);
				} else {

					String projectPath = KeyValue.readCache("compomentProjectAddress");
					if (FileUtil.isDirectory(projectPath + "/src/com/compoment")) {

					} else {
						
						String inputValue = JOptionPane.showInputDialog("请输入(mobile-android)Project路径");
						KeyValue.writeCache("compomentProjectAddress", inputValue);
					}
				}
			}
		});

		// android
		String projectpath = KeyValue.readCache("projectPath");
		if (projectpath != null && !projectpath.equals(""))
			androidPathValueEditText.setText(projectpath);
		Document doc = androidPathValueEditText.getDocument();

		// 添加DocumentListener监听器
		doc.addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				String s = androidPathValueEditText.getText();
				KeyValue.writeCache("projectPath", s);
				FileUtil.makeDir(new File(s));
				KeyValue.writeCache("picPath", s + "/pic");
				FileUtil.makeDir(new File(s+"/pic"));
				// searchPics(KeyValue.readCache("picPath"));

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// Iphone

		iphoneCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String s = iphonePathValueEditText.getText();
				if (s == null || s.equals("")) {
					if (iphoneCheckBox.isSelected())
						JOptionPane.showMessageDialog(null, "请输入iphone项目工程路径", null, JOptionPane.ERROR_MESSAGE);
					iphoneCheckBox.setSelected(false);
					return;
				}

				searchPics(KeyValue.readCache("picPath"));
			}
		});

		// Web

		webCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String s = webPathValueEditText.getText();
				if (s == null || s.equals("")) {
					if (webCheckBox.isSelected())
						JOptionPane.showMessageDialog(null, "请输入web项目工程路径", null, JOptionPane.ERROR_MESSAGE);
					webCheckBox.setSelected(false);
					return;
				}

				searchPics(KeyValue.readCache("picPath"));
			}
		});

		// Iphone
		if (projectpath != null && !projectpath.equals(""))
			iphonePathValueEditText.setText(projectpath);

		Document docIphone = iphonePathValueEditText.getDocument();

		// 添加DocumentListener监听器
		docIphone.addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				String s = iphonePathValueEditText.getText();

				KeyValue.writeCache("projectPath", s);
				FileUtil.makeDir(new File(s));
				KeyValue.writeCache("picPath", s + "/pic");
				FileUtil.makeDir(new File(s+"/pic"));

				// searchPics(KeyValue.readCache("picPath"));

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// Web
		if (projectpath != null && !projectpath.equals(""))
			webPathValueEditText.setText(projectpath);

		Document docWeb = webPathValueEditText.getDocument();

		// 添加DocumentListener监听器
		docWeb.addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				String s = webPathValueEditText.getText();

				
				KeyValue.writeCache("projectPath", s);
				FileUtil.makeDir(new File(s));
				KeyValue.writeCache("picPath", s + "/pic");
				FileUtil.makeDir(new File(s+"/pic"));

				// searchPics(KeyValue.readCache("picPath"));

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// Swing
		Document docSwing = swingPathValueEditText.getDocument();

		// 添加DocumentListener监听器
		docSwing.addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				String s = swingPathValueEditText.getText();

				KeyValue.writeCache("projectPath", s);
				FileUtil.makeDir(new File(s));
				KeyValue.writeCache("picPath", s + "/pic");
				FileUtil.makeDir(new File(s+"/pic"));
				
				// searchPics(KeyValue.readCache("picPath"));

			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// piclist

		picListListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		picListListView.setListData(listDate.toArray());

		picListListView.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent me) {
				if (checkClickTime()) {
					String fileName = picListListView.getSelectedValue().toString();
					PageFrame2 pageFrame2=new PageFrame2(path + "/" + fileName);
					pageFrame2.setVisible(true);
				}
				
				maybeShowPopup(me);
			}
		});
		
		
		//注册DropTarget，并将它与组件相连，处理哪个组件的相连    
        //即连通组件（第一个this）和Listener(第二个this)    
       new DropTarget(this,DnDConstants.ACTION_COPY_OR_MOVE, this, true);    
		

	}

	public ArrayList searchPics(String root)

	{

		path = root;

		listDate.clear();
		picListListView.setListData(listDate.toArray());

		File file = new File(root);

		File[] files = file.listFiles(new FileFilter() {

			public boolean accept(File fl) {
				return !fl.isDirectory();
			}

		});
		if (files == null) {

			androidCheckBox.setSelected(false);
			FileUtil.makeDir(file);
			JOptionPane.showMessageDialog(null, "pic文件夹以建立请放入原型图", "", JOptionPane.INFORMATION_MESSAGE);

			return null;
		}

		for (File f : files) {
			listDate.add(f.getName());

		}
		picListListView.setListData(listDate.toArray());
		return listDate;
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
	
	
	
	
	
	 //右键弹出菜单
    private void maybeShowPopup(MouseEvent e) {
    	
           if ( e.getButton() == MouseEvent.BUTTON3 ) {
        	   int index = picListListView.locationToIndex(e.getPoint());  
               picListListView.setSelectedIndex(index); 
        	   
            //获取选择项的值
             rightBtnSelectedName = picListListView.getModel().getElementAt(picListListView.getSelectedIndex());
            System.out.println(rightBtnSelectedName);
            popupMenu.show(e.getComponent(),e.getX(), e.getY());
           
          
           }
      }
	
	
	

	// 系统黏贴板变化
	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {
		// TODO Auto-generated method stub


		if (!webCheckBox.isSelected() )
		{
			JOptionPane.showMessageDialog(null, "请选择项目路径", null, JOptionPane.ERROR_MESSAGE);
			
			//重置
			if(!osName.toLowerCase().contains("Windows".toLowerCase()))
			{
			//粘贴板重置
			String str = "";// 设置字符串
			StringSelection selection = new StringSelection(str);// 构建String数据类型
			clipboard.setContents(selection, selection);// 添加文本到系统剪切板
			clipboard.setContents(clipboard.getContents(null), this);
			}
	
		return ;
		}
	
		try {

			// 如果不暂停一下，经常会抛出IllegalStateException
			// 猜测是操作系统正在使用系统剪切板，故暂时无法访问
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Transferable clipTf = clipboard.getContents(null);
			if (clipTf != null) {

				DataFlavor[] dataList = clipTf.getTransferDataFlavors();
				int wholeLength = 0;
				for (int i = 0; i < dataList.length; i++) {
					DataFlavor data = dataList[i];
					String type = data.getSubType();

					if (data.getSubType().equals("x-java-image")) {
						String fileName = JOptionPane.showInputDialog(null, "请输入图片名", "粘贴图片",
								JOptionPane.INFORMATION_MESSAGE);
						
						if(fileName==null)
						{
							//重置
							if(!osName.toLowerCase().contains("Windows".toLowerCase()))
							{
							//粘贴板重置
							String str = "";// 设置字符串
							StringSelection selection = new StringSelection(str);// 构建String数据类型
							clipboard.setContents(selection, selection);// 添加文本到系统剪切板
							clipboard.setContents(clipboard.getContents(null), this);
							}
							break;
						}
						if (fileName.indexOf(".png") == -1) {
							fileName = fileName + ".png";
						}

						Image image = (Image) clipTf.getTransferData(DataFlavor.imageFlavor);

						File file = new File(KeyValue.readCache("picPath") + "/" + fileName);
						if(!file.exists()){
							
							
						// 转成jpg
						// BufferedImage bufferedImage = new
						// BufferedImage(image.getWidth(null),
						// image.getHeight(null), BufferedImage.TYPE_INT_RGB);
						// 转成png
						BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
								BufferedImage.TYPE_INT_ARGB);
						Graphics2D g = bufferedImage.createGraphics();
						g.drawImage(image, null, null);
						// ImageIO.write((RenderedImage)bufferedImage, "jpg",
						// file);
						ImageIO.write((RenderedImage) bufferedImage, "png", file);

						searchPics(KeyValue.readCache("picPath"));
						}else
						{
							
							 JOptionPane.showMessageDialog(null, "文件已存在");
							 
						}
						
						
						//重置
						if(!osName.toLowerCase().contains("Windows".toLowerCase()))
						{
						//粘贴板重置
						String str = "";// 设置字符串
						StringSelection selection = new StringSelection(str);// 构建String数据类型
						clipboard.setContents(selection, selection);// 添加文本到系统剪切板
						clipboard.setContents(clipboard.getContents(null), this);
						}
					
						break;
					} else {
						// 在此可以判断剪贴板的其它类型，如果不是复制的rtf只在弹出提示框显示；
						System.out.println("剪贴板内容类型：" + data.getSubType());
						
//						JOptionPane.showMessageDialog(null, "剪贴板内容类型不是图片," + data.getSubType());
//
//						String str = "";// 设置字符串
//						StringSelection selection = new StringSelection(str);// 构建String数据类型
//						clipboard.setContents(selection, selection);// 添加文本到系统剪切板
//
//						clipboard.setContents(clipboard.getContents(null), this);
						
						break;
					}
				}
			}
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

	}

	/**
	 * 从剪切板获得文字。
	 */
	public static String getSysClipboardText() {
		String ret = "";
		Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
		// 获取剪切板中的内容
		Transferable clipTf = sysClip.getContents(null);

		if (clipTf != null) {
			// 检查内容是否是文本类型
			if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				try {
					ret = (String) clipTf.getTransferData(DataFlavor.stringFlavor);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return ret;
	}

	/**
	 * 将字符串复制到剪切板。
	 */
	public static void setSysClipboardText(String writeMe) {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable tText = new StringSelection(writeMe);
		clip.setContents(tText, null);
	}

	/**
	 * 从剪切板获得图片。
	 */
	public static Image getImageFromClipboard() throws Exception {
		Clipboard sysc = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable cc = sysc.getContents(null);
		if (cc == null)
			return null;
		else if (cc.isDataFlavorSupported(DataFlavor.imageFlavor))
			return (Image) cc.getTransferData(DataFlavor.imageFlavor);
		return null;
	}

	/**
	 * 复制图片到剪切板。
	 */
	public static void setClipboardImage(final Image image) {
		Transferable trans = new Transferable() {
			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[] { DataFlavor.imageFlavor };
			}

			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return DataFlavor.imageFlavor.equals(flavor);
			}

			public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
				if (isDataFlavorSupported(flavor))
					return image;
				throw new UnsupportedFlavorException(flavor);
			}

		};
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans, null);
	}
	
	
	
   //拖放文件
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void dragExit(DropTargetEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragOver(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		// TODO Auto-generated method stub
		
		if (!webCheckBox.isSelected())
		{
			JOptionPane.showMessageDialog(null, "请选择项目路径", null, JOptionPane.ERROR_MESSAGE);
			
			if(!osName.toLowerCase().contains("Windows".toLowerCase()))
			{
			//粘贴板重置
			String str = "";// 设置字符串
			StringSelection selection = new StringSelection(str);// 构建String数据类型
			clipboard.setContents(selection, selection);// 添加文本到系统剪切板
			clipboard.setContents(clipboard.getContents(null), this);
			}
			
		return ;
		}
       
		 try {
             Transferable tr = dtde.getTransferable();

             if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                 dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
              
                 List list = (List) (dtde.getTransferable()
                         .getTransferData(DataFlavor.javaFileListFlavor));
                 Iterator iterator = list.iterator();
                 while (iterator.hasNext()) {
                     File f = (File) iterator.next();
                  
                     
                     if(f.getAbsolutePath().toLowerCase().contains(".png")||f.getAbsolutePath().toLowerCase().contains(".jpg"))
             		{
						FileUtil.MoveFolderAndFileWithSelf(f.getAbsolutePath(),KeyValue.readCache("picPath"));
						
             		}
                 }
             	searchPics(KeyValue.readCache("picPath"));
                 dtde.dropComplete(true);
                 
             }else {
                 dtde.rejectDrop();
             }
         } catch (IOException ioe) {
             ioe.printStackTrace();
         } catch (UnsupportedFlavorException ufe) {
             ufe.printStackTrace();
         } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	public void dropActionChanged(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean blackList()
	{

		try {
		VersionCheckInterface vcheck = (VersionCheckInterface) Naming.lookup(RemoteUtil.rmiurl+"VersionCheck");
		
			return vcheck.blackList(DeviceUtil.getMac());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	//版本
	public boolean checkVersion()
	{
		
		
		try {
			VersionCheckInterface vcheck = (VersionCheckInterface) Naming.lookup(RemoteUtil.rmiurl+"VersionCheck");
			
			String content=vcheck.hasNewVersion("1.0");
			
			if(content==null)
			{
				
				return false;
			}else
			{
			
				int yes=JOptionPane.showConfirmDialog(null, "有新版本,请更新软件", "温馨提示", JOptionPane.DEFAULT_OPTION);
				
				
				
				if(yes==0)
				{
					//判断当前系统是否支持Java AWT Desktop扩展
			        if(java.awt.Desktop.isDesktopSupported()){
			            try{
			                //创建一个URI实例,注意不是URL
			                java.net.URI uri=java.net.URI.create(content);
			                //获取当前系统桌面扩展
			                java.awt.Desktop dp=java.awt.Desktop.getDesktop();
			                //判断系统桌面是否支持要执行的功能
			                if(dp.isSupported(java.awt.Desktop.Action.BROWSE)){
			                    //获取系统默认浏览器打开链接
			                    dp.browse(uri);
			                }
			            }catch(java.lang.NullPointerException e){
			                //此为uri为空时抛出异常
			            }catch(java.io.IOException e){
			                //此为无法获取系统默认浏览器
			            }
			        }
				
				
					
			          return true;		
				}
			}
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
		
		return false;
	}
}
