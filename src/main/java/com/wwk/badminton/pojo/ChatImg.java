package com.wwk.badminton.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("chat_img")
public class ChatImg {
    private String chatId;
    @TableField("create_time")
    private String createTime;
    @TableField("data")
    private String data;
}
