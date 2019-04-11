package cn.javaexception.service.impl;

import cn.javaexception.mapper.CategoryItemsMapper;
import cn.javaexception.mapper.CategoryMapper;
import cn.javaexception.mapper.ProductMapper;
import cn.javaexception.mapper.ProductUnitMapper;
import cn.javaexception.model.Category;
import cn.javaexception.model.CategoryItems;
import cn.javaexception.model.Product;
import cn.javaexception.service.CategoryService;
import cn.javaexception.service.HomeService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.JsonData;

import java.util.*;
import java.util.stream.Collectors;


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
    @Autowired
    CategoryService categoryService;

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
        JSONObject data = new JSONObject();

        List<Product> carousels = productMapper.selectList(new QueryWrapper<Product>()
                .select("id", "carousels_img_url"));

        List<Category> categories = categoryMapper.selectList(new QueryWrapper<Category>()
                .select("id", "name")
                .eq("is_on_page", Boolean.TRUE));

        List<String> list = categories.stream().map(category -> category.getId()).collect(Collectors.toList());

        List<CategoryItems> maps = categoryItemsMapper.selectList(new QueryWrapper<CategoryItems>()
                .select("id", "name", "img_url", "category_id")
                .eq("is_on_page", Boolean.TRUE)
                .in("category_id", list));

        Map<String, List<CategoryItems>> collect = maps.stream().collect(Collectors.groupingBy(CategoryItems::getCategoryId));
        for (Category category : categories) {
            category.setCategoryItems(collect.get(category.getId()));
        }

        data.put("categories",categories);
        data.put("carousels",carousels);
        return JsonData.buildSuccess(data);
    }
}
