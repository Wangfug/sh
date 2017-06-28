<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/activity/${action}" method="post">
		<input type = "hidden" name = "id" id = "id" value = "${activity.id}">
		<table  class="formTable" cellpadding="15">

			<tr>
				<td  style="width: 85px;padding-left: 25px">活动主标题：</td>
				<td colspan="3">
					<textarea style="width: 100%" name="mainTitle" class="easyui-validatebox">${activity.mainTitle}</textarea>
				</td>
			</tr>
			<tr>
				<td  style="width: 85px;padding-left: 25px">活动副标题：</td>
				<td colspan="3">
					<textarea style="width: 100%" name="subTitle" class="easyui-validatebox">${activity.subTitle}</textarea>
				</td>
			</tr>
			<tr>
				<td  style="width: 85px;padding-left: 25px" >活动类型：</td>
				<td>
					<select name="activityType" onchange = "showCoupon(this);">
						<c:forEach var="dict" items="${activityType}">
							<c:if test="${dict.code eq activity.activityType}"><option value="${dict.code}" selected>${dict.name}</option></c:if>
							<c:if test="${dict.code ne activity.activityType}"><option value="${dict.code}">${dict.name}</option></c:if>
						</c:forEach>
					</select>
				</td>
				<td style="width: 85px;padding-left: 40px">活动状态：</td>
				<td  style="padding-top: 10px;padding-bottom: 10px;">
					<select name="state">
						<c:forEach var="dict" items="${activityState}">
							<c:if test="${dict.code eq activity.state}"><option value="${dict.code}" selected>${dict.name}</option></c:if>
							<c:if test="${dict.code ne activity.state}"><option value="${dict.code}">${dict.name}</option></c:if>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td style="width: 85px;padding-left: 25px">活动优先级：</td>
				<td>
					<select name="activitySort">
						<c:forEach begin="1" end="5"  varStatus="status">
							<c:if test="${status.index eq activity.activitySort}">
								<option value="${status.index}" selected>${status.index}级</option>
							</c:if>
							<c:if test="${status.index ne activity.activitySort}">
								<option value="${status.index}" >${status.index}级</option>
							</c:if>

						</c:forEach>
					</select>
				</td>
				<td>是否推送到主页：</td>
				<td >
					<select name="mainPage">
						<c:if test="${activity.mainPage ne 1}">
							<option value="0">不推送</option>
							<option value="1">推送</option>
						</c:if>
						<c:if test="${activity.mainPage eq 1}">
							<option value="0">不推送</option>
							<option value="1" selected>推送</option>
						</c:if>
					</select>
				</td>
			</tr>

			<c:if test="${activity.activityType eq 90002 and !empty activity.coupon}">
				<input type="hidden" name = "couponId"  id = "couponId" value = "${activity.coupon}">
				<tr id = "coupon">
					<td style="width: 85px;padding-left: 25px">优惠券1：</td>
					<td>
						<button type = "button"  id = "addDiscount">修改</button>
					</td>
				</tr>
			</c:if>
			<c:if test="${activity.activityType eq 90002 and empty activity.coupon}">
				<input type="hidden" name = "couponId"  id = "couponId">
				<tr id = "coupon">
					<td style="width: 85px;padding-left: 25px">优惠券2：</td>
					<td>
						<button type = "button"  id = "addDiscount">添加</button>
					</td>
				</tr>
			</c:if>
			<c:if test="${activity.activityType ne 90002}">
				<input type="hidden" name = "couponId" id = "couponId">
				<tr id = "coupon" style="display:none">
					<td style="width: 85px;padding-left: 25px" id = "discount66">优惠券3：</td>
					<td>
						<button type = "button"  id = "addDiscount">添加</button>
					</td>
				</tr>
			</c:if>
			<%--<c:if test="${activity.activityType ne 90002}">--%>
				<%--<tr id = "coupon" style = "display:none;">--%>
					<%--<td style="width: 85px;padding-left: 25px">优惠券：</td>--%>
					<%--<td>--%>
						<%--<button type = "button" id = "addDiscount">添加</button>--%>

					<%--</td>--%>
				<%--</tr>--%>
			<%--</c:if>--%>

			<tr>
				<td style="width: 85px;padding-left: 25px">活动位置：</td>
				<td>
					<%--<select id="cmbProvince" name="activityPosition"></select>--%>
					<select name="activityPosition" class="easyui-combobox" >
						<c:forEach var="city" items="${citysForACT}">
							<%--<option value="${city}" selected>${city}</option>--%>
							<c:if test="${activity.activityPosition eq city}">
								<option value="${city}" selected>${city}</option>
							</c:if>
							<c:if test="${activity.activityPosition ne city}">
								<option value="${city}">${city}</option>
							</c:if>
						</c:forEach>
					</select>
					<%--<select id="cmbArea" name="activityPosition"></select>--%>
				</td>
				<td style="width: 100px;padding-left: 40px">起租价格(元/天)：</td>
				<td >
						<input name="price" type="text" value="${activity.price}" class="easyui-validatebox"
							   placeholder = "租车类型选填" data-options ="validType:'money'"/>
				</td>
			</tr>
			<tr>
				<td style="width: 85px;padding-left: 25px">开始时间：</td>
				<td>
					<input name="activityStart" type="text" value="${activity.activityStart}" class="easyui-datetimebox" />
				</td>
				<td style="padding-left: 40px">结束时间：</td>
				<td><input name="activityEnd" type="text" value="${activity.activityEnd}" class="easyui-datetimebox" /></td>
			</tr>
			<tr>
				<td style="width: 85px;padding-left: 25px">活动内容：</td>
				<td  colspan="3">
					<textarea rows="8" cols="41" name="content" class="easyui-ueditor">${activity.content}</textarea>
				</td>
			</tr>
			<tr>
				<td style="width: 85px;padding-left: 25px">备注：</td>
				<td  colspan="3"><textarea style="width: 100%"  name="activityRemark" value="" class="easyui-validatebox">${activity.activityRemark}</textarea></td>
			</tr>
			<tr>
				<td style="width: 85px;padding-left: 25px">活动图片：</td>
				<td  colspan="3"><button type = "button" onclick = "uploadImg();">上传图片</button></td>
			</tr>
			<tr>
				<td name="attachment" style="width: 85px;padding-left: 25px">图片预览：</td>
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
<div id="discountOO"></div>
<!-- 上传文件 -->
<script src="${ctx}/static/js/ajaxfileupload.js"></script>
<script type="text/javascript">
var  actId = "${activity.id}";
var  disId = "${activity.coupon}";
var dis;
var filepath1 = '${activity.attachment}';
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
//    var editor;

