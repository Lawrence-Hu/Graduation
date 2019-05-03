package cn.javaexception.service.impl

import cn.javaexception.entity.User
import cn.javaexception.entity.UserStatus
import cn.javaexception.mapper.AuthUserMapper
import cn.javaexception.mapper.UserMapper
import cn.javaexception.mapper.UserStatusMapper
import cn.javaexception.service.AdminService
import cn.javaexception.util.PageUtil
import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AdminServiceImpl implements AdminService{
    @Autowired
    UserMapper userMapper
    @Autowired
    AuthUserMapper authUserMapper
    @Autowired
    UserStatusMapper statusMapper
    @Override
    def getAllUsersByPages(PageUtil params) {
        JSONObject data = new JSONObject()
        Page<User> page = new Page<>(params.getCurrentPage(),params.getPageSize())
        def users = userMapper.selectPage(page,new QueryWrapper<User>().
                select("id","name","account","phone","gender","address","email","created_time","status","last_login_time","certification","role_id","alipay_account"))
        def userAuth = authUserMapper.selectList(null)
        List<UserStatus> status = statusMapper.selectList()
        data.put("statuses",status)
        data.put("users",users.records)
        data.put("userAuth",userAuth)
        return data
    }
}
