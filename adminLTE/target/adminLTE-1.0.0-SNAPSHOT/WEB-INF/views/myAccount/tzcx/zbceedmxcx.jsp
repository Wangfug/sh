<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>总部超额额度明细查询</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/js/tzcx/zbceedmxcx.js?${currentTime}"></script>
</head>
<body>
	<div id="div_zbceedmxcx" class="">
		<form id="form_zbceedmxcx" method="post">
			<input type="hidden" value="${userRole }" id="zbceedmxcx_userRole" />
			<input type="hidden" value="${ssfb }" id="zbceedmxcx_ssfb" />
			<input type="hidden" value="${ptgsdm }" id="zbceedmxcx_ptgsdm" /> 
			<div style="padding: 0px 0px 5px 0px">
				<label class="font_size_label_large">总部超额额度明细查询</label>
			</div>
			<div class="thick_line"></div>
			<div class="search_a">
				<label>分部公司：</label>&nbsp;<input id="zbceedmxcx_fbgs" name="fbgs"
					style="margin-left: 5px;" />&nbsp;&nbsp;&nbsp;&nbsp;<a
					class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-search'" onclick="zbceedmxcxReload();">
					查询 </a>&nbsp;&nbsp;
				<shiro:hasPermission name="tzcx:zbceedmxcx:export">
					<a class="easyui-linkbutton" href="#"
						data-options="iconCls:'icon-print'"
						onclick="commExport('总部超额额度明细查询', 'zbceedmxcx_datagrid');"> 导出
					</a>
				</shiro:hasPermission>
			</div>
		</form>
	</div>
	<table id="zbceedmxcx_datagrid"></table>
</body>
</html>