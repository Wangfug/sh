<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/system/dictType/${action}" method="post">
	<table  class="formTable">
		<tr>
			<td>代码：</td>
			<td>
			<input type="hidden" name="id" value="${dictType.id }" />
			<input name="code" type="text" value="${dictType.code }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td>名称：</td>
			<td><input name="name" type="text" value="${dictType.name }" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>父类型：</td>
			<td><select name="parentCode">${dictTypeComboHtml}</select></td>
		</tr>
		<tr>
			<td>描述：</td>
			<td><textarea rows="2" cols="41" name="comment" style="font-size: 12px;font-family: '微软雅黑'">${dictType.comment}</textarea></td>
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