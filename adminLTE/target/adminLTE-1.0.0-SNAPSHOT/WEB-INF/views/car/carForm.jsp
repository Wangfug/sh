<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/car/${action}" method="post">
	<table  class="formTable">
		<tr>
			<td>车牌号：</td>
			<td>
			<input type="hidden" name="id" value="${car.id }" />
			<input name="carCode" type="text" value="${car.carCode }" class="easyui-validatebox" required="required"/>
			</td>
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
			<%--<td>经营种类：</td>
			<td><input name="belong" type="text" value="${car.belong }" class="easyui-validatebox" required="required"/></td>--%>
			<td>服务状态：</td>
			<td><select name="state">
					<c:forEach var="dict" items="${dictsForCar}">
						<c:if test="${dict.code eq car.state}"><option value="${dict.code}" selected>${dict.name}</option></c:if>
						<c:if test="${dict.code ne car.state}"><option value="${dict.code}">${dict.name}</option></c:if>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>入库时间：</td>
			<td>
				<input name="intime"  value="${car.intime }"  class="easyui-datetimebox" required="required"/>
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
			<c:if test="${fn:startsWith(user.jobCode,'MG') or fn:startsWith(user.jobCode,'KF')}">
				<td>所在门店：</td>
				<td>
					<select class="easyui-combobox" name="carShop">
						<c:forEach var="dict" items="${carShops}">
							<option value="${dict.id}">${dict.shopName}</option>
						</c:forEach>
					</select>
				</td>
			</c:if>
		</tr>
		<tr>
			<td>备注1：</td>
			<td colspan="3"><textarea  style="width: 100%;height: 100px;"  name="remark1" value="${car.remark1 }" class="easyui-ueditor">${car.remark1 }</textarea></td>
		</tr>
    	<tr>
			<td>备注2：</td>
			<td  colspan="3"><textarea style="width: 100%;height: 100px;" name="remark2" value="${car.remark2 }" class="easyui-ueditor">${car.remark2 }</textarea></td>
		</tr>
		<tr>
			<td>备注3：</td>
			<td colspan="3"><textarea style="width: 100%;height: 100px;"  name="remark3" value="${car.remark3 }" class="easyui-ueditor">${car.remark3 }</textarea></td>
		</tr>
		<tr>
			<td>车辆图片：</td>
			<td  colspan="3"><button type = "button" onclick = "uploadImg();">上传图片</button></td>
		</tr>
		<tr>
			<td>图片预览：</td>
			<td  colspan="3">
				<div id = "images"></div>
				<div id = "fileHome"></div>
				<div id = "imgView" style="display: none;">
					<img  id='bigImg' src='${ctx}/static/images/remove.png' style='width:300px;height:200px;position: absolute;left:200px;top:150px;z-index: 99999'>
				</div>
			</td>
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
var filepath1 = '${car.attachment}';
console.log(filepath1)
if(filepath1){
    var filepath2 = filepath1.split(",");
    var imgPath = filepath2[0];
    $("#images").html("");
    var htm="";
    htm+="<div style='position: relative; width: 68px; float: left; margin-left: 5px'>";
    htm+="<input type = 'hidden' value ='"+imgPath+"' name = 'attachment'>";
    htm+="<img src='"+imgPath+"' style='width:68px;height:68px;vertical-align: middle;' onmouseover='toBig(this)'onmouseout='toSmall();' >";
    htm+="<img src='${ctx}/static/images/remove.png'  style='position: absolute;top:0px;right:0px;width:20px;' imgsrc='"+imgPath+"' onclick='deleteImg(this)'>";
    htm+="</div>";
    $("#images").html(htm);
}
var index;
//上传图片
var imgPath="";
var upload = function(){
    $.ajaxFileUpload
    (
        {
            url: "${ctx}/system/fileupload/upload",
            secureuri: false,
            fileElementId: 'file'+index,
            dataType:'json',
            success: function (data)
            {
                $("#fileHome").html("");
                var htm="";
                if(data.status==1){
                    imgPath=data.data;
                    htm+="<div style='position: relative; width: 68px; float: left; margin-left: 5px'>";
                    htm+="<input type = 'hidden' value ='"+imgPath+"' name = 'attachment'>";
                    htm+="<img src='"+imgPath+"' style='width:68px;height:68px;vertical-align: middle;' onmouseover='toBig(this)'onmouseout='toSmall();' >";
                    htm+="<img src='${ctx}/static/images/remove.png'  style='position: absolute;top:0px;right:0px;width:20px;' imgsrc='"+imgPath+"' onclick='deleteImg(this)'>";
                    htm+="</div>";
                    $("#images").html(htm);
                }else{
                    alert("上传失败");
                }
            },
            error: function (data)
            {
                alert("连接失败");
            }
        }
    )
}
function uploadImg(){
    var forms = $("form");
    for(var i=0;i<forms.length;i++){
        if(forms.eq(i).attr("id")!="searchFrom" && forms.eq(i).attr("id")!="mainform"){
            forms.eq(i).remove();
        }
    }
    index=Math.round(Math.random()*1000000000);
    var fileInput=$('<input type="file" id="file'+index+'"  name="file" />');
    fileInput.hide();
    fileInput.appendTo($("#fileHome"));
    fileInput.trigger("click");
    fileInput.on('change',function(){
        upload();
    });
}
function toBig(th){
    $("#bigImg").attr("src",$(th).attr("src"));
    $("#imgView").show();
}
function toSmall(){
    $("#imgView").hide();
}
function deleteImg(th) {
    var path = $(th).attr("imgsrc");
//        var id = $(th).attr("id");
    $.ajax({
        type: "get",
        url: "${ctx}/system/fileupload/deleteOne",
        dataType: "JSON",
        data: {
            "sPath": path,
        },
        success: function(data) {
            if(data ==true || data=="true"){
                $(th).parent().remove();
                alert("删除成功");
            }else{
                alert("删除失败");
            }
        },
        error: function() {
        }
    });
}
</script>
</body>
</html>