package com.wwk.badminton.controller;

import com.wwk.badminton.common.R;
import com.wwk.badminton.pojo.Comment;
import com.wwk.badminton.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    //发布评论
    @PostMapping("/publish")
    public R publish(@RequestBody Comment comment){
        boolean save = commentService.save(comment);
        return save ?R.success("评论发布成功"):R.error("评论发布失败");
    }
}
