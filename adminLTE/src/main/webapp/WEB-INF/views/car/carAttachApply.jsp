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
		<%--<input type="text" name="filter_car_code" class="easyui-validatebox" data-options="width:150,prompt: '车牌号'"/>--%>
		<select name="filter_state"  style="vertical-align: middle;width: 100px;">
			<option value="" selected>请选择</option>
			<option value="0">未审核</option>
			<option value="1">已审核通过</option>
			<option value="2">未通过</option>

		</select>

		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCarAttachApply()">查询</a>
		<shiro:hasPermission name="web:carAttachApply:edit">
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCarAttachApply()">修改</a>
		</shiro:hasPermission>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="CarAttachApply"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var CarAttachApply;
    var d;
    $(function(){
        CarAttachApply=$('#CarAttachApply').datagrid({
            method: "post",
            url:'${ctx}/web/carAttachApply/getList',
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
                /*{field:'carCode',title:'车牌号',formatter:function(value,row,index){
                    if (row.car_info){
                        myjson = eval("("+row.car_info+")");
                        if(myjson != '')
                            return myjson.carCode;
                        else{
                            return '';
                        }
                    } else {
                        return '';
                    }
                },width:50},
                {field:'brand',title:'品牌',formatter:function(value,row,index){
                    if (row.car_info){
                        myjson = eval("("+row.car_info+")");
                        if(myjson != '')
                            return myjson.brand;
                        else{
                            return '';
                        }
                    } else {
                        return '';
                    }
                },width:50},
                {field:'model',title:'型号',formatter:function(value,row,index){
                    if (row.car_info){
                        myjson = eval("("+row.car_info+")");
                        if(myjson != '')
                            return myjson.model;
                        else{
                            return '';
                        }
                    } else {
                        return '';
                    }
                },width:50},*/
                {field:'name',title:'申请人名字',sortable:true,width:50},
                {field:'mobile_phone',title:'申请人电话',sortable:true,width:50},
                {field:'create_time',title:'挂靠申请时间',formatter:function(value,row,index){
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                },sortable:true,width:50},
                {field:'state',title:'状态',formatter:function(value,row,index){
                    if (null != value){
                        if(value == '0') {
                            value = "<a href = 'javascript:void(0)'  onclick = 'updateCarAttachApply("+row.id+",0)'>未审核</a>";
                        }else if(value == '1'){
                            value = "<a href = 'javascript:void(0)'  onclick = 'updateCarAttachApply("+row.id+",1)' style='color:black'>已审核通过</a>";
                        }else if(value == '2'){
                            value = "<a href = 'javascript:void(0)'  onclick = 'updateCarAttachApply("+row.id+",2)' style='color:red'>未通过</a>";
                        }
                        return  value;
                    } else {
                        return '';
                    }
                },width:50}
            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCarAttachApply(){
        var obj=$("#searchFrom").serializeObject();
        CarAttachApply.datagrid('load',obj);
    }

    //弹窗增加
    function addCarAttachApply() {
        d=$("#dlg").dialog({
            title: '新增车辆故障信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carAttachApply/addCarAttachApply',
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

    //创建查询对象并查询
    function updateCarAttachApply(obj,type){
        var applyId = obj;
        d=$("#dlg").dialog({
            title: '查看详情-'+obj,
            width: 800,
            height: 650,
            href:'${ctx}/web/carAttachApply/toExamine?id='+applyId+"&type="+type,
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

    //弹窗修改
    function updateCarAttachApply1(){
        var row = CarAttachApply.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改车辆故障信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carAttachApply/update/'+row.id,
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