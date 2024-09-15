package com.wwk.badminton.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wwk.badminton.common.R;
import com.wwk.badminton.common.TimeUtil;
import com.wwk.badminton.pojo.Chat;
import com.wwk.badminton.pojo.ChatImg;
import com.wwk.badminton.service.ChatImgService;
import com.wwk.badminton.service.ChatService;
import com.wwk.badminton.ws.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    ChatImgService  chatImgService;

    @Autowired
    private WebSocketService webSocketService;

    String time = TimeUtil.getTime();

    @PostMapping("/send")
    public R<String> pushToWeb(@RequestBody Chat chat) throws IOException {

        chat.setCreateTime(time);
        chatService.save(chat);
        return R.success("保存消息成功");
    }

    @GetMapping("/close")
    public String close(String userId) {
        return "ok";
    }


    @GetMapping("/getMessages")
    public R getMessage(String fromAccount,String toAccount) {
        List<Chat> chats = chatService.getMessage(fromAccount,toAccount);
        for (Chat chat : chats) {
            if (chat.getIfImg()==1){
                LambdaQueryWrapper<ChatImg> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(ChatImg::getChatId,chat.getId());
                ChatImg chatImg = chatImgService.getOne(queryWrapper);
                chat.setImg(chatImg.getData());
            }
        }
        return R.success(chats);
    }
    @GetMapping("/getMessagePage")
    public R getMessagePage(String fromAccount,String toAccount,int currentLimit,int limit) {
        List<Chat> chats = chatService.getMessagePage(fromAccount, toAccount, limit, currentLimit);
        Collections.reverse(chats);
        for (Chat chat : chats) {
            if (chat.getIfImg()==1){
                LambdaQueryWrapper<ChatImg> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(ChatImg::getChatId,chat.getId());
                ChatImg chatImg = chatImgService.getOne(queryWrapper);
                chat.setImg(chatImg.getData());
            }
        }
        return R.success(chats);
    }

    @PostMapping("/saveImg")
    public R saveImg(@RequestBody ChatImg chatImg){
        chatImg.setCreateTime(time);
        boolean save = chatImgService.save(chatImg);
        return R.success(save);
    }

    @GetMapping("/getImg")
    public R getImg(String fromUser,String toUser){
        String img = chatImgService.getImg(fromUser, toUser);
        return R.success(img);
    }

    @GetMapping("/getChats")
    public R getChats(String formUser) {
        List<Chat> chats = chatService.getChats(formUser);
        for (Chat chat : chats) {
            if (chat.getIfImg()==1){
                LambdaQueryWrapper<ChatImg> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(ChatImg::getChatId,chat.getId());
                ChatImg chatImg = chatImgService.getOne(queryWrapper);
                chat.setImg(chatImg.getData());
            }
        }
        Map<String, List<Chat>> conversationGroups = new HashMap<>();
        for (Chat conversation : chats) {
            String from = conversation.getFrom();
            String to = conversation.getTo();
            String groupId = (from.compareTo(to) < 0) ? from + "-" + to : to + "-" + from;
            if (conversationGroups.containsKey(groupId)) {
                conversationGroups.get(groupId).add(conversation);
            } else {
                List<Chat> group = new ArrayList<>();
                group.add(conversation);
                conversationGroups.put(groupId, group);
            }
        }
        return R.success(conversationGroups);
    }
}
