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
    <input type="text" name="filter_comName" class="easyui-validatebox" data-options="width:150,prompt: '公司名字'"/>
    <input type="text" name="filter_corporation" class="easyui-validatebox" data-options="width:150,prompt: '公司法人'"/>
    公司类型
    <select name="filter_type"  style="vertical-align: middle;width: 100px;">
      <option value="" selected>请选择</option>
        <c:forEach var="dict" items="${companyType}">
          <option value="${dict.code}">${dict.name}</option>
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
  <table id="compAssociated"></table>
  <div id="dlg"></div>
</div>

<script type="text/javascript">
    var compAssociated;
    var d;
    $(function(){
        compAssociated=$('#compAssociated').datagrid({
            method: "post",
            url:'${ctx}/web/compAssociated/getCompAssociatedList',
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
                {field:'corporation',title:'公司法人',sortable:true,width:50},
                {field:'phone',title:'法人电话',sortable:true,width:50},
                {field:'idCardType',title:'法人证件类型',formatter:function(value,row,index){
                    var idCardType = '${idCardType}';
                    if(idCardType){
                        idCardType = eval(idCardType);
                        for(var n=0;n<idCardType.length;n++){
                            if(value==idCardType[n].code){
                                value = idCardType[n].name;
                                break;
                            }
                        }
                    }else{
                        value = "";
                    }
                    return value;
                },sortable:true,width:50},
                {field:'idCardNo',title:'法人证件号码',sortable:true,width:50},
                {field:'type',title:'公司类型',formatter:function(value,row,index){
                    var companyType = '${companyType}';
                    if(companyType){
                        companyType = eval(companyType);
                        for(var n=0;n<companyType.length;n++){
                            if(value==companyType[n].code){
                                value = companyType[n].name;
                                break;
                            }
                        }
                    }else{
                        value = "";
                    }
                    return value;
                }, sortable:true,width:50},
                {field:'country',title:'法人国家',sortable:true,width:50},
                {field:'province',title:'法人省份',sortable:true,width:50},
                {field:'area',title:'法人城市',sortable:true,width:50},
                {field:'comAddress',title:'公司注册地址',sortable:true,width:50},
                {field:'remark',title:'备注',sortable:true,width:50},
                {field:'attachment',title:'附件',sortable:true,width:50},
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
        compAssociated.datagrid('load',obj);
    }

    //弹窗增加
    function add() {
        d=$("#dlg").dialog({
            title: '新增公司信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/compAssociated/add',
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
        var row = compAssociated.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改公司信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/compAssociated/update/'+row.id,
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