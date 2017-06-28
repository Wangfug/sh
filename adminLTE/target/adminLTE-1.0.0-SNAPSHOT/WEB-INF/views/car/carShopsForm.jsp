<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
	<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/carShops/${action}" method="post">
		<input type="hidden" name="id" value="${carShop.id }" />
	<table  class="formTable" cellpadding="15">
		<tr>
			<td align = "right">店名：</td>
			<td>
				<input name="shopName" type="text" value="${carShop.shopName }" class="easyui-validatebox" required="required"/>
			</td>
			<td align = "right">联系电话：</td>
			<td>
				<input name="phone" type="text" value="${carShop.phone }" class="easyui-validatebox" data-options="required:'required',validType:'phoneRex'"/>
			</td>
		</tr>
		<tr>
			<td align = "right">门店编码：</td>
			<td>
				<input name="shopCode" type="text" value="${carShop.shopCode }" class="easyui-validatebox" required="required"/>
			</td>
			<td align = "right">父级编码：</td>
			<td>
				<input name="parentCode" type="text" value="${carShop.parentCode }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
            <td align = "right">营业时间：</td>
            <td colspan = "3">
                <input name="yysj" type="text" value="${carShop.yysj }" class="easyui-validatebox" required="required" style="width: 100%"/>
            </td>
			<%--<td align = "right">营业开始时间：</td>
			<td>
				<input name="businessStart" type="text" value="${carShops.businessStart }" class="easyui-datetimebox" required="required"/>
			</td>
			<td align = "right">营业结束时间：</td>
			<td>
				<input name="businessEnd" type="text" value="${carShops.businessEnd }" class="easyui-datetimebox" required="required"/>
			</td>--%>
		</tr>
		<tr>
			<td align = "right">门店类型：</td>
			<td>
				<select class="easyui-combobox" name="shopType"  style="width:140px;">
					<c:forEach var="shopType" items="${dictTypesForCarShops}">
						<c:if test="${shopType.code eq carShop.shopType}"><option value="${shopType.code}" selected>${shopType.name}</option></c:if>
						<c:if test="${shopType.code ne carShop.shopType}"><option value="${shopType.code}">${shopType.name}</option></c:if>
					</c:forEach>
				</select>
			</td>
			<td align = "right">直线：</td>
			<td>
				<input name="stiffPhone" type="text" value="${carShop.stiffPhone }" class="easyui-validatebox" required="required"/>
			</td>
		</tr>
		<tr>
			<td align = "right">所在地区：</td>
			<td colspan="3">
				省:<select id="cmbProvince" name="province"></select>
				市:<select id="cmbCity" name="city"></select>
				区:<select id="cmbArea" name="area"></select>
			</td>
		</tr>
		<%--<tr>
			<td align = "right">店长：</td>
			<td>
				<select id="shopManager" name="shopManager">
					<c:forEach var="manager" items="${managersForCarShops}">
						<c:if test="${manager.id eq carShop.shopManager}"><option value="${manager.id}" selected>${manager.e_name}</option></c:if>
						<c:if test="${manager.id ne carShop.shopManager}"><option value="${manager.id}">${manager.e_name}</option></c:if>
					</c:forEach>
				</select>

			</td>
		</tr>--%>
		<tr>
			<td align = "right">详细地址：</td>
			<td colspan="3">
				<textarea name="address" id="address" style = "width:100%;">${carShop.address }</textarea>
				<%--<input name="remark" type="text" value="${carShops.remark }" class="easyui-validatebox" required="required"/>--%>
			</td>
		</tr>
		<tr>
			<td align = "right">坐标：</td>
			<td colspan="3">
				<button type = "button" onclick = "chooseMap();">选取</button>
				<input name="lon" id = "xpoint" type="text" value="${carShop.lon }" class="easyui-validatebox" required="required"/>
				<input name="lat" id = "ypoint" type="text" value="${carShop.lat }" class="easyui-validatebox" required="required"/>
			<%--<input name="remark" type="text" value="${carShops.remark }" class="easyui-validatebox" required="required"/>--%>
			</td>
		</tr>
		<tr>
			<td align = "right">备注：</td>
			<td colspan="3">
				<textarea name="remark" id="remark" style = "width:100%;">${carShop.remark}</textarea>
				<%--<input name="remark" type="text" value="${carShops.remark }" class="easyui-validatebox" required="required"/>--%>
			</td>
		</tr>
		<tr>
			<td align = "right">运营门店：</td>
			<td colspan="3">
				<select name="sellShop" id="sellShop">
					<c:if test="${carShop.sellShop eq 0}">
					<option value = "0" selected>不是</option>
					<option value = "1">是</option>
					</c:if>
					<c:if test="${carShop.sellShop eq 1}">
						<option value = "0">不是</option>
						<option value = "1" selected>是</option>
					</c:if>
					<c:if test="${carShop.sellShop ne 1 and carShop.sellShop ne 0 }">
						<option value = "0">不是</option>
						<option value = "1">是</option>
					</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<td align = "right">门店图片：</td>
			<td  colspan="3"><button type = "button" onclick = "uploadImg();">上传图片</button></td>
		</tr>
		<tr>
			<td name="attachment" align = "right">图片预览：</td>
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
<div id = "showMap">
</div>
<!-- 上传文件 -->
<script src="${ctx}/static/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	var showMap;
	var xpoint = "xxx666";
	var ypoint = "yyy666";
