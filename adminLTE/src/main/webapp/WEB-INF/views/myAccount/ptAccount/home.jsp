<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>首页</title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>
<link href="${ctx}/static/css/stylept.css" rel="stylesheet" />
<link href="${ctx}/static/css/home.css" rel="stylesheet" />
</head>
<body>
<div id = "body">
<c:if test="${type eq 'admin'}">您好！超级管理员</c:if>
<c:if test="${type eq 'DZ'}">
	<div id = "showOrder">
		<p id = "newOrderNumber">新增订单数20</p>
	</div>
	<ul>
		<li class = "li right1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('未指派工单','${ctx}/web/orderWork?state=10002','')"
			   class = "abtn" >未指派工单<span style = "color:red">${notAssign}</span>单</a>
		</li>
		<li class = "li left1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('订单列表','${ctx}/web/orderInfo','')"
			   class = "abtn" >新订单数(半小时之内)<span id = "newOrder"></span></a>
		</li>
		<li class = "li right1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('工单列表','${ctx}/web/orderWork','')"
			   class = "abtn" >全部工单</a>
		</li >
		<li class = "li left1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('员工列表','${ctx}/web/tbaseEmployee','')"
			   class = "abtn" >员工列表</a>
		</li>
		<li class = "li right1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('车辆列表','${ctx}/web/car','')"
			   class = "abtn" >车辆列表</a>
		</li>
		<li class = "li left1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('订单列表','${ctx}/web/orderInfo','')"
			   class = "abtn" >全部订单</a>
		</li>
		<li class = "li left1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('挂靠车辆审核','${ctx}/web/carAttachApply','')"
			   class = "abtn" >挂靠车辆审核</a>
		</li>
	</ul>
</c:if>
<c:if test="${type eq 'KF'}">
	<ul>
		<li class = "li left1" id = "li2">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('订单列表','${ctx}/web/orderInfo','')"
			   class = "abtn" >全部订单</a>
		</li>
		<li class = "li right1" id = "li3">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('工单列表','${ctx}/web/orderWork','')"
			   class = "abtn" >全部工单</a>
		</li >
		<li class = "li left1" id = "li4">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('员工列表','${ctx}/web/tbaseEmployee','')"
			   class = "abtn" >员工列表</a>
		</li>
		<li class = "li right1" id = "li5">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('车辆列表','${ctx}/web/car','')"
			   class = "abtn" >车辆列表</a>
		</li>
	</ul>
</c:if>
<c:if test="${type eq 'CW'}">
	<ul>
		<li class = "li left1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('全部订单','${ctx}/web/orderInfo','')"
			   class = "abtn" >全部订单</a>
		</li>
		<li class = "li right1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('待开发票','${ctx}/web/orderBill?state=0&readonly=1','')"
			   class = "abtn" >待开发票</a>
		</li>
		<li class = "li left1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('余额提现申请','${ctx}/web/customerBalanceCash','')"
			   class = "abtn" >余额提现申请</a>
		</li>
		<li class = "li left1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('挂靠订单列表','${ctx}/web/orderInfo?type=attach','')"
			   class = "abtn" >挂靠订单列表</a>
		</li>
	</ul>
</c:if>
<c:if test="${type eq 'MG'}">
	<ul>
		<li class = "li right1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('订单列表','${ctx}/web/orderInfo','')"
			   class = "abtn" >全部订单</a>
		</li>
		<li class = "li left1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('工单列表','${ctx}/web/orderWork','')"
			   class = "abtn" >全部工单</a>
		</li >
		<li class = "li right1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('员工列表','${ctx}/web/tbaseEmployee','')"
			   class = "abtn" >员工列表</a>
		</li>
		<li class = "li left1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('车辆列表','${ctx}/web/car','')"
			   class = "abtn" >车辆列表</a>
		</li>
		<li class = "li left1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('活动列表','${ctx}/web/activity','')"
			   class = "abtn" >活动列表</a>
		</li>
		<li class = "li left1">
			<a href="javascript:void(0)" onclick="parent.window.mainpage.mainTabs.addModule('门店列表','${ctx}/web/carShops','')"
			   class = "abtn" >门店列表</a>
		</li>
	</ul>
</c:if>
</div>
</body>
</html>
<script>
	var job = '${type}';
	var inter1;
    var timeout1;
	$(function(){
        if(job=="DZ"){
            inter1= setInterval(getNewOrder,60000);
        }
	});
	function getNewOrder(){
//	    console.log(11)
	    $.ajax({
			url:"${ctx}/web/orderInfo/getNewOrder",
			type:"get",
			dataType:"json",
			success:function(data){
//			    console.log(data);
					if(data.status==1&&data.data[1]>0){
//                        alert("新增加了"+data.data[1]+"条订单！");
                        $("#newOrder").html(data.data[0]);
                        $("#newOrderNumber").html("新增订单"+data.data[1]+"条");
                        $("#showOrder").show();
                        setTimeout(function(){
							$("#showOrder").hide();
						},5000);
                    }
			},
			error:function(){
//				alert("未获取新订单数");
			}
		});
	}
</script>
