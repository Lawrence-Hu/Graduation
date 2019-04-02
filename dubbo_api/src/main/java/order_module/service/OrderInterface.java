package order_module.service;

import order_module.entity.Order;
import utils.JsonData;

public interface OrderInterface {
//
    Order selectById(Integer orderId);//查询订单
//
    boolean updatePriceById(Integer orderId,Integer orderPrice);//修改价格
//
    boolean delOrder(Integer orderId);//删除订单
//
   boolean updateOrderSta(Integer orderId,Integer statusId);//修改订单状态
//
//

     JsonData addToOrder(Order order);
}
