package com.wwk.badminton.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwk.badminton.dto.ImgDto;
import com.wwk.badminton.mapper.ImgMapper;
import com.wwk.badminton.service.ImgService;
import org.springframework.stereotype.Service;

@Service
public class ImgServiceImpm extends ServiceImpl<ImgMapper,ImgDto> implements ImgService {
}
