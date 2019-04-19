package cn.javaexception.service.impl;

import cn.javaexception.mapper.UserInterfaceMapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import user_module.entity.User;
import user_module.service.UserInterface;

/**
 * @author hcuhao
 * @date 2019-03-05-13:40
 */
@Service
public class UserInterfaceImpl implements UserInterface {
    @Autowired
    UserInterfaceMapper userInterfaceMapper;

    @Override
    public User findUserById(String id) {
        //User user = userInterfaceMapper.selectById(id);
       // System.out.println("23");
        return null;
    }
}
