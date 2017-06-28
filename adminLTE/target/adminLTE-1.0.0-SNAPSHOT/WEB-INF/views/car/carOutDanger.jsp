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
		<input type="text" name="filter_orderNo" class="easyui-validatebox" data-options="width:150,prompt: '订单号'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCarOutDanger()">查询</a>
		<%--<shiro:hasPermission name="sys:tzgg:edit">--%>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCarOutDanger();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCarOutDanger()">修改</a>
		<%--</shiro:hasPermission>--%>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="carOutDanger"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var carOutDanger;
    var d;
    $(function(){
        carOutDanger=$('#carOutDanger').datagrid({
            method: "post",
            url:'${ctx}/web/carOutDanger/getList',
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
                {field:'insurance_time',title:'出险日期',formatter:function(value,row,index){
                    if(value)
                    var unixTimestamp = new Date(value);
                    else
                        return "";
                    return unixTimestamp.toLocaleString();
                },sortable:true,width:50},
                {field:'insurance_type',title:'险种',formatter:function(value,row,index){
                    var result = "";
                    if(value){
                        var insuranceTypes = value.split(",");
                        var dictsForInsurance1 = '${dictsForInsurance1}';
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
                },sortable:true,width:50},
                {field:'order_no',title:'订单号',sortable:true,width:50},
                {field:'repair_money',title:'修理费用(元)',sortable:true,width:50},
                {field:'compensation',title:'赔偿款项',sortable:true,width:50},
                {field:'bad_component',title:'损坏部位',sortable:true,width:50},
                {field:'before_image',title:'出险前',sortable:true,width:50},
                {field:'after_image',title:'出险后',sortable:true,width:50},
                {field:'remark',title:'备注',sortable:true,width:50},
            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCarOutDanger(){
        var obj=$("#searchFrom").serializeObject();
        carOutDanger.datagrid('load',obj);
    }

    //弹窗增加
    function addCarOutDanger() {
        d=$("#dlg").dialog({
            title: '新增车辆出险信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carOutDanger/create',
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
    function updateCarOutDanger(){
        var row = carOutDanger.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改车辆出险信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carOutDanger/create?id='+row.id,
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