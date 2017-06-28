<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/pickupPrice/${action}" method="post">
		<input type = "hidden" name = "id" id = "id" value = "${pickupPrice.id}">
	<table  class="formTable" cellpadding="20" align="center">
		<tr>
			<td>城市名：</td>
			<td>
				<input name="cityName" type="text" value="${pickupPrice.cityName}" class="easyui-validatebox" required="required"/>

			</td>
			<td>三字码：</td>
			<td>
				<input name="cityThreeCode" type="text" value="${pickupPrice.cityThreeCode}" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td>上级城市：</td>
			<td>
				<select class="easyui-combobox" name="parentCity" id="parentCity">
					<option value = "0">省级</option>
					<c:forEach var="city" items="${citysForCreate}">
						<c:if test="${city.id eq pickupPrice.parentCity}"><option value = "${city.id}" selected>${city.city_name}</option></c:if>
						<c:if test="${city.id ne pickupPrice.parentCity}"><option value = "${city.id}">${city.city_name}</option></c:if>
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
    successTip(data,pickupPrice,d);
}
});
});

</script>
</body>
</html>