$(function(){
    addressInit('cmbProvince', 'cmbCity', 'cmbArea','${carShop.province}','${carShop.city}','${carShop.area}');
$('#mainform').form({
onSubmit: function(){
    var isValid = $(this).form('validate');
    return isValid;	// 返回false终止表单提交
},
success:function(data){
        successTip(data,carShops,d);
}
});
});
function chooseMap(){
    var url = "${ctx}/web/carShops/chooseMap";
    var title = "选取地图";
    var content = '<iframe src="' + url + '" width="100%" height="99%" frameborder="0" scrolling="no"></iframe>';
    var boarddiv = '<div id="msgwindow" title="' + title + '"></div>'//style="overflow:hidden;"可以去掉滚动条
    $(document.body).append(boarddiv);
    var win = $('#msgwindow').dialog({
        content: content,
        width: 1000,
        height: 400,
        modal: true,
        title: title,
        maximizable:true,
        onClose: function () {
            $(this).dialog('destroy');//后面可以关闭后的事件
        },
        buttons:[{
            text:'确定',
            handler:function() {
                win.panel('close');
            }
        },{
            text:'取消',
            handler:function(){
                win.panel('close');
            }
        }]
    });
    win.dialog('open');
}
    var index;
    //上传图片
    var imgPath="";
    var attachment = "${carShop.attachment}";
    if(attachment){
        var atts = attachment.split(",");
//        console.log(atts)
        $("#images").html();
        var htm="";
//        alert($("#images").html())
        for(var i=0;i<atts.length;i++){
            imgPath=atts[i];
//            console.log(imgPath);
            htm+="<div style='position: relative; width: 68px; float: left; margin-left: 5px'>";
            htm+="<input type = 'hidden' value ='"+imgPath+"' name = 'attachment'>";
            htm+="<img src='"+imgPath+"' style='width:68px;height:68px;vertical-align: middle;' onmouseover='toBig(this)'onmouseout='toSmall();' >";
            htm+="<img src='${ctx}/static/images/remove.png'  style='position: absolute;top:0px;right:0px;width:20px;' imgsrc='"+imgPath+"' onclick='deleteImg(this)'>";
            htm+="</div>";
		}
        $("#images").html(htm);
//        alert($("#images").html());
	}
//    $("#images")
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
//                        if(data.data == "bili"){
//                            alert("上传失败，图片上传比例为16/9");
//                            return false;
//                        }
                        imgPath=data.data;
                        htm+="<div style='position: relative; width: 68px; float: left; margin-left: 5px'>";
                        htm+="<input type = 'hidden' value ='"+imgPath+"' name = 'attachment'>";
                        htm+="<img src='"+imgPath+"' style='width:68px;height:68px;vertical-align: middle;' onmouseover='toBig(this)'onmouseout='toSmall();' >";
                        htm+="<img src='${ctx}/static/images/remove.png'  style='position: absolute;top:0px;right:0px;width:20px;' imgsrc='"+imgPath+"' onclick='deleteImg(this)'>";
                        htm+="</div>";
                        $("#images").append(htm);
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