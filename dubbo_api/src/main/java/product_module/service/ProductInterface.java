package product_module.service;

import product_module.entity.Product;

/**
 * 2 * @Author: HuChao
 * 3 * @Date: 19-3-27 下午10:35
 * 4
 */
public interface ProductInterface {
    Product findProductById(Integer id);
    boolean updateHotIndexById(Integer id,boolean type);
}
