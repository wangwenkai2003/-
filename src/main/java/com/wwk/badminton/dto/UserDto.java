package com.wwk.badminton.dto;

import com.wwk.badminton.pojo.User;
import lombok.Data;

@Data
public class UserDto extends User {
    //token
    private String token;
    //是否被关注
    private boolean isFollow;
}
