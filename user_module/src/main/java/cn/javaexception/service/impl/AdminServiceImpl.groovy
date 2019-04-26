package cn.javaexception.service.impl

import cn.javaexception.entity.User
import cn.javaexception.mapper.AuthUserMapper
import cn.javaexception.mapper.UserMapper
import cn.javaexception.service.AdminService
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdminServiceImpl implements AdminService{
    @Autowired
    UserMapper userMapper
    @Autowired
    AuthUserMapper authUserMapper
    @Override
    def getAllUsersByPages() {
        Page<User> page = new Page<>(1,2)
        def selectPage = userMapper.selectPage(page,null)

        return selectPage
    }
}
