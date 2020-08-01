package com.ifsaid.shark.service;

import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.domain.TableResultResponse;
import com.ifsaid.shark.common.service.BaseService;
import com.ifsaid.shark.entity.Dealer;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzb
 * @since 2020-05-12
 */
public interface IDealerService extends BaseService<Dealer,Integer> {
	TableResultResponse<Dealer> commonSearch(Query query);
	TableResultResponse<Dealer> highSearch(Query query);
	Integer createDealer(Dealer dealer);
	Integer updateDealer(Dealer dealer);
}
