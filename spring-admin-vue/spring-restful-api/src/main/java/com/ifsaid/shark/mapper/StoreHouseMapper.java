package com.ifsaid.shark.mapper;


import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.mapper.BaseMapper;
import com.ifsaid.shark.entity.StoreHouse;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author lzb
 * @since 2020-05-17
 */
public interface StoreHouseMapper extends BaseMapper<StoreHouse,Integer> {
       List<StoreHouse> commonSearch(Query query);
       List<StoreHouse> highSearch(Query query);
       List<StoreHouse> chooseAvailableStoreHouse(Integer nums);
}