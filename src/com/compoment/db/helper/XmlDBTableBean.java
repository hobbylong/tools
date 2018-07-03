package com.compoment.db.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class XmlDBTableBean {
	public String type = "";// com.umlet.element.Class
	public String tableString = "";// 产品 购物车　收藏: ProductShoppingcarStoreUp --
									// 产品编号:product_id -- 产品所属系列:product_class
									// --
									// 产品名字:product_name -- 产品图片:ProductPic --
									// 产品缩略图:ProductSmalPic -- 产品简介:ProductInfo
									// --
									// 产品规格:ProductStype -- 产品原价:ProductPrice --
									// 产品折扣率:ProductDiscount --
									// 产品优惠价:ProductPreferentialPrice --
									// 产品点数:ProductIntegrate --
									// 是否加入购物车:isInShoppingCar -- 是否收藏:isStoreUp
									// --
									// 购买数量:BuyNumber -- 库存数量:InventoryNumber --
									// 是否为礼品:isGift
	public String tableName;
	public String tableChineseName;
	public List<XmlDBColumnBean> columnsName = new ArrayList<XmlDBColumnBean>();

	public String getSqliteTableName() {
		String sqliteTableName="";
		for (int i = 0; i < tableName.length(); i++) {
			char c = tableName.charAt(i);
			if (c > 'A' && c < 'Z') {
                if(i!=0)
                {
                	sqliteTableName+="_"+c;
                }else{
				sqliteTableName+=c;
                }
			}else
			{
				sqliteTableName+=c;
			}
		}

		return sqliteTableName.toLowerCase();
	}

	public String firstCharLowercase(String s)
	{

		if(s.length()>0)
		{
		String firstchar=String.valueOf(s.charAt(0)).toLowerCase();
		s=s.substring(1);
		s=firstchar+s;
		return s;
		}else
		{
			return null;
		}

	}

}
