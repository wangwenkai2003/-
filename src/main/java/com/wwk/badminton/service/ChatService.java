package com.wwk.badminton.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwk.badminton.common.R;
import com.wwk.badminton.pojo.Chat;
import com.wwk.badminton.pojo.Message;

import java.util.List;

public interface ChatService extends IService<Chat> {
    public   List<Chat>  getMessage(String fromAccount, String toAccount);
    public List<Chat> getChats(String formUser);
    public   List<Chat>  getMessagePage(String fromAccount, String toAccount,int limit,int currentLimit);
}
