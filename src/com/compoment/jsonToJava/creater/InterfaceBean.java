package com.compoment.jsonToJava.creater;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class InterfaceBean implements Serializable{
	public String title;// 接口名称
	public String detail;// 接口描述
	public String id;// 接口id号
	public String enName;
	public String projectName;
	public String companyName;
	public boolean isTable;
	public String tableName;
	
	
	

	public String getProjectName() {
		return projectName;
	}
	

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	

	public String getCompanyName() {
		return companyName;
	}
	

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public List<Group> requestGroups ;// 循环域开始结束构成一个组 ，
												// 自定义对象开始结束构成一个组，
	// 其它的则构成一个通用组
	// ，每个组由一行或多行row构成

	public List<Group> respondGroups ;
	
	public InterfaceBean()
	{requestGroups = new ArrayList();
		
		respondGroups = new ArrayList();
	
	}
	

	
	
	
	public class Group implements Serializable {
		public String name;// 组名
		public List<Row> rows = new ArrayList();
		public Group()
		{
			
		}
	}

	public class Row implements Serializable {
		public String cnName;
		public String enName;
		public String type;
		public long time;
		public Row()
		{
			
			time=System.currentTimeMillis();
		}
		
		public String getType() {
			return type.replaceAll("[^0-9a-zA-Z]","");
		}
		public void setType(String type) {
			this.type = type;
		}
		public String remarks;
		
		
	}

}

