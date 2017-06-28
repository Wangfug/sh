<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/customerBalanceChange/${action}" method="post">
		<input type="hidden" name="id" value="${customerBalanceChange.id }" />
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align = "right">变动前余额：</td>
			<td>
				<input name="beforeChange" type="text" value="${customerBalanceChange.beforeChange }" class="easyui-validatebox" required="required"/>
			</td>
			<td align = "right">变动后余额：</td>
			<td>
				<input name="afterChange" type="text" value="${customerBalanceChange.afterChange }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align = "right">用户选择：</td>
			<td>
				<select class="easyui-combobox" name="balanceCustomer"  style="width:140px;">
					<c:forEach var="customer" items="${customersForBalance}">
						<c:if test="${customer.id eq customerBalanceChange.balanceCustomer}"><option value="${customer.id}" selected>${customer.name}</option></c:if>
						<c:if test="${customer.id ne customerBalanceChange.balanceCustomer}"><option value="${customer.id}">${customer.name}</option></c:if>
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
    successTip(data,customerBalanceChange,d);
}
});
});

</script>
</body>
</html>