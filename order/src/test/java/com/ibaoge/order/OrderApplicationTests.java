package com.ibaoge.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ibaoge.order.entity.Order;

import com.ibaoge.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


@SpringBootTest
class OrderApplicationTests {

    @Resource
    private OrderService orderService;

    // 模拟插入订单的数量
    private static final int INSERT_ORDER_NUM = 100_0000;

    // 简单模拟插入一条订单数据
    @Test
    void insertOrder() {
        getOrder().insert();
    }

    @Test
    void insertSingle() {
        System.out.println("插入 " + INSERT_ORDER_NUM + " 条订单数据...");
        long start = System.currentTimeMillis();
        for (int i = 0; i < INSERT_ORDER_NUM; i++) {
            insertOrder();
        }
        long end = System.currentTimeMillis();
        System.out.println("插入完成，共耗时：" + (end - start) + "ms.");
    }

    @Test
    void insertThreadPool() {
        int n = Runtime.getRuntime().availableProcessors();
//        ExecutorService executorService = Executors.newFixedThreadPool(2 * n);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        System.out.println("插入 " + INSERT_ORDER_NUM + " 条订单数据...");
        long start = System.currentTimeMillis();
        for (int i = 0; i < INSERT_ORDER_NUM; i++) {
            executorService.execute(this::insertOrder);
        }
        executorService.shutdown();
        while (true){
            if(executorService.isTerminated()){
                break;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("插入完成，共耗时：" + (end - start) + "ms.");
    }

    @Resource
    private DataSource dataSource;

    // 批量插入 n 个订单
    private int insertBath(int n){
        String sql = "INSERT INTO t_order(user_id, total_price, discount_price, state, create_time, update_time) " +
                "values (?, ?, ?, ?, ?, ?)";
        int count = 0;
        try (Connection conn = dataSource.getConnection()){
            // 关闭自动提交
            conn.setAutoCommit(false);
            // 预编译 sql
            PreparedStatement ps = conn.prepareStatement(sql);
            // 设置参数
            for (int i = 0; i < n; i++) {
                Order order = getOrder();
                ps.setInt(1, order.getUserId());
                ps.setBigDecimal(2, order.getTotalPrice());
                ps.setBigDecimal(3, order.getDiscountPrice());
                ps.setInt(4, order.getState());
                ps.setLong(5, order.getCreateTime());
                ps.setLong(6, order.getUpdateTime());
                // 添加到批处理
                ps.addBatch();
            }
            // 批量添加
            int[] batch = ps.executeBatch();

            for (int b : batch) {
                count += b;
            }
            // System.out.println("批量添加了 " + count + " 条订单数据.");
            conn.commit(); // 提交事务
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    // 每次批量插入的数量
    private static final int BATCH_SIZE = 100;

    @Test
    void insertSingleBath(){
        System.out.println("单线程批量插入 " + INSERT_ORDER_NUM + " 条数据，每次插入 " + BATCH_SIZE + " 条数据...");
        long start = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < INSERT_ORDER_NUM; i += BATCH_SIZE) {
            count += insertBath(BATCH_SIZE);
        }
        long end = System.currentTimeMillis();
        System.out.println("插入完成，共耗时：" + (end - start) + "ms.");
        System.out.println("共插入了 " + count + " 条订单数据.");
    }

    private AtomicInteger count = new AtomicInteger();

    @Test
    void insertThreadPoolBatch(){
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        System.out.println("多线程批量插入 " + INSERT_ORDER_NUM + " 条数据，每次插入 " + BATCH_SIZE + " 条数据...");
        long start = System.currentTimeMillis();
        count.set(0);
        for (int i = 0; i < INSERT_ORDER_NUM; i += BATCH_SIZE) {
            executorService.execute(() -> count.getAndAdd(insertBath(BATCH_SIZE)));
        }
        executorService.shutdown();
        while (true){
            if(executorService.isTerminated()){
                break;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("插入完成，共耗时：" + (end - start) + "ms.");
        System.out.println("共插入了 " + count.get() + " 条订单数据.");
    }

    @Test
    void delete(){
        orderService.remove(new QueryWrapper<>());
    }

    /**
     * 返回一个模拟订单
     * @return
     */
    private Order getOrder(){
        Order order = new Order();
        order.setUserId(1);
        order.setTotalPrice(BigDecimal.valueOf(100));
        order.setDiscountPrice(BigDecimal.valueOf(100));
        order.setState(1);
        long time = System.currentTimeMillis();
        order.setCreateTime(time);
        order.setUpdateTime(time);
        return order;
    }
}
