package cn.javaexception.service.impl;


import cn.javaexception.mapper.ImgInterfaceMapper;
import cn.javaexception.mapper.ProductInterfceMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import product_module.entity.Img;
import product_module.entity.Product;

import product_module.entity.TProduct;
import product_module.service.ProductInterfce;

import java.util.List;


/**
 * @author hcuhao
 * @date 2019-03-08-13:57
 */
@Service
@Component
public class ProductIntefaceImpl implements ProductInterfce {
    @Autowired
    private ProductInterfceMapper productInterfceMapper;
    @Autowired
    private ImgInterfaceMapper imgInterfaceMapper;

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
     * @param tproduct
     * @return Boolean
     * @author huchao
     * @description 添加商品
     */
    @Override
    public Boolean addProduct(TProduct tproduct) {
        List<Img> imgList=tproduct.getImgs();
        if(tproduct.getProduct()==null||imgList==null)//验证判断
        {
            return  false;
        }

        int i=productInterfceMapper.insert(tproduct.getProduct());
        int a=0;
        for (Img img: imgList) {
            //插入img
            int insert =imgInterfaceMapper.insert(img.setProductId(tproduct.getProduct().getId()));
            if (insert == 1)
                a++;
        }
        //判断是否插入成功
        if (i == 1 && a == imgList.size()) {
            return true;
        }

        return false;
    }

    /**
     * @param productId
     * @return boolean
     * @author JDR
     * @description 删除商品
     */
    @Override
    public boolean delProduct(Integer productId) {
        if(productId==null) {
            return false;
        }
        productInterfceMapper.deleteById(productId);
        imgInterfaceMapper.delete(new QueryWrapper<Img>().eq("product_id",productId));
        return true;
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

