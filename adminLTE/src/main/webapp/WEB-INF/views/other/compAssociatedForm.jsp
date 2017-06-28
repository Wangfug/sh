<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body>
<div>
	<form id="mainform" action="${ctx}/web/compAssociated/${action}" method="post">
		<input type = "hidden" name = "id" id = "id" value = "${compAssociated.id}">
		<table  class="formTable">
			<tr>
				<td>公司名称：</td>
				<td>
					<input name="comName" type="text" value="${compAssociated.comName}" class="easyui-validatebox" required="required"/>
				</td>
				<td  style="width: 85px;padding-left: 95px">公司法人：</td>
				<td>
					<input name="corporation" type="text" value="${compAssociated.corporation}" class="easyui-validatebox" required="required"/>
				</td>
			</tr>
			<tr>
				<td  >法人电话：</td>
				<td>
					<input name="phone" type="text" value="${compAssociated.phone}" class="easyui-validatebox"/>
				</td>
				<td  style="width: 85px;padding-left: 95px">法人证件类型?：</td>
				<td>
					<select name="idCardType">
						<c:forEach var="dict" items="${idCardType}">
							<c:if test="${dict.code eq compAssociated.idCardType}"><option value="${dict.code}" selected>${dict.name}</option></c:if>
							<c:if test="${dict.code ne compAssociated.idCardType}"><option value="${dict.code}">${dict.name}</option></c:if>
						</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<td>法人证件号码：</td>
				<td >
					<input name="idCardNo" type="text" value="${compAssociated.idCardNo}" class="easyui-validatebox"/>
				</td>
				<td style="width: 85px;padding-left: 95px">公司类型：</td>
				<td>
					<select name="type">
						<c:forEach var="dict" items="${companyType}">
							<c:if test="${dict.code eq compAssociated.type}"><option value="${dict.code}" selected>${dict.name}</option></c:if>
							<c:if test="${dict.code ne compAssociated.type}"><option value="${dict.code}">${dict.name}</option></c:if>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>法人地区：</td>
				<td colspan="3">
					<select name="country" id="country" class="dept_select" style="width: 130px;">
					</select>
					<select name="province" id ="province" class="dept_select"  style="width: 130px;">

					</select>
					<select name="area" id="city" class="dept_select"  style="width: 130px;">

					</select>
				</td>
			</tr>

			<tr>
				<td>公司注册地址：</td>
				<td colspan="3">
					<input name="comAddress" type="text" value="${compAssociated.comAddress}" class="easyui-validatebox"  style="width: 350px;"/>
				</td>
			</tr>

			<tr>
				<td>备注：</td>
				<td  colspan="3"><textarea style="width: 300px;height: 100px;" name="remark" value="" class="easyui-ueditor">${compAssociated.remark}</textarea></td>
			</tr>
			<tr>
				<td name="attachment">附件：</td>
				<td  colspan="3"><img src="${ctx}/static/images/test.png" style="width: 100px;"></td>
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
    successTip(data,compAssociated,d);
}
});
});
    var areaObj = [];
    function initLocation(e){
        var a = 0;
        for (var m in e) {
            areaObj[a] = e[m];
            var b = 0;
            for (var n in e[m]) {
                areaObj[a][b++] = e[m][n];
            }
            a++;
        }
    }
</script>
<script type="text/javascript" src="${ctx}/static/js/worldCity/chosen.jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/worldCity/area_chs.js"></script>
<script type="text/javascript" src="${ctx}/static/js/worldCity/location_chs.js"></script>
<script type="text/javascript" src="${ctx}/static/js/worldCity/city.js"></script>
<script >
	var country = "${compAssociated.country}";
    var province = "${compAssociated.province}";
    var area = "${compAssociated.area}";
    init(country,province,area);
</script>
</body>
</html>