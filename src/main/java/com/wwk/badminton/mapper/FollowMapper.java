package com.wwk.badminton.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwk.badminton.pojo.Follow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowMapper extends BaseMapper<Follow> {
    @Delete("delete from follow where befollow_user_account=#{befollowUserAccount} and follow_user_account=#{followUserAccount}")
    public void deleteFollow(String befollowUserAccount, String followUserAccount);
}
