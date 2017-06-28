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
      <input type="text" name="filter_orderId" class="easyui-validatebox" data-options="width:150,prompt: '订单号'"/>
      <span class="toolbar-item dialog-tool-separator"></span>
      <a href="javascript(0)" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="cx()">查询</a>
      <shiro:hasPermission name="sys:tzgg:edit">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">添加</a>
        <span class="toolbar-item dialog-tool-separator"></span>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-options="disabled:false" onclick="del()">删除</a>
        <span class="toolbar-item dialog-tool-separator"></span>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
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
<script type="text/javascript">
    var order;
    var d;
    $(function(){
        order=$('#order').datagrid({
            method: "POST",
            url:'${ctx}/web/orderState/getOrderList',
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
                {field:'orderNo',title:'订单号',sortable:true,width:100},
                {field:'state',title:'状态',sortable:true,width:100},
                {field:'orderStateInfo',title:'状态信息',sortable:true,width:100},
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
            title: '添加订单状态更新记录',
            width: 380,
            height: 250,
            href:'${ctx}/web/orderState/create',
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
                    url:"${ctx}/web/orderState/delete?id="+row.id,
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
            width: 380,
            height: 250,
            href:'${ctx}/web/orderState/create?id='+row.id,
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