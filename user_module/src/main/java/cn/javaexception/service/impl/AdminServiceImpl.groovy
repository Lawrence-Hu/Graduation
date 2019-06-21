package cn.javaexception.service.impl

import cn.javaexception.entity.UserAudit
import cn.javaexception.entity.AuditImg
import cn.javaexception.entity.User
import cn.javaexception.entity.UserStatus
import cn.javaexception.mapper.*
import cn.javaexception.service.AdminService
import cn.javaexception.util.JsonData
import cn.javaexception.util.PageUtil
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.shiro.SecurityUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class AdminServiceImpl implements AdminService{
    @Autowired
    UserMapper userMapper
    @Autowired
    RoleMapper roleMapper
    @Autowired
    UserStatusMapper statusMapper
    @Autowired
    PermissionMapper permissionMapper
    @Autowired
    UserRoleMapper userRoleMapper
    @Autowired
    UserAuditMapper userAuditMapper
    @Autowired
    AuditImgMapper auditImgMapper

    @Override
    def getAllUsersByPages(PageUtil params,String type) {
        Page<User> page = new Page<>(params.getCurrentPage(),params.getPageSize())

        def users = userMapper.selectAllUsers(page, type)
        page.setRecords(users)
        List<UserStatus> status = statusMapper.selectList()
        def data = JSON.toJSON(page) as JSONObject
        data.put("statuses",status)
        return JsonData.buildSuccess(data)
    }

    JsonData updateUserInfoById(User user) {
        //直接更新
        User dbUser = userMapper.selectById(user.getId())

        def permissions = permissionMapper.getPermissonsByUserId(user.getId())

        def hasPer = permissions.stream().any { permission -> permission.getName() == "admin:updateUserInfo" }

        if (hasPer){
            return JsonData.buildError("当前用户与你有相同权限,不能修改!")
        }
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
        return user.updateById()?JsonData.buildSuccess("修改用户信息成功!"):JsonData.buildError("未修改任何信息!")
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

    @Override
    def findUserAuthByPages(PageUtil params,Boolean isHandled) {
        Page<UserAudit> page = new Page<>(params.getCurrentPage(),params.getPageSize())
//        def records = userAuditMapper.selectPage(page, new QueryWrapper<UserAudit>().eq("is_handled",isHandled).orderByDesc("created_time"))
//        def data = records.getRecords()
//
//        if (records.records.isEmpty()){
//            return  JsonData.buildError("最近没有要处理的认证信息")
//        }
//        def imgs = auditImgMapper.selectList(new QueryWrapper<AuditImg>().in("audit_id", data.stream().map({record->record.getId()}).collect(Collectors.toList())))
//        def userImg = imgs.groupBy { img -> img.getAuditId() }
//        for(userAudit in data){
//            userAudit.setImgs(userImg.get(userAudit.getId()))
//        }
        def users = userAuditMapper.getUserAuditByPage(page, isHandled)
        page.setRecords(users)
        return JsonData.buildSuccess(page)
    }

    @Override
    def updateUserAuthStatus(JSONObject params) {
        def user = SecurityUtils.getSubject().getPrincipal() as User
        def user_id = params.get("user_id") as String
        def auth_id = params.get("id") as String
        def comments = params.get("comments") as String
        def isPassed = params.get("isPassed") as Boolean
        userMapper.updateById(new User().setId(user_id).setCertification(isPassed))
        userAuditMapper.updateById(new UserAudit()
                                    .setId(auth_id)
                                    .setStatus(isPassed)
                                    .setComments(comments)
                                    .setIsHandled(true)
                                    .setAuditUserId(user.getId()))
        return JsonData.buildSuccess("处理认证信息成功！")
    }
}
