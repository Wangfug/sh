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
		<input type = "hidden" name = "getInfo"  value = "${getInfo}" id = "getInfo">
		<input type = "hidden" name = "returnInfo"  value = "${returnInfo}" id = "returnInfo">
		<input type = "hidden" name = "orderNo"  value = "${orderNo}">
		<table  class="formTable" cellpadding="20" align="center">
		<c:forEach var="work" items="${works}" >
			<c:if test="${work.order_type == 0}">
				<tr>
					<td data = "${work.id}">取车：</td>
					<td>
						<select id="emp1" onchange = "changeValue(this);">
						<c:forEach var="emp" items="${emps}">
							<c:if test="${emp.id eq work.e_no}">
								<option value = "${emp.id}" selected>${emp.name}--${emp.code}</option>
							</c:if>
							<c:if test="${emp.id ne work.e_no}">
								<option value = "${emp.id}">${emp.name}--${emp.code}</option>
							</c:if>
						</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>
			<c:if test="${work.order_type == 1}">
				<tr>
					<td data = "${work.id}">还车：</td>
					<td>
						<select id="emp2" onchange = "changeValue(this);">
							<c:forEach var="emp" items="${emps}">
								<c:if test="${emp.id eq work.e_no}">
									<option value = "${emp.id}" selected>${emp.name}--${emp.code}</option>
								</c:if>
								<c:if test="${emp.id ne work.e_no}">
									<option value = "${emp.id}">${emp.name}--${emp.code}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>

		</c:forEach>
</table>
</form>
</div>
<script type="text/javascript">
//	$("select").on("change",function(){
//        changeValue(this)
//	});
$(function(){
$('#mainform').form({
onSubmit: function(){
    var isValid = $(this).form('validate');
    return isValid;	// 返回false终止表单提交
},
success:function(data){
    successTip(data,order,orderWork);
}
});
});
function changeValue(obj){
    var select = $(obj);
    var val;
    var emp = select.val();
    var work = select.parent().prev().attr("data");
    val = work+","+emp;
    if(select.attr("id")=="emp1"){
		$("#getInfo").val(val);
	}
    if(select.attr("id")=="emp2"){
        $("#returnInfo").val(val);
    }
}
</script>
</body>
</html>