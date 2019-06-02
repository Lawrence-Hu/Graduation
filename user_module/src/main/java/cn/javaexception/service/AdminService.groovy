 package cn.javaexception.service

import cn.javaexception.entity.User
import cn.javaexception.util.JsonData
import cn.javaexception.util.PageUtil
import com.alibaba.fastjson.JSONObject

interface AdminService {
    def getAllUsersByPages(PageUtil params,String type)
    def updateUserInfoById(User user)
    JsonData changeUserStatusById(String id, String type);
    def findUserAuthByPages(PageUtil pageUtil,Boolean isHandled)
    def updateUserAuthStatus(JSONObject params)

}