package com.compoment.remote;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.compoment.cut.CompomentBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;



public interface CheckProblemInterface extends Remote{


	public List<CompomentBean> analyseRelative( List<CompomentBean> oldbeans) throws RemoteException; 
	
	
	
	 
	public  boolean check(List<CompomentBean> old) throws RemoteException; 
	
	
	
}
