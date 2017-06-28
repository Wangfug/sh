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
   				 <input type="hidden" name="filter_deptCode" id="filter_deptCode"/>
        	    <input type="text" name="filter_memberCode" class="easyui-validatebox" data-options="width:150,prompt: '帐号'"/>
       	        <input type="text" name="filter_memberName" class="easyui-validatebox" data-options="width:150,prompt: '姓名'"/>
		        <span class="toolbar-item dialog-tool-separator"></span>
		        <a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="cx()">查询</a>
		        <span class="toolbar-item dialog-tool-separator"></span>
		        <a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="cpwd()">修改密码</a>
		        <span class="toolbar-item dialog-tool-separator"></span>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-hamburg-suppliers" plain="true" onclick="userForRole()">用户角色</a>
		        
			</form>
		
    </div>
       <div data-options="region:'center',split:true,border:false" >

		<table id="dg"></table>
    </div>   
  <div data-options="region:'west',split:true,border:false,title:'部门列表'" style="width: 225px">
    	<div style="padding:5px;height:auto">
		    <ul id="bmtree" >
			</ul>
		    
		</div>
		
    </div>

<div id="dlg"></div>  
<script type="text/javascript">
var dg;
var d;
$(function(){   
	
	//部门树
	$("#bmtree").tree({
		url: "role/bmlist",
		method:"get",
		onSelect:function(node){ 
			//alert("node:" + node);
			//debugger;
			//dg.datagrid("reload",{filter_deptcode:node.id,});
			if(node.id==$("#filter_deptCode").val()){
				$("#filter_deptCode").val("");
				$('#bmtree').find('.tree-node-selected').removeClass('tree-node-selected');
			}else{
				$("#filter_deptCode").val(node.id);
			}
			// alert(node.id); //选择某个节点时执行的函数；
		 }
	});	
	
	dg=$('#dg').datagrid({    
	method: "post",
    url:'${ctx}/system/user/json',
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'member_code',
	sortName: 'member_code',
	striped:true,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 20,
	pageList : [ 10, 20, 30, 40, 50 ],
	singleSelect:true,
    columns:[[    
        {field:'memberCode',title:'帐号',sortable:true,width:100},
        {field:'memberName',title:'姓名',sortable:true,width:100},
        {field:'companyName',title:'公司',sortable:true},
        {field:'deptName',title:'部门',sortable:true,width:100},
        {field:'jobName',title:'岗位',sortable:true,width:100},

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
    toolbar:'#tb'
	});
	$("#searchFrom :text").keydown(function (e) { if (e.which == 13) { cx();e.preventDefault(); } });
	$("#bmtree").tree({
		url: "role/bmlist",
		method:"get",
		onSelect:function(node){ 
			//dg.datagrid("reload",{filter_deptcode:node.id,});
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
	var row = dg.datagrid('getSelected');
	if(rowIsNull(row)) return;
	parent.$.messager.confirm('提示', '删除后无法恢复您确定要删除？', function(data){
		if (data){
			$.ajax({
				type:'get',
				url:"${ctx}/system/user/delete/"+row.id,
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
	var row = dg.datagrid('getSelected');
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
	var row = dg.datagrid('getSelected');
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
//弹窗修改
function cpwd(){
	var row = dg.datagrid('getSelected');
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
	dg.datagrid('load',obj); 
}

</script>
</body>
</html>