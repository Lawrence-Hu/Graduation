package cn.javaexception.service.impl;

import cn.javaexception.entity.City;
import cn.javaexception.mapper.CityMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements IService<City> {

}
