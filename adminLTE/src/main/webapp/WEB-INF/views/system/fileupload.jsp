<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript" src="${ctx}/static/plugins/jquery/jquery.form.js"></script>
</head>
<body class="easyui-layout" data-options="fit: true">
 <form id="fileform" action="${ctx }/system/fileupload" method="post" enctype="multipart/form-data">  
            选择文件:<input type="file" name="upFile">  
            <input type="text" name="fileName" id="t1">
            <input type="button" value="提交" id="submitfile">
            <input type="button" value="下载" id="downfile">  
    </form>  
    <br>
    <a id="down"  href="${ctx }/system/fileupload/down">先上传文件，点提交，再点此下载连接</a>
<script type="text/javascript">
$(function(){
	$("#submitfile").click(function(){
		$('#fileform').form('submit', {
			url : '${ctx }/system/fileupload',
			success : function(data) {
					$("#t1").val(data);
					$("#down").prop("href",$("#down").prop("href")+"?fileName="+data);
			}
		});
	})
	
	$("#t1").change(function(){
		$("#down").prop("href",$("#down").prop("href")+"?fileName="+$("#t1").val());
	})
	$("#downfile").click(function(){
		$('#fileform').form('submit', {
			url : '${ctx }/system/fileupload/down',
			success : function(data) {
					
			}
		});
	})
})
</script>
</body>
</html>