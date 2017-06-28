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
		<input type="text" name="filter_name" class="easyui-validatebox" data-options="width:150,prompt: '客户姓名'"/>
		<input type="text" name="filter_mobile" class="easyui-validatebox" data-options="width:150,prompt: '电话号码'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="cx()">查询</a>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="customer"></table>
</div>
<button onclick="add()">666666666666666666</button>
<div id="dlg"></div>


<script type="text/javascript">
    var customer;
    var d;
    $(function(){

        //部门树
        $("#bmtree").tree({
            url: "role/bmlist",
            method:"get",
            onSelect:function(node){
                //alert("node:" + node);
                //debugger;
                //customer.datagrid("reload",{filter_deptcode:node.id,});
                if(node.id==$("#filter_deptCode").val()){
                    $("#filter_deptCode").val("");
                    $('#bmtree').find('.tree-node-selected').removeClass('tree-node-selected');
                }else{
                    $("#filter_deptCode").val(node.id);
                }
                // alert(node.id); //选择某个节点时执行的函数；
            }
        });

        customer=$('#customer').datagrid({
            method: "post",
            url:'${ctx}/mysystem/test/getList',
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
                {field:'id',title:'用户姓名',sortable:true,width:50},
                {field:'name',title:'用户电话',sortable:true,width:50},
                {field:'mobile',title:'用户城市',sortable:true,width:50},
            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
        $("#searchFrom :text").keydown(function (e) { if (e.which == 13) { cx();e.preventDefault(); } });
        $("#bmtree").tree({
            url: "role/bmlist",
            method:"get",
            onSelect:function(node){
                //customer.datagrid("reload",{filter_deptcode:node.id,});
                if(node.id==$("#filter_deptCode").val()){
                    $("#filter_deptCode").val("");
                    $('#bmtree').find('.tree-node-selected').removeClass('tree-node-selected');
                }else{
                    $("#filter_deptCode").val(node.id);
                }
                // alert(node.id); //选择某个节点时执行的函数；
            }
        });
    });

    //弹窗增加
    function add() {
        d=$("#dlg").dialog({
            title: '添加用户',
            width: 380,
            height: 380,
            href:'${ctx}/system/user/create',
            maximizable:true,
            modal:true,
            buttons:[{
                text:'确认',
                handler:function(){
                    $("#mainform").submit();
                }
            },{
                text:'取消',
                handler:function(){
                    d.panel('close');
                }
            }]
        });
    }

    //删除
    function del(){
        var row = customer.datagrid('getSelected');
        if(rowIsNull(row)) return;
        parent.$.messager.confirm('提示', '删除后无法恢复您确定要删除？', function(data){
            if (data){
                $.ajax({
                    type:'get',
                    url:"${ctx}/system/user/delete/"+row.id,
                    success: function(data){
                        successTip(data,customer);
                    }
                });
            }
        });
    }

    //弹窗修改
    function upd(){
        var row = customer.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改用户',
            width: 380,
            height: 340,
            href:'${ctx}/system/user/update/'+row.id,
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

    //用户角色弹窗
    function userForRole(){
        var row = customer.datagrid('getSelected');
        if(rowIsNull(row)) return;
        $.ajaxSetup({type : 'GET'});
        d=$("#dlg").dialog({
            title: '用户角色管理',
            width: 580,
            height: 350,
            href:'${ctx}/system/user/'+row.id+'/userRole',
            maximizable:true,
            modal:true,
            buttons:[{
                text:'确认',
                handler:function(){
                    saveUserRole();
                    d.panel('close');
                }
            },{
                text:'取消',
                handler:function(){
                    d.panel('close');
                }
            }]
        });
    }
    //用户机构弹窗
    function userForOrg(){
        var row = customer.datagrid('getSelected');
        if(rowIsNull(row)) return;
        $.ajaxSetup({type : 'GET'});
        d=$("#dlg").dialog({
            title: '用户机构管理',
            width: 580,
            height: 350,
            href:'${ctx}/system/user/'+row.id+'/userOrg',
            maximizable:true,
            modal:true,
            buttons:[{
                text:'确认',
                handler:function(){
                    saveUserOrg();
                    d.panel('close');
                }
            },{
                text:'取消',
                handler:function(){
                    d.panel('close');
                }
            }]
        });
    }

    //查看
    function look(){
        var row = customer.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改用户',
            width: 380,
            height: 340,
            href:'${ctx}/system/user/update/'+row.id,
            maximizable:true,
            modal:true,
            buttons:[{
                text:'取消',
                handler:function(){
                    d.panel('close');
                }
            }]
        });
    }
    //弹窗修改
    function cpwd(){
        var row = customer.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改密码',
            width: 350,
            height: 200,
            href:'${ctx}/system/user/updateRyxxPwd/'+row.psncode,
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
    //创建查询对象并查询
    function cx(){
        var obj=$("#searchFrom").serializeObject();
        customer.datagrid('load',obj);
    }

</script>
</body>
</html>