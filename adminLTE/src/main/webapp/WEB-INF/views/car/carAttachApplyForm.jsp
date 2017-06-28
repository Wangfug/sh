<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body>
<div style="padding-bottom: 20px">
	<form id="mainform" action="${ctx}/web/car/${action}" method="post" style = "margin-left:30px; " >
		<input type = "hidden" name = "id" value = "${id}"/>
		<h4>车辆信息</h4>
	<table  class="formTable">
		<tr>
			<td>车牌号：</td>
			<td>
			<input name="carCode" id = "carCode" type="text" value="" class="easyui-validatebox" />
			</td>
			<td style="padding-left: 40px;" >所在城市：</td>
			<td>
				<input name="city" id = "attachCity" type="text" value="" class="easyui-validatebox" />
			</td>
		</tr>
		<tr>
			<td>行驶里程：</td>
			<td><input name="finalMileage" type="text" id = "finalMileage" value="" class="easyui-validatebox" /></td>
			<td style="padding-left: 40px;">发动机号：</td>
			<td><input name="engineNo" id = "engineNo" type="text" value="" class="easyui-validatebox"/></td>
		</tr>
		<tr>
			<td>车架号：</td>
			<td>
				<input name="frameNo" type="text" id = "frameNo" value="" class="easyui-validatebox" />
			</td>
			<td style="padding-left: 40px;">颜色：</td>
			<td><input name="color" type="text" id = "color" value="" class="easyui-validatebox" /></td>
		</tr>
		<tr>
			<td>购买日期：</td>
			<td><input name="buyTime"  class="easyui-datebox" value=""  id = "buyTime"/></td>
			<td style="padding-left: 40px;">品牌：</td>
			<td><input name="brand" id = "brand" type="text" value="" class="easyui-validatebox"/></td>
		</tr>
		<tr>
			<td>型号：</td>
			<td>
				<input name="model" id = "model" type="text" value="" class="easyui-validatebox"  />
			</td>
			<td style="padding-left: 40px;">车厢数：</td>
			<td><input name="cartonNumber" id = "cartonNumber" type="text" value="" class="easyui-validatebox" /></td>
		</tr>
		<tr>
			<td>车型：</td>
			<td colspan="3" style="padding-top: 10px;padding-bottom: 10px;" id = "carType">
				<c:forEach var="dict" items="${dicts}">
					<input name="carType" id = "carType${dict.code}" type="checkbox" value="${dict.code}" class="easyui-validatebox" />	${dict.name}
				</c:forEach>

			</td>

		</tr>
		<tr>
			<td >出厂日期：</td>
			<td><input name="leaveFactoryTime" id = "leaveFactoryTime" value=""  class="easyui-datebox" /></td>
			<td>备注：</td>
			<td><input name="remark" type="text" id = "remark" value="" class="easyui-validatebox" /></td>

		</tr>
</table>
		<h4>车辆检查情况</h4>
		<table  class="formTable" id ="carCheck" cellpadding="15">

		</table>
		<div style="margin-top:20px; margin-left:20px; " id = "hasExamine">
			<button type = "button" onclick = "checkPass(0)">审核通过</button>
			<button type = "button" style=" margin-left:20px; " onclick = "checkPass(1)">审核不通过</button>
		</div>
</form>
</div>
<script type="text/javascript">
	var type = "${type}";
	if(type=="hide"){
        $("#hasExamine").hide();
	}
    var carInfo = '${carAttachApply.carInfo}';
//    console.log(carInfo);
	var driveLicImg = '${carAttachApply.driveLicImg}';
	var carAttachDetail = '${carAttachApply.carAttachDetail}';
	var customerId = '${carAttachApply.customerId}';
    var dicts = '${dicts}';
	if(carInfo){
//        console.log(carInfo)
        carInfo = eval("("+carInfo+")");
//        console.log(carInfo)
		$("#carCode").val(carInfo.carCode);cartonNumber
        $("#brand").val(carInfo.brand);
        $("#cartonNumber").val(carInfo.cartonNumber);
        $("#color").val(carInfo.color);
        $("#buyTime").val(carInfo.createTime);
        $("#engineNo").val(carInfo.engineNo);
        $("#finalMileage").val(carInfo.finalMileage);
        $("#frameNo").val(carInfo.frameNo);
        $("#leaveFactoryTime").val(carInfo.leaveFactoryTime);
        $("#model").val(carInfo.model);
        $("#remark").val(carInfo.remark);
        $("#attachCity").val(carInfo.attachCity);
		var carType11 = carInfo.carType;
		if(carType11){
//            carType11 = carType11.split(",");
            for(var i=0;i<carType11.length;i++){
                $("#carType"+carType11[i]).attr("checked",true);
			}
		}

        if(carAttachDetail){
//            console.log(carAttachDetail)
            carAttachDetail = eval("("+carAttachDetail+")");
//            console.log(carAttachDetail)
			var text = carAttachDetail.text;
//            console.log(text)
			var textValue = text.value;
			for(var i=0;i<textValue.length;i++){
                if(i%2==1){
                    var tr = $("<tr></tr>");
                    tr.append("<td>"+textValue[i-1].name+"</td><td>"+textValue[i-1].pass+"</td>");
                    tr.append("<td>"+textValue[i].name+"</td><td>"+textValue[i-1].pass+"</td>");
                    $("#carCheck").append(tr);
				}
			}

            var image = carAttachDetail.image;
            var imgValue = image.value;
//            console.log(imgValue)
            for(var i=0;i<imgValue.length;i++){
//                if(i%2==1){
                    var tr = $("<tr></tr>");
				tr.append("<td>"+imgValue[i].name+"</td><td><img src = '"+imgValue[i].filepath+"' style = 'width:200px;height:125px'/></td>");
                $("#carCheck").append(tr);
//                }
            }
        }
	}


	function checkPass(flag){
	    $.ajax({
			url:"${ctx}/web/carAttachApply/texamineResult?type="+flag,
			type:"post",
//			dataType:"json",
			data:$("#mainform").serialize(),
			success:function(data){
			    if(data=="success"){
			        alert("审核成功");
                    CarAttachApply.datagrid('reload');
                    d.panel('close');
                }else{
                    alert("审核失败");
                    CarAttachApply.datagrid('reload');
                    d.panel('close');
                }
			},
			error:function(){
					alert("审核失败");
                	d.panel('close');
			}
		});
	}


</script>
</body>
</html>