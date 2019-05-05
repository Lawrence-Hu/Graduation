package cn.javaexception.service.impl;

import cn.javaexception.entity.DeliverAddress;
import cn.javaexception.entity.User;
import cn.javaexception.mapper.DeliverAddressMapper;
import cn.javaexception.service.DeliverAddressService;
import cn.javaexception.util.JsonData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@Service
public class DeliverAddressServiceImpl extends ServiceImpl<DeliverAddressMapper, DeliverAddress> implements DeliverAddressService {
    @Autowired
    DeliverAddressMapper deliverAddressMapper;

    @Override
    public JsonData addAddress(DeliverAddress address) {
        //获取当前用户！
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        //判断省id在不在
        //判断城市id在不在
        //城市id与省id是否一致
        int insert = deliverAddressMapper.insert(address.setUserId(principal.getId()).setCreateTime(new Date()));
        return insert > 0 ? JsonData.buildSuccess("添加地址成功！") : JsonData.buildError("添加地址失败！");
    }

    /**
     * @param
     * @return
     * @author huchao
     * @description 此处有bug 若需完全没问题还需要查数据库删除的地址id是否是当前用户的！！！ //bug以修复
     */
    @Override
    public JsonData deleteAddress(String[] addresseIds) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        //删除的条数

        int delete  = deliverAddressMapper.delete(new QueryWrapper<DeliverAddress>().eq("user_id", principal.getId()).in("id", addresseIds));

        //前端数据有误
        if (0 < delete && delete < addresseIds.length) {
            return JsonData.buildSuccess("只删除了部分数据,请检查传入参数是否正确！");
        }
        return delete==addresseIds.length? JsonData.buildSuccess("删除成功！") : JsonData.buildError("删除失败！请检查参数！");
    }

    @Override
    public JsonData updateAddress(DeliverAddress address) {
        //判断是否是当前用户
        Subject subject = SecurityUtils.getSubject();
        User principal = (User)subject.getPrincipal();

        DeliverAddress updateAddress = deliverAddressMapper.selectById(address.getId());
        //判断地址是否存在
        if(updateAddress ==null){
            return JsonData.buildError("没有该收货地址！更新失败！");
        }
        //查新改地址是否是当前用户的地址
        if (!updateAddress.getUserId().equals(principal.getId())){
            return JsonData.buildError("更新失败");
        }
        //更新地址
        return address.setUpdateTime(new Date()).updateById()?JsonData.buildSuccess("更新成功"):JsonData.buildError("更新失败");
    }
}
