package com.ifsaid.shark.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.domain.TableResultResponse;
import com.ifsaid.shark.common.service.impl.BaseServiceImpl;
import com.ifsaid.shark.entity.Dealer;
import com.ifsaid.shark.entity.Product;
import com.ifsaid.shark.mapper.ProductMapper;
import com.ifsaid.shark.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzb
 * @since 2020-05-17
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product,Integer, ProductMapper> implements IProductService {
	public TableResultResponse<Product> commonSearch(Query query){
        Page<Product> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Product> list = baseMapper.commonSearch(query);
        return new TableResultResponse<Product>(result.getTotal(),list);
    }

    @Override
    public Integer createProduct(Product product) {
        return baseMapper.createProduct(product);
    }

    @Override
    public Integer deleteProduct(String id) {
        return baseMapper.deleteProduct(id);
    }


}
