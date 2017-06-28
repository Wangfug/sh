package com.lte.admin.common.schedulJob;

import com.lte.admin.order.service.OrderInfoService;

import javax.annotation.Resource;
import java.util.Date;

public class OrderCloseJob{
	@Resource
	private OrderInfoService orderInfoService;

	public void CloseOrder(){
		System.out.println("-----定时关闭订单任务----："+new Date());
		orderInfoService.closeOrderAuto();
	}
}
