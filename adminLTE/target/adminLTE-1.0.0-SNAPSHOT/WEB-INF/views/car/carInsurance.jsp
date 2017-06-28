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
		<input type="text" name="filter_insurance_id" class="easyui-validatebox" data-options="width:150,prompt: '保单号'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCarInsurance()">查询</a>
		<shiro:hasPermission name="sys:tzgg:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCarInsurance();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCarInsurance()">修改</a>
		</shiro:hasPermission>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="carInsurance"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var carInsurance;
    var d;
    $(function(){
        carInsurance=$('#carInsurance').datagrid({
            method: "post",
            url:'${ctx}/web/carInsurance/getCarInsuranceDetailList',
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
                {field:'car_code',title:'车牌号',sortable:true,width:30},
                {field:'insurance_starttime',title:'保险生效日期',formatter:function(value,row,index){
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                },sortable:true,width:50},
                {field:'insurance_endtime',title:'保险结束日期',formatter:function(value,row,index){
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                },sortable:true,width:50},
                {field:'insurance_comp',title:'保险公司',sortable:true,width:50},
                {field:'insurance_id',title:'保险单号',sortable:true,width:50},
                {field:'insurance_type',title:'险种',formatter:function(value,row,index){
                    var dictsForInsurance = '${dictsForInsurance}';
                    if(dictsForInsurance){
                        dictsForInsurance = eval(dictsForInsurance);
                        for(var n=0;n<dictsForInsurance.length;n++){
                            if(value==dictsForInsurance[n].code){
                                value = dictsForInsurance[n].name;
                                break;
                            }
                        }
                    }else{
                        value = "";
                    }
                    return value;
                },sortable:true,width:50},
                {field:'insurance_type_money',title:'保险金额',sortable:true,width:50},
                {field:'total_money',title:'总保险金额',sortable:true,width:50},
                {field:'insurance_by',title:'保险办理人',sortable:true,width:50},
                {field:'insurance_salesman',title:'保险业务员',sortable:true,width:50},
                {field:'remark',title:'备注',sortable:true,width:50},
        ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCarInsurance(){
        var obj=$("#searchFrom").serializeObject();
        carInsurance.datagrid('load',obj);
    }

    //弹窗增加
    function addCarInsurance() {
        d=$("#dlg").dialog({
            title: '新增车辆保险信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carInsurance/addCarInsurance',
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
    function updateCarInsurance(){
        var row = carInsurance.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改车辆保险信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carInsurance/update/'+row.id,
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