package com.ifsaid.shark.service;

import com.github.pagehelper.PageInfo;
import com.ifsaid.shark.common.service.BaseService;
import com.ifsaid.shark.entity.Goods;
import com.ifsaid.shark.entity.GoodsClassify;
import com.ifsaid.shark.util.QueryParameter;

public interface IGoodsClassifyService extends BaseService<GoodsClassify,Integer> {
    PageInfo<GoodsClassify> findAllPageInfo(QueryParameter parameter);
    int deleteTypeById(Integer id);

    int updateType(GoodsClassify goodsClassify);
}
