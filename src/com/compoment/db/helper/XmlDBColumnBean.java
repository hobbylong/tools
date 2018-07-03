package com.compoment.db.helper;
// 内部类
	public class XmlDBColumnBean {
		public String columnName;
		public String columnChineseName;


		public String getSqliteColumnName() {
			String sqliteTableName="";
			for (int i = 0; i < columnName.length(); i++) {
				char c = columnName.charAt(i);
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
	}