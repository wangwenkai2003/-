package com.wwk.badminton.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwk.badminton.common.R;
import com.wwk.badminton.dto.ReservationSiteDto;
import com.wwk.badminton.pojo.ReserTime;
import com.wwk.badminton.pojo.ReservationSite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReservationSiteMapper extends BaseMapper<ReservationSite> {
    @Select("select reserve_user_account,reservation_site.id,site_name,if_reservation,start_time,end_time,site_type,reserve_id,site_id,site_address,is_reserve from site,reservation_site where site.id=reservation_site.site_id and" +
            " site_type=#{siteType}")
    public List<ReservationSite> getReservationBySiteType(String siteType);

    @Select("select reserve_user_account,reservation_site.id,site_name,if_reservation,start_time,end_time,site_type,reserve_id,site_id,site_address,is_reserve from site,reservation_site where site.id=reservation_site.site_id and" +
            " site_type=#{siteType}")
    public List<ReservationSiteDto> getReserveInfoBySiteType(String siteType);

    @Select("select * from reservation_site where DATE_FORMAT(start_time,'%Y-%m-%d') = #{date} and site_id=#{siteId}")
    public List<ReservationSite> getTimeReservetBySiteId(Integer siteId,String date);

    @Select("SELECT DATE_FORMAT(start_time, '%H:%i') AS start_time, DATE_FORMAT(end_time, '%H:%i') AS end_time FROM reservation_site,site WHERE DATE(start_time) = CURDATE() and reservation_site.site_id=site.id and site.site_type = #{siteType} and site_id=#{siteId};")
    public List<ReserTime> getReserveTime(String siteType,String siteId);

    @Select("select reserve_user_account,reservation_site.id,site_name,if_reservation,start_time,end_time,site_type,reserve_id,site_id,site_address,is_reserve from reservation_site,site where site_id = site.id and reserve_user_account = 202104279 order by reservation_site.id desc")
    public List<ReservationSiteDto> getByAccount(String userAccount);

}
