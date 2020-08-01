package com.ifsaid.shark.mapper;

import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.domain.SearchParmas;
import com.ifsaid.shark.common.mapper.BaseMapper;
import com.ifsaid.shark.entity.Dealer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author lzb
 * @since 2020-05-12
 */
public interface DealerMapper extends BaseMapper<Dealer,Integer> {
    List<Dealer> commonSearch(Query query);
    List<Dealer> highSearch(Query query);

}