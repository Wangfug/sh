<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div style="width:100%">
	<form id="mainform" action="${ctx}/web/carAttach/${action}" method="post" align = "center">
		<input type="hidden" name="id" value="${car.id }" />
	<table  class="formTable" cellpadding="15">
		<tr>
			<td>车牌号：</td>
			<td>
			<input name="carCode" type="text" value="${car.carCode }" class="easyui-validatebox" required="required"/>
			</td>
			<td style="padding-left: 40px;">所在门店：</td>
			<td><input name="carShop" type="text" value="${car.carShop }" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>车辆照片：</td>
			<td><input name="attachment" type="text" value="${car.attachment }" class="easyui-validatebox" required="required"/></td>
			<td style="padding-left: 40px;">发动机号：</td>
			<td><input name="engineNo" type="text" value="${car.engineNo }" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>车架号：</td>
			<td>
				<input name="frameNo" type="text" value="${car.frameNo }" class="easyui-validatebox" required="required"/>
			</td>
			<td style="padding-left: 40px;">颜色：</td>
			<td><input name="color" type="text" value="${car.color }" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>购买日期：</td>
			<td><input name="buyTime"  class="easyui-datebox" value="${car.buyTime }"  required="required"/></td>
			<td style="padding-left: 40px;">品牌：</td>
			<td><input name="brand" type="text" value="${car.brand }" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>型号：</td>
			<td>
				<input name="model" type="text" value="${car.model }" class="easyui-validatebox" required="required"/>
			</td>
			<td style="padding-left: 40px;">车型：</td>
			<td><input name="cartonNumber" type="text" value="${car.cartonNumber }" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>经营种类：</td>
			<td><input name="belong" type="text" value="${car.belong }" class="easyui-validatebox" required="required"/></td>
			<td style="padding-left: 40px;">服务状态：</td>
			<td><select name="state">${carStateHtml}</select></td>
		</tr>
		<tr>
			<td>入库时间：</td>
			<td>
				<input name="createTime"  value="${car.createTime }"  class="easyui-datetimebox" required="required"/>
			</td>
			<td style="padding-left: 40px;">出厂日期：</td>
			<td><input name="leaveFactoryTime"  value="${car.leaveFactoryTime }"  class="easyui-datebox" required="required"/></td>
		</tr>
		<tr>
			<td>购买价格：</td>
			<td><input name="moneyBuy" type="text" value="${car.moneyBuy }" class="easyui-validatebox" required="required"/></td>
			<td style="padding-left: 40px;">资产挂靠公司/个人：</td>
			<td><input name="bindObj" type="text" value="${car.bindObj }" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>备注1：</td>
			<td colspan="3"><textarea  style="width: 300px;height: 100px;"  name="remark1" value="${car.remark1 }" class="easyui-ueditor">${car.remark1 }</textarea></td>
		</tr>
    	<tr>
			<td>备注2：</td>
			<td  colspan="3"><textarea style="width: 300px;height: 100px;" name="remark2" value="${car.remark2 }" class="easyui-ueditor">${car.remark2 }</textarea></td>
		</tr>
		<tr>
			<td>备注3：</td>
			<td colspan="3"><textarea style="width: 300px;height: 100px;"  name="remark3" value="${car.remark3 }" class="easyui-ueditor">${car.remark3 }</textarea></td>
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
    successTip(data,car,d);
}
});
});

</script>
</body>
</html>