<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script src="${ctx}/static/plugins/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

</head>
<body style="font-family: '微软雅黑'">
<div id="tb" style="padding:5px;height:auto">
        <div>
        	<form id="searchFrom" action="">
       	        <input type="text" name="filter_zdlxdm" class="easyui-validatebox" data-options="width:150,prompt: '代码'"/>
       	        <input type="text" name="filter_zdlxmc" class="easyui-validatebox" data-options="width:150,prompt: '名称'"/>
		        <span class="toolbar-item dialog-tool-separator"></span>
		        <a href="javascript(0)" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="cx()">查询</a>
			</form>
			
	       		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">添加</a>
	       		<span class="toolbar-item dialog-tool-separator"></span>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-options="disabled:false" onclick="del()">注销</a>
	            <span class="toolbar-item dialog-tool-separator"></span>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="upd()">修改</a>
        </div> 
        
</div>
<table id="dg"></table> 
<div id="dlg"></div>  
<script type="text/javascript">
var dg;
$(function(){   
	dg=$('#dg').datagrid({    
	method: "get",
    url:'${ctx}/system/zdlx/json', 
    fit : true,
	fitColumns : true,
	border : false,
	striped:true,
	idField : 'zdlxid',
	sortName: 'zdlxid',
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 20,
	pageList : [ 10, 20, 30, 40, 50 ],
	singleSelect:true,
    columns:[[    
		{field:'zdlxid',title:'zdlxid',hidden:true},  
		{field:'zdlxdm',title:'代码',sortable:true,width:100},
		{field:'zdlxmc',title:'名称',sortable:true,width:100},
        {field:'zdlxsm',title:'说明',sortable:true,width:100},    
        {field:'parentLxName',title:'父节点',sortable:true},
        {field:'zxbj',title:'注销标记',sortable:true,width:100,formatter : function(value, row, index) {
        	if(value=="0"){
        		return '否';
        	}else{
        		return '是';
        	}
		}}
    ]],
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    enableRowContextMenu: false,
    toolbar:'#tb'
});
	$("#searchFrom :text").keydown(function (e) { if (e.which == 13) { cx();e.preventDefault(); } });
});

//弹窗增加
function add() {
	d=$("#dlg").dialog({   
	    title: '添加字典类型',    
	    width: 380,    
	    height: 250,    
	    href:'${ctx}/system/zdlx/create',
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
	var row = dg.datagrid('getSelected');
	if(rowIsNull(row)) return;
	parent.$.messager.confirm('提示', '注销后无法恢复，您确定要注销？', function(data){
		if (data){
			$.ajax({
				type:'get',
				url:"${ctx}/system/zdlx/delete/"+row.zdlxid,
				success: function(data){
					successTip(data,dg);
				}
			});
		} 
	});
}

//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
	if(rowIsNull(row)) return;
	d=$("#dlg").dialog({   
	    title: '修改字典类型',    
	    width: 380,    
	    height: 250,    
	    href:'${ctx}/system/zdlx/update/'+row.zdlxid,
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
	dg.datagrid('reload',obj); 
}

</script>
</body>
</html>