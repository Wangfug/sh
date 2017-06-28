<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/tbaseCompany/${action}" method="post">
		<input type = "hidden" name = "id" id = "id" value = "${tbaseCompany.id}">
	<table  class="formTable" cellpadding="20" align="center">
		<tr>
			<td>公司名：</td>
			<td>
				<input name="companyName" type="text" value="${tbaseCompany.companyName}" class="easyui-validatebox" required="required"/>

			</td>
			<td>公司代码：</td>
			<td>
				<input name="companyCode" type="text" value="${tbaseCompany.companyCode}" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td>公司类型：</td>
			<td>
				<select class="easyui-combobox" name="companyType" id="companyType">
					<c:forEach var="dict" items="${dictsForCompany}">
						<c:if test="${dict.code eq tbaseCompany.companyType}"><option value = "${dict.code}" selected>${dict.name}</option></c:if>
						<c:if test="${dict.code ne tbaseCompany.companyType}"><option value = "${dict.code}">${dict.name}</option></c:if>
					</c:forEach>
				</select>
			</td>
			<td>上级公司：</td>
			<td>
				<select class="easyui-combobox" name="parentComanyCode" id="parentComanyCode">
					<c:forEach var="company" items="${companysForCompany}">
						<c:if test="${company.comCode eq tbaseCompany.parentComanyCode}"><option value = "${company.comCode}" selected>${company.comName}</option></c:if>
						<c:if test="${company.comCode ne tbaseCompany.parentComanyCode}"><option value = "${company.comCode}">${company.comName}</option></c:if>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>所在城市：</td>
			<td>
				<select class="easyui-combobox" name="cityId" id="cityId">
					<c:forEach var="city" items="${citysForCompany}">
						<c:if test="${city.id eq tbaseCompany.cityId}"><option value = "${city.id}" selected>${city.city_name}</option></c:if>
						<c:if test="${city.id ne tbaseCompany.cityId}"><option value = "${city.id}">${city.city_name}</option></c:if>
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
    successTip(data,tbaseCompany,d);
}
});
});

</script>
</body>
</html>