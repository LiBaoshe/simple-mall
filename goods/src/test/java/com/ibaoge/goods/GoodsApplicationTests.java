package com.ibaoge.goods;

import com.ibaoge.goods.entity.Goods;
import com.ibaoge.goods.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.math.BigDecimal;

@SpringBootTest
class GoodsApplicationTests {

    //

    @Autowired
    private GoodsService goodsService;

    @Test
    void findGoods() {
        Goods goods1 = goodsService.findGoodsByMasterDB(1);
        Goods goods2 = goodsService.findGoodsBySlaveDB(1);
    }

    @Test
    void save(){
        goodsService.save(getGoods());
    }

    private Goods getGoods(){
        Goods goods = new Goods();
        goods.setName("商品名");
        goods.setPrice(BigDecimal.valueOf(20));
        goods.setDiscount(0.85f);
        goods.setStock(88);
        goods.setType("aa");
        goods.setCreateTime(System.currentTimeMillis());
        goods.setUpdateTime(System.currentTimeMillis());
        return goods;
    }
}
