<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/orderState/${action}" method="post">
		<input type = "hidden" name = "id" id = "id" value = "${id}">
	<table  class="formTable">
		<tr>
			<td>订单状态：</td>
			<td>
				<select name="state" id="orderState">
					<c:forEach var="dict" items="${dictsForOrderState}">
						<c:if test="${dict.code eq state}"><option value = "${dict.code}" selected>${dict.name}</option></c:if>
						<c:if test="${dict.code ne state}"><option value = "${dict.code}">${dict.name}</option></c:if>
					</c:forEach>
				</select>
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
    successTip(data,order,d);
}
});
});

</script>
</body>
</html>