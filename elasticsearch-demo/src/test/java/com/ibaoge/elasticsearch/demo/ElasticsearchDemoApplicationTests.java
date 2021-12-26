package com.ibaoge.elasticsearch.demo;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ElasticsearchDemoApplicationTests {

    @Resource
    private RestHighLevelClient client;

    @Test
    void contextLoads() {
        Thread.State s;
        System.out.println(client);
    }

}
