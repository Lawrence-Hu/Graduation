package cn.javaexception.service.impl;

import cn.javaexception.entity.OperateCategory;
import cn.javaexception.mapper.OperateCategoryMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huchao
 * @since 2019-05-02
 */
@Service
public class OperateCategoryServiceImpl extends ServiceImpl<OperateCategoryMapper, OperateCategory> implements IService<OperateCategory> {

}
