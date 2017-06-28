<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/carInsurance/${action}" method="post">
	<table  class="formTable">
		<tr>
			<td  style="width: 75px;">车牌号：</td>
			<td>
			<input type="hidden" name="id" value="${carInsurance.id}" />
			<%--<input name="carId" type="text" value="${carInsurance.car_code}" class="easyui-validatebox" required="required" readonly="readonly" style="border: 0"/>--%>

				<select class="easyui-combobox" name="carId"  style="width:140px;">
					<c:forEach var="car" items="${cars}">
						<c:if test="${car.id eq carInsurance.car_id}"><option value="${car.id}" selected>${car.car_code}</option></c:if>
						<c:if test="${car.id ne carInsurance.car_id}"><option value="${car.id}">${car.car_code}</option></c:if>
					</c:forEach>
				</select>

			</td>
			<td  style="width: 85px;padding-left: 95px">保险公司：</td>
			<td><input name="insuranceComp" type="text" value="${carInsurance.insurance_comp}" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>保险生效日期：</td>
			<td><input name="insuranceStarttime" type="text" value="${carInsurance.insurance_starttime}" class="easyui-datebox" required="required"/></td>
			<td  style="width: 85px;padding-left: 95px">保险结束日期：</td>
			<td><input name="insuranceEndtime" type="text" value="${carInsurance.insurance_endtime}" class="easyui-datebox" required="required"/></td>
		</tr>
		<tr>
			<td>险种：</td>
			<td>
				<input name="insuranceType"  class="easyui-validatebox" value="${carInsurance.insurance_type}" />
				<select class="easyui-combobox" name="insuranceType"  style="width:140px;">
					<c:forEach var="dict" items="${dictsForInsurance}">
						<c:if test="${dict.code eq carInsurance.insurance_type}"><option value="${dict.code}" selected>${dict.name}</option></c:if>
						<c:if test="${dict.code ne carInsurance.insurance_type}"><option value="${dict.code}">${dict.name}</option></c:if>
					</c:forEach>
				</select>
			</td>
			<td   style="width: 85px;padding-left: 95px">保险单号：</td>
			<td><input name="insuranceId"  class="easyui-validatebox" value="${carInsurance.insurance_id}" /></td>
		</tr>
		<tr>
			<td>保险金额：</td>
			<td>
				<input name="insuranceTypeMoney" type="text" value="${carInsurance.insurance_type_money}" class="easyui-validatebox" />
			</td>
			<td style="padding-left: 95px">总保险金额：</td>
			<td><input name="totalMoney" type="text" value="${carInsurance.total_money}" class="easyui-validatebox" /></td>
		</tr>

		<tr>
			<td>保险办理人：</td>
			<td>
				<input name="insuranceBy" type="text" value="${carInsurance.insurance_by}" class="easyui-validatebox" />
			</td>
			<td style="padding-left: 95px">保险业务员：</td>
			<td><input name="insuranceSalesman" type="text" value="${carInsurance.insurance_salesman}" class="easyui-validatebox" /></td>
		</tr>

		<tr>
			<td>备注：</td>
			<td  colspan="3"><textarea style="width: 300px;height: 100px;" name="remark" value="${carInsurance.remark}" class="easyui-ueditor">${carInsurance.remark}</textarea></td>
		</tr>
		<tr>
			<td>附件：</td>
			<td  colspan="3"><img src="${ctx}/static/images/test.png" style="width: 100px;"></td>
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
    successTip(data,carInsurance,d);
}
});
});
</script>
</body>
</html>