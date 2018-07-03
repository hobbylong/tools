package com.compoment.db.tabledocinterfacedoc;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;

public class Sql {

	public void createTableSql(List<TableBean> tables) {

		
		
		String tableString = "";
        String insertString="";
        
		for (TableBean table : tables) {

			tableString = "CREATE TABLE IF NOT EXISTS  \'" + table.tableEnName + "\'  (";
			insertString="INSERT INTO "+table.tableEnName+" (";
			
			
			String columnstring = "";
			String insertColumnStringLeft="";
			String insertColumnStringRight="";
			
			for (TableColumnBean column : table.columns) {
				

				if ("PRIMARY KEY".equals(column.key)) {

				   if ("int".equals(column.type.toLowerCase())||"Integer".equals(column.type.toLowerCase())) {

						columnstring+="\'"+column.columnEnName+"\' int(10) NOT NULL AUTO_INCREMENT,";
						columnstring+="PRIMARY KEY (\'"+column.columnEnName+"\') ,";
						
					} 

				} else if ("NOT NULL".equals(column.key)) {

					if ("string".equals(column.type.toLowerCase()) || "char".equals(column.type.toLowerCase())
							|| "varchar".equals(column.type.toLowerCase())) {

						columnstring+="\'"+column.columnEnName+"\' char(200) NOT NULL ,";
						insertColumnStringRight+="\'"+column.columnEnName+"\',";
					} else if ("int".equals(column.type.toLowerCase())||"Integer".equals(column.type.toLowerCase())) {
						
						columnstring+="\'"+column.columnEnName+"\' int(10) NOT NULL ,";
						insertColumnStringRight+="1,";
					} else if ("date".equals(column.type.toLowerCase())) {
						
						columnstring+="\'"+column.columnEnName+"\' date NOT NULL ,";
						insertColumnStringRight+="20151011,";
						
					}
					
					insertColumnStringLeft+=column.columnEnName+",";
					
					
				} else {

					if ("string".equals(column.type.toLowerCase()) || "char".equals(column.type.toLowerCase())
							|| "varchar".equals(column.type.toLowerCase())) {
						columnstring+="\'"+column.columnEnName+"\' char(200)  ,";
						insertColumnStringRight+="\'"+column.columnEnName+"\',";
					} else if ("int".equals(column.type.toLowerCase())||"Integer".equals(column.type.toLowerCase())) {

						columnstring+="\'"+column.columnEnName+"\' int(10) ,";
						insertColumnStringRight+="1,";
					} else if ("date".equals(column.type.toLowerCase())) {
						
						columnstring+="\'"+column.columnEnName+"\' date  ,";
						insertColumnStringRight+="20151011,";
					}
					
					insertColumnStringLeft+=column.columnEnName+",";

				}

			}
			
			
			
			tableString+=columnstring.substring(0,columnstring.lastIndexOf(","))+");\n";
			
			System.out.println("建表:"+tableString);
			
			insertString+=insertColumnStringLeft.substring(0,insertColumnStringLeft.lastIndexOf(","))+") VALUES("+insertColumnStringRight.substring(0,insertColumnStringRight.lastIndexOf(","))+");\n";
			
			System.out.println("插入:"+insertString);
		}

		
		
		// CREATE TABLE `people` (
		// `peopleid` smallint(6) NOT NULL AUTO_INCREMENT,
		// `firstname` char(50) NOT NULL,
		// `lastname` char(50) NOT NULL,
		// `age` smallint(6) NOT NULL,
		// `townid` smallint(6) NOT NULL,
		// PRIMARY KEY (`peopleid`),
		// UNIQUE KEY `unique_fname_lname`(`firstname`,`lastname`),
		// KEY `fname_lname_age` (`firstname`,`lastname`,`age`)
		// ) ;

		
		//INSERT INTO tbl_name (col1,col2) VALUES(15,'col');
	}

	public String createQuerySql(List<TableBean> tables) {

		String show = "";
		String condition = "";
		String relate = "";
		
		boolean haveRelate=false;

		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("left".equals(column.leftClickSelected)) {
					show += " " + column.belongWhichTable.tableEnName + "." + column.columnEnName + ",";

				} else if ("right".equals(column.rightClickSelected)) {
					condition += " " + column.belongWhichTable.tableEnName + "." + column.columnEnName + "= ,and";
				} else {

				}

				if (column.relateColumnBeans != null && column.relateColumnBeans.size() > 0) {
					haveRelate=true;
					relate += column.belongWhichTable.tableEnName;
					// 关联的
					for (TableColumnBean relateColumn : column.relateColumnBeans) {

						if (column.relateColumnBeans.size() == 1) {// 两表

							relate += " inner join " + relateColumn.belongWhichTable.tableEnName + " on "
									+ column.belongWhichTable.tableEnName + "." + column.columnEnName + "="
									+ relateColumn.belongWhichTable.tableEnName + "." + relateColumn.columnEnName;

						} else if (column.relateColumnBeans.size() > 1) {// 三表或以上
							relate += " inner join " + relateColumn.belongWhichTable.tableEnName + " on "
									+ column.belongWhichTable.tableEnName + "." + column.columnEnName + "="
									+ relateColumn.belongWhichTable.tableEnName + "." + relateColumn.columnEnName;

						}

					}
				}
			}
		}

		
		
		
		
		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {
		
		
	if(!haveRelate)
		{//单个表
			relate = column.belongWhichTable.tableEnName;
		}
		
		}
	}
		
		
		
		
		
		
		
		String sql="";
		
		if("".equals(show) && "".equals(condition))
		{
			 sql = "select * from " + relate ;
		}
		
		else if("".equals(show) && !"".equals(condition))
		{
			 sql = "select * from " + relate + " where "
						+ condition.substring(0, condition.lastIndexOf(",and"));
		}else if(!"".equals(show) && "".equals(condition))
		{
			 sql = "select " + show.substring(0, show.lastIndexOf(",")) + " from " + relate ;
		}
		else
		{
			 sql = "select " + show.substring(0, show.lastIndexOf(",")) + " from " + relate + " where "
						+ condition.substring(0, condition.lastIndexOf(",and"));
		}
		

		System.out.println("查询:" + sql);

		return sql;

		// select * from student ,course where student.ID=course.ID

		// select s.Name,C.Cname from student_course as sc left join student as
		// s on s.Sno=sc.Sno left join course as c on c.Cno=sc.Cno
	}

}
