package com.compoment.util;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.Remote;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllFileNames {

	String sourceFile;
	String unRenameFile;

	public static void main(String[] args) {

		

		AllFileNames rename = new AllFileNames();

	 
	}

	public AllFileNames() {

		String classDir = "";

		
			classDir = this.getClass().getResource("/").getPath();
		

		sourceFile = classDir + "/com";
		unRenameFile="InterfaceBean;";
		
		reName(sourceFile);
		
		
		//System.out.println(getAllFileNames());
		
	}

	File currentFile;
	String currentName;
	String currentFileParent;

	String allFileNames;
	
	public String getAllFileNames()
	{
		
		return allFileNames;
	}
	
	public void reName(String filePath) {
		File rootFile = new File(filePath);
		if (rootFile.isDirectory()) {
			File files[] = rootFile.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					File f = files[i];
					if (f.isDirectory()) {
						reName(f.getAbsolutePath());
					} else {
						currentFile = f;

						allFileNames+=currentFile.getName();
					}
				}
			}
			
		} else {
			// rootFile.renameTo(new File(path + Math.round(Math.random() * 8999
			// + 1000) + ".jpg"));// 记得将路径也输入
		}
		
		
		
		
		
	}


}
