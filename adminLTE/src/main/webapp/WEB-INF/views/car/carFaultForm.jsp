<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
	<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/carFaultHandle/${action}" method="post">
		<table  class="formTable">
			<tr>
				<td  style="width: 75px;">车牌号：</td>
				<td>
					<input type="hidden" name="id" value="${carFaultHandle.id}" />
					<%--<input name="car_code" type="text" value="${carFaultHandle.car_code}" class="easyui-validatebox" required="required" readonly="readonly" style="border: 0"/>--%>
					<select class="easyui-combobox" name="carId"  style="width:140px;">
						<c:forEach var="dict" items="${cars}">
							<c:if test="${dict.id eq carFaultHandle.car_id}"><option value="${dict.id}" selected>${dict.car_code}</option></c:if>
							<c:if test="${dict.id ne carFaultHandle.car_id}"><option value="${dict.id}">${dict.car_code}</option></c:if>
						</c:forEach>
					</select>
				</td>
				<td  style="width: 85px;padding-left: 95px">订单号：</td>
				<td>
					<%--<input name="order_no" type="text" value="${carFaultHandle.order_no}" class="easyui-validatebox" required="required" readonly="readonly" style="border: 0"/>--%>
					<select class="easyui-combobox" name="faultOrder"  style="width:140px;">
						<c:forEach var="dict" items="${orders}">
							<c:if test="${dict.id eq carFaultHandle.fault_order}"><option value="${dict.id}" selected>${dict.order_no}</option></c:if>
							<c:if test="${dict.id ne carFaultHandle.fault_order}"><option value="${dict.id}">${dict.order_no}</option></c:if>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>故障部位：</td>
				<td colspan="3" style="padding-top: 10px;padding-bottom: 10px;">
					<c:forEach var="dict" items="${dictsForCarFaultComponengt}">
						<input name="faultComponengt" id = "faultComponengt${dict.code}" type="checkbox" value="${dict.code}" class="easyui-validatebox" />${dict.name}
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>赔偿者：</td>
				<td>
					<input name="compensator" type="text" value="${carFaultHandle.compensator}" class="easyui-validatebox"/>
				</td>
				<td style="padding-left: 95px">得到赔偿款：</td>
				<td><input name="getMoney" type="text" value="${carFaultHandle.get_money}" class="easyui-validatebox" /></td>
			</tr>
			<tr>
				<td>经手办理人：</td>
				<td><input name="handleBy"  class="easyui-validatebox" value="${carFaultHandle.handle_by}" /></td>
				<td style="padding-left: 95px">出款门店：</td>
				<td>
					<select class="easyui-combobox" name="provideShop"  style="width:140px;">
						<c:forEach var="dict" items="${carShops}">
							<option value="${dict.id}">${dict.shopName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>故障日期：</td>
				<td>
					<input name="faultTime" type="text" value="${carFaultHandle.fault_time}" class="easyui-datebox" />
				</td>
				<td style="padding-left: 95px">修理日期：</td>
				<td><input name="repairTime" type="text" value="${carFaultHandle.repair_time}" class="easyui-datebox" /></td>
			</tr>
			<tr>
				<td>修理费用：</td>
				<td><input name="repairMoney" type="text" value="${carFaultHandle.repair_money}" class="easyui-validatebox"/></td>
			</tr>
			<tr>
				<td>故障描述：</td>
				<td colspan="3"><textarea  style="width: 300px;height: 100px;"  name="faultDescr" value="${carFaultHandle.fault_descr}" class="easyui-ueditor"></textarea></td>
			</tr>
			<tr>
				<td>备注：</td>
				<td  colspan="3"><textarea style="width: 300px;height: 100px;" name="remark" value="${carFaultHandle.remark}" class="easyui-ueditor"></textarea></td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
    $(function(){
        var componengs = '${componengs}';
        if(componengs){
            componengs = eval(componengs);
            for(var i=0;i<componengs.length;i++){
                $("#faultComponengt"+componengs[i]).attr("checked","true");
            }
        }
        $('#mainform').form({
            onSubmit: function(){
                var isValid = $(this).form('validate');
                return isValid;	// 返回false终止表单提交
            },
            success:function(data){
                successTip(data,carFaultHandle,d);
            }
        });
    });

</script>
</body>
</html>