package cn.javaexception.service.impl;

import cn.javaexception.mapper.DeliverAddressMapper;
import cn.javaexception.entity.DeliverAddress;
import cn.javaexception.entity.User;
import cn.javaexception.service.DeliverAddressService;
import cn.javaexception.util.JsonData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        int insert = deliverAddressMapper.insert(address.setUserId(principal.getId()).setCreateTime(LocalDateTime.now()));
        return insert > 0 ? JsonData.buildSuccess("添加地址成功！") : JsonData.buildError("添加地址失败！");
    }

    /**
     * @param
     * @return
     * @author huchao
     * @description 此处有bug 若需完全没问题还需要查数据库删除的地址id是否是当前用户的！！！ //bug以修复
     */
    @Override
    public JsonData deleteAddress(Integer[] addresseIds) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        //删除的条数
        int delete = 0;
        for (Integer id : addresseIds) {
            int i = deliverAddressMapper.delete(new QueryWrapper<DeliverAddress>().eq("user_id", principal.getId()).eq("id", id));
            if(i==1)
            delete++;
        }
        //前端数据有误
        if (0 < delete && delete < addresseIds.length) {
            return JsonData.buildSuccess("只删除了部分数据,请检查传入参数是否正确！");
        }
        return delete==addresseIds.length? JsonData.buildSuccess("删除成功！") : JsonData.buildError("删除失败！请检查参数是否正确！");
    }

    @Override
    public JsonData updateAddress(DeliverAddress address) {
        //判断是否是当前用户
        Subject subject = SecurityUtils.getSubject();
        User principal = (User)subject.getPrincipal();
        //判断地址是否存在
        if(deliverAddressMapper.selectById(address.getId())==null){
            return JsonData.buildError("没有改收货地址！更新失败！");
        }
        //查新改地址是否是当前用户的地址
        DeliverAddress ret = deliverAddressMapper.selectById(address.getId());
        if (!ret.getUserId().equals(principal.getId())){
            return JsonData.buildError("用户操作非法！");
        }
        //跟新地址
        boolean b = ret.setUpdateTime(LocalDateTime.now()).updateById();
        return b?JsonData.buildSuccess("更新成功"):JsonData.buildError("更新失败");
    }
}
