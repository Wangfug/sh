<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/customerDiscountHold/${action}" method="post">
		<input type="hidden" name="id" value="${customerDiscountHold.id }" />
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align = "right">优惠券选择：</td>
			<td>
				<select class="easyui-combobox" name="discount"  style="width:140px;">
						<c:forEach var="discount" items="${discountsForHoldDiscount}">
							<c:if test="${discount.id eq customerDiscountHold.discount}"><option value="${discount.id}" selected>${discount.discount_info}</option></c:if>
							<c:if test="${discount.id ne customerDiscountHold.discount}"><option value="${discount.id}">${discount.discount_info}</option></c:if>
						</c:forEach>
				</select>
			</td>
			<td align = "right">用户选择：</td>
			<td>
				<select class="easyui-combobox" name="customer"  style="width:140px;">
					<c:forEach var="customer" items="${customersForHoldDiscount}">
						<c:if test="${customer.id eq customerDiscountHold.customer}"><option value="${customer.id}" selected>${customer.name}</option></c:if>
						<c:if test="${customer.id ne customerDiscountHold.customer}"><option value="${customer.id}">${customer.name}</option></c:if>
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
    successTip(data,customerDiscountHold,d);
}
});
});

</script>
</body>
</html>