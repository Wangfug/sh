<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/orderAccount/${action}" method="post">
		<input type = "hidden" name = "id" id = "id" value = "${orderAccount.id}">
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align="right">账单总额：</td>
			<td >
				<input name="totalMoney" type="text" value="${orderAccount.totalMoney}" class="easyui-validatebox" required="required"/>元
			</td>
			<td align="right">实际支付金额：</td>
			<td>
				<input name="realPay" type="text" value="${orderAccount.realPay}" class="easyui-validatebox" required="required"/>元
			</td>
			<%--<td align="right">所属订单：</td>
			<td >
				<input name="belongOrder" type="text" value="${orderAccount.belongOrder}" class="easyui-validatebox" required="required"/>
			</td>--%>
		</tr>
		<tr>
			<td align="right">付款人：</td>
			<td >
				<input name="customer" type="text" value="${orderAccount.customer}" class="easyui-validatebox" required="required"/>
			</td>
			<td align="right">付款方式：</td>
			<td>
				<input name="payWay" type="text" value="${orderAccount.payWay}" class="easyui-validatebox" required="required"/>
			</td>
			<td align="right">付款账户：</td>
			<td >
				<input name="payAmount" type="text" value="${orderAccount.payAmount}" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align="right">收款人：</td>
			<td >
				<input name="acceptMan" type="text" value="${orderAccount.acceptMan}" class="easyui-validatebox" required="required"/>
			</td>
			<td align="right">收款方式：</td>
			<td>
				<input name="acceptWay" type="text" value="${orderAccount.acceptWay}" class="easyui-validatebox" required="required"/>
			</td>
			<td align="right">收款账户：</td>
			<td >
				<input name="acceptAmount" type="text" value="${orderAccount.acceptAmount}" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
</table>
</form>
</div>
<script type="text/javascript">
    $(function(){
//        addressInit('cmbProvince', 'cmbCity', 'cmbArea');
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