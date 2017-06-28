<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/customerCreditCard/${action}" method="post">
		<input type="hidden" name="id" value="${customerCreditCard.id }" />
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align = "right">开户银行：</td>
			<td>
				<input name="creditBank" type="text" value="${customerCreditCard.creditBank }" class="easyui-validatebox" required="required"/>
			</td>
			<td align = "right">信用卡号：</td>
			<td>
				<input name="creditNo" type="text" value="${customerCreditCard.creditNo }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align = "right">安全码：</td>
			<td>
				<input name="security" type="text" value="${customerCreditCard.security }" class="easyui-validatebox" required="required"/>
			</td>
			<td align = "right">预留号码：</td>
			<td>
				<input name="linkephone" type="text" value="${customerCreditCard.linkephone }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align = "right">有效期：</td>
			<td>
				<input name="validityTime" type="text" value="${customerCreditCard.validityTime }" class="easyui-datetimebox" required="required"/>
			</td>
			<td align = "right">信用卡状态：</td>
			<td>
				<select class="easyui-combobox" name="creditState"  style="width:140px;">
				<option value="1" selected>有效</option>
				<option value="2">失效</option>
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
    successTip(data,customerCreditCard,d);
}
});
});

</script>
</body>
</html>