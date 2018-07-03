package com.compoment.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.compoment.remote.IphoneViewControllerXibInterface;
import com.compoment.remote.VersionCheckInterface;

public class VersionCheck  extends UnicastRemoteObject implements VersionCheckInterface {
	public VersionCheck() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	String nowVersion="1.0";
	
	public String hasNewVersion(String currentVersion) throws RemoteException
	{
		if(!currentVersion.equals(nowVersion))
		{
			
			
			File file = FileUtil.findFile(new File(KeyValue.readCache("projectPath")), "DevelopHelper" + "." + "zip");

			try {
				
				
				 
		       	
		          //FileUtil fileUtil=new FileUtil();
		          //return fileUtil.fileToByte(file.getPath());
			
				return "http://39.108.233.7:8080/DeveloperHelperHomePage/app/DevelopHelper.zip";
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}else
			
		{
			return null;
		}
		
	}
	
	public boolean blackList(String macip) throws RemoteException
	{
		if(readBlackList(macip)==null ||"".equals(readBlackList(macip)))
		{
			
			writeBlackList(macip,macip);
			return false;
			
		}else
		{
			return false;
		}
		
	}
	
	
	
	
	public static String readBlackList(String key)
	{
		String courseFile = null;
		File directory = new File("");// 参数为空
		try {
			courseFile =directory.getCanonicalPath();
		

		String path = courseFile + "/res/other/blackList.txt";
		
		return KeyValue.readValue(path,key);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		return "";
	}
	
	public static void writeBlackList(String key,String value)
	{
		if(value==null || value.equals(""))
		{
			return;
		}
		String courseFile = null;
		File directory = new File("");// 参数为空
		try {
			courseFile =directory.getCanonicalPath();
		

		String path = courseFile + "/res/other/blackList.txt";
		
		KeyValue.writeProperties(path,key,value);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	}
}

