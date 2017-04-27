<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>总部借款</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/js/ptAccount/zbjk.js?${currentTime}"></script>
<link rel="stylesheet" href="${ctx }/static/css/stylezycd.css" />
<link rel="stylesheet" href="${ctx }/static/css/stylesp.css" />
<style type="text/css">
#zbjktable td {
	padding: 3px;
	margin: 3px
}
</style>
</head>
<body>
	<div class="main">
		<div style="padding: 5px; width: 500px;">
			<shiro:hasPermission name="sxyw:zbjk:save">
				<a id="fill_save" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-save'" onclick="zbjkOpt('tempsave');">
					暂存 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:zbjk:submit">
				<a id="fill_submit" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-add'" onclick="zbjkOpt('submit');">
					提交 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:zbjk:delete">
				<a id="fill_delete" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-cancel'" onclick="zbjkOpt('delete');">
					删除 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:zbjk:agree">
				<a id="approve" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-ok'" onclick="spjeqr(1)">同意</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:zbjk:refuse">
				<a id="reject" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-no'" onclick="spjeqr(2)">拒绝</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:zbjk:confirm">
				<a id="sp_ok" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-ok'" onclick="zbjkConfirm('1');">
					确认 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:zbjk:back">
				<a id="sp_no" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-back'" onclick="zbjkConfirm('0');">
					退回 </a>
			</shiro:hasPermission>
		</div>

		<jsp:include page="/WEB-INF/views/sxyw/basicInfo.jsp" flush="true" />

		<div class="content"></div>
		<div class="infor_box">
			<div class="bt">
				用款申请信息&nbsp;&nbsp;<a href="#" onclick="">用信计划</a>
			</div>
			<div class="infor" style="height: 230px;">
				<form id="form_zbjk" method="post">
					<input type="hidden" value="${mapData.ptgsdm }" id="zbjk_ptgsdm" />
					<input type="hidden" value="${mapData.clid }" id="zbjk_PrimaryKey" />
					<input type="hidden" value="${mapData.taskId }" id="zbjk_taskId" />
					<input type="hidden" value="${mapData.wfId }" id="zbjk_wfId" /> <input
						type="hidden" value="${mapData.gwRole }" id="zbjk_gwRole" /> <input
						type="hidden" value="${mapData.isViewZhLs }" id="isViewZhLs" /> <input
						type="hidden" value="${mapData.paramsMap }" id="paramsMap" /> <input
						type="hidden" value="${mapData.spzt }" id="spzt" />

					<table style="font-size: 11pt; margin: 5px 0px 20px 0px;"
						id="zbjktable">
						<tr>
							<td style="text-align: right">申请付款金额（小写）：</td>
							<td><input type="text" id="zbjk_sqfkjexx"
								value="${mapData.sqfkjexx }" class="easyui-numberbox"
								data-options="min:0.01,precision:2,groupSeparator:','"
								style="text-align: right;" onkeyup="jeChange(this.value);"
								onblur="jeBlurFNumber(this.value);"
								onfocus="jeFocusRNumber(this.value);" />&nbsp;&nbsp;元</td>
							<td style="text-align: right">申请付款金额（大写）：</td>
							<td><input type="text" readonly="readonly"
								id="zbjk_sqfkjedx" value="${mapData.sqfkjedx }"
								style="text-align: right" /></td>

						</tr>
						<tr>
							<td style="text-align: right">申请付款日：</td>
							<td><input id="zbjk_sqfkr" type="text"
								value="${mapData.sqfkr }" class="easyui-datebox"
								data-options="currentText:''" /></td>
							<td style="text-align: right">申请用途：</td>
							<td><input id="zbjk_sqyt" type="text"
								value="${mapData.sqyt }" /></td>


						</tr>
						<tr>
							<td style="text-align: right">申请到期日：</td>
							<td><input id="zbjk_sqdqr" type="text"
								value="${mapData.sqdqr}" data-options="currentText:''" /></td>
							<td style="text-align: right">本次冻结现款额度：</td>
							<td><input type="text" id="zbjk_djxked"
								value="${mapData.sqfkjexx}" readonly="readonly"
								style="text-align: right;"></input></td>
						</tr>
						<tr>
							<td style="text-align: right">备注：</td>
							<td colspan="3"><input type="text" value="${mapData.bzxx}"
								id="zbjk_bzxx" style="width: 99%" maxlength="64" /></td>
						</tr>
						<tr id="ptye_qdsqje">
							<td style="text-align: right">确定申请付款金额：</td>
							<td><input type="text" id="zbjk_qdsqje"
								class="easyui-numberbox" value="${mapData.qdsqje }"
								data-options="min:0,precision:2,groupSeparator:','"
								style="text-align: right;"
								onblur="qdsqjeBlurFNumber(this.value);" />&nbsp;&nbsp;元</td>
						</tr>
						<tr id="th_tr">
							<td style="text-align: right">确定放款日：</td>
							<td><input id="zbjk_qdfkr" type="text"
								value="${mapData.qdfkr}" required="required"
								data-options="required:true,currentText:''" /></td>
							<td style="text-align: right">确定到期日：</td>
							<td><input id="zbjk_qddqr" type="text"
								value="${mapData.qddqr}" required="required"
								data-options="required:true,currentText:''" /></td>
						</tr>

					</table>
				</form>
			</div>
		</div>

		<div class="sp_box" id="spjg_info_hide">
			<div class="sp_bt">审批结果</div>
			<div class="sp_infor">
				<ul>
					<li><span class="mc">审批结果：</span><span id="zbjk_spjg">${mapData.spjg }</span></li>
					<li><span class="mc">审批日期：</span><span id="zbjk_sprq">${mapData.sprq }</span></li>
				</ul>
				<ul>
					<li><span class="mc">审批金额：</span><span id="zbjk_spje">${mapData.spje }</span>元</li>
					<li><span class="mc">审批人：</span> <span id="zbjk_spr">${mapData.spr }</span></li>
				</ul>
			</div>
		</div>

		<div class="sp_box" id="ptyeqrjg_info_hide">
			<div class="sp_bt">平台业财确认情况</div>
			<div class="sp_infor">
				<ul>
					<li><span class="mc">确认人名：</span> <span id="zbjk_ptyeqrrm">${mapData.ptycqrrm }</span></li>
					<li><span class="mc">确认状态：</span><span id="zbjk_ptyespzt">${mapData.ptycspzt }</span></li>
				</ul>
				<ul>
					<li></li>
					<li><span class="mc">确认时间：</span><span id="zbjk_ptyeqrsj">${mapData.ptycqrsj }</span></li>
				</ul>
			</div>
		</div>

		<div class="sp_box" id="pjwhjg_info_hide">
			<div class="sp_bt">票据确认情况</div>
			<div class="sp_infor">
				<ul>
					<li><span class="mc">票据确认人名：</span><span id="zbjk_pjwhrm">${mapData.pjwhrm }</span></li>
					<li><span class="mc">票据确认状态：</span><span id="zbjk_pjwhspzt">${mapData.pjwhspzt }</span></li>
				</ul>
				<ul>
					<li><span class="mc">票据确认时间：</span> <span id="zbjk_pjwhsj">${mapData.pjwhsj }</span></li>

				</ul>
			</div>
		</div>

		<div id="gys_dialog"></div>
	</div>
	<jsp:include page="../../sxyw/explanation.jsp" flush="true" />
</body>
</html>