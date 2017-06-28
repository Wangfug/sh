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
		<input type="text" name="filter_shopName" class="easyui-validatebox" data-options="width:150,prompt: '门店名称'"/>
		<input type="text" name="filter_address" class="easyui-validatebox" data-options="width:150,prompt: '门店地址'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCarShops()">查询</a>
		<shiro:hasPermission name="web:carShop:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCarShops();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCarShops()">修改</a>
		</shiro:hasPermission>
		<%--<shiro:hasPermission name="web:carShop:add">
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addDianzhang();">添加店长</a>
		</shiro:hasPermission>--%>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="carShops"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var carShops;
    var d;
    $(function(){
        carShops=$('#carShops').datagrid({
            method: "post",
            url:'${ctx}/web/carShops/getList',
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
                {field:'shop_name',title:'门店名称',sortable:true,width:50},
                {field:'shop_code',title:'门店编码',sortable:true,width:50},
                {field:'parent_code',title:'上级编码',sortable:true,width:50},
                {field:'state',title:'运营状态',formatter:function(value,row,index){
                    var result = "";
                    if(value==1)
                        result = "运营";
                    else if(value==2)
                        result = "歇业";
                    else
                        result = "";
                    return result;
                },sortable:true,width:50},
                {field:'address',title:'地址',sortable:true,width:50},
                {field:'evaluate',title:'评价',formatter:function(value,row,index){
                    var result = "";
                    if(value)
                        result = value+"星";

                    else
                        result = "5星";
                    return result;
                },sortable:true,width:50},
                {field:'yysj',title:'营业时间',sortable:true,width:50},
                {field:'phone',title:'联系电话',sortable:true,width:50},
                {field:'dianzhang',title:'店长',sortable:true,width:50},
                {field:'remark',title:'备注',sortable:true,width:50}
            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCarShops(){
        var obj=$("#searchFrom").serializeObject();
        carShops.datagrid('load',obj);
    }

    //弹窗增加
    function addCarShops() {
        d=$("#dlg").dialog({
            title: '新增门店信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carShops/create',
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

    //增加店长
    function addDianzhang(){
        var row = carShops.datagrid('getSelected');
        console.log(row)
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '添加店长',
            width: 700,
            height: 400,
            href:'${ctx}/web/carShops/createDianzhang?id='+row.Id,
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



    //弹窗修改
    function updateCarShops(){
        var row = carShops.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改门店信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carShops/create?id='+row.Id,
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