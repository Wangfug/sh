<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<link href="${ctx}/static/plugins/ueditor/themes/default/css/ueditor.css" rel="stylesheet"/>
<script src="${ctx}/static/plugins/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctx}/static/plugins/ueditor/ueditor.config.js"></script>
<script src="${ctx}/static/plugins/ueditor/ueditor.all.js"></script>
<script src="${ctx}/static/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jquery.ueditor.js"></script>
</head>
<body style="font-family: '微软雅黑'">
<div id="tb" style="padding:5px;height:auto">
        <div>
        	<form id="searchFrom" action="">
       	        <input type="text" name="filter_title" class="easyui-validatebox" data-options="width:150,prompt: '标题'"/>
       	        <select name="filter_lx">${lxComboHtml}</select>
		        <span class="toolbar-item dialog-tool-separator"></span>
		        <a href="javascript(0)" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="cx()">查询</a>
			</form>
			<shiro:hasPermission name="sys:tzgg:edit">
	       		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">添加</a>
	       		<span class="toolbar-item dialog-tool-separator"></span>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-options="disabled:false" onclick="del()">删除</a>
	            <span class="toolbar-item dialog-tool-separator"></span>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="upd()">修改</a>
	            </shiro:hasPermission>
        </div> 
        
</div>
<table id="dg"></table> 
<div id="dlg"></div>  
<script type="text/javascript">
var dg;
$(function(){   
	dg=$('#dg').datagrid({    
	method: "POST",
    url:'${ctx}/system/placard/json', 
    fit : true,
	fitColumns : true,
	border : false,
	striped:true,
	idField : 'id',
	sortName: 'id',
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 20,
	pageList : [ 10, 20, 30, 40, 50 ],
	singleSelect:true,
    columns:[[    
		{field:'id',title:'id',hidden:true},  
		{field:'title',title:'标题',sortable:true,width:100,formatter : function(value, row, index) {
			return '<a href="javascript:;" onclick="viewtz('+row.id+',\''+row.title+'\');">'+value+'</a>';
		}},
        {field:'isTop',title:'置顶',sortable:true,width:100,formatter : function(value, row, index) {
        	if(value=="0"){
        		return '否';
        	}else{
        		return '是';
        	}
		}},    
        {field:'deleteFlag',title:'注销标记',sortable:true,width:100,formatter : function(value, row, index) {
        	if(value=="0"){
        		return '否';
        	}else{
        		return '是';
        	}
		}},    
        {field:'updateName',title:'编写人',sortable:true,width:100},
        {field:'updateTime',title:'时间',sortable:true,width:100,formatter : function(value, row, index) {
    		if(value!=""&&value!=null){
    			return (new Date(parseInt(value))).format("yyyy-MM-dd");
    		}
    		return "";
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
	    title: '添加通知公告',    
	    width: 680,    
	    height: 550,    
	    href:'${ctx}/system/placard/create',
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
	parent.$.messager.confirm('提示', '您确定要删除这条记录吗？', function(data){
		if (data){
			$.ajax({
				type:'get',
				url:"${ctx}/system/placard/delete/"+row.id,
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
	    title: '修改通知公告',    
	    width: 680,    
	    height: 550,    
	    href:'${ctx}/system/placard/update/'+row.id,
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

function viewtz(id,title){
	d=$("#dlg").dialog({   
	    title: '查看通知公告-'+title,    
	    width: 950,    
	    height: 550,    
	    href:'${ctx}/system/placard/view/'+id,
	    maximizable:true,
	    modal:true,
	    buttons:[{
			text:'关闭',
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