package com.compoment.ui.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.cut.CompomentBean;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class WebSpringMvc {

	String className;

	String m = "";

	public WebSpringMvc(String pageName, List<CompomentBean> oldBeans) {

		className = firstCharToUpperAndJavaName(pageName);

		

		m += "import java.util.HashMap;\n";
		m += "import java.util.Iterator;\n";
		m += "import java.util.LinkedHashMap;\n";
		m += "import java.util.List;\n";
		m += "import java.util.Map;\n";
		m += "import java.util.Set;\n";
		m += "import javax.annotation.Resource;\n";
		m += "import javax.servlet.http.HttpServletRequest;\n";
		m += "import javax.servlet.http.HttpSession;\n";
		m += "import org.apache.commons.lang.StringUtils;\n";
		m += "import com.google.gson.Gson;\n";
		
		
		m+="@Controller\n";
		m += "public class " + className + "Controller  {\n";
		
		m+="@RequestMapping(\"/addUser2\")\n";
		m+=" public void demo(@RequestParam MultipartFile myfile,HttpServletRequest request,HttpServletResponse response) {\n";
		
		m += "     \n //第一步 取值  \n";
		m += "        //取值  ajax提交的数据  \n";
		m+="request.setCharacterEncoding(\"UTF-8\");\n";
		m+="String username=request.getParameter(\"username\");\n";
		
		m+="ServletContext servletContext = request.getSession().getServletContext();\n";
		m+="//2.调用realPath方法，获取根据一个虚拟目录得到的真实目录	\n";
		m+="String realPath = servletContext.getRealPath(\"/WEB-INF/file\");\n";
		m+="//3.如果这个真实的目录不存在，需要创建\n";
		m+="File file = new File(realPath );\n";
		m+="if(!file.exists()){\n";
		m+="file.mkdirs();\n";
		m+="}\n";
		m+="myfile.renameTo(new File(file,myfileFileName));\n";
		
		
		m += "      \n //第二步  发网络请求或发数据库请求 \n";
		m += "        \n";
		
		       
		m += "       \n //第三步  正确跳转到哪  错误跳转到哪  一般用forward\n";
		m += "        //A跳到新页面\n";
	    m+="respon.setAttribute(\"message\",\"\");\n";
		m+="respon.getRequestDispatcher(\"index.jsp\").forward(request,response);\n";
		
		m += "        //B跳回到本页面(带参数)\n";
		m += "      response.setCharacterEncoding(\"utf-8\");\n";
		m += "		response.setContentType(\"application/json\");\n";
		m += "		PrintWriter out = response.getWriter();\n";
		m += "		\n";
		m += "		JSONArray jsonArray = JSONArray.fromObject(rows);\n";
		m += "		\n";
		m += "		out.write(\"{\"status\":\"true\",\"info\":\"导入成功。\",\"rows\":\" + jsonArray.toString()+ \"}\");\n";
		m += "        out.flush();\n";
		m += "        out.close();\n";
		     
		m += "   }\n";
		
		m += "	}\n\n\n";
		
		
		
		m += "	springmvc.xml\n";
		m += "	<!-- 文件上传 -->\n";
		m += "	<bean id=\"multipartResolver\" class=\"org.springframework.web.multipart.commons.CommonsMultipartResolver\">\n";
		m += "	<!-- 设置上传文件的最大尺寸为5MB -->\n";
		m += "	<property name=\"maxUploadSize\">\n";
		m += "	 <value>5242880</value>\n";
		m += "	</property>\n";
		m += "	</bean>\n";
		
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web", className + "Action", "java", m);
	}

	

	

	public boolean isCommonType(String type) {
		if (type.equals("String") || type.equals("int") || type.equals("long") || type.equals("float")) {
			return true;
		} else {
			return false;
		}
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

	public void makeFile(String fileName, String s) {
		try {
			String doc = KeyValue.readCache("docPath");
			int p = doc.lastIndexOf("/");
			if (p == -1) {
				p = doc.lastIndexOf("\\");
			}

			File tofile = new File(doc.substring(0, p) + "/java/" + fileName + ".java");
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
	}

	public void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}
}
