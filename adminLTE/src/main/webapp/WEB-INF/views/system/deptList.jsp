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
		<input type="text" name="filter_deptCode" class="easyui-validatebox" data-options="width:150,prompt: '部门号'"/>
		<input type="text" name="filter_deptName" class="easyui-validatebox" data-options="width:150,prompt: '部门名称'"/>
		<input type="text" name="filter_parentCode" class="easyui-datebox" data-options="width:150,prompt: '上级编号'"/>
		<input type="text" name="filter_companyCode" class="easyui-validatebox" data-options="width:150,prompt: '公司编号'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="cx()">查询</a>
		<shiro:hasPermission name="sys:tzgg:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addMemberJob();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateMemberJob()">修改</a>
		</shiro:hasPermission>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="memberJob"></table>
</div>
<div id="dlg"></div>


<script type="text/javascript">
    var memberJob;
    var d;
    $(function(){
        memberJob=$('#memberJob').datagrid({
            method: "post",
            url:'${ctx}/system/memberJob/getDeptList',
            fit : true,
            fitColumns : true,
            border : false,
            idField : 'id',
            sortName: 'id',
            striped:true,
            pagination:true,
            rownumbers:true,
            pageNumber:1,
            pageSize : 5,
            pageList : [ 2, 5, 10, 20, 30, 50 ],
            singleSelect:true,
            columns:[[
                {field:'deptCode',title:'部门编号',sortable:true,width:50},
                {field:'deptName',title:'部门名称',sortable:true,width:50},
                {field:'parentCode',title:'父级编号',sortable:true,width:60},
                {field:'companyCode',title:'公司编号',sortable:true,width:60}
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
        memberJob.datagrid('load',obj);
    }

    //弹窗增加
    function addMemberJob() {
        d=$("#dlg").dialog({
            title: '新增部门信息',
            width: 700,
            height: 400,
            href:'${ctx}/system/memberJob/create1',
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
    function updateMemberJob(){
        var row = memberJob.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改部门信息',
            width: 700,
            height: 400,
            href:'${ctx}/system/memberJob/create1?id='+row.id,
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