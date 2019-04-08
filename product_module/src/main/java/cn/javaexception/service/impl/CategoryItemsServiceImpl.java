package cn.javaexception.service.impl;

import cn.javaexception.model.CategoryItems;
import cn.javaexception.mapper.CategoryItemsMapper;
import cn.javaexception.service.CategoryItemsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huchao
 * @since 2019-04-04
 */
@Service
public class CategoryItemsServiceImpl extends ServiceImpl<CategoryItemsMapper, CategoryItems> implements CategoryItemsService {

}
