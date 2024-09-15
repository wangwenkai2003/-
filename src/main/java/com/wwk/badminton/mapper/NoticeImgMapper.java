package com.wwk.badminton.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwk.badminton.pojo.NoticeImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeImgMapper extends BaseMapper<NoticeImg> {
    @Select("select * from notice_img order by id desc limit 5;")
    public List<NoticeImg> getNoticeLimit();
}
