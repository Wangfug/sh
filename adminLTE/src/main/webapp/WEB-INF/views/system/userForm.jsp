<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx }/system/user/${action}" method="post">
		<table class="formTable">
			<tr>
				<td>用户名：</td>
				<td>
					<input type="hidden" name="pkPsnbasdoc" value="${id }"/>
					<input id="loginName" name="psnname" class="easyui-validatebox" data-options="width: 150" value="${employee.psnname }" >
					<span id = "loginCheck" style="display:none"></span>
				</td>
			</tr>
			<c:if test="${action != 'update'}">
			<tr>
				<td>密码：</td>
				<td><input id="plainPassword" name="plainPassword" type="password" class="easyui-validatebox" data-options="width: 150,required:'required',validType:'length[6,20]'"/></td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input id="confirmPassword" name="confirmPassword" type="password" class="easyui-validatebox" data-options="width: 150,required:'required',validType:'equals[$(\'#plainPassword\').val()]'"/></td>
			</tr>
			</c:if>
			<tr>
				<td>真实姓名：</td>
				<td><input name="memberName" type="text" value="${employee.memberName }" class="easyui-validatebox" data-options="width: 150,required:'required',validType:'length[2,20]'"/></td>
			</tr>
			<%--<tr>
				<td>工号：</td>
				<td>
					<input type="text" name="eno" value="${employee.eno }" class="easyui-validatebox"  data-options="width: 150,required:'required'"/>
				</td>
			</tr>--%>
			<tr>
				<td>电话：</td>
				<td><input type="text" name="mobile" value="${employee.mobile }" class="easyui-numberbox"  data-options="width: 150,required:'required',validType:'phoneRex'" /></td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td><input type="text" name="email" value="${employee.email }" class="easyui-validatebox"  data-options="width: 150,required:'required'" validtype="email"
						   invalidMessage="邮箱格式不正确"/></td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
var action="${action}";
//用户 添加修改区分
if(action=='create'){
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

//提交表单
$('#mainform').form({    
    onSubmit: function(){    
    	var isValid = $(this).form('validate');
		return isValid;	// 返回false终止表单提交
    },    
    success:function(data){   
    	successTip(data,dg,d);
    }    
});
$.extend($.fn.validatebox.defaults.rules, {
    phoneRex: {
        validator: function(value){
            var rex=/^1[3-8]+\d{9}$/;
            //var rex=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
            //区号：前面一个0，后面跟2-3位数字 ： 0\d{2,3}
            //电话号码：7-8位数字： \d{7,8
            //分机号：一般都是3位数字： \d{3,}
            //这样连接起来就是验证电话的正则表达式了：/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/
            var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
            if(rex.test(value)||rex2.test(value))
            {
                return true;
            }else
            {
                return false;
            }
        },
        message: '请输入正确电话或手机格式'
    }
});
</script>
</body>
</html>