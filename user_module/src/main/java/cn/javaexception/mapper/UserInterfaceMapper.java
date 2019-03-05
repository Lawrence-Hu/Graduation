package cn.javaexception.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import user_module.entity.User;

/**
 * @author hcuhao
 * @date 2019-03-05-13:48
 */
@Mapper
public interface UserInterfaceMapper extends BaseMapper<User> {

}
