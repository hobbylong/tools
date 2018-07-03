import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import com.tools.CommonFunction;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//问题及建议表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/tbquestionsugguest")
@Action(value = "TbQuestionSugguestAction" ,results = { 
		@Result(name = "tbquestionsugguest", location = "/WEB-INF/tbquestionsugguest/tbquestionsugguest.jsp"),
		@Result(name = "tbquestionsugguestSetting", location = "/WEB-INF/tbquestionsugguest/tbquestionsugguestSetting.jsp"),
		@Result(name = "tbquestionsugguestAdd", location = "/WEB-INF/tbquestionsugguest/tbquestionsugguestAdd.jsp"),
		
	})

/**问题及建议表*/
public class TbQuestionSugguestAction  {
	
	@Resource
	private ObjectDao objectDao;
	
    private TbQuestionSugguestEntity entity;
    private File file1;//对应的就是表单中文件上传的那个输入域的名称，Struts2框架会封装成File类型的
    private String file1FileName;//   上传输入域FileName  文件名
    private String file1ContentType;// 上传文件的MIME类型
	public File getFile1() {
		return file1;
	}
	public void setFile1(File file1) {
		this.file1 = file1;
	}
	public String getFile1FileName() {
		return file1FileName;
	}
	public void setFile1FileName(String file1FileName) {
		this.file1FileName = file1FileName;
	}
	public String getFile1ContentType() {
		return file1ContentType;
	}
	public void setFile1ContentType(String file1ContentType) {
		this.file1ContentType = file1ContentType;
	}
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public TbQuestionSugguestEntity getEntity() {
		return entity;
	}
	public void setEntity(TbQuestionSugguestEntity  tbquestionsugguest) {
		this.entity = tbquestionsugguest;
	}
    public void doPost()
            {
		HttpServletRequest request = StrutsParamUtils.getRequest();
		HttpServletResponse response = StrutsParamUtils.getResponse();
        //获取对应的请求参数
        String method = request.getParameter("method");
        //根据请求参数去调用对应的方法。
        if ("query".equals(method)) {
        	query(request, response);
        } 
        if ("insert".equals(method)) {
        	insert(request, response);
        } 
        if ("update".equals(method)) {
        	update(request, response);
        } 
        if ("delete".equals(method)) {
        	delete(request, response);
        } 
    }
    private void demo(HttpServletRequest request, HttpServletResponse response) {
     
 //第一步 取值  
        //取值  ajax提交的数据  
          request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method"); //参数用html里name=""的值
        
        
      //文件上传及其它取值  form表单提交的数据  method="post" enctype="multipart/form-data"
        if(ServletFileUpload.isMultipartContent(request)) 
        {
try {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(request);
            for(FileItem i: items)
            {
                //i.getFieldName();  　　//参数名
                //i.getString();   　　//参数值（返回字符串），如果是上传文件，则为文件内容
        　　　　 //i.get();         　　//参数值（返回字节数组），如果是上传文件，则为文件内容
                  //i.getInputStream();//上传文件内容
        　　　　 //i.getSize();　　　　　//参数值的字节大小
        　　　　 //i.getName();   　 　 //上传文件的文件名
        　　　　 //i.getContentType();  //上传文件的内容类型
 if(!i.isFormField()&&i.getSize()>0)
  {//文件
ServletContext servletContext = request.getSession().getServletContext();
//2.调用realPath方法，获取根据一个虚拟目录得到的真实目录	
String realPath = servletContext.getRealPath("/WEB-INF/file");
//3.如果这个真实的目录不存在，需要创建
File file = new File(realPath );
if(!file.exists()){
file.mkdirs();
}
 i.write(new File(realPath + "/" + i.getName()));
}else
{
formFields.put(i.getFieldName(), i.getString());
}
}
}catch(Exception e)
{
e.printStackTrace();
}
}
        
      
 //第二步  发网络请求或发数据库请求 
        
       
 //第三步  正确跳转到哪  错误跳转到哪  一般用forward
        //A跳到新页面
        // 1.
        try {
			response.sendRedirect("/a.jsp");//servlet?name=tom 通过get方法传递数据到下个页面(本域名下页面或跨域页面) 跳转后浏览器地址栏变化。
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
         //2.
         request.setAttribute("strRequest", ""); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/a.jsp");//本域   跳转后浏览器地址栏不会变化。
        try {
			dispatcher .forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        //B跳回到本页面(带参数)
      response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		JSONArray jsonArray = JSONArray.fromObject(rows);
		
		out.write("{"returnCode":"00","info":"成功。","returnData":" + jsonArray.toString()+ "}");
        out.flush();
        out.close();
        
        
        
    }
 
}
private TbQuestionSugguestService tbQuestionSugguestService=new TbQuestionSugguestService();
	public void query(HttpServletRequest request, HttpServletResponse response) {
		String pageNo = request.getParameter("pageNo");
		if (StringUtils.isBlank(pageNo)) {//判断某字符串是否为空或长度为0或由空白符(whitespace) 构成
			pageNo = "1";
			request.setAttribute("pageNo", pageNo);
		}
		String pageSize = request.getParameter("pageSize");
		if (StringUtils.isBlank(pageSize)) {
			pageSize = "10";
			request.setAttribute("pageSize", pageSize);
		}
Map paraMap=new HashMap();
paraMap.put("currIndex", (pageNo-1)*pageSize);
paraMap.put("pageSize", pageSize);
List<TbQuestionSugguestBean> tbquestionsugguestBeans=null;
try {
tbquestionsugguestBeans=tbQuestionSugguestService.get(reqMap);
} catch (Exception e) {
	e.printStackTrace();
}
      response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		JSONArray jsonArray = JSONArray.fromObject(tbquestionsugguestBeans);
		
		  out.write("{"returnCode":"00","info":"成功。","returnData":" + jsonArray.toString()+ "}");
        out.flush();
        out.close();
	}
	public void insert(HttpServletRequest request, HttpServletResponse response) {
		int QuestionID = request.getParameter("QuestionID") == null ? 0 :Integer.valueOf( request.getParameter("QuestionID").toString());
		String QuestionType = request.getParameter("QuestionType") == null ? null : request.getParameter("QuestionType").toString();
		String  = request.getParameter("") == null ? null : request.getParameter("").toString();
		String ApplicationName = request.getParameter("ApplicationName") == null ? null : request.getParameter("ApplicationName").toString();
		String ModelName = request.getParameter("ModelName") == null ? null : request.getParameter("ModelName").toString();
		String Question = request.getParameter("Question") == null ? null : request.getParameter("Question").toString();
		String UploadfileUrl = request.getParameter("UploadfileUrl") == null ? null : request.getParameter("UploadfileUrl").toString();
		String FeedbackName = request.getParameter("FeedbackName") == null ? null : request.getParameter("FeedbackName").toString();
		String FeedbackPhone = request.getParameter("FeedbackPhone") == null ? null : request.getParameter("FeedbackPhone").toString();
		String FeedbackOrg = request.getParameter("FeedbackOrg") == null ? null : request.getParameter("FeedbackOrg").toString();
		String FeedbackDateTime = request.getParameter("FeedbackDateTime") == null ? null : request.getParameter("FeedbackDateTime").toString();
		String DealPhone = request.getParameter("DealPhone") == null ? null : request.getParameter("DealPhone").toString();
		String DealDateTime = request.getParameter("DealDateTime") == null ? null : request.getParameter("DealDateTime").toString();
		String DealProgress = request.getParameter("DealProgress") == null ? null : request.getParameter("DealProgress").toString();
		String DealMsg = request.getParameter("DealMsg") == null ? null : request.getParameter("DealMsg").toString();
		String DealState = request.getParameter("DealState") == null ? null : request.getParameter("DealState").toString();
		String lastModifyTime = request.getParameter("lastModifyTime") == null ? null : request.getParameter("lastModifyTime").toString();
		String lastModifyTimeName = request.getParameter("lastModifyTimeName") == null ? null : request.getParameter("lastModifyTimeName").toString();
		String lastModifyTimeChannel = request.getParameter("lastModifyTimeChannel") == null ? null : request.getParameter("lastModifyTimeChannel").toString();
TbQuestionSugguestBean tbquestionsugguestBean=new TbQuestionSugguestBean();
tbquestionsugguestBean.QuestionID=QuestionID;
tbquestionsugguestBean.QuestionType=QuestionType;
tbquestionsugguestBean.=;
tbquestionsugguestBean.ApplicationName=ApplicationName;
tbquestionsugguestBean.ModelName=ModelName;
tbquestionsugguestBean.Question=Question;
tbquestionsugguestBean.UploadfileUrl=UploadfileUrl;
tbquestionsugguestBean.FeedbackName=FeedbackName;
tbquestionsugguestBean.FeedbackPhone=FeedbackPhone;
tbquestionsugguestBean.FeedbackOrg=FeedbackOrg;
tbquestionsugguestBean.FeedbackDateTime=FeedbackDateTime;
tbquestionsugguestBean.DealPhone=DealPhone;
tbquestionsugguestBean.DealDateTime=DealDateTime;
tbquestionsugguestBean.DealProgress=DealProgress;
tbquestionsugguestBean.DealMsg=DealMsg;
tbquestionsugguestBean.DealState=DealState;
tbquestionsugguestBean.lastModifyTime=lastModifyTime;
tbquestionsugguestBean.lastModifyTimeName=lastModifyTimeName;
tbquestionsugguestBean.lastModifyTimeChannel=lastModifyTimeChannel;
boolean isOk=true;
try {
tbQuestionSugguestService.insert(tbquestionsugguestBean);
} catch (Exception e) {
isOk=false;
	e.printStackTrace();
}
      response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		  out.write("{"returnCode":"00","info":"新增成功。","returnData":""}");
        out.flush();
        out.close();
	}
	public void update(HttpServletRequest request, HttpServletResponse response) {
		int QuestionID = request.getParameter("QuestionID") == null ? 0 :Integer.valueOf( request.getParameter("QuestionID").toString());
		String QuestionType = request.getParameter("QuestionType") == null ? null : request.getParameter("QuestionType").toString();
		String  = request.getParameter("") == null ? null : request.getParameter("").toString();
		String ApplicationName = request.getParameter("ApplicationName") == null ? null : request.getParameter("ApplicationName").toString();
		String ModelName = request.getParameter("ModelName") == null ? null : request.getParameter("ModelName").toString();
		String Question = request.getParameter("Question") == null ? null : request.getParameter("Question").toString();
		String UploadfileUrl = request.getParameter("UploadfileUrl") == null ? null : request.getParameter("UploadfileUrl").toString();
		String FeedbackName = request.getParameter("FeedbackName") == null ? null : request.getParameter("FeedbackName").toString();
		String FeedbackPhone = request.getParameter("FeedbackPhone") == null ? null : request.getParameter("FeedbackPhone").toString();
		String FeedbackOrg = request.getParameter("FeedbackOrg") == null ? null : request.getParameter("FeedbackOrg").toString();
		String FeedbackDateTime = request.getParameter("FeedbackDateTime") == null ? null : request.getParameter("FeedbackDateTime").toString();
		String DealPhone = request.getParameter("DealPhone") == null ? null : request.getParameter("DealPhone").toString();
		String DealDateTime = request.getParameter("DealDateTime") == null ? null : request.getParameter("DealDateTime").toString();
		String DealProgress = request.getParameter("DealProgress") == null ? null : request.getParameter("DealProgress").toString();
		String DealMsg = request.getParameter("DealMsg") == null ? null : request.getParameter("DealMsg").toString();
		String DealState = request.getParameter("DealState") == null ? null : request.getParameter("DealState").toString();
		String lastModifyTime = request.getParameter("lastModifyTime") == null ? null : request.getParameter("lastModifyTime").toString();
		String lastModifyTimeName = request.getParameter("lastModifyTimeName") == null ? null : request.getParameter("lastModifyTimeName").toString();
		String lastModifyTimeChannel = request.getParameter("lastModifyTimeChannel") == null ? null : request.getParameter("lastModifyTimeChannel").toString();
TbQuestionSugguestBean tbquestionsugguestBean=new TbQuestionSugguestBean();
tbquestionsugguestBean.QuestionID=QuestionID;
tbquestionsugguestBean.QuestionType=QuestionType;
tbquestionsugguestBean.=;
tbquestionsugguestBean.ApplicationName=ApplicationName;
tbquestionsugguestBean.ModelName=ModelName;
tbquestionsugguestBean.Question=Question;
tbquestionsugguestBean.UploadfileUrl=UploadfileUrl;
tbquestionsugguestBean.FeedbackName=FeedbackName;
tbquestionsugguestBean.FeedbackPhone=FeedbackPhone;
tbquestionsugguestBean.FeedbackOrg=FeedbackOrg;
tbquestionsugguestBean.FeedbackDateTime=FeedbackDateTime;
tbquestionsugguestBean.DealPhone=DealPhone;
tbquestionsugguestBean.DealDateTime=DealDateTime;
tbquestionsugguestBean.DealProgress=DealProgress;
tbquestionsugguestBean.DealMsg=DealMsg;
tbquestionsugguestBean.DealState=DealState;
tbquestionsugguestBean.lastModifyTime=lastModifyTime;
tbquestionsugguestBean.lastModifyTimeName=lastModifyTimeName;
tbquestionsugguestBean.lastModifyTimeChannel=lastModifyTimeChannel;
try {
tbQuestionSugguestService.update(tbquestionsugguestBean);
} catch (Exception e) {
	e.printStackTrace();
}
      response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		  out.write("{"returnCode":"00","info":"更新成功。","returnData":""}");
        out.flush();
        out.close();
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		int QuestionID = request.getParameter("QuestionID") == null ? 0 :Integer.valueOf( request.getParameter("QuestionID").toString());
TbQuestionSugguestBean tbquestionsugguestBean=new TbQuestionSugguestBean();
tbquestionsugguestBean.QuestionID=QuestionID;
try {
tbQuestionSugguestService.delete(tbquestionsugguestBean);
} catch (Exception e) {
	e.printStackTrace();
}
      response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		  out.write("{"returnCode":"00","info":"删除成功。","returnData":""}");
        out.flush();
        out.close();
	}
}

