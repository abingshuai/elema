package com.ifsaid.shark.service;

import com.ifsaid.shark.entity.Statistics;

import java.util.List;

public interface IStatisticsService {
    List<Statistics> selectPerWeek();
}
