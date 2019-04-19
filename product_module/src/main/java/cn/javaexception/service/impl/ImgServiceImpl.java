package cn.javaexception.service.impl;

import cn.javaexception.mapper.ImgMapper;
import cn.javaexception.model.Img;
import cn.javaexception.service.ImgService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import user_module.service.UserInterface;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huchao
 * @since 2019-03-27
 */
@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements ImgService {
    @Reference
    UserInterface userInterface;
    @Override
    public void test() {
        System.out.println("service:"+userInterface);
        userInterface.findUserById("1");
    }

}
