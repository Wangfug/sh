<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>${tzgg.title}</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div id="downform">
       ${content}
</div>
<script type="text/javascript">
$(function(){
	$('#downform a').prop("target","_new");
});

</script>
</body>
</html>