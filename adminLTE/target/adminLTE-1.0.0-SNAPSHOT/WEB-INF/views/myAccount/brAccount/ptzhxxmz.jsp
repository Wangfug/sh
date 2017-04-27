<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>平台账户实时查询</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script type="text/javascript" src="${ctx}/static/js/brAccount/ptzhxxmz.js"></script>
<style type="text/css">
	.cxtj {
		width:10%;
		font-size: 10pt;
	}
	
	.selecttj {
		width:20%;
	}
		
	.buttontj {
		width:20%;
	}
	
	.checktj {
		width:20%;
		font-size: 10pt;
		}	

	
</style>
</head>
<body>
    <div id="div_ptzhxx" class="">
    <input type="hidden" id="ssfbmc" value="${fbmc }"/>
    <input type="hidden" id="ssfbdm" value="${fbdm }"/>    
		<form id="form_ptzhxx" method="post">
			<div style="padding: 0px 0px 15px 0px">
				<label style="font-size:14pt;font-weight:bold;">平台账户实时查询</label>
			</div>
			<div class="thick_line"></div>			
			<div style="margin:10px 0px 10px 40px;">
				<table>
					<tr>
						<td class="cxtj">分部公司:</td>
						<td class="selecttj">
							<input id="dqfb" name="dqfb" disabled="disabled" style="width:158px;"value="${fbmc }" />
						</td>
						<td class="cxtj">平台公司：</td>
						<td class="selecttj">
							<select class="easyui-combobox" style="width:200px;" id="ptgsmc">							
							</select>
						</td>
						<td class="buttontj">
							<shiro:hasPermission name="myAccount:ptAccount:cz">
	       					</shiro:hasPermission>
							<a id="btn_ptzhxx_cz" class="easyui-linkbutton" href="#" data-options="iconCls:'icon-add'"
								onclick="ptzhxxCx();">
								查询
							</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<shiro:hasPermission name="myAccount:ptAccount:cz">
	       					</shiro:hasPermission>
							<a id="btn_ptzhxx_cz" class="easyui-linkbutton" href="#" data-options="iconCls:'icon-add'"
								onclick="ptzhxxDc()">
								导出
							</a>
						</td>

					</tr>
					<tr>
						<td class="cxtj">级别：</td>
						<td class="selecttj">							
							<select class="easyui-combobox" style="width:160px;" id="xzjb">
								<option value="" selected="selected">--</option>
								<option value="1">金牌</option>
								<option value="2">银牌</option>		
								<option value="3">铜牌</option>
								<option value="4">红牌</option>						
							</select>
						</td>
						<td></td>
						<td class="checktj" >
							<input type="checkbox" id="zkzhyebz" name="zkzhyebz" />
							只看账户余额不足数
						</td>
						<td class="checktj">
							<input type="checkbox" id="zksxyebz" name="zksxyebz" />
							只看账户授信不足数							
						</td>
					</tr>
				</table>
			</div>	
			
			<div style="margin:30px 0px 0px 0px;">
				<table id="ptzhmx_datagrid"></table>  
			</div>
		</form>
	</div>  	
  </body>
</html>