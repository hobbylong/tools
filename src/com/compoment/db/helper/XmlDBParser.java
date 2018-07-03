package com.compoment.db.helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.DefaultText;

//http://baike.baidu.com/link?url=Pe1pAEdhF7hUpe6jvgHy6dG7TT36FSr53-hx93GZPEpv_M8e3AumDQtZbC6_Gvp0RNwangorOQgsY5X-MdbrNK

public class XmlDBParser {

	public List<XmlDBTableBean> tables = new ArrayList();

	public  Comparator<XmlDBTableBean> comparator = new Comparator<XmlDBTableBean>(){
		   public int compare(XmlDBTableBean s1, XmlDBTableBean s2) {

		     if(!s1.tableName.equals(s2.tableName)){
		      return s1.tableName.compareTo(s2.tableName);
		     }
		     return 0;
		   }
	 };

	public void parserXml(String fileName) {

		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(new File(fileName));

			List list = document.selectNodes("//panel_attributes"); // 订单:OrderForm　
																	// --
																	// 订单号:OrderId
																	// --
																	// 购货日期:OrderDate
																	// -- 订货人ID
																	// :OrderManID
																	// --
																	// 订货人姓名:OrderManName
																	// --
																	// 订货人电话:OrderManPhone
																	// --
																	// 送货方式:DeliverWay
																	// --
																	// 送货（提货）地址：DeliverAddress
																	// --
																	// 提货人姓名：PickupManName
																	// --
																	// 提货人电话：PickupManPhone
																	// --
																	// 提货地址：PickupAddress
																	// --
																	// 提货人证件号：PickupManCertificateNo
																	// --
																	// 产品ID(多个产品用逗号分割',')：ProductId
																	// --
																	// 产品名：ProductName
																	// --
																	// 产品价格：ProductPrice
																	// --
																	// 产品数量：ProductNum
																	// --
																	// 产品总金额：ProductTotalPrice
																	// --
																	// 运费：DeliverPrice
																	// --
																	// 礼品ID(多个产品用逗号分割',')：GiftId
																	// --
																	// 礼品名：GiftName
																	// --
																	// 礼品数量：GiftNum
																	// --
																	// 订单状态(已付款，未付款,已经发货,付款失败):OrderState
			for (Iterator iter = list.iterator(); iter.hasNext();) {

				DefaultElement node = (DefaultElement) iter.next();
				List content = node.content(); // 订单:OrderForm　--
				XmlDBTableBean tableBean = new XmlDBTableBean();
				for (int i = 0; i < content.size(); i++) {
					if (i == 0) {
						String temp = ((DefaultText) content.get(i)).getStringValue();
						temp = temp.replaceAll("\n", "");
						temp = temp.replaceAll("--", "");
						temp = temp.replaceAll(" ", "");
						temp = temp.replaceAll("：", ":");
						temp=temp.trim();
                          if(temp.indexOf("_表")==-1)
                          {tableBean=null;
                        	  break;
                          }
						System.out.println();
						System.out.println();

						System.out.println(temp);
						String[] names = temp.split(":");
						if (names == null)
							names = temp.split("：");
						if (names != null && names.length>1) {
							tableBean.tableName = names[1];
							tableBean.tableChineseName = names[0];
						}

					} else {

						XmlDBColumnBean columnBean = new XmlDBColumnBean();

						String temp = ((DefaultText) content.get(i)).getStringValue();
						temp = temp.replaceAll("\n", "");
						temp = temp.replaceAll("--", "");
						temp = temp.replaceAll(" ", "");
						temp = temp.replaceAll("：", ":");

                        System.out.println(temp);
						String[] names = temp.split(":");
						if (names == null)
							names = temp.split("：");
						if (names != null && names.length>1) {

							columnBean.columnName = names[1];
							columnBean.columnChineseName = names[0];
						}

						tableBean.columnsName.add(columnBean);
					}

				}
                if(tableBean!=null)
				tables.add(tableBean);

			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 Collections.sort(tables,comparator);
	}

}
