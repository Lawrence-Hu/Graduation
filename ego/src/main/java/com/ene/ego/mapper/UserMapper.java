package com.ene.ego.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ene.ego.beens.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
