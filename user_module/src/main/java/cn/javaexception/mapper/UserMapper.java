package cn.javaexception.mapper;

import cn.javaexception.entity.User;
import cn.javaexception.util.JsonData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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
