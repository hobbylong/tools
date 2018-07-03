package com.compoment.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.compoment.cut.CompomentBean;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

//http://www.2cto.com/kf/201208/148029.html
public class SerializeToFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CompomentBean myPersons = new CompomentBean();
	
		myPersons.x = 100;
		myPersons.y = 200;
		myPersons.chirlds = new ArrayList();

		CompomentBean myPerson = new CompomentBean();
		myPerson.x = 300;
		myPerson.y = 400;
	
		List<CompomentBean> list=new ArrayList();
		list.add(myPerson);
		list.add(myPersons);

		SerializeToFile serializeToFile = new SerializeToFile();
		serializeToFile.serializeToXml(list,"");
		serializeToFile.deSerializeFromXml();
		
	}

	public static void serializeToXml(List<CompomentBean> myPersons,String pageName) {

		String courseFile = null;
		File directory = new File("");// 参数为空
		try {
			courseFile = directory.getCanonicalPath();

			String path = courseFile
					+ "/res/other/CacheCompomentBeans.xml";

			XStream xStream = new XStream();
			xStream.alias("CompomentBean", CompomentBean.class);

			FileOutputStream foStream = new FileOutputStream(path);
			xStream.toXML(myPersons, foStream);
			
			
			if(!"".equals( pageName))
			{
			String xmlFileName = FileUtil.makeFile(KeyValue.readCache("picPath"),
					"cacheCompomentBean/CacheCompomentBeans", "CacheCompomentBeans_"+pageName, "xml", "");
			FileOutputStream outStream = new FileOutputStream(xmlFileName);
			xStream.toXML(myPersons, outStream);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void serializeToXmlForPublicCompoment(List<CompomentBean> myPersons,String path) {

	
		try {
			XStream xStream = new XStream();
			xStream.alias("CompomentBean", CompomentBean.class);

			FileOutputStream foStream = new FileOutputStream(path);
			xStream.toXML(myPersons, foStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<CompomentBean> deSerializeFromXml() {
		String courseFile = null;
		File directory = new File("");// 参数为空
		try {
			courseFile = directory.getCanonicalPath();

			String path = courseFile
					+ "/res/other/CacheCompomentBeans.xml";
			XStream xStream = new XStream();
			xStream.alias("CompomentBean", CompomentBean.class);
			List<CompomentBean> myPersons = null;

			FileInputStream flStream = new FileInputStream(path);
			myPersons = (List<CompomentBean>) xStream.fromXML(flStream);
			return myPersons;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<CompomentBean> deSerializeFromXml(String path) {
		
		try {
		
			XStream xStream = new XStream();
			xStream.alias("CompomentBean", CompomentBean.class);
			List<CompomentBean> myPersons = null;

			FileInputStream flStream = new FileInputStream(path);
			myPersons = (List<CompomentBean>) xStream.fromXML(flStream);
			return myPersons;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void writeJSON(CompomentBean myPersons) {
		XStream xStream = new XStream(new JettisonMappedXmlDriver());

		try {
			FileOutputStream fos = new FileOutputStream("E:\\json.js");
			xStream.setMode(XStream.NO_REFERENCES);
			xStream.alias("Person", CompomentBean.class);
			xStream.toXML(myPersons, fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void readJSON() {
		XStream xStream = new XStream(new JettisonMappedXmlDriver());
		CompomentBean person = null;
		try {
			FileInputStream fis = new FileInputStream("E:\\CompomentBean.js");
			xStream.setMode(XStream.NO_REFERENCES);
			xStream.alias("Person", CompomentBean.class);
			person = (CompomentBean) xStream.fromXML(fis);
			System.out.println(person.x);
			System.out.println(person.y);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
