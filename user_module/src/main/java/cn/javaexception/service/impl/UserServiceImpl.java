package cn.javaexception.service.impl;

import cn.javaexception.entity.User;
import cn.javaexception.mapper.DeliverAddressMapper;
import cn.javaexception.mapper.UserMapper;
import cn.javaexception.service.UserService;
import cn.javaexception.util.JsonData;
import cn.javaexception.util.SendJMail;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
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
    @Autowired
    private DeliverAddressMapper deliverAddressMapper;

    /**
     * @param account
     * @return map
     * @author huchao
     * @description 用户注册
     */
    @Override
    public JsonData register(String account,String password) {
        //判断用户是否存在
        if (!userMapper.selectList(new QueryWrapper<User>()
                .eq("account", account)
                .or()
                .eq("phone", account)
                .or()
                .eq("email", account)).isEmpty()) {
            return JsonData.buildError("用户名已被占用!");
        }
        //用户注册
        User user = new User().setAccount(account).setPassword(password).setCreatedTime(new Date());

        return userMapper.insert(user) >0? JsonData.buildSuccess("用户注册成功！"):JsonData.buildError("用户注册失败，请重试！");

    }

    /**
     * @param user
     * @return boolean
     * @author huchao
     * @description 修改邮件信息
     */
    @Override
    public JsonData setEmail(User user) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        //验证码不能我空！
        if (user.getIdentifyingCode() == null) {
            if (user.getEmail() == null) {
                return JsonData.buildError("新邮箱不能为空");
            }
            return JsonData.buildError("验证码不能为空");
        }
        //获取最新的激活码 !!! 很关键！直接获取session里的code有可能不是最新的！！！！！
        User ret = principal.selectById();
        if (user.getIdentifyingCode().equals(ret.getIdentifyingCode())) {
            User one = userMapper.selectOne(new QueryWrapper<User>().eq("email", user.getEmail()));
            //判断邮箱未被被绑定过或者邮箱就是自己的账户
            if (one == null) {
                //设置新邮箱并让验证码失效
                ret.setEmail(user.getEmail()).setIdentifyingCode(UUID.randomUUID().toString()).updateById();
                return JsonData.buildSuccess("修改邮箱成功！");
            } else if (Objects.equals(one.getId(), principal.getId())) {
                ret.setEmail(user.getEmail()).setIdentifyingCode(UUID.randomUUID().toString()).updateById();
                return JsonData.buildSuccess("修改邮箱成功！");
            }
            //使验证码失效
            ret.setIdentifyingCode(UUID.randomUUID().toString()).updateById();
            return JsonData.buildError("邮箱已经被绑定过！修改失败！");
        }
        //使验证码失效
        ret.setIdentifyingCode(UUID.randomUUID().toString()).updateById();
        return JsonData.buildError("验证码不正确！");
    }


    @Override
    public User operateLog(JSONObject data) {
        System.err.println(data);
        return userMapper.selectOne(new QueryWrapper<User>().select("id","address", "alipay_account", "certification", "email", "role_id", "status", "phone", "name").eq("id", data.get("id")));
    }


    @Override
    public User getUserInfoById(String id) {

        return userMapper.selectById(id);
    }

    /**
     * @param user
     * @return boolean
     * @author huchao
     * @description 修改默认收货地址
     */
    @Override
    public JsonData setDefaultDeliverAddress(User user) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        //收货地址不能为空
        if (user.getDeliveryAddressId() == null) {
            return JsonData.buildError("修改的默认地址id不能为空！");
        }
        //前端传过来的id要有效，并且在地址表中userId要与当前userId一致！
        if (deliverAddressMapper.selectById(user.getDeliveryAddressId()) == null
                || !principal.getId().equals(deliverAddressMapper.selectById(user.getDeliveryAddressId()).getUserId())) {
            return JsonData.buildError("无该地址,请检查重试！");
        }
        if (principal.setDeliveryAddressId(user.getDeliveryAddressId()).updateById()) {
            return JsonData.buildSuccess("设置为默认地址成功");
        } else {
            return JsonData.buildError("设置失败，请刷新后重试！");
        }
    }

    /**
     * @param user
     * @return boolean
     * @author huchao
     * @description 设置手机号码
     */
    @Override
    public boolean setPhoneNumber(User user) {
        //TODO 发送短信
        int i = userMapper.updateById(user);
        return i > 0;
    }

    /**
     * @param user
     * @return boolean
     * @author huchao
     * @description 发送邮箱给用户
     */
    @Override
    public JsonData sendEmailCode(User user) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        //获取用户最新的邮箱信息
        User dbUser = principal.selectById();
        //生成验证码
        long l = System.currentTimeMillis();

        String strCode = Long.toString(l).substring(0,6);
        //如果用户没有绑定
        try {
            if (dbUser.getEmail() == null || "".equals(dbUser.getEmail())) {
                if (!SendJMail.sendMail(user.getEmail(), strCode, "验证码")) {
                    return JsonData.buildError("发送验证码失败！请稍后重试！");
                }
            } else {
                //如果已绑定则向以前的邮箱发送验证码
                if (!SendJMail.sendMail(dbUser.getEmail(), strCode, "验证码")) {
                    return JsonData.buildError("发送验证码失败！请稍后重试！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbUser.setIdentifyingCode(strCode).updateById()? JsonData.buildSuccess("发送验证码成功！") : JsonData.buildError("发送验证码失败！请稍后重试！");
    }
}
