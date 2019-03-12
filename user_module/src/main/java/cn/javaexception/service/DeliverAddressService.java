package cn.javaexception.service;

import cn.javaexception.entity.DeliverAddress;
import cn.javaexception.util.JsonData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
public interface DeliverAddressService extends IService<DeliverAddress> {

    JsonData addAddress(DeliverAddress address);

    JsonData deleteAddress(Integer[] addressIds);

    JsonData updateAddress(DeliverAddress address);
}
