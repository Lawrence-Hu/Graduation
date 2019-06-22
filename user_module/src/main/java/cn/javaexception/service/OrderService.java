package cn.javaexception.service;

import cn.javaexception.entity.Order;
import cn.javaexception.util.JsonData;

public interface OrderService {
   JsonData addToOrder(Order order);
}
