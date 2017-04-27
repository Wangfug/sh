<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<script src="${ctx}/static/plugins/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body>
<div id="tb" style="padding:5px;height:auto">

  </div>
<table id="dg"></table> 
<div id="dlg"></div>  
<script type="text/javascript">
var dg;
var d;
$(function(){   
	dg=$('#dg').datagrid({    
	method: "get",
    url:'${ctx}/workflow/useryb/json', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'taskId',
	striped:true,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 20,
	pageList : [ 10, 20, 30, 40, 50 ],
	singleSelect:true,
    columns:[[    
        {field:'taskId',title:'taskId',hidden:false,sortable:false},    
        {field:'processName',title:'业务名称',sortable:false,hidden:false},    
        {field:'bizName',title:'实例名称',hidden:false,sortable:false},
        {field:'stepName',title:'流程步骤',sortable:false,width:100},
        {field:'assignee',title:'处理人',hidden:false,sortable:false},
        {field:'assigneeName',title:'处理人',hidden:false,sortable:false},
        {field:'bizKey',title:'bizKey',sortable:false,width:100},
        {field:'url',title:'url',hidden:true,sortable:false},
        {field:'view',title:'操作',hidden:false,
        	formatter : function(value, row, index) {
				return "<a href='#' onclick='ybView("+ row.taskId  + "," + "\"" + row.bizKey + "\"" + "," + "\"" + row.processName + "-" + row.bizKey + "\"" + ");'>查看</a>";
			}
		}
    ]],
    headerContextMenu: [
        {
            text: "冻结该列", disabled: function (e, field) { return dg.datagrid("getColumnFields", true).contains(field); },
            handler: function (e, field) { dg.datagrid("freezeColumn", field); }
        },
        {
            text: "取消冻结该列", disabled: function (e, field) { return dg.datagrid("getColumnFields", false).contains(field); },
            handler: function (e, field) { dg.datagrid("unfreezeColumn", field); }
        }
    ],
    enableHeaderClickMenu: true,
    enableHeaderContextMenu: true,
    enableRowContextMenu: false,
    toolbar:'#tb',
    queryParams: {
    	filter_dqblrid: '<shiro:principal property="id"/>',
    	filter_dbyb: '1'
	}
	});

});



//弹窗修改
function upd(){
	var row = dg.datagrid('getSelected');
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


//查看
function look(){
	var row = dg.datagrid('getSelected');
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

//创建查询对象并查询
//function cx(){
	
//}

</script>
</body>
</html>