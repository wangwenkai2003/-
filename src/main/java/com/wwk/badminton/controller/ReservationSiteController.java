package com.wwk.badminton.controller;

import com.wwk.badminton.common.R;
import com.wwk.badminton.common.TimeUtil;
import com.wwk.badminton.dto.ReservationSiteDto;
import com.wwk.badminton.pojo.ReserTime;
import com.wwk.badminton.pojo.ReservationSite;
import com.wwk.badminton.service.ReservationSiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reservationSite")
public class ReservationSiteController {
    @Autowired
    private ReservationSiteService reservationSiteService;
    //获取预约根据场地
    @GetMapping("getBySiteType")
    public R getReservationBySiteType(String siteType){
        List<ReservationSite> list = reservationSiteService.getReservationBySiteType(siteType);
        String time = TimeUtil.getTime();
        List<ReservationSiteDto> newList =new ArrayList<>();
        for (ReservationSite reservationSite : list) {
            ReservationSiteDto reservationSiteDto = new ReservationSiteDto();
            BeanUtils.copyProperties(reservationSite,reservationSiteDto);
            boolean b = TimeUtil.compareTime(time, reservationSite.getEndTime());
            if (b){
                reservationSiteDto.setIfUse(true);
                System.out.println("在用");
            }else {
                reservationSiteDto.setIfUse(false);
                System.out.println("空闲");
            }
            newList.add(reservationSiteDto);
        }
        return R.success(newList);
    }
    //获取预约信息根据场地
    @GetMapping("getReserveInfoBySiteType")
    public R getReserveInfoBySiteType(String siteType){
        List<ReservationSiteDto> list = reservationSiteService.getReserveInfoBySiteType(siteType);
        String time = TimeUtil.getTime();
        List<ReservationSiteDto> newList =new ArrayList<>();
        for (ReservationSiteDto reservationSite : list) {
            boolean b = TimeUtil.compareTime(time, reservationSite.getEndTime());
            if (b){
                reservationSite.setIfUse(true);
                System.out.println("在用");
            }else {
                reservationSite.setIfUse(false);
                System.out.println("空闲");
            }
            newList.add(reservationSite);
        }
        return R.success(newList);
    }
    //获取可预约的时间
    @GetMapping("getReserTime")
    public List<String> getReserTime(String siteType, String siteId){
        List<String> availableTime = TimeUtil.getAvailableTime();
        List<ReserTime> reserTime = reservationSiteService.getReserTime(siteType,siteId);
        List<String> filteredList = new ArrayList<>();
        for (String item : availableTime) {
            boolean aiailabe = true;
            String[] split = item.split("-");
            String startTime = split[0];
            String endTime = split[1];
            for (ReserTime reserTime1 : reserTime) {
                if (!(TimeUtil.compareHourAndMinutes(startTime,reserTime1.getStartTime())==-1) && TimeUtil.compareHourAndMinutes(startTime,reserTime1.getEndTime())==-1){
                    aiailabe = false;
                }
                if (TimeUtil.compareHourAndMinutes(endTime,reserTime1.getStartTime())==1 && (TimeUtil.compareHourAndMinutes(endTime,reserTime1.getEndTime())==-1||(TimeUtil.compareHourAndMinutes(endTime,reserTime1.getEndTime()))==0)){
                    aiailabe = false;
                }

            }
            if (aiailabe){
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    //保存预约信息
    @PostMapping("saveReservation")
    public R saveReservation(@RequestBody ReservationSite reservationSite){
        reservationSite.setStartTime(TimeUtil.getFullDate(reservationSite.getStartTime().trim()));
        reservationSite.setEndTime(TimeUtil.getFullDate(reservationSite.getEndTime().trim()));
        boolean save = reservationSiteService.save(reservationSite);
        return save?R.success("预约成功"):R.error("预约失败");
    }
    //根据用户账号获取预约信息
    @GetMapping("getByAccount")
    public  R getByAccount(String userAccount){
        List<ReservationSiteDto> reservationSiteDtos = reservationSiteService.getByAccount(userAccount);
        List<ReservationSiteDto> reservationSiteDtos1 = new ArrayList<>();
        for (ReservationSiteDto reservationSiteDto : reservationSiteDtos) {
            if (TimeUtil.compareTime(reservationSiteDto.getEndTime(), TimeUtil.getTime())){
                reservationSiteDto.setStatus("已过期");
            }
            if (TimeUtil.compareTime(reservationSiteDto.getStartTime(),TimeUtil.getTime()) && !TimeUtil.compareTime(reservationSiteDto.getEndTime(),TimeUtil.getTime())){
                reservationSiteDto.setStatus("使用中");
            }
            if (!TimeUtil.compareTime(reservationSiteDto.getStartTime(),TimeUtil.getTime())){
                reservationSiteDto.setStatus("未使用");
            }
            reservationSiteDtos1.add(reservationSiteDto);
        }
        return R.success(reservationSiteDtos1);
    }
}
