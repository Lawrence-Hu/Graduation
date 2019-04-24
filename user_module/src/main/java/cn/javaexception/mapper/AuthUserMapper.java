package cn.javaexception.mapper;

import cn.javaexception.entity.AuthUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huchao
 * @since 2019-04-24
 */
@Mapper
public interface AuthUserMapper extends BaseMapper<AuthUser> {

}
