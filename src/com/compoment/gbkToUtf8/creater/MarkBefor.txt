package com.compoment.creater.first;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class QuotationMarks {

	public static void main(String[] args) {
	        try {
	        	QuotationMarks mark=new QuotationMarks();
	        	String classDir = mark.getClass().getResource("/").getPath();
	            FileReader fr = new FileReader(classDir+"com/compoment/creater/first/MarkBefor.txt");
	            BufferedReader br = new BufferedReader(fr);
	            FileWriter fw = new FileWriter(classDir+"/temp.txt",true);
	            BufferedWriter bw = new BufferedWriter(fw);
	            String myreadline;
	            while (br.ready()) {
	                myreadline = br.readLine();


	                if(myreadline.length()>2)
	                {


	                String head=myreadline.substring(0, 2);

	                //将例�?�?.�?过滤�?
	                if(isinteger(head))
	                {
	                	int position=myreadline.indexOf(".");
	                	myreadline=myreadline.substring(position+1);
	                }

	                myreadline=myreadline.replace("\"", "\\\"");
	                myreadline="m+=\""+myreadline+"\\n"+"\";";

	                }



	                bw.write(myreadline); //д���ļ�
	                bw.newLine();
	                System.out.println(myreadline);//����Ļ�����?
	            }
	            bw.flush();    //ˢ�¸����Ļ���
	            bw.close();
	            br.close();
	            fw.close();
	            br.close();
	            fr.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }


	public static boolean isinteger(String value) {
		 try {
		  Integer.parseInt(value);
		  return true;
		 } catch (NumberFormatException e) {
		  return false;
		 }
		}



}

//���統ǰ��·��Ϊ C:\test ��
//File directory = new File("abc");
//directory.getCanonicalPath(); //�õ�����C:\test\abc
//directory.getAbsolutePath(); //�õ�����C:\test\abc
//direcotry.getPath(); //�õ�����abc
//
//File directory = new File(".");
//directory.getCanonicalPath(); //�õ�����C:\test
//directory.getAbsolutePath(); //�õ�����C:\test\.
//direcotry.getPath(); //�õ�����.
//
//File directory = new File("..");
//directory.getCanonicalPath(); //�õ�����C:\
//directory.getAbsolutePath(); //�õ�����C:\test\..
//direcotry.getPath(); //�õ�����..

