package com.compoment.server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.compoment.jsonToJava.creater.JsonToJavaBeanForSimple;
import com.compoment.jsonToJava.creater.RequestRespondForServerlet;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;



public class Serverlet {

	
	
	
	public void  create(String baseJson,InterfaceBean interfaceBean)
	{
		JsonToJavaBeanForSimple jsonToJavaBeanForSimple = new JsonToJavaBeanForSimple(baseJson);
		RequestRespondForServerlet requestRespondForServerlet=new RequestRespondForServerlet();
		
		String m="";
		m+="import java.io.IOException;\n";
		m+="import java.io.PrintWriter;\n";
		m+="import java.util.ArrayList;\n";
		m+="import java.util.List;\n";

		m+="import javax.servlet.AsyncContext;\n";
		m+="import javax.servlet.AsyncEvent;\n";
		m+="import javax.servlet.AsyncListener;\n";
		m+="import javax.servlet.ServletException;\n";
		m+="import javax.servlet.ServletOutputStream;\n";
		m+="import javax.servlet.annotation.WebServlet;\n";
		m+="import javax.servlet.http.HttpServlet;\n";
		m+="import javax.servlet.http.HttpServletRequest;\n";
		m+="import javax.servlet.http.HttpServletResponse;\n";

		m+="import com.google.gson.Gson;\n";

		m+="/**\n";
		m+=" * Servlet implementation class \n";
		m+=" */\n";
		m+="@WebServlet(urlPatterns = \"/Serverlet"+interfaceBean.id+"\", asyncSupported = true)\n";
		m+="public class Serverlet"+interfaceBean.id+" extends HttpServlet {\n";
		m+="	private static final long serialVersionUID = 1L;\n";

		m+="	String parameter = \"\";\n";

		m+="	/**\n";
		m+="	 * @see HttpServlet#HttpServlet()\n";
		m+="	 */\n";
		m+="	public Serverlet"+interfaceBean.id+"() {\n";
		m+="		super();\n";
		m+="		// TODO Auto-generated constructor stub\n";
		m+="	}\n";

		m+="	/**\n";
		m+="	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse\n";
		m+="	 *      response)\n";
		m+="	 */\n";
		m+="	protected void doGet(HttpServletRequest request,\n";
		m+="			HttpServletResponse response) throws ServletException, IOException {\n";
		m+="		// TODO Auto-generated method stub\n";

		m+="		// 1. start async\n";
		m+="		final AsyncContext ctx = request.startAsync();\n";
		m+="		parameter = ctx.getRequest().getParameter(\"parameter\");\n";

		
		m+=requestRespondForServerlet.request(interfaceBean);
		
		m+="		// 2. set the timeout\n";
		m+="		ctx.setTimeout(500);\n";

		m+="		// 3. add listener\n";
		m+="		ctx.addListener(new AsyncListener() {\n";

		m+="			@Override\n";
		m+="			public void onTimeout(AsyncEvent arg0) throws IOException {\n";
		m+="				System.out.println(\"onTimeout...\");\n";
		m+="			}\n";

		m+="			@Override\n";
		m+="			public void onStartAsync(AsyncEvent arg0) throws IOException {\n";
		m+="				System.out.println(\"onStartAsync...\");\n";
		m+="			}\n";

		m+="			@Override\n";
		m+="			public void onError(AsyncEvent arg0) throws IOException {\n";
		m+="				System.out.println(\"onError...\");\n";
		m+="			}\n";

		m+="			@Override\n";
		m+="			public void onComplete(AsyncEvent arg0) throws IOException {\n";
		m+="				System.out.println(\"onComplete...\");\n";
		m+="			}\n";
		m+="		});\n";

		m+="		// 4. run a thread\n";
		m+="		ctx.start(new Runnable() {\n";
		m+="			@Override\n";
		m+="			public void run() {\n";
		m+="				try {\n";

		m+="					ctx.getResponse().setContentType(\"application/json\");\n";

		m+="//000000 表示成功\n";
		m+=jsonToJavaBeanForSimple.toUseJavaBeanClass();
		
		m+=requestRespondForServerlet.respond(interfaceBean);
		

		m+="					\n";
		m+="					PrintWriter writer = ctx.getResponse().getWriter();\n";
		m+="					writer.write(new Gson().toJson(respond,Respond.class));\n";
		m+="					writer.flush();\n";


		m+="				} catch (IOException e) {\n";
		m+="					e.printStackTrace();\n";
		m+="				}\n";
		m+="				ctx.complete();\n";
		m+="			}\n";
		m+="		});\n";
		m+="	}\n";

		m+="	/**\n";
		m+="	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse\n";
		m+="	 *      response)\n";
		m+="	 */\n";
		m+="	protected void doPost(HttpServletRequest request,\n";
		m+="			HttpServletResponse response) throws ServletException, IOException {\n";
		m+="		// TODO Auto-generated method stub\n";
		m+="	}\n";
		m+="	\n";
		
	
		m+=jsonToJavaBeanForSimple.getJaveBeanClass();
	
		
		
		m+="}\n";
		
		
	
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				"Serverlet"+interfaceBean.id, "java", m);

	}
	
	
	
	
}
