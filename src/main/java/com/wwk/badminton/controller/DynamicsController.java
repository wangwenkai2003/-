package com.wwk.badminton.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwk.badminton.common.R;
import com.wwk.badminton.common.TimeUtil;
import com.wwk.badminton.dto.DynamicsDto;
import com.wwk.badminton.dto.ImgDto;
import com.wwk.badminton.pojo.*;
import com.wwk.badminton.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dynamics")
public class DynamicsController {
    @Autowired
    private DynamicsService dynamicsService;

    @Autowired
    private ImgService  imgService;

    @Autowired
    private DylikeService  dylikeService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UsrServcie usrServcie;

    @Autowired
    private FollowService followService;

    @PostMapping("/publish")
    public R register(@RequestBody DynamicsDto dynamicsDto){
        String time = TimeUtil.getTime();
        dynamicsDto.setCreateTime(time);
        boolean save = dynamicsService.save(dynamicsDto);
        return save ? R.success("发表成功") : R.error("发表失败");
    }
    @PostMapping("/appPublish")
    public R appPublish(@RequestBody Dynamics dynamics){
        String time = TimeUtil.getTime();
        dynamics.setCreateTime(time);
        ArrayList<String> images = dynamics.getImages();
        String id = dynamics.getId();
        for (String image : images) {
            ImgDto imgDto = new ImgDto();
            imgDto.setData(image);
            imgDto.setDynamicsId(id);
            imgDto.setUserAccount(dynamics.getUserAccount());
            imgDto.setCreateTime(time);
            imgService.save(imgDto);
        }
        DynamicsDto dynamicsDto = new DynamicsDto();
        BeanUtils.copyProperties(dynamics,dynamicsDto);
        boolean save = dynamicsService.save(dynamicsDto);
        return save ? R.success("发表成功") : R.error("发表失败");
    }
    //获取关注动态信息
    @GetMapping("/getFollowDynamics")
    public R getFollowDynamics(String userAccount,int currentLimit,int limit ){
        //获取动态基本信息
        LambdaQueryWrapper<Follow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Follow::getFollowUserAccount,userAccount);
        List<Follow> followList = followService.list(queryWrapper);
        List<String> followIdList = new ArrayList<>();
        for (Follow follow : followList) {
            followIdList.add(follow.getBefollowUserAccount());
        }
        List<DynamicsDto> list = dynamicsService.getFollowDynamics(followIdList, currentLimit, limit);
        List<Dynamics> newList = new ArrayList<>();
        for (DynamicsDto dynamicsDto : list) {
            Dynamics dynamics = new Dynamics();
            BeanUtils.copyProperties(dynamicsDto,dynamics);
            String id = dynamicsDto.getId();
            //获取动态用户信息
            String followUserAccount = dynamicsDto.getUserAccount();
            LambdaQueryWrapper<User> queryWrapperX =new LambdaQueryWrapper<>();
            queryWrapperX.eq(User::getAccount,followUserAccount);
            User user = usrServcie.getOne(queryWrapperX);
            dynamics.setUser(user);
            //获取动态图片信息
            LambdaQueryWrapper<ImgDto> queryWrapper1 =new LambdaQueryWrapper<>();
            queryWrapper1.eq(ImgDto::getDynamicsId,id);
            List<ImgDto> imgs = imgService.list(queryWrapper1);
            ArrayList<String> imgList = new ArrayList<>();
            for (ImgDto img : imgs) {
                imgList.add(img.getData());
            }
            dynamics.setImgs(imgList);
            //获取动态点赞信息
            LambdaQueryWrapper<DyLike> queryWrapper2 =new LambdaQueryWrapper<>();
            queryWrapper2.eq(DyLike::getDynamicsId,id);
            List<DyLike> likes = dylikeService.list(queryWrapper2);
            dynamics.setDyLikes(likes);
            dynamics.setLikeCount(likes.size());
            //获取动态评论信息
            LambdaQueryWrapper<Comment> queryWrapper3 = new LambdaQueryWrapper<>();
            queryWrapper3.eq(Comment::getDynamicsId,id);
            List<Comment> comments = commentService.list(queryWrapper3);
            dynamics.setComments(comments);
            dynamics.setCommentCount(comments.size());
            newList.add(dynamics);
        }
        return newList.size()>0 ? R.success(newList) : R.error("获取失败");
    }
    //获取我的动态信息
    @GetMapping("/getDynamics")
    public R getDynamics(String userAccount){
        //获取动态基本信息
        LambdaQueryWrapper<DynamicsDto> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DynamicsDto::getUserAccount,userAccount);
        queryWrapper.orderByDesc(DynamicsDto::getCreateTime);
        List<DynamicsDto> list = dynamicsService.list(queryWrapper);
        List<Dynamics> newList = new ArrayList<>();
        for (DynamicsDto dynamicsDto : list) {
            String id = dynamicsDto.getId();
            //获取动态图片信息
            LambdaQueryWrapper<ImgDto> queryWrapper1 =new LambdaQueryWrapper<>();
            queryWrapper1.eq(ImgDto::getDynamicsId,id);
            List<ImgDto> imgs = imgService.list(queryWrapper1);
            ArrayList<String> imgList = new ArrayList<>();
            for (ImgDto img : imgs) {
                imgList.add(img.getData());
            }
            Dynamics dynamics = new Dynamics();
            BeanUtils.copyProperties(dynamicsDto,dynamics);
            dynamics.setImgs(imgList);
            //获取动态点赞信息
            LambdaQueryWrapper<DyLike> queryWrapper2 =new LambdaQueryWrapper<>();
            queryWrapper2.eq(DyLike::getDynamicsId,id);
            List<DyLike> likes = dylikeService.list(queryWrapper2);
            dynamics.setDyLikes(likes);
            dynamics.setLikeCount(likes.size());
            //获取动态评论信息
            LambdaQueryWrapper<Comment> queryWrapper3 = new LambdaQueryWrapper<>();
            queryWrapper3.eq(Comment::getDynamicsId,id);
            List<Comment> comments = commentService.list(queryWrapper3);
            dynamics.setComments(comments);
            dynamics.setCommentCount(comments.size());
            newList.add(dynamics);
        }
        return newList.size()>0 ? R.success(newList) : R.error("获取失败");
    }
    //获取动态数量
    @GetMapping("/getDynamicsCount")
    private R getDynamicsCount(String userAccount){
        LambdaQueryWrapper<DynamicsDto> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DynamicsDto::getUserAccount,userAccount);
        int count = dynamicsService.count(queryWrapper);
        return R.success(count);
    }
    //根据用户账号获取动态信息
    @GetMapping("/getDynamicsByUserAccount")
    public R getDynamics(String userAccount,int currentLimit,int limit ){
        //获取动态基本信息
        List<DynamicsDto> list = dynamicsService.getDynamicsPageByUserAccount(userAccount,currentLimit,limit);
        List<Dynamics> newList = new ArrayList<>();
        for (DynamicsDto dynamicsDto : list) {
            String id = dynamicsDto.getId();
            //获取动态图片信息
            LambdaQueryWrapper<ImgDto> queryWrapper1 =new LambdaQueryWrapper<>();
            queryWrapper1.eq(ImgDto::getDynamicsId,id);
            List<ImgDto> imgs = imgService.list(queryWrapper1);
            ArrayList<String> imgList = new ArrayList<>();
            for (ImgDto img : imgs) {
                imgList.add(img.getData());
            }
            Dynamics dynamics = new Dynamics();
            BeanUtils.copyProperties(dynamicsDto,dynamics);
            dynamics.setImgs(imgList);
            //获取动态点赞信息
            LambdaQueryWrapper<DyLike> queryWrapper2 =new LambdaQueryWrapper<>();
            queryWrapper2.eq(DyLike::getDynamicsId,id);
            List<DyLike> likes = dylikeService.list(queryWrapper2);
            dynamics.setDyLikes(likes);
            dynamics.setLikeCount(likes.size());
            //获取动态评论信息
            LambdaQueryWrapper<Comment> queryWrapper3 = new LambdaQueryWrapper<>();
            queryWrapper3.eq(Comment::getDynamicsId,id);
            List<Comment> comments = commentService.list(queryWrapper3);
            dynamics.setComments(comments);
            dynamics.setCommentCount(comments.size());
            newList.add(dynamics);
        }
        return newList.size()>0 ? R.success(newList) : R.error("获取失败");
    }
    //获取所有动态信息
    @GetMapping("/getDynamicsList")
    public R getDynamicsList(){
        //获取动态基本信息
        LambdaQueryWrapper<DynamicsDto> queryWrapper0 = new LambdaQueryWrapper<>();
        queryWrapper0.orderByDesc(DynamicsDto::getCreateTime);
        List<DynamicsDto> list = dynamicsService.list(queryWrapper0);
        List<Dynamics> newList = new ArrayList<>();
        for (DynamicsDto dynamicsDto : list) {
            String id = dynamicsDto.getId();
            Dynamics dynamics = new Dynamics();
            BeanUtils.copyProperties(dynamicsDto,dynamics);
            //获取动态用户信息
            String userAccount = dynamicsDto.getUserAccount();
            LambdaQueryWrapper<User> queryWrapper =new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getAccount,userAccount);
            User user = usrServcie.getOne(queryWrapper);
            dynamics.setUser(user);
            //获取动态图片信息
            LambdaQueryWrapper<ImgDto> queryWrapper1 =new LambdaQueryWrapper<>();
            queryWrapper1.eq(ImgDto::getDynamicsId,id);
            List<ImgDto> imgs = imgService.list(queryWrapper1);
            ArrayList<String> imgList = new ArrayList<>();
            for (ImgDto img : imgs) {
                imgList.add(img.getData());
            }
            dynamics.setImgs(imgList);
            //获取动态点赞信息
            LambdaQueryWrapper<DyLike> queryWrapper2 =new LambdaQueryWrapper<>();
            queryWrapper2.eq(DyLike::getDynamicsId,id);
            List<DyLike> likes = dylikeService.list(queryWrapper2);
            dynamics.setDyLikes(likes);
            dynamics.setLikeCount(likes.size());
            //获取动态评论信息
            LambdaQueryWrapper<Comment> queryWrapper3 = new LambdaQueryWrapper<>();
            queryWrapper3.eq(Comment::getDynamicsId,id);
            List<Comment> comments = commentService.list(queryWrapper3);
            dynamics.setComments(comments);
            dynamics.setCommentCount(comments.size());
            newList.add(dynamics);
        }
        return newList.size()>0 ? R.success(newList) : R.error("获取失败");
    }
    @GetMapping("/getDynamicsPage")
    public R getDynamicsList(int currentLimit,int limit){
        //获取动态基本信息
        List<DynamicsDto> list = dynamicsService.getDynamicsPage(currentLimit, limit);
        List<Dynamics> newList = new ArrayList<>();
        for (DynamicsDto dynamicsDto : list) {
            String id = dynamicsDto.getId();
            Dynamics dynamics = new Dynamics();
            BeanUtils.copyProperties(dynamicsDto,dynamics);
            //获取动态用户信息
            String userAccount = dynamicsDto.getUserAccount();
            LambdaQueryWrapper<User> queryWrapper =new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getAccount,userAccount);
            User user = usrServcie.getOne(queryWrapper);
            dynamics.setUser(user);
            //获取动态图片信息
            LambdaQueryWrapper<ImgDto> queryWrapper1 =new LambdaQueryWrapper<>();
            queryWrapper1.eq(ImgDto::getDynamicsId,id);
            List<ImgDto> imgs = imgService.list(queryWrapper1);
            ArrayList<String> imgList = new ArrayList<>();
            for (ImgDto img : imgs) {
                imgList.add(img.getData());
            }
            dynamics.setImgs(imgList);
            //获取动态点赞信息
            LambdaQueryWrapper<DyLike> queryWrapper2 =new LambdaQueryWrapper<>();
            queryWrapper2.eq(DyLike::getDynamicsId,id);
            List<DyLike> likes = dylikeService.list(queryWrapper2);
            dynamics.setDyLikes(likes);
            dynamics.setLikeCount(likes.size());
            //获取动态评论信息
            LambdaQueryWrapper<Comment> queryWrapper3 = new LambdaQueryWrapper<>();
            queryWrapper3.eq(Comment::getDynamicsId,id);
            List<Comment> comments = commentService.list(queryWrapper3);
            dynamics.setComments(comments);
            dynamics.setCommentCount(comments.size());
            newList.add(dynamics);
        }
        return newList.size()>0 ? R.success(newList) : R.error("获取失败");
    }
    @DeleteMapping("/deleteById")
    public R deleteById( String id){
        boolean b = dynamicsService.removeById(id);
        return b ? R.success("删除成功") : R.error("删除失败");
    }
}
