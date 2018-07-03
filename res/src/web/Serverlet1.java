import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
/**
 * Servlet implementation class 
 */
@WebServlet(urlPatterns = "/Serverlet1", asyncSupported = true)
public class Serverlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String parameter = "";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Serverlet1() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1. start async
		final AsyncContext ctx = request.startAsync();
		parameter = ctx.getRequest().getParameter("parameter");



Gson gson = new Gson();
RequestParam1 bean = gson.fromJson(parameter, RequestParam1.class);
		// 2. set the timeout
		ctx.setTimeout(500);
		// 3. add listener
		ctx.addListener(new AsyncListener() {
			@Override
			public void onTimeout(AsyncEvent arg0) throws IOException {
				System.out.println("onTimeout...");
			}
			@Override
			public void onStartAsync(AsyncEvent arg0) throws IOException {
				System.out.println("onStartAsync...");
			}
			@Override
			public void onError(AsyncEvent arg0) throws IOException {
				System.out.println("onError...");
			}
			@Override
			public void onComplete(AsyncEvent arg0) throws IOException {
				System.out.println("onComplete...");
			}
		});
		// 4. run a thread
		ctx.start(new Runnable() {
			@Override
			public void run() {
				try {
					ctx.getResponse().setContentType("application/json");
//000000 表示成功
Respond respond=new Respond();
respond.returnCode=;
ReturnData returnData=respond.new ReturnData();
respond.returnData=returnData;
Head head=returnData.new Head();
returnData.head=head;
head.ret_errcode=;
head.ret_msg=;
Body body=returnData.new Body();
returnData.body=body;



/**问题及建议表1*/
CacheRespondParam1 cacheRespondParam1=new CacheRespondParam1(); 
/** 上报ID 备注:key*/
cacheRespondParam1.QuestionID=;//int
/** 上报类型 备注: */
cacheRespondParam1.QuestionType=;//String
/**  备注:*/
cacheRespondParam1.=;//String
/** 涉及系统 备注: */
cacheRespondParam1.ApplicationName=;//String
/** 功能模块名称 备注: */
cacheRespondParam1.ModelName=;//String
/** 问题或需求描述 备注: */
cacheRespondParam1.Question=;//String
/** 附件上传路径 备注: */
cacheRespondParam1.UploadfileUrl=;//String
/** 反馈人姓名 备注: */
cacheRespondParam1.FeedbackName=;//String
/** 反馈人联系方式 备注: */
cacheRespondParam1.FeedbackPhone=;//String
/** 反馈人机构名称 备注: */
cacheRespondParam1.FeedbackOrg=;//String
/** 提交日期 备注: */
cacheRespondParam1.FeedbackDateTime=;//String
/** 处理人电话 备注: */
cacheRespondParam1.DealPhone=;//String
/** 处理时间 备注: */
cacheRespondParam1.DealDateTime=;//String
/** 处理进度 备注: */
cacheRespondParam1.DealProgress=;//String
/** 处理意见（反馈意见） 备注: */
cacheRespondParam1.DealMsg=;//String
/** 处理状态 备注: */
cacheRespondParam1.DealState=;//String
/** 最后修改时间 备注: */
cacheRespondParam1.lastModifyTime=;//String
/** 最后修改人 备注: */
cacheRespondParam1.lastModifyTimeName=;//String
/** 最后修改渠道 备注: */
cacheRespondParam1.lastModifyTimeChannel=;//String
					
					PrintWriter writer = ctx.getResponse().getWriter();
					writer.write(new Gson().toJson(respond,Respond.class));
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ctx.complete();
			}
		});
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	

 class Respond{
public String returnCode;
public ReturnData returnData;

 class ReturnData{
public Head head;

 class Head{
public String ret_errcode;
public String ret_msg;
}
public Body body;

 class Body{
}
}
}
}

