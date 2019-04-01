package user_module.service;

import user_module.entity.User;

/**
 * 2 * @Author: HuChao
 * 3 * @Date: 19-3-27 下午10:49
 * 4
 */
public interface UserInterface {
    public User findUserById(Integer id);
}
