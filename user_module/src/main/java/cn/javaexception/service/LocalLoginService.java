package cn.javaexception.service;

import product_module.LocalLogin;
import product_module.User;
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
