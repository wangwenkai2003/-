package com.wwk.badminton.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("chat")
public class Chat {
    @TableField("id")
    private String id;
    @TableField("from_user")
    private String from;
    @TableField("to_user")
    private String to;
    @TableField("user_content")
    private String content;
    @TableField("create_time")
    private String createTime;
    @TableField("if_receive")
    private int ifReceive;
    @TableField("if_img")
    private int ifImg;
    @TableField("img")
    private String img;
}

