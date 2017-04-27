<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>提现</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/js/ptAccount/tx.js?${currentTime}"></script>
<link rel="stylesheet" href="${ctx }/static/css/stylezycd.css" />
<link rel="stylesheet" href="${ctx }/static/css/stylesp.css" />
<style type="text/css">
#txtable td {
	padding: 3px;
	margin: 6px
}
</style>
</head>
<body>
	<div class="main">
		<div style="padding: 5px; width: 500px;">
			<shiro:hasPermission name="sxyw:zbtx:save">
				<a id="fill_save" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-save'" onclick="txOpt('tempsave');">
					暂存 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:zbtx:submit">
				<a id="fill_submit" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-add'" onclick="txOpt('submit');">
					提交 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:zbtx:delete">
				<a id="fill_delete" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-cancel'" onclick="txOpt('delete');">
					删除 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:zbtx:agree">
				<a id="approve" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-ok'" onclick="tx_ptycqr(1);">同意</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:zbtx:refuse">
				<a id="reject" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-no'" onclick="tx_ptycqr(2);">拒绝</a>
			</shiro:hasPermission>
		</div>

		<jsp:include page="/WEB-INF/views/sxyw/basicInfo.jsp" flush="true" />

		<div class="infor_box">
			<div class="bt">
				平台账户&nbsp;&nbsp;<a href="#" onclick="">月度余额变动履历</a>
			</div>
			<div class="infor">
				<ul>
					<li><span class="mc">账存余额：</span><span id="ptzh_tx_zcye"></span>&nbsp;&nbsp;元</li>
					<li><span class="mc">可用余额：</span><span id="ptzh_tx_ye"></span>&nbsp;&nbsp;元</li>
				</ul>
				<ul>
					<li><span class="mc">冻结余额：</span><span id="ptzh_tx_dj"></span>&nbsp;&nbsp;元</li>
					<li><span class="mc">逾期未还：</span><span id="ptzh_tx_zhyqbzje"></span>&nbsp;&nbsp;元</li>
				</ul>
			</div>
		</div>


		<div class="content"></div>
		<div class="infor_box">
			<div class="bt">
				用款申请信息&nbsp;&nbsp;<a href="#" onclick="">用信计划</a>
			</div>
			<div class="infor infor_01">
				<form id="form_tx" method="post">
					<input type="hidden" value="${userRole }" id="tx_userRole" /> <input
						type="hidden" value="${ptgsdm }" id="tx_ptgsdm" /> <input
						type="hidden" value="${ssfb }" id="tx_ssfb" /> <input
						type="hidden" value="${mapData.clid }" id="tx_PrimaryKey" /> <input
						type="hidden" value="${paramsMap }" id="tx_taskId" /> <input
						type="hidden" value="${mapData.wfId }" id="tx_wfId" /> <input
						type="hidden" value="${gwRole }" id="tx_gwRole" /> <input
						type="hidden" value="${paramsMap }" id="tx_paramsMap" /> <input
						type="hidden" value="${mapData.isViewZhLs }" id="isViewZhLs" /> <input
						type="hidden" value="${mapData.spzt }" id="spzt" />
					<table style="font-size: 11pt; margin: 5px 0px 20px 0px;"
						id="txtable">
						<tr>
							<td style="text-align: right">提现金额（小写）：</td>
							<td><input type="text" id="tx_txjexx"
								style="text-align: right;" class="easyui-numberbox"
								value="${mapData.txjexx }" onkeyup="jeChange(this.value);"
								onblur="jeBlurFNumber(this.value);"
								onfocus="jeFocusRNumber(this.value);"
								data-options="min:0.01,precision:2,groupSeparator:','"></input>&nbsp;&nbsp;元</td>
						</tr>
						<tr>

							<td style="text-align: right">提现金额（大写）：</td>
							<td><input type="text" readonly="readonly"
								value="${mapData.txjedx }" id="tx_txjedx"
								style="text-align: right;" /></td>
							<td style="text-align: right">付款方式：</td>
							<td><input id="tx_fkfs" type="text" name="fkfs"
								value="${mapData.fkfs }" /></td>
						</tr>
						<tr>
							<td style="text-align: right">本次冻结账户余额：</td>
							<td style=""><input type="text" readonly="readonly"
								style="text-align: right" value="${mapData.txjexx }"
								data-options="min:0,precision:2" id="tx_djzhye"></input></td>
							<td style="text-align: right">提现用途：</td>
							<td><input id="tx_txyt" type="text" name="txyt"
								value="${mapData.txyt }" /></td>
						</tr>
						<tr>
							<td style="text-align: right">付款行名称：</td>
							<td style=""><select id="tx_fkhmc" onchange="selectfkh()">${fkhComboHtml}</select></td>
							<td style="text-align: right">付款行账号：</td>
							<td><input type="text" id="tx_fkhzh"
								value="${mapData.fkhzh }" readonly="readonly" /></td>
						</tr>
						<tr>
							<td style="text-align: right">收款行名称：</td>
							<td style=""><select id="tx_skhmc" onchange="selectskh()">${skhComboHtml}</select></td>
							<td style="text-align: right">收款行账号：</td>
							<td><input type="text" id="tx_skhzh"
								value="${mapData.skhzh }" readonly="readonly" /></td>
						</tr>
						<tr>
							<td style="text-align: right">备注：</td>
							<td colspan="5"><input type="text" value="${mapData.bz }"
								id="tx_bz" style="width: 99%" maxlength="64" /></td>
						</tr>
					</table>
				</form>
			</div>

			<div class="sp_box" id="spjg_info_hide">
				<div class="sp_bt">审批结果</div>
				<div class="sp_infor">
					<ul>
						<li><span class="mc">审批结果：</span><span id="ptzh_tx_spjg">${mapData.spjg }</span></li>
						<li><span class="mc">审批日期：</span><span id="ptzh_tx_sprq">${mapData.sprq }</span></li>
					</ul>
					<ul>
						<li><span class="mc">审批金额：</span><span id="ptzh_tx_spje">${mapData.spje }</span>元</li>
						<li><span class="mc">审批人：</span> <span id="ptzh_tx_spr">${mapData.spr }</span></li>
					</ul>
				</div>
			</div>

			<div class="sp_box" id="ptyeqrjg_info_hide">
				<div class="sp_bt">平台业财确认情况</div>
				<div class="sp_infor">
					<ul>
						<li><span class="mc">确认人名：</span> <span id="tx_ptyeqrrm">${mapData.ptycqrrm }</span></li>
						<li><span class="mc">确认状态：</span><span id="tx_ptyespzt">${mapData.ptycspzt }</span></li>
					</ul>
					<ul>
						<li></li>
						<li><span class="mc">确认时间：</span><span id="tx_ptyeqrsj">${mapData.ptycqrsj }</span></li>
					</ul>
				</div>
			</div>


		</div>
	</div>

	<jsp:include page="../../sxyw/explanation.jsp" flush="true" />
	<script>
		$('#tx_skhzh').val($('#tx_skhmc').val());
	</script>
</body>
</html>