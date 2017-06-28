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
      <select name="filter_state" style = "vertical-align: middle;">
        <option value = "">全部评价</option>
        <option value = "1">有效评价</option>
        <option value = "2">无效评价</option>
      </select>
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
<script type="text/javascript">
    var order;
    var d;
    $(function(){
        order=$('#order').datagrid({
            method: "POST",
            url:'${ctx}/web/orderEvaluate/getOrderList',
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
//                {field:'createBy',title:'创建人',sortable:true,width:40},
                {field:'createTime',title:'创建时间',formatter:function(value,row,index){
                    var unixTimestamp;
                    if(value)
                        unixTimestamp = new Date(value);
                    else
                        return "";
                    return unixTimestamp.toLocaleString();
                },sortable:true,width:50},
                {field:'state',title:'状态',formatter:function(value,row,index){
                    var unixTimestamp;
                    if(value==1)
                        unixTimestamp = "1星";
                    else
                        unixTimestamp = "5星";
                    return unixTimestamp;
                },sortable:true,width:30},
//                {field:'lastTime',title:'修改时间',sortable:true,width:50},
//                {field:'lastBy',title:'修改人',sortable:true,width:40},
                {field:'grade',title:'总评分',sortable:true,width:40},
                {field:'content',title:'评论内容',sortable:true,width:50},
                {field:'vehicleCondition',title:'车辆情况',formatter:function(value,row,index){
                    var unixTimestamp;
                    if(value==1)
                        unixTimestamp = "1星";
                    else
                        unixTimestamp = "5星";
                    return unixTimestamp;
                },sortable:true,width:50},
                {field:'getVehicleService',title:'取车服务',formatter:function(value,row,index){
                    var unixTimestamp;
                    if(value==1)
                        unixTimestamp = "1星";
                    else
                        unixTimestamp = "5星";
                    return unixTimestamp;
                },sortable:true,width:50},
                {field:'returnVehicleService',title:'送车服务',formatter:function(value,row,index){
                    var unixTimestamp;
                    if(value==1)
                        unixTimestamp = "1星";
                    else
                        unixTimestamp = "5星";
                    return unixTimestamp;
                },sortable:true,width:50},
                {field:'openOrder',title:'开单好评指数',formatter:function(value,row,index){
                    var unixTimestamp;
                    if(value==1)
                        unixTimestamp = "1星";
                    else
                        unixTimestamp = "5星";
                    return unixTimestamp;
                },sortable:true,width:60},
                {field:'closeOrder',title:'关单好评指数',formatter:function(value,row,index){
                    var unixTimestamp;
                    if(value==1)
                        unixTimestamp = "1星";
                    else
                        unixTimestamp = "5星";
                    return unixTimestamp;
                },sortable:true,width:60},
                {field:'totalService',title:'总体服务好评指数',formatter:function(value,row,index){
                    var unixTimestamp;
                    if(value==1)
                        unixTimestamp = "1星";
                    else
                        unixTimestamp = "5星";
                    return unixTimestamp;
                },sortable:true,width:75},
                {field:'orderNo',title:'订单编号',sortable:true,width:50},
                {field:'attachment',title:'附件',sortable:true,width:30},
                {field:'openOpinion',title:'开单业务员反馈',sortable:true,width:80},
                {field:'closeOpinion',title:'关单业务员反馈',sortable:true,width:80},
                {field:'totalOpinion',title:'总体反馈意见',sortable:true,width:80}
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
            href:'${ctx}/web/orderEvaluate/create',
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
                    url:"${ctx}/web/orderEvaluate/delete?id="+row.id,
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
            href:'${ctx}/web/orderEvaluate/create?id='+row.id,
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
</script>
</body>
</html>