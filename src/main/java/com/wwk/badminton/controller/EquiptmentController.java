package com.wwk.badminton.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wwk.badminton.common.R;
import com.wwk.badminton.common.TimeUtil;
import com.wwk.badminton.dto.EquiptmentDto;
import com.wwk.badminton.pojo.Equiptment;
import com.wwk.badminton.pojo.EquiptmentImgs;
import com.wwk.badminton.pojo.User;
import com.wwk.badminton.service.EquiptmentImgsService;
import com.wwk.badminton.service.EquiptmentService;
import com.wwk.badminton.service.Impl.UserServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/equiptment")
public class EquiptmentController {
    @Autowired
    private EquiptmentService equiptmentService;
    @Autowired
    private EquiptmentImgsService equiptmentImgsService;
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("save")
    public R save(@RequestBody EquiptmentDto equiptmentDto){
        Equiptment equiptment = new Equiptment();
        BeanUtils.copyProperties(equiptmentDto,equiptment);
        equiptment.setCreateTime(TimeUtil.getTime());
        String img_id = equiptmentDto.getImgId();
        List<String> imgs = equiptmentDto.getImgs();
        for (String img : imgs) {
            EquiptmentImgs equiptmentImgs = new EquiptmentImgs();
            equiptmentImgs.setData(img);
            equiptmentImgs.setId(img_id);
            equiptmentImgsService.save(equiptmentImgs);
        }
        //保存装备基本信息
        boolean save = equiptmentService.save(equiptment);
        return save?R.success("发布成功"):R.error("发布失败");
    }
    @GetMapping("get")
    public R get(Integer currentLimit,Integer limit){
        List<Equiptment> list = equiptmentService.get(currentLimit,limit);
        List<EquiptmentDto> equiptmentDtoList = new ArrayList<>();
        for (Equiptment equiptment : list) {
            EquiptmentDto equiptmentDto = new EquiptmentDto();
            BeanUtils.copyProperties(equiptment,equiptmentDto);
            String img_id = equiptment.getImgId();
            String userAccount = equiptment.getUserAccount();
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(User::getAccount,userAccount);
            User user = userService.getOne(userLambdaQueryWrapper);
            equiptmentDto.setUser(user);
            LambdaQueryWrapper<EquiptmentImgs> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(EquiptmentImgs::getId,img_id);
            List<EquiptmentImgs> equiptmentImgs = equiptmentImgsService.list(queryWrapper);
            List<String> imgs = new ArrayList<>();
            for (EquiptmentImgs equiptmentImg : equiptmentImgs) {
                imgs.add(equiptmentImg.getData());
            }
            equiptmentDto.setImgs(imgs);
            equiptmentDtoList.add(equiptmentDto);
        }
        return R.success(equiptmentDtoList);
    }
}
