<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>线下电汇</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<%@ include file="/WEB-INF/views/include/pop_wfUser.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/js/ptAccount/xxdh.js?${currentTime}"></script>
<link rel="stylesheet" href="${ctx }/static/css/stylezycd.css" />
<link rel="stylesheet" href="${ctx }/static/css/stylesp.css" />
</head>
<body>
	<div id="div_xxdh" class="main">
		<div style="margin: 10px 0px 10px 0px;">
			<shiro:hasPermission name="sxyw:xxdh:save">
				<a id="fill_save" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-save'" onclick="xxdhOpt('tempsave');">
					暂存 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:xxdh:submit">
				<a id="fill_submit" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-add'" onclick="xxdhOpt('submit');">
					提交 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:xxdh:delete">
				<a id="fill_delete" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-cancel'" onclick="xxdhOpt('delete');">
					删除 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:xxdh:close">
				<a id="fill_close" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-no'" onclick="xxdhClose();"> 关闭 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:xxdh:pass">
				<a id="sp_ok" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-ok'" onclick="xxdhApprove('1');">
					通过 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:xxdh:back">
				<a id="sp_no" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-back'" onclick="xxdhApprove('0');">
					退回 </a>
			</shiro:hasPermission>
		</div>

		<div class="infor_box">
			<div class="bt">基本信息</div>
			<div class="infor">
				<ul>
					<li><span class="mc">所属分部：</span><span id="xxdh_ssfbmc">${mapData.ssfbmc }</span></li>
					<li><span class="mc">公司名称：</span><span id="xxdh_ptgsmc">
							${mapData.ptgsmc }</span></li>
				</ul>
				<ul>
					<li><span class="mc">申请日期：</span><span id="xxdh_sqrq">
							${mapData.sqrq }</span></li>
					<li><span class="mc">申请人：</span><span id="xxdh_sqr">
							${mapData.sqr }</span></li>
				</ul>
			</div>
		</div>

		<div class="infor_box">
			<div class="bt">汇款信息</div>
			<div class="infor" style="height: 125px;">
				<form id="form_xxdh" method="post">
					<input type="hidden" value="${mapData.ptgsdm }" id="xxdh_ptgsdm" />
					<input type="hidden" value="${mapData.clid }" id="xxdh_PrimaryKey" />
					<input type="hidden" value="${mapData.taskId }" id="xxdh_taskId" />
					<input type="hidden" value="${mapData.wfId }" id="xxdh_wfId" /> <input
						type="hidden" value="${mapData.isViewZhLs }" id="isViewZhLs" /> <input
						type="hidden" value="${mapData.paramsMap }" id="paramsMap" /> <input
						type="hidden" value="${mapData.spzt }" id="spzt" />

					<table style="font-size: 11pt; margin: 5px 20px 20px 0px;">
						<tr>
							<td style="text-align: right">汇出日期：</td>
							<td><input id="xxdh_hcrq" type="text"
								value="${mapData.hcrq }" /></td>
							<td style="text-align: right">汇款金额（小写）：</td>
							<td><input type="text" id="xxdh_hkjexx"
								value="${mapData.hkjexx }" class="easyui-numberbox"
								data-options="min:0.01,precision:2,groupSeparator:','"
								style="text-align: right;" onkeyup="jeChange(this.value);"
								onblur="jeBlurFNumber(this.value);"
								onfocus="jeFocusRNumber(this.value);" />&nbsp;&nbsp;元</td>
						</tr>
						<tr>
							<td style="text-align: right">汇款公司名称：</td>
							<td><input id="xxdh_hkgsmc" type="text"
								value="${mapData.hkgsmc}" style="" /></td>
							<td style="text-align: right">汇款金额（大写）：</td>
							<td><input type="text" readonly="readonly" id="xxdh_hkjedx"
								value="${mapData.hkjedx }" style="text-align: right" /></td>

						</tr>
						<tr>
							<td style="text-align: right">汇款用途：</td>
							<td colspan="3"><input type="text" value="${mapData.hkyt }"
								id="xxdh_hkyt" style="width: 93%" maxlength="64" /></td>
						</tr>
						<tr id="th_tr">
							<td style="text-align: right">退回原因：</td>
							<td colspan="3"><input type="text" id="xxdh_thReason"
								value="${mapData.thReason }" style="width: 93%" maxlength="200" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>

		<div class="infor_box">
			<div class="bt">请按照以下收款人信息进行汇款</div>
			<div class="infor">
				<ul>
					<li><span class="mc">开户行名称：</span><span id="xxdh_khhmc">${mapData.khhmc }</span></li>
					<li><span class="mc">开户行账号：</span><span id="xxdh_khhzh">${mapData.khhzh }</span></li>
				</ul>
				<ul>
					<li><span class="mc">公司名称：</span><span id="xxdh_gsmc">舜昊集团</span></li>
					<li><span class="mc"> </span><span> </span></li>
				</ul>
			</div>
		</div>

		<div class="sp_box" id="cljg_info_hide">
			<div class="sp_bt">审批结果</div>
			<div class="sp_infor">
				<ul>
					<li><span class="mc">审批结果：</span><span id="xxdh_cljg">${mapData.cljg }</span></li>
					<li><span class="mc">审批日期：</span><span id="xxdh_clrq">${mapData.clrq }
					</span></li>
				</ul>
				<ul>
					<li><span class="mc">审批人：</span><span id="xxdh_clr">${mapData.clr }</span></li>
					<li><span class="mc"></span><span> </span></li>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../../sxyw/explanation.jsp" flush="true" />
</body>
</html>