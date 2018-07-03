package com.chinapost.jiyouwx.action;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.chinapost.service.Server;
import com.chinapost.weixin.util.Const;
import com.forgon.tools.StrutsParamUtils;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("unchecked")
@ParentPackage(value = "default")
@Namespace(value = "/jiyou/wx")
@Action(value = "AddressAction" ,results = { 
		@Result(name = "add", location = "/chinapost/jiyouwx/address/addressAdd.jsp")
})
public class QuestionSuggestDetailAction {
	private static final Logger log = LoggerFactory.getLogger(QuestionSuggestDetailAction.class);

//文件上传start
        private String myFileFileName;  
		    private File myFile;  //<input  name="myFile" name里定义的名字  
		    private String myFileContentType;  
		    public String getMyFileFileName() {  
		        return myFileFileName;  
		    }  
		    public void setMyFileFileName(String myFileFileName) {  
		        this.myFileFileName = myFileFileName;  
		    }  
		    public File getMyFile() {  
		        return myFile;  
		    }  
		    public void setMyFile(File myFile) {  
		        this.myFile = myFile;  
		    }  
		    public String getMyFileContentType() {  
		        return myFileContentType;  
		    }  
		    public void setMyFileContentType(String myFileContentType) {  
		        this.myFileContentType = myFileContentType;  
		    }  
//文件上传end

	
	public String init(){
	}
		
	//demo
	  public String demo() throws Exception{ 
      //第一步 取值  
     //取值  ajax提交的数据  
		  HttpServletRequest request = ServletActionContext.getRequest();
		  String city = request.getParameter("city");
//取值  file  
	
//1.拿到ServletContext
ServletContext servletContext = ServletActionContext.getServletContext();
//2.调用realPath方法，获取根据一个虚拟目录得到的真实目录	
String realPath = servletContext.getRealPath("/WEB-INF/file");
//3.如果这个真实的目录不存在，需要创建
File file = new File(realPath );
if(!file.exists()){
file.mkdirs();
}
myfile.renameTo(new File(file,myfileFileName));
    
 //第二步  发网络请求或发数据库请求 
    
   
 //第三步  正确跳转到哪  错误跳转到哪  一般用forward
    //A跳到新页面
		 request.setAttribute("address_info", info);
		 return "add";
		  //B跳回到本页面(带参数)
		  ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		  ServletActionContext.getResponse().getWriter().write(new Gson().toJson(rltMap));
		  return ActionSupport.NONE;
	  }
			
}

