<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>平台账户实时查询</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/js/tzcx/ptsszhmxcx.js?${currentTime}"></script>
</head>
<body>
	<div id="div_ptsszhmxcx" class="">
		<form id="form_ptsszhmxcx" method="post">
			<input type="hidden" value="${userRole }" id="ptsszhmxcx_userRole" />
			<input type="hidden" value="${ptgsdm }" id="ptsszhmxcx_ptgsdm" /> <input
				type="hidden" value="${ssfb }" id="ptsszhmxcx_ssfb" /><input
				type="hidden" value="" id="ptsszhmxcx_zkzhyebzQuery" /> <input
				type="hidden" value="" id="ptsszhmxcx_zksxyebzQuery" /> <input
				type="hidden" value="${paramsMap }" id="ptsszhmxcx_params" />

			<div style="padding: 0px 0px 5px 0px">
				<label class="font_size_label_large">平台账户实时查询</label>
			</div>
			<div class="thick_line"></div>
			<div class="search_a">
				<label>分部公司：</label>&nbsp;<input id="ptsszhmxcx_fbgs" name="fbgs"
					style="margin-left: 5px;" /> <label
					style="margin-left: 30px;">平台公司：</label><input id="ptsszhmxcx_ptgs"
					name="ptgs" style="margin-left: 5px;width: 200px;"/> <a class="easyui-linkbutton"
					href="#" data-options="iconCls:'icon-search'"
					onclick="ptsszhmxcxReload();"> 查询 </a>&nbsp;&nbsp;
				<shiro:hasPermission name="tzcx:ptsszhmxcx:export">
					<a id="btn_ptzhxx_sc" class="easyui-linkbutton" href="#"
						data-options="iconCls:'icon-print'"
						onclick="commExport('平台实时账户明细查询', 'ptsszhmxcx_datagrid');"> 导出
					</a>
				</shiro:hasPermission>
			</div>
			<div class="search_a">
				<label style="margin-left: 23px;">级别：</label>&nbsp;<input
					id="ptsszhmxcx_jb" name="jb" value="${jb }"
					style="margin-left: 5px;" /><input type="checkbox"
					style="margin-left: 35px;" id="checkboxZkzhyebz" value="1"
					onchange="xkckZkzhyeChange();">只看帐户余额不足</input> <input
					type="checkbox" id="checkboxZksxyebz" value="1"
					onchange="xkckZksxyeChange();">只看授信余额不足</input>
			</div>
		</form>
	</div>
	<table id="ptsszhmxcx_datagrid"></table>
</body>
</html>