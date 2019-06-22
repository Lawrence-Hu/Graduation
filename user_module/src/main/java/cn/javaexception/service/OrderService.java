package cn.javaexception.service;

import cn.javaexception.entity.Order;
import cn.javaexception.util.JsonData;

/**
 * @author huchao
 * @since 2019-03-02
 */
public interface OrderService {
   JsonData addToOrder(Order order);
}
