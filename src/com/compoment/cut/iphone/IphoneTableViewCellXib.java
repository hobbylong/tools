package com.compoment.cut.iphone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

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
import com.compoment.remote.IphoneViewControllerXibInterface;
import com.compoment.remote.RemoteUtil;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class IphoneTableViewCellXib {

	String bodym= "\n\n\n";
	String connection = "";
	String pageName = "";
    String className="";
    String tableViewCellContentViewId="";
    String tableViewCellid="";
    String type;

    
	public IphoneTableViewCellXib(String pageName,List<CompomentBean> oldBeans,String type) {
		//type= TableViewCell  TableViewHeadCell   ScrollViewCell  
		this.type=type;
		this.pageName=pageName;
		className=firstCharToUpperAndJavaName(pageName);
		
		String body="";
		 CompomentBean maxBean = null ;
		//rmi://120.76.232.114:1099/checkProblem
		try {
		
			
			 IphoneViewControllerXibInterface iphoneViewControllerXibForHorizontallayout=(IphoneViewControllerXibInterface) Naming.lookup(RemoteUtil.rmiurl+"IphoneViewControllerXib");
					 
			 iphoneViewControllerXibForHorizontallayout.IphoneViewControllerXib(320,40);
				
				 body=iphoneViewControllerXibForHorizontallayout.analyse(oldBeans);
				connection=iphoneViewControllerXibForHorizontallayout.getConnection();
				  maxBean = iphoneViewControllerXibForHorizontallayout.getMaxBean();
				 
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
		
	  


		String m="";
		
		m+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n";
		m+="<document type=\"com.apple.InterfaceBuilder3.CocoaTouch.XIB\" version=\"3.0\" toolsVersion=\"6751\" systemVersion=\"14D131\" targetRuntime=\"iOS.CocoaTouch\" propertyAccessControl=\"none\" useAutolayout=\"YES\" useTraitCollections=\"YES\">\n";
		m+="    <dependencies>\n";
		m+="        <deployment identifier=\"iOS\"/>\n";
		m+="        <plugIn identifier=\"com.apple.InterfaceBuilder.IBCocoaTouchPlugin\" version=\"6736\"/>\n";
		m+="        <capability name=\"Constraints to layout margins\" minToolsVersion=\"6.0\"/>\n";
		m+="        <capability name=\"Constraints with non-1.0 multipliers\" minToolsVersion=\"5.1\"/>\n";
		m+="    </dependencies>\n";
		m+="    <objects>\n";
		m+="        <placeholder placeholderIdentifier=\"IBFilesOwner\" id=\"-1\" userLabel=\"File's Owner\"/>\n";
		m+="        <placeholder placeholderIdentifier=\"IBFirstResponder\" id=\"-2\" customClass=\"UIResponder\"/>\n";
		 tableViewCellid=id();
		tableViewCellContentViewId=id();
		m+="        <tableViewCell contentMode=\"scaleToFill\" selectionStyle=\"blue\" indentationWidth=\"0.0\" rowHeight=\""+maxBean.h+"\" id=\""+tableViewCellid+"\" customClass=\""+className+type+"\">\n";
		m+="            <rect key=\"frame\" x=\"0.0\" y=\"0.0\" width=\"320\" height=\""+maxBean.h+"\"/>\n";
		m+="            <autoresizingMask key=\"autoresizingMask\"/>\n";
		m+="            <tableViewCellContentView key=\"contentView\" opaque=\"NO\" clipsSubviews=\"YES\" multipleTouchEnabled=\"YES\" contentMode=\"center\" tableViewCell=\""+tableViewCellid+"\" id=\""+tableViewCellContentViewId+"\">\n";
		m+="                <autoresizingMask key=\"autoresizingMask\"/>\n";
		m+="                <subviews>\n";

		m+=body;
		
		m+=" </subviews>\n";
		

		
		m+="   </tableViewCellContentView>\n";
		m+="            <inset key=\"separatorInset\" minX=\"0.0\" minY=\"0.0\" maxX=\"0.0\" maxY=\"0.0\"/>\n";
		m+=" <connections>\n";
        m+=connection;
        m+="</connections>\n";
		m+="        </tableViewCell>\n";
		m+="    </objects>\n";
		m+="</document>\n";

		
		System.out.println(m);
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/ios", className+type,
				"xib", m);

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


	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String genID(int length) // 参数为返回随机数的长度
	{
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}

	public static String id() {

		return genID(3) + "-" + genID(2) + "-" + genID(3);
	}
}
