package com.ifsaid.shark.service;

import com.github.pagehelper.PageInfo;
import com.ifsaid.shark.common.service.BaseService;
import com.ifsaid.shark.entity.Order;
import com.ifsaid.shark.entity.OrderItem;
import com.ifsaid.shark.util.QueryParameter;

import java.math.BigDecimal;
import java.util.List;

public interface IOrderService extends BaseService<Order, Integer> {
    PageInfo<Order> findAllPageInfo(QueryParameter parameter);

    List<OrderItem> getOrderItemByNo(String orderNo);

    Long getOrderCount();
    BigDecimal getOrderPrice();
}