$(function(){

$('#mainform').form({
onSubmit: function(){
    var isValid = $(this).form('validate');
    return isValid;	// 返回false终止表单提交
},
success:function(data){
    successTip(data,activity,d);
}
});
//    addressInit('cmbProvince', 'cmbCity', 'cmbArea');
    $("#addDiscount").on("click",addDiscount);
});

var index;
//上传图片
var imgPath="";
var upload = function(){
    $.ajaxFileUpload
    (
        {
            url: "${ctx}/system/fileupload/upload?lunbo=lunbo",
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
    function showCoupon(obj){
        var val = $(obj).val();
        if("90002"==val){
            if(disId){
                $("#addDiscount").html("修改");
			}else{
                $("#addDiscount").html("添加");
			}
			$("#coupon").show();
		}else{
            $("#coupon").val("");
            $("#coupon").hide();
		}
	}

	function addDiscount(){
        dis=$("#discountOO").dialog({
            title: '编辑优惠券信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/customerDiscount/create?id='+disId,
            maximizable:true,
            modal:true,
            buttons:[{
                text:'确认',
                handler:function(){
                    $('#mainform1').submit();
                }
            },{
                text:'取消',
                handler:function(){
                    dis.panel('close');
                }
            }]
        });
    }

</script>
</body>
</html>