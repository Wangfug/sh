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
       	        数据类型：<select name="filter_sjlx">${lxComboHtml}</select>
		        <span class="toolbar-item dialog-tool-separator"></span>
		        <a href="javascript(0)" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="cx()">查询</a>
			</form>
			
	       		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">添加</a>
	       		<span class="toolbar-item dialog-tool-separator"></span>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-options="disabled:false" onclick="del()">停用</a>
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
    url:'${ctx}/system/sjxx/json', 
    fit : true,
	fitColumns : true,
	border : false,
	striped:true,
	idField : 'id',
	sortName: 'jsrq',
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 20,
	pageList : [ 10, 20, 30, 40, 50 ],
	singleSelect:true,
    columns:[[    
		{field:'id',title:'id',hidden:true},  
		{field:'sjlx',title:'数据类型',sortable:true},
		{field:'sjval',title:'数据值',sortable:true,width:100},
		{field:'ksrq',title:'开始日期',sortable:true,width:100,formatter : function(value, row, index) {
    		if(value!=""&&value!=null){
    			return (new Date(parseInt(value))).format("yyyy-MM-dd");
    		}
    		return "";
	}},
        {field:'jsrq',title:'结束日期',sortable:true,width:100,formatter : function(value, row, index) {
    		if(value!=""&&value!=null){
    			return (new Date(parseInt(value))).format("yyyy-MM-dd");
    		}
    		return "";
	}},
        {field:'tybj',title:'停用标记',sortable:true,formatter : function(value, row, index) {
        	if(value=="0"){
        		return '启用';
        	}else{
        		return '停用';
        	}
		}},
        {field:'createId',title:'创建人ID',sortable:true,hidden:true},
        {field:'createName',title:'创建人名称',sortable:true},
        {field:'createTime',title:'创建时间',sortable:true,width:100,formatter : function(value, row, index) {
    		if(value!=""&&value!=null){
    			return (new Date(parseInt(value))).format("yyyy-MM-dd HH:mm:ss");
    		}
    		return "";
		}},
		{field:'updateId',title:'更新人ID',sortable:true,hidden:true},
        {field:'updateName',title:'更新人名称',sortable:true},
        {field:'updateTime',title:'更新时间',sortable:true,width:100,formatter : function(value, row, index) {
    		if(value!=""&&value!=null){
    			return (new Date(parseInt(value))).format("yyyy-MM-dd HH:mm:ss");
    		}
    		return "";
		}}
    ]],
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    enableRowContextMenu: false,
    toolbar:'#tb'
});
});

//弹窗增加
function add() {
	d=$("#dlg").dialog({   
	    title: '添加数据信息',    
	    width: 380,    
	    height: 250,    
	    href:'${ctx}/system/sjxx/create',
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
	parent.$.messager.confirm('提示', '您确定要停用？', function(data){
		if (data){
			$.ajax({
				type:'get',
				url:"${ctx}/system/sjxx/delete/"+row.id,
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
	    title: '修改数据信息',    
	    width: 380,    
	    height: 250,    
	    href:'${ctx}/system/sjxx/update/'+row.id,
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