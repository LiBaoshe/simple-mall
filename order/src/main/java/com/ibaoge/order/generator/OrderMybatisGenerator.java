package com.ibaoge.order.generator;


import com.ibaoge.common.generator.MyBatisPlusGenerator;

public class OrderMybatisGenerator {

    public static void main(String[] args) {
        MyBatisPlusGenerator.generate(
                "order",
                "com.ibaoge.order",
                new String[]{"t_order"});
    }
}
