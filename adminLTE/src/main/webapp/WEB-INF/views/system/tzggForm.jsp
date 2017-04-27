<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/system/tzgg/${action}" method="post">
	<table  class="formTable">
		<tr>
			<td>标题：</td>
			<td>
			<input type="hidden" name="id" value="${tzgg.id }" />
			<input name="tzbt" type="text" value="${tzgg.tzbt }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td>类型：</td>
			<td><select name="lx">${lxComboHtml}</select></td>
		</tr>
		<tr>
			<td>置顶：</td>
			<td><select name="zdbz">${zdComboHtml}</select></td>
		</tr>
		
		<tr>
			<td>内容：</td>
			<td><textarea rows="8" cols="41" name="tznr" class="easyui-ueditor">${tzggnr}</textarea></td>
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