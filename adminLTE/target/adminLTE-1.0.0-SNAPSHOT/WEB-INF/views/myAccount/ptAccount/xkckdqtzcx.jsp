<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>现款敞口到期台账查询</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/js/ptAccount/xkckdqtzcx.js?${currentTime}"></script>
</head>
<body>
	<div id="div_xkckdqtzcx" class="">
		<form id="form_xkckdqtzcx" method="post">
			<input type="hidden" value="${userRole }" id="xkckdqtzcx_userRole" />
			<input type="hidden" value="${ptgsdm }" id="xkckdqtzcx_ptgsdm" />
			<input type="hidden" value="${ssfb }" id="xkckdqtzcx_ssfb" />
			<input type="hidden" value="" id="xkckdqtzcx_jylxQuery" />
			<input type="hidden" value="" id="xkckdqtzcx_zjlxQuery" />
			<input type="hidden" value="" id="xkckdqtzcx_yqztQuery" />
			<input type="hidden" value="" id="xkckdqtzcx_zkydqQuery" />
			<input type="hidden" value="" id="xkckdqtzcx_zkwdqQuery" />
			<input type="hidden" value="${paramsMap }" id="xkckdqtzcx_params" />
			<input type="hidden" value="" id="xkckdqtzcx_yjqQuery" />
			<input type="hidden" value="" id="xkckdqtzcx_wjqQuery"/>
			<div style="padding: 0px 0px 5px 0px">
				<label class="font_size_label_large">现款敞口到期台账查询</label>
			</div>
			<div class="thick_line"></div>
			<div>
				<label>分部公司：</label>&nbsp;<input id="xkckdqtzcx_fbgs" name="fbgs"
					style="margin-left: 5px;" /> <label
					style="margin-left: 30px;">平台公司：</label><input id="xkckdqtzcx_ptgs"
					name="ptgs" style="margin-left: 5px;width: 200px;"/> <a class="easyui-linkbutton"
					href="#" data-options="iconCls:'icon-search'"
					onclick="xkckdqtzcxReload();"> 查询 </a>&nbsp;&nbsp;
				<shiro:hasPermission name="tzcx:xkckdqtzcx:export">
					<a id="btn_ptzhxx_sc" class="easyui-linkbutton" href="#"
						data-options="iconCls:'icon-print'"
						onclick="commExport('现款敞口到期台账查询', 'xkckdqtzcx_datagrid');"> 导出
					</a>
				</shiro:hasPermission>
			</div>
			<div class="search_a">
				<label class="">到期日期：</label>
					<input id="xkckdqtzcx_dqsjStart" type="text"></input>&nbsp;&nbsp;-&nbsp;&nbsp;
					<input id="xkckdqtzcx_dqsjEnd" type="text"></input> &nbsp;&nbsp; 
					<a style="margin-left: 10px;" href="#" id="xkckdqtzcx_dqsj_dy" onclick="xkckdqtzcxDqsjSearch('dy');"> 当月 </a> 
					<a style="margin-left: 10px;" href="#" id="xkckdqtzcx_dqsj_zj3gy" onclick="xkckdqtzcxDqsjSearch('zj3gy');"> 最近3个月 </a>
					<a style="margin-left: 10px;" href="#" id="xkckdqtzcx_dqsj_jn" onclick="xkckdqtzcxDqsjSearch('jn');"> 今年 </a>
			</div>
			<div class="search_a">
				<label class="">交易类型：</label> <a id="a_xkckdqtzcx_jylxAll"
					style="margin-left: 0px;" href="#"
					onclick="xkckdqtzcxJylxSearch('','');" class="current_a"> 全部 </a> 
					<c:forEach items ="${jylxList}" var = "zdxx" >
						<a style="margin-left: 10px;" href="#" id="a_xkckdqtzcx_${zdxx.code}"
						onclick="xkckdqtzcxJylxSearch('${zdxx.value}','${zdxx.name}');">${zdxx.name}</a>
					</c:forEach>
			</div>
			<div class="search_a">
				<label id="zjlx_label">资金类型：</label> <a id="a_xkckdqtzcx_zjlxAll"
					style="margin-left: 0px;" href="#"
					onclick="xkckdqtzcxZjlxSearch('');" class="current_a"> 全部 </a>
					<c:forEach items ="${zjlxList}" var = "zdxx" >
						<a style="margin-left: 10px;" href="#" id="a_xkckdqtzcx_${zdxx.code}"
						onclick="xkckdqtzcxZjlxSearch('${zdxx.value}');">${zdxx.name}</a>
					</c:forEach>
			</div>
			<div>
				<label>到期逾期：</label>
				<input type="checkbox" id="checkboxZKWDQ" value="" onchange="xkckZKWDQChange();">只看未到期</input> 
				<input type="checkbox" id="checkboxZKYDQ" value="" onchange="xkckZKYDQChange();">只看已到期</input>
				<input type="checkbox" id="checkboxZKYQ" value="" onchange="xkckZKYQChange();">只看逾期</input>
			</div>
			<div>
				<label>是否结清：</label>
				<input type="checkbox" id="checkboxYjq" value=""
					onchange="xkckYjqChange();">已结清</input>
					<input type="checkbox" id="checkboxWjq" value=""
					onchange="xkckWjqChange();">未结清</input> 
			</div>
		</form>
	</div>
	<table id="xkckdqtzcx_datagrid"></table>
	<div id="ptzhZk_win" class="easyui-window" title="平台账户转款"
		style="width: 500px; height: 200px;"
		data-options="iconCls:'icon-save',modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false,resizable:false,">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'"
				style="padding: 20px 0 0 50px; background: #eee;">
				<input id="xkckdqtzcxZkWin_khzje" type="hidden" /> <input
					id="xkckdqtzcxZkWin_clid" type="hidden" /> <input
					id="xkckdqtzcxZkWin_pjid" type="hidden" />
				<div style="float: left; width: 120px; height: 30px;">
					<label style="font-size: 14pt;">转款金额：</label>
				</div>
				<input id="xkckdqtzcxZkWin_zkje" type="text" maxlength="30"
					class="easyui-numberbox easyui-validatebox" required="required"
					value="" data-options="min:0.00,precision:2"
					style="text-align: right;" />
				<div
					style="width: 100%; height: 25px; margin-top: 30px; text-align: center;">
					<input type="button"
						style="margin-right: 15px; no-repeat; width: 85px; height: 25px; font-size: 14px; font-weight: bold; cursor: pointer;"
						value="转款" onclick="xkckdqSaveZkAction()" /> <input type="button"
						style="margin-right: 15px; no-repeat; width: 85px; height: 25px; font-size: 14px; color: red; font-weight: bold; cursor: pointer;"
						value="取消" onclick="javascript:$('#ptzhZk_win').window('close');" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>