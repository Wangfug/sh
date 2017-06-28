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
    <input type="text" name="filter_filename" class="easyui-validatebox" data-options="width:150,prompt: '文件名'"/>
    <span class="toolbar-item dialog-tool-separator"></span>
    <a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="cx()">查询</a>
    <shiro:hasPermission name="sys:tzgg:edit">
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">添加</a>
      <span class="toolbar-item dialog-tool-separator"></span>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改</a>
    </shiro:hasPermission>
  </form>
</div>

<div data-options="region:'center',split:true,border:false" >
  <table id="tbaseAttachment"></table>
  <div id="dlg"></div>
</div>

<script type="text/javascript">
    var tbaseAttachment;
    var d;
    $(function(){
        tbaseAttachment=$('#tbaseAttachment').datagrid({
            method: "post",
            url:'${ctx}/web/tbaseAttachment/getTbaseAttachmentList',
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
                {field:'filename',title:'文件名',sortable:true,width:50},
                {field:'filepath',title:'文件路径',sortable:true,width:50},
                {field:'fileSize',title:'文件大小',sortable:true,width:50},
                {field:'remark',title:'备注',sortable:true,width:50}
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
        tbaseAttachment.datagrid('load',obj);
    }

    //弹窗增加
    function add() {
        d=$("#dlg").dialog({
            title: '新增附件信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/tbaseAttachment/create',
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
        var row = tbaseAttachment.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改附件信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/tbaseAttachment/create?id='+row.id,
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