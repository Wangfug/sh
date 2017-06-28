<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
	<script type="text/javascript" src="${ctx}/static/plugins/jquery/jquery.form.js"></script>
</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/tbaseAttachment/${action}" method="post">
		<input type = "hidden" name = "id" id = "id" value = "${tbaseAttachment.id}">
	<table  class="formTable" cellpadding="20" align="center">
		<tr>
			<td align = "right">选择附件：</td>
			<td>
				<%--<input name="cityName" type="text" value="${tbaseAttachment.cityName}" class="easyui-validatebox" required="required"/>--%>
					<%--<input name="file" type="file" id = "file" />--%>
				<button type="button" id = "chooseFile" onclick = "upload()" class="easyui-linkbutton">选择文件</button>
					<p type="text" name="fileName" id="t1"></p>
					<input name="filename" type="hidden" id = "filename" value = ""/>
					<input name="filepath" type="hidden" id = "filepath" value = ""/>
					<input name="fileSize" type="hidden" id = "fileSize" value = ""/>
			</td>
		<tr>
		<tr>
			<td align = "right">文件名：</td>
			<td>
				<c:if test="${empty tbaseAttachment.filepath}">
					<a id = "down" href="${ctx }/system/fileupload/down" style="display: none;" ></a>
					<img id = "img" src="${ctx }/system/fileupload/down" style="display: none;" />
				</c:if>
				<c:if test="${!empty tbaseAttachment.filepath}">
					<a id = "down" href="${ctx }/system/fileupload/down?fileName=${tbaseAttachment.filepath}" >${tbaseAttachment.filename}</a>
				</c:if>

			</td>
		<tr>
		</tr>
			<td align = "right">备注：</td>
			<td style = "width:85%">
				<textarea name="remark" type="text"  style = "width:100%">${tbaseAttachment.remark}</textarea>
			</td>
		</tr>
</table>
</form>
</div>
<div id = "fileDiv"></div>
<script type="text/javascript">
	var fileDiv;
$(function(){
$('#mainform').form({
onSubmit: function(){
    var isValid = $(this).form('validate');
    return isValid;	// 返回false终止表单提交
},
success:function(data){
    successTip(data,tbaseAttachment,d);
}
});
});
function upload(){
    fileDiv=$("#fileDiv").dialog({
        title: '上传附件',
        width: 700,
        height: 400,
        href:'${ctx}/system/fileupload',
        maximizable:true,
        modal:true,
        buttons:[{
            text:'上传',
            handler:function(){
//                $('#fileform').submit();
                $('#fileform').form('submit', {
                    url : '${ctx }/system/fileupload',
					dataType:"json",
                    success : function(data) {
                        data = eval("("+data+")");
                        if(data.state==1){
                            alert("上传成功");
                            $("#chooseFile").html("重新选择！");

                            $("#t1").val(data);
                           /* var end = data.filename.substring(data.filename.lastIndexOf("."));
                            if(end==".png"||end==".jpg"){
                                $("#img").prop("src","/resource"+data.filepath);
                                $("#img").show();
                                $("#down").hide();
                                $("#img").html(data.filename);
                            }else{*/
                                $("#down").prop("href","${ctx }/system/fileupload/down?fileName="+data.filepath);
                                $("#img").hide();
                                $("#down").show();
                                $("#down").html(data.filename);
//                            }

                            $("#fileSize").val(data.filesize);
                            $("#filepath").val(data.filepath);
                            $("#filename").val(data.filename);
                            fileDiv.panel('close');
                        }
                    }
                });
            }
        },{
            text:'取消',
            handler:function(){
                fileDiv.panel('close');
            }
        }]
    });
}
</script>
</body>
</html>