package com.wwk.badminton.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwk.badminton.mapper.ChatImgMapper;
import com.wwk.badminton.pojo.ChatImg;
import com.wwk.badminton.service.ChatImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatImgServiceImpl extends ServiceImpl<ChatImgMapper, ChatImg> implements ChatImgService {
    @Autowired
    private ChatImgMapper chatImgMapper;
    @Override
    public String getImg(String fromUser, String toUser) {
        return chatImgMapper.getImg(fromUser, toUser);
    }
}
