package com.ibaoge.shardingsphere.generator;

import com.ibaoge.common.generator.MyBatisPlusGenerator;

public class OrderGenerator {
    public static void main(String[] args) {
        MyBatisPlusGenerator.generate(
                "shardingsphere-proxy-demo",
                "com.ibaoge.shardingsphere",
                new String[]{"t_order"});
    }
}
