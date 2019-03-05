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
public interface LocalLoginService extends IService<LocalLogin> {

    User activateByEmail(String account);
}
