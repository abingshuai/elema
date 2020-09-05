package com.ifsaid.shark.mapper;

import com.ifsaid.shark.entity.Statistics;

import java.util.List;

public interface StatisticsMapper {
    int insert(Statistics statistics);

    List<Statistics> selectPerWeek();
}
