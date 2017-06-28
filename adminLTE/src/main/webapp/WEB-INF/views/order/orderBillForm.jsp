<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form  action="${ctx}/web/orderBill/${action}" method="post">
		<input type = "hidden" name = "id" id = "billId" value = "${orderBill.id}">
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align="right">发票类型：</td>
			<td >
				<c:if test="${orderBill.billType eq '1'}">
					<span>普通发票</span>
				</c:if>
				<c:if test="${orderBill.billType eq '2'}">
					<span>增值税发票</span>
				</c:if>
			</td>
			<td align="right">发票抬头内容：</td>
			<td >
				<input name="billTitle" type="text" value="${orderBill.billTitle}" class="easyui-validatebox" readonly/>
			</td>
		</tr>
		<tr>
			<td align="right">收件人姓名：</td>
			<td>
				<input name="addresseeName" type="text" value="${orderBill.addresseeName}" class="easyui-validatebox" readonly/>
			</td>
			<td align="right" readonly>联系电话：</td>
			<td >
				<input name="linkphone" type="text" value="${orderBill.linkphone}" class="easyui-validatebox" readonly/>
			</td>
		</tr>

		<tr>
		<td align="right" readonly>寄送地址：</td>
			<td colspan="3">
			<input name="area" type="text" value="${orderBill.area}" class="easyui-validatebox" readonly/>
		</td>
		</tr>
		<tr>
			<td align="right" readonly>详细地址：</td>
			<td colspan="3">
				<input name="address" type="text" value="${orderBill.address}" class="easyui-validatebox" style="width: 400px;"readonly/>
			</td>
		</tr>
		<c:if test="${orderBill.billType eq '2'}">
		<tr>
			<td align="right">纳税人识别号：</td>
			<td >
				<input name="taxpayerCode" type="text" value="${orderBill.taxpayerCode}" class="easyui-validatebox" readonly/>
			</td>
		</tr>
		<tr>
			<td align="right" readonly>开户银行：</td>
			<td >
				<input name="depositBank" type="text" value="${orderBill.depositBank}" class="easyui-validatebox" readonly/>
			</td>
			<td align="right">银行账号：</td>
			<td>
				<input name="bankAccount" type="text" value="${orderBill.bankAccount}" class="easyui-validatebox" readonly/>
			</td>
		</tr>
		</c:if>
		<tr>
			<td align="right">发票状态：</td>
			<td >
				<c:if test="${orderBill.state eq '1'}">
					<span>已寄出</span>
				</c:if>
				<c:if test="${orderBill.state eq '0'}">
					<span>未寄出</span>
				</c:if>
			</td>
		</tr>

		<c:if test="${orderBill.state eq '1'}">
			<tr>
				<td align="right" readonly>快递公司：</td>
				<td>
					<input name="express" type="text" value="${orderBill.express}" class="easyui-validatebox" readonly/>
				</td>
				<td align="right" readonly>快递单号：</td>
				<td >
					<input name="expressNo" type="text" value="${orderBill.expressNo}" class="easyui-validatebox" readonly/>
				</td>
			</tr>
		</c:if>
		<c:if test="${orderBill.state eq '0'}">
			<tr>
			<td align="right" readonly>快递公司：</td>
			<td>
				<input name="express" type="text" value="" class="easyui-validatebox" required="required" id="express"/>
			</td>
			<td align="right" readonly>快递单号：</td>
			<td >
				<input name="expressNo" type="text" value="" class="easyui-validatebox" required="required"  id="expressNo"/>
			</td>
		</tr>

			<tr>
				<td colspan="4">
				<button type = "button" onclick="confirm()">确认发出</button>
				</td>
			</tr>

		</c:if>
</table>

</form>
</div>
<script type="text/javascript">
//$(function(){
//        addressInit('cmbProvince', 'cmbCity', 'cmbArea');
//$('#mainform').form({
//onSubmit: function(){
//    var isValid = $(this).form('validate');
//    return isValid;	// 返回false终止表单提交
//},
//success:function(data){
//    successTip(data,order,d);
//}
//});
//});

function confirm(){
    var billId = $("#billId").val();
    var express = $("#express").val();
    var expressNo = $("#expressNo").val();
    if(express && expressNo){
        $.ajax({
            type:'post',
            url:"${ctx}/web/orderBill/updateBill?id="+billId+"&express="+express+"&expressNo="+expressNo,
            success:function(data){
               successTip(data,orderBill,d);
            },error:function(){
                alert("error");
            }
        });
    }else{
        alert("请填写快递名称/号码");
    }
}
</script>
</body>
</html>