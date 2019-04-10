package cn.javaexception.service;

import cn.javaexception.entity.DeliverAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import utils.JsonData;

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

    JsonData deleteAddress(String[] addressIds);

    JsonData updateAddress(DeliverAddress address);
}
