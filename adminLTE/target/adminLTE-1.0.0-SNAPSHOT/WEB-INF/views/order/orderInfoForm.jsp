<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/orderInfo/${action}" method="post">
		<input type = "hidden" name = "id" id = "id" value = "${order.id}">
	<table  class="formTable">
		<tr>
			<td>订单状态：</td>
			<td>
				<select name="state" id="orderState">
					<c:forEach var="dict" items="${dictsForOrder1}">
						<option value = "${dict.code}"
						<c:if test="${dict.code eq order.state}">selected</c:if>
						>
							${dict.name}
						</option>
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