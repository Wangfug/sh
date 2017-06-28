<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>${tzgg.title}</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body>
<div id="downform">
              <form id="ff" method="post">
                     <table cellpadding="5" align = "center">
                            <tr>
                                   <td align="right">车牌号:</td>
                                   <td>${carDetail.car_code}</td>
                                   <td align="right" style = "padding-left: 100px;">品牌:</td>
                                   <td>
                                       ${carDetail.model}
                                          </td>
                            </tr>
                            <tr>
                                   <td align="right">型号:</td>
                                   <td>${carDetail.brand}</td>
                                   <td align="right" style = "padding-left: 100px;">车型:</td>
                                   <td>${carDetail.carType}</td>
                            </tr>
                            <tr>
                                   <td align="right">经营种类:</td>
                                   <td>${carDetail.belong}</td>
                                   <td align="right" style = "padding-left: 100px;">入库时间:</td>
                                   <td>${carDetail.create_time}</td>
                            </tr>
                            <tr>
                                   <td align="right">购买日期:</td>
                                   <td>${carDetail.buy_time}</td>
                                   <td align="right" style = "padding-left: 100px;">所属门店:</td>
                                   <td>${carDetail.shopName}</td>
                            </tr>
                            <tr>
                                   <td align="right">发动机号:</td>
                                   <td>${carDetail.engine_no}</td>
                                   <td align="right" style = "padding-left: 100px;">车架号:</td>
                                   <td>${carDetail.frame_no}</td>
                            </tr>
                            <tr>
                                   <td align="right">颜色:</td>
                                   <td>${carDetail.color}</td>
                                   <td align="right" style = "padding-left: 100px;">车厢数:</td>
                                   <td>${carDetail.carton_number}</td>
                            </tr>
                         <tr>
                             <td align="right">里程:</td>
                             <td>${carDetail.mileage}</td>
                         </tr>
                             <tr>
                                 <td align="right" style = "padding-left: 100px;">操作:</td>
                                 <td colspan="3">
                                     <button type = "button" onclick = "upSale('${carDetail.car_code}',20001);" style="margin-left:130px;">
                                         上架
                                     </button>
                                     <button type = "button" onclick = "upSale('${carDetail.car_code}',20002);" style="margin-left:30px;">
                                         下架
                                     </button>
                                 </td>
                             </tr>
                     </table>
              </form>
</div>

<script type="text/javascript">
function upSale(carCode,type){
    $.ajax({
        url:"${ctx}/web/car/changeIsSale",
        type:"post",
        data:{carCode:carCode,type:type},
        success:function(data){
//            alert(1111);
            if("success"==data){
                alert("修改车辆状态成功！");
                d.panel('close');
                cx();
            }else{
                alert(data);
            }
        },
        error:function(){
            alert("系统异常！");
        }
    });
}
</script>
</body>
</html>