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


public class JsonToJavaBean {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JsonToJavaBean pojo = new JsonToJavaBean();
		String jsonString= pojo.jsonFromFile();
		//String jsonString=pojo.jsonFromUrl();
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		System.out.println("public class Bean{");
		pojo.getPojo(jsonObject);
		//pojo.parseJson01(jsonString);
	}

	/**从url得到json数据*/
	public String jsonFromUrl() {
		try {
			//String baseurl="https://ip:port/front/macula-gbss-mobile/";
			//String urlPath = baseurl+"gbss-mobile/product/series/getSeries?lastUpdateTime=\"\"";
			String urlPath = "http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=210af0ac7c5dad997a19f7667e5779d3&tags=Singapore&per_page=200&format=json&nojsoncallback=1";
			URL url = new URL(urlPath);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(30000);
			connection.setReadTimeout(30000);
			connection.setInstanceFollowRedirects(true);
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			// ��Ӧ���ַ����ת��
			Reader reader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String str = null;
			StringBuffer sb = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				sb.append(str);
			}
			reader.close();
			connection.disconnect();
		return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**从文件得到json数据*/
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
			jsonString = FileUtils.readFileToString(new File(classDir
					+ "/src/com/compoment/jsonToJava/creater/jsonToJaveBean.json"));
			return jsonString;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;

	}


  	int i=0;
	 /**生成Bean  json->bean*/
	public  void getPojo(JSONObject jsonObject) {

		try {
			Set set = jsonObject.keySet();
			Iterator it = set.iterator();

			while (it.hasNext()) {
				String key = (String) it.next();
				Object obj = (Object) jsonObject.get(key);

				if (obj instanceof JSONArray) {
					// List<PhotoBean> photoBeanList;
					System.out.println("public List<" + firstCharUpperCase(key)
							+ "Bean> " + key + ";");
					// public class PhotoBean {
					System.out.println("public class "
							+ firstCharUpperCase(key) + "Bean{");
					JSONArray jSONArray = (JSONArray) obj;
					if(jSONArray!=null && jSONArray.size()>0)
					{
					for (int i = 0; i < 1; i++) {
						JSONObject temp = jSONArray.getJSONObject(i); // ���JSON�����е�ÿһ��JSONObject����
						getPojo(temp);
					}}
				} else if (obj instanceof JSONObject) {

						System.out.println("public "
								+ firstCharUpperCase(key) + "Bean "+key+";");

					System.out.println("public class "
							+ firstCharUpperCase(key) + "Bean{");

					JSONObject temp = (JSONObject) obj;
					getPojo(temp);

				} else {
					System.out.println("public String " + key + ";");
				}

			}
			System.out.println("}");

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	/**首字母大写*/
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




/**如何使用生成的Bean  即json->java*/
	public void addJsonValueToBean(String jsonString) {
		//本包内有此类
//		public class Bean{
//			public List<PeopleBean> people;
//			public class PeopleBean{
//			public String firstName;
//			public String lastName;
//			public String email;
//			}
//			}
		Gson gson = new Gson();
		Bean bean = gson.fromJson(jsonString, Bean.class);
		if(bean!=null)
		{
        System.out.println(bean.toString());
		}
	}
	
	
	public void parseJson01(String jsonData){
        try {
            //解析json对象首先要生产一个JsonReader对象
            JsonReader reader=new JsonReader(new StringReader(jsonData));
            reader.beginArray();
            while(reader.hasNext()){
                reader.beginObject();
                while(reader.hasNext()){
                    String tagName=reader.nextName();
                    //if("name".equals(tagName)){
                        System.out.println("name--->"+reader.nextString());
                   // }else if("age".equals(tagName)){
                      //  System.out.println("age--->"+reader.nextInt());
                    //}
                }
                reader.endObject();
            }
            reader.endArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bean parseJson02(String jsonData){
        Gson gson=new Gson();
        Bean user=gson.fromJson(jsonData, Bean.class);
return user;
    }
    
    public void parseJson03(String jsonData){
        Type listType = new TypeToken<LinkedList<Bean>>(){}.getType();
        Gson gson=new Gson();
        LinkedList<Bean> users=gson.fromJson(jsonData, listType);
        for(Bean bean:users){
          
        }
        System.out.println("==================");
        for (Iterator iterator = users.iterator(); iterator.hasNext();) {
            Bean user = (Bean) iterator.next();
           
        }
    }

    public void toJson01(Bean bean01)
    {
    	//把java对象转换成json
       
        Gson gson=new Gson();
        String obj=gson.toJson(bean01);
        System.out.println(obj);//输出{"name":"linjiqin", "age":24}

    }
    


	/**简化版例子
	 *
	 {
    "returnData": {
        "D44_70_BRCH_NO": [
            "CPApplication.operatorMsg.organID",
            "CPApplication.operatorMsg.organID",
            "CPApplication.operatorMsg.organID"
        ],
        "RCVMSG_HEAD": {
            "H_RFILE_NUM": "0",
            "H_SEQ_NO": "10000176",
            "SYS_DATE": "20141216",
            "HOST_RET_MSG": "交易完成",
            "H_REND_FLAG": "1",
            "HOST_RET_ERR": "000000"
        },
        "D44_70_CITYORGANNAME": [
            "湛江市邮政局",
            "湛江市邮政局",
            "湛江市邮政局"
        ],
        "D44_70_ASS_NO": [
            "",
            "",
            ""
        ],
        "D44_70_MONEY3": [
            "17.40",
            "3.60",
            "7.59"
        ],
        "D44_70_MODE_DEAL": [
            "01",
            "01",
            "01"
        ],
        "D44_70_TRAN_DATE": [
            "20131125",
            "20131125",
            "20131125"
        ],
        "D44_70_NEW_STATUS": [
            "02",
            "02",
            "02"
        ],
        "D44_70_MAXRECORD": "3",
        "D44_70_COUNTYORGAN": [
            "52456100",
            "52456100",
            "52456100"
        ],
        "D44_70_COUNTYORGANNAME": [
            "吴川市邮政局",
            "吴川市邮政局",
            "吴川市邮政局"
        ],
        "D44_70_REMARK": [
            "自动计算酬金，交易笔数为5笔",
            "自动计算酬金，交易笔数为2笔",
            "自动计算酬金，交易笔数为2笔"
        ],
        "D44_70_TRAN_TIME": [
            "112542",
            "112542",
            "112541"
        ],
        "D44_70_OPRID": [
            "X001",
            "X001",
            "X001"
        ],
        "D44_70_SEQNO": [
            "147",
            "146",
            "144"
        ],
        "D44_70_PAYOFF_DATE": [
            "",
            "",
            ""
        ],
        "D44_70_COMPUTEDATE": [
            "20131101",
            "20131112",
            "20131106"
        ],
        "D44_70_CITYORGAN": [
            "52402200",
            "52402200",
            "52402200"
        ],
        "D44_70_RECORDNUM": "3",
        "D44_70_ORGANNAME": [
            "吴川市体彩07262站邮政加盟点",
            "吴川市体彩07262站邮政加盟点",
            "吴川市体彩07262站邮政加盟点"
        ],
        "D44_70_BUSI_ID": [
            "8701",
            "8701",
            "8701"
        ],
        "D44_70_LINK_TYPE": [
            "",
            "",
            ""
        ]
    },
    "returnCode": "0"
}
	 * 
	 * **/
	
	
	
	/**详细版例子
	 {
    "result": "0",
    "msg": "�ɹ�",
    "orderNo": "PAPP0000000000153172",
    "total": 683.22,
    "point": 4357,
    "buyerData": {
        "phone": "13456273344",
        "dealerNo": "361002115",
        "Identity": "������",
        "dealerName": "*����"
    },
    "recommendData": {
        "phone": "15888858586",
        "sex": "��",
        "address": "����ʡȪ���н��и���ʡȪ���н���hh",
        "name": "gigfgt",
        "branchName": "�����ֹ�˾",
        "certificateNo": "-",
        "regionName": "�й�",
        "certificateName": "��֤��"
    },
    "distributionData": {
        "phone": "0756-8603655",
        "address": "�㶫ʡ�麣��������ǰɽ��������·3312��A��8��",
        "name": "����",
        "addressType": "05"
    },
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
    ],
    "saleData": [],
    "attachData": []
}
	 * 
	 * */
	
}
