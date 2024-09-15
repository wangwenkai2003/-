package com.wwk.badminton.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wwk.badminton.common.R;
import com.wwk.badminton.dto.CollectDto;
import com.wwk.badminton.dto.EquiptmentDto;
import com.wwk.badminton.pojo.Collect;
import com.wwk.badminton.pojo.Equiptment;
import com.wwk.badminton.pojo.EquiptmentImgs;
import com.wwk.badminton.pojo.User;
import com.wwk.badminton.service.CollectService;
import com.wwk.badminton.service.EquiptmentImgsService;
import com.wwk.badminton.service.EquiptmentService;
import com.wwk.badminton.service.Impl.UserServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("collect")
public class CollectController {
    @Autowired
    CollectService collectService;
    @Autowired
    EquiptmentService equiptmentService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    EquiptmentImgsService equiptmentImgsService;

    //保存收藏信息
    @PostMapping("/save")
    public R  save(@RequestBody Collect collect){
        boolean save = collectService.save(collect);
        return save?R.success("收藏成功"):R.error("收藏失败");
    }
    //获取收藏信息
    @GetMapping("/getByAccount")
    public R getByAccount(String userAccount){
        LambdaQueryWrapper<Collect> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Collect::getUserAccount,userAccount);
        List<Collect> list = collectService.list(queryWrapper);
        return R.success(list);
    }
    //删除收藏信息
    @PostMapping("/delete")
    public R cancleCollect(@RequestBody Collect collect){
        LambdaQueryWrapper<Collect> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Collect::getUserAccount,collect.getUserAccount()).eq(Collect::getEquiptmentId,collect.getEquiptmentId());
        boolean remove = collectService.remove(queryWrapper);
        return remove?R.success("取消收藏成功"):R.error("取消收藏失败");
    }
    //获取完整收藏信息
    @GetMapping("getAllByAccount")
    public R getAllByAccount(String userAccount){
        LambdaQueryWrapper<Collect> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Collect::getUserAccount,userAccount);
        List<Collect> collects = collectService.list(queryWrapper);
        List<CollectDto> collectDtos = new ArrayList<>();
        for (Collect collect : collects) {
            CollectDto collectDto = new CollectDto();
            BeanUtils.copyProperties(collect,collectDto);
            Integer equiptmentId = collect.getEquiptmentId();
            Equiptment equiptment = equiptmentService.getById(equiptmentId);
            EquiptmentDto equiptmentDto = new EquiptmentDto();
            BeanUtils.copyProperties(equiptment,equiptmentDto);
            String img_id = equiptment.getImgId();
            String equiptmentUserAccount = equiptment.getUserAccount();
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(User::getAccount,equiptmentUserAccount);
            User user = userService.getOne(userLambdaQueryWrapper);
            equiptmentDto.setUser(user);
            LambdaQueryWrapper<EquiptmentImgs> queryWrapper3 = new LambdaQueryWrapper<>();
            queryWrapper3.eq(EquiptmentImgs::getId,img_id);
            List<EquiptmentImgs> equiptmentImgs = equiptmentImgsService.list(queryWrapper3);
            List<String> imgs = new ArrayList<>();
            for (EquiptmentImgs equiptmentImg : equiptmentImgs) {
                imgs.add(equiptmentImg.getData());
            }
            equiptmentDto.setImgs(imgs);
            collectDto.setEquiptmentDto(equiptmentDto);
            collectDtos.add(collectDto);
        }
        return R.success(collectDtos);
    }
}
