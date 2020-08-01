package com.ifsaid.shark.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.domain.TableResultResponse;
import com.ifsaid.shark.common.service.impl.BaseServiceImpl;
import com.ifsaid.shark.entity.Dealer;
import com.ifsaid.shark.mapper.DealerMapper;
import com.ifsaid.shark.service.IDealerService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzb
 * @since 2020-05-12
 */
@Service
public class DealerServiceImpl extends BaseServiceImpl<Dealer,Integer,DealerMapper> implements IDealerService {

    @Override
    public TableResultResponse<Dealer> commonSearch(Query query) {
        Page<Dealer> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Dealer> list = baseMapper.commonSearch(query);

        return new TableResultResponse<Dealer>(result.getTotal(), list);
    }

    @Override
    public TableResultResponse<Dealer> highSearch(Query query) {
        Page<Dealer> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Dealer> list = baseMapper.highSearch(query);

        return new TableResultResponse<Dealer>(result.getTotal(), list);
    }

    @Override
    public Integer createDealer(Dealer dealer) {
        return baseMapper.insert(dealer);
    }

    @Override
    public Integer updateDealer(Dealer dealer) {
        return baseMapper.updateByPrimaryKey(dealer);
    }
}
