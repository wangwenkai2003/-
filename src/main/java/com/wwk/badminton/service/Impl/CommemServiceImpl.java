package com.wwk.badminton.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwk.badminton.mapper.CommentMapper;
import com.wwk.badminton.pojo.Comment;
import com.wwk.badminton.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommemServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}
