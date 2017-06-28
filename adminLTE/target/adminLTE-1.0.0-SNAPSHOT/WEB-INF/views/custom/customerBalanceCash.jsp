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

		<span>用户名</span>
		<select class="easyui-combobox" name="filter_createBy"  style="width:140px;">
			<option value='' selected></option>
			<c:forEach var="customer" items="${userList}">
				<option value="${customer.id}">${customer.name}</option>
			</c:forEach>
		</select>
		<input type="text" name="filter_createTime" class="easyui-datebox" data-options="width:150,prompt: '申请时间'"/>

		<span>状态</span>
		<select class="easyui-combobox" name="filter_state"  style="width:140px;">
			<option value='' selected></option>
			<option value=0'' >等待处理</option>
			<option value='1'>已提现</option>
		</select>

		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCustomerBalanceCash()">查询</a>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="customerBalanceCash"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var customerBalanceCash;
    var d;
    $(function(){
        customerBalanceCash=$('#customerBalanceCash').datagrid({
            method: "post",
            url:'${ctx}/web/customerBalanceCash/getList',
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
                {field:'name',title:'申请人',sortable:true,width:50},
                {field:'createTime',title:'申请时间', formatter:function(value,row,index){
                    var unixTimestamp
                    if(value)
                        unixTimestamp  = new Date(value);
                    else
                        return "";
                    return unixTimestamp.toLocaleString();
                },sortable:true,width:50},
//                {field:'lastBy',title:'打款人',sortable:true,width:50},
//                {field:'lastTime',title:'打款时间', formatter:function(value,row,index){
//                    var unixTimestamp
//                    if(value)
//                        unixTimestamp  = new Date(value);
//                    else
//                        return "";
//                    return unixTimestamp.toLocaleString();
//                },sortable:true,width:50},
                {field:'money',title:'提现金额',sortable:true,width:50},
                {field:'bank',title:'银行',sortable:true,width:50},
                {field:'accountNum',title:'银行卡号',sortable:true,width:50},
                {field:'state',title:'状态',formatter:function(value,row,index){
                    if (null != value){
                        if(value == '0') {
                            value = "<a href = 'javascript:void(0)'  onclick = 'toDetail("+row.id+")' style='color:red'>等待处理</a>";
                        }else if(value == '1'){
                            value = "<a href = 'javascript:void(0)'  onclick = 'toDetail("+row.id+")' style='color:bule'>已提现</a>";
                        }
                        return  value;
                    } else {
                        return '';
                    }
                },sortable:true,width:50},

            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCustomerBalanceCash(){
        var obj=$("#searchFrom").serializeObject();
        customerBalanceCash.datagrid('load',obj);
    }

    //弹窗增加
    function toDetail(id) {
        d=$("#dlg").dialog({
            title: '提现打款信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/customerBalanceCash/cashDetail?id='+id,
            maximizable:true,
            modal:true,
        });
    }

    //弹窗修改
    function updateCustomerBalanceCash(){
        var row = customerBalanceCash.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改余额变更信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/customerBalanceCash/create?id='+row.id,
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