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
    <input type="text" name="filter_comName" class="easyui-validatebox" data-options="width:150,prompt: '公司名'"/>
    <input type="text" name="filter_contacts" class="easyui-validatebox" data-options="width:150,prompt: '联系人'"/>
    <input type="text" name="filter_contactsPhone" class="easyui-validatebox" data-options="width:150,prompt: '联系电话'"/>
    <input type="text" name="filter_businessType" class="easyui-validatebox" data-options="width:150,prompt: '企业类型'"/>
    <input type="text" name="filter_city" class="easyui-validatebox" data-options="width:150,prompt: '城市'"/>
    <span class="toolbar-item dialog-tool-separator"></span>
    <a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="cx()">查询</a>
  </form>
</div>

<div data-options="region:'center',split:true,border:false" >
  <table id="govRentInfo"></table>
  <div id="dlg"></div>
</div>

<script type="text/javascript">
    var govRentInfo;
    var d;
    $(function(){
        govRentInfo=$('#govRentInfo').datagrid({
            method: "post",
            url:'${ctx}/web/govRentInfo/govRentInfoList',
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
                {field:'comName',title:'公司名称',sortable:true,width:50},
                {field:'contacts',title:'联系人',sortable:true,width:50},
                {field:'contactsPhone',title:'联系电话',sortable:true,width:50},
                {field:'businessType',title:'企业类型',sortable:true,width:50},
                {field:'city',title:'城市',sortable:true,width:50},
                {field:'businessType',title:'企业类型',sortable:true,width:50}
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
        govRentInfo.datagrid('load',obj);
    }


</script>
</body>
</html>