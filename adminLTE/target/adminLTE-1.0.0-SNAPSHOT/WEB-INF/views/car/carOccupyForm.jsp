<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/carOccupy/${action}" method="post">
		<input type="hidden" name="id" value="${carOccupy.id }" />
	<table  class="formTable">
		<tr>
			<td>车辆选择：</td>
			<td>
				<select class="easyui-combobox" name="carId"  style="width:140px;">
					<c:forEach var="car" items="${cars}">
						<c:if test="${car.id eq carOccupy.carId}"><option value="${car.id}" selected>${car.carCode}</option></c:if>
						<c:if test="${car.id ne carOccupy.carId}"><option value="${car.id}">${car.carCode}</option></c:if>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>租赁开始时间：</td>
			<td>
				<input name="timeStart" type="text" value="${carOccupy.timeStart }" class="easyui-datetimebox" required="required"/>
			</td>
			<td style="padding-left: 40px;">租赁结束时间：</td>
			<td>
				<input name="timeEnd" type="text" value="${carOccupy.timeEnd }" class="easyui-datetimebox" required="required"/>
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
    successTip(data,carOccupy,d);
}
});
});

</script>
</body>
</html>