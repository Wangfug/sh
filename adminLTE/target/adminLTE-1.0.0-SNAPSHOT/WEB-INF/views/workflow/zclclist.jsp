<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
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
    url:'${ctx}/workflow/zclclist/json', 
    fit : true,
	fitColumns : true,
	border : false,
	idField : 'clid',
	sortName:'clid',
	sortOrder:'desc',
	striped:true,
	pagination:true,
	rownumbers:true,
	pageNumber:1,
	pageSize : 20,
	pageList : [ 10, 20, 30, 40, 50 ],
	singleSelect:true,
    columns:[[    
        {field:'clid',title:'处理id',hidden:false},    
        {field:'lcnd',title:'年度',hidden:false},    
        {field:'lclxmc',title:'流程类型名称',hidden:false},
        {field:'ptgsdm',title:'平台公司代码',sortable:true,width:100},
        {field:'ptgsmc',title:'平台公司名称',sortable:true,width:100},
        {field:'zjlx',title:'资金类型',hidden:false	},
        {field:'sxed',title:'授信额度',hidden:false},
        {field:'xkfwbl',title:'现款服务比例',hidden:false,formatter : function(value, row, index) {
			if (value) {
				return Number(value) * 100 + "%";
			} else {
				return 0 + "%";
			}
	}},
        {field:'ckbzjbl',title:'敞口保证金比例',sortable:true,width:100,formatter : function(value, row, index) {
			if (value) {
				return Number(value) * 100 + "%";
			} else {
				return 0 + "%";
			}
	}},
        {field:'djbzjje',title:'冻结保证金金额',sortable:true},
        {field:'sqsj',title:'申请时间',hidden:false,formatter : function(value, row, index) {
    		if(value!=""&&value!=null){
    			return (new Date(parseInt(value))).format("yyyy-MM-dd");
    		}
    		return "";
	}},        
        {field:'wfid',title:'workflowid',hidden:true},
        {field:'lclx',title:'操作',hidden:false,
        	formatter : function(value, row, index) {
    				return "<a href='#' onclick='zclcView("+ row.clid  + "," + "\""+ row.lclxmc+ '-' + row.clid   + "\",\"" + row.lclx + "\",\"" + row.ptgsdm + "\"" + ");'>处理</a>";
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
    toolbar:'#tb'
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
function zclcView(clid,mc,lclx,gsdm){
	var url='';
	if(lclx=='13'){//总部借款
		url = 'myAccount/platformAccount/ptAccountDoCz/'+gsdm+'/'+lclx+'/'+clid+'/-1/-1';
	}else if(lclx=='12'){//线下电汇
		url = 'myAccount/platformAccount/ptAccountDoCz/'+gsdm+'/'+lclx+'/'+clid+'/-1/-1';
	}else if(lclx=='51'){//提现
		url = 'myAccount/platformAccount/redirect/tx/'+gsdm+'/'+clid+'/-1'
	}else if(lclx=='23'){//三方转款
		url = 'sxyw/sfzklc/wf/'+clid+'/-88'
	}else if(lclx=='14'){//票据贴现
		url = 'myAccount/platformAccount/ptAccountDoCz/'+gsdm+'/'+lclx+'/'+clid+'/-88/-88'
	}else if(lclx=='21'){//总部新办
        url = 'sxyw/zbxblc/wf/'+clid+'/-88'
    }else if(lclx=='22'){//总部背书
        url = 'sxyw/zbbslc/wf/'+clid+'/-88'
    }else if(lclx=='31'){//自有现金
        url = 'sxyw/zyxjlc/wf/'+clid+'/-88'
    }else if(lclx=='32'){//自有现金
        url = 'sxyw/zycdlc/wf/'+clid+'/-88'
    }else if(lclx=='33'){//自有现金
        url = 'sxyw/pjxblc/wf/'+clid+'/-88'
    }
	parent.addNewTab(mc,url, clid);
}
</script>
</body>
</html>