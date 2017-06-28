<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>111</title>
	<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
	<%@ include file="/WEB-INF/views/include/validation.jsp"%>
	<script type="text/javascript" src="${ctx}/static/plugins/jquery/jquery.form.js"></script>
	<script src="${ctx}/static/plugins/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body class="easyui-layout" data-options="fit: true">
<div data-options="region:'north',split:true,border:false" style="padding:5px;height:auto;overflow: hidden;">
	<form id="searchFrom" action="">
		<input type="hidden" name="filter_id" id="filter_id"/>
		<input type="text" name="filter_carCode" class="easyui-validatebox" data-options="width:150,prompt: '车牌号'"/>
		<input type="text" name="filter_bindObj" class="easyui-validatebox" data-options="width:150,prompt: '所属公司/个人'"/>
		<input type="text" name="filter_brand" class="easyui-validatebox" data-options="width:150,prompt: '品牌'"/>
		<select name="filter_state"  style="vertical-align: middle;width: 100px;">${carStates}</select>
		<input type="text" name="filter_engineNo" class="easyui-validatebox" data-options="width:150,prompt: '发动机号'"/>
		<span class="toolbar-item dialog-tool-separator"></span>
		<a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="cx()">查询</a>
		<%--<shiro:hasPermission name="sys:tzgg:edit">--%>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">添加</a>
			<span class="toolbar-item dialog-tool-separator"></span>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
		<%--</shiro:hasPermission>--%>
	</form>
</div>

<div data-options="region:'center',split:true,border:false" >
	<table id="car"></table>
	<div id="dlg"></div>
</div>

<script type="text/javascript">
    var car;
    var d;
    $(function(){
        car=$('#car').datagrid({
            method: "post",
            url:'${ctx}/web/car/getCarList',
            fit : true,
            fitColumns : true,
            border : false,
            idField : 'id',
            sortName: 'createTime',
            sortOrder: "desc",
            striped:true,
            pagination:true,
            rownumbers:true,
            pageNumber:1,
            pageSize : 20,
            pageList : [ 5, 10, 20, 30, 40, 50 ],
            singleSelect:true,
            columns:[[
                {field:'carCode',title:'车牌号',formatter : function(value, row, index) {
                    if(value)
                        value = "<a href=\"javascript:void(0);\" onclick=\"viewCar('"+value+"');\">"+value+"</a>";
                    else
                        value = "";
                    return value;
                },sortable:true,width:30},
                {field:'carShop',title:'所在门店',formatter:function(value,row,index){
                    var carShops = '${carShops}';
                    if(carShops){
                        carShops = eval(carShops);
                        for(var n=0;n<carShops.length;n++){
                            if(value==carShops[n].id){
                                value = carShops[n].shopName;
                                break;
							}
                        }
                    }else{
                        value = "";
                    }
                    return value;
                },sortable:true,width:50},
//                {field:'attachment',title:'车辆照片',sortable:true,width:50},
//                {field:'engineNo',title:'发动机号',sortable:true,width:50},
//                {field:'frameNo',title:'车架号',sortable:true,width:50},
//                {field:'color',title:'颜色',sortable:true,width:50},
                {field:'buyTime',title:'购买日期',formatter:function(value,row,index){
                    if(value){
                        var unixTimestamp = new Date(value);
                        return unixTimestamp.toLocaleString();
                    }else{
                        return "";
                    }
                },sortable:true,width:50},
                {field:'brand',title:'品牌',sortable:true,width:20},
                {field:'model',title:'型号',sortable:true,width:30},
//                {field:'cartonNumber',title:'车厢数',sortable:true,width:50},
                {field:'carType',title:'车型'
                    ,formatter:function(value,row,index){
                    var result = "";
                    if(value){
                        var models = value.split(",");
                        var model = '${CarModel}';
                        if(model)
                            model = eval(model);
                        for(var i=0;i<models.length;i++){
                            for(var j=0;j<model.length;j++){
                                if(models[i]==model[j].code){
                                    result+=model[j].name+",";
                                    model.splice(j,1);
                                    break;
                                }
                            }
                        }
                        if(result) {
                            result = result.substring(0, result.length - 1);
                            result = "<span title = '"+result+"'>" + result + "</span>";
                        }
                    }
                    else
                        result =  "";
                    return result;
                }
                    ,sortable:true,width:50},

                {field:'belong',title:'经营种类',formatter:function(value,row,index){
                    var carManageType = '${carManageType}';
                    if(carManageType){
                        carManageType = eval(carManageType);
                        for(var n=0;n<carManageType.length;n++){
                            if(value==carManageType[n].code){
                                value = carManageType[n].name;
                                break;
                            }
                        }
                    }else{
                        value = "";
                    }
                    return value;
                },sortable:true,width:50},
                {field:'state',title:'服务状态',formatter:function(value,row,index){
                    var carStates = '${dictsForCar}';
                    if(carStates){
                        carStates = eval(carStates);
                        for(var n=0;n<carStates.length;n++){
                            if(value==carStates[n].code){
                                value = carStates[n].name;
                                break;
                            }
                        }
                    }else{
                        value = "";
                    }
                    return value;
                },sortable:true,width:30},
                {field:'createTime',title:'入库时间',formatter:function(value,row,index){
                    if(value){
                        var unixTimestamp = new Date(value);
                        return unixTimestamp.toLocaleString();
                    }else{
                        return "";
                    }
                },sortable:true,width:50},
                /*{field:'leaveFactoryTime',title:'出厂日期',formatter:function(value,row,index){
                    if(value){
                        var unixTimestamp = new Date(value);
                        return unixTimestamp.toLocaleString();
                    }else{
                        return "";
                    }
                },sortable:true,width:50},*/
                {field:'moneyBuy',title:'购买价格',sortable:true,width:50},
                {field:'bindObj',title:'资产挂靠公司/个人',sortable:true,width:50}
//                {field:'id',title:'操作',sortable:true,width:50},
//                {field:'remark1',title:'备注1',sortable:true,width:50},
//                {field:'remark2',title:'备注2',sortable:true,width:50},
//                {field:'remark3',title:'备注3',sortable:true,width:50},
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
        car.datagrid('load',obj);
    }
    //弹窗增加
    function add() {
        d=$("#dlg").dialog({
            title: '新增车辆信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/car/add',
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
    function update(){
        var row = car.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改车辆信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/car/update/'+row.id,
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
    function viewCar(carCode){
//        alert(carCode);
        d=$("#dlg").dialog({
            title: '车辆信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/car/viewCarInfo?carCode='+carCode,
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
</script>
</body>
</html>