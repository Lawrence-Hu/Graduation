package cn.javaexception.service.impl;

import cn.javaexception.entity.Order;
import cn.javaexception.entity.User;
import cn.javaexception.mapper.OrderMapper;
import cn.javaexception.service.OrderService;
import cn.javaexception.util.JsonData;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {

    @Override
    public JsonData addToOrder() {
        //判断是否是当前用户
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();

        return null;
    }
}
