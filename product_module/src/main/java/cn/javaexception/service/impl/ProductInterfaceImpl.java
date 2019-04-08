package cn.javaexception.service.impl;

import cn.javaexception.mapper.ProductInterfaceMapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import product_module.entity.Product;
import product_module.service.ProductInterface;
import utils.JsonData;

/**
 * 2 * @Author: HuChao
 * 3 * @Date: 3/29/19 7:01 PM
 * 4
 */
@Service
public class ProductInterfaceImpl implements ProductInterface {
    @Autowired
    ProductInterfaceMapper productMapper;
    @Override
    public JsonData findProductById(Integer id) {
        if(id ==null){
            return  JsonData.buildError("商品id不能为空！");
        }
        Product product = productMapper.selectById(id);
        if(product!=null)
            return JsonData.buildSuccess(product);
        return JsonData.buildError("没有该商品！");
    }

    @Override
    public boolean updateHotIndexById(Integer id, boolean type) {
        return false;
    }
}
