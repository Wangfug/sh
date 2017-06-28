/**
 * Created by Administrator on 2017/5/3.
 */
//创建查询对象并查询
function viewOrder(ctx,obj){
    var orderNo = $(obj).html()
    d=$("#dlg").dialog({
        title: '查看订单-'+$(obj).html(),
        width: 950,
        height: 550,
        href:ctx+'/web/orderInfo/view?orderNo='+orderNo,
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
//弹窗增加
/*function add() {
    d=$("#dlg").dialog({
        title: '新增订单信息',
        width: 700,
        height: 400,
        href:'${ctx}/web/orderInfo/add',
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
}*/

//弹窗修改
function update(ctx){
    var row = order.datagrid('getSelected');
    if(rowIsNull(row)) return;
    d=$("#dlg").dialog({
        title: '修改订单信息',
        width: 700,
        height: 400,
        href:ctx+'/web/orderInfo/update?id='+row.id,
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