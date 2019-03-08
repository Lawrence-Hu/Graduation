package cn.javaexception.service.impl;

import cn.javaexception.mapper.ProductInterfceMapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product_module.entity.Product;
import product_module.service.ProductInterfce;

/**
 * @author hcuhao
 * @date 2019-03-08-13:57
 */
@Service
@Component
public class ProductIntefaceImpl implements ProductInterfce {
    @Autowired
    ProductInterfceMapper productInterfceMapper;

    @Override
    public Product findProductById(Integer id) {
        if (id == null || "".equals(id.toString())) {
            return null;
        }
        return productInterfceMapper.selectById(id);
    }
}
