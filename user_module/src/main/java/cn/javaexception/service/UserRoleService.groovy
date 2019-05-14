package cn.javaexception.service

import cn.javaexception.entity.UserRole
import cn.javaexception.util.PageUtil
import com.alibaba.fastjson.JSONObject
import com.baomidou.mybatisplus.extension.service.IService

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huchao
 * @since 2019-05-07
 */
interface UserRoleService extends IService<UserRole> {

    def getAllUsersByRoles(PageUtil pageUtil)

    def findUserRoleById(String s)

    def findAllRoles()

    def deleteUserRole(JSONObject params)

    def asginUserRole(JSONObject params)
}
