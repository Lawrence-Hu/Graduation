package cn.javaexception.service.impl;

import cn.javaexception.mapper.ImgInterfaceMapper;
import cn.javaexception.mapper.ProductInterfaceMapper;
import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import product_module.entity.Product;
import product_module.service.ProductInterface;
import utils.JsonData;

import java.util.List;

/**
 * 2 * @Author: HuChao
 * 3 * @Date: 3/29/19 7:01 PM
 * 4
 */
@Service
public class ProductInterfaceImpl implements ProductInterface {
    @Autowired
    ProductInterfaceMapper productMapper;
    @Autowired
    ImgInterfaceMapper imgInterfaceMapper;


    /**
     * @param id
     * @return JsonData
     * @author JDR
     * @description 查询商品
     * */
    @Override
    public JsonData findProductById(Integer id) {
        if(id ==null){
            return  JsonData.buildError("商品id为空！");
        }
        Product product = productMapper.selectById(id);
        if(product!=null)
            return JsonData.buildSuccess(product);
        return JsonData.buildError("没有该商品！");
    }

    @Override
    public boolean updateHotIndexById(Integer id, Boolean type) {
        return false;
    }

//    /**
//     * @param id,type
//     * @return JsonData
//     * @author huchao
//     * @description 修改商品热度
//     * */
//    @Override
//    public JsonData updateHotById(Integer id, Integer type) {
//
//        if(id == null||type == null)
//        {
//            return JsonData.buildError("商品id或热度为空！");
//        }
//        //查询商品
//        Product product = productMapper.selectById(id);
//        type=product.getHotIndex()+type;
//
//        if (product == null) {
//            return JsonData.buildError("商品不存在!");
//        }
//        int i = productMapper.updateById(product.setHotIndex(type));
//
//        return i>0?JsonData.buildSuccess("修改成功！"):JsonData.buildError("系统异常请稍后再试！");
//    }

    /**
     * @param product
     * @return JsonData
     * @author JDR
     * @description 添加商品
     */
    @Override
    public JsonData addProduct(Product product) {

        List<Product.Img> imgList = product.getImg();
        if (product == null || imgList == null)//验证判断
        {
            return JsonData.buildError("请输入商品的信息");
        }
        int i = productMapper.insert(product);
        int a = 0;
        for (Product.Img img : imgList) {
            //插入img
            int insert = imgInterfaceMapper.insert(img.setProductId(product.getId()));
            if (insert == 1)
                a++;
        }
        //判断是否插入成功
        if (i == 1 && a == imgList.size()) {
            return JsonData.buildSuccess("添加商品成功");
        }
        return JsonData.buildError("系统异常,请稍后再试");
   }

    /**
     * @param id
     * @return JsonData
     * @author JDR
     * @description 删除商品
     */
    @Override
    public JsonData delProductById(Integer id) {

        if(id==null) {
            return JsonData.buildError("请输入商品的id号");
        }
        Product product= productMapper.selectById(id);
        if(product == null)
        {
            return  JsonData.buildError("请输入正确的商品id号");
        }

        int i=productMapper.deleteById(id);
        int j=imgInterfaceMapper.delete(new QueryWrapper<Product.Img>().eq("product_id",id));

        return i+j>0? JsonData.buildSuccess("删除成功"):JsonData.buildError("系统异常，请稍后再试");

    }
}
