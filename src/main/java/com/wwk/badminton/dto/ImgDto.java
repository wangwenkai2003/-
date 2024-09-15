package com.wwk.badminton.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

//图片文件的实体类
@Data
@TableName("imgs")
public class ImgDto {
    private String data;

    private String fileName;

    private String userAccount;

    private String dynamicsId;

    private String  createTime;
}
