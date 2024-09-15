package com.wwk.badminton.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("notice_img")
@Data
public class NoticeImg {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String data;
    private String fileName;
    private String createTime;
}
