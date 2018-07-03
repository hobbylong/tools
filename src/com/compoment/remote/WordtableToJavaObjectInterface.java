package com.compoment.remote;



//http://wenku.baidu.com/link?url=ll3rEIIMCAr5m_T-F3rcvzawiI-pd5E5W2uxHBXTzHoQkSBMgQXdtnhBaU9VITz4neKofs_J66_OCR_QPpYz94QMVw6xBBkVqhDnMxkIgk_
/**
 * 名称 数据元素 类型 长度 备注 （0） （1） （2） （3） （4） 列用index标记
 * 
 * */



import java.io.File;
import java.io.FileInputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import com.compoment.remote.IphoneViewControllerXibInterface;



public interface WordtableToJavaObjectInterface extends Remote {



	public List wordAnalyse(byte[] file,Map point,boolean isTable)  throws RemoteException ;
	
}


