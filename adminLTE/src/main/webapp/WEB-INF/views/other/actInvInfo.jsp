<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<table cellpadding="15">
		<tr>
			<td>活动标题</td>
			<td>参与人姓名</td>
			<td>参与人手机号</td>
			<td>参与人邮箱</td>
			<td>
				操作
			</td>
		</tr>
		<c:forEach var ="actInv" items="${actInvList}">
			<tr>
				<td>${actInv.mainTitle}</td>
				<td>${actInv.name}</td>
				<td>${actInv.phone}</td>
				<td>${actInv.email}</td>
				<td>
					<c:if test="${actInv.state==0}">
						<button type = "button" onclick = "queren(this)" data = "${actInv.id}">批准</button>
						<button type = "button" onclick = "queren(this)" data = "${actInv.id}">不批准</button></c:if>
					<c:if test="${actInv.state==1}">
						审核通过
					</c:if>
					<c:if test="${actInv.state==2}">
						审核未通过  ${actInv.failReason}
					</c:if>
					<%--<input type="text" id = "failReason" placeholder = "请填写不通过原因" style = "hidden">--%>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<script type="text/javascript">
function queren(obj){
    var id = $(obj).attr("data");
    var type = "1";//批准
//    var type = "2";//不批准
    var value = $(obj).html();
    var failReason;
    if(value=="批准"){
        type = "1";
        $(obj).parent().html("审核通过");
	}
    if(value=="不批准"){
        type = "2";
        var failReason =prompt("请填写未通过原因");
//        failReason = $("#failReason").val();
        if(!failReason){
            alert("未填写驳回原因");
            return false;
		}
//		alert(failReason)
        $(obj).parent().html("审核未通过");
//        return false;
    }
    $.ajax({
		url:"${ctx}/web/activity/joinInACT?type="+type+"&id="+id,
		type:"post",
		dataType:"json",
		data:{failReason:failReason},
		success:function(data){
			alert(data.info);
		},
		error:function(){
			alert("操作异常")
		}
	});
}
</script>
</body>
</html>