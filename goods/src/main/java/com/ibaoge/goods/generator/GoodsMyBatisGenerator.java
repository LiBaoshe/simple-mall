package com.ibaoge.goods.generator;

import com.ibaoge.common.generator.MyBatisPlusGenerator;

public class GoodsMyBatisGenerator {

    public static void main(String[] args) {
        MyBatisPlusGenerator.generate(
                "goods",
                "com.ibaoge.goods",
                new String[]{"t_goods"});
    }
}
