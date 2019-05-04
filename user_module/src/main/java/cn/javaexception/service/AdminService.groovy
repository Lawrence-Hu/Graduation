package cn.javaexception.service

import cn.javaexception.entity.User
import cn.javaexception.util.PageUtil
import utils.JsonData

interface AdminService {
    def getAllUsersByPages(PageUtil parmas,String type)

    def updateUserInfoById(User user)

    JsonData changeUserStatusById(String id, String type);
}