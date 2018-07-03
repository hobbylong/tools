package com.compoment.addfunction.web.servletMybatis;

import java.util.ArrayList;
import java.util.List;

import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;

public class DBUseMybatis {
	
	public DBUseMybatis(String interfaceName,
			String interfaceCnName, List<TableBean> temptables)
	{
		for (TableBean table : temptables) {
		for (TableColumnBean column : table.columns) {
			column.belongWhichTable=table;
		}
		
		List<TableBean>  tables =new ArrayList();
		tables.add(table);
		
		interfaceName=table.tableEnName;
		interfaceCnName=table.tableCnName;
		
		MapperXmlForSingleTable  mapperXmlForSingleTable=new MapperXmlForSingleTable();
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
		
		
		
		}
		
		
		
		MybatisUtil mybatisUtil=new MybatisUtil();
		mybatisUtil.mybatisUtil();
	}

}
