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
    <input type="text" name="filter_comCode" class="easyui-validatebox" data-options="width:150,prompt: '公司代码'"/>
    <input type="text" name="filter_comName" class="easyui-validatebox" data-options="width:150,prompt: '公司名'"/>
    <input type="text" name="filter_parComCode" class="easyui-validatebox" data-options="width:150,prompt: '父级公司代码'"/>
    <input type="text" name="filter_parComName" class="easyui-validatebox" data-options="width:150,prompt: '父级公司名'"/>
    公司类型
    <select name="filter_comType">
      <c:forEach var="comType" items="${dictsForCompany}">
        <option value = "">请选择企业类型</option>
        <option value = "${comType.code}">${comType.name}</option>
      </c:forEach>
    </select>
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
  <table id="tbaseCompany"></table>
  <div id="dlg"></div>
</div>

<script type="text/javascript">
    var tbaseCompany;
    var d;
    $(function(){
        tbaseCompany=$('#tbaseCompany').datagrid({
            method: "post",
            url:'${ctx}/web/tbaseCompany/getTbaseCompanyList',
            fit : true,
            fitColumns : true,
            border : false,
            idField : 'id',
            sortName: 'id',
            sortOrder: "desc",
            striped:true,
            pagination:true,
            rownumbers:true,
            pageNumber:1,
            pageSize : 20,
            pageList : [ 5, 10, 20, 30, 40, 50 ],
            singleSelect:true,
            columns:[[
                {field:'comcode',title:'公司代码',sortable:true,width:50},
                {field:'comname',title:'公司名',sortable:true,width:50},
                {field:'supercode',title:'上级公司代码',sortable:true,width:50},
                {field:'supername',title:'上级公司名',sortable:true,width:50},
                {field:'comtype',title:'企业类型',sortable:true,width:50},
                {field:'cityname',title:'所在城市',sortable:true,width:50}
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
        tbaseCompany.datagrid('load',obj);
    }

    //弹窗增加
    function add() {
        d=$("#dlg").dialog({
            title: '新增车辆信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/tbaseCompany/create',
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
        var row = tbaseCompany.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改车辆信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/tbaseCompany/create?id='+row.id,
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