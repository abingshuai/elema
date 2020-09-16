package com.ifsaid.shark.mapper;

import com.ifsaid.shark.common.mapper.BaseMapper;
import com.ifsaid.shark.entity.Goods;
import com.ifsaid.shark.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface OrderMapper extends BaseMapper<Order, Integer> {
    List<Order> getAll();
    Set<Order> selectByKeywords(String keywords);

    Long getOrderCount();

    BigDecimal getOrderPrice();

    Long getOrderCountByDate(@Param("begin") Date begin,@Param("end") Date end);

    BigDecimal getOrderPriceByDate(@Param("begin") Date begin,@Param("end") Date end);

    List<Order> getOrderByStatus(String userId, Integer status);

    int updateOrderStatus(String userId, String orderNo, Integer status);
}
