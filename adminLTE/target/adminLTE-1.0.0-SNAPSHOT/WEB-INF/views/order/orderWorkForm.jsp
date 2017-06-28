<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lte.admin.common.consts.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/orderWork/${action}" method="post">
		<input type = "hidden" name = "id" id = "id" value = "${orderWork.id}">
	<table  class="formTable" cellpadding="20" align="center">
		<tr>
			<td>工单类型：</td>
			<td>
				<select name="orderType" id="orderType">
					<c:if test="${orderWork.orderType eq OrderWayEnum.GET_CAR.getCode()}">
					<option value = "<%= OrderWorkTypeEnum.GET_CAR.getCode()%>" selected><%= OrderWorkTypeEnum.GET_CAR.getName()%></option>
					<option value = "<%= OrderWorkTypeEnum.SEND_CAR.getCode()%>"><%= OrderWorkTypeEnum.SEND_CAR.getName()%></option>
					</c:if>
					<c:if test="${orderWork.orderType ne OrderWayEnum.GET_CAR.getCode()}">
						<option value = "<%= OrderWorkTypeEnum.GET_CAR.getCode()%>"><%= OrderWorkTypeEnum.GET_CAR.getName()%></option>
						<option value = "<%= OrderWorkTypeEnum.SEND_CAR.getCode()%>" selected><%= OrderWorkTypeEnum.SEND_CAR.getName()%></option>
					</c:if>
				</select>
			</td>
			<td>交易方式：</td>
			<td>
				<select name="way" id="way">
					<c:if test="${orderWork.way eq OrderWayEnum.BYSHOP.getCode()}">
						<option value = "<%= OrderWayEnum.BYSHOP.getCode()%>" selected><%= OrderWayEnum.BYSHOP.getName()%></option>
						<option value = "<%= OrderWayEnum.BYSELF.getCode()%>"><%= OrderWayEnum.BYSELF.getName()%></option>
					</c:if>
					<c:if test="${orderWork.way ne OrderWayEnum.BYSHOP.getCode()}">
						<option value = "<%= OrderWayEnum.BYSHOP.getCode()%>"><%= OrderWayEnum.BYSHOP.getName()%></option>
						<option value = "<%= OrderWayEnum.BYSELF.getCode()%>"selected><%= OrderWayEnum.BYSELF.getName()%></option>
					</c:if>
				</select>
			</td>
		</tr>
		<%--<tr>
			<td>交易地点：</td>
			<td>
					<input name="address" type="text" value="${orderWork.address}" class="easyui-validatebox" required="required"/>
			</td>
			<td>交易负责人：</td>
			<td>
				<input name="eno" type="text" value="${orderWork.eno}" class="easyui-validatebox" required="required"/>
			</td>
		</tr>--%>
		<tr>
			<td>工单状态：</td>
			<td>
				<select name="orderState" id="order_state">
					<option value = "1">进行中</option>
					<option value = "2">已关闭</option>
				</select>
			</td>
			<td>交易门店：</td>
			<td>
				<select name="carShop" id="carShop">
					<option value="1">苏州舜昊1店</option>
					<option value="2">南京舜昊2店</option>
					<option value="3">北京舜昊3店</option>
					<option value="4">北京舜昊1店</option>
					<option value="5">苏州舜昊2店</option>
					<option value="7">北京</option>
					<option value="8">北京自由店</option>
				</select>
				<%--<input name="carShop" type="text" value="${orderWork.carShop}" class="easyui-validatebox" required="required"/>--%>
			</td>
		</tr>
		<tr>
			<td>附件：</td>
			<td>
				<select name="attachment" id="attachment">
					<option value = "1">附件一</option>
					<option value = "2">附件二</option>
				</select>
			</td>
			<td>车辆：</td>
			<td>
				<select name="carId" id="carId">
					<option value="1">苏A1111</option>
					<option value="2">苏A1112</option>
					<option value="3">苏A1113</option>
					<option value="4">苏A1114</option>
				</select>
				<%--<input name="carId" type="text" value="${orderWork.carId}" class="easyui-validatebox" required="required"/>--%>
			</td>
		</tr>
		<tr>
			<td>订单号：</td>
			<td>
				<input name="orderNo" type="text" value="${orderWork.orderNo}" class="easyui-validatebox" required="required"/>
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