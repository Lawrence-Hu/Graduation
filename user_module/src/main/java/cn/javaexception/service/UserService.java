package cn.javaexception.service;

import cn.javaexception.entity.LocalLogin;
import cn.javaexception.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import utils.JsonData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
public interface UserService extends IService<User> {

    User getUserInfoById(String id);

    JsonData setDefaultDeliverAddress(User user);

    boolean setPhoneNumber(User user);
    

    JsonData sendEmailCode(User user);

    JsonData register(LocalLogin localLogin);

    JsonData setEmail(User user);

    JsonData updateUserInfoById(User user);

    User selectUserInfo(User user);
}
