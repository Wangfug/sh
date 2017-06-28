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
		<input type="text" name="filter_fault_order" class="easyui-validatebox" data-options="width:150,prompt: '订单号'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCarFault()">查询</a>
		<shiro:hasPermission name="sys:tzgg:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCarFault();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCarFault()">修改</a>
		</shiro:hasPermission>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="carFaultHandle"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var carFaultHandle;
    var d;
    $(function(){
        carFaultHandle=$('#carFaultHandle').datagrid({
            method: "post",
            url:'${ctx}/web/carFaultHandle/getCarFaultHandleDetailList',
            fit : true,
            fitColumns : true,
            border : false,
            idField : 'id',
            sortName: 'id',
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
                {field:'fault_componengt',title:'故障部位'
					,formatter:function(value,row,index){
                    var result = "";
                    if(value){
                        var insuranceTypes = value.split(",");
                        var dictsForInsurance1 = '${dictsForCarFaultComponengt}';
                        if(dictsForInsurance1)
                            dictsForInsurance1 = eval(dictsForInsurance1);
                        for(var i=0;i<insuranceTypes.length;i++){
                            for(var j=0;j<dictsForInsurance1.length;j++){
                                if(insuranceTypes[i]==dictsForInsurance1[j].code){
                                    result+=dictsForInsurance1[j].name+",";
                                    dictsForInsurance1.splice(j,1);
                                    break;
                                }
                            }
                        }
                        if(result) {
                            result = result.substring(0, result.length - 1);
                            result = "<span title = '"+result+"'>" + result + "</span>";
                        }
                    }
                    else
                        result =  "";
                    return result;
                }
                    ,sortable:true,width:50},
                {field:'fault_descr',title:'故障描述',sortable:false,width:100},
                {field:'attachment',title:'照片',sortable:true,width:50},
                {field:'fault_time',title:'故障日期',formatter:function(value,row,index){
                    if(value){
                        var unixTimestamp = new Date(value);
                        return unixTimestamp.toLocaleString();
                    }else{
                        return "";
                    }
                },sortable:true,width:50},
                {field:'repair_time',title:'修理日期',formatter:function(value,row,index){
                    if(value){
                        var unixTimestamp = new Date(value);
                        return unixTimestamp.toLocaleString();
                    }else{
                        return "";
                    }
                },sortable:true,width:50},
                {field:'handle_by',title:'经手办理人',sortable:true,width:50},
                {field:'order_no',title:'订单号',sortable:true,width:50},
                {field:'get_money',title:'得到赔偿款',sortable:true,width:50},
                {field:'compensator',title:'赔偿者',sortable:true,width:50},
                {field:'repair_money',title:'修理费用',sortable:true,width:50},
                {field:'shop_name',title:'出款门店',sortable:true,width:50},
                {field:'remark',title:'备注',sortable:true,width:50},
            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCarFault(){
        var obj=$("#searchFrom").serializeObject();
        carFaultHandle.datagrid('load',obj);
    }

    //弹窗增加
    function addCarFault() {
        d=$("#dlg").dialog({
            title: '新增车辆故障信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carFaultHandle/addCarFaultHandle',
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
    function updateCarFault(){
        var row = carFaultHandle.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改车辆故障信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carFaultHandle/update/'+row.id,
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