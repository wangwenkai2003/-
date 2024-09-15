package com.wwk.badminton.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("follow")
public class Follow {
    private String followUserAccount;
    private String befollowUserAccount;
}
