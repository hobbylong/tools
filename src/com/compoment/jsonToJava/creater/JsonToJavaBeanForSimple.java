package com.compoment.jsonToJava.creater;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.io.FileUtils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

//http://www.cnblogs.com/hoojo/archive/2011/04/21/2023805.html

/*
 * 
 * {}为对象  []为数组
 * {,,} 整个json字符串
 * */

/*key:普通类型(int ,string)
 "result": "0"
 "point": 4357
 */

/*key：对象    
 "buyerData": {
 "phone": "13456273344",
 "dealerNo": "361002115",
 "Identity": "������",
 "dealerName": "*����"
 }*/

/*key：对象数组       
 "productData": [
 {
 "productCode": "18002-03",
 "canBuyAmount": 2,
 "productName": "���޼�Ů�˿ڷ�Һ",
 "buyAmount": 2,
 "totalPrice": 650
 },
 {
 "productCode": "18003-03",
 "canBuyAmount": 11,
 "productName": "���޼����˿ڷ�Һ",
 "buyAmount": 11,
 "totalPrice": 33.22
 }
 ]*/

/*key:普通类型数组  
 "D44_70_COUNTYORGANNAME": [
 "吴川市邮政局",
 "吴川市邮政局",
 "吴川市邮政局"
 ]*/

public class JsonToJavaBeanForSimple {

	String m = "";

