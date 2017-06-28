<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/customerDrivingLicence/${action}" method="post">
		<input type="hidden" name="id" value="${customerDrivingLicence.id }" />
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align = "right">准驾车型：</td>
			<td>
				<input name="quasiDrivingType" type="text" value="${customerDrivingLicence.quasiDrivingType }" class="easyui-validatebox" required="required"/>
			</td>
			<td align = "right">领证时间：</td>
			<td>
				<input name="getTime" type="text" value="${customerDrivingLicence.getTime }" class="easyui-datebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align = "right">档案编号：</td>
			<td>
				<input name="fileNumber" type="text" value="${customerDrivingLicence.fileNumber }" class="easyui-validatebox" required="required"/>
			</td>
			<td align = "right">驾驶证照片：</td>
			<td>
				<input name="attachment" type="text" value="${customerDrivingLicence.attachment }" class="easyui-file" required="required"/>
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
    successTip(data,customerDrivingLicence,d);
}
});
});

</script>
</body>
</html>