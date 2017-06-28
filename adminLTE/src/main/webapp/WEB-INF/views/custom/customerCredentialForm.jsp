<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/customerCredential/${action}" method="post">
		<input type="hidden" name="id" value="${customerCredential.id }" />
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align = "right">证件类型：</td>
			<td>
				<select class="easyui-combobox" name="credentialType"  style="width:140px;">
					<%--<option value="" selected>身份证</option>--%>
					<%--<option value="2" selected>军官证</option>--%>
					<%--<option value="3" selected>自营</option>--%>
					<%--<option value="4" selected>联营</option>--%>
					<%--<option value="5" selected>自营</option>--%>
					<%--<option value="6" selected>自营</option>--%>
					<c:forEach var="dict" items="${dictForCredential}">
						<c:if test="${dict.code eq customerCredential.credentialType}"><option value="${dict.code}" selected>${dict.name}</option></c:if>
						<c:if test="${dict.code ne customerCredential.credentialType}"><option value="${dict.code}">${dict.name}</option></c:if>
					</c:forEach>
				</select>
			</td>
			<td align = "right">证件号：</td>
			<td>
				<input name="credentialCode" type="text" value="${customerCredential.credentialCode }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align = "right">领证日期：</td>
			<td>
				<input name="getTime" type="text" value="${customerCredential.getTime }" class="easyui-datetimebox" required="required"/>
			</td>
			<td align = "right">证件照正面：</td>
			<td>
				<input name="frontView" type="text" value="${customerCredential.frontView }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align = "right">证件照反面：</td>
			<td>
				<input name="backView" type="text" value="${customerCredential.backView }" class="easyui-validatebox" required="required"/>
			</td>
			<td align = "right">证件照状态：</td>
			<td>
				<select class="easyui-combobox" name="state"  style="width:140px;">
					<option value="1" selected>有效</option>
					<option value="2" >失效</option>
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
    successTip(data,customerCredential,d);
}
});
});

</script>
</body>
</html>