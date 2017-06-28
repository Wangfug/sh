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
<div id="tb" style="padding:5px;height:auto">
  <div>
    <form id="searchFrom" action="">
      <input type="text" name="filter_orderNo" class="easyui-validatebox" data-options="width:150,prompt: '订单号'"/>
      <input type="text" name="filter_addresseeName" class="easyui-validatebox" data-options="width:150,prompt: '收件人'"/>

      <select name="filter_readonly" style = "vertical-align: middle;" id="readonly" onchange="change()">
        <option value = "" selected>全部</option>
        <option value = "1" >发票邮寄信息</option>
        <option value = "0">用户发票模板</option>
      </select>

      <select name="filter_state" style = "vertical-align: middle;" id="state">
        <option value = "">全部发票</option>
        <option value = "0">未寄出</option>
        <option value = "1">已寄出</option>
      </select>

      <select name="filter_billType" style = "vertical-align: middle;">
        <option value = "">发票类型</option>
        <option value = "1">普通发票</option>
        <option value = "2">增值税发票</option>
      </select>

      <span class="toolbar-item dialog-tool-separator"></span>
      <a href="javascript(0)" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="cx()">查询</a>
      <shiro:hasPermission name="sys:tzgg:edit">
        <%--<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">添加</a>--%>
        <span class="toolbar-item dialog-tool-separator"></span>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-options="disabled:false" onclick="del('${ctx}')">删除</a>
        <span class="toolbar-item dialog-tool-separator"></span>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="update('${ctx}')">修改</a>
      </shiro:hasPermission>
    </form>
  </div>

</div>
<table id="orderBill"></table>
<div id="dlg"></div>
<script type="text/javascript">
  var state = "${billState}";
  var readonly = "${billReadonly}";
  function change(){
      if($("#readonly").val()=='0' ){
          $("#state").val("");
          $("#state").css("display","none");
      }else{
          $("#state").css("display","");
      }
  }
    var orderBill;
    var d;
    $(function(){
        change();
        orderBill=$('#orderBill').datagrid({
            method: "POST",
            url:'${ctx}/web/orderBill/getOrderBillList?state='+state+"&readonly="+readonly,
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
                    if(row.readonly == '1'){
                        if(value==1){
                            unixTimestamp = "<a href=\"javascript:;\" onclick=\"viewBill("+row.id+");\">已寄出</a>";
                        }else{
                            unixTimestamp = "<a href=\"javascript:;\" onclick=\"viewBill("+row.id+");\">未寄出</a>";
                        }
                        return unixTimestamp;
                    }
                },sortable:true,width:30},
//                {field:'lastTime',title:'修改时间',sortable:true,width:50},
//                {field:'lastBy',title:'修改人',sortable:true,width:40},
                {field:'billType',title:'发票类型',formatter:function(value,row,index){
                    var unixTimestamp;
                    if(value==1)
                        unixTimestamp = "普通发票";
                    else
                        unixTimestamp = "增值税发票";
                    return unixTimestamp;
                },sortable:true,width:40},
                {field:'billTitle',title:'发票抬头内容',sortable:true,width:50},
                {field:'address',title:'寄送地址',sortable:true,width:50},
                {field:'linkphone',title:'联系电话',sortable:true,width:50},
                {field:'taxpayerCode',title:'纳税人识别号',sortable:true,width:60},
                {field:'depositBank',title:'开户银行',sortable:true,width:60},
                {field:'bankAccount',title:'银行账号',sortable:true,width:75},
                {field:'addresseeName',title:'收件人姓名',sortable:true,width:50},
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
            title: '添加发票',
            width: 800,
            height: 500,
            href:'${ctx}/web/orderBill/create',
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
        var row = orderBill.datagrid('getSelected');
        if(rowIsNull(row)) return;
        parent.$.messager.confirm('提示', '删除后无法恢复您确定要删除？', function(data){
            if (data){
                $.ajax({
                    type:'get',
                    url:"${ctx}/web/orderBill/delete?id="+row.id,
                    success: function(data){
                        successTip(data,orderBill);
                    }
                });
            }
        });
    }

    //弹窗修改
    function update(){
        var row = orderBill.datagrid('getSelected');
        if(rowIsNull(row)) return;
        d=$("#dlg").dialog({
            title: '修改发票',
            width: 800,
            height: 500,
            href:'${ctx}/web/orderBill/create?id='+row.id,
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
        orderBill.datagrid('reload',obj);
    }

    function viewBill(id){
        d=$("#dlg").dialog({
            title: '发票邮寄信息',
            width: 700,
            height: 400,
            href:'${ctx}/web/orderBill/create?id='+id,
            maximizable:true,
            modal:true,
        });
    }
</script>
</body>
</html>