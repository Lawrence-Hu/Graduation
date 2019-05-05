package cn.javaexception.controller;


import cn.javaexception.entity.Favourite;
import cn.javaexception.service.FavouriteService;
import cn.javaexception.util.JsonData;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequiresRoles({"user"})
public class FavouriteController {
    @Autowired
    FavouriteService favouriteService;

    @PostMapping("/add")
    public JsonData addToFavorite(@RequestParam("id") String id) {
        if (id == null) {
            return JsonData.buildError("产品id不能为空！");
        }
        return favouriteService.addTofavourite(new Favourite().setProductId(id));
    }

    @PostMapping("/delete")
    public JsonData addToFavorite(@RequestBody String[] ids) {
        if (ids == null || ids.length == 0) {
            return JsonData.buildError("产品id不能为空！");
        }
        return favouriteService.deleteFavouriteItems(ids);
}
}

