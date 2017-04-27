<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/system/sjxx/${action}" method="post">
	<table  class="formTable">
		<tr>
			<td>数据类型：</td>
			<td><select name="sjlx">${lxComboHtml}</select></td>
		</tr>
		<tr>
			<td>数据值：</td>
			<td>
			<input type="hidden" name="id" value="${sjxx.id }" />
			<input name="sjval" type="text" value="${sjxx.sjval }" class="easyui-numberbox" required="required" min="0.0000"  max="999999.9999" precision="4"/>
			</td>
		</tr>
		<tr>
			<td>开始日期：</td>
			<td><input name="ksrq" id="ksrq" type="text" value="<fmt:formatDate value="${sjxx.ksrq }" pattern="yyyy-MM-dd"/>" class="easyui-datebox"  data-options="onSelect:onSelect"  required="required" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>结束日期：</td>
			<td><input name="jsrq" id="jsrq" type="text" value="<fmt:formatDate value="${sjxx.jsrq }" pattern="yyyy-MM-dd"/>" class="easyui-datebox" data-options="onSelect:onSelect" /></td>
		</tr>
		
	</table>
	</form>
</div>
<script type="text/javascript">
$(function(){
	$('#mainform').form({    
	    onSubmit: function(){    
	    	//debugger;
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },    
	    success:function(data){   
	    	successTip(data,dg,d);
	    }    
	}); 
	var kk = '${sjxx.ksrq }';
	//debugger
	if(new Date(kk) <  new Date()){
		$('#ksrq').datebox({
			disabled: true
		});
	}
});

function onSelect(d){
	 var issd = this.id == 'ksrq', 
	 sd = issd ? d : new Date($('#ksrq').datebox('getValue')), 
	 ed = issd ? new Date($('#jsrq').datebox('getValue')) : d;
     if (ed < sd) {
         alert('结束日期小于开始日期');
         //只要选择了日期，不管是开始或者结束都对比一下，如果结束小于开始，则清空结束日期的值并弹出日历选择框
         $('#jsrq').datebox('setValue', '').datebox('showPanel');
     }

}
</script>
</body>
</html>