package cn.javaexception.service.impl;

import cn.javaexception.entity.Brand;
import cn.javaexception.mapper.BrandMapper;
import cn.javaexception.service.BrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huchao
 * @since 2019-03-05
 */
@Service

public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

}