	List touse = new ArrayList();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JsonToJavaBeanForSimple pojo = new JsonToJavaBeanForSimple();
		String jsonString = pojo.jsonFromFile();
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Bean rootBean = pojo.relation("respond", jsonObject);
		pojo.createJavaBeanClass(rootBean);
		System.out.println(pojo.getJaveBeanClass());
		System.out.println(pojo.toUseJavaBeanClass());
	}

	public JsonToJavaBeanForSimple() {

	}

	public JsonToJavaBeanForSimple(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Bean rootBean = relation("respond", jsonObject);
		createJavaBeanClass(rootBean);
	}

	public String getJaveBeanClass() {
		return m;
	}

	public void createJavaBeanClass(Bean rootBean) {

		m += "\n class " + firstCharUpperCase(rootBean.name) + "{\n";
		touse.add(firstCharUpperCase(rootBean.name) + " " + rootBean.name
				+ "=new " + firstCharUpperCase(rootBean.name) + "();\n");

		Set set = rootBean.kvcommonobject.keySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			m += "public String " + key + ";\n";

			touse.add(rootBean.name + "." + key + "=;\n");
			Object obj = (Object) rootBean.kvcommonobject.get(key);
		}

		Set setArray = rootBean.kvarrayobject.keySet();
		Iterator itArray = setArray.iterator();
		while (itArray.hasNext()) {
			String key = (String) itArray.next();
			m += "public String " + key + "[];\n";
			Object obj = (Object) rootBean.kvarrayobject.get(key);
		}

		for (Bean bean : rootBean.childs) {
			m += "public " + firstCharUpperCase(bean.name) + " " + bean.name
					+ ";\n";

			touse.add(rootBean.name + "." + bean.name + "=" + bean.name + ";\n");
			createJavaBeanClass(bean);
		}

		m += "}\n";

	}

	public String  toUseJavaBeanClass() {

		// 功能：1.在new前加root. 2.对调下面两行
		// root.returnData = returnData;
		// ReturnData returnData = new ReturnData();

		for (int i = 0; i < touse.size(); i++) {
			if (i != 0 && touse.get(i).toString().contains("new ")) {
				String before = touse.get(i - 1).toString();
				String beforeindex = before.substring(0,
						before.indexOf(".") + 1);

				String current = touse.get(i).toString();
				current = current.replace("new", beforeindex + "new");

				touse.set(i - 1, current);
				touse.set(i, before);

			}
		}

		String m = "";
		for (int i = 0; i < touse.size(); i++) {
			m += touse.get(i);
		}
		return m;
		
	}



	/** 从文件得到json数据 */
	public String jsonFromFile() {
		String classDir = "";
		File directory = new File("");// 参数为空
		try {
			classDir = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonString = null;
		try {
			jsonString = FileUtils
					.readFileToString(new File(
							classDir
									+ "/src/com/compoment/jsonToJava/creater/jsonToJaveBean.json"));
			return jsonString;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;

	}

	class Bean {

		String name = "";
		Bean parent;
		Map kvcommonobject = new HashMap();
		Map kvarrayobject = new HashMap();
		List<Bean> childs = new ArrayList();
	}

	public Bean relation(String name, JSONObject jsonObject) {
		Bean bean = new Bean();
		bean.name = name;

		try {
			Set set = jsonObject.keySet();
			Iterator it = set.iterator();

			while (it.hasNext()) {
				String key = (String) it.next();
				Object obj = (Object) jsonObject.get(key);

				if (obj instanceof JSONArray) {

					JSONArray jSONArray = (JSONArray) obj;
					if (jSONArray != null && jSONArray.size() > 0) {
						bean.kvarrayobject.put(key, jSONArray.size());
					}
				} else if (obj instanceof JSONObject) {

					Bean child = relation(key, (JSONObject) obj);
					bean.childs.add(child);
				} else {

					bean.kvcommonobject.put(key, obj);
				}
			}
		} catch (Exception e) {

		}

		return bean;
	}

	

	/** 首字母大写 */
	public static String firstCharUpperCase(String s) {
		if (s.length() > 0) {
			String firstchar = String.valueOf(s.charAt(0)).toUpperCase();
			s = s.substring(1);
			s = firstchar + s;
			return s;
		} else {
			return null;
		}

	}



	/**
	 * 简化版例子
	 *
	 * { "returnData": { "D44_70_BRCH_NO": [
	 * "CPApplication.operatorMsg.organID", "CPApplication.operatorMsg.organID",
	 * "CPApplication.operatorMsg.organID" ], "RCVMSG_HEAD": { "H_RFILE_NUM":
	 * "0", "H_SEQ_NO": "10000176", "SYS_DATE": "20141216", "HOST_RET_MSG":
	 * "交易完成", "H_REND_FLAG": "1", "HOST_RET_ERR": "000000" },
	 * "D44_70_CITYORGANNAME": [ "湛江市邮政局", "湛江市邮政局", "湛江市邮政局" ],
	 * "D44_70_ASS_NO": [ "", "", "" ], "D44_70_MONEY3": [ "17.40", "3.60",
	 * "7.59" ], "D44_70_MODE_DEAL": [ "01", "01", "01" ], "D44_70_TRAN_DATE": [
	 * "20131125", "20131125", "20131125" ], "D44_70_NEW_STATUS": [ "02", "02",
	 * "02" ], "D44_70_MAXRECORD": "3", "D44_70_COUNTYORGAN": [ "52456100",
	 * "52456100", "52456100" ], "D44_70_COUNTYORGANNAME": [ "吴川市邮政局", "吴川市邮政局",
	 * "吴川市邮政局" ], "D44_70_REMARK": [ "自动计算酬金，交易笔数为5笔", "自动计算酬金，交易笔数为2笔",
	 * "自动计算酬金，交易笔数为2笔" ], "D44_70_TRAN_TIME": [ "112542", "112542", "112541" ],
	 * "D44_70_OPRID": [ "X001", "X001", "X001" ], "D44_70_SEQNO": [ "147",
	 * "146", "144" ], "D44_70_PAYOFF_DATE": [ "", "", "" ],
	 * "D44_70_COMPUTEDATE": [ "20131101", "20131112", "20131106" ],
	 * "D44_70_CITYORGAN": [ "52402200", "52402200", "52402200" ],
	 * "D44_70_RECORDNUM": "3", "D44_70_ORGANNAME": [ "吴川市体彩07262站邮政加盟点",
	 * "吴川市体彩07262站邮政加盟点", "吴川市体彩07262站邮政加盟点" ], "D44_70_BUSI_ID": [ "8701",
	 * "8701", "8701" ], "D44_70_LINK_TYPE": [ "", "", "" ] }, "returnCode": "0"
	 * }
	 * 
	 * **/

	/**
	 * 详细版例子 { "result": "0", "msg": "�ɹ�", "orderNo": "PAPP0000000000153172",
	 * "total": 683.22, "point": 4357, "buyerData": { "phone": "13456273344",
	 * "dealerNo": "361002115", "Identity": "������", "dealerName": "*����" },
	 * "recommendData": { "phone": "15888858586", "sex": "��", "address":
	 * "����ʡȪ���н��и���ʡȪ���н���hh", "name": "gigfgt", "branchName":
	 * "�����ֹ�˾", "certificateNo": "-", "regionName": "�й�", "certificateName":
	 * "��֤��" }, "distributionData": { "phone": "0756-8603655", "address":
	 * "�㶫ʡ�麣��������ǰɽ��������·3312��A��8��", "name": "����", "addressType":
	 * "05" }, "productData": [ { "productCode": "18002-03", "canBuyAmount": 2,
	 * "productName": "���޼�Ů�˿ڷ�Һ", "buyAmount": 2, "totalPrice": 650 }, {
	 * "productCode": "18003-03", "canBuyAmount": 11, "productName":
	 * "���޼����˿ڷ�Һ", "buyAmount": 11, "totalPrice": 33.22 } ], "saleData": [],
	 * "attachData": [] }
	 * 
	 * */

}
