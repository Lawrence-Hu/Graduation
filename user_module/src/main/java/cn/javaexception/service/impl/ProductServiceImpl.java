package cn.javaexception.service.impl;

import cn.javaexception.entity.Img;
import cn.javaexception.entity.Product;
import cn.javaexception.mapper.ImgMapper;
import cn.javaexception.mapper.ProductMapper;
import cn.javaexception.service.ProductService;
import cn.javaexception.util.JsonData;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huchao
 * @since 2019-05-05
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ImgMapper imgMapper;

    //查询商品byid
    @Override
    public Product getProductInfoById(String id) {
        return productMapper.selectById(id);
    }
    /**
     * @param product
     * @return Jsondata
     * @author JDR
     * @description 添加商品
     */
    @Override
    public JsonData addProduct(Product product) {
        List<Img> imgList = product.getImg();
        if(product==null)
         {
            return  JsonData.buildError("商品数据或图片信息不能为空！");
         }
        if(product.getProductName()==null)
        {
            return  JsonData.buildError("商品名字不能为空!");
        }
        if(product.getMarketPrice()==null||product.getMarketPrice()<0)
        {
            return  JsonData.buildError("商品价格不能为空或负数!");
        }
        if(product.getProductsStock()==null||product.getProductsStock()<0)
        {
            return  JsonData.buildError("商品库存不能为空或负数!");
        }
        if(product.getProductUnitId()==null)
        {
            return  JsonData.buildError("商品规格不能为空!");
        }

        if(product.getBrandId()==null)
        {
            return  JsonData.buildError("商品品牌不能为空!");
        }
        if(product.getProductDesc()==null)
        {
            return  JsonData.buildError("商品描述不能为空!");
        }
        product.setSaleTime(LocalDateTime.now() );

        int i = productMapper.insert(product);
        int a = 0;
        for (Img img : imgList) {
            //插入img
            int insert = imgMapper.insert(img.setProductId(product.getId()));
            if (insert == 1)
                a++;
        }
        //判断是否插入成功
        if (i == 1 && a == imgList.size()) {
            return JsonData.buildSuccess("添加商品成功");
        }
        return JsonData.buildError("系统异常,请稍后再试");
    }
}
