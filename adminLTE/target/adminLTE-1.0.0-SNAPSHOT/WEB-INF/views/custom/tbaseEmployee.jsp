<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title></title>
	<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
	<%@ include file="/WEB-INF/views/include/validation.jsp"%>
	<script type="text/javascript" src="${ctx}/static/plugins/jquery/jquery.form.js"></script>
	<script src="${ctx}/static/plugins/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body class="easyui-layout" data-options="fit: true">
<div data-options="region:'north',split:true,border:false" style="padding:5px;height:auto;overflow: hidden;">
	<form id="searchFrom" action="">
		<input type="hidden" name="filter_deptCode" id="deptCode" value = "${user.deptCode}"/>
		<input type="hidden" name="filter_comCode" id="comCode" value = "${user.companyCode}"/>
		<input type="text" name="filter_memberCode" class="easyui-validatebox" data-options="width:150,prompt: '帐号'"/>
		<input type="text" name="filter_memberName" class="easyui-validatebox" data-options="width:150,prompt: '姓名'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchTbaseEmployee()">查询</a>
		<shiro:hasPermission name="sys:emp:create">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addTbaseEmployee();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateTbaseEmployee()">修改</a>
		</shiro:hasPermission>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="tbaseEmployee"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var tbaseEmployee;
    var d;
    $(function(){
        tbaseEmployee=$('#tbaseEmployee').datagrid({
            method: "post",
            url:'${ctx}/system/user/json',
            fit : true,
            fitColumns : true,
            border : false,
            idField : 'membercode',
            sortName: 'membercode',
            striped:true,
            pagination:true,
            rownumbers:true,
            pageNumber:1,
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50 ],
            singleSelect:true,
            columns:[[
                {field:'membercode',title:'帐号',sortable:true,width:100},
                {field:'membername',title:'姓名',sortable:true,width:100},
                {field:'mobile',title:'手机号',sortable:false,width:100},
                {field:'eno',title:'工号',sortable:false,width:100},
                {field:'job',title:'岗位',sortable:true,width:100},
                {field:'dept',title:'部门',sortable:false,width:100},
                {field:'company',title:'门店',sortable:false,width:100}

            ]],
            headerContextMenu: [
                {
                    text: "冻结该列", disabled: function (e, field) { return dg.datagrid("getColumnFields", true).contains(field); },
                    handler: function (e, field) { dg.datagrid("freezeColumn", field); }
                },
                {
                    text: "取消冻结该列", disabled: function (e, field) { return dg.datagrid("getColumnFields", false).contains(field); },
                    handler: function (e, field) { dg.datagrid("unfreezeColumn", field); }
                }
            ],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
        /*tbaseEmployee=$('#tbaseEmployee').datagrid({
            method: "post",
            url:'/system/user/json',
            fit : true,
            fitColumns : true,
            border : false,
            idField : 'id',
            sortName: 'create_time',
            sortOrder: "desc",
            striped:true,
            pagination:true,
            rownumbers:true,
            pageNumber:1,
            pageSize : 20,
            pageList : [ 5, 10, 20, 30, 40, 50 ],
            singleSelect:true,
            columns:[[
                {field:'e_name',title:'姓名',sortable:true,width:50},
                {field:'e_phone',title:'手机号',sortable:true,width:50},
                {field:'e_no',title:'员工号',sortable:true,width:50},
                {field:'shop_name',title:'门店',sortable:true,width:50}
//                {field:'name',title:'职务',sortable:true,width:50}
            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });*/
    });

    //创建查询对象并查询
    function searchTbaseEmployee(){
        var obj=$("#searchFrom").serializeObject();
        tbaseEmployee.datagrid('load',obj);
    }

    //弹窗增加
    function addTbaseEmployee() {
        d=$("#dlg").dialog({
            title: '新增员工信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/tbaseEmployee/create',
            maximizable:true,
            modal:true,
            buttons:[{
                text:'确认',
                handler:function(){
                    $('#mainform').submit();
                }
            },{
                text:'取消',
                handler:function(){
                    d.panel('close');
                }
            }]
        });
    }

    //弹窗修改
    function updateTbaseEmployee(){
        var row = tbaseEmployee.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改员工信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/tbaseEmployee/create?id='+row.id,
            maximizable:true,
            modal:true,
            buttons:[{
                text:'修改',
                handler:function(){
                    $('#mainform').submit();
                }
            },{
                text:'取消',
                handler:function(){
                    d.panel('close');
                }
            }]
        });
    }

</script>
</body>
</html>