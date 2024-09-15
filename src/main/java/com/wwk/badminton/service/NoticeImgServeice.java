package com.wwk.badminton.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwk.badminton.pojo.NoticeImg;

import java.util.List;

public interface NoticeImgServeice extends IService<NoticeImg> {
    public List<NoticeImg> getNoticeLimit();
}
