package com.wwk.badminton.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwk.badminton.mapper.UserMapper;
import com.wwk.badminton.pojo.User;
import com.wwk.badminton.service.UsrServcie;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UsrServcie {

}
