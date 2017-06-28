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
		<input type="text" name="filter_model" class="easyui-validatebox" data-options="width:150,prompt: '车型'"/>
		<%--<input type="text" name="filter_area" class="easyui-validatebox" data-options="width:150,prompt: '地区'"/>--%>
		<input type="text" name="filter_carShop" class="easyui-validatebox" data-options="width:150,prompt: '门店'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchCarRentPrice()">查询</a>
		<shiro:hasPermission name="web:carRentPrice:edit">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCarRentPrice();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateCarRentPrice()">修改</a>
		</shiro:hasPermission>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="carRentPrice"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var carRentPrice;
    var d;
    $(function(){
        carRentPrice=$('#carRentPrice').datagrid({
            method: "post",
            url:'${ctx}/web/carRentPrice/getList',
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
//                {field:'carCode',title:'车型',sortable:true,width:30},
//                {field:'city',title:'城市',sortable:true,width:50},
//                {field:'area',title:'区',sortable:true,width:50},
                {field:'brand',title:'品牌',sortable:true,width:50},
                {field:'model',title:'型号',sortable:true,width:50},
                {field:'priceByDay',title:'日租价格',sortable:true,width:50},
                {field:'priceByHour',title:'时租价格',sortable:true,width:50},
                {field:'feeInsurance',title:'基本保险费',sortable:true,width:50},
                {field:'feeDeductible',title:'不计免赔费',sortable:true,width:50},
                {field:'carShop',title:'门店',formatter:function(value,row,index){
                    var shops = '${carShops}';
                    var result = "";
                    if(shops){
                        shops = eval(shops);
						for(var i=0;i<shops.length;i++){
								if(value == shops[i].id){
                                    result = shops[i].shopName;
								    break;
                                }
                        }
                    }else{
                        return "";
                    }
            return result;
        },sortable:true,width:50},
                {field:'hotcar',title:'热门车型',formatter:function(value,row,index){
                            if(value =='0'){
                               value="否"
                            }else{
                                value="是"
							}
                    return value;
                },sortable:true,width:50},
            ]],
            enableHeaderClickMenu: true,
            enableHeaderContextMenu: true,
            enableRowContextMenu: false,
            toolbar:'#tb'
        });
    });

    //创建查询对象并查询
    function searchCarRentPrice(){
        var obj=$("#searchFrom").serializeObject();
        carRentPrice.datagrid('load',obj);
    }

    //弹窗增加
    function addCarRentPrice() {
        d=$("#dlg").dialog({
            title: '新增车型租赁信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carRentPrice/create',
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
    function updateCarRentPrice(){
        var row = carRentPrice.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改车型租赁信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/carRentPrice/create?id='+row.id,
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