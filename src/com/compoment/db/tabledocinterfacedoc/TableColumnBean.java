package com.compoment.db.tabledocinterfacedoc;

import java.awt.Font;
import java.awt.FontMetrics;
import java.io.Serializable;
import java.util.List;


public class TableColumnBean implements Serializable{
	
	public String columnEnName;
	public int columnEnNameWidth;
	public int columnEnNameHeight;
	public int columnEnNameX;
	public int columnEnNameY;
	
	public String columnCnName;
	public int columnCnNameWidth;
	public int columnCnNameHeight;
	public int columnCnNameX;
	public int columnCnNameY;
	
	public String type;
	public int typeWidth;
	public int typeHeight;
	public int typeX;
	public int typeY;
	
	public String key;
	public int keyWidth;
	public int keyHeight;
	public int keyX;
	public int keyY;
	
	public int x; 
	public int y;
	public int x1;
	public long time;
	
	public TableBean belongWhichTable;
	
	public List<TableColumnBean>  relateColumnBeans;//与其连线的ColumnBean
	
	/**left right null*/
	public String leftOrRightClickSelected=null;
	public String leftClickSelected=null;
	public String rightClickSelected=null;

	public TableColumnBean()
	{
		time=System.currentTimeMillis();
	}
	
	public String getColumnEnName() {
		return columnEnName;
	}
	public void setColumnEnName(String columnEnName) {
		
		 Font f = new Font("宋体", Font.BOLD, 11);  
	     FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
		    // 高度
	     columnEnNameHeight= fm.getHeight();
		    // 整个字符串的宽度 
	     columnEnNameWidth= fm.stringWidth(columnEnName);  
		
		this.columnEnName = columnEnName;
		
	}
	
	public String getColumnCnName() {
		return columnCnName;
	}
	public void setColumnCnName(String columnCnName) {
		 Font f = new Font("宋体", Font.BOLD, 11);  
	     FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
		    // 高度
	     columnCnNameHeight= fm.getHeight();
		    // 整个字符串的宽度 
	     columnCnNameWidth= fm.stringWidth(columnCnName);  
		this.columnCnName = columnCnName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		 Font f = new Font("宋体", Font.BOLD, 11);  
	     FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
		    // 高度
	     typeHeight= fm.getHeight();
		    // 整个字符串的宽度 
	     typeWidth= fm.stringWidth(type);  
		this.type = type;
	}
	public String getKey() {
		
		return key;
	}
	public void setKey(String key) {
		 Font f = new Font("宋体", Font.BOLD, 11);  
	     FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
		    // 高度
	     keyHeight= fm.getHeight();
		    // 整个字符串的宽度 
	     keyWidth= fm.stringWidth(key);  
		this.key = key;
	}
	public int y1;
	public String color;
	public int width;
	public int height;

}
