package product_module.service;

import product_module.entity.Product;

/**
 * @author hcuhao
 * @date 2019-03-08-13:55
 */
public interface ProductInterfce {
    Product findProductById(Integer id);
}
