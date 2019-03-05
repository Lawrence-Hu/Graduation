package cn.javaexception.service.impl;

import cn.javaexception.mapper.UserMapper;
import cn.javaexception.model.LocalLogin;
import cn.javaexception.model.User;
import cn.javaexception.service.UserService;
import cn.javaexception.util.SendJMail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * @param localLogin
     * @return boolean
     * @author huchao
     * @description 用户注册
     */
    @Override
    public boolean register(LocalLogin localLogin) {
        int insert = userMapper.insert(new User().setCreatedTime(LocalDateTime.now()).setAccount(localLogin.getAccount()));
        boolean b = localLogin.insert();
        return insert > 0 && b;
    }

    /**
     * @param user
     * @return boolean
     * @author huchao
     * @description 修改默认收货地址
     */
    @Override
    public boolean setDefaultDeliverAddress(User user) {
        int i = userMapper.updateById(user);
        return i > 0;
    }
    /**
     * @author huchao
     * @description 设置手机号码
     * @param user
     * @return boolean
     */
    @Override
    public boolean setPhoneNumber(User user) {
        //TODO 发送短信
        int i = userMapper.updateById(user);
        return i>0;
    }
    /**
     * @author huchao 
     * @description 修改邮件信息
     * @param  user
     * @return boolean
     */
    @Override
    public boolean setEmail(User user) {
        User ret = userMapper.selectById(user.getId());
        if (ret.getIdentifyingCode().equals(user.getIdentifyingCode())){
            //判断该邮箱是否已被绑定
            if(userMapper.selectOne(new QueryWrapper<User>().eq("email",user.getEmail()))==null){
                //让验证码失效
                userMapper.updateById(user.setIdentifyingCode(UUID.randomUUID().toString()));
                return true;
            }

        }
        //让验证码失效
        userMapper.updateById(ret.setIdentifyingCode(UUID.randomUUID().toString()));
        return false;
    }
    /**
     * @author huchao 
     * @description 发送邮箱给用户
     * @param  user
     * @return boolean
     */
    @Override
    public boolean sendEmailCode(User user) {
        //发送到邮箱验证码
        SendJMail.sendMail(user.getEmail(),"123456","验证码");
        //查询该用户
        User ret = userMapper.selectById(user.getId());
        if(ret==null){
            return false;
        }
        return ret.setIdentifyingCode("123456").updateById();
    }
}
