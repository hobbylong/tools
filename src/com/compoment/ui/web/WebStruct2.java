package com.compoment.ui.web;

import java.util.List;

import com.compoment.cut.CompomentBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class WebStruct2 {
	
	String pageName;
	String className;
	
	public WebStruct2(String pageName, List<CompomentBean> oldBeans) {
		pageName = pageName;
		className = firstCharToUpperAndJavaName(pageName);
		
		String m="";
		m+="package com.chinapost.jiyouwx.action;\n";

		m+="import java.util.HashMap;\n";
		m+="import java.util.Iterator;\n";
		m+="import java.util.LinkedHashMap;\n";
		m+="import java.util.List;\n";
		m+="import java.util.Map;\n";
		m+="import java.util.Set;\n";

		m+="import javax.annotation.Resource;\n";
		m+="import javax.servlet.http.HttpServletRequest;\n";
		m+="import javax.servlet.http.HttpSession;\n";

		m+="import org.apache.commons.lang.StringUtils;\n";
		m+="import org.apache.struts2.convention.annotation.Action;\n";
		m+="import org.apache.struts2.convention.annotation.Namespace;\n";
		m+="import org.apache.struts2.convention.annotation.ParentPackage;\n";
		m+="import org.apache.struts2.convention.annotation.Result;\n";
		m+="import org.slf4j.Logger;\n";
		m+="import org.slf4j.LoggerFactory;\n";

		m+="import com.chinapost.service.Server;\n";
		m+="import com.chinapost.weixin.util.Const;\n";
		m+="import com.forgon.tools.StrutsParamUtils;\n";
	
		m+="import com.google.gson.Gson;\n";
		m+="import com.opensymphony.xwork2.ActionSupport;\n";

		m+="@SuppressWarnings(\"unchecked\")\n";
		m+="@ParentPackage(value = \"default\")\n";
		m+="@Namespace(value = \"/jiyou/wx\")\n";
		m+="@Action(value = \"AddressAction\" ,results = { \n";
		m+="		@Result(name = \"add\", location = \"/chinapost/jiyouwx/address/addressAdd.jsp\")\n";
		m+="})\n";

		m+="public class "+className+"Action {\n";
		m+="	private static final Logger log = LoggerFactory.getLogger("+className+"Action.class);\n";
		
		m+="\n//文件上传start\n";
		m+="        private String myFileFileName;  \n";
		m+="		    private File myFile;  //<input  name=\"myFile\" name里定义的名字  \n";
		m+="		    private String myFileContentType;  \n";
		m+="		    public String getMyFileFileName() {  \n";
		m+="		        return myFileFileName;  \n";
		m+="		    }  \n";
		m+="		    public void setMyFileFileName(String myFileFileName) {  \n";
		m+="		        this.myFileFileName = myFileFileName;  \n";
		m+="		    }  \n";
		m+="		    public File getMyFile() {  \n";
		m+="		        return myFile;  \n";
		m+="		    }  \n";
		m+="		    public void setMyFile(File myFile) {  \n";
		m+="		        this.myFile = myFile;  \n";
		m+="		    }  \n";
		m+="		    public String getMyFileContentType() {  \n";
		m+="		        return myFileContentType;  \n";
		m+="		    }  \n";
		m+="		    public void setMyFileContentType(String myFileContentType) {  \n";
		m+="		        this.myFileContentType = myFileContentType;  \n";
		m+="		    }  \n";
		m+="//文件上传end\n\n";
		
	
		m+="	\n";
		m+="	public String init(){\n";

		m+="	}\n";
		m+="		\n";

	
	
		m+="	//demo\n";
		m+="	  public String demo() throws Exception{ \n";
		m += "      //第一步 取值  \n";
		m += "     //取值  ajax提交的数据  \n";
		m+="		  HttpServletRequest request = ServletActionContext.getRequest();\n";
		m+="		  String city = request.getParameter(\"city\");\n";
		
		m += "//取值  file  \n";
		m+="	\n//1.拿到ServletContext\n";
		m+="ServletContext servletContext = ServletActionContext.getServletContext();\n";
		m+="//2.调用realPath方法，获取根据一个虚拟目录得到的真实目录	\n";
		m+="String realPath = servletContext.getRealPath(\"/WEB-INF/file\");\n";
		m+="//3.如果这个真实的目录不存在，需要创建\n";
		m+="File file = new File(realPath );\n";
		m+="if(!file.exists()){\n";
		m+="file.mkdirs();\n";
		m+="}\n";
		m+="myfile.renameTo(new File(file,myfileFileName));\n";
		
		
		
		m += "    \n //第二步  发网络请求或发数据库请求 \n";
		m += "    \n";
		
		
		m += "   \n //第三步  正确跳转到哪  错误跳转到哪  一般用forward\n";
		m += "    //A跳到新页面\n";
		m+="		 request.setAttribute(\"address_info\", info);\n";
		m+="		 return \"add\";\n";
		
		m+="		  //B跳回到本页面(带参数)\n";
		m+="		  ServletActionContext.getResponse().setCharacterEncoding(\"UTF-8\");\n";
		m+="		  ServletActionContext.getResponse().getWriter().write(new Gson().toJson(rltMap));\n";
		m+="		  return ActionSupport.NONE;\n";
		m+="	  }\n";
		m+="			\n";
		m+="}\n";
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web", className + "Action", "java", m);

	}

	
	
	public static String firstCharToUpperAndJavaName(String string) {
		// buy_typelist
		String[] ss = string.split("_");
		String temp = "";
		for (String s : ss) {
			if (!s.equals("item"))
				temp += s.substring(0, 1).toUpperCase() + s.substring(1);
		}
		return temp;
	}
}
