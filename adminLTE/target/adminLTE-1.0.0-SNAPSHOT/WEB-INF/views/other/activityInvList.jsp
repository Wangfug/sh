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
    活动
    <select name="filter_activityId"  style="vertical-align: middle;width: 100px;">
      <option value="" selected>请选择</option>
        <c:forEach var="dict" items="${activitys}">
          <option value="${dict.id}">${dict.activityName}</option>
        </c:forEach>
    </select>
    活动参与者
    <select name="filter_activityInv"  style="vertical-align: middle;width: 100px;">
      <option value="" selected>请选择</option>
      <c:forEach var="dict" items="${customers}">
        <option value="${dict.id}">${dict.name}</option>
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
  <table id="activityInv"></table>
  <div id="dlg"></div>
</div>

<script type="text/javascript">
    var activityInv;
    var d;
    $(function(){
        activityInv=$('#activityInv').datagrid({
            method: "post",
            url:'${ctx}/web/activityInv/getActivityInvList',
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
                {field:'activityId',title:'活动名称',formatter:function(value,row,index){
                    var activitys = '${activitys}';
                    if(activitys){
                        activitys = eval(activitys);
                        for(var n=0;n<activitys.length;n++){
                            if(value==activitys[n].id){
                                value = activitys[n].activityName;
                                break;
                            }
                        }
                    }else{
                        value = "";
                    }
                    return value;
                },sortable:true,width:50},
                {field:'createTime',title:'报名时间',formatter:function(value,row,index){
                    if(null != value && value!=""){
                        var unixTimestamp = new Date(value);
                        return unixTimestamp.toLocaleString();
                    }else{
                        return "";
                    }
                },sortable:true,width:50},
                {field:'activityInv',title:'活动参与者',formatter:function(value,row,index){
                    var customers = '${customers}';
                    if(customers){
                        customers = eval(customers);
                        for(var n=0;n<customers.length;n++){
                            if(value==customers[n].id){
                                value = customers[n].name;
                                break;
                            }
                        }
                    }else{
                        value = "";
                    }
                    return value;
                },sortable:true,width:50},
                {field:'state',title:'活动参与状态',sortable:true,width:50},
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
        activityInv.datagrid('load',obj);
    }

    //弹窗增加
    function add() {
        d=$("#dlg").dialog({
            title: '新增活动报名信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/activityInv/add',
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
        var row = activityInv.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改活动报名信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/activityInv/update/'+row.id,
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