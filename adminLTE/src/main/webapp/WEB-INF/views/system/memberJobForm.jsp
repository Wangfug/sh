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
		<input type="hidden" name="id" value="${memberJob.id }" />
		<input type="hidden" name="deptId" value="${memberJob.deptId }" />
	<table  class="formTable">
		<tr>
			<td>职位编号：</td>
			<td>
			<input name="jobCode" type="text" value="${memberJob.jobCode }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td>职位名称：</td>
			<td><input name="jobName" type="text" value="${memberJob.jobName }" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>所属部门编号：</td>
			<td><input name="deptCode" type="text" value="${memberJob.deptCode}" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>所属门店编号：</td>
			<td><input name="shopCode" type="text" value="${memberJob.shopCode}" class="easyui-validatebox" required="required"/></td>
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
	    	successTip(data,dg,d);
	    }    
	}); 
});

</script>
</body>
</html>