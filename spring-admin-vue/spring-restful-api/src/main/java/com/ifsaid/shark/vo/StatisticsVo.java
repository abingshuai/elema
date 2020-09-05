package com.ifsaid.shark.vo;

import lombok.*;

import java.math.BigDecimal;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsVo implements java.io.Serializable{
    private Long userCount;
    private Long goodsCount;
    private Long orderCount;
    private BigDecimal orderPrice;

    public Long getUserCount() {
        return userCount;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    public Long getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Long goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}
