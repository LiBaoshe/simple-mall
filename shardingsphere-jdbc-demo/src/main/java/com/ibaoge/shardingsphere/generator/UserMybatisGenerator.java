package com.ibaoge.shardingsphere.generator;

import com.ibaoge.common.generator.MyBatisPlusGenerator;

public class UserMybatisGenerator {

    public static void main(String[] args) {
        MyBatisPlusGenerator.generate("shardingsphere", "t_user");
    }
}
