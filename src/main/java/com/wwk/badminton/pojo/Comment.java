package com.wwk.badminton.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("comment")
public class Comment {
    private String commentContent;
    private String commentUserAccount;
    private String commentUserName;
    private String dynamicsId;
}
