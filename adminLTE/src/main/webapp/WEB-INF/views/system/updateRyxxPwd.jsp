<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body>
	<div style="padding: 5px">
	<form id="mainform" action="${ctx }/system/user/updateRyxxPwd" method="post">
	<table>
		<tr>
			<td>密码：<input type="hidden" name="psncode" value="${user.psncode }"/></td>
			<td><input id="plainPassword" name="plainPassword" type="password" class="easyui-validatebox" required="required" minlength="6" maxlength="20"/></td>
		</tr>
		<tr>
			<td>确认密码：</td>
			<td><input id="confirmPassword" name="confirmPassword" type="password" class="easyui-validatebox" required="required" equalTo="#plainPassword"/></td>
		</tr>
		<tr>
			<td><input id="submit" type="submit" value="submit" style="display: none"/></td>
			<td></td>
		</tr>
	</table>
	</form>
</div>
<script>
$(function(){
// 	debugger;
	$("#mainform").validate({
		rules: {
		},
		messages: {
		},
		 submitHandler:function(form){
				$("#mainform").ajaxSubmit(function(data){
					 if(data=='success'){
						 $.messager.show({ title : "提示",msg: "操作成功！", position: "bottomRight" });
							d.panel('close');
						}
			   });
        } 
	});
	
});
</script>
</body>
</html>