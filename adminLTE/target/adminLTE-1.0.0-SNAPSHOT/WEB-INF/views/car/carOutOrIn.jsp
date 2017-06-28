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
		<input type="text" name="filter_car_id" class="easyui-validatebox" data-options="width:150,prompt: '车牌号'"/>
		<input type="text" name="filter_dispatch_no" class="easyui-validatebox" data-options="width:150,prompt: '调度单号'"/>
		<select name="filter_in_or_out"  style="width:120px;vertical-align: middle;height: 22px;">
			<option value="" selected>请选择出/入库</option>
			<option value="1" >入库</option>
			<option value="2">出库</option>
		</select>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCarOutOrIn()">查询</a>
		<shiro:hasPermission name="sys:tzgg:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCarOutOrIn();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCarOutOrIn()">修改</a>
		</shiro:hasPermission>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="carOutOrIn"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var carOutOrIn;
    var d;
    $(function(){
        carOutOrIn=$('#carOutOrIn').datagrid({
            method: "post",
            url:'${ctx}/web/carOutOrIn/getCarOutOrInDetailList',
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
                {field:'dispatch_no',title:'调度单号',sortable:true,width:50},
                {field:'reason',title:'出入原因',sortable:true,width:50},
                {field:'car_code',title:'车牌号',sortable:true,width:30},
                {field:'out_time',title:'调度时间',formatter:function(value,row,index){
                    if(value){
                        var unixTimestamp = new Date(value);
                        return unixTimestamp.toLocaleString();
                    }else{
                        return "";
                    }
                },sortable:true,width:50},
                {field:'shop_name',title:'出/入库门店',sortable:true,width:50},
                {field:'ename',title:'出/入库店员',sortable:true,width:50},
                {field:'eno',title:'出/入库店员工号',sortable:true,width:50},
//                {field:'approve_by',title:'调度批准人',sortable:true,width:50},
                {field:'in_or_out',title:'出库/入库',sortable:true,width:50},
                {field:'remark',title:'备注',sortable:true,width:50},
        ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCarOutOrIn(){
        var obj=$("#searchFrom").serializeObject();
        carOutOrIn.datagrid('load',obj);
    }

    //弹窗增加
    function addCarOutOrIn() {
        d=$("#dlg").dialog({
            title: '新增车辆出入库信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carOutOrIn/addCarOutOrIn',
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
    function updateCarOutOrIn(){
        var row = carOutOrIn.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改车辆故障信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carOutOrIn/update/'+row.id,
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