package cn.javaexception.mapper;

import cn.javaexception.entity.UserStatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huchao
 * @since 2019-05-01
 */
@Mapper
public interface UserStatusMapper extends BaseMapper<UserStatus> {
    @Override
    UserStatus selectById(Serializable id);
}
