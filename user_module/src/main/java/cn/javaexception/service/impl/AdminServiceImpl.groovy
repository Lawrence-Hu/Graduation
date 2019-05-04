package cn.javaexception.service.impl


import cn.javaexception.entity.User
import cn.javaexception.entity.UserStatus
import cn.javaexception.mapper.RoleMapper
import cn.javaexception.mapper.UserMapper
import cn.javaexception.mapper.UserStatusMapper
import cn.javaexception.service.AdminService
import cn.javaexception.util.PageUtil
import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import utils.JsonData

@Service
class AdminServiceImpl implements AdminService{
    @Autowired
    UserMapper userMapper
    @Autowired
    RoleMapper roleMapper
    @Autowired
    UserStatusMapper statusMapper

    @Override
    def getAllUsersByPages(PageUtil params,String type) {
        JSONObject data = new JSONObject()
        Page<User> page = new Page<>(params.getCurrentPage(),params.getPageSize())
        def users
        if(type==null){
            users=userMapper.selectPage(page,new QueryWrapper<User>().
                    select("id","name","account","phone","gender","address","email","created_time","status","last_login_time","certification","role_id","alipay_account"))
        }else{
            users=userMapper.selectPage(page,new QueryWrapper<User>().
                    select("id","name","account","phone","gender","address","email","created_time","status","last_login_time","certification","role_id","alipay_account").eq("status",type))
        }

        def userAuth = roleMapper.selectList(null)
        List<UserStatus> status = statusMapper.selectList()
        data.put("statuses",status)
        data.put("users",users.records)
        data.put("userAuth",userAuth)
        return data
    }

    JsonData updateUserInfoById(User user) {
        //直接更新
        User dbUser = userMapper.selectById(user.getId())
        boolean isUpdateEmail = userMapper.selectList(new QueryWrapper<User>()
                .eq("phone", user.getEmail())
                .or()
                .eq("email", user.getEmail())).stream().allMatch({u -> (dbUser.getId() == u.getId()) })
        boolean isUpdatePhone = userMapper.selectList(new QueryWrapper<User>()
                .eq("phone",user.getPhone())
                .or()
                .eq("email",user.getPhone())).stream().allMatch({u-> (u.getId() == dbUser.getId()) })

        //没有修改邮箱修改了手机号
        if(user.getEmail() == dbUser.getEmail() && user.getPhone() != dbUser.getPhone()){
            if(isUpdatePhone){
                //执行更新
                System.out.println("修改了手机号")
                return userMapper.updateById(user)!=0?JsonData.buildSuccess("修改用户信息成功!"):JsonData.buildSuccess("修改用户信息失败!")
            }else{
                return JsonData.buildError("修改失败!手机号已被占用!")
            }
        }
        //没有修改手机号修改了邮箱
        if(user.getPhone() == dbUser.getPhone() && user.getEmail() != dbUser.getEmail()){
            if(isUpdateEmail){
                //执行更新
                System.out.println("修改了邮箱")
                return userMapper.updateById(user)!=0?JsonData.buildSuccess("修改用户信息成功!"):JsonData.buildSuccess("修改用户信息失败!")
            }else{
                return JsonData.buildError("修改失败!邮箱号已被占用!")
            }
        }
        //修改了手机号修改了邮箱
        if(user.getEmail() != dbUser.getEmail() && user.getPhone() != dbUser.getPhone()){
            if(isUpdateEmail&&isUpdatePhone){
                //执行更新
                System.out.println("都修改了")
                return userMapper.updateById(user)!=0?JsonData.buildSuccess("修改用户信息成功!"):JsonData.buildSuccess("修改用户信息失败!")
            }
        }
        //都没修改直接更新
        System.out.println("都没修改")
        return userMapper.updateById(user)!=0?JsonData.buildSuccess("修改用户信息成功!"):JsonData.buildError("未修改任何信息!")
    }

    @Override
    JsonData changeUserStatusById(String id, String type) {
        User user = userMapper.selectById(id)
        if(user==null){
            return JsonData.buildError("该用户不存在!修改失败")
        }
        if (type==null){
            //默认恢复正常
            return user.setStatus("1").updateById()? JsonData.buildSuccess("成功解除用户!"):JsonData.buildError("更新用户失败!刷新后试试?")
        }else{
            return user.setStatus(type).updateById()?JsonData.buildSuccess("成功解除用户!"):JsonData.buildError("更新用户失败!刷新后试试?")
        }
    }

}
