package cn.javaexception.service.impl;

import cn.javaexception.mapper.CategoryItemsMapper;
import cn.javaexception.mapper.CategoryMapper;
import cn.javaexception.mapper.ProductMapper;
import cn.javaexception.mapper.ProductUnitMapper;
import cn.javaexception.model.Category;
import cn.javaexception.model.CategoryItems;
import cn.javaexception.model.Product;
import cn.javaexception.service.HomeService;
import cn.javaexception.vo.CategoriesAndCarouselsVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.JsonData;

import java.util.ArrayList;
import java.util.List;


@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    ProductUnitMapper productUnitMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CategoryItemsMapper categoryItemsMapper;

    @Override
    public JsonData getHome() {

        return null;
    }

    @Override
    public JsonData getCategories() {
        return null;
    }

    @Override
    public JsonData getCarousel() {
        return null;
    }

    @Override
    public JsonData getNewProductForDay() {
        return null;
    }

    @Override
    public JsonData getHotProduct() {
        return null;
    }

    @Override
    public JsonData getRecommendProduct() {
        return null;
    }

    @Override
    public JsonData getCategoriesAndCarousels() {
        CategoriesAndCarouselsVo data = new CategoriesAndCarouselsVo();

        List<Product> products = productMapper.selectList(new QueryWrapper<Product>()
                                              .select("id", "carousels_img_url"));
        List<Category> categories = categoryMapper.selectList(new QueryWrapper<Category>()
                                                  .select("id", "name")
                                                  .eq("is_on_page", Boolean.TRUE));

        for (Category category : categories) {
            List<CategoryItems> categoryItems = categoryItemsMapper.selectList(new QueryWrapper<CategoryItems>()
                    .select("id", "name", "img_url")
                    .eq("is_on_page", Boolean.TRUE)
                    .eq("category_id", category.getId()));
            category.setCategoryItems(categoryItems);
        }

        data.setCarousels(products);
        data.setCategories(categories);
        return JsonData.buildSuccess(data);
    }
}
