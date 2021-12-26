package com.ibaoge.shardingsphere.service.impl;

import com.ibaoge.shardingsphere.entity.Order;
import com.ibaoge.shardingsphere.mapper.OrderMapper;
import com.ibaoge.shardingsphere.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baoge
 * @since 2021-12-25
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
