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
		<input type="text" name="filter_name" class="easyui-validatebox" data-options="width:150,prompt: '姓名'"/>
		<input type="text" name="filter_mobilePhone" class="easyui-validatebox" data-options="width:150,prompt: '电话号码'"/>
		<input type="text" name="filter_createTime" class="easyui-datebox" data-options="width:150,prompt: '注册时间'"/>
		<input type="text" name="filter_email" class="easyui-validatebox" data-options="width:150,prompt: '个人邮箱'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="cx()">查询</a>
		<shiro:hasPermission name="sys:tzgg:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCustomer();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCustomer()">修改</a>
		</shiro:hasPermission>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="customer"></table>
</div>
<div id="dlg"></div>


<script type="text/javascript">
    var customer;
    var d;
    $(function(){
        customer=$('#customer').datagrid({
            method: "post",
            url:'${ctx}/web/customer/getList',
            fit : true,
            fitColumns : true,
            border : false,
            idField : 'id',
            sortName: 'id',
            striped:true,
            pagination:true,
            rownumbers:true,
            pageNumber:1,
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50],
            singleSelect:true,
            columns:[[
                {field:'name',title:'用户姓名',sortable:true,width:50},
                {field:'mobile_phone',title:'用户电话',sortable:true,width:50},
                {field:'create_time',title:'注册时间', formatter:function(value,row,index){
                    var unixTimestamp
                    if(value)
                        unixTimestamp  = new Date(value);
                    else
                        return "";
                    return unixTimestamp.toLocaleString();
                },sortable:true,width:60},
                {field:'state',title:'用户状态', formatter:function(value,row,index){
                    if (null != value){
                        if(value == '0') {
                            value = "<a style='color:black'>未认证</a>";
                        }else if(value == '1'){
                            value = "<a href = 'javascript:void(0)'  onclick = 'updateCustomer("+row.id+")' style='color:blue'>等待审核</a>";
                        }else if(value == '2'){
                            value = "<a href = 'javascript:void(0)'  onclick = 'updateCustomer("+row.id+")' style='color:blue'>已认证通过</a>";
                        }else if(value == '3'){
                        value = "<a href = 'javascript:void(0)'  onclick = 'updateCustomer("+row.id+")' style='color:red'>未认证通过</a>";
                   		}else if(value == '4'){
              			  value = "<a href = 'javascript:void(0)' onclick = 'updateCustomer("+row.id+")' style='color:green'>黑名单</a>";
          				}
                        return  value;
                    } else {
                        return '';
                    }
                },sortable:true,width:50},
                {field:'credentialtype',title:'证件类型',sortable:true,width:50},
                {field:'credential_code',title:'证件号码',sortable:true,width:50},
                {field:'quasi_driving_type',title:'准驾车型',sortable:true,width:50},
                {field:'file_number',title:'驾驶证号',sortable:true,width:50},
                {field:'email',title:'个人邮箱',sortable:true,width:50},
                {field:'balance',title:'余额',sortable:true,width:50},
                {field:'integral',title:'积分',sortable:true,width:50}
            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function cx(){
        var obj=$("#searchFrom").serializeObject();
        customer.datagrid('load',obj);
    }

    //弹窗增加
    function addCustomer() {
        d=$("#dlg").dialog({
            title: '新增用户信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/customer/create',
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
    function updateCustomer(){
        var row = customer.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改用户信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/customer/create?id='+row.id,
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
    function updateCustomer(rowId){
        if(rowIsNull(rowId)) return;
        d=$("#dlg").dialog({
            title: '修改用户信息',
            width: 800,
            height: 500,
            href:'${ctx}/web/customer/authCustomer?id='+rowId,
            maximizable:true,
            modal:true
//            buttons:[{
//                text:'修改',
//                handler:function(){
//                    $('#mainform').submit();
//                }
//            },{
//                text:'取消',
//                handler:function(){
//                    d.panel('close');
//                }
//            }]
        });
    }

</script>
</body>
</html>