package com.ibaoge.shardingsphere;

import com.ibaoge.shardingsphere.entity.Order;
import com.ibaoge.shardingsphere.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Random;

@SpringBootTest
class ShardingsphereProxyApplicationTests {

    @Resource
    private OrderMapper orderMapper;

    @Test
    void insert1() {
        getOrder().insert();
    }

    @Test
    void insertBatch() {
        for (int i = 0; i < 1000; i++) {
            getOrder().insert();
        }
    }

    private Order getOrder(){
        Order order = new Order();
        order.setUserId(1 + new Random().nextInt(100));
        order.setTotalPrice(BigDecimal.valueOf(100));
        order.setDiscountPrice(BigDecimal.valueOf(100));
        order.setStatus(1);
        long time = System.currentTimeMillis();
        order.setCreateTime(time);
        order.setUpdateTime(time);
        return order;
    }

}
