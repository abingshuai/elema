package com.ifsaid.shark.mapper;


import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.mapper.BaseMapper;
import com.ifsaid.shark.entity.Stock;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author lzb
 * @since 2020-05-17
 */
public interface StockMapper extends BaseMapper<Stock,Integer> {
    List<Stock> commonSearch(Query query);
}