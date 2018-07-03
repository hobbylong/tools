package com.compoment.gbkToUtf8.creater;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * GBK文件或目录 转成 UTF-8编码类型
 */
public class GbkToUtf_FileOrDir extends org.apache.commons.io.FileUtils {

	private static final String ENCODE_GBK = "GBK";
	private static final String ENCODE_UTF8 = "UTF-8";

	public static void convertGBK2UTF8(File file) {
		convertFileEncode(file, ENCODE_GBK, ENCODE_UTF8);
	}

	public static void convertUTF82GBK(File file) {
		convertFileEncode(file, ENCODE_UTF8, ENCODE_GBK);
	}

	public static void convertFileEncode(File file, String fromEncode,
			String toEncode) {
		try {
			String str = readFileToString(file, fromEncode);
			writeStringToFile(file, str, toEncode);
			String strResult = readFileToString(file, toEncode);
			System.out.println(strResult);
		} catch (IOException e) {
			System.out.println("Convert failed. File not exsit?");
			e.printStackTrace();
		}
	}

	public static String readFile(String filePath, String encode) {
		String fileContent = "";
		try {
			FileInputStream fis = new FileInputStream(filePath);
			InputStreamReader isr = new InputStreamReader(fis, encode);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer();
			for (String line = br.readLine(); line != null; line = br
					.readLine()) {
				sb.append(line);
			}
			fileContent = sb.toString();
			br.close();
			isr.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not exist��" + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}

	public static String saveFile(String fileContent, String encode,
			String savePath) {
		try {
			FileOutputStream fos = new FileOutputStream(savePath);
			OutputStreamWriter osw = new OutputStreamWriter(fos, encode);
			BufferedWriter bw = new BufferedWriter(osw);

			bw.write(fileContent);
			bw.close();
			osw.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not exist��" + savePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}

	public static synchronized void convertDirectory(File dir,boolean isUtf82Gbk)
			throws IOException {
		if (!dir.exists() && !dir.isDirectory()) {
			throw new IOException("[" + dir + "] not exsit or not a Directory");
		}
		convert(dir,isUtf82Gbk);
	}

	public static void convert(File dir,boolean  isUtf82Gbk) {
		if (dir.canRead() && dir.canWrite()) {
			if (dir.isDirectory()) {// Directory
				String[] files = dir.list();
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						convert(new File(dir, files[i]),isUtf82Gbk);// �ݹ�
					}
				}
			} else {// File
				if(isUtf82Gbk)
				{
					convertUTF82GBK(dir);
				}else
				{
				convertGBK2UTF8(dir);
				}
			}
		}
	}

	public static  void main(String[] args) throws IOException {
		GbkToUtf_FileOrDir gbkToUtf_FileOrDir = new GbkToUtf_FileOrDir();

		// D:\\Workspace\\Android_Demonstrate_AbstractCode\\src\\com\\compoment\\file_manage
		 
		String classDir = gbkToUtf_FileOrDir.getClass().getResource("/").getPath();
       
		File src = new
		 File(classDir+"com/compoment/gbkToUtf8/creater/MarkBefor.txt");
		 boolean isUtf82Gbk=false;//false  Gbk2Utf8
		 convertDirectory(src,isUtf82Gbk);




	}

}