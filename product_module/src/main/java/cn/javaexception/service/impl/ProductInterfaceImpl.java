package cn.javaexception.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import product_module.entity.Product;
import product_module.service.ProductInterface;

/**
 * 2 * @Author: HuChao
 * 3 * @Date: 3/29/19 7:01 PM
 * 4
 */
@Service
public class ProductInterfaceImpl implements ProductInterface {
    @Override
    public Product findProductById(Integer id) {
        return null;
    }

    @Override
    public boolean updateHotIndexById(Integer id, boolean type) {
        return false;
    }
}
