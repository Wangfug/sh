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
		<input type="hidden" name="filter_id" id="filter_id"/>
		<input type="text" name="filter_name" class="easyui-validatebox" data-options="width:150,prompt: '用户名'"/>
		<input type="text" name="filter_createTime" class="easyui-datebox" data-options="width:150,prompt: '変动时间'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCustomerBalanceChange()">查询</a>
		<shiro:hasPermission name="sys:tzgg:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCustomerBalanceChange();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCustomerBalanceChange()">修改</a>
		</shiro:hasPermission>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="customerBalanceChange"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var customerBalanceChange;
    var d;
    $(function(){
        customerBalanceChange=$('#customerBalanceChange').datagrid({
            method: "post",
            url:'${ctx}/web/customerBalanceChange/getList',
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
                {field:'create_time',title:'変动时间', formatter:function(value,row,index){
                    var unixTimestamp
                    if(value)
                        unixTimestamp  = new Date(value);
                    else
                        return "";
                    return unixTimestamp.toLocaleString();
                },sortable:true,width:50},
                {field:'before_change',title:'变动前余额(元)',sortable:true,width:50},
                {field:'after_change',title:'变动后余额(元)',sortable:true,width:50},
                {field:'name',title:'用户',sortable:true,width:50},
            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCustomerBalanceChange(){
        var obj=$("#searchFrom").serializeObject();
        customerBalanceChange.datagrid('load',obj);
    }

    //弹窗增加
    function addCustomerBalanceChange() {
        d=$("#dlg").dialog({
            title: '新增余额变更信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/customerBalanceChange/create',
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
    function updateCustomerBalanceChange(){
        var row = customerBalanceChange.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改余额变更信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/customerBalanceChange/create?id='+row.id,
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