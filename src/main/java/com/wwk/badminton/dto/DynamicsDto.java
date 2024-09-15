package com.wwk.badminton.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@TableName("dynamics")
public class DynamicsDto {
    private String id;

    private String userAccount;

    private String descContent;

    private String createTime;


}
