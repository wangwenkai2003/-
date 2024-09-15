package com.wwk.badminton.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dy_like")
public class DyLike {
    private long id;
    private String userAccount;
    private String dynamicsId;
}
