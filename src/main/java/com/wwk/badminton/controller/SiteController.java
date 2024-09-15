package com.wwk.badminton.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wwk.badminton.common.R;
import com.wwk.badminton.common.TimeUtil;
import com.wwk.badminton.dto.ReservationSiteDto;
import com.wwk.badminton.pojo.ReservationSite;
import com.wwk.badminton.pojo.Site;
import com.wwk.badminton.service.ReservationSiteService;
import com.wwk.badminton.service.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("site")
public class SiteController {
    @Autowired
    SiteService siteService;
    @Autowired
    ReservationSiteService reservationSiteService;
    //获取站点信息
    @GetMapping("getSites")
    public R getSitesBySiteType(String siteType){
        LambdaQueryWrapper<Site> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Site::getSiteType,siteType);
        List<Site> list =  siteService.list(queryWrapper);
        return R.success(list);
    }
    //保存站点信息
    @PostMapping("saveSite")
    public R saveSite(@RequestBody Site site){
        if(site.getId()!=null && !site.getId().equals("")){
            LambdaQueryWrapper<Site> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Site::getId,site.getId());
            boolean update = siteService.update(site,queryWrapper);
            return update?R.success("更新成功"):R.error("更新失败");
        }
        boolean save = siteService.save(site);
        if (save){
            return R.success("保存成功");
        }else{
            return R.error("保存失败");
        }

    }
    //删除站点信息
    @DeleteMapping("deleteSite")
    public R deleteSite(String id){
        LambdaQueryWrapper<Site> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Site::getId,id);
        boolean remove = siteService.remove(queryWrapper);
        return remove?R.success("删除成功"):R.error("删除失败");
    }
    //获取站点预约信息
    @GetMapping("getSiteAppoint")
    public R getSiteAppoint(Integer siteType){
        LambdaQueryWrapper<Site> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Site::getSiteType,siteType);
        List<Site> list = siteService.list(queryWrapper);
        List<ReservationSiteDto> reservationSiteDtos = new ArrayList<>();
        for (Site site : list) {
            ReservationSiteDto reservationSiteDto = new ReservationSiteDto();
            BeanUtils.copyProperties(site,reservationSiteDto);
            reservationSiteDto.setSiteId(site.getId());
            if (site.getIfReservation()==0){
                List<ReservationSite> reservationSites = reservationSiteService.getTimeReservetBySiteId(site.getId(),TimeUtil.getNyrTime());
                boolean end = true;
                for (ReservationSite reservationSite : reservationSites) {
                    String endTime = reservationSite.getEndTime();
                    String startTime = reservationSite.getStartTime();
                    boolean using = TimeUtil.isBetweenTime(startTime, endTime);
                    if (using){
                        reservationSiteDto.setIfUse(true);
                        reservationSiteDto.setStartTime(startTime);
                        reservationSiteDto.setEndTime(endTime);
                        reservationSiteDto.setReserveUserAccount(reservationSite.getReserveUserAccount());
                        end=false;
                    }
                }
                if (end){
                    reservationSiteDto.setIfUse(false);
                }
            }else{
                reservationSiteDto.setIfUse(false);
            }
            reservationSiteDtos.add(reservationSiteDto);
        }
        return R.success(reservationSiteDtos);
    }

    public static void main(String[] args) {
        String fullDate = TimeUtil.getFullDate("18:00");
        System.out.println(fullDate);
    }
}
