<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>票据贴现</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript"
	src="${ctx}/static/js/ptAccount/pjtx.js?${currentTime}"></script>
<style type="text/css">
table thead tr th {
	font-size: 12pt;
}
</style>
<link rel="stylesheet" href="${ctx }/static/css/stylezycd.css" />
<link rel="stylesheet" href="${ctx }/static/css/stylesp.css" />
</head>
<body>
	<div id="div_pjtx" class="main">
		<div style="margin: 10px 0px 10px 0px;">
			<shiro:hasPermission name="sxyw:pjtx:save">
				<a id="fill_save" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-save'" onclick="pjtxOpt('tempsave');">
					暂存 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:pjtx:submit">
				<a id="fill_submit" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-add'" onclick="pjtxOpt('submit');">
					提交 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:pjtx:delete">
				<a id="fill_delete" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-remove'" onclick="pjtxOpt('delete');">
					删除 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:pjtx:pass">
				<a id="sp_ok" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-ok'" onclick="pjtxApprove('1');">
					通过 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:pjtx:back">
				<a id="sp_no" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-no'" onclick="pjtxApprove('0');">
					退回 </a>
			</shiro:hasPermission>
			<shiro:hasPermission name="sxyw:pjtx:close">
				<a id="sp_close" class="easyui-linkbutton" href="#"
					data-options="iconCls:'icon-no'" onclick="pjtxclose();"
					style="display: none;"> 关闭 </a>
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
			<div class="bt">票面信息</div>
			<div class="infor" style="height: 150px;">
				<form id="form_xxdh" method="post">
					<input type="hidden" value="${mapData.ptgsdm }" id="pjtx_ptgsdm" />
					<input type="hidden" value="${mapData.clid }" id="pjtx_PrimaryKey" />
					<input type="hidden" value="${mapData.taskId }" id="pjtx_taskId" />
					<input type="hidden" value="${mapData.cljg }" id="pjtx_cljg" /> <input
						type="hidden" value="${mapData.clrq }" id="pjtx_clrq" /> <input
						type="hidden" value="${mapData.clr }" id="pjtx_clr" /> <input
						type="hidden" value="${mapData.spzt }" id="lczt" /> <input
						type="hidden" value="${mapData.pmdqr}" id="pmdqrxx" /> <input
						type="hidden" value="${mapData.pmcprq}" id="pmcprxx" />
					<table style="font-size: 11pt; margin: 5px 0px 20px 0px;">
						<tr>
							<td style="text-align: right">票据类型：</td>
							<td><select id="pjtx_pjlx" name="pjtx_pjlx"
								class="easyui-combobox"
								data-options="required:true,editable:false" style="width: 250px">
									<option value="1"
										<c:if test="${mapData.pjlx eq 1}">selected="selected"</c:if>>纸质承兑</option>
									<option value="2"
										<c:if test="${mapData.pjlx eq 2}">selected="selected"</c:if>>电子承兑</option>
									<option value="3"
										<c:if test="${mapData.pjlx eq 3}">selected="selected"</c:if>>票存</option>
							</select></td>
							<td style="width: 140px; text-align: right">票面出票人名称：</td>
							<td><input type="text" id="pjtx_pmcprmc"
								class="easyui-validatebox" style="width: 250px"
								value="${mapData.pmcprmc}" /></td>
							<input type="hidden" id="pjtx_pmcprmc_hidden"
								value="${mapData.pmcprmc}" />
						</tr>
						<tr>

							<td style="text-align: right">票号：</td>
							<td><input id="pjtx_ph" name="pjtx_ph" type="text"
								value="${mapData.ph}" class="easyui-validatebox"
								style="width: 250px" /></td>
							<td style="width: 140px; text-align: right">票面付款行名称：</td>
							<td><input type="text" class="easyui-validatebox"
								id="pjtx_pmfkhmc" value="${mapData.pmfkhmc}"
								style="width: 250px;" /></td>
							<input type="hidden" id="pjtx_pmfkhmc_hidden"
								value="${mapData.pmfkhmc}" />
						</tr>
						<tr>

							<td style="text-align: right">票面出票日：</td>
							<td><input id="pjtx_pmcprq" name="pjtx_pmcprq" type="text"
								value="${mapData.pmcprq}" class="easyui-datebox"
								data-options="editable:false,  onSelect: function(rec){commSetQXDATE('pjtx_pmcprq','pjtx_pjqx','pjtx_pmdqr')}"
								style="width: 250px" /></td>
							<td style="width: 140px; text-align: right">票面金额（小写）：</td>
							<td><input type="text" id="pjtx_pmjexx"
								value="${mapData.pmjexx}" style="width: 250px;"
								class="easyui-numberbox" data-options="min:0,precision:2"
								onkeyup="hkjeChange(this.value);"
								onblur="hkjeChange(this.value);" /></td>
						</tr>
						<tr>

							<td style="text-align: right">票面到期日：</td>
							<td><input id="pjtx_pmdqr" name="pjtx_pmdqr" type="text"
								value="${mapData.pmdqr}" class="easyui-datebox"
								data-options="editable:false" style="width: 250px" /></td>
							<td style="width: 140px; text-align: right">票面金额（大写）：</td>
							<td><input type="text" class="easyui-validatebox"
								readonly="readonly" id="pjtx_pmjedx" value="${mapData.pmjedx}"
								style="width: 250px;" /></td>
						</tr>
						<tr>

							<td style="text-align: right">票据期限：</td>
							<td><select id="pjtx_pjqx" name="pjtx_pjqx"
								class="easyui-combobox"
								data-options="required:true,editable:false,onSelect: function(rec){commSetQXDATE('pjtx_pmcprq','pjtx_pjqx','pjtx_pmdqr')}"
								style="width: 250px">
									<option value="3"
										<c:if test="${mapData.pjqx eq 3}">selected="selected"</c:if>>3个月</option>
									<option value="6"
										<c:if test="${mapData.pjqx eq 6}">selected="selected"</c:if>>6个月</option>
							</select></td>
							<td style="width: 140px; text-align: right">票面收款人名称：</td>
							<td><input type="text" class="easyui-validatebox"
								id="pjtx_pmskrmc" value="${mapData.pmskrmc}"
								style="width: 250px;" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>

		<div class="sp_box" id="cljg_info_hide">
			<div class="sp_bt">处理结果</div>
			<div class="sp_infor" style="height: 120px">
				<ul>
					<li><span class="mc">处理结果：</span><span id="xxdh_cljg">${mapData.cljg }</span></li>
					<li><span class="mc">处理日期：</span><span id="xxdh_clrq">${mapData.clrq }
					</span></li>
					<li><span class="mc">贴现日 ：</span><span id="txr">${mapData.txr }
					</span></li>
					<li><span class="mc">贴现天数 ：</span><span id="txts">${mapData.txts }</span></li>
				</ul>
				<ul>
					<li><span class="mc">处理人：</span><span id="xxdh_clr">${mapData.clr }</span></li>
					<li><span class="mc">贴现率：</span><span id="txl">${mapData.txl }
					</span></li>
					<li><span class="mc">贴现息：</span><span id="txx">${mapData.txx }</span></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>