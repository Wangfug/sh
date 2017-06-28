<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/orderFee/${action}" method="post">
		<input type = "hidden" name = "id" id = "id" value = "${orderFee.id}">
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align="right">车辆租赁费：</td>
			<td >
				<input name="carRentFee" type="text" value="${orderFee.carRentFee}" class="easyui-validatebox" required="required"/>元
			</td>
			<td align="right">送车服务费：</td>
			<td>
				<input name="sendCarFee" type="text" value="${orderFee.sendCarFee}" class="easyui-validatebox" required="required"/>元
			</td>
		</tr>
		<tr>
			<td align="right">基本保险费：</td>
			<td >
				<input name="baseFee" type="text" value="${orderFee.baseFee}" class="easyui-validatebox" required="required"/>元
			</td>
			<td align="right">其他费用：</td>
			<td >
				<input name="otherFee" type="text" value="${orderFee.otherFee}" class="easyui-validatebox" required="required"/>元
			</td>
		</tr>
		<tr>
			<td align="right">附加不计免赔：</td>
			<td>
				<input name="additionalBujimianpei" type="text" value="${orderFee.additionalBujimianpei}" class="easyui-validatebox" required="required"/>元
			</td>
			<td align="right">手续费：</td>
			<td >
				<input name="handingCharge" type="text" value="${orderFee.handingCharge}" class="easyui-validatebox" required="required"/>元
			</td>
		</tr>
		<tr>
			<td align="right">附加三责险：</td>
			<td >
				<input name="additionalFeeForThree" type="text" value="${orderFee.additionalFeeForThree}" class="easyui-validatebox" required="required"/>元
			</td>
			<td align="right">总计费用：</td>
			<td>
				<input name="totalFee" type="text" value="${orderFee.totalFee}" class="easyui-validatebox" required="required"/>元
			</td>
		</tr>
		<tr>
			<td align="right">预授权：</td>
			<td>
				<input name="preAuthorized" type="text" value="${orderFee.preAuthorized}" class="easyui-validatebox" required="required"/>元
			</td>
			<td align="right">费用单状态：</td>
			<td >
				<select name="state" id="state">
					<option value = "1">有效费用单</option>
					<option value = "2">失效费用单</option>
				</select>
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