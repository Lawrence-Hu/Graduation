package cn.javaexception.service.impl;

import Util.JsonData;
import cn.javaexception.mapper.BrandInterfaceMapper;
import cn.javaexception.mapper.CatagoryInterfaceMapper;
import cn.javaexception.mapper.ImgInterfaceMapper;
import cn.javaexception.mapper.ProductInterfceMapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import product_module.entity.Brand;
import product_module.entity.Catagory;
import product_module.entity.Img;
import product_module.entity.Product;
import product_module.service.ProductInterfce;
import user_module.service.UserInterface;

/**
 * @author hcuhao
 * @date 2019-03-08-13:57
 */
@Service
@Component
public class ProductIntefaceImpl implements ProductInterfce {
    @Autowired
    ProductInterfceMapper productInterfceMapper;

    @Autowired
    BrandInterfaceMapper brandInterfaceMapper;

    @Autowired
    CatagoryInterfaceMapper catagoryInterfaceMapper;

    @Autowired
    ImgInterfaceMapper imgInterfaceMapper;

    @Autowired
    UserInterface userInterface;

    @Override
    public boolean addBrand(Brand brand) {
        if(brand.getName()== null)
        {
            return false;
        }
        else
            brandInterfaceMapper.insert(brand);
        return  true;
    }

    @Override
    public boolean delBrand(Integer id) {
        if(id.equals(""))
        {
            return false;
        }else if (brandInterfaceMapper.selectById(id)==null){

            return false;
        }
        else
            brandInterfaceMapper.deleteById(id);
        {
            return true;
        }
    }

    @Override
    public boolean addCatagoy(Catagory catagory) {

        if(catagory.getName()== null)
        {
            return false;
        }
        else
            catagoryInterfaceMapper.insert(catagory);
        return true;
    }

    @Override
    public boolean delCatagoy(Integer id) {

        if(id.equals(""))
        {
            return false;
        }else if (catagoryInterfaceMapper.selectById(id) == null){

            return false;
        }
        else
            catagoryInterfaceMapper.deleteById(id);
        {
            return true;
        }
    }

    @Override
    public boolean addImg(Img img) {

        if(img.getImgUrl()== null)
        {
            return false;
        }
        else if(productInterfceMapper.selectById(img.getProductId())== null){

            return false;
        }
        else{
        imgInterfaceMapper.insert(img);
        return  true;
      }
    }

    @Override
    public boolean deleImg(Integer id) {

        if(id.equals(""))
        {
            return false;

        }else if (imgInterfaceMapper.selectById(id).equals("")){

            return false;
        }
        else
            imgInterfaceMapper.deleteById(id);
        {
            return true;
        }
    }

    @Override
    public boolean addproduct(Product product) {
        if(product.getProductName()==null)
        {return false;}
        if(product.getMarketPrice()==null)
        {return false;}
        if(product.getShopPrice()==null)
        {return false;}
        if(product.getProductsStock()==null)
        {return false;}
        if(product.getBrandId()==null)
        {return false;}
        if(product.getProductDesc()==null)
        {return false;}
        if(product.getProductCataId()==null)
        {return false;}
        if(product.getUserId()==null)
        {return false;}
            else if (userInterface.findUserById(product.getUserId())==null)
        {return false;}

        else
        {
            productInterfceMapper.insert(product);
            return true;
        }
    }

    @Override
    public boolean delproduct(Integer id) {

        productInterfceMapper.deleteById(id);
        return false;
    }

    @Override
    public boolean updateproduct(Product product) {
        if(productInterfceMapper.selectById(product.getId())==null)
        {return false;}
         else
        {
            productInterfceMapper.updateById(product);
            return true;

        }
    }

    @Override
    public Product findProductById(Integer id) {
        if (id == null || "".equals(id.toString())) {
            return null;
        }
        return productInterfceMapper.selectById(id);
    }
}

