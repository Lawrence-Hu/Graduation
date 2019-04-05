package cn.javaexception.mapper;

import cn.javaexception.model.Product;
import cn.javaexception.vo.CategoriesAndCarouselsVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huchao
 * @since 2019-04-04
 */
public interface ProductMapper extends BaseMapper<Product> {
}
