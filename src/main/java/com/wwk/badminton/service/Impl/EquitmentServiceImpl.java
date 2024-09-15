package com.wwk.badminton.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwk.badminton.mapper.EquiptmentMapper;
import com.wwk.badminton.pojo.Equiptment;
import com.wwk.badminton.service.EquiptmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquitmentServiceImpl extends ServiceImpl<EquiptmentMapper, Equiptment> implements EquiptmentService {
    @Autowired
    private EquiptmentMapper equiptmentMapper;
    @Override
    public List<Equiptment> get(Integer currentLimit, Integer limit) {
        return equiptmentMapper.get(currentLimit,limit);
    }
}
