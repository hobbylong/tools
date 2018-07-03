package com.compoment.util;

import java.net.NetworkInterface;
import java.util.Enumeration;

public class DeviceUtil {

	public static String getMac()
	{
		   try {  
	              Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();  
	              StringBuilder builder = new StringBuilder();  
	              while (el.hasMoreElements()) {  
	                  byte[] mac = el.nextElement().getHardwareAddress();  
	                  if (mac == null){  
	                     continue;  
	                  }  
	                  if(builder.length() > 0){  
	                      builder.append(",");  
	                  }  
	                  for (byte b : mac) {  
	                        
	                     //convert to hex string.  
	                     String hex = Integer.toHexString(0xff & b).toUpperCase();  
	                     if(hex.length() == 1){  
	                         hex  = "0" + hex;  
	                     }  
	                     builder.append(hex);  
	                     builder.append("-");  
	                  }  
	                  builder.deleteCharAt(builder.length() - 1);  
	             }  
	               
	             if(builder.length() == 0){  
	                 System.out.println("Sorry, can't find your MAC Address.");  
	                 return "";
	             }else{  
	                 System.out.println("Your MAC Address is " + builder.toString());  
	                 return builder.toString();
	             }  
	         }catch (Exception exception) {  
	             exception.printStackTrace();  
	         }
		return "";  
	}
}
