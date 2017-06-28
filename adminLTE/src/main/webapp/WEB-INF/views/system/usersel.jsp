<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body style="TEXT-ALIGN: center;MARGIN-RIGHT: auto; MARGIN-LEFT: 10px;">
<br>
 <div class="easyui-panel" title="选择部门岗位" style="width:400px;MARGIN-RIGHT: auto; MARGIN-LEFT: auto;">
<form id="loginForm" action="${ctx}/a/seluser" method="post">
        <c:forEach items="${userlist}"  var="user">
        <br>
			<%--<div><input type="radio" name="gwxx" value="${user.pkPsndoc}"/>${user.gsname} ${user.bmname} ${user.jobname} ${user.psnname}</div>--%>
            <div><input type="radio" name="gwxx" value="${user.jobCode}"/>${user.memberName} ${user.deptName} ${user.jobName} ${user.companyName}</div>
        </c:forEach>
		<br>
        　　　　<input type="button" value="选择" id="selectuser"/>
        <br><br>
</form>
  </div>

<script type="text/javascript">

$(function(){   
	$("#selectuser").click(function(){
		var item = $(":radio:checked"); 
		var len=item.length; 
		if(len>0){ 
		  $("#loginForm").submit();
		}else{
			alert("请选择一个岗位!")
		}
	})

});



</script>
</body>
</html>