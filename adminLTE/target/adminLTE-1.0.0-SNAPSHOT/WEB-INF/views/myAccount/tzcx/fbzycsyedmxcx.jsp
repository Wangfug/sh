<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>分部资源池使用额度明细查询</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/js/tzcx/fbzycsyedmxcx.js?${currentTime}"></script>
</head>
<body>
	<div id="div_fbzycsyedmxcx" class="">
		<form id="form_fbzycsyedmxcx" method="post">
            <input type="hidden" id="userBz" name="userBz" value="${userBz}" />
            <input type="hidden" id="gsdm" name="gsdm" value="${gsdm}" />
			<div style="padding: 0px 0px 5px 0px">
				<label class="font_size_label_large">分部资源池使用额度明细查询</label>
			</div>
			<div class="thick_line"></div>
			<div class="search_a">
				<label>分部公司：</label>&nbsp;
				<form:select path="fbList" id="fbList" name="fbgs">
                    <form:options items="${fbList}" itemLabel="gsxxName" itemValue="gsxxCode"></form:options>
                </form:select>
                   <a class="easyui-linkbutton" href="#"
                    data-options="iconCls:'icon-search'"
                    onclick="fbzycsyedmxcxReload();"> 查询 </a>&nbsp;&nbsp;
                
				<shiro:hasPermission name="tzcx:fbzycsyedmxcx:export">
					<a class="easyui-linkbutton" href="#"
						data-options="iconCls:'icon-print'"
						onclick="commExport('分部资源池使用额度明细查询', 'fbzycsyedmxcx_datagrid');">
						导出 </a>
				</shiro:hasPermission>
			</div>
		</form>
	</div>
	<table id="fbzycsyedmxcx_datagrid"></table>
</body>
</html>