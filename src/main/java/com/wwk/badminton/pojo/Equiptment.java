package com.wwk.badminton.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("equiptment")
public class Equiptment {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private double price;
    private String  descContent;
    private String title;
    @TableField("img_id")
    private String imgId;
    private String  userAccount;
    private String createTime;
}
