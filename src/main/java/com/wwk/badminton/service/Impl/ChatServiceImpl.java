package com.wwk.badminton.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwk.badminton.common.R;
import com.wwk.badminton.mapper.ChatMapper;
import com.wwk.badminton.pojo.Chat;
import com.wwk.badminton.pojo.Message;
import com.wwk.badminton.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {
    @Autowired
    private ChatMapper chatMapper;
    @Override
    public List<Chat> getMessage(String fromAccount, String toAccount) {
        List<Chat> chats = chatMapper.getMessage(fromAccount, toAccount);
        return chats;
    }

    @Override
    public List<Chat> getChats(String formUser) {
        List<Chat> chats = chatMapper.getChats(formUser);
        return chats;
    }

    @Override
    public List<Chat> getMessagePage(String fromAccount, String toAccount, int limit, int currentLimit) {
        return chatMapper.getMessagePage(fromAccount,toAccount,limit,currentLimit);
    }
}
