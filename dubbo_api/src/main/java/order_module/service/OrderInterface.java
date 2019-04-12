package order_module.service;

import order_module.entity.Order;
import utils.JsonData;

public interface OrderInterface {
//
//    JsonData selectById(String orderId);//查询订单
//
    JsonData updatePriceById(String orderId,Float orderPrice);//修改价格
//
    JsonData delOrder(String orderId);//删除订单
////
//    JsonData updateOrderSta(String orderId,String statusId);//修改订单状态
//
//

     JsonData addToOrder(Order order);
     //按订单号查询
    JsonData findOrderById(String id);
    //数字表示对应的数据库状态
    boolean changeOrderStatus(String id,Integer i);
}
