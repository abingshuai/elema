package com.ifsaid.shark.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ifsaid.shark.common.domain.Query;
import com.ifsaid.shark.common.domain.TableResultResponse;
import com.ifsaid.shark.common.service.impl.BaseServiceImpl;

import com.ifsaid.shark.entity.Contact;
import com.ifsaid.shark.entity.Dealer;
import com.ifsaid.shark.mapper.ContactMapper;
import com.ifsaid.shark.service.IContactService;

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
public class ContactServiceImpl extends BaseServiceImpl<Contact,Integer, ContactMapper> implements IContactService {

    @Override
    public TableResultResponse<Contact> commonSearch(Query query) {
        Page<Contact> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Contact> list = baseMapper.commonSearch(query);
        return new TableResultResponse<Contact>(result.getTotal(),list);
    }

    @Override
    public TableResultResponse<Contact> highSearch(Query query) {
        Page<Contact> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Contact> list = baseMapper.highSearch(query);

        return new TableResultResponse<Contact>(result.getTotal(), list);
    }
}
