package product_module.service;

import product_module.entity.Product;
import utils.JsonData;

/**
 * 2 * @Author: HuChao
 * 3 * @Date: 19-3-27 下午10:35
 * 4
 */
public interface ProductInterface {
    JsonData findProductById(String id);//查询商品
    boolean updateHotIndexById(String id,Boolean type);//修改商品
   // JsonData updateHotById(Integer id,Integer type);
    JsonData addProduct(Product product);//添加商品
    JsonData delProductById(String id);//删除商品
    //is接口  用于修改部分属性
    JsonData updateIsSaleById(String id,Boolean type);//是否开售
    JsonData updateIsBestById(String id,Boolean type);//是否精品
    JsonData updateIsNewById(String id,Boolean type);//是否新品
    JsonData updateIsRecomById(String id,Boolean type);//是否推荐
}
