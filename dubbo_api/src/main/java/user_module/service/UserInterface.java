package user_module.service;

import user_module.entity.User;

/**
 * @author hcuhao
 * @date 2019-03-05-13:39
 */
public interface UserInterface {
    User findUserById(Integer id);
}
