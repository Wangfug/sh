<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/carOutOrIn/${action}" method="post">
	<table  class="formTable">
		<tr>
			<td  style="width: 75px;">车牌号：</td>
			<td>
			<input type="hidden" name="id" value="${carOutOrIn.id}" />
				<select class="easyui-combobox" name="carId"  style="width:140px;">
					<c:forEach var="car" items="${cars}">
						<c:if test="${car.id eq carOutOrIn.carId}"><option value="${car.id}" selected>${car.car_code}</option></c:if>
						<c:if test="${car.id ne carOutOrIn.carId}"><option value="${car.id}">${car.car_code}</option></c:if>
					</c:forEach>
				</select>
			</td>
			<td  style="width: 85px;padding-left: 95px">调度单号：</td>
			<td><input name="dispatchNo" type="text" value="${carOutOrIn.dispatchNo}" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>调度时间：</td>
			<td><input name="outTime" type="text" value="${carOutOrIn.outTime}" class="easyui-datebox" required="required"/></td>
			<td  style="width: 85px;padding-left: 95px">出/入库门店：</td>
			<td>
				<%--<input name="carShop" type="text" value="${carOutOrIn.carShop}" class="easyui-validatebox" required="required"/>--%>
				<select class="easyui-combobox" name="carShop"  style="width:140px;">
					<c:forEach var="dict" items="${carShops}">
						<c:if test="${dict.id eq carOutOrIn.carShop}"><option value="${dict.id}" selected>${dict.shopName}</option></c:if>
						<c:if test="${dict.id ne carOutOrIn.carShop}"><option value="${dict.id}">${dict.shopName}</option></c:if>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>出库/入库：</td>
			<td>
				<select name="inOrOut" name="carShop">
					<c:if test="${'1'eq carOutOrIn.inOrOut}"><option value="1" selected>入库</option><option value="2">出库</option></c:if>
					<c:if test="${'2'eq carOutOrIn.inOrOut}"><option value="1">入库</option><option value="2" selected>出库</option></c:if>
					<c:if test="${carOutOrIn.inOrOut ne '1' or '2'}"><option value="1">入库</option><option value="2">出库</option></c:if>
				</select>
			</td>
			<td   style="width: 85px;padding-left: 95px">调度批准人：</td>
			<td><input name="approveBy"  class="easyui-validatebox" value="${carOutOrIn.approveBy}"/></td>
		</tr>

		<tr>
			<td>出入原因：</td>
			<td  colspan="3"><textarea style="width: 300px;height: 100px;" name="reason" value="${carOutOrIn.reason}" class="easyui-ueditor">${carOutOrIn.remark}</textarea></td>
		</tr>

		<tr>
			<td>备注：</td>
			<td  colspan="3"><textarea style="width: 300px;height: 100px;" name="remark" value="${carOutOrIn.remark}" class="easyui-ueditor">${carOutOrIn.remark}</textarea></td>
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
    successTip(data,carOutOrIn,d);
}
});
});
</script>
</body>
</html>