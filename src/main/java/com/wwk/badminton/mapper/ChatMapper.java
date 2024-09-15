package com.wwk.badminton.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwk.badminton.pojo.Chat;
import com.wwk.badminton.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMapper extends BaseMapper<Chat> {
    @Select("SELECT id, `from_user` AS `from`, `to_user` AS `to`, user_content AS content, create_time AS createTime, if_img,img,if_receive\n" +
            "FROM chat\n" +
            "WHERE (from_user = #{fromAccount} AND to_user = #{toAccount}) OR (from_user = #{toAccount} AND to_user = #{fromAccount}) order by sort")
    public   List<Chat>  getMessage(String fromAccount, String toAccount);

    @Select("SELECT id, `from_user` AS `from`, `to_user` AS `to`, user_content AS content, create_time AS createTime, if_img,img,if_receive\n" +
            "FROM chat\n" +
            "WHERE (from_user = #{fromAccount} AND to_user = #{toAccount}) OR (from_user = #{toAccount} AND to_user = #{fromAccount}) order by sort desc limit #{currentLimit},#{limit}")
    public   List<Chat>  getMessagePage(String fromAccount, String toAccount,int limit,int currentLimit);

//    @Select("SELECT * from chat where from_user = '202104279' or to_user ='202104279' order by sort")
    @Select("SELECT id, `from_user` AS `from`, `to_user` AS `to`, user_content AS content, create_time AS createTime, if_img,img,if_receive \tfrom chat where to_user =#{fromUser}  or from_user =#{fromUser} order by sort asc")
    public List<Chat> getChats(String formUser);
}
