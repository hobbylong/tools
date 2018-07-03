package com.compoment.db.creater;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compoment.db.helper.XmlDBColumnBean;
import com.compoment.db.helper.XmlDBParser;
import com.compoment.db.helper.XmlDBTableBean;





public class CreaterDBContentResolver {
	List<XmlDBTableBean> tables = null;

	public static void main(String[] args) {
		new CreaterDBContentResolver();
	}

	public CreaterDBContentResolver() {
		String classDir = this.getClass().getResource("/").getPath();
		try {

			XmlDBParser xmlDbParser = new XmlDBParser();
			xmlDbParser.parserXml(classDir + "com/compoment/db/db.uxf");
			tables = xmlDbParser.tables;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String className = "";
//		for (XmlDBTableBean table : tables) {
//			className += table.tableName + "_";
//		}

		
		for (XmlDBTableBean table : tables) {
		String m = "\n";
		m += "import java.util.ArrayList;\n";
		m += "import java.util.List;\n";
		m += "import android.content.ContentResolver;\n";
		m += "import android.content.ContentValues;\n";
		m += "import android.content.Context;\n";
		m += "import android.database.Cursor;\n";
		m += "import android.util.Log;\n";

		m += "//http://www.2cto.com/kf/201207/144022.html\n";

		m += "public class " + table.tableName  + "DBContentResolver {\n";
		m += "	private ContentResolver resolver;\n";

		m += "	public static final String FALSE = \"0\";\n";
		m += "	public static final String TRUE = \"1\";\n";

		m += "	public " + table.tableName + "DBContentResolver(Context context) {\n";
		m += "		this.resolver = context.getContentResolver();\n";
		m += "	}\n";

	
			// m+="	public void insert(FileDetailBean messageRecord) {\n";
			m += "/**插入数据(" + table.tableChineseName + ")*/\n";
			m += "	public void insert(" + table.tableName + "Bean "
					+ table.firstCharLowercase(table.tableName) + "Bean"
					+ ") {\n";

			m += "		ContentValues values = new ContentValues();\n";

			// m +=
			// "		values.put(UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageRecordTable.FILE_LENGTH,\n";
			// m += "				messageRecord.getFileLength());\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m+="//"+column.columnChineseName+"\n";
				m += "		values.put(" + className + "DBTableDescribe."
						+ table.tableName + "Table."
						+ column.getSqliteColumnName().toUpperCase() + ", "
						+ table.firstCharLowercase(table.tableName) + "Bean"
						+ ".get" + column.columnName + "());\n";
			}

			m += "		resolver.insert(" + className + "DBTableDescribe."
					+ table.tableName + "Table.CONTENT_URI, values);\n";
			m += "	}\n";

		

	
			m += "/**更新数据(" + table.tableChineseName + ")*/\n";
			// m += "	public int update(FileDetailBean messageRecord) {\n";
			m += "	public int update(" + table.tableName + "Bean "
					+ table.firstCharLowercase(table.tableName) + "Bean"
					+ ") {\n";
			m += "		ContentValues values = new ContentValues();\n";

			// m +=
			// "		values.put(UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageRecordTable.ID, messageRecord.getId());\n";
			int i = 0;
			String sqliteid = "";
			String id = "";
			for (XmlDBColumnBean column : table.columnsName) {
				m+="//"+column.columnChineseName+"\n";
				m += "		values.put(" + className + "DBTableDescribe."
						+ table.tableName + "Table."
						+ column.getSqliteColumnName().toUpperCase() + ", "
						+ table.firstCharLowercase(table.tableName) + "Bean"
						+ ".get" + column.columnName + "());\n";
				if (i == 0) {
					sqliteid = column.getSqliteColumnName().toUpperCase();
					id = column.columnName;
				}
				i++;
			}

			// m+="		int count = resolver.update(UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.FileUploadPartTable.CONTENT_URI,\n";
			// m+="				values, UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.FileUploadPartTable.FILE_ID + \"=?\",\n";
			// m+="				new String[] { messageUploading.getFile_Id() });\n";

			m += "		int count = resolver.update(" + className
					+ "DBTableDescribe." + table.tableName
					+ "Table.CONTENT_URI, values,\n";
			m += "				" + className + "DBTableDescribe." + table.tableName
					+ "Table." + sqliteid + " + \"=?\",\n";
			m += "				new String[] { "
					+ table.firstCharLowercase(table.tableName) + "Bean"
					+ ".get" + id + "() });\n";

			m += "		return count;\n";
			m += "	}\n";

		

		
			m += "/**删除某条数据(" + table.tableChineseName + ")*/\n";
			int i1 = 0;
			String sqliteid1 = "";
			String id1 = "";
			for (XmlDBColumnBean column : table.columnsName) {
				if (i1 == 0) {
					sqliteid1 = column.getSqliteColumnName().toUpperCase();
					id1 = column.columnName;
				}
				i1++;
			}

			m += "	public int delete" + table.tableName + "(String "
					+ table.firstCharLowercase(id1) + ") {\n";

			m += "		int count = resolver.delete(" + className
					+ "DBTableDescribe." + table.tableName
					+ "Table.CONTENT_URI,\n";
			m += "				" + className + "DBTableDescribe." + table.tableName
					+ "Table." + sqliteid1 + " + \"=?\", new String[] { "
					+ table.firstCharLowercase(id1) + " });\n";

			m += "		return count;\n";
			m += "	}\n";
		

	
			m += "/**删除整表数据(" + table.tableChineseName + ")*/\n";
			m += "	public int clear" + table.tableName + "() {\n";
			m += "		int count = resolver.delete(" + className
					+ "DBTableDescribe." + table.tableName
					+ "Table.CONTENT_URI, null,\n";
			m += "				null);\n";

			m += "		return count;\n";
			m += "	}\n";
		

	
			for (XmlDBColumnBean column2 : table.columnsName) {
			m += "/**查询某条表数，据根据"+column2.columnName+column2.columnChineseName+"(" + table.tableChineseName + ")*/\n";
			
			// m +=
			// "	public List<SentDetailBean> querySentByRecordId(String rcd_id) {\n";
			
			m += "	public List<" + table.tableName + "Bean> query" + table.tableName
					+ "By"+column2.columnName+"(String i_" + column2.columnName + ") {\n";

			

			//List<ProductShoppingcarStoreUpBean> productShoppingcarStoreUps = new ArrayList<ProductShoppingcarStoreUpBean>();
			m += "		List<" + table.tableName + "Bean> "
					+ table.firstCharLowercase(table.tableName)
					+ "s= new ArrayList<" + table.tableName + "Bean>();\n";


			// "				.query(UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageSentTable.CONTENT_URI,\n";
			// m +=
			m += "		Cursor cursor = resolver\n";
			m += "				.query(" + className + "DBTableDescribe."
					+ table.tableName + "Table.CONTENT_URI,\n";

			// "						new String[] { UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageSentTable.MESSAGE_SENT_ID,\n";
			// m +=
			// "								UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageSentTable.TIME,\n";
			// m +=
			// "								UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageSentTable.RECEIVER_NUMBER,\n";
			// m +=
			// "								UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageSentTable.SENDER_NUMBER,\n";
			// m +=
			// "								UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageSentTable.CODE },\n";
			m += "						new String[] { ";

			int j = 0;
			for (XmlDBColumnBean column : table.columnsName) {
				j++;
				if (j < table.columnsName.size()) {
					m += className + "DBTableDescribe." + table.tableName
							+ "Table."
							+ column.getSqliteColumnName().toUpperCase()
							+ ",\n";
				} else {
					m += "								" + className + "DBTableDescribe."
							+ table.tableName + "Table."
							+ column.getSqliteColumnName().toUpperCase();
				}

			}

			m += "},\n";

			// "						UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageSentTable.MESSAGE_RECORD_ID + \"=?\",\n";
			// m += "						new String[] { rcd_id }, null);// String ordersort= MESSAGE_RECORD_ID DESC  ...ASC\n";
			m += "						" + className + "DBTableDescribe." + table.tableName
					+ "Table." + column2.getSqliteColumnName().toUpperCase() + "+ \"=?\",\n";
			m += "						new String[] { i_" + column2.columnName
					+ "}, null);// String ordersort= MESSAGE_RECORD_ID DESC  ...ASC\n";

			m += "		if (cursor != null) {\n";
			m += "			if (cursor.moveToFirst()) {\n";

			// m += "				int indexOfSentId = cursor\n";
			// m +=
			// "						.getColumnIndex(UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.MessageSentTable.MESSAGE_SENT_ID);\n";
			for (XmlDBColumnBean column : table.columnsName) {
				m += "				int indexOf" + column.columnName + " = cursor\n";
				m += "						.getColumnIndex(" + className + "DBTableDescribe."
						+ table.tableName + "Table."
						+ column.getSqliteColumnName().toUpperCase() + ");\n";
			}

		
			m += "				do {\n";

			//	m += "					String path = cursor.getString(indexOfPath);\n";
			for (XmlDBColumnBean column : table.columnsName) {
			m+="//"+column.columnChineseName+"\n";
			m += "					String "+table.firstCharLowercase(column.columnName)+" = cursor.getString(indexOf"+column.columnName+");\n";

			}


			//	ProductShoppingcarStoreUpBean productShoppingcarStoreUpBean = new ProductShoppingcarStoreUpBean();
			m += "					"+table.tableName+"Bean "+table.firstCharLowercase(table.tableName)+"Bean = new "+table.tableName+"Bean();\n" ;


			//productShoppingcarStoreUpBean.setBuyNumber(buyNumber);
			for (XmlDBColumnBean column : table.columnsName) {
				m+="//"+column.columnChineseName+"\n";
			m+=table.firstCharLowercase(table.tableName)+"Bean.set"+column.columnName+"("+table.firstCharLowercase(column.columnName)+");\n";
			}


			//productShoppingcarStoreUps.add(productShoppingcarStoreUpBean);
			m += "					"+table.firstCharLowercase(table.tableName)+"s.add("+table.firstCharLowercase(table.tableName)+"Bean);\n";
			m += "				} while (cursor.moveToNext());\n";
			m += "			}\n";

			m += "			cursor.close();\n";
			m += "		}\n";

			m += "		return " +table.firstCharLowercase(table.tableName)
					+ "s;\n";
			m += "	}\n";
		}

	
			m += "/**查询整表数据(" + table.tableChineseName + ")*/\n";

			//public List<ProductShoppingcarStoreUpBean> queryAllProductShoppingcarStoreUp() {
			m += "	public List<" + table.tableName + "Bean> queryAll"
					+ table.tableName + "() {\n";

			//List<ProductShoppingcarStoreUpBean> productShoppingcarStoreUps = new ArrayList<ProductShoppingcarStoreUpBean>();
			m += "		List<" + table.tableName + "Bean> "
					+ table.firstCharLowercase(table.tableName)
					+ "s= new ArrayList<" + table.tableName + "Bean>();\n";

			m += "		Cursor cursor = resolver.query(" + className
					+ "DBTableDescribe." + table.tableName
					+ "Table.CONTENT_URI,\n";
			m += "				new String[] { ";



//			new String[] {
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.PRODUCT_ID,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.PRODUCT_CLASS,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.PRODUCT_NAME,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.PRODUCT_PIC,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.PRODUCT_SMAL_PIC,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.PRODUCT_INFO,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.PRODUCT_STYPE,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.PRODUCT_PRICE,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.PRODUCT_DISCOUNT,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.PRODUCT_PREFERENTIAL_PRICE,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.PRODUCT_INTEGRATE,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.IS_IN_SHOPPING_CAR,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.IS_STORE_UP,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.BUY_NUMBER,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.INVENTORY_NUMBER,
//					Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.IS_GIFT },
//			null, null, null);
			int j = 0;
			for (XmlDBColumnBean column : table.columnsName) {
				j++;
				if (j < table.columnsName.size()) {
					m += "" + className + "DBTableDescribe." + table.tableName
							+ "Table."
							+ column.getSqliteColumnName().toUpperCase()
							+ ",\n";
				} else {
					m += "" + className + "DBTableDescribe." + table.tableName
							+ "Table."
							+ column.getSqliteColumnName().toUpperCase();
				}

			}
			m += "}, null, null, null);\n";

			m += "		if (cursor != null) {\n";
			m += "			if (cursor.moveToFirst()) {\n";

			//				int indexOfisGift = cursor.getColumnIndex(Account_Deliver_OrderForm_ProductShoppingcarStoreUp_DBTableDescribe.ProductShoppingcarStoreUpTable.IS_GIFT);
			for (XmlDBColumnBean column : table.columnsName) {
			m += "				int indexOf"+column.columnName+" = cursor\n";
			m += "						.getColumnIndex("+className+"DBTableDescribe."+table.tableName+"Table."+column.getSqliteColumnName().toUpperCase()+");\n";
			}



			m += "				do {\n";

			//	m += "					String path = cursor.getString(indexOfPath);\n";
			for (XmlDBColumnBean column : table.columnsName) {
			m+="//"+column.columnChineseName+"\n";
			m += "					String "+table.firstCharLowercase(column.columnName)+" = cursor.getString(indexOf"+column.columnName+");\n";

			}


			//	ProductShoppingcarStoreUpBean productShoppingcarStoreUpBean = new ProductShoppingcarStoreUpBean();
			m += "					"+table.tableName+"Bean "+table.firstCharLowercase(table.tableName)+"Bean = new "+table.tableName+"Bean();\n" ;


			//productShoppingcarStoreUpBean.setBuyNumber(buyNumber);
			for (XmlDBColumnBean column : table.columnsName) {
				m+="//"+column.columnChineseName+"\n";
			m+=table.firstCharLowercase(table.tableName)+"Bean.set"+column.columnName+"("+table.firstCharLowercase(column.columnName)+");\n";
			}


			//productShoppingcarStoreUps.add(productShoppingcarStoreUpBean);
			m += "					"+table.firstCharLowercase(table.tableName)+"s.add("+table.firstCharLowercase(table.tableName)+"Bean);\n";
			m += "				} while (cursor.moveToNext());\n";
			m += "			}\n";

			m += "			cursor.close();\n";
			m += "		}\n";

			m += "		return "+table.firstCharLowercase(table.tableName)+"s;\n";
			m += "	}\n";
		

		m += "	}\n";

		System.out.println(m);
		
		stringToFile("d:\\"+table.tableName+"DBContentResolver.java",m);
		}
	}

	
	public void stringToFile(String fileName,String str)
	{
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			fw.write(str); 
			fw.flush();//加上这句
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
