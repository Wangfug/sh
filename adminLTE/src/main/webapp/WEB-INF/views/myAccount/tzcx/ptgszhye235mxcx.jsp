<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>按照235原则平台公司账户余额明细查询</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/js/tzcx/ptgszhye235mxcx.js?${currentTime}"></script>
</head>
<body>
	<div id="div_ptgszhye235mxcx" class="">
		<form id="form_ptgszhye235mxcx" method="post">
			<input type="hidden" value="${userRole }"
				id="ptgszhye235mxcx_userRole" /><input type="hidden"
				value="${ptgsdm }" id="ptgszhye235mxcx_ptgsdm" /> 
				<input type="hidden" value="${ssfb }" id="ptgszhye235mxcx_ssfb" />

			<div style="padding: 0px 0px 5px 0px">
				<label class="font_size_label_large">按照235原则平台公司账户余额明细查询</label>
			</div>
			<div class="thick_line"></div>
			<div class="search_a">
				<label>分部公司：</label>&nbsp;<input id="ptgszhye235mxcx_fbgs"
					name="fbgs"  style="margin-left: 5px;" /> <label
					style="margin-left: 30px;">平台公司：</label><input
					id="ptgszhye235mxcx_ptgs" name="ptgs"  style="margin-left: 5px;width: 200px;"/>
			</div>
			<div class="search_a">
				<label style="margin-left: 24px;">级别：</label>&nbsp;<input
					id="ptgszhye235mxcx_jb" name="jb" value="${jb }"
					style="margin-left: 5px;" /><a class="easyui-linkbutton" href="#"
					style="margin-left: 35px;" data-options="iconCls:'icon-search'"
					onclick="ptgszhye235mxcxReload();"> 查询 </a>&nbsp;&nbsp;
				<shiro:hasPermission name="tzcx:ptgszhye235mxcx:export">
					<a id="btn_ptzhxx_sc" class="easyui-linkbutton" href="#"
						data-options="iconCls:'icon-print'"
						onclick="commExport('按照235原则平台公司账户余额明细查询', 'ptgszhye235mxcx_datagrid');">
						导出 </a>
				</shiro:hasPermission>
			</div>
		</form>
	</div>
	<table id="ptgszhye235mxcx_datagrid"></table>
</body>
</html>