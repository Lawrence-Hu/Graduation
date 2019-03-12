package order_module.service;

import order_module.entity.Order;

public interface OrderInterface {
//    boolean addOrders(Order order);//添加订单
//
//    boolean SelectbyNum(Integer id);//查询订单
//
//    boolean UpdatepriceById(Order order);//修改价格
//
//    boolean delOrders(Integer id);//删除订单
//
//    boolean UpdateOrSta(Order order);//修改订单状态
//
//    boolean addOrderItem(Order order);//添加订单详情
//
//    boolean delOrderItem(Integer id);//删除订单详情
    boolean addToOrder(Order order);
}
