package com.compoment.addfunction.web.springmvcSpringMybatis;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.compoment.addfunction.web.servletMybatis.MapperJava;
import com.compoment.addfunction.web.servletMybatis.MapperXml;
import com.compoment.addfunction.web.servletMybatis.MapperXmlForSingleTable;
import com.compoment.addfunction.web.servletMybatis.SqlScript;
import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.remote.CheckProblemInterface;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

/**springmvc spring  mybatis*/
public class InterfaceServiceController_springmvcSpringMybatis  {


	public InterfaceServiceController_springmvcSpringMybatis() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	public List<TableColumnBean> getQueryConditionColumns(List<TableBean> tables)
	{
	
		List<TableColumnBean> queryConditionColumns=new ArrayList();
	
		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("right".equals(column.rightClickSelected)) {
					String tablename=StringUtil
							.tableName(column.belongWhichTable.tableEnName);
					String shortTableName=tablename.substring(tablename.lastIndexOf("_")+1);

					queryConditionColumns.add(column);
				}

			}
		}
		return queryConditionColumns;
	}
	
	public List<TableColumnBean> getResultColumns(List<TableBean> tables)
	{
	
		
		List<TableColumnBean> resultColumns=new ArrayList();
		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("left".equals(column.leftClickSelected)) {
					String tablename=StringUtil
							.tableName(column.belongWhichTable.tableEnName);
					String shortTableName=tablename.substring(tablename.lastIndexOf("_")+1);
					
					resultColumns.add(column);

				}

				
			}
		}
		return resultColumns;
	}
	
	
	String interfaceName;
	String interfaceCnName;

	public void createInterfaceService(String interfaceName,
			String interfaceCnName, List<TableBean> tables) {
		this.interfaceName = interfaceName;
		this.interfaceCnName = interfaceCnName;

		MapperXmlForSingleTable mapperXmlForSingleTable=new MapperXmlForSingleTable();
		mapperXmlForSingleTable.mapperXmlForSingleTable(tables);
		
		MapperXml mapperXml=new MapperXml();
		mapperXml.mapperXml(tables,interfaceName);
		
		MapperJava mapperJava=new MapperJava();
		mapperJava.mapperJava(tables,interfaceName,interfaceCnName);
		
		Entity entity=new Entity();
		entity.entity(tables,interfaceName,interfaceCnName);
		
		SqlScript  sqlScript=new SqlScript();
		sqlScript.mysql(tables);
		sqlScript.oracle(tables);
		
		ServiceInterface serviceInterface=new ServiceInterface();
		serviceInterface.serviceInterface(tables,interfaceName,interfaceCnName);
		
		Controller controller=new Controller();
		controller.controller(tables,interfaceName,interfaceCnName);

		TestInterface_springmvcSpringMybatis testInterface = new TestInterface_springmvcSpringMybatis();
		testInterface.testJsp(interfaceName, interfaceCnName, tables);

	}

	

	public String typeCheck(String type) {
		if (!type.toLowerCase().contains("int")) {
			type = "String";
		} else {
			type = "int";
		}

		return type;
	}
}
