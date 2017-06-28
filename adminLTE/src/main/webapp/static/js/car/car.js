var car;
var d;
//创建查询对象并查询
function cx(){
    var obj=$("#searchFrom").serializeObject();
    car.datagrid('load',obj);
}

//弹窗增加
function add() {
    d=$("#dlg").dialog({
        title: '新增车辆信息',
        width: 700,
        height: 400,
        href:'${ctx}/web/car/add',
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
function update(ctx){
    var row = car.datagrid('getSelected');
    if(rowIsNull(row)) return;
    d=$("#dlg").dialog({
        title: '修改车辆信息',
        width: 700,
        height: 400,
        href:'${ctx}/web/car/update/'+row.id,
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
//表单提交
$('#mainform').form({
    onSubmit: function(){
        var isValid = $(this).form('validate');
        return isValid;	// 返回false终止表单提交
    },
    success:function(data){
        successTip(data,car,d);
    }
});