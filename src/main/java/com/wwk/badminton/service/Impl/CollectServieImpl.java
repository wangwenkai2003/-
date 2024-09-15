package com.wwk.badminton.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwk.badminton.mapper.CollectMapper;
import com.wwk.badminton.pojo.Collect;
import com.wwk.badminton.service.CollectService;
import org.springframework.stereotype.Service;

@Service
public class CollectServieImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
}
