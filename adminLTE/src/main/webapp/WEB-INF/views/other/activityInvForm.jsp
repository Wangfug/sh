<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/activityInv/${action}" method="post">
		<input type = "hidden" name = "id" id = "id" value = "${activityInv.id}">
		<table  class="formTable">

			<tr>
			<td >活动名称：</td>
			<td>
				<select name="activityId" class="easyui-combobox">
					<c:forEach var="dict" items="${activitys}">
							<c:if test="${dict.id eq activityInv.activityId}"><option value="${dict.id}" selected>${dict.activityName}</option></c:if>
					<c:if test="${dict.id ne activityInv.activityId}"><option value="${dict.id}">${dict.activityName}</option></c:if>
					</c:forEach>
				</select>
			</td>

				<td >活动参与者：</td>
				<td>
					<select name="activityInv" class="easyui-combobox">
						<c:forEach var="dict" items="${customers}">
							<c:if test="${dict.id eq activityInv.activityInv}"><option value="${dict.id}" selected>${dict.name}</option></c:if>
							<c:if test="${dict.id ne activityInv.activityInv}"><option value="${dict.id}">${dict.name}</option></c:if>
						</c:forEach>
					</select>
				</td>
		</tr>

			<tr>
				<td >活动状态：</td>
				<td>
					<input name="state" type="text" value="${activityInv.state}" class="easyui-validatebox" required="required"/>
				</td>
			</tr>
		</table>
</form>
</div>
<script type="text/javascript">
$(function(){
$('#mainform').form({
onSubmit: function(){
    var isValid = $(this).form('validate');
    return isValid;	// 返回false终止表单提交
},
success:function(data){
    successTip(data,activityInv,d);
}
});
});

</script>
</body>
</html>