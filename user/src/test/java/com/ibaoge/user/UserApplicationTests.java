package com.ibaoge.user;

import com.ibaoge.user.dao.UserMapper;
import com.ibaoge.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserApplicationTests {

    @Resource
    UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("baoge");
        user.setPassword("baoge");
        user.setAge((short) 20);
        user.setGender((byte)1);
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        userMapper.insert(user);
    }
}
