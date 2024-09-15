package com.wwk.badminton.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
@TableName("site")
public class Site {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String siteName;
    private String siteAddress;
    private Integer siteType;
    private Integer isReserve;
    private Integer ifReservation;
}
