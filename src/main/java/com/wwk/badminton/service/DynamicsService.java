package com.wwk.badminton.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwk.badminton.common.R;
import com.wwk.badminton.dto.DynamicsDto;

import java.util.List;


public interface DynamicsService extends IService<DynamicsDto> {
    //获取我的动态
    public List<DynamicsDto> getDynamicsPage(int currentLimit, int limit);
    //获取校区动态
    public List<DynamicsDto> getDynamicsPageByUserAccount(String userAccount,int currentLimit, int limit);
    //获取关注动态
    public List<DynamicsDto> getFollowDynamics(List<String> followList,int currentLimit,int limit);
}
