package cn.javaexception.service.impl;

import cn.javaexception.mapper.ProductInterfceMapper;
import cn.javaexception.mapper.ProductMapper;
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
    private ProductInterfceMapper productInterfceMapper;

    /**
     * @param id
     * @return Product
     * @author huchao
     * @description 查找商品byID
     */
    @Override
    public Product findProductById(Integer id) {
        if (id == null) {
            return null;
        }
        Product product = productInterfceMapper.selectById(id);
        if (product == null) {
            return null;
        }
        return product;
    }

    /**
     * @param product
     * @return Boolean
     * @author huchao
     * @description 添加商品
     */
    @Override
    public Boolean addProduct(Product product) {
        int insert = productInterfceMapper.insert(product);
        if (insert > 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * @param id,type
     * @return boolean
     * @author huchao
     * @description 修改商品热度
     */
    @Override
    public boolean updateHotIndexById(Integer id, Boolean type) {
        if (id == null || type == null) {
            return false;
        }
        //查询商品
        Product product = findProductById(id);
        if (product == null) {
            return false;
        }
        Integer hotIndex = product.getHotIndex();
        if (type) {
            hotIndex++;
            int i = productInterfceMapper.updateById(product.setHotIndex(hotIndex));
            return i > 0;
        } else {
            hotIndex--;
            int i = productInterfceMapper.updateById(product.setHotIndex(hotIndex));
            return i > 0;
        }
    }
}

