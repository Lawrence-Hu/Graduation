package cn.javaexception.service.impl;

import cn.javaexception.mapper.ProductMapper;
import cn.javaexception.model.Product;
import cn.javaexception.service.ProductService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import user_module.entity.User;
import user_module.service.UserInterface;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huchao
 * @since 2019-03-05
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Reference
    UserInterface userInterface;
    @Override
    public boolean addProduct(Integer id) {
        User user = userInterface.findUserById(id);
        System.out.println(user);
        return true;
    }
}
