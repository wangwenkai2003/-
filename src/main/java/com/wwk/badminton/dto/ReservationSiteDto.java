package com.wwk.badminton.dto;

import com.wwk.badminton.pojo.ReservationSite;
import com.wwk.badminton.pojo.Site;
import lombok.Data;

@Data
public class ReservationSiteDto extends ReservationSite{
    private Boolean ifUse;
    private Integer id;
    private String siteName;
    private String siteAddress;
    private Integer siteType;
    private Integer isReserve;
    private Integer ifReservation;
    private String status;
}
