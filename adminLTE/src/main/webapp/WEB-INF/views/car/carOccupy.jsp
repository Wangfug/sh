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
		<input type="text" name="filter_carCode" class="easyui-validatebox" data-options="width:150,prompt: '车牌号'"/>
		<input type="text" name="filter_timeStart" class="easyui-datetimebox" data-options="width:150,prompt: '开始时间'"/>
		<input type="text" name="filter_timeEnd" class="easyui-datetimebox" data-options="width:150,prompt: '结束时间'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCarOccupy()">查询</a>
		<shiro:hasPermission name="sys:tzgg:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCarOccupy();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCarOccupy()">修改</a>
		</shiro:hasPermission>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="carOccupy"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var carOccupy;
    var d;
    $(function(){
        carOccupy=$('#carOccupy').datagrid({
            method: "post",
            url:'${ctx}/web/carOccupy/getList',
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
//                {field:'carCode',title:'车型',sortable:true,width:30},
                {field:'car_code',title:'车牌号',sortable:true,width:50},
                {field:'time_start',title:'开始时间',formatter:function(value,row,index){
                    if(value)
                        var unixTimestamp = new Date(value);
                    else
                        return "";
                    return unixTimestamp.toLocaleString();
                },sortable:true,width:50},
                {field:'time_end',title:'结束时间',formatter:function(value,row,index){
                    if(value)
                        var unixTimestamp = new Date(value);
                    else
                        return "";
                    return unixTimestamp.toLocaleString();
                },sortable:true,width:50},
            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCarOccupy(){
        var obj=$("#searchFrom").serializeObject();
        carOccupy.datagrid('load',obj);
    }

    //弹窗增加
    function addCarOccupy() {
        d=$("#dlg").dialog({
            title: '新增车辆占用信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carOccupy/create',
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
    function updateCarOccupy(){
        var row = carOccupy.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改车辆占用信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carOccupy/create?id='+row.id,
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