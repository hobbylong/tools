package com.compoment.util;

public class ImportString {
	
	public static String  autoAddImportInMFileHead(String fileString)
	{
		
		String importString="";
		String lines[]=fileString.split("\n");
		
		for(String line:lines)
		{
			if(line.contains("alloc"))
			{
				
				int start=line.lastIndexOf("[");
				int end=line.indexOf("alloc");
				String name=line.substring(start+1,end);
				if(((String)name.subSequence(0, 2)).equals("NS") ||((String)name.subSequence(0, 2)).equals("UI") )
				{
					
				}else
				{
				importString+="#import \""+line.substring(start+1,end).trim()+".h\"\n";
				}
			}
			
			
			
			if(line.contains("loadNibNamed:"))
			{
				
				int start=line.indexOf("\"");
				int end=line.lastIndexOf("\"");
				String name=line.substring(start+1,end);
				importString+="#import \""+line.substring(start+1,end).trim()+".h\"\n";
			}
			
		}
		
		
		fileString=importString+fileString;
		return fileString;
		
	}

}
