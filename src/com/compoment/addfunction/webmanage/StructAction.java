package com.compoment.addfunction.webmanage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.compoment.jsonToJava.creater.InterfaceBean.Group;
import com.compoment.jsonToJava.creater.InterfaceBean;
import com.compoment.jsonToJava.creater.InterfaceBean.Row;
import com.compoment.util.FileUtil;
import com.compoment.util.KeyValue;

public class StructAction {

	public StructAction(List<InterfaceBean> interfaceBeans) {
		if (interfaceBeans == null)
			return;

		for (InterfaceBean interfaceBean : interfaceBeans) {
			
			action(interfaceBean, "Respond");
		}
	}
	public void action(InterfaceBean interfaceBean ,String type)
	{
		
		String m="";
		
		m+="import java.util.ArrayList;\n";
		m+="import java.util.Collection;\n";
		m+="import java.util.HashMap;\n";
		m+="import java.util.List;\n";
		m+="import java.util.Map;\n";
		m+="import java.util.Vector;\n";

		m+="import javax.servlet.http.HttpServletRequest;\n";
		m+="import javax.servlet.http.HttpServletResponse;\n";
		m+="import javax.servlet.http.HttpSession;\n";

		m+="import org.apache.log4j.Logger;\n";
		m+="import org.apache.struts.action.ActionForm;\n";
		m+="import org.apache.struts.action.ActionForward;\n";
		m+="import org.apache.struts.action.ActionMapping;\n";
		m+="import org.apache.struts.util.LabelValueBean;\n";
		m+="import org.apache.struts.actions.DispatchAction;\n";
	
		m+="import com.gdpost.cbp.base.PackUtil;\n";
		m+="import com.gdpost.cbp.base.common.Const;\n";

		m+="import com.gdpost.cbp.bizconstant.SysConstant;\n";
		m+="import com.gdpost.cbp.login.bm.PartnerLoginFacade;\n\n";

	

		m+="public class "+interfaceBean.enName+"Action extends BaseAction {\n";
		m+="	Logger log = Logger.getLogger(this.getClass());\n";
		m+="	public static String msg;\n";
		m+="	public static String err;\n\n\n";

		m+="	public ActionForward init(ActionMapping mapping, ActionForm form,\n";
		m+="			HttpServletRequest request, HttpServletResponse response) {\n";
		m+="		return mapping.findForward(\"queryJsp\");\n";
		m+="	}\n\n\n";

		
		
		
		m+="/**跳到更新页面*/\n";
		m+="	public ActionForward updateJsp(ActionMapping mapping, ActionForm form,\n";
		m+="			HttpServletRequest request, HttpServletResponse response) {\n";



		
		m+=""+interfaceBean.enName+"ActionForm myform = ("+interfaceBean.enName+"ActionForm) form;\n";
		m+="		\n";
		m+="		String st = request.getParameter(\"parm\") == null ? \" \" : request\n";
		m+="				.getParameter(\"parm\").trim();\n";
		m+="		String[] rs = Const.split(st);\n";
		m+="	\n";
		
		m+="		String errmsg = null;\n";
		m+="		Map map = new HashMap();\n";
		m+="		map.put(\"headmsg\", PackUtil.setPacketHead(request));\n";
		
		List<Group> groups = interfaceBean.respondGroups;
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
						m+="		map.put(\""+row.enName+"\", 1);\n";
					} else {
						
						m+="String "+row.enName+" = rs["+columnCount+"].trim();//"+row.cnName+"\n";
						m+="		List "+row.enName+"list = new ArrayList();\n";
						m+="		"+row.enName+"list.add("+row.enName+" == null ? \" \" : "+row.enName+");\n";
						m+="		map.put(\""+row.enName+"\", "+row.enName+"list);\n";
						
						columnCount++;
					}
					i++;
				}
			}

		}
		
	

	
	
		m+="		try {\n";
	
		m+="log.info(\"主交易\" + \"4453329\");\n";
		m+="        PacketMgr packetmgr= PacketMgr.getInstance();\n";
		m+="		//生成发送报文\n";
		m+="		String sendmsg = packetmgr.getPacketData(map, \"MSG_4453329_IN\");\n";
		m+="		log.info(\"发送报文\" + sendmsg);\n";
		m+="		//接收报文\n";
		m+="		String receivemsg = TuxedoMgr.runTuxedoService(\"4453329\", sendmsg);\n";
		m+="		log.info(\"接收报文\"+receivemsg);\n";
		m+="		\n";
		m+="		String checkerror = packetmgr.checkErrorMsg(receivemsg);\n";
		m+="		log.info(checkerror);\n";
		m+="Map recmap=null ;\n";
		m+="		if (checkerror == null || \"\".equals(checkerror)) {\n";
		m+="			 recmap = packetmgr.getUnPacketData(receivemsg, \"MSG_4453329_OUT\");\n";
		
		m+="		} else {\n";
	
		m+="		}\n";
		
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
						m+="	int recodeNum=	Integer.valueOf(recmap.get(\""+row.enName+"\").toString());\n";
						m+="List list=new ArrayList();\n";
						m+="for(int i=0;i<recodeNum;i++){\n";
						m+=""+interfaceBean.enName+"ActionForm actionForm=new "+interfaceBean.enName+"ActionForm();\n";
					} else {
					
						
						m+="	actionForm."+row.enName.toLowerCase()+"=	recmap.get(\""+row.enName+"\").toString();\n";
						
						columnCount++;
					}
					i++;
				}
			}
		}
		
		m+="list.add(actionForm);\n";
	
		m+="}\n";
		
		

		
	
	
		m+="			myform.setRedo(list);\n";
		m+="			\n";

		m+="		} catch (Exception e) {\n";

		m+="			log.info(e.getMessage());\n";
		m+="			String msg = e.getMessage();\n";
		m+="			if (msg == null || \"\".equals(msg)) {\n";
		m+="				errmsg = \"未知错误！\";\n";
		m+="			} else {\n";
		m+="				errmsg = \"服务器内部错误！\";\n";
		m+="			}\n";
		m+="			request.setAttribute(\"errmsg\", errmsg);\n";
		m+="			ActionForward af = mapping.findForward(\"errmsg\");\n";
		m+="			return af;\n";
		m+="		}\n";

		
		
	

		
		m+="		ActionForward af = null;\n";
	
		m+="		try {\n";
		m+="			HttpSession session = request.getSession();\n";
		
		m+="			af = mapping.findForward(\"updateJsp\");\n";
		m+="			return af;\n";

		m+="			\n";
		m+="		} catch (Exception e) {\n";
		m+="			e.printStackTrace();\n";
		m+="			String msg = e.getMessage();\n";
		m+="			if (msg == null || \"\".equals(msg)) {\n";
		m+="				errmsg = \"错误！\";\n";
		m+="			} else {\n";
		m+="				errmsg = \"服务器内部错误！\";\n";
		m+="			}\n";
		m+="			request.setAttribute(\"errmsg\", errmsg);\n";
		m+="			af = mapping.findForward(\"errmsg\");\n";
		m+="			return af;\n";
		m+="		}\n";
		
		m+="	}\n";


		m+="/**更新交易*/\n";
		m+="	public ActionForward update(ActionMapping mapping, ActionForm form,\n";
		m+="			HttpServletRequest request, HttpServletResponse response) {\n";
		m+="		"+interfaceBean.enName+"ActionForm sform = ("+interfaceBean.enName+"ActionForm) form;\n";
		m+="		\n";
		m+="		\n";
		m+="		\n";
		m+="		String st = request.getParameter(\"parm\") == null ? \" \" : request\n";
		m+="				.getParameter(\"parm\").trim();\n";
		m+="		\n";
		m+="		\n";
		m+="		\n";
		m+="		\n";
		m+="		System.out.println(st);\n";
		m+="		String[] rs = st.split(\"|\");\n";
		m+="	\n";
		
		m+="		Map map = new HashMap();\n";
		m+="		map.put(\"headmsg\", PackUtil.setPacketHead(request));\n";
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
						m+="		map.put(\""+row.enName+"\", 1);\n";
					} else {
						
						m+="String "+row.enName+" = rs["+columnCount+"].trim();//"+row.cnName+"\n";
						m+="		ArrayList "+row.enName+"list = new ArrayList();\n";
						m+="		"+row.enName+"list.add("+row.enName+" == null ? \" \" : "+row.enName+");\n";
						m+="		map.put(\""+row.enName+"\", "+row.enName+"list);\n";
						
						columnCount++;
					}
					i++;
				}
			}

		}
		
		

		m+="		String errmsg = null;\n";
		m+="		try {\n";
		m+="log.info(\"主交易\" + \"4453329\");\n";
		m+="        PacketMgr packetmgr= PacketMgr.getInstance();\n";
		m+="		//生成发送报文\n";
		m+="		String sendmsg = packetmgr.getPacketData(map, \"MSG_4453329_IN\");\n";
		m+="		log.info(\"发送报文\" + sendmsg);\n";
		m+="		//接收报文\n";
		m+="		String receivemsg = TuxedoMgr.runTuxedoService(\"4453329\", sendmsg);\n";
		m+="		log.info(\"接收报文\"+receivemsg);\n";
		m+="		\n";
		m+="		String checkerror = packetmgr.checkErrorMsg(receivemsg);\n";
		m+="		log.info(checkerror);\n";
		m+="		if (checkerror == null || \"\".equals(checkerror)) {\n";
		m+="			Map recmap = packetmgr.getUnPacketData(receivemsg, \"MSG_4453329_OUT\");\n";
		
		m+="		} else {\n";
	
		m+="		}\n";
		
		
		m+="			String[] rs1 = Const.split(receivemsg);\n";
		m+="			String err = rs1[0];\n";
		m+="			if (\"000000\".equals(err)) {\n";
		m+="				errmsg = \"更新成功！\";\n";
		m+="				request.setAttribute(\"optret\", errmsg);\n";
		m+="				return this.queryJsp(mapping, form, request, response);\n";
		
		m+="			} else {\n";
		m+="				errmsg = rs1[1];\n";
		m+="				request.setAttribute(\"optret\", errmsg);\n";
		
		m+="				return this.queryJsp(mapping, form, request, response);\n";
		m+="			}\n";
	
		m+="		} catch (Exception e) {\n";
		m+="			log.info(e.getMessage());\n";
		m+="			String msgg = e.getMessage();\n";
		m+="			if (msgg == null || \"\".equals(msgg)) {\n";
		m+="				errmsg = \"更新失败！\";\n";
		m+="			} else {\n";
		m+="				errmsg = \"服务器内部错误！\";\n";
		m+="			}\n";
		m+="			e.printStackTrace();\n";
		m+="			request.setAttribute(\"errmsg\", errmsg);\n";
		m+="			ActionForward af = mapping.findForward(\"msgb\");\n";
		m+="			return af;\n";
		m+="		}\n";
		m+="	}\n\n\n";

		
		
		
		m+="/**删除*/\n";
		m+="	public ActionForward del(ActionMapping mapping, ActionForm form,\n";
		m+="			HttpServletRequest request, HttpServletResponse response) {\n";
		m+="		String errmsg = null;\n";
		m+="		"+interfaceBean.enName+"ActionForm myform = ("+interfaceBean.enName+"ActionForm) form;\n";

		m+="		try {\n";
	
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
						m+="		ArrayList "+row.enName+"list = new ArrayList();//"+row.cnName+"\n";
						columnCount++;
					}
					i++;
				}
			}
		}
		
	
		m+="		\n";
		m+="		for (int i = 0; i < myform.checkbox_row_id.length; i++) {\n";
		m+="			String st = myform.checkbox_row_id[i].trim()==null?\"\":myform.checkbox_row_id[i].trim();\n";
		m+="			String[] rs = Const.split(st);\n";
	
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
					
						m+=" "+row.enName+"list.add(rs["+columnCount+"].trim());//"+row.cnName+"\n";
						columnCount++;
					}
					i++;
				}
			}
		}
		

		
		m+="		}	\n";
		m+="		\n";
		m+="		Map packet = new HashMap();\n";
		m+="		packet.put(\"SNDMSG_HEAD\", PackUtil.setPacketHead(request));\n";
	
		
		m+="		packet.put(\"D44_70_RECORDNUM\",myform.checkbox_row_id.length);\n";
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
					} else {
					
				
						m+="		packet.put(\""+row.enName+"\","+row.enName+"list); \n";
						columnCount++;
					}
					i++;
				}
			}
		}
		
	
	
		m+="		\n";
		m+="		log.info(\"主交易\" + \"\");\n";
		m+="        PacketMgr packetmgr= PacketMgr.getInstance();\n";
		m+="		//生成发送报文\n";
		m+="		String sendmsg = packetmgr.getPacketData(packet, \"MSG_4453329_IN\");\n";
		m+="		log.info(\"发送报文\" + sendmsg);\n";
		m+="		//接收报文\n";
		m+="		String receivemsg = TuxedoMgr.runTuxedoService(\"4453329\", sendmsg);\n";
		m+="		log.info(\"接收报文\"+receivemsg);\n";
		m+="		\n";
		m+="		String checkerror = packetmgr.checkErrorMsg(receivemsg);\n";
		m+="		log.info(checkerror);\n";
		m+="		if (checkerror == null || \"\".equals(checkerror)) {\n";
		m+="			Map recmap = packetmgr.getUnPacketData(receivemsg, \"MSG_4453329_OUT\");\n";
	
		m+="		} else {\n";
	
		m+="		}\n";
		
		
		
		m+="			request.setAttribute(\"optret\", receivemsg);\n";
	
		m+="			return this.queryJsp(mapping, form, request, response);\n";
		m+="		} catch (Exception e) {\n";
		m+="			log.info(e.getMessage());\n";
		m+="			String msgg = e.getMessage();\n";
		m+="			if (msgg == null || \"\".equals(msgg)) {\n";
		m+="				errmsg = \"删除失败！\";\n";
		m+="			} else {\n";
		m+="				errmsg = \"服务器内部错误！\";\n";
		m+="			}\n";
		m+="			// e.printStackTrace();\n";
		m+="			request.setAttribute(\"errmsg\", errmsg);\n";
		m+="			ActionForward af = mapping.findForward(\"msgb\");\n";
		m+="			return af;\n";
		m+="		}\n";
		m+="	}\n\n\n";

		
		
		
		
		m+="/**新增*/\n";
		m+="	public ActionForward add(ActionMapping mapping, ActionForm form,\n";
		m+="			HttpServletRequest request, HttpServletResponse response) {\n";
		m+="		"+interfaceBean.enName+"ActionForm sform = ("+interfaceBean.enName+"ActionForm) form;\n";
		
		m+="		\n";
		m+="		String st = request.getParameter(\"parm\") == null ? \" \" : request\n";
		m+="				.getParameter(\"parm\").trim();\n";
		m+="		\n";
	
		m+="		System.out.println(st);\n";
		m+="		String[] rs = Const.split(st);\n";
	
		m+="		Map map = new HashMap();\n";
		m+="		String errmsg = null;\n";
		m+="		map.put(\"headmsg\", PackUtil.setPacketHead(request));\n";
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
						m+="		map.put(\""+row.enName+"\", 1);\n";
					} else {
						m+="String "+row.enName+" = rs["+columnCount+"].trim();//"+row.cnName+"\n";
						m+="		ArrayList "+row.enName+"list = new ArrayList();\n";
						m+="		"+row.enName+"list.add("+row.enName+" == null ? \" \" : "+row.enName+");\n";
						m+="		map.put(\""+row.enName+"\", "+row.enName+"list);\n";
						columnCount++;
					}
					i++;
				}
			}
		}
		
	
		m+="	\n";
		m+="		try {\n";
		
		
		m+="log.info(\"主交易\" + \"4453329\");\n";
		m+="        PacketMgr packetmgr= PacketMgr.getInstance();\n";
		m+="		//生成发送报文\n";
		m+="		String sendmsg = packetmgr.getPacketData(map, \"MSG_4453329_IN\");\n";
		m+="		log.info(\"发送报文\" + sendmsg);\n";
		m+="		//接收报文\n";
		m+="		String receivemsg = TuxedoMgr.runTuxedoService(\"4453329\", sendmsg);\n";
		m+="		log.info(\"接收报文\"+receivemsg);\n";
		m+="		\n";
		m+="		String checkerror = packetmgr.checkErrorMsg(receivemsg);\n";
		m+="		log.info(checkerror);\n";
		m+="		if (checkerror == null || \"\".equals(checkerror)) {\n";
		m+="			Map recmap = packetmgr.getUnPacketData(receivemsg, \"MSG_4453329_OUT\");\n";
		
		m+="		} else {\n";
	
		m+="		}\n";
		
		m+="			String[] rs1 = Const.split(receivemsg);\n";
		m+="			String err = rs1[0];\n";
		m+="			if (\"000000\".equals(err)) {\n";
		m+="				errmsg = \"新增成功！\";\n";
		m+="				request.setAttribute(\"optret\", errmsg);\n";
		m+="				return this.queryJsp(mapping, form, request, response);\n";
	
		m+="			} else {\n";
		m+="				errmsg = rs1[1];\n";
		m+="				request.setAttribute(\"optret\", errmsg);\n";
	
		m+="				return this.queryJsp(mapping, form, request, response);\n";
		m+="			}\n";
		m+="		} catch (Exception e) {\n";
		m+="			log.info(e.getMessage());\n";
		m+="			String msgg = e.getMessage();\n";
		m+="			if (msgg == null || \"\".equals(msgg)) {\n";
		m+="				errmsg = \"新增失败！\";\n";
		m+="			} else {\n";
		m+="				errmsg = \"服务器内部错误！\";\n";
		m+="			}\n";
		m+="			e.printStackTrace();\n";
		m+="			request.setAttribute(\"errmsg\", errmsg);\n";
		m+="			ActionForward af = mapping.findForward(\"msgb\");\n";
		m+="			return af;\n";
		m+="		}\n";
		m+="	}\n\n";

		
		

		
		
		m+="/**跳转到新增addJsp页*/\n";
		m+="	public ActionForward addJsp(ActionMapping mapping, ActionForm form,\n";
		m+="			HttpServletRequest request, HttpServletResponse response) {\n";
		m+="		\n";
		m+="		String errmsg = null;\n";
		m+="		ActionForward af = null;\n";
		m+="		Map map = new HashMap();\n";
		m+="		try {\n";
		m+="			HttpSession session = request.getSession();\n";
		m+="			String brchNO = session.getAttribute(\"brch_no\").toString();\n";
		m+="			if (brchNO != null) {\n";
		m+="					af = mapping.findForward(\"addJsp\");\n";
		m+="					return af;\n";
		m+="			} else {\n";
		m+="				String msg = \"没有获取用户信息，请先登陆！！！\";\n";
		m+="				String nextUrl = \"/login.jsp\";\n";
		m+="				return this.errorPage(msg, nextUrl, mapping, request);\n";
		m+="			}\n";
		m+="		} catch (Exception e) {\n";
		m+="			e.printStackTrace();\n";
		m+="			String msg = e.getMessage();\n";
		m+="			if (msg == null || \"\".equals(msg)) {\n";
		m+="				errmsg = \"错误！\";\n";
		m+="			} else {\n";
		m+="				errmsg = \"服务器内部错误！\";\n";
		m+="			}\n";
		m+="			request.setAttribute(\"errmsg\", errmsg);\n";
		m+="			af = mapping.findForward(\"errmsg\");\n";
		m+="			return af;\n";
		m+="		}\n";
		m+="	}\n";



		

		m+="	public ActionForward errorPage(String msg, String nextUrl,\n";
		m+="			ActionMapping mapping, HttpServletRequest request) {\n";
		m+="		HttpSession session = request.getSession();\n";
		m+="		session.setAttribute(\"successMsg\", msg);\n";
		m+="		System.out.println(\"msg \" + msg);\n";
		m+="		session.setAttribute(\"nextPage\", nextUrl);\n";
		m+="		return mapping.findForward(\"error\");\n";
		m+="	}\n";

		
		
		
		
		
		m+="/**跳转到QueryJsp页*/\n";
		m+="	public ActionForward queryJsp(ActionMapping mapping, ActionForm form,\n";
		m+="			HttpServletRequest request, HttpServletResponse response) {\n";
		m+="		String errmsg = null;\n";
		m+="		ActionForward af = null;\n";
		m+="		try {\n";
		m+="			HttpSession session = request.getSession();\n";
		m+="			String brchNO = session.getAttribute(\"brch_no\").toString();\n";
	
		m+="			if (brchNO != null) {\n";
		m+="					af = mapping.findForward(\"queryJsp\");\n";
		m+="					return af;\n";
		m+="			} else {\n";
		m+="				String msg = \"没有获取用户信息，请先登陆！！！\";\n";
		m+="				String nextUrl = \"/login.jsp\";\n";
		m+="				return this.errorPage(msg, nextUrl, mapping, request);\n";
		m+="			}\n";
		m+="		} catch (Exception e) {\n";
		m+="			e.printStackTrace();\n";
		m+="			String msg = e.getMessage();\n";
		m+="			if (msg == null || \"\".equals(msg)) {\n";
		m+="				errmsg = \"错误！\";\n";
		m+="			} else {\n";
		m+="				errmsg = \"服务器内部错误！\";\n";
		m+="			}\n";
		m+="			request.setAttribute(\"errmsg\", errmsg);\n";
		m+="			af = mapping.findForward(\"errmsg\");\n";
		m+="			return af;\n";
		m+="		}\n";
		m+="	}\n\n";
		
		
		
		m+="/**查询*/\n";
		m+="	public ActionForward query(ActionMapping mapping, ActionForm form,\n";
		m+="			HttpServletRequest request, HttpServletResponse response) {\n";
		
		m+="		"+interfaceBean.enName+"ActionForm sform = ("+interfaceBean.enName+"ActionForm) form;\n";
		
		m+="		Map map = new HashMap();\n";
		m+="		map.put(\"headmsg\", PackUtil.setPacketHead(request));\n";
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
				
					} else {
						m+="		String "+row.enName+" = sform."+row.enName.toLowerCase()+".trim() == null ? \"\"\n";
						m+="				: sform."+row.enName.toLowerCase()+".trim();//"+row.cnName+"\n";
						
						m+="		map.put(\""+row.enName+"\", "+row.enName+");\n";
						columnCount++;
					}
					i++;
				}
			}
		}
		
		
	
		
	
	
	
		m+="		String errmsg = null;\n";
		m+="		try {\n";
		m+="log.info(\"主交易\" + \"4453329\");\n";
		m+="        PacketMgr packetmgr= PacketMgr.getInstance();\n";
		m+="		//生成发送报文\n";
		m+="		String sendmsg = packetmgr.getPacketData(map, \"MSG_4453329_IN\");\n";
		m+="		log.info(\"发送报文\" + sendmsg);\n";
		m+="		//接收报文\n";
		m+="		String receivemsg = TuxedoMgr.runTuxedoService(\"4453329\", sendmsg);\n";
		m+="		log.info(\"接收报文\"+receivemsg);\n";
		m+="		\n";
		m+="		String checkerror = packetmgr.checkErrorMsg(receivemsg);\n";
		m+="		log.info(checkerror);\n";
		m+="Map recmap=null ;\n";
		m+="		if (checkerror == null || \"\".equals(checkerror)) {\n";
		m+="			 recmap = packetmgr.getUnPacketData(receivemsg, \"MSG_4453329_OUT\");\n";
		
		m+="		} else {\n";
	
		m+="		}\n";
		
		
		
		for (Group group : groups) {
			String groupname = group.name;
			if (!groupname.equals("CommonGroup")) {
				int i = 0;
				int columnCount=0;
				for (Row row : group.rows) {
					if (i == 0) {// 循环域开始
						m+="	int recodeNum=	Integer.valueOf(recmap.get(\""+row.enName+"\").toString());\n";
						m+="List list=new ArrayList();\n";
						m+="for(int i=0;i<recodeNum;i++){\n";
						m+=""+interfaceBean.enName+"ActionForm actionForm=new "+interfaceBean.enName+"ActionForm();\n";
					} else {
					
						
						m+="	actionForm."+row.enName.toLowerCase()+"=	((String[])recmap.get(\""+row.enName+"\"))["+columnCount+"].toString();//"+row.cnName+"\n";
						
						columnCount++;
					}
					i++;
				}
			}
		}
		
		m+="list.add(actionForm);\n";
	
		m+="}\n";
		
		
		m+="			sform.setRedo(list);\n";
	
		m+="		} catch (Exception e) {\n";

		m+="			log.info(e.getMessage());\n";
		m+="			String msg = e.getMessage();\n";
		m+="			if (msg == null || \"\".equals(msg)) {\n";
		m+="				errmsg = \"未知错误！\";\n";
		m+="			} else {\n";
		m+="				errmsg = \"服务器内部错误！\";\n";
		m+="			}\n";
		m+="			request.setAttribute(\"errmsg\", errmsg);\n";
		m+="			ActionForward af = mapping.findForward(\"errmsg\");\n";
		m+="			return af;\n";
		m+="		}\n";

		m+="		return this.queryJsp(mapping, form, request, response);\n";
		m+="	}\n";
		
		
		
		m+="}\n";
	
		FileUtil.makeFile(KeyValue.readCache("projectPath"), "src/webManager", interfaceBean.enName+"Action", "java", m);
		System.out.println(m);
	}
	
	
}





