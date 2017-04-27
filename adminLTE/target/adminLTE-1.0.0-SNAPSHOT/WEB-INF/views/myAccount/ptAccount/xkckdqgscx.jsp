<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>现款敞口到期公司查询</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/js/ptAccount/xkckdqgscx.js?${currentTime}"></script>
</head>
<body>
	<div id="div_xkckdqgscx" class="">
		<form id="form_xkckdqgscx" method="post">
			<input type="hidden" value="${userRole }" id="xkckdqgscx_userRole" />
			<input type="hidden" value="${ptgsdm }" id="xkckdqgscx_ptgsdm" /> <input
				type="hidden" value="${ssfb }" id="xkckdqgscx_ssfb" /><input
				type="hidden" value="" id="xkckdqgscx_zjlxQuery" /> <input
				type="hidden" value="" id="xkckdqgscx_dqztQuery" /> <input
				type="hidden" value="${paramsMap }" id="xkckdqgscx_params" />

			<div style="padding: 0px 0px 5px 0px">
				<label class="font_size_label_large">现款敞口到期公司查询</label>
			</div>
			<div class="thick_line"></div>
			<div class="search_a">
				<label>分部公司：</label>&nbsp;<input id="xkckdqgscx_fbgs" name="fbgs"
					style="margin-left: 5px;" /> <label
					style="margin-left: 30px;">平台公司：</label><input id="xkckdqgscx_ptgs"
					name="ptgs" style="margin-left: 5px;width: 200px;"/> <a class="easyui-linkbutton"
					href="#" data-options="iconCls:'icon-search'"
					onclick="xkckdqgscxReload();"> 查询 </a>&nbsp;&nbsp;
				<shiro:hasPermission name="tzcx:xkckdqgscx:export">
					<a id="btn_ptzhxx_sc" class="easyui-linkbutton" href="#"
						data-options="iconCls:'icon-print'"
						onclick="commExport('现款敞口到期公司查询', 'xkckdqgscx_datagrid');"> 导出
					</a>
				</shiro:hasPermission>
			</div>
			<div class="search_a">
				<label class="">日期：</label> 
				<input id="rqSearchStart" type="text" />
				&nbsp;&nbsp;-&nbsp;&nbsp; 
				<input id="rqSearchEnd" type="text" />
			</div>
			<div class="search_a">
				<label class="">资金类型：</label> <a id="a_xkckdqgscx_zjlxAll"
					style="margin-left: 0px;" href="#"
					onclick="xkckdqgscxZjlxSearch('');" class="current_a">全部</a> 
					<c:forEach items ="${zjlxList}" var = "zdxx" >
						<a style="margin-left: 10px;" href="#"
						onclick="xkckdqgscxZjlxSearch('${zdxx.value}');">${zdxx.name}</a>
					</c:forEach>
					<input type="checkbox" id="checkboxZKDQ" value="1"
					onchange="xkckZKDQChange();">只看到期</input> <input type="checkbox"
					id="checkboxZKYQ" value="2" onchange="xkckZKYQChange();">只看逾期</input>
			</div>
		</form>
	</div>
	<table id="xkckdqgscx_datagrid"></table>
</body>
</html>