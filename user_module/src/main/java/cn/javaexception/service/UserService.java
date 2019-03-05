package cn.javaexception.service;

import cn.javaexception.model.LocalLogin;
import cn.javaexception.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
public interface UserService extends IService<User> {

    boolean register(LocalLogin localLogin);

    boolean setDefaultDeliverAddress(User user);

    boolean setPhoneNumber(User user);

    boolean setEmail(User user);

    boolean sendEmailCode(User user);
}
