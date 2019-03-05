package cn.javaexception.mapper;

import cn.javaexception.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
