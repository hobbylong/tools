package com.compoment.jsonToJava.creater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import com.compoment.util.FileUtil;

//http://bbs.csdn.net/topics/390493566
//http://haohaoxuexi.iteye.com/blog/2049110
//http://blog.csdn.net/gltide/article/details/39929259
class JavaObjectToWordtable {

	// （1）poi的HWPF是不能新建word文档的，只能先有一个文档，再生成一个新文档。这个我试验过很多次了，但是也可能是错的，仅供大家参考哈~~关键代码如下：

	public static void main(String[] args) throws Exception {
		JavaObjectToWordtable javaObjectToWordtable = new JavaObjectToWordtable();
		//javaObjectToWordtable.createWordTable();
		javaObjectToWordtable.dbTable();
	}

	public JavaObjectToWordtable() {

	}

	public void createWordTable() {

		String sourceFile;
		String classDir = "";

		File directory = new File("");// 参数为空
		try {
			classDir = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sourceFile = classDir + "/src/com/compoment/jsonToJava/creater/"
				+ "sqlToWordTable.txt";

		String destinationFile = classDir
				+ "/src/com/compoment/jsonToJava/creater/"
				+ "JaveObjectTowordTable.docx";

		List<String> lines = FileUtil.fileContentToArrayList(sourceFile);

		XWPFDocument document = new XWPFDocument();
		XWPFTable tableOne = null;

		for (String line : lines) {

			// requestTableStart:中文:3
			if (line.contains("requestTableStart")) {
				String head[] = line.split(":");

				// //创建一个段落
				XWPFParagraph para2 = document.createParagraph();
				XWPFRun run2 = para2.createRun();
				run2.setBold(true); // 加粗
				run2.setText(head[1]);
				run2.setText("(" + head[2] + ")");

				// 创建一个段落
				XWPFParagraph para3 = document.createParagraph();
				XWPFRun run3 = para3.createRun();
				run3.setBold(true); // 加粗
				run3.setText("输入参数");

				tableOne = document.createTable();

				// 第一行
				XWPFTableRow tableOneRow0 = tableOne.getRow(0);
				tableOneRow0.getCell(0).setText("参数名");
				tableOneRow0.createCell().setText("变量域");
				tableOneRow0.createCell().setText("类型");
				tableOneRow0.createCell().setText("长度");
				tableOneRow0.createCell().setText("说明");

			}

			// 1 变量声明式
			String type = "";
			String name = "";
			String toast = "";
			// private BaseServiceInterface mBaseService;//阿反对
			String regex = "(public|private)(\\s+)(String|int|float|long)(\\s+)(\\w+)(\\s*)(;)(\\s*)(//)(\\w*)([\\s\\S]*)";
			Pattern pattern = Pattern.compile(regex);

			Matcher matcher = pattern.matcher(line);
			if (matcher.find()) {
				type = matcher.group(3);// 类型
				name = matcher.group(5);// 名字
				toast = matcher.group(11);// 中文注视

				XWPFTableRow tableOneRow3 = tableOne.createRow();
				tableOneRow3.getCell(0).setText(toast);
				tableOneRow3.getCell(1).setText(name);
				tableOneRow3.getCell(2).setText(type);
				tableOneRow3.getCell(3).setText("");
				tableOneRow3.getCell(4).setText("");
			}

			// 2 "id": 0,"name":"zhan"
			String regex1 = "(\")(\\s*)(\\w+)(\\s*)(\")(\\s*)(:)(\\s*)(\"?)(\\w+)(\"?)(\\s*)(,*)";
			Pattern pattern1 = Pattern.compile(regex1);

			Matcher matcher1 = pattern1.matcher(line);
			if (matcher1.find()) {

				XWPFTableRow tableOneRow3 = tableOne.createRow();
				tableOneRow3.getCell(0).setText("");
				tableOneRow3.getCell(1).setText(matcher1.group(3));
				tableOneRow3.getCell(2).setText("");
				tableOneRow3.getCell(3).setText("");
				tableOneRow3.getCell(4).setText("");
			}

		}

		FileOutputStream fOut;

		try {
			fOut = new FileOutputStream(destinationFile);

			document.write(fOut);
			fOut.flush();
			// 操作结束，关闭文件
			fOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dbTable() {

		String sourceFile;
		String classDir = "";

		File directory = new File("");// 参数为空
		try {
			classDir = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sourceFile = classDir + "/src/com/compoment/jsonToJava/creater/"
				+ "someto";

		String destinationFile = classDir
				+ "/src/com/compoment/jsonToJava/creater/"
				+ "JaveObjectTowordTable.docx";

		String lines = FileUtil.fileContent(sourceFile);

		String outputFile = destinationFile;

		XWPFDocument document = new XWPFDocument();
		XWPFTable tableOne = null;

		tableOne = document.createTable();

		// 第一行
		XWPFTableRow tableOneRow0 = tableOne.getRow(0);
		tableOneRow0.getCell(0).setText("参数名");
		tableOneRow0.createCell().setText("变量域");
		tableOneRow0.createCell().setText("类型");
		tableOneRow0.createCell().setText("长度");
		tableOneRow0.createCell().setText("说明");

		//String line = " CREATE TABLE pedometer_app_log (app_log_type   VARCHAR2(25)  NOT  null,app_start_time   VARCHAR2(25) NOT NULL PRIMARY KEY,dd";
		String regex2 = "(,?)(\\w+)(\\s+)(VARCHAR2|INTEGER|TEXT|REAL)(\\w*)(,?)";
		Pattern pattern2 = Pattern.compile(regex2);

		Matcher matcher2 = pattern2.matcher(lines);
		while (matcher2.find()) {
			
			
			String name = matcher2.group(2);
			String type = matcher2.group(4);
			XWPFTableRow tableOneRow3 = tableOne.createRow();
			tableOneRow3.getCell(0).setText("");
			tableOneRow3.getCell(1).setText(name);
			tableOneRow3.getCell(2).setText(type);
			tableOneRow3.getCell(3).setText("");
			tableOneRow3.getCell(4).setText("");

		}

		FileOutputStream fOut;

		try {
			fOut = new FileOutputStream(destinationFile);

			document.write(fOut);
			fOut.flush();
			// 操作结束，关闭文件
			fOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
