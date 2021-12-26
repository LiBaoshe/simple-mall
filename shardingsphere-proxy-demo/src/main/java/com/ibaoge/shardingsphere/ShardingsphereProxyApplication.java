package com.ibaoge.shardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ibaoge.shardingsphere.mapper")
@SpringBootApplication
public class ShardingsphereProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingsphereProxyApplication.class, args);
    }

}
