package com.wwk.badminton.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wf.captcha.SpecCaptcha;
import com.wwk.badminton.common.R;
import com.wwk.badminton.dto.RegisterDto;
import com.wwk.badminton.dto.UserDto;
import com.wwk.badminton.pojo.Follow;
import com.wwk.badminton.pojo.User;
import com.wwk.badminton.service.FollowService;
import com.wwk.badminton.service.Impl.UserServiceImpl;
import com.wwk.badminton.util.JwtTokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //图形验证码
    @GetMapping("/captcha")
    public R captcha(){
        SpecCaptcha specCaptcha = new SpecCaptcha(130,48,5);
        String vercode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(key,vercode,30, TimeUnit.MINUTES);
        ArrayList<String> code = new ArrayList<>();
        code.add(key);
        code.add(specCaptcha.toBase64());
        return R.success(code);
    }

    @PostMapping("/login")
    public R login(@RequestBody User user){
        log.info(user.toString());
        UserDto userDto = new UserDto();
        if (user.getPhone() != null ){
            LambdaQueryWrapper<User> queryWrapper =new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,user.getPhone());
            User userDb = userService.getOne(queryWrapper);
            if (userDb.getPassword().equals(user.getPassword())){
                String token = JwtTokenGenerator.generateToken(user.getPhone());
                BeanUtils.copyProperties(userDb,userDto);
                userDto.setToken(token);
                log.info(userDto.toString());
                return R.success(userDto,"登录成功");
            }
        }
        if (user.getAccount() !=null){
            LambdaQueryWrapper<User> queryWrapper =new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getAccount,user.getAccount());
            User userDb = userService.getOne(queryWrapper);
            if (userDb!=null && userDb.getPassword().equals(user.getPassword()) ){
                String token = JwtTokenGenerator.generateToken(user.getPhone());
                BeanUtils.copyProperties(userDb,userDto);
                userDto.setToken(token);
                log.info(userDto.toString());
                return R.success(userDto,"登录成功");
            }
        }
        return R.error("登录失败");
    }

    @PostMapping("/register")
    public  R register(@RequestBody RegisterDto registerDto){
        String key = registerDto.getKey();
        Object o = redisTemplate.opsForValue().get(key);
        String s = String.valueOf(o);
        String code = registerDto.getCode();
        if (o !=null && code.equals(s)){
            boolean save = userService.save(registerDto);
            if (save){
                String token = JwtTokenGenerator.generateToken(registerDto.getAccount());
                return R.success(token,"注册成功");
            }
        }
        return R.error("注册失败,请稍后重试");
    }

    @PutMapping("/update")
    public R update(@RequestBody User user){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount,user.getAccount());
        boolean update = userService.update(user,queryWrapper);
        if (update){
            User userServiceOne = userService.getOne(queryWrapper);
            return R.success(userServiceOne,"修改成功");
        }
        return R.error("修改失败");
    }

    @GetMapping("getUserByAccount")
    public R getUserByAccount(@RequestParam("account") String account){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount,account);
        User user = userService.getOne(queryWrapper);
        return R.success(user);
    }
}
