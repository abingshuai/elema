package com.ifsaid.shark.schedule;

import com.ifsaid.shark.entity.Statistics;
import com.ifsaid.shark.mapper.OrderMapper;
import com.ifsaid.shark.mapper.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Component
public class Scheduled {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private StatisticsMapper statisticsMapper;

    @org.springframework.scheduling.annotation.Scheduled(cron = "1 0 0 * * ?")
    public void statisticsTotal(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date end = calendar.getTime();
        //统计的是昨天的数据
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date begin = calendar.getTime();
        java.sql.Date sqlDate = new java.sql.Date(calendar.getTime().getTime());
        Long orderCount = orderMapper.getOrderCountByDate(begin,end);
        BigDecimal salesVolume = orderMapper.getOrderPriceByDate(begin,end);
        Statistics statistics = new Statistics();
        statistics.setDate(sqlDate);
        statistics.setOrderCount(orderCount);
        statistics.setSalesVolume(salesVolume);
        statisticsMapper.insert(statistics);
    }

}
