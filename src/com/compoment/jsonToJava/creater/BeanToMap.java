package com.compoment.jsonToJava.creater;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.io.FileUtils;



import com.compoment.creater.first.QuotationMarks;

import com.google.gson.Gson;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
//http://www.cnblogs.com/hoojo/archive/2011/04/21/2023805.html
public class BeanToMap {

	String beanName="RespondParam4463512";
	public static void main(String[] args) {
		BeanToMap pojo = new BeanToMap();
		//pojo.beanToMap();
		
	    pojo.to();
	
	}

	

	/**从文件得到json数据*/
	public void beanToMap() {
		String m="";
		String classDir = "";
		File directory = new File("");// 参数为空
        try {
        
        	classDir = directory.getCanonicalPath();
            FileReader fr = new FileReader(classDir+"/src/com/compoment/jsonToJava/creater/beanToMap.txt");
            BufferedReader br = new BufferedReader(fr);
        
            String myreadline;
            while (br.ready()) {
                myreadline = br.readLine();

              
                if( myreadline.contains("String"))
                {
                	
                int pStart=myreadline.indexOf("String");
                int pEnd=myreadline.indexOf(";");
                m+="map.put(\""+myreadline.substring(pStart+6, pEnd).replace(" ", "")+"\",);\n";

                }else     if( myreadline.contains("float"))
                {
                	
                int pStart=myreadline.indexOf("float");
                int pEnd=myreadline.indexOf(";");
                m+="map.put(\""+myreadline.substring(pStart+5, pEnd).replace(" ", "")+"\",);\n";

                }else     if( myreadline.contains("int"))
                {
                	
                int pStart=myreadline.indexOf("int");
                int pEnd=myreadline.indexOf(";");
                m+="map.put(\""+myreadline.substring(pStart+3, pEnd).replace(" ", "")+"\",);\n";

                }else
                {
                	  m+=myreadline+"\n";
                }



            
               
            }
      
            br.close();
          
            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    
        System.out.println(m);//����Ļ�����?
	}

	
	
	String n="";
	public void to()
	{
		String m="";
		String classDir = "";
		File directory = new File("");// 参数为空
        try {
        
        	classDir = directory.getCanonicalPath();
            FileReader fr = new FileReader(classDir+"/src/com/compoment/jsonToJava/creater/beanToMap.txt");
            BufferedReader br = new BufferedReader(fr);
        
            String myreadline;
            while (br.ready()) {
                myreadline = br.readLine();

              
                if( myreadline.contains("String"))
                {
                	
                int pStart=myreadline.indexOf("String");
                int pEnd=myreadline.indexOf(";");
               String name=myreadline.substring(pStart+6, pEnd).replace(" ", "");
               if(name.equals("D44_70_RECORDNUM") || name.equals("D44_70_MAXRECORD") )
                   continue;
            	n+="ArrayList<String> "+name+"List = JSONConversion.ja2list(\""+name+"\", resultMap);\n";

                }else     if( myreadline.contains("float"))
                {
                	
                int pStart=myreadline.indexOf("float");
                int pEnd=myreadline.indexOf(";");
                String name=myreadline.substring(pStart+5, pEnd).replace(" ", "");

                if(name.equals("D44_70_RECORDNUM") || name.equals("D44_70_MAXRECORD") )
                    continue;
            	n+="ArrayList<String> "+name+"List = JSONConversion.ja2list(\""+name+"\", resultMap);\n";
                }else     if( myreadline.contains("int"))
                {
                	
                int pStart=myreadline.indexOf("int");
                int pEnd=myreadline.indexOf(";");
                String name=myreadline.substring(pStart+3, pEnd).replace(" ", "");
                if(name.equals("D44_70_RECORDNUM") || name.equals("D44_70_MAXRECORD") )
                    continue;
            	n+="ArrayList<String> "+name+"List = JSONConversion.ja2list(\""+name+"\", resultMap);\n";
                }else
                {
                	//  m+=myreadline+"\n";
                }



            
               
            }
      
            br.close();
          
            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    
     
		
        to2();
	}

	
	
	public void to2()
	{
		
        n+="ArrayList<"+beanName+"> listData = new ArrayList<"+beanName+">();\n";
        n+=beanName+" bean;\n";

		n+="for (int i = 0; i < count; i++) {\n";
		n+="	bean = new "+beanName+"();\n";
		
		String m="";
		String classDir = "";
		File directory = new File("");// 参数为空
        try {
        
        	classDir = directory.getCanonicalPath();
            FileReader fr = new FileReader(classDir+"/src/com/compoment/jsonToJava/creater/beanToMap.txt");
            BufferedReader br = new BufferedReader(fr);
        
            String myreadline;
            while (br.ready()) {
                myreadline = br.readLine();

              
                if( myreadline.contains("String"))
                {
                	
                int pStart=myreadline.indexOf("String");
                int pEnd=myreadline.indexOf(";");
               String name=myreadline.substring(pStart+6, pEnd).replace(" ", "");
                
               if(name.equals("D44_70_RECORDNUM") || name.equals("D44_70_MAXRECORD") )
                   continue;
            	n+="bean."+name+"="+name+"List.get(i);\n";
                }else     if( myreadline.contains("float"))
                {
                	
                int pStart=myreadline.indexOf("float");
                int pEnd=myreadline.indexOf(";");
                String name=myreadline.substring(pStart+5, pEnd).replace(" ", "");
                if(name.equals("D44_70_RECORDNUM") || name.equals("D44_70_MAXRECORD") )
                continue;
                n+="bean."+name+"=(float)Float.valueOf("+name+"List.get(i));\n";
                }else     if( myreadline.contains("int"))
                {
                	
                int pStart=myreadline.indexOf("int");
                int pEnd=myreadline.indexOf(";");
                String name=myreadline.substring(pStart+3, pEnd).replace(" ", "");
                if(name.equals("D44_70_RECORDNUM") || name.equals("D44_70_MAXRECORD") )
                    continue;
                n+="bean."+name+"=(int)Integer.valueOf("+name+"List.get(i));\n";
                }else
                {
                	  m+=myreadline+"\n";
                }



            
               
            }
      
            br.close();
          
            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    
       n+=" listData.add(bean);\n";
	
	n+="}\n";
        System.out.println(n);//����Ļ�����?
		
		
	}
	
	


}
