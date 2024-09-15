package com.wwk.badminton.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwk.badminton.common.R;
import com.wwk.badminton.dto.ReservationSiteDto;
import com.wwk.badminton.pojo.ReserTime;
import com.wwk.badminton.pojo.ReservationSite;

import java.util.List;

public interface ReservationSiteService extends IService<ReservationSite> {
    public List<ReservationSite> getReservationBySiteType(String siteType);
    public List<ReservationSiteDto> getReserveInfoBySiteType(String siteType);
    public List<ReservationSite> getTimeReservetBySiteId(Integer siteId,String date);
    public List<ReserTime> getReserTime(String siteType, String siteId);
    public List<ReservationSiteDto> getByAccount(String userAccount);
}
