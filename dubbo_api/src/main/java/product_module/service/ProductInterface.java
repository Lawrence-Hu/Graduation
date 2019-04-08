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
}
