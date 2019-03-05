package cn.javaexception.service;

import cn.javaexception.model.DeliverAddress;
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

    boolean addAddress(DeliverAddress address);

    boolean deleteAddress(DeliverAddress[] addresses);
}
