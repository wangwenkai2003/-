package com.wwk.badminton.dto;

import com.wwk.badminton.pojo.User;
import lombok.Data;

@Data
public class RegisterDto extends User {
    private String key;

    private String code;
}
