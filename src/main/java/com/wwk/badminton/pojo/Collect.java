package com.wwk.badminton.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("collect")
public class Collect {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String userAccount;
    private Integer equiptmentId;
}
