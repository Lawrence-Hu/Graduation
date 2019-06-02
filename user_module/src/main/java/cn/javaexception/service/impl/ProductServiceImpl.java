package cn.javaexception.service.impl;

import cn.javaexception.entity.*;
import cn.javaexception.mapper.*;
import cn.javaexception.service.ProductService;
import cn.javaexception.util.JsonData;
import cn.javaexception.util.PageUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    UserMapper userMapper;
    @Autowired
    ProductCategoryMapper productCategoryMapper;
    @Autowired
    ProductUnitMapper productUnitMapper;
    @Autowired
    AuditImgMapper auditImgMapper;
    @Autowired
    ProductAuditMapper productAuditMapper;

//    //查询商品byid
//    @Override
//    public Product getProductInfoById(String id) {
//        return productMapper.selectById(id);
//    }
//    /**
//     * @param product
//     * @return Jsondata
//     * @author JDR
//     * @description 添加商品
//     */
//    @Override
//    public JsonData addProduct(Product product) {
//        List<Img> imgList = product.getImg();
//        if(product==null)
//         {
//            return  JsonData.buildError("商品数据或图片信息不能为空！");
//         }
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
//        product.setSaleTime(LocalDateTime.now() );
//
//        int i = productMapper.insert(product);
//        int a = 0;
//        for (Img img : imgList) {
//            //插入img
//            int insert = imgMapper.insert(img.setProductId(product.getId()));
//            if (insert == 1)
//                a++;
//        }
//        //判断是否插入成功
//        if (i == 1 && a == imgList.size()) {
//            return JsonData.buildSuccess("添加商品成功");
//        }
//        return JsonData.buildError("系统异常,请稍后再试");
//    }
//
//
//    /**
//     * @param id
//     * @return JsonData
//     * @author JDR
//     * @description 删除商品
//     */
//    @Override
//    public JsonData delProductById(String[] id) {
//
//        if(id==null) {
//            return JsonData.buildError("请输入商品的id号!");
//        }
//        Product product= productMapper.selectById(id);
//        if(product == null)
//        {
//            return  JsonData.buildError("请输入正确的商品id号!");
//        }
//
//        int i=productMapper.deleteById(id);
//        int j=imgMapper.delete(new QueryWrapper<Img>().eq("product_id",id));
//
//        return i+j>0? JsonData.buildSuccess("删除成功!"):JsonData.buildError("系统异常，请稍后再试");
//
//    }
//    /**
//     * @param id,type
//     * @return JsonData
//     * @author JDR
//     * @description 修改新品
//     */
//
//    //默认上架为新品，审核过后才能修改
//    @Override
//    public JsonData updateIsNewById(String id, Boolean type) {
//
//        if(id==null||type==null)
//        {
//            return  JsonData.buildError("商品id或type不能为空!");
//        }
//        Product product=productMapper.selectById(id);
//        if(product==null)
//        {
//            return  JsonData.buildError("商品不存在，请检查id是否正确!");
//        }
//        String msg = product.getStatus();
//        if(msg=="0")
//        {
//            JsonData.buildError("商品未审核!");
//        }
//        if(product.getIsNew()==true && type==false)
//        {
//            productMapper.updateById(product.setIsNew(false));
//            return JsonData.buildSuccess("修改成功，商品已取消新品!");
//        }
//
//        return JsonData.buildError("操作失败，请检查商品状态后重试!");
//    }

    @Override
    public Product getProductInfoById(String id) {
        return null;
    }

    @Override
    public JsonData addProduct(Product product) {
        return null;
    }

    @Override
    public JsonData delProductById(String[] id) {
        return null;
    }

    @Override
    public JsonData updateIsNewById(String id, Boolean type) {
        return null;
    }

    @Override
    public JsonData getProductsByPages(PageUtil page) {
        Page<Product> params = new Page<>(page.getCurrentPage(),page.getPageSize());
        IPage<Product> pages = productMapper.selectPage(params, null);
        List<ProductCategory> productCategories = productCategoryMapper.selectList(null);
        List<ProductUnit> productUnits = productUnitMapper.selectList(null);
        JSONObject object = new JSONObject();
        object.put("products",pages);
        object.put("category",productCategories);
        object.put("unit",productUnits);
        return JsonData.buildSuccess(object);
    }

    @Override
    public JsonData getAuditProductsByPages(PageUtil pageUtil,Boolean isHandled) {
        IPage<ProductAudit> data = productAuditMapper.selectPage(new Page<>(pageUtil.getCurrentPage(), pageUtil.getCurrentPage()),
                new QueryWrapper<ProductAudit>().eq("is_handled", isHandled));
        List<String> auditIds = data.getRecords().stream().map(ProductAudit::getId).collect(Collectors.toList());
        List<AuditImg> auditImgs = auditImgMapper.selectList(new QueryWrapper<AuditImg>().in("audit_id", auditIds));
        Map<String, List<AuditImg>> imgs = auditImgs.stream().collect(Collectors.groupingBy(AuditImg::getAuditId));
        for (ProductAudit audit : data.getRecords()){
            audit.setImgs(imgs.get(audit.getId()));
        }
        return JsonData.buildSuccess(data);
    }

    @Override
    public JsonData updateAuditStatus(JSONObject params) {
        productAuditMapper.updateById(
                new ProductAudit()
                        .setId(params.getString("audit_id"))
                        .setIsHandled(true)
                        .setStatus(params.getBoolean("is_passed")));
        productMapper.updateById(
                new Product()
                        .setId(params.getString("product_id"))
                        .setIsSale(params.getBoolean("is_passed"))
                        .setIsAudit(params.getBoolean("is_passed")));
        return JsonData.buildSuccess("认证商品成功！");
    }

}
