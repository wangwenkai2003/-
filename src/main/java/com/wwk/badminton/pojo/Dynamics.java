package com.wwk.badminton.pojo;

import com.wwk.badminton.dto.DynamicsDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Dynamics extends DynamicsDto {
    private List<String> imgs;
    private List<DyLike> dyLikes;
    private List<Comment> comments;
    private ArrayList<String> images;
    private User user;
    private int likeCount;
    private int commentCount;
}
