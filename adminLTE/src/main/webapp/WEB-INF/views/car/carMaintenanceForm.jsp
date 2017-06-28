<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/carMaintenance/${action}" method="post">
		<input type="hidden" name="id" value="${carMaintenance.id }" />
	<table  class="formTable">
		<tr>
			<td>车辆选择：</td>
			<td>
				<select class="easyui-combobox" name="carId"  style="width:140px;">
					<c:forEach var="car" items="${cars}">
						<c:if test="${car.id eq carMaintenance.carId}"><option value="${car.id}" selected>${car.carCode}</option></c:if>
						<c:if test="${car.id ne carMaintenance.carId}"><option value="${car.id}">${car.carCode}</option></c:if>
					</c:forEach>
				</select>
			</td>
			<td style="padding-left: 40px;">维保经手人：</td>
			<td><input name="maintenanceBy" type="text" value="${carMaintenance.maintenanceBy }" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>维保时间：</td>
			<td><input name="maintenanceTime" type="text" value="${carMaintenance.maintenanceTime }" class="easyui-datebox" required="required"/></td>
			<td style="padding-left: 40px;">维保订单号：</td>
			<td>
				<select class="easyui-combobox" name="maintenanceOrder"  style="width:140px;">
					<c:forEach var="order" items="${orders}">
						<c:if test="${order.id eq carMaintenance.maintenanceOrder}"><option value="${order.id}" selected>${order.order_no}</option></c:if>
						<c:if test="${order.id ne carMaintenance.maintenanceOrder}"><option value="${order.id}">${order.order_no}</option></c:if>
					</c:forEach>
				</select>
				<%--<input name="maintenanceOrder" type="text" value="${carMaintenance.maintenanceOrder }" class="easyui-validatebox" required="required"/></td>--%>
		</tr>
		<tr>
			<td >维保费用：</td>
			<td colspan = "3"><input name="maintenanceMoney" style = "width:80%" type="text" value="${carMaintenance.maintenanceMoney }" class="easyui-validatebox" required="required"/>元
			</td>
		</tr>
		<tr>
			<td>维保内容：</td>
			<td colspan = "3">
				<textarea name="maintenanceContent" id="maintenanceContent" style = "width:100%;" >${carMaintenance.maintenanceContent}</textarea>
				<%--<input name="maintenanceContent" type="text" style = "width:100%" value="${carMaintenance.maintenanceContent }" class="easyui-validatebox" required="required"/>--%>
			</td>
		</tr>
		<tr>
			<td>备注：</td>
			<td colspan = "3">
				<textarea name="remark" id="remark" style = "width:100%;" >${carMaintenance.remark}</textarea>
				<%--<input name="remark" style = "width:100%" class="easyui-validatebox" value="${carMaintenance.remark }"  required="required"/>--%>
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
    successTip(data,carMaintenance,d);
}
});
});

</script>
</body>
</html>