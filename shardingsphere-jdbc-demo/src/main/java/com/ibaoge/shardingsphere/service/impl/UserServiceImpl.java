package com.ibaoge.shardingsphere.service.impl;

import com.ibaoge.shardingsphere.entity.User;
import com.ibaoge.shardingsphere.mapper.UserMapper;
import com.ibaoge.shardingsphere.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baoge
 * @since 2021-12-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
