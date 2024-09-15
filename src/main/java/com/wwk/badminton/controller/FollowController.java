package com.wwk.badminton.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wwk.badminton.common.R;
import com.wwk.badminton.dto.UserDto;
import com.wwk.badminton.pojo.Follow;
import com.wwk.badminton.pojo.User;
import com.wwk.badminton.service.FollowService;
import com.wwk.badminton.service.UsrServcie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    private UsrServcie userService;

    @Autowired
    private FollowService followService;

    @PostMapping("saveFollow")
    public R saveFollow(@RequestBody Follow follow){
        boolean save = followService.save(follow);
        return save?R.success("关注成功"):R.error("关注失败");
    }
    //获取我的关注
    @GetMapping("getMyFollow")
    public R getMyFollow(String userAccount){
        LambdaQueryWrapper<Follow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Follow::getFollowUserAccount,userAccount);
        List<Follow> followList = followService.list(queryWrapper);
        //被关注者列表
        ArrayList<User> users = new ArrayList<>();
        for (Follow follow : followList) {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            String befollowUserAccount = follow.getBefollowUserAccount();
            wrapper.eq(User::getAccount,befollowUserAccount);
            User beUser = userService.getOne(wrapper);
            users.add(beUser);
        }
        return R.success(users);
    }
    //获取我的粉丝
    @GetMapping("getMyBeFollow")
    public R getMyBeFollow(String userAccount){
        //粉丝列表
        LambdaQueryWrapper<Follow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Follow::getBefollowUserAccount,userAccount);
        List<Follow> followList = followService.list(queryWrapper);
        //关注列表
        LambdaQueryWrapper<Follow> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Follow::getFollowUserAccount,userAccount);
        List<Follow> followList1 = followService.list(queryWrapper1);

        ArrayList<UserDto> users = new ArrayList<>();
        for (Follow follow : followList) {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            String followUserAccount = follow.getFollowUserAccount();
            wrapper.eq(User::getAccount,followUserAccount);
            User beUser = userService.getOne(wrapper);
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(beUser,userDto);
            boolean flag = true;
            for (Follow follow1 : followList1) {
                 if (follow1.getBefollowUserAccount().equals(follow.getFollowUserAccount())) {
                    userDto.setFollow(true);
                    users.add(userDto);
                    flag=false;
                    break;
                }
            }
            if (flag){
                users.add(userDto);
            }
        }
        return R.success(users);
    }

    //获取关注和粉丝数量
    @GetMapping("getFollowAndFans")
    private R getFollowAndFans(String userAccount){
        ArrayList<Integer> followAndFans = new ArrayList<>();
        LambdaQueryWrapper<Follow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Follow::getFollowUserAccount,userAccount);
        int followCount = followService.count(queryWrapper);
        LambdaQueryWrapper<Follow> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Follow::getBefollowUserAccount,userAccount);
        int beFollowCount = followService.count(queryWrapper1);
        followAndFans.add(followCount);
        followAndFans.add(beFollowCount);
        return R.success(followAndFans);
    }

    @DeleteMapping("deleteFollow")
    public R deleteFollow(@RequestBody Follow follow){
        followService.deleteFollow(follow);
        return R.success("取消关注");
    }

    //检查是否关注
    @GetMapping("checkFollow")
    public R checkFollow(String followAccount,String beFollowAccount){
        LambdaQueryWrapper<Follow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Follow::getFollowUserAccount,followAccount).eq(Follow::getBefollowUserAccount,beFollowAccount);
        Follow one = followService.getOne(queryWrapper);
        return  one!=null ? R.success(one) : R.error("未关注");
    }
}
