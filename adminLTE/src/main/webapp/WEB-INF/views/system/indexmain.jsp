<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lte.admin.common.consts.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>汇通达金融服务平台</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<%@ include file="/WEB-INF/views/include/validation.jsp"%>
<c:set var="GSBZ_PT" value="<%= DictConsts.GSBZ_PT %>"/>
<c:set var="GSBZ_FB" value="<%= DictConsts.GSBZ_FB %>"/>
<c:set var="GSBZ_ZB" value="<%= DictConsts.GSBZ_ZB %>"/>
<script type="text/javascript" src="${ctx}/static/plugins/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/static/js/xd_index.js?${currentTime}"></script>

<!--导入首页启动时需要的相应资源文件(首页相应功能的 js 库、css样式以及渲染首页界面的 js 文件)-->
<link href="${ctx}/static/css/style.css?${currentTime}" rel="stylesheet" />
<script src="${ctx}/static/js/common.js?${currentTime}"></script>
<style>body{background: white}</style>
</head>

<body>

          
<div class="main_box" style="height: 800px">

  
  <div class="xx_box">

  
AAAA
<%--
<!--通知公告开始-->
    <div id="tab">
    <c:forEach  var="lx" items="${tzggLxBeans}" varStatus="i">
		<h3 id="two${i.index+1}" onmouseover="setContentTab('two',${i.index+1},4)">${lx.name}</h3>
		<div id="con_two_${i.index+1}">
			<ul>
				<c:set var="tzcount" value="0" />
				<c:forEach var="tzgg" items="${tzggList}">
					<c:if test="${ lx.value eq tzgg.lx }">
						<c:set var="tzcount" value="${tzcount+1}" />
						<li><a class="tab_title tab_title_01"	href="javascript:void(0)"	onclick="viewtz(${tzgg.id},'${tzgg.tzbt}')">${tzgg.tzbt}</a>
							<span><fmt:formatDate value="${tzgg.createTime}" /></span></li>
						<li class="more_title" style="border-bottom: none;"><a href="javascript:void(0)" onclick="addNewTab('通知公告','system/tzgg', '')">查看更多&gt;&gt;</a></li>
					</c:if>
				</c:forEach>
				<c:if test="${ tzcount eq 0 }">
					<li><span>暂无</span></li>
				</c:if>
			</ul>
		</div>
	</c:forEach>
    </div>

    <!--通知公告结束--> 
    --%>
</div>
</div>

<script>


$('.easyui-linkbutton').on('click', function(){    
	$('.easyui-linkbutton').linkbutton({selected:false}); 
    $(this).linkbutton({selected:true});  
});   


var dbTable;
var d;


function viewtz(id,title){
	d=parent.$("#dlg").dialog({   
	    title: '查看通知公告-'+title,    
	    width: 950,    
	    height: 550,    
	    href:'${ctx}/system/tzgg/view/'+id,
	    maximizable:true,
	    modal:true,
	    buttons:[{
			text:'关闭',
			handler:function(){
					d.panel('close');
				}
		}]
	});
}
setContentTab('two',1,4);
</script>
</body>
</html>
