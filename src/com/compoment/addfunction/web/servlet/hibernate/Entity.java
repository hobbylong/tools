package com.compoment.addfunction.web.servlet.hibernate;

import java.util.List;

import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class Entity {

	public void entity(List<TableBean> tables,String interfaceName,String interfaceCnName) {
		String m = "";
		String m1 = "";
		

		for (TableBean table : tables) {
			m="";
			m += "package com.company.pojo;\n";
			m += "import java.util.List;\n";
			m += "import java.util.Map;\n";
			
			m += "import javax.persistence.Entity;\n";
			m += "import javax.persistence.Id;\n";
			m += "import javax.persistence.Table;\n";
			m += "import javax.persistence.GeneratedValue;\n";
			m += "import javax.persistence.GenerationType;\n";
			m += "import org.apache.commons.lang.builder.ToStringBuilder;\n";
			m += "import org.hibernate.annotations.Cache;\n";
			m += "import org.hibernate.annotations.CacheConcurrencyStrategy;\n";
			m += "//"+interfaceCnName+"\n";
			m1 = "";
			m += "@Entity\n";
			m += "@Table(name = \""+table.tableEnName+"\")\n";
		
			m += "public class "
					+ StringUtil.firstCharToUpperAndJavaName(table.tableEnName)
					+ "Bean {\n";
			
			for (TableColumnBean column : table.columns) {

				if (column.type.toLowerCase().contains("int")) {
					m += "	public Integer " + column.columnEnName + ";/**"
							+ column.columnCnName + "*/\n";
					m1 += "	public Integer get"
							+ StringUtil
									.firstCharToUpperAndJavaName(column.columnEnName)
							+ "() {\n";
					m1 += "		return " + column.columnEnName + ";\n";
					m1 += "	}\n";

					m1 += "	public void set"
							+ StringUtil
									.firstCharToUpperAndJavaName(column.columnEnName)
							+ "(Integer " + column.columnEnName + ") {\n";
					m1 += "		this." + column.columnEnName + " = "
							+ column.columnEnName + ";\n";
					m1 += "	}\n";
					
					m1 += "	public Integer get"
							+ StringUtil
									.firstCharToUpperRetainUnderLine(column.columnEnName)
							+ "() {\n";
					m1 += "		return " + column.columnEnName + ";\n";
					m1 += "	}\n";

					m1 += "	public void set"
							+ StringUtil
									.firstCharToUpperRetainUnderLine(column.columnEnName)
							+ "(Integer " + column.columnEnName + ") {\n";
					m1 += "		this." + column.columnEnName + " = "
							+ column.columnEnName + ";\n";
					m1 += "	}\n";
					
					
				} else {
					m += "	public String " + column.columnEnName + ";/**"
							+ column.columnCnName + "*/\n";
					m1 += "	public String get"
							+ StringUtil
									.firstCharToUpperAndJavaName(column.columnEnName)
							+ "() {\n";
					m1 += "		return " + column.columnEnName + ";\n";
					m1 += "	}\n";

					m1 += "	public void set"
							+ StringUtil
									.firstCharToUpperAndJavaName(column.columnEnName)
							+ "(String " + column.columnEnName + ") {\n";
					m1 += "		this." + column.columnEnName + " = "
							+ column.columnEnName + ";\n";
					m1 += "	}\n";
					
					m1 += "	public String get"
							+ StringUtil
									.firstCharToUpperRetainUnderLine(column.columnEnName)
							+ "() {\n";
					m1 += "		return " + column.columnEnName + ";\n";
					m1 += "	}\n";

					m1 += "	public void set"
							+ StringUtil
									.firstCharToUpperRetainUnderLine(column.columnEnName)
							+ "(String " + column.columnEnName + ") {\n";
					m1 += "		this." + column.columnEnName + " = "
							+ column.columnEnName + ";\n";
					m1 += "	}\n";
				}
			}

		
				FileUtil.makeFile(
						KeyValue.readCache("projectPath"),
						"src/web",
						StringUtil
								.firstCharToUpperAndJavaName(table.tableEnName)
								+ "Bean", "java", m + m1 + "}\n");
			

		}

	}

}
