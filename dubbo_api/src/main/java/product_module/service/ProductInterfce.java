package product_module.service;

import product_module.entity.Brand;
import product_module.entity.Catagory;
import product_module.entity.Img;
import product_module.entity.Product;

/**
 * @author hcuhao
 * @date 2019-03-08-13:55
 */
public interface ProductInterfce {

    public boolean addBrand(Brand brand);//添加商品牌子
    public boolean delBrand(Integer id);//删除某商品牌子

    public  boolean  addCatagoy(Catagory catagory);//添加商品类型
    public  boolean  delCatagoy(Integer id);//删除商品类型

    public boolean addImg(Img img);//添加图片
    public boolean deleImg(Integer id);//删除图片

    public boolean addproduct(Product product);//添加商品
    public boolean delproduct(Integer id);//删除商品byid
    public boolean updateproduct(Product product);//修改商品byid;
    Product findProductById(Integer id);//查找商品byid


}
