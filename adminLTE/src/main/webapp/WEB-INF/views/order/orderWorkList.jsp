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
      <input type="text" name="filter_orderNo" class="easyui-validatebox" data-options="width:150,prompt: '订单号'"/>
      <input type="radio" name="filter_orderType" checked class="easyui-validatebox" data-options="width:20,prompt: '工单类型'" value = "0"/>取车单
      <input type="radio" name="filter_orderType" class="easyui-validatebox" data-options="width:20,prompt: '工单类型'" value = "1"/>还车单
      <select name="filter_way" style = "vertical-align: middle;">
        <option value = "">取车/还车方式</option>
        <option value = "0">上门送取</option>
        <option value = "1">门店取还</option>
      </select>
      <select name="filter_orderState" style = "vertical-align: middle;">
        <option value = "">工单状态</option>
        <c:forEach var="dict" items="${dictTypes}">
          <option value = "${dict.code}">${dict.name}</option>
        </c:forEach>
      </select>
      <%--<input type="text" name="filter_carShop" class="easyui-validatebox" data-options="width:150,prompt: '交易门店'"/>--%>
      <input type="text" name="filter_eno" class="easyui-validatebox" data-options="width:150,prompt: '交易负责人'"/>
      <input type="text" name="filter_car" class="easyui-validatebox" data-options="width:150,prompt: '车牌号'"/>
      <span class="toolbar-item dialog-tool-separator"></span>
      <a href="javascript(0)" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="cx()">查询</a>
      <shiro:hasPermission name="sys:tzgg:edit">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">添加</a>
        <span class="toolbar-item dialog-tool-separator"></span>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-options="disabled:false" onclick="del('${ctx}')">删除</a>
        <span class="toolbar-item dialog-tool-separator"></span>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update('${ctx}')">修改</a>
      </shiro:hasPermission>
    </form>
  </div>

</div>
<table id="order"></table>
<div id="dlg"></div>
<div id="orderWork"></div>
<script type="text/javascript">
    var order;
    var d;
    var orderWork;
    $(function(){
        order=$('#order').datagrid({
            method: "POST",
            url:'${ctx}/web/orderWork/getOrderList?state=${orderstate}',
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
            pageList : [ 5, 10, 20, 30, 50 ],
            singleSelect:true,
            columns:[[
                {field:'id',title:'id',hidden:true},
               {field:'carcode',title:'车辆',sortable:true,width:100},
                {field:'create_time',title:'创建时间',formatter:function(value,row,index){
                    var unixTimestamp;
                    if(value)
                        unixTimestamp = new Date(value);
                    else
                        return "";
                    return unixTimestamp.toLocaleString();
                },sortable:true,width:100},
                {field:'order_type',title:'工单类型',formatter:function(value,row,index){
                    var unixTimestamp;
                    if(value==0)
                        unixTimestamp = "取车";
                    else
                        unixTimestamp = "还车";
                    return unixTimestamp;
                },sortable:true,width:100},
//                {field:'lastTime',title:'修改时间',sortable:true,width:100},
//                {field:'lastBy',title:'修改人',sortable:true,width:100},
                {field:'way',title:'取车或还车方式',formatter:function(value,row,index){
                    var unixTimestamp;
                    if(value==1)
                        unixTimestamp = "门店取还";
                    else
                        unixTimestamp = "上门送取";
                    return unixTimestamp;
                },sortable:true,width:100},
                {field:'address',title:'取车或还车地址',sortable:true,width:100},
                {field:'dictname',title:'工单状态',sortable:true,width:100},
                {field:'state',title:'是否有效',formatter:function(value,row,index){
                    var unixTimestamp;
                    if(value==1)
                        unixTimestamp = "有效";
                    else
                        unixTimestamp = "作废";
                    return unixTimestamp;
                },sortable:true,width:100},
//                {field:'shopname',title:'交易门店',sortable:true,width:100},
                {field:'ename',title:'交易负责人',sortable:true,width:100},
                {field:'order_no',title:'订单编号',sortable:true,width:100},
                {field:'orderstate',title:'订单状态',formatter:function(value,row,index){
                    var orderStates = '${dictsForOrderWork}';
                    if(orderStates){
                        orderStates = eval(orderStates);
                        for(var n=0;n<orderStates.length;n++){
                            if(value==orderStates[n].code){
                                value = orderStates[n].name;
                                break;
                            }
                        }
                    }else{
                        value = "";
                    }
                    var jobCode = '${user.jobCode}';
                    if(jobCode&&value == "待指派"&&jobCode.indexOf("DZ")==0){
                        value = "<a href = 'javascript:void(0)'  onclick = 'handleOrder("+row.order_no+")'>指派</a>";
                    }
                    if(jobCode&&value == "待提车"&&jobCode.indexOf("DZ")==0){
                        value = "待提车，可重新<a href = 'javascript:void(0)'  onclick = 'handleOrder("+row.order_no+")'>指派</a>";
                    }
                    return value;
                },sortable:true,width:100}

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
            title: '添加工单',
            width: 800,
            height: 600,
            href:'${ctx}/web/orderWork/create',
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
        var row = order.datagrid('getSelected');
        if(rowIsNull(row)) return;
        parent.$.messager.confirm('提示', '删除后无法恢复您确定要删除？', function(data){
            if (data){
                $.ajax({
                    type:'get',
                    url:"${ctx}/web/orderWork/delete?id="+row.id,
                    success: function(data){
                        successTip(data,order);
                    }
                });
            }
        });
    }

    //弹窗修改
    function update(){
        var row = order.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改用户',
            width: 800,
            height: 600,
            href:'${ctx}/web/orderWork/create?id='+row.id,
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
        order.datagrid('reload',obj);
    }
    //分配工单
    function handleOrder(orderNo){
        orderWork=$("#orderWork").dialog({
            title: '分配工单',
            width: 500,
            height: 300,
            href:'${ctx}/web/orderWork/createOrderWork?orderNo='+orderNo,
            maximizable:true,
            modal:true,
            buttons:[{
                text:'指派',
                handler:function(){
                    $("#mainform").submit();
                }
            },{
                text:'取消',
                handler:function(){
                    orderWork.panel('close');
                }
            }]
        });
    }
</script>
</body>
</html>