package com.wwk.badminton.controller;

import com.wwk.badminton.common.R;
import com.wwk.badminton.pojo.DyLike;
import com.wwk.badminton.service.DylikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/dyLike")
public class DyLikeController {
    @Autowired
    private DylikeService dyLikeService;
    @PostMapping("/save")
    public R save(@RequestBody DyLike dyLike){
        boolean save = dyLikeService.save(dyLike);
        return save ? R.success("点赞成功"):R.error("点赞失败");
    }
}
