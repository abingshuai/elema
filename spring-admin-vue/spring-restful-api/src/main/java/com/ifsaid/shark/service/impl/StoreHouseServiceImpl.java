package com.ifsaid.shark.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.domain.TableResultResponse;
import com.ifsaid.shark.common.service.impl.BaseServiceImpl;
import com.ifsaid.shark.entity.Contact;
import com.ifsaid.shark.entity.Stock;
import com.ifsaid.shark.entity.StoreHouse;
import com.ifsaid.shark.mapper.StoreHouseMapper;
import com.ifsaid.shark.service.IStoreHouseService;
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
public class StoreHouseServiceImpl extends BaseServiceImpl<StoreHouse,Integer,StoreHouseMapper> implements IStoreHouseService {

    @Override
    public TableResultResponse<StoreHouse> commonSearch(Query query) {
        Page<StoreHouse> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<StoreHouse> list = baseMapper.commonSearch(query);
        return new TableResultResponse<StoreHouse>(result.getTotal(),list);
    }

    @Override
    public TableResultResponse<StoreHouse> highSearch(Query query) {
        Page<StoreHouse> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<StoreHouse> list = baseMapper.highSearch(query);

        return new TableResultResponse<StoreHouse>(result.getTotal(), list);
    }

    @Override
    public List<StoreHouse> chooseAvailableStoreHouse(Integer nums) {
        return baseMapper.chooseAvailableStoreHouse(nums);
    }
}
