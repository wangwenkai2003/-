package com.wwk.badminton.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwk.badminton.dto.DynamicsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DynamicsMapper extends BaseMapper<DynamicsDto> {
    @Select("select * from dynamics order by create_time desc limit #{currentLimit},#{limit}")
    public List<DynamicsDto> getDynamicsPage(int currentLimit, int limit);

    @Select("select * from dynamics where user_account = #{userAccount} order by create_time desc limit #{currentLimit},#{limit}")
    public List<DynamicsDto> getDynamicsPageByUserAccount(String userAccount,int currentLimit, int limit);

    @Select({
            "<script>",
            "SELECT * FROM dynamics",
            "WHERE user_account IN",
            "<foreach item='item' collection='followList' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "ORDER BY create_time DESC",
            "LIMIT #{currentLimit}, #{limit}",
            "</script>"
    })
    public List<DynamicsDto> getFollowDynamics(@Param("followList") List<String> followList, @Param("currentLimit") int currentLimit, @Param("limit") int limit);
}
