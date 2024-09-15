package com.wwk.badminton.controller;

import com.wwk.badminton.common.R;
import com.wwk.badminton.common.TimeUtil;
import com.wwk.badminton.pojo.NoticeImg;
import com.wwk.badminton.service.NoticeImgServeice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("notice")
public class NoticeImgController {
    @Autowired
    private NoticeImgServeice noticeImgServeice;
    @PostMapping("save")
    public R saveNoticeImg(@RequestBody List<NoticeImg> noticeImgList) {
        for (NoticeImg noticeImg : noticeImgList) {
            noticeImg.setCreateTime(TimeUtil.getTime());
        }
        boolean b = noticeImgServeice.saveBatch(noticeImgList);
        return b?R.success("发布成功"):R.error("发布失败");
    }
    @GetMapping("getNoticeLimit")
    public R getNoticeLimit(){
        List<NoticeImg> noticeLimit = noticeImgServeice.getNoticeLimit();
        return R.success(noticeLimit);
    }
    @PutMapping("updateNotice")
    public R updateNotice(@RequestBody NoticeImg noticeImg){
        boolean b = noticeImgServeice.updateById(noticeImg);
        return b?R.success("修改成功"):R.error("修改失败");
    }
}
