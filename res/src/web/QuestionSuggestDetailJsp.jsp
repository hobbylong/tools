


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset='utf-8'>
	<meta name="viewport" content="width=device-width,initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta Http-Equiv="Cache-Control" Content="no-cache"/>
	<meta Http-Equiv="Pragma" Content="no-cache"/>
	<meta Http-Equiv="Expires" Content="-1"/>
  <link rel="stylesheet" href="<%=basePath%>css/frozen.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.form.js"></script>
	<script src="<%=basePath%>lib/zepto.min.js"></script>
	<script src="<%=basePath%>js/frozen.js"></script>
<link href="<%=basePath%>js/calendar_control/mobiscroll.css" rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/calendar_control/mobiscroll.js" type="text/javascript"></script>
	
	<script
	src="<%=basePath%>js/calendar_control/mobiscroll_002.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>js/calendar_control/mobiscroll_004.js"
	type="text/javascript"></script>
<link
	href="<%=basePath%>js/calendar_control/mobiscroll_002.css"
	rel="stylesheet" type="text/css">
<script
	src="<%=basePath%>js/calendar_control/mobiscroll_003.js"
	type="text/javascript"></script>
<script
	src="<%=basePath%>js/calendar_control/mobiscroll_005.js"
	type="text/javascript"></script>
<link
	href="<%=basePath%>js/calendar_control/mobiscroll_003.css"
	rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/calendar_control/date.js"></script>
<link
	href="<%=basePath%>js/calendar_control/mobiscroll.css"
	rel="stylesheet" type="text/css">
<script
	src="<%=basePath%>js/calendar_control/mobiscroll.js"
	type="text/javascript"></script>
<script>
//js取request值  var contentWidth = <s:property value="#request.cut_img_content_info.contentWidth"/>;
//html取request值  <input type="hidden"  name="busiNo" id="busiNo" value="<s:property value="#request.busiNo"/>" />


</script>
</head>
<body id="body" class="h-body" style="display:flex;flex-direction: column;">
<div id="emptyOrErrorMsg"></div>
<div onclick="history.back()" style="margin:10px; width: 10px; height: 10px; border-top: 2px solid #dfdfdf; border-right: 2px solid #dfdfdf; transform: rotate(225deg);"></div>
</body>
<script type="text/javascript">
$(document).ready(function(){
     init();
});

function getUrlParam(name) {
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
var r = window.location.search.substr(1).match(reg); //匹配目标参数
if (r != null)
return unescape(r[2]);
return null; //返回参数值
}
</script> 

<script type="text/javascript">
//body宽高等于窗体
 var screenHeight=document.documentElement.clientHeight;
 var screenWidth=document.documentElement.clientWidth; 
var body = $(".h-body");
body.width(screenWidth + "px");
body.height(screenHeight + "px");
 </script> 

<script type="text/javascript">
//进入页面向后台取数据,初始化页面
 function init() {
$.ajax({
url:${pageContext.request.contextPath}/__,
type:'post',
dataType:'json',
async:true,
data:{where:window.where},
timeout:1000,
error:function(){
requestIng=false;
alert("ajax error");
}, 
success:function(rsObj)
{
requestIng=false;
var resultSize=rsObj.resultSize;
window.resultTotal=rsObj.resultTotal;
var resultData=rsObj.resultData;
if(resultSize>0){
for(var i=0;i<resultData.length;i++){ 
var itemHtml='';



itemHtml+='<div onclick="history.back()" style="margin:10px; width: 10px; height: 10px; border-top: 2px solid #dfdfdf; border-right: 2px solid #dfdfdf; transform: rotate(225deg);"></div>'
 $(".h-body").append(itemHtml); 
 }
 }
});
}
</script>
</html>

