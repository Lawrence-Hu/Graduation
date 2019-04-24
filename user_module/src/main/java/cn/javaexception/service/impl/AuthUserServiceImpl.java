package cn.javaexception.service.impl;

import cn.javaexception.entity.AuthUser;
import cn.javaexception.mapper.AuthUserMapper;
import cn.javaexception.service.AuthUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huchao
 * @since 2019-04-24
 */
@Service
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser> implements AuthUserService {

}
