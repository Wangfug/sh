<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/carRentPrice/${action}" method="post">
		<input type="hidden" name="id" value="${carRentPrice.id }" />
	<table  class="formTable">

		<tr>
			<td>品牌：</td>
			<td>
				<select  name="brand"  style="width:140px;" onchange="toModel()" id="brand">
					<c:forEach var="mybrand" items="${brands}">
						<c:if test="${mybrand eq carRentPrice.brand}"><option value="${mybrand}" selected>${mybrand}</option></c:if>
						<c:if test="${mybrand ne carRentPrice.brand}"><option value="${mybrand}">${mybrand}</option></c:if>
					</c:forEach>
				</select>
			<%--<input name="brand" type="text" value="${carRentPrice.brand }" class="easyui-validatebox" required="required"/>--%>
			</td>
			<td style="padding-left: 40px;">型号：</td>
			<td>
				<select class="" name="model"  style="width:140px;" id="model">
				</select>
				<%--<input name="model" type="text" value="${carRentPrice.model }" class="easyui-validatebox" required="required"/>--%>
			</td>
		</tr>
		<%--<tr>
			<td>城市：</td>
			<td><input name="city" type="text" value="${carRentPrice.city }" class="easyui-validatebox" required="required"/></td>
			<td style="padding-left: 40px;">区：</td>
			<td><input name="area" type="text" value="${carRentPrice.area }" class="easyui-validatebox" required="required"/></td>
		</tr>--%>
		<tr>
			<td>日租价格：</td>
			<td>
				<input name="priceByDay" type="text" value="${carRentPrice.priceByDay }" class="easyui-validatebox" required="required"/>元
			</td>
			<td style="padding-left: 40px;">时租价格：</td>
			<td><input name="priceByHour" type="text" value="${carRentPrice.priceByHour }" class="easyui-validatebox" required="required"/>元</td>
		</tr>

		<tr>
			<td>基本保险费：</td>
			<td>
				<input name="feeInsurance" type="text" value="${carRentPrice.feeInsurance }" class="easyui-validatebox" required="required"/>元
			</td>
			<td style="padding-left: 40px;">不计免赔险：</td>
			<td><input name="feeDeductible" type="text" value="${carRentPrice.feeDeductible }" class="easyui-validatebox" required="required"/>元</td>
		</tr>


		<tr>
			<td>门店：</td>
			<td>
				<select class="easyui-combobox" name="carShop"  style="width:140px;">
					<option value="">总部统一价格</option>
					<c:forEach var="carShop" items="${carShops}">
						<c:if test="${carShop.id eq carRentPrice.carShop}"><option value="${carShop.id}" selected>${carShop.shopName}</option></c:if>
						<c:if test="${carShop.id ne carRentPrice.carShop}"><option value="${carShop.id}">${carShop.shopName}</option></c:if>
					</c:forEach>
				</select>
				<%--<input name="carShop"  class="easyui-validatebox" value="${carRentPrice.carShop }"  required="required"/>--%>
			</td>
			<td>是否推荐到热门车型：</td>

			<td>
				<select class="" name="hotcar"  style="width:140px;">
						<c:if test="${'0' eq carRentPrice.hotcar}">
							<option value="0" selected>否</option>
							<option value="1" >是</option>
						</c:if>
						<c:if test="${'1' eq carRentPrice.hotcar}">
							<option value="1" selected>是</option>
							<option value="0">否</option>
						</c:if>
					<c:if test="${null eq carRentPrice.hotcar}">
						<option value="0" selected>否</option>
						<option value="1" >是</option>
					</c:if>

				</select>
			</td>
		</tr>

		<tr>
			<td>活动图片：</td>
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
<script src="${ctx}/static/js/ajaxfileupload.js"></script>
<script type="text/javascript">
$(function(){
    toModel();
$('#mainform').form({
onSubmit: function(){
    var isValid = $(this).form('validate');
    return isValid;	// 返回false终止表单提交
},
success:function(data){
    successTip(data,carRentPrice,d);
}
});
});

function toModel(){
    var htm="";
    var brand = $("#brand").val();
    var model = '${models}';
    var oneModel = '${carRentPrice.model}';
	if(model){
        model = eval(model);
        for(var a=0;a<model.length;a++){
            var obj = model[a];
            if(brand == obj.brand){
					if(oneModel == obj.model){
                        htm+="<option value="+obj.model+" selected>"+obj.model+"</option>";
					}else{
                        htm+="<option value="+obj.model+">"+obj.model+"</option>";
					}

            }
        }
        $("#model").html(htm);

    }
}

var filepath1 = '${carRentPrice.img}';
if(filepath1){
    var filepath2 = filepath1.split(",");
    var imgPath = filepath2[0];
    $("#images").html("");
    var htm="";
    htm+="<div style='position: relative; width: 68px; float: left; margin-left: 5px'>";
    htm+="<input type = 'hidden' value ='"+imgPath+"' name = 'img'>";
    htm+="<img src='"+imgPath+"' style='width:68px;height:68px;vertical-align: middle;' onmouseover='toBig(this)'onmouseout='toSmall();' >";
    htm+="<img src='${ctx}/static/images/remove.png'  style='position: absolute;top:0px;right:0px;width:20px;' imgsrc='"+imgPath+"' onclick='deleteImg(this)'>";
    htm+="</div>";
    $("#images").html(htm);
};

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
                    if(data.data == "bili"){
                        alert("上传失败，图片上传比例为16/9");
                        return false;
                    }
                    imgPath=data.data;
                    htm+="<div style='position: relative; width: 68px; float: left; margin-left: 5px'>";
                    htm+="<input type = 'hidden' value ='"+imgPath+"' name = 'img'>";
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