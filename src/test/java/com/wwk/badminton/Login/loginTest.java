package com.wwk.badminton.Login;

import com.wwk.badminton.pojo.User;
import com.wwk.badminton.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class loginTest {
    @Autowired
    private UserServiceImpl loginServcie;
    @Test
    public void getUser(){
        List<User> list = loginServcie.list();
        System.out.println(list);
    }
}
