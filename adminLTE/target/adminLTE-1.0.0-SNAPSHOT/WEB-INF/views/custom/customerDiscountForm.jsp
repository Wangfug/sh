<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform1" action="${ctx}/web/customerDiscount/${action}" method="post">
		<input type="hidden" name="disid" value="${customerDiscount.id }" />
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align = "right">优惠券标题：</td>
			<td>
				<input name="discountTitle" type="text" value="${customerDiscount.discountTitle }"
					      class="easyui-validatebox" required="required" maxlength="30"/>
			</td>
		</tr>
		<tr>
			<td align = "right">优惠券面额(元)：</td>
			<td>
				<input name="discountMoney" type="text" value="${customerDiscount.discountMoney }"
					   data-options ="validType:'money'"   class="easyui-validatebox" required="required" maxlength="11"/>
			</td>
			<td align = "right">最低消费(元)：</td>
			<td>
				<input name="minimumConsumption" type="text" value="${customerDiscount.minimumConsumption }"
					   data-options ="validType:'money'"class="easyui-validatebox" required="required" maxlength="11"/>
			</td>
		</tr>
		<tr>
			<td align = "right">优惠券数量：</td>
			<td>
				<input name="discountNumber" type="text" value="${customerDiscount.discountNumber }"
					   class="easyui-validatebox" required="required" data-options ="validType:'number'" maxlength="11"/>
			</td>
			<td align = "right">优惠券描述：</td>
			<td>
				<input name="discountInfo" type="text" value="${customerDiscount.discountInfo }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align = "right">截止日期：</td>
			<td>
				<input name="validtime" type="text" value="${customerDiscount.validtime }" class="easyui-datetimebox" required="required"/>
			</td>
			<td align = "right">优惠券状态：</td>
			<td>
				<select class="easyui-combobox" name="discountState"  style="width:140px;">
					<option value="1">生效</option>
					<option value="2">失效</option>
				</select>
			</td>
		</tr>
</table>
</form>
</div>
<script type="text/javascript">
$(function(){
$('#mainform1').form({
onSubmit: function(){
    var isValid = $(this).form('validate');
    console.log(isValid)
    return isValid;	// 返回false终止表单提交
},
success:function(data){
    if(typeof(customerDiscount)!="undefined"){
        successTip(data,customerDiscount,d);
	}else {
        if(data!="false"){
            alert("保存成功！");
            dis.panel('close');
            $("#dlg").find("#couponId").val(data);
		}
	}

}
});
});
</script>
</body>
</html>