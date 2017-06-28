<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/tbaseEmployee/${action}" method="post">
	<table  class="formTable" cellpadding="15">
		<tr>
			<td>用户名：</td>
			<td>
				<input type="hidden" name="oldCode" value="${employee.psnname }"/>
				<input type="hidden" name="pkPsnbasdoc" value="${id }"/>
				<input id="loginName" name="psnname" class="easyui-validatebox" data-options="width: 150" value="${employee.psnname }" >
				<span id = "loginCheck" style="display:none"></span>
			</td>
			<td>真实姓名：</td>
			<td><input name="memberName" type="text" value="${employee.memberName }" class="easyui-validatebox" data-options="width: 150,required:'required',validType:'length[2,20]'"/></td>
		</tr>
		<tr>
			<td>选择岗位：</td>
			<td>
				<input type="checkbox" value = "">选择岗位<br/>
				<%--<select name="jobCode" >--%>
					<%--<option value="">请选择岗位</option>--%>
						<c:forEach var="staffJob" items="${StaffJobs}">
							<input type="checkbox" id = "${staffJob.jobCode}" value = "${staffJob.jobCode}" name = "jobCode">${staffJob.jobName}--${staffJob.jobCode}<br/>
							<%--<option value="${staffJob.jobCode}">${staffJob.jobName}--${staffJob.jobCode}</option>--%>
						</c:forEach>
				<%--</select>--%>
			</td>
		</tr>
		<c:if test="${type ne 'update'}">
			<tr>
				<td>密码：</td>
				<td><input id="plainPassword" name="plainPassword" type="password" class="easyui-validatebox" data-options="width: 150,required:'required',validType:'length[6,20]'"/></td>
				<td>确认密码：</td>
				<td><input id="confirmPassword" name="confirmPassword" type="password" class="easyui-validatebox" data-options="width: 150,required:'required',validType:'equals[$(\'#plainPassword\').val()]'"/></td>
			</tr>
		</c:if>
		<%--<tr>
			<td>真实姓名：</td>
			<td><input name="memberName" type="text" value="${employee.memberName }" class="easyui-validatebox" data-options="width: 150,required:'required',validType:'length[2,20]'"/></td>
			<td>工号：</td>
			<td>
				<input type="text" name="eno" value="${employee.eno }" class="easyui-validatebox"  data-options="width: 150,required:'required'"/>
			</td>
		</tr>--%>
		<tr>
			<td>电话：</td>
			<td><input type="text" name="mobile" value="${employee.mobile }" class="easyui-numberbox"  data-options="width: 150,required:'required',validType:'phoneRex'" /></td>
			<td>邮箱：</td>
			<td><input type="text" name="email" value="${employee.email }" class="easyui-validatebox"  data-options="width: 150,required:'required'" validtype="email"
					   invalidMessage="邮箱格式不正确"/></td>
		</tr>
		<tr>
			<%--<td>证件类型：</td>
			<td>
				<input type="text" name="certificateType" value="${employee.certificateType }" class="easyui-validatebox"  data-options="width: 150,required:'required'" />
			</td>--%>
			<td>身份证：</td>
			<td><input type="text" name="certificateCode" value="${employee.certificateCode }" class="easyui-validatebox"  data-options="width: 150,required:'required',validType:'certificate'"
					   /></td>
		</tr>
</table>
</form>
</div>
<script type="text/javascript">
	var roles = '${roles}';
    var employee='${employee}';
    var action = '${action}';
    //用户 添加修改区分
    if(!employee){
//	$("input[name='gender'][value=1]").attr("checked",true);
        //用户名存在验证
        $('#loginName').validatebox({
            required: true,
            validType:{
                length:[2,20],
                remote:["${ctx}/system/user/checkLoginName","loginName"]
            }
        });
    }else if(action=='update'){
        $("input[name='psnname']").attr('readonly','readonly');
        $("input[name='psnname']").css('background','#eee')
    }
$(function(){
$('#mainform').form({
onSubmit: function(){
    var isValid = $(this).form('validate');
    return isValid;	// 返回false终止表单提交
},
success:function(data){
    successTip(data,tbaseEmployee,d);
}
});

if(roles){
    roles = eval(roles);
    for(var i=0;i<roles.length;i++){
		$("#"+roles[i].jobCode).attr("checked",true);
	}
}
});

</script>
</body>
</html>