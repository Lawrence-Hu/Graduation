package order_module.service;

import order_module.entity.Order;
import utils.JsonData;

public interface OrderInterface {
//
    JsonData selectById(String orderId);//查询订单
//
    JsonData updatePriceById(String orderId,Integer orderPrice);//修改价格
//
    JsonData delOrder(String orderId);//删除订单
//
    JsonData updateOrderSta(String orderId,Integer statusId);//修改订单状态
//
//

     JsonData addToOrder(Order order);
}
