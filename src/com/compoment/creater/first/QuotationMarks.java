package com.compoment.creater.first;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**代码转换成字符串形式String*/
public class QuotationMarks {
	
	public QuotationMarks()
	{try {
		String courseFile = null;
		File directory = new File("");// 参数为空
		courseFile = directory.getCanonicalPath();
		String path = courseFile
				+ "/src/com/compoment/creater/first/MarkBefor.txt";

		//C:\Documents and Settings\Administrator\My Documents\下载\QuotationMarks-github\QuotationMarks-github\QuotationMarks-github\src\com\compoment\creater\first\MarkBefor.txt
		
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);

		String myreadline;
		while (br.ready()) {
			myreadline = br.readLine();

			if (myreadline.length() >= 1) {

				myreadline = myreadline.replace("\"", "\\\"");
				myreadline = "m+=\"" + myreadline + "\\n" + "\";";

			}

			System.out.println(myreadline);// ����Ļ�����?
		}

		br.close();

		br.close();
		fr.close();

	} catch (IOException e) {
		e.printStackTrace();
	}
	}

	public static void main(String[] args) {
		
			QuotationMarks mark = new QuotationMarks();

			
	}

	public static boolean isinteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
