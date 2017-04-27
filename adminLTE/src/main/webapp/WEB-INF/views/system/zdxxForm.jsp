<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/system/zdxx/${action}" method="post">
	<table  class="formTable">
		<tr>
			<td>代码：</td>
			<td>
			<input type="hidden" name="zdxxid" value="${zdxx.zdxxid }" />
			<input name="zdxxdm" type="text" value="${zdxx.zdxxdm }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td>名称：</td>
			<td><input name="zdxxmc" type="text" value="${zdxx.zdxxmc }" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>值：</td>
			<td><input name="zdxxval" type="text" value="${zdxx.zdxxval }" /></td>
		</tr>
		<tr>
			<td>类型：</td>
			<td><select name="zdlxid">${zdlxComboHtml}</select></td>
		</tr>
		<tr>
			<td>排序值：</td>
			<td><input name="pxz" type="text" value="${zdxx.pxz }" class="easyui-numberbox" required="required" min="1" max="9999"/></td>
		</tr>
		<tr>
			<td>描述：</td>
			<td><textarea rows="2" cols="41" name="zdxxsm" style="font-size: 12px;font-family: '微软雅黑'">${zdxx.zdxxsm}</textarea></td>
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