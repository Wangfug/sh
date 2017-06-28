<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/system/memberJob/${action}" method="post">
		<input type="hidden" name="deptId" value="${dept.id }" />
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align = "right">部门编号：</td>
			<td>
				<input name="deptCode" type="text" value="${dept.deptCode }" class="easyui-validatebox" required="required"/>
			</td>
			<td align = "right">上级部门编号：</td>
			<td>
				<input name="parentCode" type="text" value="${dept.parentCode }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align = "right">部门名称：</td>
			<td>
				<input name="deptName" type="text" value="${dept.deptName }" class="easyui-validatebox" required="required"/>
			</td>
			<td align = "right">公司编号：</td>
			<td>
				<input name="companyCode" type="text" value="${dept.companyCode }" class="easyui-validatebox" required="required"/>
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
    successTip(data,memberJob,d);
}
});
});

</script>
</body>
</html>