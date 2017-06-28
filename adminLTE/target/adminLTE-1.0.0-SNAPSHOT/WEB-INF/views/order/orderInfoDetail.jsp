<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>${tzgg.title}</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
</head>
<body>
<div id="downform">
       <%--<div id = "keyword" align="right">
              <span>亲爱的</span>
       </div>
       <div id = "orderValue" align="left">${content}</div>--%>
              <form id="ff" method="post">
                     <table cellpadding="5" align="center" >
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">订单编号:</td>
                                   <td>${orderDetail.orderNo}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">订单状态:</td>
                                   <td>
                                          <c:forEach var = "option" items = "${dictsForOrder}">
                                                 <c:if test="${option.code eq orderDetail.state}">
                                                        ${option.name}
                                                 </c:if>
                                          </c:forEach>
                                          </td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">用户姓名:</td>
                                   <td>${orderDetail.name}</td>
                            </tr>
                           <%-- <tr>
                                   <td align="right" style = "padding-right: 20px;">证件类型:</td>
                                   <td>${orderDetail.order_no}</td>
                            </tr>--%>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">电话号码:</td>
                                   <td>${orderDetail.phone}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">取车业务员:</td>
                                   <td>${orderDetail.getName}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">取车服务好评指数:</td>
                                   <td>${orderDetail.getVehicleService}星</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">还车业务员:</td>
                                   <td>${orderDetail.returnName}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">还车服务好评指数:</td>
                                   <td>${orderDetail.returnVehicleService}星</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">总体服务好评指数:</td>
                                   <td>${orderDetail.totalService}星</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">预订车牌号:</td>
                                   <td>${orderDetail.carCode}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">预订车型:</td>
                                   <td>${orderDetail.reserveType}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">实际车型:</td>
                                   <td>${orderDetail.realCarType}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">取车模式:</td>
                                   <td>${orderDetail.getWAY}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">还车模式:</td>
                                   <td>${orderDetail.returnWAY}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">取车门店编号:</td>
                                   <td>${orderDetail.getShopCode}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">取车门店名称:</td>
                                   <td>${orderDetail.getShopName}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">还车门店编号:</td>
                                   <td>${orderDetail.returnShopCode}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">还车门店名称:</td>
                                   <td>${orderDetail.returnShopName}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">支付方式:</td>
                                   <td>${orderDetail.payWay}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">支付账号:</td>
                                   <td>${orderDetail.payAccount}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">车辆租赁费:</td>
                                   <td>${orderDetail.carRentFee}</td>
                            </tr>
                            <tr>
                                   <td align="right" style = "padding-right: 20px;">不计免赔:</td>
                                   <td>${orderDetail.bujimianpei}</td>
                            </tr>
                             <tr>
                                 <td align="right" style = "padding-right: 20px;">送车费:</td>
                                 <td>${orderDetail.sendCarFee}</td>
                             </tr>
                             <tr>
                                 <td align="right" style = "padding-right: 20px;">基本保险费:</td>
                                 <td>${orderDetail.baseFee}</td>
                             </tr>
                         <tr>
                             <td align="right" style = "padding-right: 20px;">送车费:</td>
                             <td>${orderDetail.sendCarFee}</td>
                         </tr>
                         <tr>
                             <td align="right" style = "padding-right: 20px;">基本保险费:</td>
                             <td>${orderDetail.baseFee}</td>
                         </tr>
                         <tr>
                             <td align="right" style = "padding-right: 20px;">预授权:</td>
                             <td>${orderDetail.preAuthorized}</td>
                         </tr>
                         <tr>
                             <td align="right" style = "padding-right: 20px;">费用总额:</td>
                             <td>${orderDetail.totalFee}</td>
                         </tr>
                         <c:if test="${roleType eq 'MG' and orderDetail.state ne '10007' and orderDetail.state ne '10006'}">
                             <tr>
                                 <td align="right" style = "padding-right: 20px;">操作:</td>
                                 <td>
                                     <button type = "button" onclick = "cancelOrder(${orderDetail.orderNo});">
                                         终止订单
                                     </button>
                                 </td>
                             </tr>
                             <tr>
                                 <td align="right" style = "padding-right: 20px;">终止理由:</td>
                                 <td>
                                     <input id="reason" type="text" class="easyui-validatebox" />
                                 </td>
                             </tr>
                         </c:if>
                     </table>
              </form>
</div>

<script type="text/javascript">
$(function(){
	$('#downform a').prop("target","_new");
});
//取消订单
function cancelOrder(id){
    if(!$("#reason").val()){
        alert("请填写取消原因！");
        return false;
    }
$.ajax({
       url:"${ctx}/web/orderInfo/cancelOrder?orderNo="+id,
       type:"post",
       dataType:"json",
       data:{reason:$("#reason").val()},
       success:function(data){
            alert(data.info);
           orderWork.panel('close');
           cx();
       },
        error:function(){
            alert("error");
//            orderWork.panel('close');
//            cx();
        }
    }

);
}

</script>
</body>
</html>