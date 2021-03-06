package com.ifsaid.shark.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ifsaid.shark.common.service.impl.BaseServiceImpl;
import com.ifsaid.shark.entity.Order;
import com.ifsaid.shark.entity.OrderItem;
import com.ifsaid.shark.mapper.OrderItemMapper;
import com.ifsaid.shark.mapper.OrderMapper;
import com.ifsaid.shark.service.IOrderService;
import com.ifsaid.shark.util.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order,Integer, OrderMapper> implements IOrderService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageInfo<Order> findAllPageInfo(QueryParameter parameter) {
        PageInfo<Order> pageInfo= PageHelper
                .startPage(parameter.getPageNum(),parameter.getPageSize())
                .doSelectPageInfo(()->{
                    if(StringUtils.isEmpty(parameter.getKeywords())){
                        baseMapper.getAll();
                    }else{
                        baseMapper.selectByKeywords(parameter.getKeywords());
                    }
                });

        List<Order> orderList = pageInfo.getList();
        PageInfo<Order> result = new PageInfo<>();
        result.setList(orderList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public List<OrderItem> getOrderItemByNo(String orderNo) {
        return orderItemMapper.getOrderItemByNo(orderNo);
    }

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        return orderItemMapper.saveOrderItem(orderItem);
    }

    @Override
    public Long getOrderCount() {
        return baseMapper.getOrderCount();
    }

    @Override
    public BigDecimal getOrderPrice() {
        return baseMapper.getOrderPrice();
    }

    @Override
    public List<Order> getOrderByStatus(String userId, Integer status) {
        return orderMapper.getOrderByStatus(userId,status);
    }

    @Override
    public int updateOrderStatus(String userId, String orderNo, Integer status) {
        return orderMapper.updateOrderStatus(userId,orderNo,status);
    }

}
