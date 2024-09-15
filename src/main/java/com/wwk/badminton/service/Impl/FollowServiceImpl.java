package com.wwk.badminton.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwk.badminton.mapper.FollowMapper;
import com.wwk.badminton.pojo.Follow;
import com.wwk.badminton.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {
    @Autowired
    private  FollowMapper followMapper;

    public void deleteFollow(Follow follow) {
        String befollowUserAccount = follow.getBefollowUserAccount();
        String followUserAccount = follow.getFollowUserAccount();
        followMapper.deleteFollow(befollowUserAccount,followUserAccount);
    }
}
