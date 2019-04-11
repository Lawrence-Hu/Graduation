package cn.javaexception.service.impl;

import cn.javaexception.mapper.ImgInterfaceMapper;
import cn.javaexception.mapper.ProductInterfaceMapper;
import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import product_module.entity.Product;
import product_module.service.ProductInterface;
import utils.Error;
import utils.JsonData;
import utils.ValidatorUtil;

import java.time.LocalDateTime;
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
            return  JsonData.buildError("商品id不能为空！");
        }
        Product product = productMapper.selectById(id);
        if(product!=null)
            return JsonData.buildSuccess(product);
        return JsonData.buildError("请输入正确的id号！");
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
        Error error = ValidatorUtil.validate(product);

      if (error.hasError()){
           return JsonData.buildError(error.getErrorInfo());
       }
//        if (product == null ||  imgList == null)//验证判断
//        {
//            return JsonData.buildError("请输入商品的信息!");
//        }
//        if(product.getProductName()==null)
//        {
//            return  JsonData.buildError("商品名字不能为空!");
//        }
//        if(product.getMarketPrice()==null||product.getMarketPrice()<0)
//        {
//            return  JsonData.buildError("商品价格不能为空或负数!");
//        }
//        if(product.getProductsStock()==null||product.getProductsStock()<0)
//        {
//            return  JsonData.buildError("商品库存不能为空或负数!");
//        }
//        if(product.getProductUnitId()==null)
//        {
//            return  JsonData.buildError("商品规格不能为空!");
//        }
//
//        if(product.getBrandId()==null)
//        {
//            return  JsonData.buildError("商品品牌不能为空!");
//        }
//        if(product.getProductDesc()==null)
//        {
//            return  JsonData.buildError("商品描述不能为空!");
//        }
        product.setSaleTime(LocalDateTime.now() );

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
            return JsonData.buildError("请输入商品的id号!");
        }
        Product product= productMapper.selectById(id);
        if(product == null)
        {
            return  JsonData.buildError("请输入正确的商品id号!");
        }

        int i=productMapper.deleteById(id);
        int j=imgInterfaceMapper.delete(new QueryWrapper<Product.Img>().eq("product_id",id));

        return i+j>0? JsonData.buildSuccess("删除成功!"):JsonData.buildError("系统异常，请稍后再试");

    }

    /**
     * @param id,type
     * @return JsonData
     * @author JDR
     * @description 是否开售
     */
    @Override
    public JsonData updateIsSaleById(Integer id, Boolean type) {
        if(id==null||type==null)
        {
            return  JsonData.buildError("商品id或type不能为空!");
        }
        Product product=productMapper.selectById(id);
        if(product==null)
        {
            JsonData.buildError("商品不存在，请检查id是否正确!");
        }
        String msg = product.getProductStatus();
        if(msg=="-1")
        {
            JsonData.buildError("商品违规或已下架!");
        }
        if(msg=="0")
        {
            JsonData.buildError("商品未审核!");
        }
        //type为真,修改值为1
        if(product.getIsSale()==false && type==true)
        {
            productMapper.updateById(product.setIsSale(true));
            return JsonData.buildSuccess("修改成功,商品已开售!");
        }
        if(product.getIsSale()==true && type==false)
        {
            productMapper.updateById(product.setIsSale(false));
            return  JsonData.buildSuccess("修改成功，商品已停售");
        }
        return JsonData.buildError("操作失败，请检查商品状态后重试!");
    }
    /**
     * @param id,type
     * @return JsonData
     * @author JDR
     * @description 是否精品
     */
    @Override
    public JsonData updateIsBestById(Integer id, Boolean type) {

        if(id==null||type==null)
        {
            return  JsonData.buildError("商品id或type不能为空!");
        }
        Product product=productMapper.selectById(id);
        if(product==null)
        {
            JsonData.buildError("商品不存在，请检查id是否正确!");
        }
        String msg = product.getProductStatus();
        if(msg=="-1")
        {
            JsonData.buildError("商品违规或已下架!");
        }
        if(msg=="0")
        {
            JsonData.buildError("商品未审核!");
        }
        if(product.getIsBest()==false && type==true)
        {
            productMapper.updateById(product.setIsBest(true));
            return JsonData.buildSuccess("修改成功，商品已加精!");
        }
        if(product.getIsBest()==true&& type==false)
        {
            productMapper.updateById(product.setIsBest(false));
            return JsonData.buildSuccess("修改成功，商品已取消加精!");
        }

        return JsonData.buildError("操作失败，请检查商品状态后重试!");
    }

    /**
     * @param id,type
     * @return JsonData
     * @author JDR
     * @description 修改新品
     */

    //默认上架为新品，审核过后才能修改
    @Override
    public JsonData updateIsNewById(Integer id, Boolean type) {

        if(id==null||type==null)
        {
            return  JsonData.buildError("商品id或type不能为空!");
        }
        Product product=productMapper.selectById(id);
        if(product==null)
        {
            JsonData.buildError("商品不存在，请检查id是否正确!");
        }
        String msg = product.getProductStatus();
        if(msg=="0")
        {
            JsonData.buildError("商品未审核!");
        }
        if(product.getIsNew()==true && type==false)
        {
            productMapper.updateById(product.setIsNew(false));
            return JsonData.buildSuccess("修改成功，商品已取消新品!");
        }

        return JsonData.buildError("操作失败，请检查商品状态后重试!");
    }


    /**
     * @param id,type
     * @return JsonData
     * @author JDR
     * @description 是否推荐
     */
    @Override
    public JsonData updateIsRecomById(Integer id, Boolean type) {

        if(id==null||type==null)
        {
            return  JsonData.buildError("商品id或type不能为空!");
        }
        Product product=productMapper.selectById(id);
        if(product==null)
        {
            JsonData.buildError("商品不存在，请检查id是否正确!");
        }

        String msg = product.getProductStatus();
        if(msg=="-1")
        {
            JsonData.buildError("商品违规或已下架!");
        }
        if(msg=="0")
        {
            JsonData.buildError("商品未审核!");
        }
        if(product.getIsRecom()==false && type==true)
        {
            productMapper.updateById(product.setIsRecom(true));
            return JsonData.buildSuccess("修改成功，商品已推荐!");
        }
        if(product.getIsRecom()==true&& type==false)
        {
            productMapper.updateById(product.setIsRecom(false));
            return JsonData.buildSuccess("修改成功，商品已取消推荐!");
        }

        return JsonData.buildError("操作失败，请检查商品状态后重试!");
    }
    /**
     * @param id，type
     * @return JsonDate
     * @author JDR
     * @description 修改商品审核状态
     * */
    @Override
    public JsonData updateProductStatusById(Integer id, Boolean type) {
        if(id==null||type==null)
        {
            return  JsonData.buildError("商品id或type不能为空!");
        }
        Product product=productMapper.selectById(id);
        if(product==null)
        {
            JsonData.buildError("商品不存在，请检查id是否正确!");
        }
        if(product.getProductStatus()=="0"&&type==true)
        {
            productMapper.updateById(product.setProductStatus("1"));
            return JsonData.buildSuccess("修改成功，商品为审核通过状态");
        }
        if(product.getProductStatus()=="0"&&type==false)
        {
            productMapper.updateById(product.setProductStatus("-1"));
            return JsonData.buildSuccess("修改成功，商品审核未通过状态");
        }

        if(product.getProductStatus()=="1"&&type==false)
        {
            productMapper.updateById(product.setProductStatus("-1"));
            return  JsonData.buildSuccess("修改成功，商品为下架或者违规状态");
        }
        return JsonData.buildError("操作失败,请重试！");
    }

    /**
     * @param id
     * @return Boolean
     * @author JDR
     * @description 查询商品是否开售
     * */
    @Override
    public Boolean findIsSaleById(Integer id) {
        if(id == null)
        {
            System.out.println("id不能为空!");
            return false;
        }
        Product product=productMapper.selectById(id);
        if(product==null)
        {
            System.out.println("id错误,请重试!");
            return false;
        }
        return product.getIsSale();
    }

    /**
     * @param id
     * @return Boolean
     * @author JDR
     * @description 查询商品是否加精
     * */
    @Override
    public Boolean findIsBestById(Integer id) {
        if(id == null)
        {
            System.out.println("id不能为空!");
            return false;
        }
        Product product=productMapper.selectById(id);
        if(product==null)
        {
            System.out.println("id错误,请重试!");
            return false;
        }
        return product.getIsBest();
    }

    /**
     * @param id
     * @return Boolean
     * @author JDR
     * @description 查询商品是否新品
     * */
    @Override
    public Boolean findIsNewById(Integer id) {
        if(id == null)
        {
            System.out.println("id不能为空!");
            return false;
        }
        Product product=productMapper.selectById(id);
        if(product==null)
        {
            System.out.println("id错误,请重试!");
            return false;
        }
        return product.getIsNew();
    }

    /**
     * @param id
     * @return Boolean
     * @author JDR
     * @description 查询商品是否被推荐
     * */
    @Override
    public Boolean findIsRecomById(Integer id) {
        if (id == null) {
            System.out.println("id不能为空!");
            return false;
        }
        Product product = productMapper.selectById(id);
        if (product == null) {
            System.out.println("id错误,请重试!");
            return false;
        }
        return product.getIsRecom();
    }
    /**
     * @param id
     * @return Boolean
     * @author JDR
     * @description 查询商品审核状态
     * */
    @Override
    public JsonData findProductStatusById(Integer id) {
        if (id == null) {
            return JsonData.buildError("id不能为空!");
        }
        Product product = productMapper.selectById(id);
        if (product == null) {
            JsonData.buildError("id错误,请重试!");
        }
        String msg = product.getProductDesc();
        if (msg == "-1")
        {
            return JsonData.buildSuccess(-1,null,"商品已下架");
        }
        if(msg=="0")
        {
            return JsonData.buildSuccess(0,null,"商品未审核");
        }
        return JsonData.buildSuccess(1,null,"商品已审核");
    }
}

