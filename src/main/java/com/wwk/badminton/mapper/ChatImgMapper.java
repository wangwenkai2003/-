package com.wwk.badminton.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwk.badminton.pojo.ChatImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChatImgMapper extends BaseMapper<ChatImg> {
    @Select("SELECT chat_img.data FROM chat_img where chat_img.chat_id = (select id from chat where chat.from_user = #{fromUser} and chat.to_user=#{toUser} order by sort desc limit 1)")
    public String getImg(String fromUser,String toUser);

}
