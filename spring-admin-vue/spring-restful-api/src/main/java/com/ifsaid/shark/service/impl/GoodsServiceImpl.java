package com.ifsaid.shark.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ifsaid.shark.common.mapper.BaseMapper;
import com.ifsaid.shark.common.service.impl.BaseServiceImpl;
import com.ifsaid.shark.entity.Goods;
import com.ifsaid.shark.entity.GoodsClassify;
import com.ifsaid.shark.mapper.GoodsClassifyMapper;
import com.ifsaid.shark.mapper.GoodsMapper;
import com.ifsaid.shark.service.IGoodsService;
import com.ifsaid.shark.util.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods,Integer, GoodsMapper> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsClassifyMapper goodsClassifyMapper;

    @Override
    public PageInfo<Goods> findAllPageInfo(QueryParameter parameter) {
        PageInfo<Goods> pageInfo= PageHelper
                .startPage(parameter.getPageNum(),parameter.getPageSize())
                .doSelectPageInfo(()->{
                    if(StringUtils.isEmpty(parameter.getKeywords())){
                        baseMapper.getAll();
                    }else{
                        baseMapper.selectByKeywords(parameter.getKeywords());
                    }
                });

        List<Goods> goodsList = pageInfo.getList();
        PageInfo<Goods> result = new PageInfo<>();
        result.setList(goodsList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public List<GoodsClassify> getAll() {
        return goodsClassifyMapper.getAll();
    }

    @Override
    public int deleteGoodsById(Integer id) {
        return goodsMapper.deleteGoodsById(id);
    }

    @Override
    public int updateGoods(Goods goods) {
        return goodsMapper.updateGoods(goods);
    }

    @Override
    public int saveGoods(Goods goods) {
        return goodsMapper.saveGoods(goods);
    }

    @Override
    public Long getGoodsCount() {
        return baseMapper.getGoodsCount();
    }


}
