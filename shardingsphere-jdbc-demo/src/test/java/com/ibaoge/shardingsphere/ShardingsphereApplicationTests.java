package com.ibaoge.shardingsphere;

import com.ibaoge.shardingsphere.entity.User;
import com.ibaoge.shardingsphere.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShardingsphereApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void findUserById() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            User user = userService.getById(1);
            System.out.println("第 " + i + " 次查询结果：user = " + user);
        }
    }

    @Test
    void saveUser() {
        User user = getUser();
        user.insert();
        System.out.println(user);
        System.out.println("新增 user.id = " + user.getId());
    }

    private User getUser(){
        long now = System.currentTimeMillis();
        User user = new User();
        user.setUsername("abc");
        user.setPassword("abc");
        user.setNickname("哈哈哈");
        user.setGender(1);
        user.setAge(20);
        user.setIdNo("66666666666666");
        user.setPhone("18888888888");
        user.setState(1);
        user.setCreateTime(now);
        user.setUpdateTime(now);
        return user;
    }

}
