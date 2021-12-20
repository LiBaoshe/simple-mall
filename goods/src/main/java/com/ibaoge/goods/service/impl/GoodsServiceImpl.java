package com.ibaoge.goods.service.impl;

import com.ibaoge.goods.datasource.CurDataSource;
import com.ibaoge.goods.datasource.DataSourceNames;
import com.ibaoge.goods.entity.Goods;
import com.ibaoge.goods.mapper.GoodsMapper;
import com.ibaoge.goods.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baoge
 * @since 2021-12-15
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public Goods findGoodsByMasterDB(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @CurDataSource(name = DataSourceNames.SLAVE)
    @Override
    public Goods findGoodsBySlaveDB(Integer id) {
        return this.baseMapper.selectById(id);
    }
}
