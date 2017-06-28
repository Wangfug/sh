<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/customer/${action}" method="post">
		<input type="hidden" name="id" value="${customer.id }" id="customerId"/>
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align = "right">姓名：</td>
			<td>
				<input name="name" type="text" value="${customer.name }" class="easyui-validatebox"  readonly/>
			</td>
			<td align = "right">联系电话：</td>
			<td>
				<input name="mobilePhone" type="text" value="${customer.mobile_phone }" class="easyui-validatebox"  readonly/>
			</td>
		</tr>
		<tr>
			<td align = "right">个人邮箱：</td>
			<td>
				<input name="email" type="text" value="${customer.email }" class="easyui-validatebox"  readonly/>
			</td>
			<td align = "right">余额：</td>
			<td>
				<input name="balance" type="text" value="${customer.balance }" class="easyui-validatebox" readonly/>
			</td>
		</tr>
		<tr>
			<td align = "right">证件类型：</td>
			<td>
				<input name="credentialtype" type="text" value="${customer.credentialtype }" class="easyui-validatebox" readonly/>
			</td>
			<td align = "right">证件号：</td>
			<td>
				<input name="credentialcode" type="text" value="${customer.credential_code }" class="easyui-validatebox"  readonly/>
			</td>
		</tr>
		<tr>
			<td align = "right">驾驶证档案编号：</td>
			<td>
				<input name="drivingLicence" type="text" value="${customer.file_number }" class="easyui-validatebox" readonly />
			</td>

			<td align = "right">准驾车型：</td>
			<td>
				<input name="quasi_driving_type" type="text" value="${customer.quasi_driving_type}" class="easyui-validatebox" readonly/>
			</td>
		<tr>
			<td align = "right">领证日期：</td>
			<td>
				<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
				<input name="balance" type="text" value="${fn:substring(customer.get_time, 0, 19)}" readonly/>
			</td>
		</tr>

		<tr>
			<td align = "right">身份证照片1：</td>
			<td  colspan="3">
				<div id = "images1"></div>
				<div id = "imgView1" style="display: none;">
					<img id='bigImg1' src='' style='width:600px;height:400px;position: absolute;left:80px;top:50px;z-index: 99999'>
				</div>
			</td>
		</tr>

		<tr>
			<td align = "right">身份证照片2：</td>
			<td  colspan="3">
				<div id = "images2"></div>
			</td>
		</tr>

		<tr>
			<td align = "right">驾驶证照片1：</td>
			<td  colspan="3">
				<div id = "images3"></div>
			</td>
		</tr>

		<tr>
			<td align = "right">驾驶证照片2：</td>
			<td  colspan="3">
				<div id = "images4"></div>
			</td>
		</tr>

		<tr>
			<td align = "right">实时照片：</td>
			<td  colspan="3">
				<div id = "images5"></div>
			</td>
		</tr>

</table>
		<c:if test="${customer.state ne '2'}">

			<c:if test="${customer.state eq '3'}">
			<div>
				<span>原因</span>
				<input id="reason" type="text" class="easyui-validatebox" style="width:400px" value="${customer.auth_reason}"/>
			</div>
			</c:if>

			<c:if test="${customer.state ne '3'}">
				<div>
					<span>原因（如果不通过，请填写原因）</span>
					<input id="reason" type="text" class="easyui-validatebox" style="width:400px"/>
				</div>

		<div style="margin-top:50px; margin-left:20px; margin-bottom:50px;" id = "hasExamine">
			<button type = "button" onclick = "checkPass(2)">审核通过</button>
			<button type = "button" style=" margin-left:20px; " onclick = "checkPass(3)">审核不通过</button>
			<%--<button type = "button" style=" margin-left:20px; " onclick = "checkPass(4)">加入黑名单</button>--%>
		</div>
			</c:if>

		</c:if>

</form>
</div>
<script type="text/javascript">
//$(function(){
//$('#mainform').form({
//onSubmit: function(){
//    var isValid = $(this).form('validate');
//    return isValid;	// 返回false终止表单提交
//},
//success:function(data){
//    successTip(data,customer,d);
//}
//});
//});

var filepath1 = '${allImg.idCardImg}';
if(filepath1){
    var filepath2 = filepath1.split(",");
    var imgPath = filepath2[0];
    $("#images1").html("");
    var htm="";
    htm+="<div style='position: relative; width: 68px; float: left; margin-left: 5px'>";
    htm+="<input type = 'hidden' value ='"+imgPath+"' name = 'attachment'>";
    htm+="<img src='"+imgPath+"' style='width:200px;height:100px;vertical-align: middle;' onmouseover='toBig(this)'onmouseout='toSmall();' >";
    htm+="</div>";
    $("#images1").html(htm);

    var imgPath2 = filepath2[1];
    $("#images2").html("");
    var htm1="";
    htm1+="<div style='position: relative; width: 68px; float: left; margin-left: 5px'>";
    htm1+="<input type = 'hidden' value ='"+imgPath2+"' name = 'attachment'>";
    htm1+="<img src='"+imgPath2+"' style='width:200px;height:100px;vertical-align: middle;' onmouseover='toBig(this)'onmouseout='toSmall();' >";
    htm1+="</div>";
    $("#images2").html(htm1);

}

var filepath3 = '${allImg.driveCardImg}';
if(filepath3) {
    var filepath4 = filepath3.split(",");
    var imgPath = filepath4[0];
    $("#images3").html("");
    var htm = "";
    htm += "<div style='position: relative; width: 68px; float: left; margin-left: 5px'>";
    htm += "<input type = 'hidden' value ='" + imgPath + "' name = 'attachment'>";
    htm += "<img src='" + imgPath + "' style='width:200px;height:100px;vertical-align: middle;' onmouseover='toBig(this)'onmouseout='toSmall();' >";
    htm += "</div>";
    $("#images3").html(htm);

    var imgPath2 = filepath4[1];
    $("#images4").html("");
    var htm1 = "";
    htm1 += "<div style='position: relative; width: 68px; float: left; margin-left: 5px'>";
    htm1 += "<input type = 'hidden' value ='" + imgPath2 + "' name = 'attachment'>";
    htm1 += "<img src='" + imgPath2 + "' style='width:200px;height:100px;vertical-align: middle;' onmouseover='toBig(this)'onmouseout='toSmall();' >";
    htm1 += "</div>";
    $("#images4").html(htm1);
}

var realImg = '${allImg.realImg}';
if(realImg) {
    $("#images5").html("");
    var htm = "";
    htm += "<div style='position: relative; width: 68px; float: left; margin-left: 5px'>";
    htm += "<input type = 'hidden' value ='" + realImg + "' name = 'attachment'>";
    htm += "<img src='" + realImg + "' style='width:200px;height:100px;vertical-align: middle;' onmouseover='toBig(this)'onmouseout='toSmall();' >";
    htm += "</div>";
    $("#images5").html(htm);
}

function toBig(th){
    $("#bigImg1").attr("src",$(th).attr("src"));
    $("#imgView1").show();
}
function toSmall(){
    $("#imgView1").hide();
}
function checkPass(flag){
    var id = $("#customerId").val();
    var reason = $("#reason").val();
    $.ajax({
        type:'get',
        url:"${ctx}/web/customer/auth?type="+flag+"&id="+id+"&reason="+reason,
        success: function(data){
            if("success"==data){
                successTip("操作成功");
                customer.datagrid('reload');
                d.panel('close');
			}else{
                successTip("操作失败");
                customer.datagrid('reload');
                d.panel('close');
			}
        }
    });
}

</script>
</body>
</html>