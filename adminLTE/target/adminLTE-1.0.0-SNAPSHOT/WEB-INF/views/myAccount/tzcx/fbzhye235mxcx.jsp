<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>按照235原则分部公司账户余额明细查询</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/js/tzcx/fbzhye235mxcx.js?${currentTime}"></script>
</head>
<body>
	<div id="div_fbzhye235mxcx" class="">
		<form id="form_fbzhye235mxcx" method="post">
			<input type="hidden" value="${userRole }" id="fbzhye235mxcx_userRole" />
			<input type="hidden" value="${ssfb }" id="fbzhye235mxcx_ssfb" />
			<input type="hidden" value="${ptgsdm }" id="fbzhye235mxcx_ptgsdm" /> 
			<div style="padding: 0px 0px 5px 0px">
				<label class="font_size_label_large">按照235原则分部公司账户余额明细查询</label>
			</div>
			<div class="thick_line"></div>
			<div class="search_a">
				<label>分部公司：</label>&nbsp;<input id="fbzhye235mxcx_fbgs" name="fbgs"
					style="margin-left: 5px;" /><a
					class="easyui-linkbutton" href="#" style="margin-left: 35px;"
					data-options="iconCls:'icon-search'"
					onclick="fbzhye235mxcxReload();"> 查询 </a>&nbsp;&nbsp;
				<shiro:hasPermission name="tzcx:fbzhye235mxcx:export">
					<a id="btn_ptzhxx_sc" class="easyui-linkbutton" href="#"
						data-options="iconCls:'icon-print'"
						onclick="commExport('按照235原则分部公司账户余额明细查询', 'fbzhye235mxcx_datagrid');">
						导出 </a>
				</shiro:hasPermission>
			</div>
		</form>
	</div>
	<table id="fbzhye235mxcx_datagrid"></table>
</body>
</html>