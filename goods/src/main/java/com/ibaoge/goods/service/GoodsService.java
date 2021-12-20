package com.ibaoge.goods.service;

import com.ibaoge.goods.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baoge
 * @since 2021-12-15
 */
public interface GoodsService extends IService<Goods> {

    Goods findGoodsByMasterDB(Integer id);

    Goods findGoodsBySlaveDB(Integer id);
}
