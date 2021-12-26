package com.ibaoge.user;

import org.junit.jupiter.api.Test;

public class DemoTests {

    @Test
    void test(){
        char c = '爱';
        String s = "我爱你";
        System.out.println(c);
        for (char c1 : s.toCharArray()) {
            System.out.print(c1);
        }
    }

}
