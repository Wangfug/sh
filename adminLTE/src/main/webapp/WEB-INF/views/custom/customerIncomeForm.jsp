<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/customerIncome/${action}" method="post">
		<input type="hidden" name="id" value="${customerIncome.id }" />
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align = "right">收入订单  ：</td>
			<td>
				<select class="easyui-combobox" name="incomeOrder"  style="width:140px;">
					<c:forEach var="order" items="${ordersForIncome}">
						<c:if test="${order.id eq customerIncome.incomeOrder}"><option value="${order.id}" selected>${order.order_no}</option></c:if>
						<c:if test="${order.id ne customerIncome.incomeOrder}"><option value="${order.id}">${order.order_no}</option></c:if>
					</c:forEach>
				</select>
				<%--<input name="shopName" type="text" value="${customerIncome.shopName }" class="easyui-validatebox" required="required"/>--%>
			</td>
			<td align = "right">收入车辆：</td>
			<td>
				<select class="easyui-combobox" name="incomeCar"  style="width:140px;">
					<c:forEach var="car" items="${carsForIncome}">
						<c:if test="${car.id eq customerIncome.incomeCar}"><option value="${car.id}" selected>${car.car_code}</option></c:if>
						<c:if test="${car.id ne customerIncome.incomeCar}"><option value="${car.id}">${car.car_code}</option></c:if>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td align = "right">获得收入的用户：</td>
			<td>
				<select class="easyui-combobox" name="incomeCustomer"  style="width:140px;">
					<c:forEach var="customer" items="${customersForIncome}">
						<c:if test="${customer.id eq customerIncome.incomeCustomer}"><option value="${customer.id}" selected>${customer.name}</option></c:if>
						<c:if test="${customer.id ne customerIncome.incomeCustomer}"><option value="${customer.id}">${customer.name}</option></c:if>
					</c:forEach>
				</select>
			</td>
			<td align = "right">输入金额（元）：</td>
			<td>
				<input name="incomeAccount" type="text" value="${customerIncome.incomeAccount }" class="easyui-validatebox" required="required"/>
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
    successTip(data,customerIncome,d);
}
});
});

</script>
</body>
</html>