package com.wwk.badminton.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwk.badminton.mapper.NoticeImgMapper;
import com.wwk.badminton.pojo.NoticeImg;
import com.wwk.badminton.service.NoticeImgServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NoticeImgServiceImpl extends ServiceImpl<NoticeImgMapper, NoticeImg> implements NoticeImgServeice {
    @Autowired
    private  NoticeImgMapper noticeImgMapper;
    @Override
    public List<NoticeImg> getNoticeLimit() {
        return noticeImgMapper.getNoticeLimit();
    }
}
