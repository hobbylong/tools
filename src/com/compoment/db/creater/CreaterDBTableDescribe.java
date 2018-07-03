package com.compoment.db.creater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.compoment.db.helper.XmlDBColumnBean;
import com.compoment.db.helper.XmlDBParser;
import com.compoment.db.helper.XmlDBTableBean;

public class CreaterDBTableDescribe {

	List<XmlDBTableBean> tables = null;

	public static void main(String[] args) {
		new CreaterDBTableDescribe();
	}

	public CreaterDBTableDescribe() {

		String classDir = this.getClass().getResource("/").getPath();
		try {

			XmlDBParser xmlDbParser = new XmlDBParser();
			xmlDbParser.parserXml(classDir + "com/compoment/db/db.uxf");
			tables = xmlDbParser.tables;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String m = "\n";

		m += "import android.net.Uri;\n";
		m += "import android.provider.BaseColumns;\n";

		// public final class
		// OrderForm_Deliver_Account_ProductShoppingcarStoreUp_DBTableDescribe
		// {// 订单_送货_帐户信息_产品购物车　收藏_
		String className = "";
//		for (XmlDBTableBean table : tables) {
//			className += table.tableName + "_";
//		}
		className = "DBTableDescribe";

		m += "/**";
		for (XmlDBTableBean table : tables) {
			m += table.tableChineseName + "_";
		}
		m += "*/\n";

		m += "public final class " + className;

		m += " {";



		// public static final String AUTHORITY =
		// "DBContentProvider";
		// public static final Uri AUTHORITY_URI = Uri.parse("content://" +
		// AUTHORITY);

		int pos=className.lastIndexOf("_");
		m += "	public static final String AUTHORITY = \"" 
				+ "DBContentProvider\";\n";
		m += "	public static final Uri AUTHORITY_URI = Uri.parse(\"content://\" + AUTHORITY);\n";

		// m +=
		// "	public static final String TABLE_MESSAGE_RECORD = \"message_record\";\n";

		// m +=
		// "	public static final class MessageUploadedTable implements BaseColumns,\n";
		// m += "			MessageUploadedColumns {\n";
		// m +=
		// "		public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI,\n";
		// m +=
		// "				UploadComplete_UploadPart_FileDetail_SendDetail_DBTableDescribe.TABLE_MESSAGE_UPLOADED);\n";
		// m += "}";

		// m += "	protected interface MessageRecordColumns {\n";
		// m += "		public static final String ID = \"rcd_id\"; // 录制id\n";
		// m += "		public static final String PATH = \"path\"; // 留言媒体文件\n";
		// m +=
		// "		public static final String RECORDER_TIME = \"rcd_time\"; // 录制时间\n";
		// m +=
		// "		public static final String TYPE = \"type\"; // 留言媒体类型：audio或video\n";
		// m +=
		// "		public static final String FILE_LENGTH = \"file_len\"; // 留言媒体文件长度\n";
		// m +=
		// "		public static final String DURATION = \"duration\"; // 留言媒体播放长度，单位：毫秒，精确到秒\n";
		// m +=
		// "		public static final String PREVIEW_IMAGE_PATH = \"prev_img_path\"; // 视频预览图路径\n";
		// m += "}";

		for (XmlDBTableBean table : tables) {
			m += "public static final String ";
			m += table.getSqliteTableName().toUpperCase() + "_TABLE=\""
					+ table.getSqliteTableName() + "\";\n";

		}

		for (XmlDBTableBean table : tables) {

			m += "	public static final class " + table.tableName
					+ "Table implements BaseColumns,\n";
			m += "			" + table.tableName + "Columns {\n";
			m += "		public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI,\n";
			m += "				" + className + "."
					+ table.getSqliteTableName().toUpperCase() + "_TABLE"
					+ ");\n";
			m += "}\n";
		}

		for (XmlDBTableBean table : tables) {
			m += "	protected interface " + table.tableName + "Columns {\n";

			for (XmlDBColumnBean column : table.columnsName) {

				m+="/** "+column.columnChineseName +" */\n";
				m += "		public static final String "
						+ column.getSqliteColumnName().toUpperCase() + " = \""
						+ column.getSqliteColumnName() + "\"; \n";

			}

			m += "}\n";

		}
		m += "}";
		System.out.println(m);
		stringToFile("d:\\"+className+".java",m);
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
