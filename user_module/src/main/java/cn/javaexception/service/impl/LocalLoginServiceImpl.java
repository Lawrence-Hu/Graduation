package cn.javaexception.service.impl;

import cn.javaexception.mapper.LocalLoginMapper;
import cn.javaexception.mapper.UserMapper;
import cn.javaexception.model.LocalLogin;
import cn.javaexception.model.User;
import cn.javaexception.service.LocalLoginService;
import cn.javaexception.util.FormatValidator;
import cn.javaexception.util.SendJMail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@Service
public class LocalLoginServiceImpl extends ServiceImpl<LocalLoginMapper, LocalLogin> implements LocalLoginService {
    @Autowired
    UserMapper userMapper;
    /**
     * @author huchao
     * @description 邮件激活
     * @param  account
     * @return user
     */
    @Override
    public User activateByEmail(String account) {
        //查询用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("account", account));
        if (user == null) {
            return null;
        }
        //用户已被激活
        if (user.getStatus().equals("0")) {
            return null;
        }
        //激活用户
        user.setStatus("1").updateById();
        return user;
    }
}
