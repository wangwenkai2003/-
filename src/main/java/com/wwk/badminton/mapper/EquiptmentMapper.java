package com.wwk.badminton.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwk.badminton.pojo.Equiptment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EquiptmentMapper extends BaseMapper<Equiptment> {
    @Select("select * from equiptment order by id desc limit #{currentLimit},#{limit}")
    public List<Equiptment> get(Integer currentLimit, Integer limit);
}
