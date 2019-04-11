package cn.javaexception.mapper;

import entity.Msg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huchao
 * @since 2019-03-05
 */
@Mapper
public interface MsgMapper extends BaseMapper<Msg> {

}
