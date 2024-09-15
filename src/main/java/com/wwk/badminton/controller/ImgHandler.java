package com.wwk.badminton.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wwk.badminton.common.R;
import com.wwk.badminton.common.TimeUtil;
import com.wwk.badminton.dto.ImgDto;
import com.wwk.badminton.service.ImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/img")
public class ImgHandler {
    @Autowired
    private ImgService imgService;


    /**
     * 文件上传
     *
     * @return
     */
    @PostMapping("/upload")
    public String upload( @RequestBody ImgDto imgDto){

        String fileName = UUID.randomUUID().toString() + imgDto.getFileName();

        imgDto.setFileName(fileName);

        String time = TimeUtil.getTime();

        imgDto.setCreateTime(time);
        boolean save = imgService.save(imgDto);

        return fileName;
    }



    @GetMapping("/download")
    public R download(String dynamicsId) {
        LambdaQueryWrapper<ImgDto> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ImgDto::getDynamicsId, dynamicsId);
        queryWrapper.orderByDesc(ImgDto::getCreateTime);
        List<ImgDto> list = imgService.list(queryWrapper);
        return list.size()>0 ? R.success(list) : R.error("获取失败");
    }
}
