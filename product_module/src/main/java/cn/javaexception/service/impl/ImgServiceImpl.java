package cn.javaexception.service.impl;

import cn.javaexception.entity.Img;
import cn.javaexception.mapper.ImgMapper;
import cn.javaexception.service.ImgService;
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
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements ImgService {

    @Autowired
    private  ImgMapper imgMapper;
    @Override
    public boolean addImg(Img img) {
        int i= imgMapper.insert(img);
        return i>0;
    }

    @Override
    public boolean deleImg(int id) {

        int i= imgMapper.deleteById(id);
        return i>0;
    }
}
