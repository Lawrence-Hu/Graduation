package cn.javaexception.service

import cn.javaexception.util.PageUtil

interface AdminService {
    def getAllUsersByPages(PageUtil parmas)
}