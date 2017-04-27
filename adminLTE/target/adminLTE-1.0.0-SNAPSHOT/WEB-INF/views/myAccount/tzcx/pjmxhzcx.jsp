<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>办结票据明细信息汇总</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript" src="${ctx}/static/js/tzcx/pjmxhzcx.js?${currentTime}"></script>
</head>
<body>
	<div id="div_pjmxhzcx" class="">
		<form id="form_pjmxhzcx" method="post">
			<input type="hidden" value="${userRole }" id="pjmxhzcx_userRole" />
			<input type="hidden" value="${ptgsdm }" id="pjmxhzcx_ptgsdm" /> <input
				type="hidden" value="${ssfb }" id="pjmxhzcx_ssfb" />
			<div style="margin: 10px 0px 0px 0px;">
				<label>分部公司：</label>&nbsp;<input id="pjmxhzcx_fbgs" name="fbgs"
					style="margin-left: 5px;" /> <label
					style="margin-left: 200px;">平台公司：</label><input id="pjmxhzcx_ptgs"
					name="ptgs" style="margin-left: 5px;width: 200px;"/>
			</div>
			<div style="margin: 10px 0px 0px 0px;">
				<label class="">申请日期：</label> <input id="pjmxhzcx_sqrqStart"
					type="text" class="easyui-datebox"></input>
				&nbsp;&nbsp;-&nbsp;&nbsp; <input id="pjmxhzcx_sqrqEnd" type="text"
					class="easyui-datebox"></input> &nbsp;&nbsp; <a
					style="margin-left: 10px;" href="#"
					onclick="pjmxhzcxSqrqSearch('dy');"> 当月 </a> <a
					style="margin-left: 10px;" href="#"
					onclick="pjmxhzcxSqrqSearch('zj3gy');"> 最近3个月 </a> <a
					style="margin-left: 10px;" href="#"
					onclick="pjmxhzcxSqrqSearch('jn');"> 今年 </a>
			</div>
			<div style="margin: 10px 0px 0px 0px;">
				<label class="">交易类型：</label>&nbsp;<input id="pjmxhzcx_jylx" /> <label
					style="margin-left: 200px;">票据类型：</label><input id="pjmxhzcx_pjlx" />
			</div>

			<div style="margin: 10px 0px 0px 0px;">
				<label class="">票据期限：</label>&nbsp;<input id="pjmxhzcx_pjqx" /> <a
					class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-search'" onclick="pjmxhzcxReload();"
					style="margin-left: 200px;"> 查询 </a>
			</div>
		</form>
	</div>
	<table id="pjmxhzcx_datagrid"></table>
</body>
</html>