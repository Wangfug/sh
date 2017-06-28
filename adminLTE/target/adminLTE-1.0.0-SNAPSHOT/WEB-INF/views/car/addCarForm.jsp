<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body>
<div style="padding-bottom: 20px">
	<form id="mainform" action="${ctx}/web/car/${action}" method="post">
	<table  class="formTable">
		<tr>
			<td>车牌号：</td>
			<td>
			<input name="carCode" type="text"  class="easyui-validatebox" required="required" data-options = "validType:'carCode'"/>
			</td>
			<td style="padding-left: 40px;">发动机号：</td>
			<td><input name="engineNo" type="text"  class="easyui-validatebox"/></td>
		</tr>
		<tr>
			<td>车架号：</td>
			<td>
				<input name="frameNo" type="text"  class="easyui-validatebox" required="required"/>
			</td>
			<td style="padding-left: 40px;">颜色：</td>
			<td><input name="color" type="text"  class="easyui-validatebox" /></td>
		</tr>
		<tr>
			<td>品牌：</td>
			<td><input name="brand" type="text"  class="easyui-validatebox" required="required"/></td>
			<td  style="padding-left: 40px;">型号：</td>
			<td>
				<input name="model" type="text"  class="easyui-validatebox"  />
			</td>
		</tr>
		<tr>
			<td>车厢数：</td>
			<td><input name="cartonNumber" type="text"  class="easyui-validatebox" /></td>
			<td style="padding-left: 40px;">入库时间：</td>
			<td>
				<input name="intime"   class="easyui-datetimebox"/>
			</td>
		</tr>

		<tr>
			<td>车型：</td>
			<td colspan="3" style="padding-top: 10px;padding-bottom: 10px;">
				<c:forEach var="dict" items="${CarModel}">
					<input name="carType" id = "carType${dict.code}" type="checkbox" value="${dict.code}" class="easyui-validatebox" />${dict.name}
				</c:forEach>
			</td>

		</tr>

		<tr>
			<td>经营种类：</td>
			<td>
				<select name="belong" id="belong" onchange="changeType()">
					<c:forEach var="carManage" items="${carManageType}">
						<c:if test="${carManage.code eq car.belong}">
							<option value="${carManage.code}" selected>${carManage.name}</option>
						</c:if>
						<c:if test="${carManage.code ne car.belong}">
							<option value="${carManage.code}">${carManage.name}</option>
						</c:if>
					</c:forEach>
				</select>
			</td>

			<td style="padding-left: 40px;">服务状态：</td>
			<td>
				<%--<select name="state" style="vertical-align: middle;width: 100px;" id="carState">${carStates}</select>--%>
				<select  name="state">
					<c:forEach var="dict" items="${dictsForCar}">
						<c:if test="${dict.code eq car.state}"><option value="${dict.code}" selected>${dict.name}</option></c:if>
						<c:if test="${dict.code ne car.state}"><option value="${dict.code}">${dict.name}</option></c:if>
					</c:forEach>
				</select>

			</td>
		</tr>

		<tr style="display: none" id="ly">
			<td>联营公司：</td>
			<td>
				<select name="bindObj1" class="easyui-combobox" >
					<c:forEach var="compAssociated" items="${compAssociateds}">
						<option value="${compAssociated.id}">${compAssociated.comName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>

		<tr style="display: none" id="gkgs1">
			<td>挂靠公司：</td>
			<td>
				<select name="bindObj2" class="easyui-combobox" >
					<c:forEach var="compAssociated" items="${compAssociateds}">
						<option value="${compAssociated.id}">${compAssociated.comName}</option>
					</c:forEach>
				</select>
			</td>
			<td>挂靠开始时间：</td>
			<td>
				<input name="attachStart2"   class="easyui-datetimebox" />
			</td>
		</tr>

		<tr style="display: none" id="gkgs2">
			<td>挂靠结束时间：</td>
			<td>
				<input name="attachEnd2"    class="easyui-datetimebox"/>
			</td>
			<td>挂靠备注：</td>
			<td>
				<textarea name="otherRemark2"    class="easyui-validatebox"></textarea>
			</td>

		</tr>


		<tr style="display: none" id="gkgr1">
			<td>挂靠个人：</td>
			<td>
				<select name="bindObj3"  id = "bindObj3" class="easyui-combobox" >
					<c:forEach var="customer" items="${customers}">
						<option value="${customer.id}">${customer.name}</option>
					</c:forEach>
				</select>
			</td>
			<td  style="padding-left: 40px;">挂靠开始时间：</td>
			<td>
				<input name="attachStart3"    class="easyui-datetimebox"/>
			</td>
		</tr>
		<tr style="display: none" id="gkgr2">
			<td>挂靠结束时间：</td>
			<td>
				<input name="attachEnd3"    class="easyui-datetimebox" />
			</td>
			<td  style="padding-left: 40px;">挂靠备注：</td>
			<td >
				<textarea name="otherRemark3"   class="easyui-validatebox" ></textarea>
			</td>
		</tr>

		<tr>
			<td>购买日期：</td>
			<td><input name="buyTime"  class="easyui-datebox"  /></td>
			<td style="padding-left: 40px;">出厂日期：</td>
			<td><input name="leaveFactoryTime"    class="easyui-datebox" required="required"/></td>
		</tr>
		<tr>
			<td>购买价格：</td>
			<td><input name="moneyBuy" type="text"  class="easyui-validatebox" data-options = "validType:'money'"/></td>
			<c:if test="${fn:startsWith(user.jobCode,'MG') or fn:startsWith(user.jobCode,'KF')}">
				<td style="padding-left: 40px;">所在门店：</td>
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
			<td colspan="3"><textarea  style="width: 100%;height: 50px;"  name="remark1"  class="easyui-ueditor"></textarea></td>
		</tr>
    	<tr>
			<td>备注2：</td>
			<td  colspan="3"><textarea style="width: 100%;height: 50px;" name="remark2"  class="easyui-ueditor"></textarea></td>
		</tr>
		<tr>
			<td>备注3：</td>
			<td colspan="3"><textarea style="width: 100%;height: 50px;"  name="remark3"  class="easyui-ueditor"></textarea></td>
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
function  changeType() {
   var options=$("#belong option:selected").text();
    if(options == "联营"){
        $("#ly").css("display","");
        $("#gkgs1").css("display","none");
        $("#gkgs2").css("display","none");
        $("#gkgr1").css("display","none");
        $("#gkgr2").css("display","none");
    }else if(options == "挂靠公司"){
        $("#ly").css("display","none");
        $("#gkgs1").css("display","");
        $("#gkgs2").css("display","");
        $("#gkgr1").css("display","none");
        $("#gkgr2").css("display","none");
    }else if(options == "挂靠个人"){
        $("#ly").css("display","none");
        $("#gkgs1").css("display","none");
        $("#gkgs2").css("display","none");
        $("#gkgr1").css("display","");
        $("#gkgr2").css("display","");
    }else{
        $("#ly").css("display","none");
        $("#gkgs1").css("display","none");
        $("#gkgs2").css("display","none");
        $("#gkgr1").css("display","none");
        $("#gkgr2").css("display","none");
    }
}
    $(function () {
        $('#mainform').form({
            onSubmit: function () {
                var shoudong = $("#carType150001").is(":checked");
                var zidong = $("#carType150002").is(":checked");
                var szd = $("#carType150003").is(":checked");
                if((shoudong && !zidong&&!szd) || (!shoudong && zidong&&!szd)||(!shoudong && !zidong&&szd)){
                    var isValid = $(this).form('validate');
                    return isValid;	// 返回false终止表单提交
				}else{
                    alert("手动型/自动型/手自一体应选择其中之一");
                    return false;	// 返回false终止表单提交
				}
            },
            success: function (data) {
                successTip(data, car, d);
            }
        });
    });
</script>
</body>
</html>