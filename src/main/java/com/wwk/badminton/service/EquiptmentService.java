package com.wwk.badminton.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwk.badminton.pojo.Equiptment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EquiptmentService extends IService<Equiptment> {
    public List<Equiptment> get(Integer currentLimit, Integer limit);
}
