<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>分部账户明细</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/js/hqAccount/fbzhmx.js?${currentTime}"></script>
</head>
<body>
	<div id="div_fbzhmx" class="">
		<form id="form_ptzhxx" method="post">
			<input type="hidden" value="${userRole }" id="fbzhmx_userRole" />
			<input type="hidden" value="${ptgsdm }" id="fbzhmx_ptgsdm" /> 
			<input type="hidden" value="${ssfb }" id="fbzhmx_ssfb" />
			<div style="padding: 0px 0px 5px 0px">
				<label class="font_size_label_large">分部账户实时查询</label>
			</div>
			<div class="thick_line"></div>
			<div class="search_a">
				<label>分部公司：</label>&nbsp;<select class="easyui-combobox"
					id="dqfbzs" name="dqfbzs" style="margin-left: 5px;width:150px;">
				</select>&nbsp;&nbsp;<input type="checkbox" id="zkzhyebz" value="yebz"
					onchange="zkzhyeCheckChange();" /> 只看账户余额不足数&nbsp;&nbsp;<a
					id="btn_ptzhxx_cz" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-search'" onclick="fbzhxxCx()"> 查询 </a>&nbsp;&nbsp;
				<shiro:hasPermission name="tzcx:fbsszhmxcx:export">
					<a id="btn_ptzhxx_cz" class="easyui-linkbutton" href="#"
						data-options="iconCls:'icon-print'"
						onclick="commExport('分部实时账户明细查询', 'fbzhmx_datagrid');"> 导出 </a>
				</shiro:hasPermission>
			</div>
		</form>
	</div>
	<table id="fbzhmx_datagrid"></table>
</body>
</html>