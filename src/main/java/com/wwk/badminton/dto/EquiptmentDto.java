package com.wwk.badminton.dto;

import com.wwk.badminton.pojo.Equiptment;
import com.wwk.badminton.pojo.User;
import lombok.Data;

import java.util.List;
@Data
public class EquiptmentDto extends Equiptment {
    private List<String> imgs;
    private User user;
}
