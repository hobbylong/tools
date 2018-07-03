import java.io.File;
import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;



public class RemoveRMI {

	 String sourceFile;
	
	public static void main(String[] args) {
		
		
		
		String classDir = "";

		File directory = new File("");// 参数为空
		try {
			classDir = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sourceFile = classDir + "/src/com";


	
		
		RemoveRMI rename=new RemoveRMI();
		
		rename.reName(sourceFile);
	}
	
	public RemoveRMI()
	{
		
		String classDir = "";

		File directory = new File("");// 参数为空
		try {
			classDir = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sourceFile = classDir + "/src/com";

	}

	File currentFile;
	String currentName;
	public  void reName(String filePath) {
		File rootFile = new File(filePath);
		if (rootFile.isDirectory()) {
			File files[] = rootFile.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					File f = files[i];
					if (f.isDirectory()) {
						reName(f.getAbsolutePath());
					} else {
						currentFile=f;
						currentName= "A"+Math.round(Math.random() * 8999 + 1000) ;
						
						{
							 boolean isRmiImplement=false;
						List<String> lines = FileUtil.fileContentToArrayList(f.getAbsolutePath());
						String m="";
						for(int ii=0;ii<lines.size();ii++)
						{
							String line=lines.get(ii);
							
							if(line.contains("extends UnicastRemoteObject"))
							{
								isRmiImplement=true;
							}
							
							m+=line+"\n";
							
						}
						
						
						if(isRmiImplement)
						{
							
							reContent(sourceFile);
						
							f.delete();
						}
						
						}
						
						
						
					}
				}
			}
		} else {
			//rootFile.renameTo(new File(path + Math.round(Math.random() * 8999 + 1000) + ".jpg"));// 记得将路径也输入
		}
	}
	
	
	
	
	public  void reContent(String filePath) {
		File rootFile = new File(filePath);
		if (rootFile.isDirectory()) {
			File files[] = rootFile.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					File f = files[i];
					if (f.isDirectory()) {
						reContent(f.getAbsolutePath());
					} else {
						
						
						List<String> lines = FileUtil.fileContentToArrayList(f.getAbsolutePath());
						String m="";
						for(int ii=0;ii<lines.size();ii++)
						{
							String line=lines.get(ii);
							int point=currentFile.getName().indexOf(".");
							if(point!=-1)
							{
							String filename=currentFile.getName().substring(0, point);
							
							if(line.contains("import ")&&line.contains(filename) && rightIsWhat(line,filename) && leftIsWhat(line,filename) )
							{
								line="";
							}
							}
							m+=line+"\n";
							
						}
						
						
						FileUtil.makeFile(f.getAbsolutePath(),m);
					}
				}
			}
		} else {
			//rootFile.renameTo(new File(path + Math.round(Math.random() * 8999 + 1000) + ".jpg"));// 记得将路径也输入
		}
	}
	
	
	public boolean  rightIsWhat(String line,String key)
	{
		
		
		int p=line.indexOf(key);
		if(p!=-1)
		{
			
			if(p+key.length()+1<=line.length())
			{
		String what=line.substring(p+key.length(),p+key.length()+1);
		
		if(what.equals(";")||what.equals(".")||what.equals(">")||what.equals(" ")||what.equals("{")||what.equals("(")||what.equals(")"))
			return true;
		}}
		return false;
	}

	
	
	
	public boolean  leftIsWhat(String line,String key)
	{
		
		
		int p=line.indexOf(key);
		if(p!=-1)
		{
			
			if(p-1>=0)
			{
		String what=line.substring(p-1,p);
		
		if(what.equals(",")||what.equals(".")||what.equals("<")||what.equals(" ")||what.equals("	")||what.equals("{")||what.equals("("))
			return true;
		}}
		return false;
	}
	
}
