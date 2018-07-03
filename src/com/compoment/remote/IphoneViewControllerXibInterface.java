package com.compoment.remote;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.Remote;
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

import org.apache.poi.poifs.property.Child;

import com.compoment.cut.CompomentBean;
import com.compoment.ui.ios.creater.ScrollViewCells;
import com.compoment.util.DeepCopy;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public    interface IphoneViewControllerXibInterface extends Remote{



	public void IphoneViewControllerXib(int cellWidth, int cellHeight) throws RemoteException;

	public String IphoneViewControllerXib(String pageName, List<CompomentBean> oldBeans) throws RemoteException;
	
	public String analyse(List<CompomentBean> oldBeans) throws RemoteException ;
	
	public String getConnection() throws RemoteException ;
	
	public CompomentBean getMaxBean() throws RemoteException ;
}
