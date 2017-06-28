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
      <select name="filter_orderState" style = "vertical-align: middle;">${orderState}</select>
      <input type="text" name="filter_name" class="easyui-validatebox" data-options="width:150,prompt: '客户姓名'"/>
      <input type="text" name="filter_mobilePhone" class="easyui-validatebox" data-options="width:150,prompt: '客户电话'"/>
      <input type="text" name="filter_realTimeStart" class="easyui-datebox" data-options="width:150,prompt: '开始时间'"/>
      <input type="text" name="filter_realTimeEnd" class="easyui-datebox" data-options="width:150,prompt: '结束时间'"/>
      <span class="toolbar-item dialog-tool-separator"></span>
      <a href="javascript(0)" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="cx()">查询</a>
      <shiro:hasPermission name="sys:tzgg:edit">
        <%--<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">添加</a>
        <span class="toolbar-item dialog-tool-separator"></span>--%>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-options="disabled:false" onclick="del('${ctx}')">删除</a>
        <span class="toolbar-item dialog-tool-separator"></span>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update('${ctx}')">修改</a>
      </shiro:hasPermission>
    </form>
   <%-- <shiro:hasPermission name="sys:order:edit">
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">添加</a>
      <span class="toolbar-item dialog-tool-separator"></span>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-options="disabled:false" onclick="del()">删除</a>
      <span class="toolbar-item dialog-tool-separator"></span>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="upd()">修改</a>
    </shiro:hasPermission>--%>
  </div>

</div>
<table id="order"></table>
<div id="dlg"></div>
<div id="orderWork"></div>
<script type="text/javascript">
  var type = '${type}';
    var order;
    var d;
    var orderWork;
    $(function(){
        if(type=="attach"){
            order=$('#order').datagrid({
                method: "POST",
                url:'${ctx}/web/orderInfo/getAttachOrderList',
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
                    {field:'order_no',title:'订单号',formatter : function(value, row, index) {
                        if(value)
                            value = "<a href=\"javascript:;\" onclick=\"viewOrder('${ctx}',this);\">"+value+"</a>";
                        else
                            value = "";
                        return value;
                    },sortable:true,width:100},
                    {field:'getshopname',title:'取车门店',sortable:true,width:100},
                    {field:'returnshopname',title:'还车门店',sortable:true,width:100},
                    {field:'real_time_start',title:'租车开始时间',formatter:function(value,row,index){
                        var unixTimestamp;
                        if(value)
                            unixTimestamp = new Date(value);
                        else
                            return "";
//                        unixTimestamp = new Date();
                        return unixTimestamp.toLocaleString();
                    },sortable:true,width:100},
                    {field:'real_time_end',title:'租车结束时间',formatter:function(value,row,index){
                        if(value)
                            var unixTimestamp = new Date(value);
                        else
                            return "";
//                        var unixTimestamp = new Date();
                        return unixTimestamp.toLocaleString();
                    },sortable:true,width:100},
                    <c:if test="${type eq 'attach'}" >
                    {field:'cname',title:'挂靠人',sortable:true,width:100},
                    </c:if>
                    {field:'name',title:'客户姓名',sortable:true,width:100},
                    {field:'mobile_phone',title:'客户电话',sortable:true,width:100},
                    {field:'state',title:'订单状态',formatter:function(value,row,index){
                        var orderStates = '${dictsForOrder}';
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
        }else{
            order=$('#order').datagrid({
                method: "POST",
                url:'${ctx}/web/orderInfo/getOrderList?type='+type,
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
                    {field:'order_no',title:'订单号',formatter : function(value, row, index) {
                        if(value)
                            value = "<a href=\"javascript:;\" onclick=\"viewOrder('${ctx}',this);\">"+value+"</a>";
                        else
                            value = "";
                        return value;
                    },sortable:true,width:100},
                    {field:'getshopname',title:'取车门店',sortable:true,width:100},
                    {field:'returnshopname',title:'还车门店',sortable:true,width:100},
                    {field:'real_time_start',title:'租车开始时间',formatter:function(value,row,index){
                        var unixTimestamp;
                        if(value)
                            unixTimestamp = new Date(value);
                        else
                            return "";
//                        unixTimestamp = new Date();
                        return unixTimestamp.toLocaleString();
                    },sortable:true,width:100},
                    {field:'real_time_end',title:'租车结束时间',formatter:function(value,row,index){
                        if(value)
                            var unixTimestamp = new Date(value);
                        else
                            return "";
//                        var unixTimestamp = new Date();
                        return unixTimestamp.toLocaleString();
                    },sortable:true,width:100},
                    {field:'name',title:'客户姓名',sortable:true,width:100},
                    {field:'mobile_phone',title:'客户电话',sortable:true,width:100},
                    {field:'state',title:'订单状态',formatter:function(value,row,index){
                        var orderStates = '${dictsForOrder}';
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
        }
        $("#searchFrom :text").keydown(function (e) { if (e.which == 13) { cx();e.preventDefault(); } });

    });
    function cx(){
        var obj=$("#searchFrom").serializeObject();
        order.datagrid('reload',obj);
    }
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
<script src="${ctx}/static/js/order/orderInfo.js"></script>
</body>
</html>