package com.ifsaid.shark.service.impl;

import com.ifsaid.shark.entity.Statistics;
import com.ifsaid.shark.mapper.StatisticsMapper;
import com.ifsaid.shark.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements IStatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public List<Statistics> selectPerWeek() {
        return statisticsMapper.selectPerWeek();
    }
}
