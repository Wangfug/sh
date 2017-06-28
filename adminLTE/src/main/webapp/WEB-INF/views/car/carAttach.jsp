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
		<input type="text" name="filter_car_code" class="easyui-validatebox" data-options="width:150,prompt: '车牌号'"/>
		<input type="text" name="filter_car_code" class="easyui-validatebox" data-options="width:150,prompt: '保单号'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCarAttach()">查询</a>
		<%--<shiro:hasPermission name="sys:tzgg:edit">--%>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCarAttach();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCarAttach()">修改</a>
		<%--</shiro:hasPermission>--%>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="CarAttach"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var CarAttach;
    var d;
    $(function(){
        CarAttach=$('#CarAttach').datagrid({
            method: "post",
            url:'${ctx}/web/carAttachExamine/getList',
            fit : true,
            fitColumns : true,
            border : false,
            idField : 'id',
            sortName: 'carcode',
            sortOrder: "desc",
            striped:true,
            pagination:true,
            rownumbers:true,
            pageNumber:1,
            pageSize : 20,
            pageList : [ 5, 10, 20, 30, 40, 50 ],
            singleSelect:true,
            columns:[[
                {field:'carcode',title:'车牌号',sortable:true,width:30},
                {field:'attachby',title:'挂靠人',sortable:true,width:50},
                {field:'attachbegin',title:'挂靠开始时间',formatter:function(value,row,index){
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                },sortable:true,width:50},
                {field:'attachend',title:'挂靠结束时间',formatter:function(value,row,index){
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                },sortable:true,width:50},
                {field:'mileage',title:'行驶里程',sortable:true,width:50},
                {field:'brand',title:'品牌',sortable:true,width:50},
                {field:'model',title:'型号',sortable:true,width:50},
                {field:'drivelic',title:'行驶证',sortable:true,width:50},
                {field:'examine',title:'审核状态',sortable:true,width:50}
            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCarAttach(){
        var obj=$("#searchFrom").serializeObject();
        CarAttach.datagrid('load',obj);
    }

    //弹窗增加
    function addCarAttach() {
        d=$("#dlg").dialog({
            title: '新增车辆故障信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/CarAttach/addCarAttach',
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
    function updateCarAttach(){
        var row = CarAttach.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改车辆故障信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/CarAttach/update/'+row.id,
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