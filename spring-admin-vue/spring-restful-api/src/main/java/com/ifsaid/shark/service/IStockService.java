package com.ifsaid.shark.service;


import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.domain.TableResultResponse;
import com.ifsaid.shark.common.service.BaseService;
import com.ifsaid.shark.entity.Stock;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzb
 * @since 2020-05-17
 */
public interface IStockService extends BaseService<Stock,Integer> {
	TableResultResponse<Stock> commonSearch(Query query);
	Integer handleInbound(Stock stock);
	Integer handleOutbound(Stock stock);
}
