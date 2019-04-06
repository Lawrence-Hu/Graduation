package cn.javaexception.controller;


import cn.javaexception.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.javaexception.entity.Favourite;
import utils.JsonData;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@RestController
@RequestMapping("/api/favourite")
public class FavouriteController {
    @Autowired
    FavouriteService favouriteService;

    @PostMapping
    public JsonData addToFavorite(@RequestParam("id") Integer id) {
        if (id == null) {
            return JsonData.buildError("产品id不能为空！");
        }
        return favouriteService.addTofavourite(new Favourite().setProductId(id));
    }

    @DeleteMapping
    public JsonData addToFavorite(@RequestBody Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return JsonData.buildError("产品id不能为空！");
        }
        return favouriteService.deleteFavouriteItems(ids);
}
}

