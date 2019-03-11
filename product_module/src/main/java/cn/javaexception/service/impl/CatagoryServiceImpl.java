package cn.javaexception.service.impl;

import cn.javaexception.entity.Catagory;
import cn.javaexception.mapper.CatagoryMapper;
import cn.javaexception.service.CatagoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CatagoryServiceImpl extends ServiceImpl<CatagoryMapper, Catagory> implements CatagoryService {

    @Autowired
    private CatagoryMapper catagoryMapper;
    @Override
    public boolean addCatagoy(Catagory catagory) {

        int i= catagoryMapper.insert(catagory);
        return i>0;
    }

    @Override
    public boolean deleCatagoy(int id) {

        int i = catagoryMapper.deleteById(id);

        return i>0;
    }
}
