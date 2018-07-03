package com.compoment.addfunction.web.structs2.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.compoment.db.tabledocinterfacedoc.TableBean;
import com.compoment.db.tabledocinterfacedoc.TableColumnBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;
import com.compoment.util.StringUtil;

public class Struct2Controller {

	public void controller(List<TableBean> tables,String interfaceName ,String interfaceCnName) {

		String servicename = "";
		String serviceCnName = "";
		String resultType = "";
	
		
		String queryCondition2 = "";
		String mainTableName = "";
		for (TableBean table : tables) {
			servicename += table.tableEnName + "_";
			serviceCnName += table.tableCnName + "_";
			if ( tables.size() > 1) {
			
				resultType = interfaceName + "Bean";
				mainTableName = interfaceName;
				queryCondition2="paraMap";
			}else
			{
				
				resultType = table.tableEnName + "Bean";
				mainTableName = table.tableEnName;
				queryCondition2="reqMap";
			}
		}

		if (servicename.lastIndexOf("_") != -1) {
			servicename = servicename
					.substring(0, servicename.lastIndexOf("_"));
		}

		if (interfaceName == null || "".equals(interfaceName)) {
			interfaceName = servicename;
		}

	

		String m = "";
		
		m+="import java.io.File;\n";
		m+="import java.io.IOException;\n";
		m+="import java.util.HashMap;\n";
		m+="import java.util.List;\n";
		m+="import java.util.Map;\n";
		m+="import java.util.ArrayList;\n";
		m+="import javax.annotation.Resource;\n";
		m+="import javax.servlet.ServletContext;\n";
		m+="import javax.servlet.http.HttpServletRequest;\n";

		m+="import net.sf.json.JSONObject;\n";

		m+="import org.apache.commons.io.FileUtils;\n";
		m+="import org.apache.commons.lang.StringUtils;\n";
		m+="import org.apache.struts2.ServletActionContext;\n";
		m+="import org.apache.struts2.convention.annotation.Action;\n";
		m+="import org.apache.struts2.convention.annotation.Namespace;\n";
		m+="import org.apache.struts2.convention.annotation.ParentPackage;\n";
		m+="import org.apache.struts2.convention.annotation.Result;\n";
	

		m+="import com.tools.CommonFunction;\n";
		
		
		
		m+="import com.tools.PaginationUtil;\n";
		m+="import com.tools.StrutsParamUtils;\n";
		m+="import com.tools.hibernate.ObjectDao;\n";
		m+="//"+interfaceCnName+"\n";
		m+="//@SuppressWarnings(\"unchecked\")\n";
		m+="//@Namespace(value = \"/"+interfaceName.toLowerCase()+"\")\n";
		m+="@Action(value = \""+interfaceName+"Action\" ,results = { \n";
		m+="		@Result(name = \""+interfaceName.toLowerCase()+"\", location = \"/WEB-INF/"+interfaceName.toLowerCase()+"/"+interfaceName.toLowerCase()+".jsp\"),\n";
		m+="		@Result(name = \""+interfaceName.toLowerCase()+"Setting\", location = \"/WEB-INF/"+interfaceName.toLowerCase()+"/"+interfaceName.toLowerCase()+"Setting.jsp\"),\n";
		m+="		@Result(name = \""+interfaceName.toLowerCase()+"Add\", location = \"/WEB-INF/"+interfaceName.toLowerCase()+"/"+interfaceName.toLowerCase()+"Add.jsp\"),\n";
		m+="		\n";
		m+="	})\n";
		
		

		m += "\n/**" + interfaceCnName + "*/\n";
		m += "public class "+interfaceName+"Action  {\n";
		
		m+="	\n";
		m+="	@Resource\n";
		m+="	private ObjectDao objectDao;\n";
		m+="	\n";
		m+="    private "+interfaceName+"Bean entity;\n";
	
	
		String fileCount="1";
		String filebean="";
		filebean+="    private File file"+fileCount+";//对应的就是表单中文件上传的那个输入域的名称，Struts2框架会封装成File类型的\n";
		filebean+="    private String file"+fileCount+"FileName;//   上传输入域FileName  文件名\n";
		filebean+="    private String file"+fileCount+"ContentType;// 上传文件的MIME类型\n";
		
		
		filebean+="	public File getFile"+fileCount+"() {\n";
		filebean+="		return file"+fileCount+";\n";
		filebean+="	}\n";
		filebean+="	public void setFile"+fileCount+"(File file"+fileCount+") {\n";
		filebean+="		this.file"+fileCount+" = file"+fileCount+";\n";
		filebean+="	}\n";
		filebean+="	public String getFile"+fileCount+"FileName() {\n";
		filebean+="		return file"+fileCount+"FileName;\n";
		filebean+="	}\n";
		filebean+="	public void setFile"+fileCount+"FileName(String file"+fileCount+"FileName) {\n";
		filebean+="		this.file"+fileCount+"FileName = file"+fileCount+"FileName;\n";
		filebean+="	}\n";
		filebean+="	public String getFile"+fileCount+"ContentType() {\n";
		filebean+="		return file"+fileCount+"ContentType;\n";
		filebean+="	}\n";
		filebean+="	public void setFile"+fileCount+"ContentType(String file"+fileCount+"ContentType) {\n";
		filebean+="		this.file"+fileCount+"ContentType = file"+fileCount+"ContentType;\n";
		filebean+="	}\n";
		
		m+=filebean;
	
		
		m+="	public ObjectDao getObjectDao() {\n";
		m+="		return objectDao;\n";
		m+="	}\n";
		m+="	public void setObjectDao(ObjectDao objectDao) {\n";
		m+="		this.objectDao = objectDao;\n";
		m+="	}\n";
		m+="	\n";
		

	
		
		
		m+="	public "+interfaceName+"Bean getEntity() {\n";
		m+="		return entity;\n";
		m+="	}\n";
		m+="	public void setEntity("+interfaceName+"Bean  "+interfaceName.toLowerCase()+") {\n";
		m+="		this.entity = "+interfaceName.toLowerCase()+";\n";
		m+="	}\n";
		
		

		m += "    public void doPost()\n";
		m += "            {\n";
		m+="		HttpServletRequest request = StrutsParamUtils.getRequest();\n";
		m+="		HttpServletResponse response = StrutsParamUtils.getResponse();\n";
		m += "        //获取对应的请求参数\n";
		m += "        String method = request.getParameter(\"method\");\n";
		m += "        //根据请求参数去调用对应的方法。\n";
	
		m += "        if (\"query\".equals(method)) {\n";
		m += "        	query(request, response);\n";
		m += "        } \n";
		m += "        if (\"insert\".equals(method)) {\n";
		m += "        	insert(request, response);\n";
		m += "        } \n";
		m += "        if (\"update\".equals(method)) {\n";
		m += "        	update(request, response);\n";
		m += "        } \n";
		m += "        if (\"delete\".equals(method)) {\n";
		m += "        	delete(request, response);\n";
		m += "        } \n";
		m += "    }\n";

		
	m += "    private void demo(HttpServletRequest request, HttpServletResponse response) {\n";
		
		m += "     \n //第一步 取值  \n";
		m += "        //取值  ajax提交的数据  \n";
		m+="          request.setCharacterEncoding(\"UTF-8\");\n";
		m += "        String method = request.getParameter(\"method\"); //参数用html里name=\"\"的值\n";
		m += "        \n";
		m += "        \n";
		m += "      //文件上传及其它取值  form表单提交的数据  method=\"post\" enctype=\"multipart/form-data\"\n";
		m += "        if(ServletFileUpload.isMultipartContent(request)) \n";
		m += "        {\n";
		 m+="try {\n";
		m += "            FileItemFactory factory = new DiskFileItemFactory();\n";
		m += "            ServletFileUpload upload = new ServletFileUpload(factory);\n";
		m += "            List<FileItem> items = upload.parseRequest(request);\n";
		m += "            for(FileItem i: items)\n";
		m += "            {\n";
		m += "                //i.getFieldName();  　　//参数名\n";
		m += "                //i.getString();   　　//参数值（返回字符串），如果是上传文件，则为文件内容\n";
		m += "        　　　　 //i.get();         　　//参数值（返回字节数组），如果是上传文件，则为文件内容\n";
		m+="                  //i.getInputStream();//上传文件内容\n";
		m += "        　　　　 //i.getSize();　　　　　//参数值的字节大小\n";
		m += "        　　　　 //i.getName();   　 　 //上传文件的文件名\n";
		m += "        　　　　 //i.getContentType();  //上传文件的内容类型\n ";
		m += "if(!i.isFormField()&&i.getSize()>0)\n";
		m += "  {//文件\n";
		m+="ServletContext servletContext = request.getSession().getServletContext();\n";
		m+="//2.调用realPath方法，获取根据一个虚拟目录得到的真实目录	\n";
		m+="String realPath = servletContext.getRealPath(\"/WEB-INF/file\");\n";
		m+="//3.如果这个真实的目录不存在，需要创建\n";
		m+="File file = new File(realPath );\n";
		m+="if(!file.exists()){\n";
		m+="file.mkdirs();\n";
		m+="}\n";

		m+=" i.write(new File(realPath + \"/\" + i.getName()));\n";
		m+="}else\n";
		m+="{\n";
		m+="formFields.put(i.getFieldName(), i.getString());\n";
		m+="}\n";
		m+="}\n";
		m+="}catch(Exception e)\n";
	    m+="{\n";
		m+="e.printStackTrace();\n";
		m+="}\n";
		m+="}\n";
		
		m += "        \n";
		
		
		m += "      \n //第二步  发网络请求或发数据库请求 \n";
		m += "        \n";
		
		
		m += "       \n //第三步  正确跳转到哪  错误跳转到哪  一般用forward\n";
		m += "        //A跳到新页面\n";
		m += "        // 1.\n";
		m += "        try {\n";
		m += "			response.sendRedirect(\"/a.jsp\");//servlet?name=tom 通过get方法传递数据到下个页面(本域名下页面或跨域页面) 跳转后浏览器地址栏变化。\n";
		m += "		} catch (IOException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		}\n";
		m += "        \n";
		m += "         //2.\n";
		m += "         request.setAttribute(\"strRequest\", \"\"); \n";
		m += "        RequestDispatcher dispatcher = request.getRequestDispatcher(\"/a.jsp\");//本域   跳转后浏览器地址栏不会变化。\n";
		m += "        try {\n";
		m += "			dispatcher .forward(request, response);\n";
		m += "		} catch (ServletException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		} catch (IOException e) {\n";
		m += "			// TODO Auto-generated catch block\n";
		m += "			e.printStackTrace();\n";
		m += "		}\n";
		m += "        \n";
		m += "        \n";
		m += "        //B跳回到本页面(带参数)\n";
		m += "      response.setCharacterEncoding(\"utf-8\");\n";
		m += "		response.setContentType(\"application/json\");\n";
		m += "		PrintWriter out = response.getWriter();\n";
		m += "		\n";
		m += "		JSONArray jsonArray = JSONArray.fromObject(rows);\n";
		m += "		\n";
		m += "		out.write(\"{\"returnCode\":\"00\",\"info\":\"成功。\",\"returnData\":\" + jsonArray.toString()+ \"}\");\n";
		m += "        out.flush();\n";
		m += "        out.close();\n";
		m += "        \n";
		m += "        \n";
		m += "        \n";
		m += "    }\n";

		m += " \n";
		m += "}\n";
		
		m += "private " + interfaceName + "Service "
				+ StringUtil.firstCharToLower(interfaceName) + "Service=new "+interfaceName+"Service();\n";

		
	
		m += "	public String query(HttpServletRequest request, HttpServletResponse response) {\n";

	
		m+="		String pageNo = request.getParameter(\"pageNo\");\n";
		
		m+="		if (StringUtils.isBlank(pageNo)) {//判断某字符串是否为空或长度为0或由空白符(whitespace) 构成\n";
		m+="			pageNo = \"1\";\n";
		m+="			request.setAttribute(\"pageNo\", pageNo);\n";
		m+="		}\n";
		
		m+="		String pageSize = request.getParameter(\"pageSize\");\n";
		m+="		if (StringUtils.isBlank(pageSize)) {\n";
		m+="			pageSize = \"10\";\n";
		m+="			request.setAttribute(\"pageSize\", pageSize);\n";
		m+="		}\n";
		
		m+="Map paraMap=new HashMap();\n";
		m+="paraMap.put(\"currIndex\", (Integer.valueOf(pageNo)-1)*Integer.valueOf(pageSize));\n";
		m+="paraMap.put(\"pageSize\", pageSize);\n";
		
		int i = 0;
		String n="";
		List<TableColumnBean> queryConditionColumns=getQueryConditionColumns(tables);
		for (TableColumnBean column : queryConditionColumns) {

			if (column.type.toLowerCase().contains("int")) {
				m += "		int " + column.columnEnName
						+ " = request.getParameter(\"" + column.columnEnName
						+ "\") == null ? 0 :Integer.valueOf( request.getParameter(\""
						+ column.columnEnName + "\").toString());\n";
				
			}else
			{
				m += "		String " + column.columnEnName
						+ " = request.getParameter(\"" + column.columnEnName
						+ "\") == null ? null : request.getParameter(\""
						+ column.columnEnName + "\").toString();\n";
			}
			
			
			
		
				m +="paraMap.put(\""+ column.columnEnName + "\","+column.columnEnName+");\n";
			

			i++;
		}
		
		m += "List<"+mainTableName + "Bean> " + mainTableName.toLowerCase()
				+ "Beans=null;\n";
		
		
		m += "try {\n";
		m += mainTableName.toLowerCase() + "Beans="
				+ StringUtil.firstCharToLower(interfaceName) + "Service.get("
				+ queryCondition2 + ",false);\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";

m+="int total=0;\n";
		m += "try {\n";
		m +="total="
				+ StringUtil.firstCharToLower(interfaceName) + "Service.getCount("
				+ queryCondition2 + ");\n";
		m += "} catch (Exception e) {\n";
		m += "	e.printStackTrace();\n";
		m += "}\n";
		
		
		
		
	m += "  try {\n";
		m += "      response.setCharacterEncoding(\"utf-8\");\n";
		m += "		response.setContentType(\"application/json\");\n";
		m += "		PrintWriter out = response.getWriter();\n";
		m += "		\n";
		m += "		JSONArray jsonArray = JSONArray.fromObject("+mainTableName.toLowerCase()+"Beans);\n";
		
		m += "		JSONObject jsonObject = new JSONObject();   \n" ;
		m+=		"   jsonObject.put(\"returnCode\",\"00\");   \n";
		m+=		"   jsonObject.put(\"info\",\"成功\");   \n";
		m+=		"   jsonObject.put(\"resultSize\","+mainTableName.toLowerCase()+"Beans.size());   \n";
		m+=		"   jsonObject.put(\"resultTotal\",total);   \n";
		m+=		"   jsonObject.put(\"resultData\",jsonArray);   \n";
		
		m += "		  out.write(jsonObject.toString());\n";
		m += "        out.flush();\n";
		m += "        out.close();\n";
	m += "  } catch (IOException e) {\n";
	m += "  e.printStackTrace();\n";
	m += "  }\n";
	
		

//		m += "  StrutsParamUtils.getResponse().setCharacterEncoding(\"UTF-8\");\n";
//		m += "  try {\n";
//	m += "		JSONArray jsonArray = JSONArray.fromObject("+mainTableName.toLowerCase()+"Beans);\n";
//		
//		m += "		JSONObject jsonObject = new JSONObject();   \n" ;
//		m+=		"   jsonObject.put(\"returnCode\",\"00\");   \n";
//		m+=		"   jsonObject.put(\"info\",\"成功\");   \n";
//		m+=		"   jsonObject.put(\"resultSize\","+mainTableName.toLowerCase()+"Beans.size());   \n";
//		m+=		"   jsonObject.put(\"resultTotal\",total);   \n";
//		m+=		"   jsonObject.put(\"resultData\",jsonArray);   \n";
//		m += "  	StrutsParamUtils.getResponse().getWriter().write(jsonObject.toString());\n";
//		m += "  } catch (IOException e) {\n";
//			
//		m += "  e.printStackTrace();\n";
//		m += "  }\n";
		
		
		m += "   return ActionSupport.NONE;\n";
		
		
	
		
		
		m += "	}\n";

		for (TableBean table : tables) {

			if (table.isMainTable && tables.size() > 1) {

			} else if (tables.size() == 1) {

			
				m += "	public void insert(HttpServletRequest request, HttpServletResponse response) {\n";

			

				 n="";
				for (TableColumnBean column : table.columns) {
					

					if (column.type.toLowerCase().contains("int")) {
						m += "		int " + column.columnEnName
								+ " = request.getParameter(\"" + column.columnEnName
								+ "\") == null ? 0 :Integer.valueOf( request.getParameter(\""
								+ column.columnEnName + "\").toString());\n";
						
					}else
					{
						m += "		String " + column.columnEnName
								+ " = request.getParameter(\"" + column.columnEnName
								+ "\") == null ? null : request.getParameter(\""
								+ column.columnEnName + "\").toString();\n";
					}
						n+=mainTableName.toLowerCase()+ "Bean."+column.columnEnName+"="+column.columnEnName+";\n";
					
				}

				m += mainTableName + "Bean " + mainTableName.toLowerCase()
						+ "Bean=new "+mainTableName+"Bean();\n";
				m+=n;
				
				m+="boolean isOk=true;\n";
				m += "try {\n";
				m += StringUtil.firstCharToLower(interfaceName)
						+ "Service.insert(" + mainTableName.toLowerCase()
						+ "Bean);\n";
				m += "} catch (Exception e) {\n";
				m+="isOk=false;\n";
				m += "	e.printStackTrace();\n";
				m += "}\n";

	
				
				
				m += "  StrutsParamUtils.getResponse().setCharacterEncoding(\"UTF-8\");\n";
				m += "  try {\n";
				m += "		JSONObject jsonObject = new JSONObject();   \n" ;
				m+=		"   jsonObject.put(\"returnCode\",\"00\");   \n";
				m+=		"   jsonObject.put(\"info\",\"成功\");   \n";
				m += "  	StrutsParamUtils.getResponse().getWriter().write(jsonObject.toString());\n";
				m += "  } catch (IOException e) {\n";
					
				m += "  e.printStackTrace();\n";
				m += "  }\n";
				m += "   return \"index\";\n";
				m += "	}\n";

			
				m += "	public void update(HttpServletRequest request, HttpServletResponse response) {\n";

		

				 n="";
				for (TableColumnBean column : table.columns) {
				
					if (column.type.toLowerCase().contains("int")) {
						m += "		int " + column.columnEnName
								+ " = request.getParameter(\"" + column.columnEnName
								+ "\") == null ? 0 :Integer.valueOf( request.getParameter(\""
								+ column.columnEnName + "\").toString());\n";
						
					}else
					{
						m += "		String " + column.columnEnName
								+ " = request.getParameter(\"" + column.columnEnName
								+ "\") == null ? null : request.getParameter(\""
								+ column.columnEnName + "\").toString();\n";
					}
						n+=mainTableName.toLowerCase()+ "Bean."+column.columnEnName+"="+column.columnEnName+";\n";
					
				}

				m += mainTableName + "Bean " + mainTableName.toLowerCase()
						+ "Bean=new "+mainTableName+"Bean();\n";
				m+=n;
				
				m += "try {\n";
				m += StringUtil.firstCharToLower(interfaceName)
						+ "Service.update(" + mainTableName.toLowerCase()
						+ "Bean);\n";
				m += "} catch (Exception e) {\n";
				m += "	e.printStackTrace();\n";
				m += "}\n";

				m += "  StrutsParamUtils.getResponse().setCharacterEncoding(\"UTF-8\");\n";
				m += "  try {\n";
				m += "		JSONObject jsonObject = new JSONObject();   \n" ;
				m+=		"   jsonObject.put(\"returnCode\",\"00\");   \n";
				m+=		"   jsonObject.put(\"info\",\"成功\");   \n";
		
				
				m += "  	StrutsParamUtils.getResponse().getWriter().write(jsonObject.toString());\n";
				m += "  } catch (IOException e) {\n";
					
				m += "  e.printStackTrace();\n";
				m += "  }\n";
				m += "   return \"index\";\n";
				
				
				m += "	}\n";

				
				
				
				
			
				m += "	public void delete(HttpServletRequest request, HttpServletResponse response) {\n";

			
				
				 n="";
				for (TableColumnBean column : table.columns) {
					if (column.key != null
							&& column.key.toLowerCase().contains("key")) {

						if (column.type.toLowerCase().contains("int")) {
							m += "		int " + column.columnEnName
									+ " = request.getParameter(\"" + column.columnEnName
									+ "\") == null ? 0 :Integer.valueOf( request.getParameter(\""
									+ column.columnEnName + "\").toString());\n";
							
						}else
						{
							m += "		String " + column.columnEnName
									+ " = request.getParameter(\"" + column.columnEnName
									+ "\") == null ? null : request.getParameter(\""
									+ column.columnEnName + "\").toString();\n";
						}
						n+=mainTableName.toLowerCase()+ "Bean."+column.columnEnName+"="+column.columnEnName+";\n";
					}
				}

				m += mainTableName + "Bean " + mainTableName.toLowerCase()
						+ "Bean=new "+mainTableName+"Bean();\n";
				m+=n;
				m += "try {\n";
				m += StringUtil.firstCharToLower(interfaceName)
						+ "Service.delete(" + mainTableName.toLowerCase()
						+ "Bean);\n";
				m += "} catch (Exception e) {\n";
				m += "	e.printStackTrace();\n";
				m += "}\n";

				
				
				m += "  StrutsParamUtils.getResponse().setCharacterEncoding(\"UTF-8\");\n";
				m += "  try {\n";
			
				m += "		JSONObject jsonObject = new JSONObject();   \n" ;
				m+=		"   jsonObject.put(\"returnCode\",\"00\");   \n";
				m+=		"   jsonObject.put(\"info\",\"成功\");   \n";
		
				
	
				m += "  	StrutsParamUtils.getResponse().getWriter().write(jsonObject.toString());\n";
				m += "  } catch (IOException e) {\n";
					
				m += "  e.printStackTrace();\n";
				m += "  }\n";
				m += "   return \"index\";\n";
				
				
				m += "	}\n";

			}
		}

		m += "}\n";

		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/web",
				interfaceName + "Action", "java", m);
	}
	
	public List<TableColumnBean> getQueryConditionColumns(List<TableBean> tables)
	{
	
		List<TableColumnBean> queryConditionColumns=new ArrayList();
	
		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("right".equals(column.rightClickSelected)) {
					String tablename=StringUtil
							.tableName(column.belongWhichTable.tableEnName);
					String shortTableName=tablename.substring(tablename.lastIndexOf("_")+1);

					queryConditionColumns.add(column);
				}

			}
		}
		return queryConditionColumns;
	}
	
	public List<TableColumnBean> getResultColumns(List<TableBean> tables)
	{
	
		
		List<TableColumnBean> resultColumns=new ArrayList();
		for (TableBean table : tables) {

			for (TableColumnBean column : table.columns) {

				if ("left".equals(column.leftClickSelected)) {
					String tablename=StringUtil
							.tableName(column.belongWhichTable.tableEnName);
					String shortTableName=tablename.substring(tablename.lastIndexOf("_")+1);
					
					resultColumns.add(column);

				}

				
			}
		}
		return resultColumns;
	}
	
}
