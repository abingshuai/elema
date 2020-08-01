package com.ifsaid.shark.service;


import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.domain.TableResultResponse;
import com.ifsaid.shark.common.service.BaseService;
import com.ifsaid.shark.entity.Product;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lzb
 * @since 2020-05-17
 */
public interface IProductService extends BaseService<Product,Integer> {
	TableResultResponse<Product> commonSearch(Query query);
	Integer createProduct(Product product);
	Integer deleteProduct(String id);
}
