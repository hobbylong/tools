package com.compoment.util;

import java.awt.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileUtil {

	/**
	 * 字符串写出到文件
	 */
	public static String makeFile(String fileFullPath, String s) {

		try {

			File tofile = new File(fileFullPath);
			if (!tofile.exists()) {
				makeDir(tofile.getParentFile());
			}

			tofile.createNewFile();

			FileWriter fw;

			fw = new FileWriter(tofile);
			BufferedWriter buffw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(buffw);

			pw.println(s);

			pw.close();
			buffw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileFullPath;
	}

	/**
	 * 字符串写出到文件
	 */
	public static String makeFile(String filePath, String secondPath, String fileName, String fileType, String s) {
		String xmlFileName = "";
		try {
			if (secondPath == null) {
				xmlFileName = filePath + "/" + fileName + "." + fileType;
			} else {
				xmlFileName = filePath + "/" + secondPath + "/" + fileName + "." + fileType;
			}

			{
			File tofile = new File(xmlFileName);
			if (!tofile.exists()) {
				makeDir(tofile.getParentFile());
			}else
			{
				tofile.delete();
			}}

			File tofile = new File(xmlFileName);
			tofile.createNewFile();

			FileWriter fw;

			fw = new FileWriter(tofile);
			BufferedWriter buffw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(buffw);

			pw.println(s);

			pw.close();
			buffw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return xmlFileName;
	}

	public static void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}

	/**
	 * 取文件内容到字符串
	 */
	public static String fileContent(String filePath) {
		String myreadline = "";
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);

			while (br.ready()) {
				myreadline += br.readLine() + "\n";

			}

			br.close();

			br.close();
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return myreadline;

	}

	/**
	 * 取文件内容到字符串
	 */
	public static ArrayList fileContentToArrayList(String filePath) {
		String myreadline = "";
		ArrayList lines = new ArrayList();
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);

			while (br.ready()) {
				// myreadline += br.readLine() + "\n";
				lines.add(br.readLine());
			}

			br.close();

			br.close();
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;

	}

	/** 递归方法 找文件 */
	public static File findFile(File startDirectory, String wantFindFileName) {

		File[] childs = startDirectory.listFiles(); // listFiles()返回目录下的所有文件.
		for (int i = 0; i < childs.length; i++) {

			if (childs[i].isDirectory()) { // isDirectory()判断是否为目录
				findFile(childs[i], wantFindFileName);
			} else {
				String fileName = childs[i].getName();
				if (fileName.equals(wantFindFileName)) {
					return childs[i];
				}
			}
		}
		return null;
	}

	public static boolean isFileExist(String wantFindFileFullPath) {

		File file = new File(wantFindFileFullPath);

		if (file != null && file.isFile() && file.exists()) {

			return true;
		}
		return false;
	}

	public static File[] findFiles(File startDirectory) {

		File[] childs = startDirectory.listFiles(); // listFiles()返回目录下的所有文件.
		return childs;
	}

	// 复制文件
	public static void copyFile(File sourceFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// 缓冲数组
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	public static boolean isDirectory(String filePath) {
		File temp = new File(filePath);
		return temp.isDirectory();
	}

	/**
	 * 移动指定文件或文件夹(包括所有文件和子文件夹)
	 * 
	 * @param fromDir
	 *            要移动的文件或文件夹
	 * @param toDir
	 *            目标文件夹
	 * @throws Exception
	 */
	public static void MoveFolderAndFileWithSelf(String from, String to) throws Exception {
		try {
			File dir = new File(from);
			// 目标
			to += File.separator + dir.getName();
			File moveDir = new File(to);
			if (dir.isDirectory()) {
				if (!moveDir.exists()) {
					moveDir.mkdirs();
				}
			} else {
				File tofile = new File(to);
				dir.renameTo(tofile);
				return;
			}

			// System.out.println("dir.isDirectory()"+dir.isDirectory());
			// System.out.println("dir.isFile():"+dir.isFile());

			// 文件一览
			File[] files = dir.listFiles();
			if (files == null)
				return;

			// 文件移动
			for (int i = 0; i < files.length; i++) {
				System.out.println("文件名：" + files[i].getName());
				if (files[i].isDirectory()) {
					MoveFolderAndFileWithSelf(files[i].getPath(), to);
					// 成功，删除原文件
					files[i].delete();
				}
				File moveFile = new File(moveDir.getPath() + File.separator + files[i].getName());
				// 目标文件夹下存在的话，删除
				if (moveFile.exists()) {
					moveFile.delete();
				}
				files[i].renameTo(moveFile);
			}
			dir.delete();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 复制单个文件(可更名复制)
	 * 
	 * @param oldPathFile
	 *            准备复制的文件源
	 * @param newPathFile
	 *            拷贝到新绝对路径带文件名(注：目录路径需带文件名)
	 * @return
	 */
	public static void CopySingleFile(String oldPathFile, String newPathFile) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPathFile);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPathFile); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPathFile);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					// System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 复制单个文件(原名复制)
	 * 
	 * @param oldPathFile
	 *            准备复制的文件源
	 * @param newPathFile
	 *            拷贝到新绝对路径带文件名(注：目录路径需带文件名)
	 * @return
	 */
	public static void CopySingleFileTo(String oldPathFile, String targetPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPathFile);
			String targetfile = targetPath + File.separator + oldfile.getName();
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPathFile); // 读入原文件
				FileOutputStream fs = new FileOutputStream(targetfile);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					// System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 复制整个文件夹的内容(含自身)
	 * 
	 * @param oldPath
	 *            准备拷贝的目录
	 * @param newPath
	 *            指定绝对路径的新目录
	 * @return
	 */
	public static void copyFolderWithSelf(String oldPath, String newPath) {
		try {
			new File(newPath).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File dir = new File(oldPath);
			// 目标
			newPath += File.separator + dir.getName();
			File moveDir = new File(newPath);
			if (dir.isDirectory()) {
				if (!moveDir.exists()) {
					moveDir.mkdirs();
				}
			}
			String[] file = dir.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) { // 如果是子文件夹
					copyFolderWithSelf(oldPath + "/" + file[i], newPath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void unZip(File zipfile, String outputDirPath) {

		ZipInputStream Zin;
		try {

			Zin = new ZipInputStream(new FileInputStream(zipfile));
			// 输入源zip路径
			BufferedInputStream Bin = new BufferedInputStream(Zin);
			String Parent = outputDirPath; // 输出路径（文件夹目录）
			File Fout = null;
			ZipEntry entry;

			while ((entry = Zin.getNextEntry()) != null && !entry.isDirectory()) {
				Fout = new File(Parent, entry.getName());
				if (!Fout.exists()) {
					(new File(Fout.getParent())).mkdirs();
				}
				FileOutputStream out = new FileOutputStream(Fout);
				BufferedOutputStream Bout = new BufferedOutputStream(out);
				int b;
				while ((b = Bin.read()) != -1) {
					Bout.write(b);
				}
				Bout.close();
				out.close();
				System.out.println(Fout + "解压成功");
			}
			Bin.close();
			Zin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	//这个方法比较重要，通过这个方法把一个名为filename的文件转化为一个byte数组  
    public static byte[] fileToByte(String filename){  
        byte[] b = null;  
        BufferedInputStream is = null;  
        try {  
            System.out.println("开始>>>>"+System.currentTimeMillis());  
            File file = new File(filename);  
            b = new byte[(int) file.length()];  
            is = new BufferedInputStream(new FileInputStream(file));  
            is.read(b);  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if(is != null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            }  
        }  
        return b;  
    }  
	
    
    public static void  byteToFile(String filename,byte[] fileContent)
    {
    	
    	 File file = new File(filename);  
         BufferedOutputStream os = null;  
         try {  
             if (!file.exists()) {  
                 file.createNewFile();  
             }  
               
             os = new BufferedOutputStream(new FileOutputStream(file));  
             os.write(fileContent);  
         } catch (FileNotFoundException e) {  
             e.printStackTrace();  
         } catch (IOException e) {  
             e.printStackTrace();  
         } finally {  
             if(os != null) {  
                 try {  
                     os.flush();  
                     os.close();  
                     System.out.println("结束>>>>"+System.currentTimeMillis());  
                 } catch (IOException e) {  
                     e.printStackTrace();  
                 }  
             }  
         }  
    }
	


}
