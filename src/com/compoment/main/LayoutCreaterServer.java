package com.compoment.main;



import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.swing.JFrame;

import com.compoment.addfunction.web.springmvcSpringMybatis.InterfaceServiceController_springmvcSpringMybatis;
import com.compoment.cut.CheckProblem;
import com.compoment.cut.android.AndroidLayoutXml;
import com.compoment.cut.iphone.IphoneViewControllerXib;
import com.compoment.jsonToJava.creater.WordtableToJavaObject;
import com.compoment.util.VersionCheck;
import com.compoment.workflow.ProjectFrame2;



public class LayoutCreaterServer {
	
	
	public static void main(String[] args) {

		
		LayoutCreaterServer layoutCreater=new LayoutCreaterServer();
		layoutCreater.remote();
		
		System.out.print("服务器启动成功");
	
	}
	
	
	public void remote()
	{
		

		/*
		 * If you want Serializable classes to be downloaded in the client machines
		 * you need to change the security level. This may not work, then you
		 * have to change the security policy.
		 */
//		System.setSecurityManager(new RMISecurityManager());
		
		System.out.println("RMI server started");
		
		try {
			/*
			 * Creates and exports a registry where objects will be registered,
			 * I choosed here port 1099 which is the default port where
			 * RMI Registry runs
			 */
			LocateRegistry.createRegistry(1099);
			System.out.println("Java RMI registry created.");
			
			CheckProblem obj = new CheckProblem();
			Naming.rebind("CheckProblem", obj);
			
			
			IphoneViewControllerXib iphoneViewControllerXib=new IphoneViewControllerXib();
			Naming.rebind("IphoneViewControllerXib", iphoneViewControllerXib);
			
			AndroidLayoutXml androidLayoutXml=new AndroidLayoutXml();
			Naming.rebind("AndroidLayoutXml", androidLayoutXml);
	
			VersionCheck versionCheck=new VersionCheck();
			Naming.rebind("VersionCheck", versionCheck);
			
			WordtableToJavaObject wordtableToJavaObject=new WordtableToJavaObject();
			Naming.rebind("WordtableToJavaObject", wordtableToJavaObject);
			
	
		} catch (RemoteException e) {
			System.out.println("Java RMI registry already exists.");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		UnicastRemoteObject.unexportObject(obj, true);
		System.out.println("RmiServer bound in registry");
	
	}
}
