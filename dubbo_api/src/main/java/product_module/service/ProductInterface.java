package product_module.service;

import product_module.entity.Product;
import utils.JsonData;

/**
 * 2 * @Author: HuChao
 * 3 * @Date: 19-3-27 下午10:35
 * 4
 */
public interface ProductInterface {
    JsonData findProductById(Integer id);//查询商品
    boolean updateHotIndexById(Integer id,Boolean type);//修改商品
   // JsonData updateHotById(Integer id,Integer type);
    JsonData addProduct(Product product);//添加商品
    JsonData delProductById(Integer id);//删除商品
    //is接口  用于修改部分属性
    JsonData updateIsSaleById(Integer id,Boolean type);//是否开售
    JsonData updateIsBestById(Integer id,Boolean type);//是否精品
    JsonData updateIsNewById(Integer id,Boolean type);//是否新品
    JsonData updateIsRecomById(Integer id,Boolean type);//是否推荐
    JsonData updateProductStatusById(Integer id,Boolean type);//修改审核状态

    //is接口查询 用于查询某些接口
    Boolean findIsSaleById(Integer id);
    Boolean findIsBestById(Integer id);
    Boolean findIsNewById(Integer id);
    Boolean findIsRecomById(Integer id);
    JsonData findProductStatusById(Integer id);

}
