package com.ifsaid.shark.mapper;

import com.ifsaid.shark.common.mapper.BaseMapper;
import com.ifsaid.shark.entity.Goods;
import com.ifsaid.shark.entity.Order;

import java.util.List;
import java.util.Set;

public interface OrderMapper extends BaseMapper<Order, Integer> {
    List<Order> getAll();
    Set<Order> selectByKeywords(String keywords);
}
