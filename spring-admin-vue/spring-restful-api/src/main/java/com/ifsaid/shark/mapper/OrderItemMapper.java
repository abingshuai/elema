package com.ifsaid.shark.mapper;

import com.ifsaid.shark.entity.OrderItem;

import java.util.List;

public interface OrderItemMapper {
    List<OrderItem> getOrderItemByNo(String orderNo);

    int saveOrderItem(OrderItem orderItem);
}
