package com.ifsaid.shark.mapper;

import com.ifsaid.shark.common.mapper.BaseMapper;
import com.ifsaid.shark.entity.Goods;
import com.ifsaid.shark.entity.GoodsClassify;

import java.util.List;
import java.util.Set;

public interface GoodsClassifyMapper extends BaseMapper<GoodsClassify, Integer> {

    List<GoodsClassify> getAll();

    Set<GoodsClassify> selectByKeywords(String keywords);

    int deleteTypeById(Integer id);

    int updateType(GoodsClassify goodsClassify);
}
