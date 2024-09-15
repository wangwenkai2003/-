package com.wwk.badminton.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("message")
public class Message {
    @TableField("from_user")
    private String from;
    @TableField("to_user")
    private String to;
    @TableField("user_content")
    private String content;
}
