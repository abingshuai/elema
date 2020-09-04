package com.ifsaid.shark.mapper;

import com.ifsaid.shark.common.mapper.BaseMapper;
import com.ifsaid.shark.entity.Goods;

import java.util.List;
import java.util.Set;

public interface GoodsMapper extends BaseMapper<Goods,Integer> {
    List<Goods> getAll();
    Set<Goods> selectByKeywords(String keywords);
    int deleteGoodsById(Integer id);

    int updateGoods(Goods goods);

    int saveGoods(Goods goods);
}
