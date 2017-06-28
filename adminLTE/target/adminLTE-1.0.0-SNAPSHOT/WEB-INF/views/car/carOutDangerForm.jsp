<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/carOutDanger/${action}" method="post">
		<input type="hidden" name="id" value="${carOutDanger.id }" />
	<table  class="formTable">
		<tr>
			<td>车辆选择：</td>
			<td>
				<select class="easyui-combobox" name="carId"  style="width:140px;">
					<c:forEach var="dict" items="${cars}">
						<c:if test="${dict.id eq carOutDanger.carId}"><option value="${dict.id}" selected>${dict.carCode}</option></c:if>
						<c:if test="${dict.id ne carOutDanger.carId}"><option value="${dict.id}">${dict.carCode}</option></c:if>
					</c:forEach>
				</select>
			</td>
			<td style="padding-left: 40px;">出险日期：</td>
			<td><input name="insuranceTime" type="text" value="${carOutDanger.insuranceTime }" class="easyui-datebox" required="required"/></td>
		</tr>
		<tr>
			<td>修理费用：</td>
			<td>
				<input name="repairMoney" type="text" value="${carOutDanger.repairMoney }" class="easyui-validatebox" required="required"/>
			</td>
			<td style="padding-left: 40px;">赔偿款项：</td>
			<td><input name="compensation" type="text" value="${carOutDanger.compensation }" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>损坏部位：</td>
			<td><input name="badComponent"  class="easyui-validatebox" value="${carOutDanger.badComponent }"  required="required"/></td>
			<td style="padding-left: 40px;">出现前照片：</td>
			<td><input name="beforeImage" type="text" value="${carOutDanger.beforeImage }" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>出险后照片：</td>
			<td>
				<input name="afterImage" type="text" value="${carOutDanger.afterImage }" class="easyui-validatebox" required="required"/>
			</td>
			<td style="padding-left: 40px;">备注：</td>
			<td><input name="remark" type="text" value="${carOutDanger.remark }" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td style="padding-left: 40px;">订单号：</td>
			<td>
			<select class="easyui-combobox" name="orderNo"  style="width:140px;">
					<c:forEach var="dict" items="${orders}">
						<c:if test="${dict.id eq carOutDanger.orderNo}"><option value="${dict.id}" selected>${dict.order_no}</option></c:if>
						<c:if test="${dict.id ne carOutDanger.orderNo}"><option value="${dict.id}">${dict.order_no}</option></c:if>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>险种：</td>
			<td>
				<c:forEach var="dict" items="${dictsForInsurance}" varStatus="index" >
						<%--<input name="insuranceType" id = "insuranceType${dict.code}" type="checkbox" value="${dict.code }" checked class="easyui-validatebox" required="required"/><label for = "insuranceType${dict.code}">${dict.name}</label></br>--%>
						<input name="insuranceType" id = "insuranceType${dict.code}" type="checkbox" value="${dict.code }" class="easyui-validatebox" required="required"/><label for = "insuranceType${dict.code}">${dict.name}</label></br>
				</c:forEach>
			</td>
		</tr>
</table>
</form>
</div>
<script type="text/javascript">
$(function(){
    var insuranceType = '${insuranceType}';
    if(insuranceType){
        insuranceType = eval(insuranceType);
        for(var i=0;i<insuranceType.length;i++){
//            console.log($("#insuranceType"+insuranceType[i]).val());
            $("#insuranceType"+insuranceType[i]).attr("checked","true");
        }
    }
$('#mainform').form({
onSubmit: function(){
    var isValid = $(this).form('validate');
    return isValid;	// 返回false终止表单提交
},
success:function(data){
    successTip(data,carOutDanger,d);
}
});
});

</script>
</body>
</html>