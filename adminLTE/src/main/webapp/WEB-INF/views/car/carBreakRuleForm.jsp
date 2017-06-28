<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/carBreakRule/${action}" method="post">
	<table  class="formTable">

		<tr>
			<td  style="width: 75px;">违章车辆：</td>
			<td>
				<input type="hidden" name="id" value="${carBreakRule.id}" />

				<select class="easyui-combobox" name="carId"  style="width:140px;">
					<c:forEach var="dict" items="${cars}">
						<c:if test="${dict.id eq carBreakRule.car_id}"><option value="${dict.id}" selected>${dict.car_code}</option></c:if>
						<c:if test="${dict.id ne carBreakRule.car_id}"><option value="${dict.id}">${dict.car_code}</option></c:if>
					</c:forEach>
				</select>

				<%--<input name="carCode" type="text" value="${carBreakRule.car_code}" class="easyui-validatebox" required="required" readonly="readonly"/>--%>
			</td>
			<td  style="width: 85px;padding-left: 105px">状态：</td>
			<td>
				<%--<input name="state" type="text" value="${carBreakRule.state}" class="easyui-validatebox" required="required"/>--%>

				<select class="easyui-combobox" name="state"  style="width:140px;">
						<c:if test="${'1'eq carBreakRule.state}"><option value="1" selected>已处理</option><option value="2">未处理</option></c:if>
						<c:if test="${'1'ne carBreakRule.state}"><option value="1">已处理</option><option value="2" selected>未处理</option></c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td >违章单号：</td>
			<td>
				<input name="illegalNo"  class="easyui-validatebox" value="${carBreakRule.illegal_no}" required="required"/>
			</td>
			<td style="width: 85px;padding-left: 95px">订单号：</td>
			<td><input name="orderNo"  class="easyui-validatebox" value="${carBreakRule.order_no}" required="required"/></td>
		</tr>
		<tr>
			<td>违章扣分：</td>
			<td><input name="illegalDeduction"  class="easyui-validatebox" value="${carBreakRule.illegal_deduction}" /></td>
			<td  style="width: 85px;padding-left: 95px">违章罚款：</td>
			<td><input name="violationFine" type="text" value="${carBreakRule.violation_fine}" class="easyui-validatebox" required="required"/></td>
			<%--<td   style="width: 85px;padding-left: 95px">违章司机：</td>
			<td><input name="customerId"  class="easyui-validatebox" value="${carBreakRule.customer_id}" /></td>--%>
		</tr>
		<tr>
		</tr>
		<tr>
			<td >违章时间：</td>
			<td><input name="illegalTime"  class="easyui-datetimebox" value="${carBreakRule.illegal_time}" required="required"/></td>
			<td  style="width: 85px;padding-left: 95px">处理门店：</td>
			<td>
				<select class="easyui-combobox" name="dealShop">
					${carShops}
					<c:forEach var="carShop" items="${carShops}">
						<c:if test="${carShop.id eq carBreakRule.deal_shop}"><option value="${carShop.id}" selected>${carShop.shopName}</option></c:if>
						<c:if test="${carShop.id ne carBreakRule.deal_shop}"><option value="${carShop.id}">${carShop.shopName}</option></c:if>
					</c:forEach>
				</select>

			</td>

		</tr>
		<tr>
			<td>违章地点：</td>
			<td colspan="3">
				<textarea name="illegalPosition"  style = "width:100%;" required="required">${carBreakRule.illegal_position}</textarea>
				<%--<input name="illegalPosition"  class="easyui-validatebox" value="${carBreakRule.illegal_position}" />--%>
			</td>
		</tr>
		<tr>
			<td>违章内容：</td>
			<td colspan="3">
				<textarea name="illegalContent" style = "width:100%;" required="required">${carBreakRule.Illegal_content}</textarea>
				<%--<input name="illegalContent" type="text" value="${carBreakRule.Illegal_content}" class="easyui-validatebox" required="required"/>--%>
			</td>
		</tr>
		<tr>
			<td align = "center">备注：</td>
			<td  colspan="3"><textarea style = "width:100%;" name="remark" value="" class="easyui-ueditor">${carBreakRule.remark}</textarea></td>
		</tr>

		<tr>
			<td>附件图片：</td>
			<td  colspan="3"><button type = "button" onclick = "uploadImg();">上传</button></td>
		</tr>
		<tr>
			<td>图片预览：</td>
			<td  colspan="3">
				<div id = "images"></div>
				<div id = "fileHome"></div>
				<%--<div id = "imgView" style="display: none;">--%>
					<%--<img  id='bigImg' src='${ctx}/static/images/remove.png' style='width:300px;height:200px;position: absolute;left:200px;top:150px;z-index: 99999'>--%>
				<%--</div>--%>
			</td>
		</tr>

</table>
</form>
</div>
<!-- 上传文件 -->
<script src="${ctx}/static/js/ajaxfileupload.js"></script>
<script type="text/javascript">
    var filepath1 = '${activity.attachment}';
    if(filepath1){
        var filepath2 = filepath1.split(",");
        var imgPath = filepath2[0];
        $("#images").html("");
        var htm="";
        htm+="<div style='position: relative; width: 68px; float: left; margin-left: 5px'>";
        htm+="<input type = 'hidden' value ='"+imgPath+"' name = 'attachment'>";
        htm+="<img src='"+imgPath+"' style='width:68px;height:68px;vertical-align: middle;'  >";
        htm+="<img src='${ctx}/static/images/remove.png'  style='position: absolute;top:0px;right:0px;width:20px;' imgsrc='"+imgPath+"' onclick='deleteImg(this)'>";
        htm+="</div>";
        $("#images").html(htm);
    }
$(function(){
    $('#mainform').form({
        onSubmit: function(){
            var isValid = $(this).form('validate');
            return isValid;	// 返回false终止表单提交
        },
        success:function(data){
            successTip(data,CarBreakRule,d);
        }
    });
});
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
                        htm+="<img src='"+imgPath+"' style='width:68px;height:68px;vertical-align: middle;' >";
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
    function showOrder(obj){
        var orderNo = $(obj).val();
//        var res = confirm(orderNo);
//        if(res){
//            alert("确定");
//		}else{
//            alert("取消");
//		}
		$.ajax({
			url:"",
			type:"post",
			dataType:"json",
			success:function(){

			},
            error:function(){
				alert(error)
            }
		});
	}
</script>
</body>
</html>