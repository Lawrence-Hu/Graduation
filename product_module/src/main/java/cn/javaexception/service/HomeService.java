package cn.javaexception.service;

import utils.JsonData;

public interface HomeService {
    JsonData getHome();

    JsonData getCategories();

    JsonData getCarousel();

    JsonData getNewProductForDay();

    JsonData getHotProduct();

    JsonData getRecommendProduct();

    JsonData getCategoriesAndCarousels();
}
