<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/orderEvaluate/${action}" method="post">
		<input type = "hidden" name = "id" id = "id" value = "${orderEvaluate.id}">
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align="right">车辆情况：</td>
			<td >
				<select name="vehicleCondition" id="vehicleCondition">
					<option value = "1">1星</option>
					<option value = "2">2星</option>
					<option value = "3">3星</option>
					<option value = "4">4星</option>
					<option value = "5">5星</option>
				</select>
			</td>
			<td align="right">取车服务：</td>
			<td >
				<select name="getVehicleService" id="getVehicleService">
					<option value = "1">1星</option>
					<option value = "2">2星</option>
					<option value = "3">3星</option>
					<option value = "4">4星</option>
					<option value = "5">5星</option>
				</select>
			</td>
			<td align="right">还车服务：</td>
			<td>
				<select name="returnVehicleService" id="returnVehicleService">
					<option value = "1">1星</option>
					<option value = "2">2星</option>
					<option value = "3">3星</option>
					<option value = "4">4星</option>
					<option value = "5">5星</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right">开单好评指数：</td>
			<td>
				<select name="openOrder" id="openOrder">
					<option value = "1">1星</option>
					<option value = "2">2星</option>
					<option value = "3">3星</option>
					<option value = "4">4星</option>
					<option value = "5">5星</option>
				</select>
			</td>
			<td align="right">关单好评指数：</td>
			<td>
				<select name="closeOrder" id="closeOrder">
					<option value = "1">1星</option>
					<option value = "2">2星</option>
					<option value = "3">3星</option>
					<option value = "4">4星</option>
					<option value = "5">5星</option>
				</select>
			</td>
			<td align="right">总体服务好评指数：</td>
			<td>
				<select name="totalService" id="totalService">
					<option value = "1">1星</option>
					<option value = "2">2星</option>
					<option value = "3">3星</option>
					<option value = "4">4星</option>
					<option value = "5">5星</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right">评论内容：</td>
			<td colspan = "5">
				<textarea name="content" id="content" style = "width:100%;" >${orderEvaluate.content}</textarea>
				<%--<input name="eno" type="textarea" value="${orderWork.eno}" class="easyui-validatebox" required="required"/>--%>
			</td>
		</tr>
		<tr>
			<td align="right">开单业务员反馈：</td>
			<td colspan = "5">
				<textarea name="openOpinion" id="openOpinion" style = "width:100%;">${orderEvaluate.openOpinion}</textarea>
			</td>
		</tr>
		<tr>
			<td align="right">关单业务员反馈：</td>
			<td colspan = "5">
				<textarea name="closeOpinion" id="closeOpinion" style = "width:100%;">${orderEvaluate.closeOpinion}</textarea>
			</td>
		</tr>
		<tr>
			<td align="right">总体反馈意见：</td>
			<td colspan = "5">
				<textarea name="totalOpinion" id="totalOpinion" style = "width:100%;">${orderEvaluate.totalOpinion}</textarea>
			</td>
		</tr>
		<tr>
			<td align="right">附件：</td>
			<td>
				<select name="attachment" id="attachment">
					<option value = "1">附件一</option>
					<option value = "2">附件二</option>
				</select>
			</td>
			<td align="right">状态：</td>
			<td>
				<select name="state" id="state">
					<option value = "1">有效评价</option>
					<option value = "2">无效评价</option>
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
    successTip(data,order,d);
}
});
});

</script>
</body>
</html>