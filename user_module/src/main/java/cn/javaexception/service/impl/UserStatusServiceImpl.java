package cn.javaexception.service.impl;

import cn.javaexception.entity.UserStatus;
import cn.javaexception.mapper.UserStatusMapper;
import cn.javaexception.service.UserStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huchao
 * @since 2019-05-01
 */
@Service
public class UserStatusServiceImpl extends ServiceImpl<UserStatusMapper, UserStatus> implements UserStatusService {

}
