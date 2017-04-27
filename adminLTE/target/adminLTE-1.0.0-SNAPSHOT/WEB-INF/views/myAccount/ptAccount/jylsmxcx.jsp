<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>交易流水明细</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/js/ptAccount/jylsmx.js?${currentTime}"></script>
</head>
<body>
	<div id="div_jylsmx" style="padding: 5px; height: auto">
		<div>
			<form id="form_jylsmx" method="post">
			
			<input type="hidden" value="${userRole }" id="jylsmx_userRole" />
			<input type="hidden" value="${ptgsdm }" id="jylsmx_ptgsdm" /> 
			<input type="hidden" value="${ssfb }" id="jylsmx_ssfb" />
			<input type="hidden" value="${paramsMap }" id="jylsmx_paramsMap" />
			<input type="hidden" value="" id="jylsmx_jylxQuery" /> 
			<input type="hidden" value="" id="jylsmx_jylxmcQuery" /> 
			<input type="hidden" value="" id="jylsmx_jyztQuery" />
                
				<div style="padding: 0px 0px 5px 0px">
					<label class="font_size_label_large">交易流水明细查询</label>
				</div>
				<div class="thick_line"></div>
				<div class="search_a">
                    <label>分部公司：</label>&nbsp;<input id="jylsmx_fbgs" name="fbgs"
					style="margin-left: 5px;" /> <label
					style="margin-left: 30px;">平台公司：</label><input id="jylsmx_ptgs"
					name="ptgs" style="margin-left: 5px;width: 200px;"/>
						<a class="easyui-linkbutton"
						href="#" data-options="iconCls:'icon-search'"
						onclick="jylsmxReload();"> 查询 </a>&nbsp;&nbsp;
					<shiro:hasPermission name="tzcx:jylsmx:export">
						<a id="btn_ptzhxx_sc" class="easyui-linkbutton" href="#"
							data-options="iconCls:'icon-print'"
							onclick="commExport('交易流水查询', 'jylsmx_datagrid');"> 导出 </a>
					</shiro:hasPermission>
				</div>
				<div class="search_a">
					<label class="">起止日期：</label> <input id="jylsmx_clsjStart"
						type="text"></input> &nbsp;&nbsp;-&nbsp;&nbsp; <input
						id="jylsmx_clsjEnd" type="text"></input> &nbsp;&nbsp; <a
						style="margin-left: 10px;" href="#" class="current_a"
						onclick="jylsmxclsjSearch('dy');"> 当月 </a> <a
						style="margin-left: 10px;" href="#"
						onclick="jylsmxclsjSearch('zj3gy');"> 最近3个月 </a> <a
						style="margin-left: 10px;" href="#"
						onclick="jylsmxclsjSearch('jn');"> 今年 </a>
				</div>
				<div class="search_a">
					<label class="">交易类型：</label> <a style="margin-left: 0px;" href="#"
						onclick="jylsmxJylxSearch('','');" class="current_a"> 全部 </a> 
						<c:forEach items ="${jylxList}" var = "zdxx" >
							<a style="margin-left: 10px;" href="#"
							onclick="jylsmxJylxSearch('${zdxx.value}','${zdxx.name}');">${zdxx.name}</a>
						</c:forEach>
						
				</div>

				<div class="search_a">
					<label class="">交易状态：</label> <a style="margin-left: 0px;" href="#"
						onclick="jylsmxJyztSearch('');" class="current_a"> 全部 </a> 
						
						<c:forEach items ="${jyztList}" var = "zdxx" >
							<a style="margin-left: 10px;" href="#"
							onclick="jylsmxJyztSearch('${zdxx.value}');">${zdxx.name}</a>
						</c:forEach>
				</div>
			</form>
		</div>
	</div>
	<table id="jylsmx_datagrid"></table>
</body>
</html>