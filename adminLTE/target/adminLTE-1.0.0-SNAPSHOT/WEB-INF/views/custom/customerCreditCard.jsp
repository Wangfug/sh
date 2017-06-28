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
		<input type="text" name="filter_name" class="easyui-validatebox" data-options="width:150,prompt: '持卡人姓名'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCustomerCreditCard()">查询</a>
		<shiro:hasPermission name="sys:tzgg:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCustomerCreditCard();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCustomerCreditCard()">修改</a>
		</shiro:hasPermission>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="customerCreditCard"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var customerCreditCard;
    var d;
    $(function(){
        customerCreditCard=$('#customerCreditCard').datagrid({
            method: "post",
            url:'${ctx}/web/customerCreditCard/getList',
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
                {field:'credit_state',title:'信用卡状态',formatter:function(value,row,index){
                    var result = "";
                    if(value==1)
                        result = "有效";
                    else if(value==2)
                        result = "失效";
                    else
                        result = "";
                    return result;
                },sortable:true,width:50},
                {field:'credit_no',title:'信用卡号',sortable:true,width:50},
                {field:'credit_bank',title:'开户银行',sortable:true,width:50},
                {field:'security',title:'安全码',sortable:true,width:50},
                {field:'linkephone',title:'预留手机号码',sortable:true,width:50},
                {field:'validity_time',title:'有效期',formatter:function(value,row,index){
                        var unixTimestamp
                        if(value)
                            unixTimestamp  = new Date(value);
                        else
                            return "";
                        return unixTimestamp.toLocaleString();
                },sortable:true,width:50},
                {field:'name',title:'持卡人',sortable:true,width:50}
            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCustomerCreditCard(){
        var obj=$("#searchFrom").serializeObject();
        customerCreditCard.datagrid('load',obj);
    }

    //弹窗增加
    function addCustomerCreditCard() {
        d=$("#dlg").dialog({
            title: '新增信用卡信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/customerCreditCard/create',
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
    function updateCustomerCreditCard(){
        var row = customerCreditCard.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改信用卡信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/customerCreditCard/create?id='+row.id,
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