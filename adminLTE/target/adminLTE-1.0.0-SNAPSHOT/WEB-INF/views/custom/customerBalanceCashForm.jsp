<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/customerBalanceCash/${action}" method="post">
		<input type="hidden" name="id" value="${customerBalanceCash.id }" id="cashId"/>
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align = "right">提现人：</td>
			<td>
				<input name="name" type="text" value="${customerBalanceCash.name }" class="easyui-validatebox" readonly/>
			</td>
			<td align = "right">申请提现时间：</td>
			<td>
				<input name="create_time" type="text" value="${customerBalanceCash.create_time }" class="easyui-validatebox" readonly/>
			</td>
		</tr>
		<tr>
			<td align = "right">提现金额：</td>
			<td>
				<input name="money" type="text" value="${customerBalanceCash.money }" class="easyui-validatebox" readonly/>
			</td>
			<td align = "right">银行：</td>
			<td>
				<input name="bank" type="text" value="${customerBalanceCash.bank }" class="easyui-validatebox" readonly/>
			</td>
		</tr>
		<tr>
			<td align = "right">银行卡号：</td>
			<td>
				<input name="account_num" type="text" value="${customerBalanceCash.account_num }" class="easyui-validatebox" readonly/>
			</td>
		</tr>

		<c:if test="${customerBalanceCash.state eq 1 and customerBalanceCash.transaction_no ne null}">
			<tr>
				<td align = "right">处理人：</td>
				<td>
					<input name="member" type="text" value="${customerBalanceCash.member }" class="easyui-validatebox" readonly/>
				</td>
				<td align = "right">处理时间：</td>
				<td>
					<input name="create_time" type="text" value="${customerBalanceCash.create_time }" class="easyui-validatebox" readonly/>
				</td>
			</tr>

			<tr>
				<td align = "right">打款交易号：</td>
				<td>
					<input name="member" type="text" value="${customerBalanceCash.transaction_no }" class="easyui-validatebox" readonly/>
				</td>
				<td align = "right">状态：</td>
				<td style="color:red"> 已打款 </td>
			</tr>

		</c:if>

		　　<c:if test="${customerBalanceCash.state eq 0 and customerBalanceCash.transaction_no eq null}">
		<tr>
			<td align = "right" >打款交易号：</td>
			<td>
				<input name="member" type="text" value="${customerBalanceCash.transaction_no }" class="easyui-validatebox" required="required" id="transactionNo"/>
			</td>
		</tr>

		<tr style="text-align: center;">
			<td colspan="4">
				<button type = "button" onclick = "confirm()" style=" width: 135px;height: 40px;">确认打款 </button>
			</td>
		</tr>

		</c:if>

	</table>
</form>
</div>
<script type="text/javascript">
//$(function(){
//$('#mainform').form({
//onSubmit: function(){
//    var isValid = $(this).form('validate');
//    return isValid;	// 返回false终止表单提交
//},
//success:function(data){
//    successTip(data,customerBalanceCash,d);
//}
//});
//});
	function confirm(){
            var id = $("#cashId").val();
            var transactionNo = $("#transactionNo").val();
            if(transactionNo){
                $.ajax({
                    type:'get',
                    url:"${ctx}/web/customerBalanceCash/cashEnd?transactionNo="+transactionNo+"&id="+id,
                    success: function(data){
                        if("success"==data){
                            successTip("操作成功");
                            customerBalanceCash.datagrid('reload');
                            d.panel('close');
                        }else{
                            successTip("操作失败");
                            customerBalanceCash.datagrid('reload');
                            d.panel('close');
                        }
                    }
                });
			}else{
                alert("请填写打款交易号");
			}
        }
</script>
</body>
</html>