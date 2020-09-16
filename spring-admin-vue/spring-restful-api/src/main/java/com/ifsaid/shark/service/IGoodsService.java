package com.ifsaid.shark.service;

import com.github.pagehelper.PageInfo;
import com.ifsaid.shark.common.service.BaseService;
import com.ifsaid.shark.entity.Goods;
import com.ifsaid.shark.entity.GoodsClassify;
import com.ifsaid.shark.util.QueryParameter;

import java.util.List;

public interface IGoodsService extends BaseService<Goods, Integer> {
    PageInfo<Goods> findAllPageInfo(QueryParameter parameter);

    List<GoodsClassify> getAll();
    int deleteGoodsById(Integer id);

    int updateGoods(Goods goods);

    int saveGoods(Goods goods);

    Long getGoodsCount();

    List<Goods> getAllByTypeId(Integer typeId);

    Goods findAllById(Integer goodsId);
}
