package com.ibaoge.order.service.impl;

import com.ibaoge.order.entity.Order;
import com.ibaoge.order.mapper.OrderMapper;
import com.ibaoge.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baoge
 * @since 2021-12-14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
