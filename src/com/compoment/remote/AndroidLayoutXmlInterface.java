package com.compoment.remote;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.compoment.cut.CompomentBean;


import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.SerializeToFile;

public interface AndroidLayoutXmlInterface extends Remote{


	public String analyseRelative(String filename, List<CompomentBean> abeans) throws RemoteException;

	public List  getBeans() throws RemoteException;
	
	
	
}
