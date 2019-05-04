package cn.javaexception.service.impl;

import cn.javaexception.entity.Role;
import cn.javaexception.mapper.RoleMapper;
import cn.javaexception.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
