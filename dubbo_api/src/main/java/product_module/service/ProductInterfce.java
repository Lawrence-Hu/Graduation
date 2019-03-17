package product_module.service;

import product_module.entity.*;

/**
 * @author hcuhao
 * @date 2019-03-08-13:55
 */
public interface ProductInterfce {
    Product findProductById(Integer id);//查找商品
    Boolean addProduct(TProduct product);//添加商品
    boolean delProduct(Integer productId);//删除商品
    boolean updateHotIndexById(Integer id,Boolean type);//增加商品热度 true 为增加 false 为减少

}
