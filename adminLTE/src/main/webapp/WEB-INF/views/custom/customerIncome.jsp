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
		<input type="text" name="filter_orderNo" class="easyui-validatebox" data-options="width:150,prompt: '订单号'"/>
		<input type="text" name="filter_carCode" class="easyui-validatebox" data-options="width:150,prompt: '车牌号'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCustomerIncome()">查询</a>
		<shiro:hasPermission name="sys:tzgg:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCustomerIncome();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCustomerIncome()">修改</a>
		</shiro:hasPermission>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="customerIncome"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var customerIncome;
    var d;
    $(function(){
        customerIncome=$('#customerIncome').datagrid({
            method: "post",
            url:'${ctx}/web/customerIncome/getList',
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
                {field:'name',title:'用户名',sortable:true,width:50},
                {field:'order_no',title:'订单号',sortable:true,width:50},
                {field:'car_code',title:'车牌号',sortable:true,width:50},
                {field:'income_account',title:'收入金额(元)',sortable:true,width:50}
            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCustomerIncome(){
        var obj=$("#searchFrom").serializeObject();
        customerIncome.datagrid('load',obj);
    }

    //弹窗增加
    function addCustomerIncome() {
        d=$("#dlg").dialog({
            title: '新增挂靠收入信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/customerIncome/create',
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
    function updateCustomerIncome(){
        var row = customerIncome.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改挂靠收入信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/customerIncome/create?id='+row.id,
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