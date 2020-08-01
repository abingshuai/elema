package com.ifsaid.shark.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.domain.TableResultResponse;
import com.ifsaid.shark.common.service.impl.BaseServiceImpl;
import com.ifsaid.shark.entity.Product;
import com.ifsaid.shark.entity.Stock;
import com.ifsaid.shark.entity.StoreHouse;
import com.ifsaid.shark.mapper.ProductMapper;
import com.ifsaid.shark.mapper.StockMapper;
import com.ifsaid.shark.mapper.StoreHouseMapper;
import com.ifsaid.shark.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class StockServiceImpl extends BaseServiceImpl<Stock,Integer,StockMapper> implements IStockService {

    @Resource
    StoreHouseMapper storeHouseMapper;
    @Resource
    ProductMapper productMapper;
    @Override
    public TableResultResponse<Stock> commonSearch(Query query) {
        Page<Stock> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Stock> list = baseMapper.commonSearch(query);
        return new TableResultResponse<Stock>(result.getTotal(),list);
    }

    @Override
    public Integer handleInbound(Stock stock) {
        StoreHouse storeHouse = storeHouseMapper.selectByPrimaryKey(stock.getStoreId());
        Product product=productMapper.selectByPrimaryKey(stock.getProductId());
        if(storeHouse.getStock()==null){
            storeHouse.setStock(0);
        }
        storeHouse.setStock(storeHouse.getStock()+stock.getProductNums());
        product.setStatus(2);
        stock.setStatus(2);
        storeHouseMapper.updateByPrimaryKey(storeHouse);
        productMapper.updateByPrimaryKey(product);
        return baseMapper.updateByPrimaryKey(stock);
    }

    @Override
    public Integer handleOutbound(Stock stock) {
        StoreHouse storeHouse = storeHouseMapper.selectByPrimaryKey(stock.getStoreId());
        Product product=productMapper.selectByPrimaryKey(stock.getProductId());

        if(storeHouse.getStock()==null){
            storeHouse.setStock(0);
        }
        storeHouse.setStock(storeHouse.getStock()-stock.getProductNums());
        product.setStatus(3);
        stock.setStatus(3);
        product.setLocation(stock.getStoreHouseName());
        stock.setStoreHouseName(storeHouse.getName());
        storeHouseMapper.updateByPrimaryKey(storeHouse);
        productMapper.updateByPrimaryKey(product);
        return baseMapper.updateByPrimaryKey(stock);
    }
}
