<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--弹出流程选择人员下拉界面-->
<div id="pop-wfUser" class="easyui-window" title="流程审批人员选择"
	style="width: 500px; height: 200px;"
	data-options="iconCls:'icon-save',modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false,resizable:false,">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'"
			style="padding: 20px 0 0 50px; background: #eee;">
			<div style="float: left; width: 113px; height: 30px;">
				<label style="font-size: 13pt;">审批人员：</label>
			</div>
			<input type="text" id="selectUser" />
			<div
				style="width: 100%; height: 25px; margin-top: 50px; text-align: center;">
				<input type="button"
					style="margin-right: 15px; no-repeat; width: 85px; height: 25px; font-size: 14px; font-weight: bold; cursor: pointer;"
					value="确认" onclick="workFlowUserConfirm()" /> <input type="button"
					style="margin-right: 15px; no-repeat; width: 85px; height: 25px; font-size: 14px; color: red; font-weight: bold; cursor: pointer;"
					value="取消" onclick="workFlowWinClose()" />
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var wfUser_backFun;
	function showWorkFlowUserSel(backFun) {
		wfUser_backFun = backFun;
		$('#selectUser').combobox({
			url : $ptzhCommBaseURL + "/getWorkFlowUserbox/json",
			method : "POST",
			valueField : 'id',
			textField : 'text',
			panelWidth : 150,
		});
		$("#pop-wfUser").window('open');
	};

	function workFlowUserConfirm() {
		//	获取select选中的 value:
		var selectUserId = $('#selectUser').combobox('getValue');
		var selectUserName = "";
		if (isEmpty(selectUserId)) {
			$.easyui.messager.alert("请选择审批人员!");
			return;
		} else {
			//	获取select 选中的 text :
			selectUserName = $('#selectUser').find("option:selected").text();
		}
		var selectObj = {
			userId : selectUserId,
			userName : selectUserName
		};
		if (wfUser_backFun && typeof (wfUser_backFun) == "function") {
			wfUser_backFun(selectObj);
		}
		$("#pop-wfUser").window('close');
	};

	function workFlowWinClose() {
		$("#pop-wfUser").window('close');
	};
</script>
