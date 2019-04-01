package cn.javaexception.service.impl;

import cn.javaexception.entity.Province;
import cn.javaexception.mapper.ProvinceMapper;
import cn.javaexception.service.ProvinceService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import product_module.service.ProductInterface;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements ProvinceService {

    @Reference
    ProductInterface productInterface;
    @Override
    public void test(){
        System.out.println("service: "+productInterface);
        productInterface.findProductById(1);
    }
}
