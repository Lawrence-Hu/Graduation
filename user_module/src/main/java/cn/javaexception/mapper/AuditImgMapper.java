package cn.javaexception.mapper;

import cn.javaexception.entity.AuditImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huchao
 * @since 2019-05-13
 */
@Mapper
public interface AuditImgMapper extends BaseMapper<AuditImg> {
    @Override
    AuditImg selectById(Serializable id);
}
