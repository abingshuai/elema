package com.ifsaid.shark.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ifsaid.shark.common.mapper.BaseMapper;
import com.ifsaid.shark.common.service.BaseService;
import com.ifsaid.shark.common.service.impl.BaseServiceImpl;
import com.ifsaid.shark.entity.Goods;
import com.ifsaid.shark.entity.GoodsClassify;
import com.ifsaid.shark.mapper.GoodsClassifyMapper;
import com.ifsaid.shark.service.IGoodsClassifyService;
import com.ifsaid.shark.util.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class GoodsClassifyServiceImpl extends BaseServiceImpl<GoodsClassify,Integer, GoodsClassifyMapper> implements IGoodsClassifyService {

    @Autowired
    private GoodsClassifyMapper goodsClassifyMapper;

    @Override
    public PageInfo<GoodsClassify> findAllPageInfo(QueryParameter parameter) {
        PageInfo<GoodsClassify> pageInfo= PageHelper
                .startPage(parameter.getPageNum(),parameter.getPageSize())
                .doSelectPageInfo(()->{
                    if(StringUtils.isEmpty(parameter.getKeywords())){
                        baseMapper.getAll();
                    }else{
                        baseMapper.selectByKeywords(parameter.getKeywords());
                    }
                });

        List<GoodsClassify> goodsList = pageInfo.getList();
        PageInfo<GoodsClassify> result = new PageInfo<>();
        result.setList(goodsList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public int deleteTypeById(Integer id) {
        return goodsClassifyMapper.deleteTypeById(id);
    }

    @Override
    public int updateType(GoodsClassify goodsClassify) {
        return goodsClassifyMapper.updateType(goodsClassify);
    }
}
