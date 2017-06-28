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
		<input type="text" name="filter_name" class="easyui-validatebox" data-options="width:150,prompt: '门店名称'"/>
		<input type="text" name="filter_finalTime" class="easyui-datebox" data-options="width:150,prompt: '到期时间'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCustomerDiscount()">查询</a>
		<shiro:hasPermission name="web:discount:edit">
			<%--<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCustomerDiscount();">添加</a>--%>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCustomerDiscount()">修改</a>
		</shiro:hasPermission>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="customerDiscount"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var customerDiscount;
    var d;
    $(function(){
        customerDiscount=$('#customerDiscount').datagrid({
            method: "post",
            url:'${ctx}/web/customerDiscount/getList',
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
                {field:'discountMoney',title:'优惠券金额(元)',sortable:true,width:50},
                {field:'minimumConsumption',title:'最低消费(元)',sortable:true,width:50},
                {field:'discountNumber',title:'优惠券数量',sortable:true,width:50},
                {field:'discountState',title:'折扣券状态',formatter:function(value,row,index){
                    var result = "";
                    if(value==1)
                        result = "有效";
                    else if(value==2)
                        result = "失效";
                    else
                        result = "失效";
                    return result;
                },sortable:true,width:50},
                {field:'validtime',title:'有效期', formatter:function(value,row,index){
                    var unixTimestamp
                    if(value)
                        unixTimestamp  = new Date(value);
                    else
                        return "";
                    return unixTimestamp.toLocaleString();
                },sortable:true,width:50},
                {field:'discountInfo',title:'优惠券描述',sortable:true,width:50},
            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCustomerDiscount(){
        var obj=$("#searchFrom").serializeObject();
        customerDiscount.datagrid('load',obj);
    }

    //弹窗增加
    function addCustomerDiscount() {
        d=$("#dlg").dialog({
            title: '新增优惠券信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/customerDiscount/create',
            maximizable:true,
            modal:true,
            buttons:[{
                text:'确认',
                handler:function(){
                    $('#mainform1').submit();
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
    function updateCustomerDiscount(){
        var row = customerDiscount.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改优惠券信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/customerDiscount/create?id='+row.id,
            maximizable:true,
            modal:true,
            buttons:[{
                text:'修改',
                handler:function(){
                    $('#mainform1').submit();
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