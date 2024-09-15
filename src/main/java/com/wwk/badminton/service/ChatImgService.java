package com.wwk.badminton.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwk.badminton.pojo.ChatImg;

public interface ChatImgService extends IService<ChatImg> {
    public String getImg(String fromUser,String toUser);
}
