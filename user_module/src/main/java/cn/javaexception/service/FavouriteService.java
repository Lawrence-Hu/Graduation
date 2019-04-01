package cn.javaexception.service;

import cn.javaexception.entity.Favourite;

import com.baomidou.mybatisplus.extension.service.IService;
import utils.JsonData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
public interface FavouriteService extends IService<Favourite> {
    JsonData addTofavourite(Favourite favourite);

    JsonData deleteFavouriteItems(Integer[] ids);
}
