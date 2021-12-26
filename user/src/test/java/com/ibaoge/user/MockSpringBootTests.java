package com.ibaoge.user;

import com.ibaoge.user.dao.UserMapper;
import com.ibaoge.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class MockSpringBootTests {

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    public void before(){
        userMapper = mock(UserMapper.class);
    }

    @Test
    public void test01(){
        User user = new User();
        user.setUsername("abc");
        user.setPassword("abc");
        user.setAge((short) 20);
        user.setGender((byte)1);
        when(userMapper.selectByPrimaryKey(1)).thenReturn(user);
        System.out.println(user);
        verify(userMapper).selectByPrimaryKey(1);
    }
}
