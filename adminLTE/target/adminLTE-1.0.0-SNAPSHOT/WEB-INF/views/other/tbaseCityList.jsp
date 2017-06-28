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
    <input type="text" name="filter_cityname" class="easyui-validatebox" data-options="width:150,prompt: '区名'"/>
    <input type="text" name="filter_parentname" class="easyui-validatebox" data-options="width:150,prompt: '城市名'"/>
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
  <table id="tbaseCity"></table>
  <div id="dlg"></div>
</div>

<script type="text/javascript">
    var tbaseCity;
    var d;
    $(function(){
        tbaseCity=$('#tbaseCity').datagrid({
            method: "post",
            url:'${ctx}/web/tbaseCity/getTbaseCityList',
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
                {field:'city_name',title:'城市名称',sortable:true,width:50},
                {field:'city_pinyin',title:'城市拼音',sortable:true,width:50},
                {field:'city_three_code',title:'城市三字码',sortable:true,width:50},
                {field:'city_first_word',title:'城市首字母',sortable:true,width:50},
                {field:'parentname',title:'上级城市',sortable:true,width:50}
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
        tbaseCity.datagrid('load',obj);
    }

    //弹窗增加
    function add() {
        d=$("#dlg").dialog({
            title: '新增车辆信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/tbaseCity/create',
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
        var row = tbaseCity.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改车辆信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/tbaseCity/create?id='+row.id,
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