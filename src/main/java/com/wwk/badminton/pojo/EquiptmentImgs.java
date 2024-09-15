package com.wwk.badminton.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("equiptment_imgs")
public class EquiptmentImgs {
    private String id;
    private String data;
}
