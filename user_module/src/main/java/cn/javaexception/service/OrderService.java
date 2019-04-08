package cn.javaexception.service;

import order_module.entity.Order;
import utils.JsonData;

import java.util.List;

public interface OrderService {
   JsonData addToOrder(List<Order.OrderItem> orderVos);
}
