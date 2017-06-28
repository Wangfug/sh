<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title></title>
  <%@ include file="/WEB-INF/views/include/easyui.jsp"%>
  <%@ include file="/WEB-INF/views/include/validation.jsp"%>
  <script type="text/javascript" src="${ctx}/static/plugins/jquery/jquery.form.js"></script>
  <script src="${ctx}/static/plugins/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
  <link href="${ctx}/static/plugins/ueditor/themes/default/css/ueditor.css" rel="stylesheet"/>
  <script src="${ctx}/static/plugins/ueditor/ueditor.config.js"></script>
  <script src="${ctx}/static/plugins/ueditor/ueditor.all.js"></script>
  <script src="${ctx}/static/plugins/ueditor/lang/zh-cn/zh-cn.js"></script>
  <script src="${ctx}/static/plugins/easyui/jeasyui-extensions/jquery.ueditor.js"></script>

</head>
<body class="easyui-layout" data-options="fit: true">
<div data-options="region:'north',split:true,border:false" style="padding:5px;height:auto;overflow: hidden;">
  <form id="searchFrom" action="">
    <input type="hidden" name="filter_id" id="filter_id"/>
    活动状态
    <select name="filter_state"  style="vertical-align: middle;width: 100px;">
      <option value="" selected>请选择</option>
        <c:forEach var="dict" items="${activityState}">
          <option value="${dict.code}">${dict.name}</option>
        </c:forEach>
    </select>
    活动类型
    <select name="filter_activityType"  style="vertical-align: middle;width: 100px;">
      <option value="" selected>请选择</option>
      <c:forEach var="dict" items="${activityType}">
        <option value="${dict.code}">${dict.name}</option>
      </c:forEach>
    </select>
    <span class="toolbar-item dialog-tool-separator"></span>
    <a href="javascript(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="cx()">查询</a>
    <shiro:hasPermission name="web:act:add">
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">新增活动</a>
      <span class="toolbar-item dialog-tool-separator"></span>
      <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update()">修改活动</a>
    </shiro:hasPermission>
  </form>
</div>

<div data-options="region:'center',split:true,border:false" >
  <table id="activity"></table>
  <div id="dlg"></div>
</div>

<script type="text/javascript">
    var activity;
    var d;
    $(function(){
        activity=$('#activity').datagrid({
            method: "post",
            url:'${ctx}/web/activity/getActivityList',
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
                {field:'mainTitle',title:'活动主标题',sortable:true,width:50},
                {field:'subTitle',title:'活动副标题',sortable:true,width:50},
                {field:'activityType',title:'活动类型',formatter:function(value,row,index){
                    var activityType = '${activityType}';
                    if(activityType){
                        activityType = eval(activityType);
                        for(var n=0;n<activityType.length;n++){
                            if(value==activityType[n].code){
                                value = activityType[n].name;
                                break;
                            }
                        }
                    }else{
                        value = "";
                    }
                    return value;
                },sortable:true,width:50},
                {field:'state',title:'活动状态',formatter:function(value,row,index){
                    var activityState = '${activityState}';
                    if(activityState){
                        activityState = eval(activityState);
                        for(var n=0;n<activityState.length;n++){
                            if(value==activityState[n].code){
                                value = activityState[n].name;
                                break;
                            }
                        }
                    }else{
                        value = "";
                    }
                    return value;
                },sortable:true,width:50},
                {field:'createTime',title:'创建时间',formatter:function(value,row,index){
                    if(value){
                        var unixTimestamp = new Date(value);
                        return unixTimestamp.toLocaleString();
                    }else{
                        return "";
                    }
                },sortable:true,width:50},
                {field:'activityStart',title:'开始时间',formatter:function(value,row,index){
                    if(value){
                        var unixTimestamp = new Date(value);
                        return unixTimestamp.toLocaleString();
                    }else{
                        return "";
                    }
                },sortable:true,width:50},
                {field:'activityEnd',title:'结束时间',formatter:function(value,row,index){
                    if(null != value && value!=""){
                        var unixTimestamp = new Date(value);
                        return unixTimestamp.toLocaleString();
                    }else{
                        return "";
                    }
                },sortable:true,width:50},
                {field:'activityPosition',title:'活动位置',sortable:true,width:50},
                {field:'activityRemark',title:'备注',sortable:true,width:50},
                {field:'id',title:'参与情况',formatter:function(value,row,index){
                    return "<a href=\"javascript:;\" onclick=\"viewACTInv('${ctx}',"+value+");\">查看详情</a>";;
                },sortable:true,width:50}
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
        activity.datagrid('load',obj);
    }

    //弹窗增加
    function add() {
        d=$("#dlg").dialog({
            title: '新增活动信息',
            width: 1000,
            height: 500,
            href:'${ctx}/web/activity/add',
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
        var row = activity.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改活动信息',
            width: 1000,
            height: 500,
            href:'${ctx}/web/activity/update/'+row.id,
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

    function viewACTInv(ctx,id){
        d=$("#dlg").dialog({
            title: '查看活动参与情况',
            width: 700,
            height: 500,
            href:'${ctx}/web/activity/viewInvInfo?id='+id,
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