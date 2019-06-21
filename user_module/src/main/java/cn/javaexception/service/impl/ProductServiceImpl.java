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
import org.apache.shiro.SecurityUtils;
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
    @Autowired
    ProductBrandMapper brandMapper;

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
       Page<ProductAudit> page = new Page<>(pageUtil.getCurrentPage(),pageUtil.getPageSize());
        List<ProductAudit> audits = productAuditMapper.getProductAudits(page,isHandled);
        page.setRecords(audits);
        return JsonData.buildSuccess(page);
    }

    @Override
    public JsonData updateAuditStatus(JSONObject params) {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        productAuditMapper.updateById(
                new ProductAudit()
                        .setId(params.getString("audit_id"))
                        .setIsHandled(true)
                        .setStatus(params.getBoolean("is_passed"))
                        .setAuditUserId(principal.getId()));
        productMapper.updateById(
                new Product()
                        .setId(params.getString("product_id"))
                        .setIsSale(params.getBoolean("is_passed"))
                        .setIsAudit(params.getBoolean("is_passed")));
        return JsonData.buildSuccess("认证商品成功！");
    }

}
