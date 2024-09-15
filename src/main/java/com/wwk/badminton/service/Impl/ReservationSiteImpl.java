package com.wwk.badminton.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwk.badminton.dto.ReservationSiteDto;
import com.wwk.badminton.mapper.ReservationSiteMapper;
import com.wwk.badminton.pojo.ReserTime;
import com.wwk.badminton.pojo.ReservationSite;
import com.wwk.badminton.service.ReservationSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationSiteImpl extends ServiceImpl<ReservationSiteMapper, ReservationSite> implements ReservationSiteService {
    @Autowired
    private ReservationSiteMapper reservationSiteMapper;

    @Override
    public List<ReservationSite> getReservationBySiteType(String siteType) {
        return reservationSiteMapper.getReservationBySiteType(siteType);
    }

    @Override
    public List<ReservationSiteDto> getReserveInfoBySiteType(String siteType) {
        return reservationSiteMapper.getReserveInfoBySiteType(siteType);
    }

    @Override
    public List<ReservationSite> getTimeReservetBySiteId(Integer siteId, String date) {
        return reservationSiteMapper.getTimeReservetBySiteId(siteId, date);
    }
    @Override
    public List<ReserTime> getReserTime(String siteType, String siteId){
        return reservationSiteMapper.getReserveTime( siteType,  siteId);
    }

    @Override
    public List<ReservationSiteDto> getByAccount(String userAccount) {
        return reservationSiteMapper.getByAccount(userAccount);
    }
}
