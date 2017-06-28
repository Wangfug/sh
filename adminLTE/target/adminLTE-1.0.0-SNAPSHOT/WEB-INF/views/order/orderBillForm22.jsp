<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/orderBill/${action}" method="post">
		<input type = "hidden" name = "id" id = "id" value = "${orderBill.id}">
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align="right">发票类型：</td>
			<td >
				<select name="billType" id="billType">
					<option value = "1">普通发票</option>
					<option value = "2">增值税专用发票</option>
					<%--<option value = "3">机动车专用发票</option>
					<option value = "4">机打发票</option>
					<option value = "5">定额发票</option>--%>
				</select>
			</td>
			<td align="right">发票抬头内容：</td>
			<td >
				<input name="billTitle" type="text" value="${orderBill.billTitle}" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align="right">收件人姓名：</td>
			<td>
				<input name="addresseeName" type="text" value="${orderBill.addresseeName}" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align="right">寄送地址：</td>
			<td>
				<select id="cmbProvince" name="address"></select>
				<select id="cmbCity" name="address"></select>
				<select id="cmbArea" name="address"></select>
			</td>
		</tr>
		<tr>
			<td align="right">详细地址：</td>
			<td>
				<input name="address" type="text" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align="right">纳税人识别号：</td>
			<td >
				<input name="taxpayerCode" type="text" value="${orderBill.taxpayerCode}" class="easyui-validatebox" required="required"/>
			</td>
			<td align="right">联系电话：</td>
			<td >
				<input name="linkphone" type="text" value="${orderBill.linkphone}" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align="right">开户银行：</td>
			<td >
				<input name="depositBank" type="text" value="${orderBill.depositBank}" class="easyui-validatebox" required="required"/>
			<%--<select name="depositBank" id="depositBank">
					<option value = "1">中国招商银行</option>
					<option value = "2">中国建设银行</option>
					<option value = "3">中国银行</option>
					<option value = "4">中国邮政银行</option>
					<option value = "5">中国工商银行</option>
					<option value = "6">中国农业银行</option>
					<option value = "7">中国交通银行</option>
					<option value = "8">中国人民银行</option>
					<option value = "9">中国兴业银行</option>
					<option value = "10">中国中信银行</option>
					<option value = "11">中国光大银行</option>
					<option value = "12">中国民生银行</option>
					<option value = "13">中国广发银行</option>
					<option value = "14">中国华夏银行</option>
					<option value = "15">中国浦发银行</option>
					<option value = "16">深圳发展银行</option>
				</select>--%>
			</td>
			<td align="right">银行账号：</td>
			<td>
				<input name="bankAccount" type="text" value="${orderBill.bankAccount}" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align="right">发票状态：</td>
			<td >
				<select name="state" id="state">
					<option value = "1">有效发票</option>
					<option value = "0">失效发票</option>
				</select>
			</td>
		</tr>
</table>
</form>
</div>
<script type="text/javascript">
    $(function(){
        addressInit('cmbProvince', 'cmbCity', 'cmbArea');
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