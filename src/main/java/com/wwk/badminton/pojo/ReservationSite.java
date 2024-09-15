package com.wwk.badminton.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("reservation_site")
public class ReservationSite{
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String startTime;
    private  String endTime;
    private Integer siteId;
    private String reserveUserAccount;
}
