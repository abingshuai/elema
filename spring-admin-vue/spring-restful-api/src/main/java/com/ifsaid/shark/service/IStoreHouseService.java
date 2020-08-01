package com.ifsaid.shark.service;


import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.domain.TableResultResponse;
import com.ifsaid.shark.common.service.BaseService;
import com.ifsaid.shark.entity.StoreHouse;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzb
 * @since 2020-05-17
 */
public interface IStoreHouseService extends BaseService<StoreHouse,Integer> {
	TableResultResponse<StoreHouse> commonSearch(Query query);
	TableResultResponse<StoreHouse> highSearch(Query query);
	List<StoreHouse> chooseAvailableStoreHouse(Integer nums);
}
