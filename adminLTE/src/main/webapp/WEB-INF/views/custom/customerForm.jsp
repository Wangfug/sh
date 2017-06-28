<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/customer/${action}" method="post">
		<input type="hidden" name="id" value="${customer.id }" />
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align = "right">姓名：</td>
			<td>
				<input name="name" type="text" value="${customer.name }" class="easyui-validatebox" required="required"/>
			</td>
			<td align = "right">联系电话：</td>
			<td>
				<input name="mobilePhone" type="text" value="${customer.mobile_phone }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align = "right">个人邮箱：</td>
			<td>
				<input name="email" type="text" value="${customer.email }" class="easyui-validatebox" required="required"/>
			</td>
			<td align = "right">用户状态：</td>
			<td>
				<select name="state" id="state">
					<option value="">请选择</option>
					<c:if test="${'1' eq customer.state}"><option value="1" selected>可用</option></c:if>
					<c:if test="${'1' ne customer.state}"><option value="1">可用</option></c:if>
					<c:if test="${'2' eq customer.state}"><option value="1" selected>禁用</option></c:if>
					<c:if test="${'2' ne customer.state}"><option value="1">禁用</option></c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td align = "right">证件类型：</td>
			<td>
				<select  style="width:140px;" id = "credentialtype" name = "credentialtype">
					<c:forEach var="credentialtype" items="${dictTypesForcredentialtype}">
						<c:if test="${credentialtype.code eq customer.credentialtype}"><option value="${credentialtype.code}" selected>${credentialtype.name}</option></c:if>
						<c:if test="${credentialtype.code ne customer.credentialtype}"><option value="${credentialtype.code}">${credentialtype.name}</option></c:if>
					</c:forEach>
				</select>
			</td>
			<td align = "right">证件号：</td>
			<td>
				<input name="credentialcode" type="text" value="${customer.credential_code }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align = "right">驾驶证：</td>
			<td>
				<select class="easyui-combobox" name="drivingLicence"  style="width:140px;">
					<c:forEach var="drivingLicence" items="${drivingLicencesForCustomer}">
						<c:if test="${drivingLicence.id eq customer.driving_licence}"><option value="${drivingLicence.id}" selected>${drivingLicence.file_number}</option></c:if>
						<c:if test="${drivingLicence.id ne customer.driving_licence}"><option value="${drivingLicence.id}">${drivingLicence.file_number}</option></c:if>
					</c:forEach>
				</select>
				<%--<input name="drivingLicence" type="text" value="${customer.drivingLicence }" class="easyui-validatebox" required="required"/>--%>
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
    successTip(data,customer,d);
}
});
});

</script>
</body>
</html>