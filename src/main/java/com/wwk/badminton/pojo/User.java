package com.wwk.badminton.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;


    //姓名
    private String name;


    //手机号
    private String phone;

    //账号
    private String account;

    //密码
    private String password;

    //性别 0 男 1 女
    private String sex;


    //身份证号
    private String idNumber;


    //头像
    private String avatar;


    //状态 0:禁用，1:正常
    private Integer status;

    //qq
    private String qq;

    //email
    private String email;

    //birthday
    private String birthday;

    //college
    private String college;

}
