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
		<select name="filter_state"  style="width:120px;vertical-align: middle;height: 22px;">
			<option value="" selected>请选择处理状态</option>
			<option value="1" >已处理</option>
			<option value="2">未处理</option>
		</select>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCarBreakRule()">查询</a>
		<shiro:hasPermission name="sys:tzgg:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCarBreakRule();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCarBreakRule()">修改</a>
		</shiro:hasPermission>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="CarBreakRule"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var CarBreakRule;
    var d;
    $(function(){
        CarBreakRule=$('#CarBreakRule').datagrid({
            method: "post",
            url:'${ctx}/web/carBreakRule/carBreakRuleDetailList',
            fit : true,
            fitColumns : true,
            border : false,
            idField : 'id',
            sortName: 'Create_time',
            sortOrder: "desc",
            striped:true,
            pagination:true,
            rownumbers:true,
            pageNumber:1,
            pageSize : 20,
            pageList : [ 5, 10, 20, 30, 40, 50 ],
            singleSelect:true,
            columns:[[
                {field:'car_code',title:'车牌号',sortable:true,width:30},
                {field:'illegal_no',title:'违章单号',sortable:true,width:30},
                {field:'illegal_time',title:'违章日期',formatter:function(value,row,index){
                    if(value){
                        var unixTimestamp = new Date(value);
                        return unixTimestamp.toLocaleString();
                    }else{
                        return "";
                    }
                },sortable:true,width:50},
                {field:'state',title:'处理状态',formatter:function(value,row,index){
                    if(value=="1"){
                        value="已处理";
                    }else if(value=="2"){
                        value="未处理";
                    }
                    return value;
                },sortable:true,width:50},
                {field:'illegal_position',title:'违章地点',sortable:true,width:50},
                {field:'Illegal_content',title:'违章内容',sortable:true,width:50},
                {field:'illegal_deduction',title:'违章扣分',sortable:true,width:50},
                {field:'violation_fine',title:'违章罚款',sortable:true,width:50},
                {field:'deal_shop',title:'处理门店',formatter:function(value,row,index){
                    var carShops = '${carShops}';
                    console.log(carShops)
                    if(carShops){
                        carShops = eval(carShops);
                        for(var n=0;n<carShops.length;n++){
                            if(value==carShops[n].id){
                                value = carShops[n].shopName;
                                break;
                            }
                        }
                    }else{
                        value = "";
                    }
                    return value;
                },sortable:true,width:50},
                {field:'remark',title:'备注',sortable:true,width:50}
        ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCarBreakRule(){
        var obj=$("#searchFrom").serializeObject();
        CarBreakRule.datagrid('load',obj);
    }

    //弹窗增加
    function addCarBreakRule() {
        d=$("#dlg").dialog({
            title: '新增车辆违章信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carBreakRule/addCarBreakRule',
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
    function updateCarBreakRule(){

        var row = CarBreakRule.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改车辆违章信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carBreakRule/update/'+row.id,
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