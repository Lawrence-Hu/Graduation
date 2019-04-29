package cn.javaexception.service.impl


import cn.javaexception.entity.User
import cn.javaexception.mapper.AuthUserMapper
import cn.javaexception.mapper.UserMapper
import cn.javaexception.service.AdminService
import com.alibaba.fastjson.JSONObject
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
    def getAllUsersByPages(params) {
        JSONObject data = new JSONObject()
        Page<User> page = new Page<>(params.currentPage,params.pageSize)
        def users = userMapper.selectPage(page,null)
        def status = authUserMapper.selectList(null)
        data.put("users",users.records)
        data.put("userAuth",status)
        return data
    }
}
