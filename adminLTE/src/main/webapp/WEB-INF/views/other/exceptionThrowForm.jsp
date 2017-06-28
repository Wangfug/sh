<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/exceptionThrow/${action}" method="post">
		<input type = "hidden" name = "id" id = "id" value = "${exceptionThrow.id}">
		<table  class="formTable">
			<tr>
				<td>异常订单号：</td>
				<td>
					<input name="exceptionOrder" type="text" value="${exceptionThrow.exceptionOrder}" class="easyui-validatebox" required="required"/>
				</td>
				<td  style="width: 85px;padding-left: 95px">处理人：</td>
				<td>
					<input name="handleBy" type="text" value="${exceptionThrow.handleBy}" class="easyui-validatebox" required="required"/>
				</td>
			</tr>
			<tr>
				<td  >是否处理：</td>
				<td>
					<select name="isHandle"  style="vertical-align: middle;width: 100px;">
						<c:if test="${exceptionThrow.isHandle eq '1'}"><option value="1" selected>已处理</option><option value="2">未处理</option></c:if>
						<c:if test="${exceptionThrow.isHandle eq '2'}"><option value="2" selected>未处理</option><option value="1">已处理</option></c:if>
						<c:if test="${exceptionThrow.isHandle ne '2' or '1'}"><option value="2">未处理</option><option value="1">已处理</option></c:if>
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
    successTip(data,exceptionThrow,d);
}
});
});

</script>
</body>
</html>