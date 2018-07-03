


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
	<script src="<%=basePath%>lib/zepto.min.js"></script>
	<script src="<%=basePath%>js/frozen.js"></script>
<script>
//js取request值  var contentWidth = <s:property value="#request.cut_img_content_info.contentWidth"/>;
//html取request值  <input type="hidden"  name="busiNo" id="busiNo" value="<s:property value="#request.busiNo"/>" />


</script>
</head>
<body id="body" class="h-body" style="display:flex;flex-direction: column;">
<div id="emptyOrErrorMsg"></div>

<div id="bg1527833785190DivLayout_Horizon" class=""  style="background-color:背景颜色;display:flex;flex-direction:row;justify-content: space-between;align-items:center;" >
<span id="backTextView" name ="backTextView" style="font-size:16;color:#FFFFFF;">back</span>
<span id="titleTextView" name ="titleTextView" style="font-size:16;color:#169BD5;">需求及故障</span>
<button  src= "/images/query.png" onclick="null"  style="">查询</button>
  </div>


<div id="bg1527834549916DivLayout_Vertical" class="" style="background-color:#FFFFFF;display:flex;flex-direction:column;" >

<div id="bg1527834581775DivLayout_Horizon" class=""  style="background-color:背景颜色;display:flex;flex-direction:row;" >
<input style=" border: 0; line-height: 8px; height: 8px;  font-size: 16px;"  type="text"  id="dealNameEditText" name="dealNameEditText" placeholder="处理人">  </div>


<div id="bg1527834928541DivLayout_Horizon" class=""  style="background-color:背景颜色;display:flex;flex-direction:row;justify-content: space-between;align-items:center;" >
<div  style="display:flex;">
<div>2018</div>
<div class="ui-select" style="margin-left: 2px;margin-right:2px;">
<select id="dateStartSelecter">
<option>2014</option>
<option selected>2015</option>
<option>2016</option>
</select>
</div>
</div>
<span id="zhiTextView" name ="zhiTextView" style="font-size:16;color:#636363;">至</span>
<div  style="display:flex;">
<div>2019</div>
<div class="ui-select" style="margin-left: 2px;margin-right:2px;">
<select id="dateEndSelecter">
<option>2014</option>
<option selected>2015</option>
<option>2016</option>
</select>
</div>
</div>
  </div>


<div id="bg1527835360085DivLayout_Horizon" class=""  style="background-color:背景颜色;display:flex;flex-direction:row;justify-content:center;align-items:center;" >
<button  src= "/images/.png" onclick="null"  style="">提交</button>
  </div>

  </div>


<ul id="bg1527835500261ListLayout" class="h-list ui-border-tb" style="flex:1;padding:10px;overflow-y:scroll">

 <li id="bg1527835538831List_ItemLayout_Vertical" class="ui-border-t" style="display:flex;flex-direction:column;width: 100%; " onclick="">

<div id="bg1527836071843DivLayout_Horizon" class=""  style="background-color:背景颜色;display:flex;flex-direction:row;justify-content:flex-start;align-items:center;" >
<span id="modelNameTextView" name ="modelNameTextView" style="font-size:16;color:#636363;">模块名称</span>
  </div>


<div id="bg1527836697256DivLayout_Horizon" class=""  style="background-color:#FFFFFF;display:flex;flex-direction:row;justify-content:flex-start;align-items:center;" >
<span id="applicationNameTextView" name ="applicationNameTextView" style="font-size:16;color:#636363;">系统名称</span>
<span id="lineTextView" name ="lineTextView" style="font-size:16;color:#636363;">|</span>
<span id="questionTypeTextView" name ="questionTypeTextView" style="font-size:16;color:#636363;">需求及建议</span>
<span id="feedBackDateTimeTextView" name ="feedBackDateTimeTextView" style="font-size:16;color:#636363;">2018</span>
  </div>


<div id="bg1527837049436DivLayout_Horizon" class=""  style="background-color:背景颜色;display:flex;flex-direction:row;" >
<span id="questionTextView" name ="questionTextView" style="font-size:16;color:#636363;">sfds</span>
  </div>

  </li>

  </ul>

</body>
<script type="text/javascript">
$(document).ready(function(){
     init();
});
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
//select选择
$("#dateStartSelecter").change(function () {  
var s = $(this).children('option:selected').val();  
window.where.set("dateStartSelecter",s);
 }); 
</script>

<script type="text/javascript">
//select选择
$("#dateEndSelecter").change(function () {  
var s = $(this).children('option:selected').val();  
window.where.set("dateEndSelecter",s);
 }); 
</script>

<script type="text/javascript">
// 分页
window.resultTotal=0;
window.pageSize=10;
window.currentPage=0;
window.requestIng=false;
 $(".h-list").scroll(function(){
 var list = $(".h-list");
var listScrollTop = list.scrollTop();//滚动条下滚多少==内容隐藏部分多少
var listScrollHeight = list[0].scrollHeight;//内容总高度
var listClientHeight = list[0].clientHeight;//可见区域高度
var listOffsetHeight = list[0].offsetHeight;//可见区域高度(包含边框高度)
if(listScrollTop + listClientHeight == listScrollHeight){
if(requestIng==false)
{
 alert("");
getListData(false);
}
}
});
 function getListData(isInitQuery) {
requestIng=true;
if(isInitQuery)
{
	window.currentPage=0;
 }
$.ajax({
url:url,
type:'post',
dataType:'json',
async:true,
data:{pageNo:currentPage,pageSize:pageSize},
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
for(var i=0;i<resultData.size();i++){ 
var itemHtml='';




 itemHtml+='<li id="bg1527835538831List_ItemLayout_Vertical" class="ui-border-t" style="display:flex;flex-direction:column;width: 100%; ">'

itemHtml+='<div id="bg1527836071843DivLayout_Horizon" class=""  style="background-color:背景颜色;display:flex;flex-direction:row;justify-content:flex-start;align-items:center;" >'
itemHtml+='<span id="modelNameTextView" name ="modelNameTextView" style="font-size:16;color:#636363;">模块名称</span>'
  itemHtml+='</div>'


itemHtml+='<div id="bg1527836697256DivLayout_Horizon" class=""  style="background-color:#FFFFFF;display:flex;flex-direction:row;justify-content:flex-start;align-items:center;" >'
itemHtml+='<span id="applicationNameTextView" name ="applicationNameTextView" style="font-size:16;color:#636363;">系统名称</span>'
itemHtml+='<span id="lineTextView" name ="lineTextView" style="font-size:16;color:#636363;">|</span>'
itemHtml+='<span id="questionTypeTextView" name ="questionTypeTextView" style="font-size:16;color:#636363;">需求及建议</span>'
itemHtml+='<span id="feedBackDateTimeTextView" name ="feedBackDateTimeTextView" style="font-size:16;color:#636363;">2018</span>'
  itemHtml+='</div>'


itemHtml+='<div id="bg1527837049436DivLayout_Horizon" class=""  style="background-color:背景颜色;display:flex;flex-direction:row;" >'
itemHtml+='<span id="questionTextView" name ="questionTextView" style="font-size:16;color:#636363;">sfds</span>'
  itemHtml+='</div>'

  itemHtml+='</li>'

 $(".h-list").append(itemHtml); 
 }
 }
 currentPage++;
 }
});
}
</script>
</html>

