package com.ibaoge.user;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.nio.charset.Charset;

public class GuavaTests {

//    ————————————————
//    版权声明：本文为CSDN博主「码客思」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/weixin_43927773/article/details/108381632

    @Test
    void bloomFilter(){
        BloomFilter<CharSequence> bloomFilter =
                BloomFilter.create(Funnels.stringFunnel(
                        Charset.forName("utf-8")), 10000, 0.0001);

        for (int i = 0; i < 5000; i++) {
            bloomFilter.put("" + i);
        }

        for (int i = 0; i < 10000; i++) {
            if(bloomFilter.mightContain("" + i)){
                System.out.println(i + " 存在");
            } else {
                System.out.println(i + " 不存在");
            }
        }

    }

    @Test
    void rBloomFilter(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        RBloomFilter<String> bloomFilter = redisson.getBloomFilter("user");
        // 初始化布隆过滤器，预计统计元素数量为55000000，期望误差率为0.03
        bloomFilter.tryInit(55000000L, 0.03);
        bloomFilter.add("Tom");
        bloomFilter.add("Jack");
        System.out.println(bloomFilter.count());   //2
        System.out.println(bloomFilter.contains("Tom"));  //true
        System.out.println(bloomFilter.contains("Linda"));  //false

    }
}
