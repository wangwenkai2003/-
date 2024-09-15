package com.wwk.badminton.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwk.badminton.dto.DynamicsDto;
import com.wwk.badminton.mapper.DynamicsMapper;
import com.wwk.badminton.service.DynamicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicsServiceImpl extends ServiceImpl<DynamicsMapper, DynamicsDto> implements DynamicsService {
    @Autowired
    private DynamicsMapper dynamicsMapper;
    @Override
    public List<DynamicsDto> getDynamicsPage(int currentLimit, int limit) {
        List<DynamicsDto> dynamicsPage = dynamicsMapper.getDynamicsPage(currentLimit, limit);
        return dynamicsPage;
    }

    @Override
    public List<DynamicsDto> getDynamicsPageByUserAccount(String userAccount, int currentLimit, int limit) {
        List<DynamicsDto> dynamicsPageByUserAccount = dynamicsMapper.getDynamicsPageByUserAccount(userAccount, currentLimit, limit);
        return dynamicsPageByUserAccount;
    }

    @Override
    public List<DynamicsDto> getFollowDynamics(List<String> followList, int currentLimit, int limit) {
       return dynamicsMapper.getFollowDynamics(followList, currentLimit, limit);
    }


}
