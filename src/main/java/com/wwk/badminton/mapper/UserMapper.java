package com.wwk.badminton.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwk.badminton.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
