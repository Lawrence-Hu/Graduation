package cn.javaexception.service.impl;

import cn.javaexception.model.DeliverAddress;
import cn.javaexception.mapper.DeliverAddressMapper;
import cn.javaexception.service.DeliverAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
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
    public boolean addAddress(DeliverAddress address) {
        int insert = deliverAddressMapper.insert(address);
        return insert>0;
    }

    @Override
    public boolean deleteAddress(DeliverAddress[] addresses) {
        List<Integer> list = new ArrayList<>();
        Arrays.asList(addresses).forEach(address -> list.add(address.getId()));
        int i = deliverAddressMapper.deleteBatchIds(list);
        return i>=list.size();
    }
}
