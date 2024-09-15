package com.wwk.badminton.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwk.badminton.pojo.Follow;

public interface FollowService extends IService<Follow> {
    public void deleteFollow(Follow follow);
}
